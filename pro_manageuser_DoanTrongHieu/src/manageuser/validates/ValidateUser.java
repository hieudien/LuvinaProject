/**
 * Copyright(C) 2017  Luvina Software Company
 * ValidateUser.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.validates;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import manageuser.entities.UserInforAdd;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLoginImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * Class chứa phương thức xử lý khi người dùng nhập liệu.
 * 
 * @author Doan Trong Hieu
 *
 */
public class ValidateUser {
	private TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();

	/**
	 * xác thực đăng nhập và trả về thông báo
	 * 
	 * @param loginName
	 *            tên đăng nhập
	 * @param password
	 *            mật khẩu
	 * @return thông báo kết quả đăng nhập
	 */
	public List<String> validateLogin(String loginName, String password) {
		List<String> listError = new ArrayList<>();
		if (Constant.EMPTY_STRING.equals(loginName)) {
			listError.add(Constant.USER_NAME_JP + MessageErrorProperties.getErrorMessage(Constant.ER001));
		}
		if (Constant.EMPTY_STRING.equals(password)) {
			listError.add(MessageErrorProperties.getErrorMessage(Constant.PASSWORD_JP + Constant.ER001));
		}
		if (listError.size() != 0) {
			return listError;
		}

		try {
			if (!tblUserLogicImpl.isUser(loginName, password)) {
				listError.add(Constant.USER_NAME_JP + MessageErrorProperties.getErrorMessage(Constant.ER004));
			}
		} catch (SQLException e) {
			listError.add(MessageErrorProperties.getErrorMessage(Constant.ER015));
		}

		return listError;
	}

	/**
	 * Phương thức validate user Info
	 * 
	 * @param userInfor
	 * @return danh sách thông báo
	 */
	public static ArrayList<String> validateUserInfo(UserInforAdd userInfor) {
		// tạo danh sách lưu các câu thông báo
		ArrayList<String> listError = new ArrayList<>();
		// xác thực tên đăng nhập
		String errorUserName = validateUserName(userInfor.getUserName());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorUserName)) {
			listError.add(Constant.USER_NAME_JP + errorUserName);
		}
		// xác thực group
		String errorGroup = validateGroup(userInfor.getGroupId());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorGroup)) {
			listError.add(Constant.GROUP_JP + errorGroup);
		}
		// xác thực full name
		String errorFullName = validateFullName(userInfor.getFullName());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorFullName)) {
			listError.add(Constant.FULL_NAME_JP + errorFullName);
		}
		// xác thực tên Kata
		String errorKatakanaName = validateKatakanaName(userInfor.getKatakanaName());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorKatakanaName)) {
			listError.add(Constant.KATA_NAME_JP + errorKatakanaName);
		}
		// xác thực tên ngày sinh
		String errorBirthday = validateBirthday(userInfor.getBirthday());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorBirthday)) {
			listError.add(Constant.BIRTHDAY_JP + errorBirthday);
		}
		// xác thực Email
		String errorEmail = validateEmail(userInfor.getEmail());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorEmail)) {
			listError.add(Constant.EMAIL_JP + errorEmail);
		}
		// xác thực số điện thoại
		String errorTel = validateTel(userInfor.getTel());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorTel)) {
			listError.add(Constant.TEL_JP + errorTel);
		}

		// xác thực password
		String errorPassword = validatePassword(userInfor.getPassword());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorPassword)) {
			listError.add(Constant.PASSWORD_JP + errorPassword);
		}
		// xác thực RePassword
		String errorRePassword = validateRePassword(userInfor.getPassword(), userInfor.getRePassword());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorRePassword)) {
			listError.add(errorRePassword);
		}
		// xác thực Japan level
		String errorJapanLevel = validateJapanLevel(userInfor.getCodeLevel());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorJapanLevel)) {
			listError.add(Constant.LEVEL_JP + errorJapanLevel);
		}

		// xác thực ngày cấp
		String errorStartDate = validateStartDate(userInfor.getCodeLevel(), userInfor.getStartDate());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorStartDate)) {
			listError.add(Constant.START_DATE_JP + errorStartDate);
		}
		// xác thực ngày hết hạn
		String errorEndDate = validateEndDate(userInfor.getCodeLevel(), userInfor.getStartDate(),
				// nếu có lỗi thì add vào danh sách lỗi
				userInfor.getEndDate());
		if (!Constant.EMPTY_STRING.equals(errorEndDate)) {
			listError.add(Constant.END_DATE_JP + errorEndDate);
		}
		// xác thực tổng điểm
		String errorTotal = validateTotal(userInfor.getCodeLevel(), userInfor.getTotal());
		// nếu có lỗi thì add vào danh sách lỗi
		if (!Constant.EMPTY_STRING.equals(errorTotal)) {
			listError.add(Constant.TOTAL_JP + errorTotal);
		}
		return listError;
	}

	/**
	 * phương thức validate ngày cấp chứng chỉ tiếng Nhật
	 * 
	 * @param startDay
	 *            ngày cấp
	 * @return thông báo
	 */
	private static String validateStartDate(String codelevel, String startDay) {
		if (!Constant.EMAIL_JP.equals(codelevel)) {
			if (!checkIsValidDate(startDay)) {
				return MessageErrorProperties.getErrorMessage(Constant.ER011);
			}
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức validate trình độ tiếng Nhật
	 * 
	 * @param codeLevel
	 *            mã level
	 * @return thông báo
	 */
	private static String validateJapanLevel(String codeLevel) {
		// Start fix bug ID 63 – HieuDT 2017/02/22
		if (!Constant.EMPTY_STRING.equals(codeLevel)) {
			if (!checkIsExistJapanLevel(codeLevel)) {
				return MessageErrorProperties.getErrorMessage(Constant.ER004);
			}
		}
		// End fix bug ID 63 – HieuDT 2017/02/22
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức kiểm tra có tồn tại level tiếng Nhật hay không
	 * 
	 * @param codeLevel
	 *            mã level
	 * @return nếu có tồn tại trả về true, không thì trả về false
	 */
	private static boolean checkIsExistJapanLevel(String codeLevel) {
		MstJapanLoginImpl mstJapanLoginImpl = new MstJapanLoginImpl();
		try {
			if (mstJapanLoginImpl.isExistJapanLevel(codeLevel)) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * phương thức validate ngày sinh
	 * 
	 * @param birthday
	 *            ngày sinh
	 * @return thông báo
	 */
	private static String validateBirthday(String birthday) {
		if (!checkIsValidDate(birthday)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER011);
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức validate nhóm
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return thông báo
	 */
	private static String validateGroup(String groupId) {
		if (Constant.ZERO_STRING.equals(groupId)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER002);
		}
		if (!checkIsExistGroup(groupId)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER004);
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức kiểm tra nhóm có tồn tại không
	 * 
	 * @param groupId
	 *            mã nhóm
	 * @return nếu có trả về true, không thì trả về false
	 */
	private static boolean checkIsExistGroup(String groupId) {
		MstGroupLogicImpl mstGroupLogicImpl = new MstGroupLogicImpl();
		try {
			if (mstGroupLogicImpl.isExistGroup(groupId)) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * phương thức validate full name
	 * 
	 * @param fullName
	 * @return thông báo
	 */
	private static String validateFullName(String fullName) {
		if (checkIsEmpty(fullName)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER001);
		}
		if (!checkMaxLenght(Constant.MAX_LENGHT_NAME_EMAIL, fullName)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER006);
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức validate katakana Name
	 * 
	 * @param katakanaName
	 * @return thông báo
	 */
	private static String validateKatakanaName(String katakanaName) {
		if (!Constant.EMPTY_STRING.equals(katakanaName)) {
			if (checkIsKatakana(katakanaName)) {
				return MessageErrorProperties.getErrorMessage(Constant.ER009);
			}
			if (!checkMaxLenght(Constant.MAX_LENGHT_NAME_EMAIL, katakanaName)) {
				return MessageErrorProperties.getErrorMessage(Constant.ER006);
			}
		}
		return Constant.EMPTY_STRING;
	}

	// /**
	// * phương thức validate ngày
	// *
	// * @param date
	// * @return thông báo
	// */
	// private static String validateDate(String date) {
	// if (checkIsValidDate(date)) {
	// return MessageErrorProperties.getErrorMessage(Constant.ER011);
	// }
	// return Constant.EMPTY_STRING;
	// }

	/**
	 * phương thức validate email
	 * 
	 * @param email
	 * @return thông báo
	 */
	private static String validateEmail(String email) {
		if (checkIsEmpty(email)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER001);
		}
		if (!checkIsMatchFormat(Constant.EMAIL_FORMAT, email)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER005);
		}
		if (!checkMaxLenght(Constant.MAX_LENGHT_NAME_EMAIL, email)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER006);
		}
		if (!checkIs1Byte(email)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER008);
		}
		if (checkIsExistUser(Constant.EMAIL_FIELD, email)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER003);
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức validate tel
	 * 
	 * @param tel
	 * @return thông báo
	 */
	private static String validateTel(String tel) {
		if (checkIsEmpty(tel)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER001);
		}
		if (!checkIsMatchFormat(Constant.TEL_FORMAT, tel)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER005_TEL);
		}
		if (!checkMaxLenght(Constant.MAX_LENGHT_TEL, tel)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER006_TEL);
		}
		if (!checkIsHalfSizeNum(tel)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER018);
		}

		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức validate password
	 * 
	 * @param password
	 * @return thông báo
	 */
	private static String validatePassword(String password) {
		if (checkIsEmpty(password)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER001);
		} else if (!checkIs1Byte(password)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER008);
		} else if (!checkLengtInRange(password, Constant.MIN_PASSWORD_LENGHT, Constant.MAX_LENGHT)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER007_PASS);
		}

		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức validate repassword
	 * 
	 * @param rePassword
	 * @return thông báo
	 */
	private static String validateRePassword(String password, String rePassword) {
		if (!checkIsMatchPassword(password, rePassword)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER017);
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức validate ngày hết hạn
	 * 
	 * @param date
	 * @return thông báo
	 */
	private static String validateEndDate(String codeLevel, String date, String endDate) {
		if (!Constant.EMPTY_STRING.equals(codeLevel)) {
			if (!checkIsValidDate(endDate)) {
				return MessageErrorProperties.getErrorMessage(Constant.ER011);
			}
			if (!checkIsBiggerThanDate(date, endDate)) {
				return MessageErrorProperties.getErrorMessage(Constant.ER012);
			}
		}

		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức validate total
	 * 
	 * @param total
	 * @return thông báo
	 */
	private static String validateTotal(String codeLevel, String total) {
		// nếu có chọn level thì mới check ER001 total
		if (!Constant.EMPTY_STRING.equals(codeLevel)) {
			if (checkIsEmpty(total)) {
				return MessageErrorProperties.getErrorMessage(Constant.ER001);
			}
			if (!checkIsHalfSizeNum(total)) {
				return MessageErrorProperties.getErrorMessage(Constant.ER018);
			}
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức kiểm tra ngày hết hạn có lớn hơn ngày cấp
	 * 
	 * @param date
	 * @param endDate
	 * @return nếu lớn hơn trả về true, ngược lại trả về false;
	 */
	private static boolean checkIsBiggerThanDate(String date, String endDate) {
		Date inDate = toDate(date);
		Date outDate = toDate(endDate);
		if (outDate.compareTo(inDate) > 0) {
			return true;
		}
		return false;
	}

	/**
	 * phương thức chuyển ngày kiểu string sang kiểu date
	 * 
	 * @param date
	 * @return
	 */
	private static Date toDate(String date) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date valueDate = null;
		try {
			valueDate = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valueDate;
	}

	/**
	 * phương thức kiểm tra xem rePass có khớp với pass không
	 * 
	 * @param password
	 * @param rePassword
	 * @return nếu khớp trả về true, không thì trả về false
	 */
	private static boolean checkIsMatchPassword(String password, String rePassword) {
		if (password.equals(rePassword)) {
			return true;
		}
		return false;
	}

	/**
	 * phương thức check xem có phải số halfsize không
	 * 
	 * @param tel
	 * @return nếu đúng trả về true, sai trả về false
	 */
	private static boolean checkIsHalfSizeNum(String tel) {
		return checkIs1Byte(tel);
	}

	/**
	 * phương thức check xem thông tin có đúng format không
	 * 
	 * @param format
	 *            định dạng mẫu
	 * @param value
	 *            thông tin cần check
	 * @return nếu đúng format trả về true, ngược lại;
	 */
	private static boolean checkIsMatchFormat(String format, String value) {
		if (value.matches(format)) {
			return true;
		}
		return false;
	}

	/**
	 * phương thức validate ngày có hợp lệ không
	 * 
	 * @param date
	 * @return nếu hợp lệ trả về true, không thì trả về false;
	 */
	private static boolean checkIsValidDate(String date) {
		String formatString = "yyyy/MM/dd";
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatString);
			format.setLenient(false);
			format.parse(date);
		} catch (ParseException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	/**
	 * phương thức check xem chuỗi có phải kí tự katakana khống
	 * 
	 * @param katakanaName
	 * @return nếu đúng trả về true, sai trả về false;
	 */
	private static boolean checkIsKatakana(String katakanaName) {
		int lenght = katakanaName.length();
		for (int i = 0; i < lenght; i++) {
			if (Character.UnicodeBlock.of(katakanaName.charAt(i)).equals(Character.UnicodeBlock.KATAKANA)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * phương thức check xem chuỗi có dài hơn max lenght ko
	 * 
	 * @param maxLenght
	 * @param str
	 *            chuỗi
	 * @return nếu (max lenght> chuỗi) trả bề true, ngược lại false;
	 */
	private static boolean checkMaxLenght(int maxLenght, String str) {
		if (maxLenght >= str.length()) {
			return true;
		}
		return false;
	}

	/**
	 * phương thức validate userName
	 * 
	 * @param userName
	 * @return thông báo
	 */
	private static String validateUserName(String userName) {
		if (checkIsEmpty(userName)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER001);
		}
		if (!checkIs1Byte(userName)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER008);
		}
		if (!checkIsMatchFormat(Constant.USER_NAME_FORMAT, userName)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER019);
		}
		if (!checkLengtInRange(userName, Constant.MIN_USERNAME_LENGHT, Constant.MAX_LENGHT)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER007);
		}
		if (checkIsExistUser(Constant.LOGIN_NAME_FIELD, userName)) {
			return MessageErrorProperties.getErrorMessage(Constant.ER003);
		}
		return Constant.EMPTY_STRING;
	}

	/**
	 * phương thức check xem trường đã tồn tại giá trị chưa
	 * 
	 * @param fieldName
	 *            tên trường
	 * @param value
	 *            giá trị
	 * @return nếu tồn tại trả vể true, không thì trả về false
	 */
	private static boolean checkIsExistUser(String fieldName, String value) {
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		try {
			if (tblUserLogicImpl.isExistUser(fieldName, value)) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return false;
	}

	/**
	 * phương thức check xem chuỗi có độ dài trong khoảng không
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return nếu độ dài trong khoảng thì trả về true, không thì trả về false;
	 */
	private static boolean checkLengtInRange(String value, int min, int max) {
		int lenght = value.length();
		if (min <= lenght && lenght <= max) {
			return true;
		}
		return false;
	}

	/**
	 * phương thức check xem chuỗi có phải toàn kí tự 1 byte không
	 * 
	 * @param value
	 * @return nếu đúng trả về true. ngược lại;
	 */
	private static boolean checkIs1Byte(String value) {
		try {
			if (value.getBytes("UTF8").length == value.length()) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * phương thức check xem có phải chuỗi rỗng không
	 * 
	 * @param str
	 * @return nếu rỗng thì trả về true, không thì trả về false;
	 */
	private static boolean checkIsEmpty(String str) {
		if (Constant.EMPTY_STRING.equals(str)) {
			return true;
		}
		return false;
	}

}
