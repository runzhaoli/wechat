package kklazy.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import kklazy.persistence.utils.DateUtils;
import kklazy.persistence.utils.FileUtils;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.support.systemconfig.utils.SystemConfigUtils;
import kklazy.utils.support.WechatX509TrustManager;
import net.sf.json.JSONObject;

/**
 * 微信公众平台通用接口工具
 * 
 * @author kk
 */
public class WechatHttpUtils {

	public final static String GET = "GET";
	public final static String POST = "POST";

	/**
	 * 根据指定链接(URL)以及请求方式(METHOD)通过HTTP的方式请求服务器并获取返回数据
	 * 
	 * @param url
	 * @param method
	 * @return
	 */
	public static JSONObject get(String url, String method) {
		JSONObject jsonObject = null;
		String result = null;
		HttpsURLConnection connection = null;
		try {
			connection = (HttpsURLConnection) (new URL(url)).openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setUseCaches(false);
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while (StringUtils.isNotBlank(line = reader.readLine())) {
				result = line;
			}
			reader.close();
			jsonObject = JSONObject.fromObject(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**
			 * 释放资源
			 */
			if (connection != null) {
				connection.disconnect();
			}
		}
		return jsonObject;
	}

	/**
	 * 根据指定链接(URL)以及访问方式(method)和需要发送的数据(json)通过HTTPS的方式请求服务器发送数据并获取返回数据
	 * 
	 * @param url
	 * @param method
	 * @param json
	 * @return
	 */
	public static JSONObject https(String url, String method, String json) {
		JSONObject retval = null;
		StringBuffer buffer = new StringBuffer();
		HttpsURLConnection connection = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new WechatX509TrustManager() };
			SSLContext context = SSLContext.getInstance("SSL", "SunJSSE");
			context.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory factory = context.getSocketFactory();

			connection = (HttpsURLConnection) new URL(url).openConnection();
			connection.setSSLSocketFactory(factory);

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			// 设置请求方式（GET/POST）
			connection.setRequestMethod(method);

			if (WechatHttpUtils.GET.equalsIgnoreCase(method)) {
				connection.connect();
			}

			// 当有数据需要提交时
			if (StringUtils.isNotBlank(json)) {
				OutputStream outputStream = connection.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(StringUtils.trim(json).getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String line = null;
			while (StringUtils.isNotBlank(line = bufferedReader.readLine())) {
				buffer.append(line);
			}

			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			retval = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**
			 * 释放资源
			 */
			connection.disconnect();
		}
		return retval;
	}

	/**
	 * 下载远程文件
	 * 
	 * @param url 远程URL
	 * @param method 访问方式(method)
	 * @return 返回图片本地访问路径
	 */
	public static String download(String url, String method) {
		HttpsURLConnection connection = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new WechatX509TrustManager() };
			SSLContext context = SSLContext.getInstance("SSL", "SunJSSE");
			context.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory factory = context.getSocketFactory();

			connection = (HttpsURLConnection) new URL(url).openConnection();
			connection.setSSLSocketFactory(factory);
			connection.setConnectTimeout(5 * 1000);		// 设置超时间为5秒

			// 防止屏蔽程序抓取而返回403错误
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			// 设置请求方式（GET/POST）
			connection.setRequestMethod(method);

			if (WechatHttpUtils.GET.equalsIgnoreCase(method)) {
				connection.connect();
			}

			BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
			String base = System.getProperty("catalina.base") + "/webapps/";
			String folder = "/attached/" + DateUtils.format("yyyyMMdd") + "/";
			String disposition = connection.getHeaderField("content-disposition");
			disposition = disposition.replaceAll("\"", "");
			String suffix = FileUtils.suffix(disposition);
			String filename = System.currentTimeMillis() + "." + suffix;
			File dir = new File(base + folder);
			if (!dir.exists()) dir.mkdirs();
			FileOutputStream outputStream = new FileOutputStream(base + folder + filename);
			outputStream.write(readInputStream(inputStream));
			String contextpath = SystemConfigUtils.getSystemConfig("system.context");
			outputStream.close();
			inputStream.close();
			return contextpath + folder + filename;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**
			 * 释放资源
			 */
			connection.disconnect();
		}
		return null;
	}

	/**
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	public static void main(String[] args) {
		String token = "q_s3ujUcj_EdMIC1OG1BtqMAUxERmllkInQcaJMF6owBtQjSPd-upRA1rWvfDYTm";
		String id = "1ZoLWsEomlgQZAHqOUeQeL-P4Se1WOQzp44ldBKHnbs7si01taK9LIL9fEgS70a4pgLVyGjcRg4GSpG-F1b-YoA";
		String url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + id;
		System.out.println(download(url, GET));
	}
}
