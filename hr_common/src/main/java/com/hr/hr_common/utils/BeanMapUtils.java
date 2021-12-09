package com.hr.hr_common.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

public class BeanMapUtils {

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws Exception {
        T bean = clazz.getDeclaredConstructor().newInstance();
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
