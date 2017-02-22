/**
 * Copyright(C) 2017  Luvina Software Company
 * BaseDao.java, 19/01/2017 Doan Trong Hieu
 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface chứa các phương thức cơ bản để thao tác với Database như connect và
 * đóng connect
 * 
 * @author Doan Trong Hieu
 *
 */
public interface BaseDao {
	/**
	 * Phương thức tạo một Connection tới Database
	 * 
	 * @return Một Connection tới Database
	 * @throws SQLException
	 *             Ngoại lệ có thể xảy ra khi việc tạo Connection tới Database
	 *             không thành công.
	 */
	public Connection getConnection() throws SQLException;

	/**
	 * Phương thức đóng một Connection đã tồn tại tới Database.
	 * 
	 * @param connection
	 *            Connection cần đóng.
	 * @throws SQLException
	 *             Ngoại lệ có thể xảy ra khi việc đóng Connection tới Database
	 *             không thành công.
	 */
	public void closeConnect(Connection connection) throws SQLException;

}
