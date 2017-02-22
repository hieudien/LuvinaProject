/**
 * Copyright(C) 2017  Luvina Software Company
 * MstJapanLogic.java, 13-02-2017 Doan Trong Hieu
 */
package manageuser.logics;

import java.util.List;

import manageuser.entities.MstJapan;

/**
 * Interface chứa các phương thức xử lý logic với bảng mst_japan
 * @author LA-AM
 *
 */
public interface MstJapanLogic {
	/**
	 * Phương thức lấy tất danh sách trình độ tiếng Nhật trong Database 
	 * 
	 * @return Một danh sách trình độ tiếng Nhậ
	 */
	public List<MstJapan> getAllJapan();
}
