/**
 * Copyright(C) 2017  Luvina Software Company
 * ConfigProperties.java, 07-02-2017 Doan Trong Hieu
 */
package manageuser.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author LA-AM
 *
 */
public class ConfigProperties {
	private int limit;
	private int numPage;

	/**
	 * Phương thức đọc thông tin config
	 */
	public void getConfigProperties() {
		Properties properties = new Properties();
		InputStream inputStream = DatabaseProperties.class.getResourceAsStream(("/config.properties"));
		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(inputStream, "utf8");
			properties.load(inputStreamReader);
		} catch (IOException e) {
			// Xu ly loi
		}
		limit = Integer.parseInt(properties.getProperty("limit"));
		numPage = Integer.parseInt(properties.getProperty("numPage"));
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @return the numPage
	 */
	public int getNumPage() {
		return numPage;
	}

}
