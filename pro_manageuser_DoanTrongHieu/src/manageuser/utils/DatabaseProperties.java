/**
 * Copyright(C) 2017  Luvina Software Company
 * DatabaseProperties.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Class chứa các phương thức đọc thông tin DB từ file Database.properties
 * 
 * @author Doan Trong Hieu
 *
 */
public class DatabaseProperties {
	/**
	 * Phương thức đọc thông tin DB theo key
	 * @param key là thông tin cần đọc
	 * @return giá trị của key
	 */
	public static String getDatabaseProperties(String key){
		String result = "";
		Properties properties = new Properties();
		InputStream inputStream = DatabaseProperties.class.getResourceAsStream(("/database.properties"));
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
