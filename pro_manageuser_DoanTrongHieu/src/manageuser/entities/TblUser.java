/**
 * Copyright(C) 2017  Luvina Software Company
 * TblUser.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.entities;

/**
 * Class mô phỏng một bản ghi trong bảng tbl_user
 * 
 * @author Doan Trong Hieu
 *
 */
public class TblUser {
	private String userId;
	private String groupId;
	private String loginName;
	private String password;
	private String fullName;
	private String fullNameKana;
	private String email;
	private String tel;
	private String birthday;

	/**
	 * @param userId
	 * @param groupId
	 * @param loginName
	 * @param password
	 * @param fullName
	 * @param fullNameKana
	 * @param email
	 * @param tel
	 * @param birthday
	 */
	
//	public TblUser(int groupId, String loginName, String password, String fullName, String fullNameKana, String email,
//			String tel, String birthday) {
//		this.groupId = groupId;
//		this.loginName = loginName;
//		this.password = password;
//		this.fullName = fullName;
//		this.fullNameKana = fullNameKana;
//		this.email = email;
//		this.tel = tel;
//		this.birthday = birthday;
//	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the fullNameKana
	 */
	public String getFullNameKana() {
		return fullNameKana;
	}

	/**
	 * @param fullNameKana
	 *            the fullNameKana to set
	 */
	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
