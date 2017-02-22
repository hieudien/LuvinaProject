/**
 * Copyright(C) 2017  Luvina Software Company
 * MstGroupLogicImpl.java, 22/01/2017 Doan Trong Hieu
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroup;
import manageuser.logics.MstGroupLogic;

/**
 * Class thực thi Interface {@link MstGroupLogic}
 * 
 * @author Doan Trong Hieu
 *
 */
public class MstGroupLogicImpl implements MstGroupLogic {
	private MstGroupDaoImpl groupDaoImpl = new MstGroupDaoImpl();

	/**
	 * ghi đè phương thức lấy tất cả các nhóm của {@link MstGroupLogic}
	 */
	@Override
	public List<MstGroup> getAllGroup() {
		List<MstGroup> allGroup = new ArrayList<>();
		try {
			allGroup = groupDaoImpl.getAllMstGroup();
		} catch (SQLException e) {
			return null;
		}
		return allGroup;
	}

	/**
	 * phương thức kiểm tra xem group có tồn tại không
	 * @param groupId
	 * @return
	 * @throws SQLException 
	 */
	public boolean isExistGroup(String groupId) throws SQLException {

		return groupDaoImpl.isExistGroup(groupId) > 0;
	}

}
