/**
 * Copyright(C) 2017  Luvina Software Company
 * TblUserLogic.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.UserInfor;

/**
 * Interface chứa các phương thức xử lý logic cho bảng tbl_user
 * 
 * @author Doan Trong Hieu
 *
 */
public interface TblUserLogic {
	/**
	 * phương thức kiểm tra xem có người dùng nào khớp với tên đăng nhập và password không
	 * @param loginName tên đăng nhập
	 * @param password mật khẩu
	 * @return nếu có trả về true, ngược lại trả về false
	 * @throws SQLException
	 */
	public boolean isUser(String loginName, String password) throws SQLException;
/**
 * phương thức lấy số lượng user theo nhóm và tên
 * @param groupId mã nhóm
 * @param fullName tên đầy đủ
 * @return số lượng user
 */
	public int getTotalUsers(int groupId, String fullName);

	/**
	 * Phương thức lấy các bản ghi thông tin của các user trong Database
	 * 
	 * @param offset
	 *            Vị trí đầu tiên cần lấy.
	 * @param limit
	 *            Số bản ghi cần lấy.
	 * @param groupId
	 *            Điều kiện tìm kiếm groupId.
	 * @param fullName
	 *            Điều kiện tìm kiếm fullName.
	 * @param sortType
	 *            Điều kiện sắp xếp ưu tiên.
	 * @param sortByFullName
	 *            Kiểu sắp xếp tăng hay giảm theo tên
	 * @param sortByCodeLevel
	 *            Kiểu sắp xếp tăng hay giảm theo CodeLevel
	 * @param sortByEndDate
	 *            Kiểu sắp xếp tăng hay giảm theo EndDate
	 * @return
	 */
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate);
}
