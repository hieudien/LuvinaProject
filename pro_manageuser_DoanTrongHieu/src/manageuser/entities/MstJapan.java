/**
 * Copyright(C) 2017  Luvina Software Company
 * MstJapan.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.entities;

/**
 * Class mô phỏng một bản ghi trong bảng mst_japan
 * @author Doan Trong Hieu
 *
 */
public class MstJapan {
    private String codeLevel;
    private String nameLevel;

    
    
    /**
	 * @param codeLevel
	 * @param nameLevel
	 */
	public MstJapan(String codeLevel, String nameLevel) {
		this.codeLevel = codeLevel;
		this.nameLevel = nameLevel;
	}

	/**
     * @return the codeLevel
     */
    public String getCodeLevel() {
	return codeLevel;
    }

    /**
     * @param codeLevel
     *            the codeLevel to set
     */
    public void setCodeLevel(String codeLevel) {
	this.codeLevel = codeLevel;
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

}
