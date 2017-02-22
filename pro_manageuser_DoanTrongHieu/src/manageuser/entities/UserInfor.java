/**
 * Copyright(C) 2017  Luvina Software Company
 * UserInfoADM002.java, 22/01/2017 Doan Trong Hieu
 */
package manageuser.entities;

/**
 * Class biểu diễn thông tin User
 * 
 * @author Doan Trong Hieu
 *
 */
public class UserInfor {
	private int id;
	private String fullName;
	private String birthday;
	private String group;
	private String email;
	private String tel;
	private String nameLevel;
	private String endDate;
	private String total;

	/**
	 * 
	 */
	public UserInfor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param fullName
	 * @param birthday
	 * @param group
	 * @param email
	 * @param tel
	 * @param nameLevel
	 * @param endDate
	 * @param total
	 */
	public UserInfor(int id, String fullName, String birthday, String group, String email, String tel, String nameLevel,
			String endDate, String total) {
		this.id = id;
		this.fullName = fullName;
		this.birthday = birthday;
		this.group = group;
		this.email = email;
		this.tel = tel;
		this.nameLevel = nameLevel;
		this.endDate = endDate;
		this.total = total;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
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
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}

	/**
	 * @param nameLevel
	 *            the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

}
