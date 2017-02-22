/**
 * Copyright(C) 2017  Luvina Software Company
 * MstGroup.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.entities;

/**
 * Class mô phỏng một bản ghi trong bảng mst_group
 * 
 * @author Doan Trong Hieu
 *
 */
public class MstGroup {
	private int groupId;
	private String groupName;

	/**
	 * @param groupId
	 * @param groupName
	 */
	public MstGroup(int groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
