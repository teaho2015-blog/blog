package com.tea.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unchecked")
public class StringUtil {

    public static char[] getCharArray(StringBuffer sb) {
        if (sb == null) {
            return (new char[0]);
        }

        char[] s = new char[sb.length()];

        for (int i = 0; i < sb.length(); i++) {
            s[i] = sb.charAt(i);
        }

        return s;
    }

    /**
     * split a string to HashMap with key and value. the string 's format like
     * this: key1=value1;key2=value2;ke3=value3 and the result is : HashMap's
     * size is 3 (key1,value1),(key2,value2),(key3,value3)
     */
    public static Map splitToKeyValuePair(String str, String pairSeparator,
                                          String keyValueSeparator) {
        LinkedHashMap linkedMap = new LinkedHashMap();
        String pairSep = org.apache.commons.lang.StringUtils
                .isEmpty(pairSeparator) ? ";" : pairSeparator;
        String keyValueSep = org.apache.commons.lang.StringUtils
                .isEmpty(keyValueSeparator) ? "=" : keyValueSeparator;

        if (null == str) {
            return linkedMap;
        }

        String[] pairs = org.apache.commons.lang.StringUtils
                .split(str, pairSep);

        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];

            // String[] keyValue =
            // org.apache.commons.lang.StringUtils.split(pair,
            // keyValueSep);
            // if (2 != keyValue.length) {
            // continue;
            // }
            String[] keyValue = new String[2];

            if (pair.indexOf(keyValueSep) > -1) {
                int firstPos = pair.indexOf(keyValueSep);
                keyValue[0] = pair.substring(0, firstPos);
                keyValue[1] = pair.substring(firstPos + 1);
            }

            linkedMap.put(keyValue[0], keyValue[1]);
        }

        return linkedMap;
    }

    public static String joinKeyValueMap(Map keyValueMap, String pairSeparator,
                                         String keyValueSeparator) {
        String pairSep = org.apache.commons.lang.StringUtils
                .isEmpty(pairSeparator) ? ";" : pairSeparator;
        String keyValueSep = org.apache.commons.lang.StringUtils
                .isEmpty(keyValueSeparator) ? "=" : keyValueSeparator;

        StringBuffer sb = new StringBuffer();
        Set keySet = keyValueMap.keySet();
        boolean isFirst = true;

        for (Iterator keyIt = keySet.iterator(); keyIt.hasNext();) {
            String key = (String) keyIt.next();
            Object valueObj = keyValueMap.get(key);

            if (null == valueObj) {
                valueObj = "";
            }

            if (!isFirst) {
                sb.append(pairSep);
            }

            sb.append(key).append(keyValueSep).append(valueObj);
            isFirst = false;
        }

        return sb.toString();
    }

    /**
     * get the true length of the string, a chinese's length is 2 eg. "a大" 's
     * char length is 3. while "ab" 's char length is 2
     *
     * @param str
     * @return
     */
    public static int getCharLength(String str) {
        int length = 0;

        try {
            length = (new String(str.getBytes("GBK"), "iso-8859-1")).length();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return length;
    }

    /**
     * 把带中文的字符串按targetByteSize截取（不会造成双字节字符的误截）
     *
     * @param origiStr
     * @param targetByteSize
     * @param detectUnicodeLength
     *            每次截取的字符长度（unicode字符数)
     * @return
     */
    public static String truncateToByteSize(String origiStr,
                                            int targetByteSize, int detectUnicodeLength) {
        if (null == origiStr) {
            return null;
        }

        int origiSize = getCharLength(origiStr);

        if (origiSize <= targetByteSize) {
            return origiStr;
        } else {
            String targetStr = origiStr;

            while (getCharLength(targetStr) > targetByteSize) {
                if ((targetStr.length() - detectUnicodeLength) < 0) {
                    targetStr = "";

                    break;
                }

                targetStr = targetStr.substring(0, targetStr.length()
                        - detectUnicodeLength);
            }

            return targetStr;
        }
    }

    public static void main(String[] arg) {

    }

    public static List json2List(String jsonStrs, String pairSeparator) {
        String pairSep = org.apache.commons.lang.StringUtils
                .isEmpty(pairSeparator) ? ";" : pairSeparator;

        String[] jsonStrArray = jsonStrs.split(pairSep);
        List list = new ArrayList();
        for (int i = 0; i < jsonStrArray.length; i++) {
            String jsonStr = jsonStrArray[i];
            Map map = json2Map(jsonStr);
            list.add(map);
        }
        return list;
    }

    public static String list2Json(List jsonList, String pairSeparator) {
        String pairSep = org.apache.commons.lang.StringUtils
                .isEmpty(pairSeparator) ? ";" : pairSeparator;

        String jsonStrs = "";
        for (Iterator iterator = jsonList.iterator(); iterator.hasNext();) {
            Map map = (Map) iterator.next();
            String jsonStr = map2Json(map);
            if (jsonStrs.equals("")) {
                jsonStrs = jsonStr;
            } else {
                jsonStrs = jsonStrs + pairSep + jsonStr;
            }
        }

        return jsonStrs;
    }

    /**
     * 将list转换为json
     *
     * @param list
     * @return
     */
    public static String list2Json(List list) {
        JsonConfig cfg = new JsonConfig();
        cfg.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
        JSONArray jsonObject = JSONArray.fromObject(list, cfg);
        String str = jsonObject.toString();
        return str;
    }

    public static String map2Json(Map map) {
        // JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(map);
        JSONObject jsonObject = JSONObject.fromObject(map);
        String str = jsonObject.toString();
        return str;
    }

    /**
     * map2json简单版，为了提高查询新能而增加
     * @param map
     * @return
     */
    public static String map2Json2(Map map) {
        if (null == map) {
            return "{}";
        }
        StringBuffer sb = new StringBuffer("{");
        for (Iterator entries = map.entrySet().iterator(); entries.hasNext();) {
            Map.Entry entry = (Map.Entry) entries.next();
            Object k = entry.getKey();
            Object v = entry.getValue();
            String key = (k instanceof String) ? (String) k : String.valueOf(k);
//			if(v instanceof Timestamp){
//				continue;
//			}
            String value = (v instanceof String) ? (String) v : String
                    .valueOf(v);
            sb.append("\"").append(key).append("\":\"").append(StringEscapeUtils.escapeJavaScript(value))
                    .append("\"").append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}");
        return sb.toString();
    }
    //替换换行字符
	/*static String InsteadWhiteSpace(String s) {        
		    StringBuffer sb = new StringBuffer ();        
		    for (int i=0; i<s.length(); i++) {        
		      
		        char c = s.charAt(i);        
		        switch (c) {        
		        case '\n':        
		            sb.append("\\n");        
		            break;        
		        case '\r':        
		            sb.append("\\r");        
		            break;        
		        case '\t':        
		            sb.append("\\t");        
		            break;        
		        default:        
		            sb.append(c);        
		        }   
		    }
		    return sb.toString();        
		 }   */

    public static Map json2Map(String jsonStr) {
        //	jsonStr =string2Json(jsonStr);
        //jsonStr = jsonStr.replaceAll("'([\\r\\n])[\\s]+'", "");

        JSONObject jsonObject = JSONObject.fromObject(jsonStr);

        Map classMap = new HashMap();
        Map props = JSONUtils.getProperties(jsonObject);
        for (Iterator iterator = props.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object value = jsonObject.get(key);
            // Object value = props.get(key);

            if (value instanceof JSONObject) {
                JSONObject tempJsonObject = (JSONObject) value;

                // 需要转换成日期类型对象
                boolean isDateType = true;
                String[] datePropNames = new String[] { "date", "month", "year" };
                for (int i = 0; i < datePropNames.length; i++) {
                    if (!tempJsonObject.containsKey(datePropNames[i])) {
                        isDateType = false;
                        break;
                    }
                }

                if (isDateType) {
                    classMap.put(key, Date.class);
                }

            }
            // else if (NumberUtils.isNumber("" + value)) {
            // try {
            // Integer.parseInt("" + value);
            // classMap.put(key, Integer.class);
            // break;
            // } catch (NumberFormatException e) {
            // // nothing todo
            // }
            // try {
            // Float.parseFloat("" + value);
            // classMap.put(key, Float.class);
            // break;
            // } catch (NumberFormatException e) {
            // // nothing todo
            // }
            // try {
            // Double.parseDouble("" + value);
            // classMap.put(key, Double.class);
            // break;
            // } catch (NumberFormatException e) {
            // // nothing todo
            // }
            //
            // }
        }

        Map map = (Map) JSONObject.toBean(jsonObject, LinkedHashMap.class,
                classMap);

        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object obj = map.get(key);
            if (obj instanceof JSONObject && ((JSONObject) obj).isNullObject()) {
                map.put(key, null);
            }
        }

        // net.sf.json.JSONObject jsonObject = (JSONObject) JSONSerializer
        // .toJSON(jsonStr);
        // Map map = new HashMap();
        // for (Iterator iterator = jsonObject.keys(); iterator.hasNext();) {
        // String key = (String) iterator.next();
        // Object value = jsonObject.get(key);
        // map.put(key, value);
        // }

        return map;
    }

    /**
     * json转map,可定义key全为大写或小写
     * @param jsonStr
     * @param letter  大(1)/小(2)写
     * @return
     */
    public static Map json2Map(String jsonStr,String letter) {

        JSONObject jsonObject = JSONObject.fromObject(jsonStr);

        Map classMap = new HashMap();
        Map props = JSONUtils.getProperties(jsonObject);
        for (Iterator iterator = props.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object value = jsonObject.get(key);
            // Object value = props.get(key);

            if (value instanceof JSONObject) {
                JSONObject tempJsonObject = (JSONObject) value;

                // 需要转换成日期类型对象
                boolean isDateType = true;
                String[] datePropNames = new String[] { "date", "month", "year" };
                for (int i = 0; i < datePropNames.length; i++) {
                    if (!tempJsonObject.containsKey(datePropNames[i])) {
                        isDateType = false;
                        break;
                    }
                }

                if (isDateType) {
                    classMap.put(key, Date.class);
                }

            }
        }

        props = (Map) JSONObject.toBean(jsonObject, LinkedHashMap.class,
                classMap);
        Map map = new HashMap();
        for (Iterator iterator = props.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object value = props.get(key);
            if(StringUtils.isNotBlank(letter)){
                map.put("1".equals(letter)?key.toUpperCase():key.toLowerCase(), value.equals("")?null:props.get(key));
            }
            else{
                map.put(key,value.equals("")?null:props.get(key));
            }
        }

        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object obj = map.get(key);
            if (obj instanceof JSONObject && ((JSONObject) obj).isNullObject()) {
                if(StringUtils.isNotBlank(letter)){
                    map.put("1".equals(letter)?key.toUpperCase():key.toLowerCase(), null);
                }
                else{
                    map.put(key, null);
                }
            }
        }
        return map;
    }

    /**
     * 默认的将对象转换为字符串
     *
     * @param object
     * @return
     */
    public static String objectToString(Object object) {
        if (object == null) {
            return "null";
        }
        String value = null;
        if (object instanceof Date) {
            value = convertDate(object, "yyyy-MM-dd HH:mm:ss");
        } else if (object instanceof String) {
            value = (String) object;
        } else {
            // TODO
            value = String.valueOf(object);
        }
        return value;
    }

    /**
     * 根据模式将对象转换为字符串
     *
     * @param object
     * @param pattern
     *            数字的模式参考 java.text.DecimalFormat,日期的模式参考
     *            java.text.SimpleDateFormat
     * @return "" if object is null
     */
    public static String objectToString(Object object, String pattern) {
        return objectToString(object, pattern, null);
    }

    /**
     * 根据模式将对象转换为字符串
     *
     * @param object
     * @param pattern
     *            数字的模式参考 java.text.DecimalFormat,日期的模式参考
     *            java.text.SimpleDateFormat
     * @param defaultValue
     * @return defaultValue if object is null
     */
    public static String objectToString(Object object, String pattern,
                                        String defaultValue) {

        if (object == null) {
            if (defaultValue == null) {
                return "";
            } else {
                return defaultValue;
            }
        }
        String value = null;
        if (object instanceof Date) {
            value = convertDate(object, pattern);
        } else if (object instanceof String) {
            value = (String) object;
        } else if (object instanceof Number) {
            value = convertNumber(object, pattern);
        } else {
            // TODO
            value = String.valueOf(object);
        }
        return value;
    }

    /**
     * 转换日期类型
     *
     * @param value
     * @return
     */
    private static String convertDate(Object object, String pattern) {
        String dateFormat = null;
        if ("default".equals(pattern)) {
            dateFormat = "yyyy-MM-dd";
        } else {
            dateFormat = StringUtils.isBlank(pattern) ? "yyyy-MM-dd" : pattern;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.format(object);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "null";
        }

    }

    private static String convertNumber(Object object, String pattern) {
        String decimalPattern = null;
        if ("default".equals(pattern)) {
            decimalPattern = "###";
        } else {
            decimalPattern = StringUtils.isBlank(pattern) ? "###" : pattern;
        }

        DecimalFormat formatter = new DecimalFormat(decimalPattern);
        try {
            return formatter.format(object);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "null";
        }
    }

    public static String getInnerTextFormHtmlElement(String elementType,
                                                     String node) {
        if (node != null && node.indexOf("<" + elementType) > -1) {
            node = node.replaceAll("<\\s*" + elementType + "\\s+([^>]*)\\s*>",
                    "");
            node = node.replaceAll("</\\s*" + elementType + "\\s*>", "");
        }
        return node;
    }

    /**
     *
     * @param encodeStr
     * @return
     */
    public static String encodeToGbk(String encodeStr) {
        try {
            encodeStr = URLEncoder.encode(encodeStr, "GBK");
        } catch (UnsupportedEncodingException e) {
            ExceptionUtil.throwRuntimeException(e);
        }
        return encodeStr;
    }

    /**
     *
     * @param encodeStr
     * @return
     */
    public static String decodeByGbk(String decodeStr) {
        try {
            decodeStr = URLDecoder.decode(decodeStr, "GBK");
        } catch (UnsupportedEncodingException e) {
            ExceptionUtil.throwRuntimeException(e);
        }
        return decodeStr;
    }

    /**
     * @param type
     *            分离的标志
     * @param value
     *            传入的字符串
     * @return
     */
    public static String[] splitStringToArray(String type, String value) {
        String[] splitValue = value.split("\\" + type);
        return splitValue;
    }

    public static int[] StringArrayToIntArray(String type, String numString){
        String[] iString = numString.split("\\" + type);
        int[] arr = new int[iString.length];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = Integer.parseInt(iString[i]);
        }
        return arr;
    }


    /**
     * java版的escape<br>
     * 中文编码成 %u1234 格式
     *
     * @param src
     * @return
     */
    public static final String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * java版的unescape<br>
     * %u1234 格式成中文编码
     *
     * @param src
     * @return
     */
    public static final String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(
                            src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(
                            src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 从带有分隔符的字符串里找出目标字符串 如从style里找到width width:100px;height:425px
     *
     * @param str
     *            原字符串
     * @param searchStr
     *            待查找目标字符串
     * @param split
     *            分隔符
     * @param lowOrUpper
     *            对原字符串进行大小写转化 0：小写；1：大写；默认不做转化
     * @return
     */
    public static String searchSplitStr(String str, String searchStr,
                                        String split, String lowOrUpper) {
        String schStr = null;
        String convertStr = "";
        if ("0".equals(lowOrUpper)) {
            convertStr = StringUtils.lowerCase(str);
            ;
        } else if ("1".equals(lowOrUpper)) {
            convertStr = StringUtils.upperCase(str);
        } else {
            convertStr = str;
        }
        String[] ss = convertStr.split(split);
        if (ss != null && ss.length > 0) {
            for (String f : ss) {
                if (StringUtils.indexOf(f, searchStr) != -1) {
                    schStr = f;
                    break;
                }
            }
        }
        return schStr;
    }

}
