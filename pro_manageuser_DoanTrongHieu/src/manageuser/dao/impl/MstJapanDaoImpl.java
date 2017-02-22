/**
 * Copyright(C) 2017  Luvina Software Company
 * MstGroupImpl.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.entities.MstJapan;

/**
 * Lớp thực thi Interface {@link MstJapanDao}
 * 
 * @author Doan Trong Hieu
 *
 */
public class MstJapanDaoImpl implements MstJapanDao {
	private BaseDaoImpl baseDao = new BaseDaoImpl();

	/**
	 * ghi đè phương thức lấy trình độ tiếng Nhật của {@link MstJapanDao}
	 */
	@Override
	public List<MstJapan> getAllMstJapan() throws SQLException {
		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}
		String sqlGetAllJapan = "SELECT * FROM mst_japan";
		List<MstJapan> allMstJapan = new ArrayList<>();
		PreparedStatement getAllJapan = connection.prepareStatement(sqlGetAllJapan);
		ResultSet allJapan = getAllJapan.executeQuery();

		while (allJapan.next()) {
			MstJapan mstJapan = new MstJapan(allJapan.getString(1), allJapan.getString(2));
			allMstJapan.add(mstJapan);
		}
		allJapan.close();
		baseDao.closeConnect(connection);
		return allMstJapan;
	}

	/**
	 * phương thức kiểm tra xem level có tồn tại không
	 * @param codeLevel
	 * @return
	 * @throws SQLException
	 */
	public int isExistJapanLevel(String codeLevel) throws SQLException {
		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}

		String sqlQuery = "SELECT code_level FROM mst_japan WHERE code_level = ?";
		PreparedStatement getUser = connection.prepareStatement(sqlQuery);
		getUser.setString(1, codeLevel);
		ResultSet result = getUser.executeQuery();
		int count = 0;
		if (result.next()) {
			count++;
		}
		result.close();
		baseDao.closeConnect(connection);
		return count;
	}

}
