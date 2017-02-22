/**
 * Copyright(C) 2017  Luvina Software Company
 * MstGroupDao.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroup;

/**
 * Interface chứa các phương thức thao tác với bảng mst_group
 * 
 * @author Doan Trong Hieu
 *
 */
public interface MstGroupDao {
	/**
	 * Phương thức lấy tất cả cả các group trong DB đồng thời thêm một group mặc
	 * định.
	 * 
	 * @return Một List các Mstgroup
	 * @throws SQLException
	 *             Ngoại lệ có thể xảy ra trong quá trình lấy dữ liệu từ
	 *             Database
	 */
	public List<MstGroup> getAllMstGroup() throws SQLException;
}
