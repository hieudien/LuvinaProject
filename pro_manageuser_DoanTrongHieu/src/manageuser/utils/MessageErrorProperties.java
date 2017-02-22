/**
 * Copyright(C) 2017  Luvina Software Company
 * MessageErrorProperties.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Class chứa các phương thức thao tác với file message_error.properties
 * 
 * @author Doan Trong Hieu
 *
 */
public class MessageErrorProperties {
	/**
	 * Phương thức đọc câu thông báo lỗi theo key
	 * @param key là mã lỗi cần đọc
	 * @return giá trị lỗi
	 */
	public static String getErrorMessage(String key) {
		String result = "";
		Properties properties = new Properties();
		InputStream inputStream = DatabaseProperties.class.getResourceAsStream(("/message_error.properties"));
		InputStreamReader inputStreamReader;
		try {
		    inputStreamReader = new InputStreamReader(inputStream, "utf8");
		    properties.load(inputStreamReader);
		} catch (IOException e) {
			//Xu ly loi
		}
		
		result = properties.getProperty(key);

		return result;
	}
}
