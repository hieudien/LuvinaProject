/**
 * Copyright(C) 2017  Luvina Software Company
 * TblUserDaoImpl.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.entities.UserInforAdd;
import manageuser.utils.Constant;

/**
 * Lớp thực thi Interface {@link TblUserDao}
 * 
 * @author Doan Trong Hieu
 *
 */
public class TblUserDaoImpl implements TblUserDao {
	private BaseDaoImpl baseDao = new BaseDaoImpl();

	/**
	 * ghi đè phương thức lấy user theo tài khoản của {@link TblUserDao}
	 */
	@Override
	public TblUser getUserByAccount(String loginName, String password) throws SQLException {

		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}

		String sqlQuery = "SELECT * FROM tbl_user u WHERE u.login_name = ? AND u.password = ? AND u.role = "
				+ Constant.ADMIN_ROLE;
		int param = 1;

		PreparedStatement findUser = connection.prepareStatement(sqlQuery);

		findUser.setString(param++, loginName);
		findUser.setString(param++, password);

		ResultSet result = findUser.executeQuery();

		if (result.next()) {
			int columnIndex = 1;
			TblUser tblUser = new TblUser();
			tblUser.setUserId(result.getString(columnIndex++));
			tblUser.setGroupId(result.getString(columnIndex++));
			tblUser.setLoginName(result.getString(columnIndex++));
			tblUser.setFullName(result.getString(columnIndex++));
			tblUser.setFullNameKana(result.getString(columnIndex++));
			tblUser.setEmail(result.getString(columnIndex++));
			tblUser.setTel(result.getString(columnIndex++));
			tblUser.setBirthday(result.getString(columnIndex++));

			baseDao.closeConnect(connection);
			return tblUser;

		}
		result.close();
		baseDao.closeConnect(connection);
		return null;
	}

	/**
	 * phương thức kiểm tra xem user có tồn tại không
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public int isExistUser(String fieldName, String value) throws SQLException {
		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}

		String sqlQuery = "SELECT user_id FROM tbl_user WHERE " + fieldName + " = ?";
		PreparedStatement getUser = connection.prepareStatement(sqlQuery);
		getUser.setString(1, value);
		ResultSet result = getUser.executeQuery();
		int count = 0;
		if (result.next()) {
			count++;
		}
		result.close();
		baseDao.closeConnect(connection);
		return count;
	}

	/**
	 * ghi đè phương thức tính tổng số bản ghi thỏa mãn điều kiện tìm kiếm của
	 * {@link TblUserDao}
	 */
	@Override
	public int getTotalUser(int groupId, String fullName) throws SQLException, ClassNotFoundException {
		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("SELECT COUNT(group_id) FROM tbl_user");
		sqlQuery.append(" WHERE");
		sqlQuery.append(" role = " + Constant.USER_ROLE + " AND ");
		if (groupId != 0) {
			sqlQuery.append(" group_id IN (?) AND");
		}
		sqlQuery.append(" full_name LIKE ?");

		int param = 1;
		PreparedStatement statement = connection.prepareStatement(sqlQuery.toString());
		if (groupId != 0) {
			statement.setInt(param++, groupId);
		}
		statement.setString(param++, "%" + fullName.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_") + "%");
		ResultSet resultSet = statement.executeQuery();
		int numberOfUser = 0;
		if (resultSet.next()) {
			numberOfUser = resultSet.getInt(1);
		}
		resultSet.close();
		baseDao.closeConnect(connection);
		return numberOfUser;
	}

	/**
	 * ghi đè phương thức các bản ghi là các thông tin User của
	 * {@link TblUserDao}
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate)
			throws SQLException, ClassNotFoundException {
		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(
				"SELECT tu.user_id, tu.full_name, DATE_FORMAT(tu.birthday,'%Y/%m/%d'), mg.group_name, tu.email, tu.tel, mj.name_level, DATE_FORMAT(du.end_date,'%Y/%m/%d'), du.total");
		sqlQuery.append(" FROM tbl_user tu");
		// # Start fix bug ID 23,25,41,42 – HieuDT 2017/02/10

		// sqlQuery.append(" INNER JOIN mst_group mg");
		// sqlQuery.append(" ON(tu.group_id = mg.group_id)");
		// sqlQuery.append(" INNER JOIN tbl_detail_user_japan du");
		// sqlQuery.append(" ON(du.user_id = tu.user_id)");
		// sqlQuery.append(" INNER JOIN mst_japan mj");
		sqlQuery.append(" LEFT JOIN mst_group mg");
		sqlQuery.append(" ON(tu.group_id = mg.group_id)");
		sqlQuery.append(" LEFT JOIN tbl_detail_user_japan du");
		sqlQuery.append(" ON(du.user_id = tu.user_id)");
		sqlQuery.append(" LEFT JOIN mst_japan mj");
		// # End fix bug ID 23,25,41,42– HieuDT 2017/02/10
		sqlQuery.append(" ON(du.code_level = mj.code_level)");
		sqlQuery.append(" WHERE");
		sqlQuery.append(" role = " + Constant.USER_ROLE + " AND ");
		if (groupId != 0) {
			sqlQuery.append(" tu.group_id IN (?) AND");
		}
		sqlQuery.append(" tu.full_name LIKE ?");
		sqlQuery.append(" ORDER BY");
		sqlQuery.append(" tu.full_name " + sortByFullName);
		sqlQuery.append(", mj.name_level " + sortByCodeLevel);
		sqlQuery.append(", du.end_date " + sortByEndDate);
		sqlQuery.append(" LIMIT ?");
		sqlQuery.append(" OFFSET ?");
		List<UserInfor> listUserInfor = new ArrayList<>();
		int param = 1;
		PreparedStatement statement = connection.prepareStatement(sqlQuery.toString());
		if (groupId != 0) {
			statement.setInt(param++, groupId);
		}
		// # Start fix bug ID 27 – HieuDT 2017/02/10
		if (fullName.contains("%")) {
			fullName = fullName.replaceAll("%", "\\\\%");
		}
		// # End fix bug ID 27 – HieuDT 2017/02/10
		statement.setNString(param++, "%" + fullName + "%");
		statement.setInt(param++, limit);
		statement.setInt(param++, offset);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			param = 1;
			UserInfor userInfor = new UserInfor(resultSet.getInt(param++), resultSet.getString(param++),
					resultSet.getString(param++), resultSet.getString(param++), resultSet.getString(param++),
					resultSet.getString(param++), resultSet.getString(param++), resultSet.getString(param++),
					resultSet.getString(param++));
			listUserInfor.add(userInfor);
		}
		resultSet.close();
		baseDao.closeConnect(connection);
		return listUserInfor;
	}
	//
	// /**
	// * @param tblUser
	// * @return
	// * @throws SQLException
	// */
	// public boolean insertUser(TblUser tblUser) throws SQLException {
	// Connection connection = baseDao.getConnection();
	// if (connection == null) {
	// throw new SQLException();
	// }
	// StringBuilder sqlQuery = new StringBuilder();
	// sqlQuery.append(
	// "INSERT INTO tbl_user (group_id, login_name, password, full_name,
	// full_name_kana, email, tel, birthday)");
	// sqlQuery.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	// PreparedStatement statement =
	// connection.prepareStatement(sqlQuery.toString());
	// int param = 1;
	// statement.setString(param++, tblUser.getGroupId());
	// statement.setString(param++, tblUser.getLoginName());
	// statement.setString(param++, tblUser.getPassword());
	// statement.setString(param++, tblUser.getFullName());
	// statement.setString(param++, tblUser.getFullNameKana());
	// statement.setString(param++, tblUser.getEmail());
	// statement.setString(param++, tblUser.getTel());
	// statement.setString(param++, tblUser.getBirthday());
	// int count = statement.executeUpdate();
	// return count > 0;
	// }

	/**
	 * Phương thức insert thông tin user vào bảng tbl user và mst japan
	 * 
	 * @param userInforAdd
	 *            thông tin user
	 * @return nếu insert thành công trả về true, ngược lại
	 * @throws SQLException
	 */
	public boolean insertUserInfo(UserInforAdd userInforAdd) throws SQLException {
		Connection connection = null;
		int count = 0;
		try {
			connection = baseDao.getConnection();
			if (connection == null) {
				throw new SQLException();
			}
			connection.setAutoCommit(false);
			// tạo câu truy vấn insert user
			StringBuilder sqlInsertUser = new StringBuilder();
			sqlInsertUser.append(
					"INSERT INTO tbl_user (group_id, login_name, password, full_name, full_name_kana, email, tel, birthday,role)");
			sqlInsertUser.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);");
			// tạo preparestm
			PreparedStatement stmInsertUser = connection.prepareStatement(sqlInsertUser.toString());
			// biến chạy cho tham số prestm
			int param = 1;
			// sét tham số cho preparestm insert user
			stmInsertUser.setString(param++, userInforAdd.getGroupId());
			stmInsertUser.setString(param++, userInforAdd.getUserName());
			// Start fix bug ID 64 – HieuDT 2017/02/22
			stmInsertUser.setString(param++, userInforAdd.getPassword());
			// End fix bug ID 64 – HieuDT 2017/02/22
			stmInsertUser.setString(param++, userInforAdd.getFullName());
			stmInsertUser.setString(param++, userInforAdd.getKatakanaName());
			stmInsertUser.setString(param++, userInforAdd.getEmail());
			stmInsertUser.setString(param++, userInforAdd.getTel());
			stmInsertUser.setString(param++, userInforAdd.getBirthday().replaceAll("/", "-"));
			stmInsertUser.setString(param, Constant.USER_ROLE);
			count = stmInsertUser.executeUpdate();
			if (!Constant.EMPTY_STRING.equals(userInforAdd.getCodeLevel())) {
				// tạo câu truy vấn insert japan
				StringBuilder sqlInsertJapan = new StringBuilder();
				sqlInsertJapan
						.append("INSERT INTO tbl_detail_user_japan (user_id, code_level,start_date, end_date, total)");
				sqlInsertJapan.append(" VALUES (LAST_INSERT_ID(), ?, ?, ?, ?);");
				// tạo preparestm
				PreparedStatement stmInsertJapan = connection.prepareStatement(sqlInsertJapan.toString());
				// tạo biến chạy cho tham số stm
				param = 1;
				// sét tham số cho preparestm insert japan
				stmInsertJapan.setString(param++, userInforAdd.getCodeLevel());
				stmInsertJapan.setString(param++, userInforAdd.getStartDate().replaceAll("/", "-"));
				stmInsertJapan.setString(param++, userInforAdd.getEndDate().replaceAll("/", "-"));
				stmInsertJapan.setString(param++, userInforAdd.getTotal());
				stmInsertJapan.executeUpdate();
			}

			connection.commit();
			// hiển thị dữ liệu test
			// System.out.println(userInforAdd.getGroupId());
			// System.out.println(userInforAdd.getUserName());
			// System.out.println(userInforAdd.getPassword());
			// System.out.println(userInforAdd.getFullName());
			// System.out.println(userInforAdd.getKatakanaName());
			// System.out.println(userInforAdd.getEmail());
			// System.out.println(userInforAdd.getTel());
			// System.out.println(userInforAdd.getBirthday().replaceAll("/",
			// "-"));
			// System.out.println(userInforAdd.getCodeLevel());
			// System.out.println(userInforAdd.getStartDate().replaceAll("/",
			// "-"));
			// System.out.println(userInforAdd.getEndDate().replaceAll("/",
			// "-"));
			// System.out.println(userInforAdd.getTotal());
			// thực hiện insert
			// count = stmInsertJapan.executeUpdate();
			// commit transaction
		} catch (SQLException e) {
			// nếu có lỗi thì rollback
			count = 0;
			connection.rollback();
			e.printStackTrace();
		} finally {
			// đóng kết nối db
			baseDao.closeConnect(connection);
		}
		return count > 0;
	}

	/**
	 * @param loginName
	 * @return
	 * @throws SQLException
	 */
	public String getRole(String loginName) throws SQLException {
		Connection connection = baseDao.getConnection();
		if (connection == null) {
			throw new SQLException();
		}
		String sql = "SELECT role FROM tbl_user WHERE login_name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, loginName);
		ResultSet rs = statement.executeQuery();
		rs.next();
		return rs.getString(1);
	}

}
