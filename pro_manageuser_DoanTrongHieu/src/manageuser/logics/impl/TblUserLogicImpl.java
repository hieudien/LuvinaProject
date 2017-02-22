/**
 * Copyright(C) 2017  Luvina Software Company
 * TblUserLogicImpl.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.util.buf.UEncoder;

import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.entities.UserInforAdd;
import manageuser.logics.TblUserLogic;

/**
 * Class thực thi Interface {@link TblUserLogic}
 * 
 * @author Doan Trong Hieu
 *
 */
public class TblUserLogicImpl implements TblUserLogic {
	private TblUserDaoImpl tblUserDao = new TblUserDaoImpl();

	/**
	 * ghi đè phương thức kiểm tra xem có người dùng nào khớp với tên đăng nhập
	 * và password không của {@link TblUserLogic}
	 * 
	 */
	@Override
	public boolean isUser(String loginName, String password) throws SQLException {
		if (tblUserDao.getUserByAccount(loginName, password) != null) {
			return true;
		}
		return false;
	}

	/**
	 * ghi đè phương thứclấy số lượng user theo nhóm và tên của
	 * {@link TblUserLogic}
	 * 
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) {
		if (groupId < 0 || groupId > 4) {
			groupId = 0;
		}
		if (fullName == null) {
			fullName = "";
		}
		try {
			return tblUserDao.getTotalUser(groupId, fullName);
		} catch (ClassNotFoundException | SQLException e) {
			return 0;
		}
	}

	/**
	 * ghi đè phương thức lấy các bản ghi thông tin của các user trong Database
	 * của {@link TblUserLogic}
	 * 
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) {
		if (fullName == null) {
			fullName = "";
		}
		List<UserInfor> listUser = null;
		try {
			listUser = tblUserDao.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName,
					sortByCodeLevel, sortByEndDate);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return listUser;
	}

	/**
	 * phương thức kiểm tra xem user có tồn tại không
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistUser(String fieldName, String value) throws SQLException {
		return tblUserDao.isExistUser(fieldName, value) > 0;
	}

	/**
	 * phương thức tạo một bản ghi user info mới
	 * 
	 * @param userInforAdd
	 *            thông tin user
	 * @return nếu thêm mới thành cồn trả về true, ngược lại
	 * @throws SQLException
	 */
	public boolean createUserInfo(UserInforAdd userInforAdd) throws SQLException {
		return tblUserDao.insertUserInfo(userInforAdd);
	}

	/**
	 * @param loginName
	 * @return
	 * @throws SQLException 
	 */
	public String getUserRole(String loginName) throws SQLException {
		return tblUserDao.getRole(loginName);
	}

}
