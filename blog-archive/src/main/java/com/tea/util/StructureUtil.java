/**
 * Created with IntelliJ IDEA.
 * User: 庭亮
 * Date: 15-4-13
 * Time: 上午3:26
 * To change this template use File | Settings | File Templates.
 */
package com.tea.util;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;

public class StructureUtil {
    /**
     * map转对象
     *
     * @param map
     *            源
     * @param obj
     *            目标
     * @return
     */
    public static Object MapToObject(Map map, Object obj) {
        Class<?> clz;
        try {
            clz = Class.forName(obj.getClass().getName());
            Field[] fields = clz.getDeclaredFields();
            Field field = null;
            Method thisMetd = null;
            Object type = null;
            for (int i = 0; i < fields.length; i++) {
                Iterator it = map.keySet().iterator();
                // 当前字段
                field = fields[i];
                // 设置私有属性为可访问
                field.setAccessible(true);
                String fieldName = field.getName();
                type = field.getType();
                while (it.hasNext()) {
                    String key = it.next().toString();
                    if (key.equalsIgnoreCase(fieldName)) {
                        // 设值
                        thisMetd = clz.getMethod(
                                "set" + firstCaseToUpper(field.getName()),
                                field.getType());
                        if (null != map.get(key)) {
                            if (type == java.math.BigDecimal.class) {
                                thisMetd.invoke(
                                        obj,
                                        StringUtils.isNotBlank(map.get(key)
                                                .toString().trim()) ? new BigDecimal(
                                                (int) Double.parseDouble((map
                                                        .get(key).toString())))
                                                : null);
                            } else if (type == java.sql.Date.class) {
                                thisMetd.invoke(
                                        obj,
                                        StringUtils.isNotBlank(map.get(key)
                                                .toString()) ? DateUtil
                                                .strToDateCN_yyyy_MM_dd(map
                                                        .get(key).toString())
                                                : null);
                            } else if (type == java.sql.Timestamp.class) {
                                // Class<? extends Object> fieldtype =
                                // map.get(key).getClass();
                                if (map.get(key).getClass() != java.sql.Timestamp.class) {
                                    if (map.get(key).getClass() == java.sql.Date.class) {
                                        thisMetd.invoke(
                                                obj,
                                                StringUtils.isNotBlank(map.get(
                                                        key).toString()) ? DateUtil
                                                        .getTimestamp((java.sql.Date) map
                                                                .get(key))
                                                        : null);
                                    } else if (map.get(key).getClass() == java.util.Date.class) {
                                        thisMetd.invoke(
                                                obj,
                                                StringUtils.isNotBlank(map.get(
                                                        key).toString()) ? DateUtil
                                                        .getTimestamp((java.util.Date) map
                                                                .get(key))
                                                        : null);
                                    } else {
                                        thisMetd.invoke(
                                                obj,
                                                StringUtils.isNotBlank(map.get(
                                                        key).toString()) ? DateUtil.getTimestamp(DateUtil
                                                        .strToDateCN_yyyy_MM_dd_HH_mm_ss(map
                                                                .get(key)
                                                                .toString()))
                                                        : null);
                                    }
                                } else {
                                    thisMetd.invoke(obj, map.get(key));
                                }
                            } else if (type == java.sql.Time.class) {
                                thisMetd.invoke(
                                        obj,
                                        StringUtils.isNotBlank(map.get(key)
                                                .toString()) ? new SimpleDateFormat(
                                                "HH:mm:ss").format(map.get(key)
                                                .toString()) : null);
                            } else if (type == java.util.Date.class) {
                                thisMetd.invoke(
                                        obj,
                                        StringUtils.isNotBlank(map.get(key)
                                                .toString()) ? DateUtil
                                                .strToDateCN_yyyy_MM_dd_HH_mm_ss(map
                                                        .get(key).toString()
                                                        .substring(0, 19))
                                                : null);
                            } else {
                                thisMetd.invoke(obj, StringUtils.isNotBlank(map
                                        .get(key).toString()) ? map.get(key)
                                        : null);
                            }
                        } else {
                            // thisMetd.invoke(obj, type);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * @param src
     *            源字符串
     * @return 字符串，将src的第一个字母转换为大写，src为空时返回null
     */
    public static String firstCaseToUpper(String src) {
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        } else {
            return null;
        }
    }
}
