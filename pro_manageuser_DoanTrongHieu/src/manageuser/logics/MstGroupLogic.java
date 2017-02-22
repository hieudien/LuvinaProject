/**
 * Copyright(C) 2017  Luvina Software Company
 * MstGroupLogic.java, 22/01/2017 Doan Trong Hieu
 */
package manageuser.logics;

import java.util.List;

import manageuser.entities.MstGroup;

/**
 * Interface chứa các phương thức xử lý logic với bảng mst_group
 * @author Doan Trong Hieu
 *
 */
public interface MstGroupLogic {
	/**
	 * Phương thức lấy tất cả các group trong Database
	 * 
	 * @return Một List các MstGroup.
	 */
	public List<MstGroup> getAllGroup();
}
