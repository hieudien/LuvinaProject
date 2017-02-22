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

import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroup;

/**
 * Lớp thực thi Interface {@link MstGroupDao}
 * 
 * @author Doan Trong Hieu
 *
 */
public class MstGroupDaoImpl implements MstGroupDao {
	private BaseDaoImpl baseDao = new BaseDaoImpl();

	/**
	 * ghi đè phương thức lấy tất cả các nhóm của {@link MstGroupDao}
	 */
	@Override
	public List<MstGroup> getAllMstGroup() throws SQLException {
		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}
		String sqlGetAllGroup = "SELECT * FROM mst_group";
		List<MstGroup> allMstGroup = new ArrayList<>();
		PreparedStatement getAllGroup = connection.prepareStatement(sqlGetAllGroup);
		ResultSet allGroup = getAllGroup.executeQuery();

		while (allGroup.next()) {
			MstGroup mstGroup = new MstGroup(allGroup.getInt(1), allGroup.getString(2));
			allMstGroup.add(mstGroup);
		}
		allGroup.close();
		baseDao.closeConnect(connection);
		return allMstGroup;
	}

	/**
	 * phương thức kiểm tra xem group có tồn tại không
	 * @param groupId
	 * @return
	 * @throws SQLException
	 */
	public int isExistGroup(String groupId) throws SQLException {

		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}

		String sqlQuery = "SELECT group_id FROM mst_group WHERE group_id = ?";
		PreparedStatement getUser = connection.prepareStatement(sqlQuery);
		getUser.setString(1, groupId);
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
