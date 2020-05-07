package net.teaho.blog.server.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

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



//    /**
//     * 将list转换为json
//     *
//     * @param list
//     * @return
//     */
//    public static String list2Json(List list) {
//        JsonConfig cfg = new JsonConfig();
//        cfg.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
//        JSONArray jsonObject = JSONArray.fromObject(list, cfg);
//        String str = jsonObject.toString();
//        return str;
//    }
//
//    public static String map2Json(Map map) {
//        // JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(map);
//        JSONObject jsonObject = JSONObject.fromObject(map);
//        String str = jsonObject.toString();
//        return str;
//    }

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
                    || Character.isUpperCase(j)) {
                tmp.append(j);
            } else if (j < 256) {
                tmp.append("%");
                if (j < 16) {
                    tmp.append("0");
                }
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
