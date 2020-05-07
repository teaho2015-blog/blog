//package net.teaho.blog.server.util;
//
//import net.sf.json.JsonConfig;
//import net.sf.json.processors.JsonValueProcessor;
//
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class DateJsonValueProcessor implements JsonValueProcessor {
//
//	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
//	private DateFormat dateFormat;
//
//	public DateJsonValueProcessor() {
//		this(DEFAULT_DATE_PATTERN);
//	}
//
//	public DateJsonValueProcessor(String datePattern) {
//		try {
//			dateFormat = new SimpleDateFormat(datePattern);
//		} catch (Exception ex) {
//			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
//		}
//	}
//
//	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
//		return process(value);
//	}
//
//	public Object processObjectValue(String key, Object value,
//			JsonConfig jsonConfig) {
//		return process(value);
//	}
//
//	private Object process(Object value) {
//		if (value instanceof Timestamp)
//			return dateFormat.format((Timestamp) value);
//		else if (value instanceof Date)
//			return dateFormat.format((Date) value);
//		else if (value == null)
//			return "";
//		else
//			return value.toString();
//	}
//
//}
