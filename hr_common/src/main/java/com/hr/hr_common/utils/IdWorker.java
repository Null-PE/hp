package com.hr.hr_common.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 1 bit 保留
 * 41 bit 时间戳 精确到毫秒 最多可以支持69年的跨度
 * 5 bit 机器ID 最多支持32个节点
 * 5 bit 业务编码 最多支持32个节点
 * 12 bit 毫秒内的计数器 每个节点每毫秒最多产生2的4096个ID
 */
public class IdWorker {
    // 时间起始标记点
    private final static long twepoch = 1231231231234L;
    // 机器标识位数
    private final static long workerIdBits = 5L;
    // 数据中心标识位数
    private final static long datacenterIdBits = 5L;
    // 机器ID最大值
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 数据中心ID最大值
    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    // 毫秒内自增
    private final static long sequenceBits = 12L;
    // 机器ID 左移
    private final static long workerIdShift = sequenceBits;
    // 数据中心 左移
    private final static long datacenterIdShift = sequenceBits + workerIdBits;
    // 时间毫秒 左移
    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);
    // 上次ID时间戳
    private static long lastTimestamp = -1L;
    // 并发控制
    private long sequence = 0L;
    // 数据标识ID
    private final long datacenterId;

    private final long workerId;

    public IdWorker() {
        this.datacenterId = getDataCenterId(maxDatacenterId);
        this.workerId = getMaxWorkerId(datacenterId, maxWorkerId);
    }

    public IdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker ID can't be greater than %d or less than 0", maxWorkerId));
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter ID can't be greater than %d or less than 0", maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取下一个ID
     * @return 新ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内 +1
            sequence = (sequence + 1) & sequence;
            if (sequence == 0) {
                // 当前毫秒内满则等待至下一秒
                timestamp = tilNextmillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        // 组合ID
        return  ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    private long tilNextmillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }
    private long timeGen() {
        return System.currentTimeMillis();
    }

    protected static long getDataCenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF00 & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDatacenterId + 1);
            }
        } catch (Exception e) {
            System.out.println(" getDatacenterId: " + e.getMessage());
        }
        return id;
    }

    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            mpid.append(name.split("@")[0]);
        }
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }
}
