/**
 * Copyright(C) 2017  Luvina Software Company
 * TblUserDao.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;

/**
 * Interface chứa các phương thức thao tác với bảng tbl_user
 * @author Doan Trong Hieu
 *
 */
public interface TblUserDao {
	/**
	 * Phương thức thức lấy ra một bản ghi trong bảng tbl_user
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TblUser getUserByAccount(String userName, String password) throws SQLException, ClassNotFoundException;

	/**
	 * Phương thức tính tổng số bản ghi thỏa mãn điều kiện tìm kiếm.
	 * 
	 * @param groupId
	 *            Điều kiện tìm kiếm theo group.
	 * @param fullName
	 *            Điều kiện tìm kiếm theo tên.
	 * @return Tổng số bản ghi thỏa mãn cả hai điều kiện tìm kiếm.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int getTotalUser(int groupId, String fullName) throws SQLException, ClassNotFoundException;

	/**
	 * Phương thức lấy ra các bản ghi là các thông tin của một User.
	 * 
	 * @param offset
	 *            Số thứ tự bản ghi cần lấy.
	 * @param limit
	 *            Số lượng bản ghi cần lấy.
	 * @param groupId
	 *            Điều kiện tìm kiếm theo groupId
	 * @param fullName
	 *            Điều kiện tìm kiếm theo tên.
	 * @param sortType
	 *            Tiêu chí ưu tiên sắp xếp.
	 * @param sortByFullName
	 *            Sắp xếp tăng hay giảm theo tên.
	 * @param sortByCodeLevel
	 *            Sắp xếp tăng hay giảm theo CodeLevel.
	 * @param sortByEndDate
	 *            Sắp xếp tăng hay giảm theo ngày hết hạn.
	 * @return Một List các {@link UserInfor} thỏa mãn các điều kiện tìm kiếm và
	 *         đã được sắp xếp.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws SQLException, ClassNotFoundException;
}
