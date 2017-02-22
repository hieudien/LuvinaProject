/**
 * Copyright(C) 2017  Luvina Software Company
 * MstJapanLoginImpl.java, 13-02-2017 Doan Trong Hieu
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.entities.MstJapan;
import manageuser.logics.MstJapanLogic;

/**
 * Class thực thi Interface {@link MstJapanLogic}
 * 
 * @author LA-AM
 *
 */
public class MstJapanLoginImpl implements MstJapanLogic {
	private MstJapanDaoImpl mstJapanDaoImpl = new MstJapanDaoImpl();

	/**
	 * ghi đè phương thức lấy danh sách trình độ tiếng Nhật của Interface
	 * {@link MstJapanLogic}
	 * 
	 */
	@Override
	public List<MstJapan> getAllJapan() {
		List<MstJapan> allJapan = new ArrayList<>();
		try {
			allJapan = mstJapanDaoImpl.getAllMstJapan();
		} catch (SQLException e) {
			return null;
		}
		return allJapan;
	}

	/**
	 * phương thức kiểm tra xem level có tồn tại không
	 * 
	 * @param codeLevel
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistJapanLevel(String codeLevel) throws SQLException {
		return mstJapanDaoImpl.isExistJapanLevel(codeLevel) > 0;
	}

}
