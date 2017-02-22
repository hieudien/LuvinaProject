/**
 * Copyright(C) 2017  Luvina Software Company
 * BaseDaoImpl.java, 19/01/2017 Doan Trong Hieu
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manageuser.dao.BaseDao;
import manageuser.utils.DatabaseProperties;

/**
 * Lớp thực thi Interface {@link BaseDao}
 * 
 * @author Doan Trong Hieu
 *
 */
public class BaseDaoImpl implements BaseDao {
	/**
	 * ghi đè phương thức mở kết nối của {@link BaseDao}
	 */
	@Override
	public Connection getConnection() throws SQLException {

		Connection connection = null;

		String url = DatabaseProperties.getDatabaseProperties("url");
		String userName = DatabaseProperties.getDatabaseProperties("userName");
		String password = DatabaseProperties.getDatabaseProperties("password");
		try {
			Class.forName(DatabaseProperties.getDatabaseProperties("classSqlName"));
		} catch (ClassNotFoundException e) {
			return null;
		}
		connection = DriverManager.getConnection(url, userName, password);

		return connection;
	}

	/**
	 * ghi đè phương thức đóng kết nối của {@link BaseDao}
	 */
	@Override
	public void closeConnect(Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}

	}

}
