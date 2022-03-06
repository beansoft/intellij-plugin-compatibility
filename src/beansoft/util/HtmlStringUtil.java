/*
 * @(#)StringUtil.java	1.0 2003.11.2
 *
 * Copyright 2003 - 2006 BeanSoft Studio. All rights reserved.
 */

package beansoft.util;

import org.cef.OS;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * StringUtil, 字符串工具类, 一些方便的字符串工具方法.
 *
 * Dependencies: Servlet/JSP API.
 *
 * @author beansoft
 * @version 1.2 2006-07-31
 */
public class HtmlStringUtil {

	/**
	 * 当前时间戳.
	 * @return
	 */
	public static String nowTimestamp() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now(ZoneId.systemDefault()));
	}
	/**
	 * Same function as javascript's escape().
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
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
	 * Same function as javascript's unsecape().
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
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
	 * 获取类路径中的资源文件的物理文件路径.
	 * NOTE: 仅在 Win32 平台下测试通过开发.
	 * @date 2005.10.16
	 * @param resourcePath 资源路径
	 * @return 配置文件路径
	 */
	public static String getRealFilePath(String resourcePath) {
		java.net.URL inputURL = HtmlStringUtil.class
							.getResource(resourcePath);

		String filePath = inputURL.getFile();
		
		// 2007-02-08  Fixed by K.D. to solve the space char problem, also with some
		// other special chars in path problem
		try{
			filePath = java.net.URLDecoder.decode(filePath, "utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// For windows platform, the filePath will like this:
		// /E:/Push/web/WEB-INF/classes/studio/beansoft/smtp/MailSender.ini
		// So must remove the first /

		if(OS.isWindows() && filePath.startsWith("/")) {
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
	public static String formatDateToDHMSString(Date date) {
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
	public static String formatDateToHMSString(Date date) {
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
	public static String formatDateToMysqlString(Date date) {
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
	public static String formatDateToMMddHHmm(Date date) {
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
	public static String formatDateToyyMMddHHmm(Date date) {
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



	// ------------------------------------ 字符串处理方法
	// ----------------------------------------------

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
		// FIXME 加这个过滤换行到 BR 的功能会把原始 HTML 代码搞乱 2006-07-30
//		content = replace(content, "\r\n", "<BR>");
		// 滤除脚本事件代码
		for (int i = 0; i < eventKeywords.length; i++) {
			content = replace(content, eventKeywords[i],
					"_" + eventKeywords[i], false); // 添加一个"_", 使事件代码无效
		}
		return content;
	}

	/**
	 * 滤除 HTML 代码 为文本代码, 将 < 转为 &lt;.
	 */
	public static String replaceHtmlToText(String input) {
		if (isEmpty(input)) {
			return "";
		}
//		return setBr(setTag(input));
		return setTag(input);
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
				stringbuffer.append("&lt;");
//				stringbuffer.append("〈");
			} else if (ch == '>') {
				stringbuffer.append("&gt;");
//				stringbuffer.append("〉");
			} else if (ch == '&') {
				stringbuffer.append("&amp;");
//				stringbuffer.append("〃");
			} else if (ch == '%') {
				stringbuffer.append("%%");
//				stringbuffer.append("※");
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
			}
			stringbuffer.append(s.charAt(i));
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
		}
		return "";
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
            String oldFileName = new String(fileName);

            fileName = fileName.replace('\\', '/');
            
            // Handle dir
            if(fileName.endsWith("/")) {
                int idx = fileName.indexOf('/');
                if(idx == -1 || idx == fileName.length() - 1) {
                    return oldFileName;
                } else {
                    return  oldFileName.substring(idx + 1, fileName.length() - 1);
                }

            }
            if(fileName.lastIndexOf("/") > 0) {
                fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
            }

            return fileName;
        }
        return "";
    }



	/**
	 * 输出分页显示的结果.
	 *
	 * @param page
	 *            当前页面
	 * @param recordCount
	 *            所有结果
	 * @param pageSize
	 *            一页显示的多少
	 * @param pageCountSize
	 *            前后跳页的多少
	 * @param linkpageurl
	 *            连接页面的 URL 字符串
	 * @return 分页结果的字符串.
	 */
	public static String paging(int page, int recordCount, int pageSize,
			int pageCountSize, String linkpageurl) {
		int PageCount = -1; //页面总数
		String LinkPageName = linkpageurl;
		String LinkText = "";
		int StartPage;
		int TempPage;
		int TempPageCount;
		TempPage = (page - 1) % pageCountSize; //临时页数
		StartPage = page - TempPage; //起始页数
		TempPageCount = recordCount % pageSize;
		if (TempPageCount == 0) {
			PageCount = recordCount / pageSize;
		} else {
			PageCount = (recordCount / pageSize) + 1; //总页数加1
		}
		String txtPrev = " [前" + pageCountSize + "页] ";
		String txtNext = " [后" + pageCountSize + "页] ";
		String txtStart = " [首页] ";
		String txtEnd = " [末页] ";
		//贸澜栏肺
		if (StartPage - 1 > 0) {
			LinkText += "<a href='" + LinkPageName + "&page=1' title='到此页'>"
					+ txtStart + "</a>";
		} else {
			LinkText += txtStart;
		}
		//捞傈 10俺..
		if (StartPage - 1 > 0) {
			LinkText += "<a href='" + LinkPageName + "&page=" + (StartPage - 1)
					+ "' title='到第" + pageCountSize + "页'>" + txtPrev + "</a>";
		} else {
			LinkText += txtPrev;
		}
		for (int i = StartPage; i < StartPage + pageCountSize; i++) {
			if (i < PageCount + 1) {
				LinkText += "<a href='" + LinkPageName + "&page=";
				LinkText += i + "' title='" + i + "页'>";
				if (i == page) {
					LinkText += "<b>[" + i + "]</b>";
				} else {
					LinkText += "[" + i + "]";
				}
				LinkText += "</a>";
			}
		}
		//中间页面
		if (StartPage + pageCountSize - PageCount - 1 < 0) {
			LinkText += "<a href='" + LinkPageName + "&page="
					+ (StartPage + pageCountSize) + "' title='到第"
					+ pageCountSize + "页'>" + txtNext + "</a>";
		} else {
			LinkText += txtNext;
		}
		//最后一页
		if (StartPage + pageCountSize <= PageCount) {
			LinkText += "<a href='" + LinkPageName + "&page=" + PageCount
					+ "' title='最后一页'>" + txtEnd + "</a>";
		} else {
			LinkText += txtEnd;
		}
		return LinkText;
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

		java.net.URL classUrl = new HtmlStringUtil().getClass().getResource(
				resourceName);

		if (classUrl != null) {
			//System.out.println("\nClass '" + className +
			//"' found in \n'" + classUrl.getFile() + "'");
			//System.out.println("\n资源 '" + resourceName +
			//"' 在文件 \n'" + classUrl.getFile() + "' 中找到.");

			return classUrl.getFile();
		}
		//System.out.println("\nClass '" + className +
		//"' not found in \n'" +
		//System.getProperty("java.class.path") + "'");
		//System.out.println("\n资源 '" + resourceName +
		//"' 没有在类路径 \n'" +
		//System.getProperty("java.class.path") + "' 中找到");
		return null;
	}

	/**
	 * 将日期转换为中文表示方式的字符串(格式为 yyyy年MM月dd日 HH:mm:ss).
	 */
	public static String dateToChineseString(Date date) {
		if (date == null) {
			return "";
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

	public static String toQuoteMark(String s) {
		s = replaceString(s, "'", "&#39;");
		s = replaceString(s, "\"", "&#34;");
		s = replaceString(s, "\r\n", "\n");
		return s;
	}

	public static String replaceChar(String s, char c, char c1) {
		if (s == null) {
			return "";
		}
		return s.replace(c, c1);
	}

	public static String replaceString(String s, String s1, String s2) {
		if (s == null || s1 == null || s2 == null) {
			return "";
		}
		return s.replaceAll(s1, s2);
	}

	public static String toHtml(String s) {
		s = replaceString(s, "<", "&#60;");
		s = replaceString(s, ">", "&#62;");
		return s;
	}

	public static String toBR(String s) {
		s = replaceString(s, "\n", "<br>\n");
		s = replaceString(s, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		s = replaceString(s, "  ", "&nbsp;&nbsp;");
		return s;
	}

	public static String toSQL(String s) {
		s = replaceString(s, "\r\n", "\n");
		return s;
	}

	public static String replaceEnter(String s) throws NullPointerException {
		return s.replaceAll("\n", "<br>");
	}

	public static String replacebr(String s) throws NullPointerException {
		return s.replaceAll("<br>", "\n");
	}

	public static String replaceQuote(String s) throws NullPointerException {
		return s.replaceAll("'", "''");
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
		String html = "\tpublic static String replacebr(String s) throws NullPointerException {\n" +
				"\t\treturn s.replaceAll(\"<br>\", \"\\n\");\n" +
				"\t}\n" +
				"\n" +
				"\tpublic static String replaceQuote(String s) throws NullPointerException {\n" +
				"\t\treturn s.replaceAll(\"'\", \"''\");\n" +
				"\t}";
		System.out.println(replaceHtmlToText(html));

	}
}