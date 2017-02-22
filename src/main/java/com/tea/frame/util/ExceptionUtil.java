/**
 *
 */
package com.tea.frame.util;

import java.io.IOException;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * @author zhenzhiqiang
 * 
 */
public class ExceptionUtil {
	public static void throwRuntimeException(String msg) {
		throw new RuntimeException(msg);
	}

	public static void throwRuntimeException(Throwable e) {
		if (e instanceof RuntimeException) {
			throw (RuntimeException) e;
		}

		throw new RuntimeException(e);
	}

	public static void throwRuntimeException(String msg, Exception e) {
		if (e instanceof RuntimeException) {
			throw (RuntimeException) e;
		}

		throw new RuntimeException(msg, e);
	}

	// TODO refactor this type of exception
	public static void throwRemoteCallException(Exception e) {
		// throw new RemoteAccessException(e.getMessage(), e);
		e.printStackTrace();
	}

	public static RuntimeException throwNotSupportException(String msg) {
		getCallerName(new Throwable());

		return new RuntimeException(getCallerName(new Throwable()) + " " + msg);
	}

	private static String getCallerName(Throwable throwable) {
		StackTraceElement[] traces = throwable.getStackTrace();

		if (traces.length < 1) {
			return null;
		}

		int callerLevel = 1;

		return traces[callerLevel].getClassName() + "."
				+ traces[callerLevel].getMethodName() + "("
				+ traces[callerLevel].getFileName() + ":"
				+ traces[callerLevel].getLineNumber() + ")";
	}
	
	/**
	 * ���쳣��Ϣת�����ַ�
	 * 
	 * @param e
	 * @return
	 * @throws java.io.IOException
	 */
	public static String toStr(Throwable e) {
		String[] causes = ExceptionUtils.getRootCauseStackTrace(e);
		StringBuffer sb = new StringBuffer();
		if (causes != null) {
			for (int i = 0; i < causes.length; i++) {
				String cause = causes[i];
				sb.append(cause);
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	// public static void checkValidation(String msg) throws ValidatedException
	// {
	// throw new ValidatedException(msg);
	// }
}
