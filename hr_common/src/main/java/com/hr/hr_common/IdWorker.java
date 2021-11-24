package com.hr.hr_common;

/**
 * 1 bit 保留
 * 41 bit 时间戳 精确到毫秒 最多可以支持69年的跨度
 * 5 bit 机器ID 最多支持32个节点
 * 5 bit 业务编码 最多支持32个节点
 * 12 bit 毫秒内的计数器 每个节点每毫秒最多产生2的4096个ID
 */
public class IdWorker {
}
