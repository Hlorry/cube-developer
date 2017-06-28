package cn.getcube.develop.commons;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class EmailHelper {

	private final static EmailHelper instance = new EmailHelper();

	private final static SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy年MM月dd日 HH时");

	private ConcurrentHashMap<String, String> contentMap;
	private ConcurrentHashMap<String, String> subjectMap;

	private EmailHelper() {
		this.contentMap = new ConcurrentHashMap<>();
		this.subjectMap = new ConcurrentHashMap<>();
	}

	public static String formatNow() {
		return sFormatter.format(new Date());
	}

	public static String formatContent(String content, String... args) {
		String ret = null;
		int argsIndex = 0;
		try {
			byte[] bytes = content.getBytes("UTF-8");
			byte[] buf = new byte[bytes.length + 10240];
			int bufLength = 0;

			for (int i = 0, size = bytes.length; i < size; ++i) {
				int next = i + 1;
				if (next < size && bytes[i] == '%' && bytes[i + 1] == 's') {
					String arg = args[argsIndex++];
					if (null == arg)
						arg = "";
					byte[] argbuf = arg.getBytes("UTF-8");
					System.arraycopy(argbuf, 0, buf, bufLength, argbuf.length);
					bufLength += argbuf.length;
					i += 1;
					continue;
				}

				buf[bufLength++] = bytes[i];
			}

			ret = new String(buf, Charset.forName("UTF-8"));
			buf = null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String readContent(String filename) {
		String content = EmailHelper.instance.contentMap.get(filename);
		if (null != content) {
			return content;
		}

		StringBuilder data = new StringBuilder();
		//String file= EmailHelper.class.getResource("/"+filename).getFile();
		InputStream inputStream = EmailHelper.class.getResourceAsStream("/"+filename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				data.append(inputLine);
				data.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		content = data.toString();
		EmailHelper.instance.contentMap.put(filename, content);

		return content;
	}

	public static String readSubject(String filename) {
		String subject = EmailHelper.instance.subjectMap.get(filename);
		if (null != subject) {
			return subject;
		}

		String content = EmailHelper.instance.contentMap.get(filename);
		if (null == content) {
			return null;
		}

		int start = content.indexOf("<title>") + 7;
		int end = content.indexOf("</title>");
		subject = content.substring(start, end);
		EmailHelper.instance.subjectMap.put(filename, subject);
		return subject;
	}

}
