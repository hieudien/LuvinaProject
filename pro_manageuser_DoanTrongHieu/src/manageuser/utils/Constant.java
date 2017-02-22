/**
 * Copyright(C) 2017  Luvina Software Company
 * Constant.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.utils;

/**
 * Class chứa các constant của dự án.
 * 
 * @author Doan Trong Hieu
 *
 */
public class Constant {
	public static final String INDEX = "/ManageUser/";
	public static final String ADM001 = "jsp/ADM001.jsp";
	public static final String ADM002 = "jsp/ADM002.jsp";
	public static final String ADM003 = "/jsp/ADM003.jsp";
	public static final String ADM004 = "/jsp/ADM004.jsp";
	// Start fix bug ID 54 – HieuDT 2017/02/22
	public static final String ADM006 = "/ADM006.login";
	public static final String SYSTEM_ERROR = "/jsp/System_Error.jsp";
	public static final String LIST_USER_ANOTATION = "listUser.login";
	public static final String LOGIN_ANOTATION = "/login";
	public static final String LOGOUT_ANOTATION = "/logout";
	public static final String USER_DETAIL = "/detailUser.login";
	public static final String ADD_USER = "/addUserInput.login";
	public static final String ADD_USER_VALIDATE = "/addUserValidate.login";
	public static final String ADD_USER_CONFIRM = "addUserConfirm.login";
	public static final String ADD_USER_OK = "/addUserOk.login";
	// End fix bug ID 54 – HieuDT 2017/02/22

	public static final String ASC = "ASC";
	public static final String DESC = "DESC";
	public static final String BACK = "back";
	public static final String FIRST = "first";
	public static final String SEARCH = "search";
	public static final String SORT = "sort";
	public static final String PAGE = "page";
	public static final String FULL_NAME = "full_name";
	public static final String CODE_LEVEL = "code_level";
	public static final String END_DATE = "end_date";
	public static final int MONTH_START = 1;
	public static final int MONTH_END = 12;
	public static final int DAY_START = 1;
	public static final int DAY_END = 31;
	public static final int YEAR_START = 1900;
	public static final String CHOOSE_ONE = "選択してください";
	public static final String ALL = "全て";
	public static final String EMPTY_STRING = "";
	public static final String SLASH_CHARACTER = "/";
	public static final int MIN_USERNAME_LENGHT = 4;
	public static final int MIN_PASSWORD_LENGHT = 6;
	// Start fix bug ID 58 – HieuDT 2017/02/22
	public static final int MAX_LENGHT = 15;
	// End fix bug ID 58 – HieuDT 2017/02/22
	public static final int MAX_LENGHT_NAME_EMAIL = 255;
	public static final int MAX_LENGHT_TEL = 14;
	public static final String LOGIN_NAME_FIELD = "login_name";
	public static final String EMAIL_FIELD = "email";
	public static final String EMAIL_FORMAT = "^[a-zA-Z0-9]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	public static final String TEL_FORMAT = "[0-9]{1,4}-[0-9]{1,4}-[0-9]{1,4}";
	public static final String USER_NAME_FORMAT = "[a-zA-Z_]+[a-zA-Z0-9_]*";
	public static final String ER001 = "ER001";
	public static final String ER002 = "ER002";
	public static final String ER003 = "ER003";
	public static final String ER004 = "ER004";
	public static final String ER005 = "ER005";
	public static final String ER006 = "ER006";
	public static final String ER005_TEL = "ER005tel";
	public static final String ER006_TEL = "ER006tel";
	public static final String ER007 = "ER007";
	public static final String ER007_PASS = "ER007pass";
	public static final String ER008 = "ER008";
	public static final String ER009 = "ER009";
	public static final String ER010 = "ER010";
	public static final String ER011 = "ER011";
	public static final String ER012 = "ER012";
	public static final String ER013 = "ER013";
	public static final String ER014 = "ER014";
	public static final String ER015 = "ER015";
	public static final String ER016 = "ER016";
	public static final String ER017 = "ER017";
	public static final String ER018 = "ER018";
	public static final String ER019 = "ER019";
	public static final String MSG001 = "MSG001";
	public static final String ZERO_STRING = "0";
	public static final String MST_GROUP = "mst_group";
	public static final String GROUP_ID = "group_id";
	public static final String TBL_USER = "tbl_user";

	public static final String USER_NAME_JP = "[アカウント名]";
	public static final String GROUP_JP = "[グループ]";
	public static final String FULL_NAME_JP = "[氏名]";
	public static final String KATA_NAME_JP = "[カタカナ氏名]";
	public static final String BIRTHDAY_JP = "[生年月日]";
	public static final String EMAIL_JP = "[メールアドレス]";
	public static final String TEL_JP = "[電話番号]";
	public static final String PASSWORD_JP = "[パスワード]";
	public static final String LEVEL_JP = "[資格]";
	public static final String START_DATE_JP = "[生年月日]";
	public static final String END_DATE_JP = "[生年月日]";
	public static final String TOTAL_JP = "[点数]";
	public static final String USER_ROLE = "1";
	public static final String ADMIN_ROLE = "0";

	// public static final String USER_NAME_JP = "[アカウント名]USER_NAME_JP";
	// public static final String GROUP_JP = "[グループ]GROUP_JP";
	// public static final String FULL_NAME_JP = "[氏名]FULL_NAME_JP";
	// public static final String KATA_NAME_JP = "[カタカナ氏名]KATA_NAME_JP";
	// public static final String BIRTHDAY_JP = "[生年月日]BIRTHDAY_JP";
	// public static final String EMAIL_JP = "[メールアドレス]EMAIL_JP";
	// public static final String TEL_JP = "[電話番号]TEL_JP";
	// public static final String PASSWORD_JP = "[パスワード]PASSWORD_JP";
	// public static final String LEVEL_JP = "[資格]LEVEL_JP";
	// public static final String START_DATE_JP = "[生年月日]START_DATE_JP";
	// public static final String END_DATE_JP = "[生年月日]END_DATE_JP";
	// public static final String TOTAL_JP = "[点数]TOTAL_JP";

}
