/**
 * Copyright(C) 2017  Luvina Software Company
 * MstGroupDao.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstJapan;

/**
 * Interface chứa các phương thức thao tác với bảng mst_japan
 * 
 * @author Doan Trong Hieu
 *
 */
public interface MstJapanDao {
	/**
	 * Phương thức lấy danh sách trình độ tiếng Nhật trong DB
	 * 
	 * @return Một danh sách trình độ tiếng Nhật
	 * @throws SQLException
	 *             Ngoại lệ có thể xảy ra trong quá trình lấy dữ liệu từ
	 *             Database
	 */
	public List<MstJapan> getAllMstJapan() throws SQLException;
}
