package net.teaho.blog.server.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Base64 {

	private static final int MAGIC_NUM_0X03 = 0x03;

	private static final int MAGIC_NUM_0X04 = 0x04;

	private static final int MAGIC_NUM_0X06 = 0x06;

	private static final int MAGIC_NUM_0X0F = 0x0F;

	private static final int MAGIC_NUM_0X30 = 0x30;

	private static final int MAGIC_NUM_0X3C = 0x3C;

	private static final int MAGIC_NUM_0X3F = 0x3F;

	private static final int MAGIC_NUM_0X7F = 0x7F;

	private static final int MAGIC_NUM_0XC0 = 0xC0;

	private static final int MAGIC_NUM_0XF0 = 0xF0;

	private Base64() {
	}

	public static final char[] encode(byte[] arg) {
		return encode(arg, 0, arg.length);
	}

	public static final byte[] decode(String arg) {
		return decode(arg.toCharArray());
	}

	public static final byte[] decode(char[] arg) {
		return decode(arg, 0, arg.length);
	}

	public static char[] encode(byte[] value, int startIndex, int endIndex) {

		if ((value == null) || (value.length < 1) || (startIndex >= endIndex)
				|| (startIndex < 0) || (endIndex > value.length)) {
			return new char[0];
		}

		int length = ((endIndex - startIndex) / MAGIC_NUM_0X03)
				* MAGIC_NUM_0X04;
		int n = (endIndex - startIndex) % MAGIC_NUM_0X03;
		int b1, b2, b3, i;

		if (n != 0) {
			length += MAGIC_NUM_0X04;
		}
		n = endIndex - n;
		char[] buf = new char[length];
		for (i = startIndex, length = 0; i < n; i += MAGIC_NUM_0X03, length += MAGIC_NUM_0X04) {
			b1 = value[i];
			b2 = value[i + 1];
			b3 = value[i + 2];
			buf[length] = BASE64_CHAR[(b1 >> 2) & MAGIC_NUM_0X3F];
			buf[length + 1] = BASE64_CHAR[((b1 << MAGIC_NUM_0X04) & MAGIC_NUM_0X30)
					| ((b2 >> MAGIC_NUM_0X04) & MAGIC_NUM_0X0F)];
			buf[length + 2] = BASE64_CHAR[((b2 << 2) & MAGIC_NUM_0X3C)
					| ((b3 >> MAGIC_NUM_0X06) & MAGIC_NUM_0X03)];
			buf[length + MAGIC_NUM_0X03] = BASE64_CHAR[b3 & MAGIC_NUM_0X3F];
		}
		switch (endIndex - n) {
		case 1:
			b1 = value[n];
			buf[length] = BASE64_CHAR[(b1 >> 2) & MAGIC_NUM_0X3F];
			buf[length + 1] = BASE64_CHAR[((b1 << MAGIC_NUM_0X04) & MAGIC_NUM_0X30)];
			buf[length + 2] = '=';
			buf[length + MAGIC_NUM_0X03] = '=';
			break;
		case 2:
			b1 = value[n];
			b2 = value[n + 1];
			buf[length] = BASE64_CHAR[(b1 >> 2) & MAGIC_NUM_0X3F];
			buf[length + 1] = BASE64_CHAR[((b1 << MAGIC_NUM_0X04) & MAGIC_NUM_0X30)
					| ((b2 >> MAGIC_NUM_0X04) & MAGIC_NUM_0X0F)];
			buf[length + 2] = BASE64_CHAR[((b2 << 2) & MAGIC_NUM_0X3C)];
			buf[length + MAGIC_NUM_0X03] = '=';
			break;
		default:
			break;
		}
		return buf;
	}

	public static byte[] decode(char[] value, int startIndex, int endIndex) {

		if ((value == null) || (value.length < 1) || (startIndex >= endIndex)
				|| (startIndex < 0) || (endIndex > value.length)
				|| (value[startIndex] == '=')) {
			return new byte[0];
		}
		int length, n, b1, b2, b3, b4, i;

		// ????????����??��?��??��??
		while (true) {
			if (value[--endIndex] != '=') {
				++endIndex;
				break;
			}
		}

		// ????"????"??"?��??"
		length = (endIndex - startIndex) % MAGIC_NUM_0X04;
		if (length > 0) {
			length--;
		}
		length += ((endIndex - startIndex) / MAGIC_NUM_0X04) * MAGIC_NUM_0X03;
		byte[] buf = new byte[length];

		n = endIndex - (endIndex - startIndex) % MAGIC_NUM_0X04;
		for (i = startIndex, length = 0; i < n; i += MAGIC_NUM_0X04, length += MAGIC_NUM_0X03) {
			b1 = BASE64_BYTE[value[i] & MAGIC_NUM_0X7F];
			b2 = BASE64_BYTE[value[i + 1] & MAGIC_NUM_0X7F];
			b3 = BASE64_BYTE[value[i + 2] & MAGIC_NUM_0X7F];
			b4 = BASE64_BYTE[value[i + MAGIC_NUM_0X03] & MAGIC_NUM_0X7F];
			buf[length] = (byte) ((b1 << 2) | ((b2 >> MAGIC_NUM_0X04)));
			buf[length + 1] = (byte) (((b2 << MAGIC_NUM_0X04) & MAGIC_NUM_0XF0) | (b3 >> 2));
			buf[length + 2] = (byte) (((b3 << MAGIC_NUM_0X06) & MAGIC_NUM_0XC0) | b4);
		}

		switch (endIndex - n) {
		case 2:
			b1 = BASE64_BYTE[value[i] & MAGIC_NUM_0X7F];
			b2 = BASE64_BYTE[value[i + 1] & MAGIC_NUM_0X7F];
			buf[length] = (byte) ((b1 << 2) | ((b2 >> MAGIC_NUM_0X04)));
			break;
		case MAGIC_NUM_0X03:
			b1 = BASE64_BYTE[value[i] & MAGIC_NUM_0X7F];
			b2 = BASE64_BYTE[value[i + 1] & MAGIC_NUM_0X7F];
			b3 = BASE64_BYTE[value[i + 2] & MAGIC_NUM_0X7F];
			buf[length] = (byte) ((b1 << 2) | ((b2 >> MAGIC_NUM_0X04)));
			buf[length + 1] = (byte) (((b2 << MAGIC_NUM_0X04) & MAGIC_NUM_0XF0) | (b3 >> 2));
			break;
		default:
			break;
		}

		return buf;
	}

	private static final char[] BASE64_CHAR = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', '+', '/' };

	private static final byte[] BASE64_BYTE = { (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0x3E, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0x3F, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37,
			(byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x3B, (byte) 0x3C,
			(byte) 0x3D, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x01,
			(byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06,
			(byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x0B,
			(byte) 0x0C, (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10,
			(byte) 0x11, (byte) 0x12, (byte) 0x13, (byte) 0x14, (byte) 0x15,
			(byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19, (byte) 0xFF,
			(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0x1A, (byte) 0x1B, (byte) 0x1C, (byte) 0x1D, (byte) 0x1E,
			(byte) 0x1F, (byte) 0x20, (byte) 0x21, (byte) 0x22, (byte) 0x23,
			(byte) 0x24, (byte) 0x25, (byte) 0x26, (byte) 0x27, (byte) 0x28,
			(byte) 0x29, (byte) 0x2A, (byte) 0x2B, (byte) 0x2C, (byte) 0x2D,
			(byte) 0x2E, (byte) 0x2F, (byte) 0x30, (byte) 0x31, (byte) 0x32,
			(byte) 0x33, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF };

	public static String encode(String input) {
		if (input == null)
			return null;
		char[] cs = encode(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cs.length; i++) {
			sb.append(cs[i]);
		}
		return sb.toString();
	}

//	public static void main(String[] args) {
//		String testStr = "<?xml version=\"1.0\" encoding=\"GB2312\"?><RESPONSE><ROUTE><VERSION>2.0</VERSION><SRC_ID>130500130009</SRC_ID><ROUTE_ID>130500000001</ROUTE_ID><OBJ_ID>130500231011</OBJ_ID><OBJ_RANGE>01</OBJ_RANGE><RET_FLAG>Y</RET_FLAG></ROUTE><DISPATCHER><VERSION>2.0</VERSION><REQU_ID>1011100000300011</REQU_ID><RESP_ID>2011100009000011</RESP_ID><SUBSYSPORT>2011100009000011</SUBSYSPORT></DISPATCHER><CONTENT><COMMAND>QUERY</COMMAND><VERSION>2.0</VERSION><QUERYID>87654321</QUERYID><USER>admin</USER><USERNAME>admin</USERNAME><TERMID>123456</TERMID><RET_FORMAT>VN,CN,VL,CT</RET_FORMAT><DATA APPID=\"A001\"><STATE>1/1</STATE><R><PHOTO VN=\"PIC\"><![CDATA[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]]></PHOTO></R></DATA></CONTENT></RESPONSE>";
//		System.out.println("\nOriginal XML String:");
//		System.out.println(testStr);
//
//		String base64 = new String(Base64.encode("���� ���� ����".getBytes()));
//		System.out.println("\nBase64 encoded XML String:");
//		System.out.println(base64);
//
//		String debase64 = new String(Base64.decode(base64));
//		System.out.println("\nBase64 decoded XML String:");
//		System.out.println(debase64);
//
//		String s = encode("http://10.41.50.31:8080/wlrk/query/TJ_Query.jsp?queryid=t_fact_whcdtj&group1=whcdtj&query=xzq&title=������Ա�Ļ��̶ȷֲ����");
//		System.out.println(s);
//		System.out.println(new String(decode(s)));
//
//		log.info("33333333333333333333333asdfsadf");
//	}
}
