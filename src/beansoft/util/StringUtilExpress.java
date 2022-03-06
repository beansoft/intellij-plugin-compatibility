/*
 * @(#)StringUtil.java	1.0 2005-10-19
 *
 * Copyright 2003 - 2006 BeanSoft Studio. All rights reserved.
 */

package beansoft.util;

import com.intellij.openapi.util.SystemInfo;

import java.text.ParseException;
import java.util.Date;


/**
 * StringUtilExpress, 字符串工具类, 一些方便的字符串工具方法. 不包含 Servlet 处理工具.
 * Used by Applet.
 *
 * Dependencies: None.
 *
 * @author beansoft
 * @version 1.1 2005-10-19
 */
public class StringUtilExpress {
	/**
	 * 获取类路径中的资源文件的物理文件路径.
	 * NOTE: 仅在 Win32 平台下测试通过开发.
	 * @date 2005.10.16A
	 * @param resourcePath 资源路径
	 * @return 配置文件路径
	 */
	public static String getRealFilePath(String resourcePath) {
		java.net.URL inputURL = StringUtilExpress.class
				.getResource(resourcePath);

		String filePath = inputURL.getFile();

		// For windows platform, the filePath will like this:
		// /E:/Push/web/WEB-INF/classes/studio/beansoft/smtp/MailSender.ini
		// So must remove the first /

		if(SystemInfo.isWindows && filePath.startsWith("/")) {
			filePath = filePath.substring(1);
		}

		return filePath;
	}


	/**
	 * 将字符串转换为 int.
	 *
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果数字
	 */
	public static int parseInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	/**
	 * 格式化日期到日时分秒时间格式的显示. d日 HH:mm:ss
	 *
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToDHMSString(java.util.Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"d日 HH:mm:ss");

		return dateFormat.format(date);

	}

	/**
	 * 格式化日期到时分秒时间格式的显示.
	 *
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToHMSString(java.util.Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"HH:mm:ss");

		return dateFormat.format(date);

	}

	/**
	 * 将时分秒时间格式的字符串转换为日期.
	 *
	 * @param input
	 * @return
	 */
	public static Date parseHMSStringToDate(String input) {
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"HH:mm:ss");

		try {
			return dateFormat.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 格式化日期到 Mysql 数据库日期格式字符串的显示.
	 *
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToMysqlString(java.util.Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		return dateFormat.format(date);

	}

	/**
	 * 将 Mysql 数据库日期格式字符串转换为日期.
	 *
	 * @param input
	 * @return
	 */
	public static Date parseStringToMysqlDate(String input) {
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		try {
			return dateFormat.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 返回时间字符串, 可读形式的, M月d日 HH:mm 格式. 2004-09-22, LiuChangjiong
	 *
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToMMddHHmm(java.util.Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"M月d日 HH:mm");

		return dateFormat.format(date);
	}

	/**
	 * 返回时间字符串, 可读形式的, yy年M月d日HH:mm 格式. 2004-10-04, LiuChangjiong
	 *
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToyyMMddHHmm(java.util.Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yy年M月d日HH:mm");

		return dateFormat.format(date);
	}

	/**
	 * 生成一个 18 位的 yyyyMMddHHmmss.SSS 格式的日期字符串.
	 *
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String genTimeStampString(Date date) {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss.SSS");
		return df.format(date);
	}

	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, 并以大小写敏感方式进行查找
	 *
	 * @param source
	 *            需要替换的源字符串
	 * @param oldStr
	 *            需要被替换的老字符串
	 * @param newStr
	 *            替换为的新字符串
	 */
	public static String replace(String source, String oldStr, String newStr) {
		return replace(source, oldStr, newStr, true);
	}

	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, matchCase 为是否设置大小写敏感查找
	 *
	 * @param source
	 *            需要替换的源字符串
	 * @param oldStr
	 *            需要被替换的老字符串
	 * @param newStr
	 *            替换为的新字符串
	 * @param matchCase
	 *            是否需要按照大小写敏感方式查找
	 */
	public static String replace(String source, String oldStr, String newStr,
								 boolean matchCase) {
		if (source == null) {
			return null;
		}
		// 首先检查旧字符串是否存在, 不存在就不进行替换
		if (source.toLowerCase().indexOf(oldStr.toLowerCase()) == -1) {
			return source;
		}
		int findStartPos = 0;
		int a = 0;
		while (a > -1) {
			int b = 0;
			String str1, str2, str3, str4, strA, strB;
			str1 = source;
			str2 = str1.toLowerCase();
			str3 = oldStr;
			str4 = str3.toLowerCase();
			if (matchCase) {
				strA = str1;
				strB = str3;
			} else {
				strA = str2;
				strB = str4;
			}
			a = strA.indexOf(strB, findStartPos);
			if (a > -1) {
				b = oldStr.length();
				findStartPos = a + b;
				StringBuffer bbuf = new StringBuffer(source);
				source = bbuf.replace(a, a + b, newStr) + "";
				// 新的查找开始点位于替换后的字符串的结尾
				findStartPos = findStartPos + newStr.length() - b;
			}
		}
		return source;
	}

	/**
	 * 清除字符串结尾的空格.
	 *
	 * @param input
	 *            String 输入的字符串
	 * @return 转换结果
	 */
	public static String trimTailSpaces(String input) {
		if (isEmpty(input)) {
			return "";
		}

		String trimedString = input.trim();

		if (trimedString.length() == input.length()) {
			return input;
		}

		return input.substring(0, input.indexOf(trimedString)
				+ trimedString.length());
	}

	/**
	 * Change the null string value to "", if not null, then return it self, use
	 * this to avoid display a null string to "null".
	 *
	 * @param input
	 *            the string to clear
	 * @return the result
	 */
	public static String clearNull(String input) {
		return isEmpty(input) ? "" : input;
	}

	/**
	 * Return the limited length string of the input string (added at:April 10,
	 * 2004).
	 *
	 * @param input
	 *            String
	 * @param maxLength
	 *            int
	 * @return String processed result
	 */
	public static String limitStringLength(String input, int maxLength) {
		if (isEmpty(input))
			return "";

		if (input.length() <= maxLength) {
			return input;
		} else {
			return input.substring(0, maxLength - 3) + "...";
		}

	}

	/**
	 * 将字符串转换为一个 JavaScript 的 alert 调用. eg: htmlAlert("What?"); returns
	 * &lt;SCRIPT language="JavaScript"&gt;alert("What?")&lt;/SCRIPT&gt;
	 *
	 * @param message
	 *            需要显示的信息
	 * @return 转换结果
	 */
	public static String scriptAlert(String message) {
		return "<SCRIPT language=\"JavaScript\">alert(\"" + message
				+ "\");</SCRIPT>";
	}

	/**
	 * 将字符串转换为一个 JavaScript 的 document.location 改变调用. eg: htmlAlert("a.jsp");
	 * returns &lt;SCRIPT
	 * language="JavaScript"&gt;document.location="a.jsp";&lt;/SCRIPT&gt;
	 *
	 * @param url
	 *            需要显示的 URL 字符串
	 * @return 转换结果
	 */
	public static String scriptRedirect(String url) {
		return "<SCRIPT language=\"JavaScript\">document.location=\"" + url
				+ "\";</SCRIPT>";
	}

	/**
	 * 返回脚本语句 &lt;SCRIPT language="JavaScript"&gt;history.back();&lt;/SCRIPT&gt;
	 *
	 * @return 脚本语句
	 */
	public static String scriptHistoryBack() {
		return "<SCRIPT language=\"JavaScript\">history.back();</SCRIPT>";
	}

	/**
	 * 滤除帖子中的危险 HTML 代码, 主要是脚本代码, 滚动字幕代码以及脚本事件处理代码
	 *
	 * @param content
	 *            需要滤除的字符串
	 * @return 过滤的结果
	 */
	public static String replaceHtmlCode(String content) {
		if (isEmpty(content)) {
			return "";
		}
		// 需要滤除的脚本事件关键字
		String[] eventKeywords = { "onmouseover", "onmouseout", "onmousedown",
				"onmouseup", "onmousemove", "onclick", "ondblclick",
				"onkeypress", "onkeydown", "onkeyup", "ondragstart",
				"onerrorupdate", "onhelp", "onreadystatechange", "onrowenter",
				"onrowexit", "onselectstart", "onload", "onunload",
				"onbeforeunload", "onblur", "onerror", "onfocus", "onresize",
				"onscroll", "oncontextmenu" };
		content = replace(content, "<script", "&ltscript", false);
		content = replace(content, "</script", "&lt/script", false);
		content = replace(content, "<marquee", "&ltmarquee", false);
		content = replace(content, "</marquee", "&lt/marquee", false);
		content = replace(content, "\r\n", "<BR>");
		// 滤除脚本事件代码
		for (int i = 0; i < eventKeywords.length; i++) {
			content = replace(content, eventKeywords[i],
					"_" + eventKeywords[i], false); // 添加一个"_", 使事件代码无效
		}
		return content;
	}

	/**
	 * 滤除 HTML 代码 为文本代码.
	 */
	public static String replaceHtmlToText(String input) {
		if (isEmpty(input)) {
			return "";
		}
		return setBr(setTag(input));
	}

	/**
	 * 滤除 HTML 标记.
	 * 因为 XML 中转义字符依然有效, 因此把特殊字符过滤成中文的全角字符.
	 * @author beansoft
	 * @param s 输入的字串
	 * @return 过滤后的字串
	 */
	public static String setTag(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		char ch;
		for (int i = 0; i < j; i++) {
			ch = s.charAt(i);
			if (ch == '<') {
//				stringbuffer.append("&lt");
				stringbuffer.append("〈");
			} else if (ch == '>') {
//				stringbuffer.append("&gt");
				stringbuffer.append("〉");
			} else if (ch == '&') {
//				stringbuffer.append("&amp");
				stringbuffer.append("〃");
			} else if (ch == '%') {
//				stringbuffer.append("%%");
				stringbuffer.append("※");
			} else {
				stringbuffer.append(ch);
			}
		}

		return stringbuffer.toString();
	}

	/** 滤除 BR 代码 */
	public static String setBr(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++) {

			if (s.charAt(i) == '\n' || s.charAt(i) == '\r') {
				continue;
			} else {
				stringbuffer.append(s.charAt(i));
			}
		}

		return stringbuffer.toString();
	}

	/** 滤除空格 */
	public static String setNbsp(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++) {
			if (s.charAt(i) == ' ') {
				stringbuffer.append("&nbsp;");
			} else {
				stringbuffer.append(s.charAt(i) + "");
			}
		}
		return stringbuffer.toString();
	}

	/**
	 * 判断字符串是否全是数字字符.
	 *
	 * @param input
	 *            输入的字符串
	 * @return 判断结果, true 为全数字, false 为还有非数字字符
	 */
	public static boolean isNumeric(String input) {
		if (isEmpty(input)) {
			return false;
		}

		for (int i = 0; i < input.length(); i++) {
			char charAt = input.charAt(i);

			if (!Character.isDigit(charAt)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 转换由表单读取的数据的内码(从 ISO8859 转换到 gb2312).
	 *
	 * @param input
	 *            输入的字符串
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String toChi(String input) {
		try {
			byte[] bytes = input.getBytes("ISO8859-1");
			return new String(bytes, "GBK");
		} catch (Exception ex) {
		}
		return input;
	}

	/**
	 * 转换由表单读取的数据的内码到 ISO(从 GBK 转换到ISO8859-1).
	 *
	 * @param input
	 *            输入的字符串
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String toISO(String input) {
		return changeEncoding(input, "GBK", "ISO8859-1");
	}

	/**
	 * 转换字符串的内码.
	 *
	 * @param input
	 *            输入的字符串
	 * @param sourceEncoding
	 *            源字符集名称
	 * @param targetEncoding
	 *            目标字符集名称
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String changeEncoding(String input, String sourceEncoding,
										String targetEncoding) {
		if (input == null || input.equals("")) {
			return input;
		}

		try {
			byte[] bytes = input.getBytes(sourceEncoding);
			return new String(bytes, targetEncoding);
		} catch (Exception ex) {
		}
		return input;
	}

	/**
	 * 将单个的 ' 换成 ''; SQL 规则:如果单引号中的字符串包含一个嵌入的引号,可以使用两个单引号表示嵌入的单引号.
	 */

	public static String replaceSql(String input) {
		return replace(input, "'", "''");
	}

	/**
	 * 对给定字符进行 URL 编码
	 */
	public static String encode(String value) {
		if (isEmpty(value)) {
			return "";
		}

		try {
			value = java.net.URLEncoder.encode(value, "GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	/**
	 * 对给定字符进行 URL 解码
	 *
	 * @param value
	 *            解码前的字符串
	 * @return 解码后的字符串
	 */
	public static String decode(String value) {
		if (isEmpty(value)) {
			return "";
		}

		try {
			return java.net.URLDecoder.decode(value, "GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	/**
	 * 判断字符串是否未空, 如果为 null 或者长度为0, 均返回 true.
	 */
	public static boolean isEmpty(String input) {
		return (input == null || input.length() == 0);
	}

	/**
	 * 获得输入字符串的字节长度(即二进制字节数), 用于发送短信时判断是否超出长度.
	 *
	 * @param input
	 *            输入字符串
	 * @return 字符串的字节长度(不是 Unicode 长度)
	 */
	public static int getBytesLength(String input) {
		if (input == null) {
			return 0;
		}

		int bytesLength = input.getBytes().length;

		//System.out.println("bytes length is:" + bytesLength);

		return bytesLength;
	}

	/**
	 * 检验字符串是否未空, 如果是, 则返回给定的出错信息.
	 *
	 * @param input
	 *            输入的字符串
	 * @param errorMsg
	 *            出错信息
	 * @return 空串返回出错信息
	 */
	public static String isEmpty(String input, String errorMsg) {
		if (isEmpty(input)) {
			return errorMsg;
		} else {
			return "";
		}
	}

	/**
	 * 得到文件的扩展名.
	 *
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the extension portion of the file's name.
	 */
	public static String getExtension(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(i + 1).toLowerCase();
			}
		}
		return "";
	}

	/**
	 * 得到文件的前缀名.
	 * @date 2005-10-18
	 *
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the prefix portion of the file's name.
	 */
	public static String getPrefix(String fileName) {
		if (fileName != null) {
			fileName = fileName.replace('\\', '/');

			if(fileName.lastIndexOf("/") > 0) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}

			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(0, i);
			}
		}
		return "";
	}

	/**
	 * 得到文件的短路径, 不保护目录.
	 * @date 2005-10-18
	 *
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the short version of the file's name.
	 */
	public static String getShortFileName(String fileName) {
		if (fileName != null) {
			fileName = fileName.replace('\\', '/');

			if(fileName.lastIndexOf("/") > 0) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}

			return fileName;
		}
		return "";
	}

	/**
	 * Gets the absolute pathname of the class or resource file containing the
	 * specified class or resource name, as prescribed by the current classpath.
	 *
	 * @param resourceName
	 *            Name of the class or resource name.
	 * @return the absolute pathname of the given resource
	 */
	public static String getPath(String resourceName) {

		if (!resourceName.startsWith("/")) {
			resourceName = "/" + resourceName;
		}

		//resourceName = resourceName.replace('.', '/');

		java.net.URL classUrl = new StringUtilExpress().getClass().getResource(
				resourceName);

		if (classUrl != null) {
			//System.out.println("\nClass '" + className +
			//"' found in \n'" + classUrl.getFile() + "'");
			//System.out.println("\n资源 '" + resourceName +
			//"' 在文件 \n'" + classUrl.getFile() + "' 中找到.");

			return classUrl.getFile();
		} else {
			//System.out.println("\nClass '" + className +
			//"' not found in \n'" +
			//System.getProperty("java.class.path") + "'");
			//System.out.println("\n资源 '" + resourceName +
			//"' 没有在类路径 \n'" +
			//System.getProperty("java.class.path") + "' 中找到");
			return null;
		}
	}

	/**
	 * 将日期转换为中文表示方式的字符串(格式为 yyyy年MM月dd日 HH:mm:ss).
	 */
	public static String dateToChineseString(Date date) {
		if (date == null) {
			return null;
		}

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy年MM月dd日 HH:mm:ss");

		return dateFormat.format(date);
	}

	/**
	 * 将日期转换为 14 位的字符串(格式为yyyyMMddHHmmss).
	 */
	public static String dateTo14String(Date date) {
		if (date == null) {
			return null;
		}

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss");

		return dateFormat.format(date);
	}

	/**
	 * 将 14 位的字符串(格式为yyyyMMddHHmmss)转换为日期.
	 */
	public static Date string14ToDate(String input) {
		if (isEmpty(input)) {
			return null;
		}

		if (input.length() != 14) {
			return null;
		}

		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss");

		try {
			return dateFormat.parse(input);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * 将 TEXT 文本转换为 HTML 代码, 已便于网页正确的显示出来.
	 *
	 * @param input
	 *            输入的文本字符串
	 * @return 转换后的 HTML 代码
	 */
	public static String textToHtml(String input) {
		if (isEmpty(input)) {
			return "";
		}

		input = replace(input, "<", "&#60;");
		input = replace(input, ">", "&#62;");

		input = replace(input, "\n", "<br>\n");
		input = replace(input, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		input = replace(input, "  ", "&nbsp;&nbsp;");

		return input;
	}



	// Test only.
	public static void main(String[] args) throws Exception {
		//System.out.println(textToHtml("1<2\r\n<b>Bold</b>"));
		//System.out.println(scriptAlert("oh!"));
		//System.out.println(scriptRedirect("http://localhost/"));
		//    System.out.println(StringUtil.getPath("/databaseconfig.properties"));
		//		java.io.File file = new java.io.File("e:\\Moblog\\abcd\\");
		//
		//		file.mkdir();
		Date time = (parseHMSStringToDate("12:23:00"));
		System.out.println(time.toLocaleString());
		Date nowTime = parseHMSStringToDate(formatDateToHMSString(new Date()));
		System.out.println(nowTime.toLocaleString());

		//		GregorianCalendar cal = new GregorianCalendar();
		//		cal.setTime(new Date());
		//		cal.add(cal.YEAR, -cal.get(cal.YEAR) + 1970);
		//		cal.add(cal.MONTH, -cal.get(cal.MONTH));
		//		cal.add(cal.DATE, -cal.get(cal.DATE) + 1);
		//
		//		System.out.println(cal.getTime().toLocaleString());

		System.out.println("name=" + getShortFileName("d:\\test\\刘海伦西瓜.jpg"));
	}
}