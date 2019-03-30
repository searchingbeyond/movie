package cn.ganlixin.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * 发送响应的工具类
 * @author ganlixin
 *
 */
public class ResponseUtils {

	/**
	 * 将要返回的数据转换为json格式再返回给客户端
	 * @param resp	HttpServletResponse对象
	 * @param data	要返回给客户端的响应数据
	 */
	public static void responseJSON(HttpServletResponse resp, Object data) {
		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
			String json = JSON.toJSONString(data);
			writer.print(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
}
