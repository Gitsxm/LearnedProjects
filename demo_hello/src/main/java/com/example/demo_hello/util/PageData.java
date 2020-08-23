package com.example.demo_hello.util;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class PageData extends HashMap implements Map {

    private Map map;
    private HttpServletRequest request;

    /**
     * http 请求转 map
     *
     * @param request
     */
    public PageData(HttpServletRequest request) {
        // 参数Map
        Map<?, ?> properties = request.getParameterMap();
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<?> entries = properties.entrySet().iterator();

        Map.Entry<String, Object> entry;
        String name = "";
        String value = "";
        Object valueObj = null;
        while (entries.hasNext()) {
            entry = (Map.Entry<String, Object>) entries.next();
            name = (String) entry.getKey();
            valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        this.map = returnMap;
        this.request = request;
    }

    /**
     * 无参构造
     */
    public PageData() {
        this.map = new HashMap();
    }

    /**
     * 参数 hashmap 构造
     *
     * @param hashMap
     */
    public PageData(HashMap hashMap) {
        this.map = hashMap;
    }

    /**
     * 返回 String 类型 value
     *
     * @param key
     * @return
     */
    public String getString(Object key) {
        return (String) map.get(key);
    }

    /**
     * 重写 get 方法
     *
     * @param key
     * @return
     */
    @Override
    public Object get(Object key) {
        Object obj = null;
        if (map.get(key) instanceof Object[]) {
            Object[] arr = (Object[]) map.get(key);
            obj = request == null ? arr : request.getParameter((String) key) == null ? arr : arr[0];
        } else {
            obj = map.get(key);
        }
        return obj;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    @Override
    public void putAll(Map m) {
        map.putAll(m);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set keySet() {
        return map.keySet();
    }

    @Override
    public Collection values() {
        return map.values();
    }

    @Override
    public Set<Entry> entrySet() {
        return map.entrySet();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Object replace(Object key, Object value) {
        return map.replace(key, value);
    }
}
