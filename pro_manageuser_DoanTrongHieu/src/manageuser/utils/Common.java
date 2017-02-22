/**
 * Copyright(C) 2017  Luvina Software Company
 * Common.java, 18/01/2017 Doan Trong Hieu
 */
package manageuser.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

/**
 * Class chứa các phương thức common
 * 
 * @author Doan Trong Hieu
 *
 */
public class Common {
	private static ConfigProperties configProperties;

	/**
	 * Phương thức kiểm tra một request tới đã login hay chưa.
	 * 
	 * @param session
	 *            {@link HttpSession} của request.
	 * @return
	 */
	public static boolean checkLogin(HttpSession session) {
		if (session == null || session.getAttribute("loginName") == null) {
			return false;
		}
		return true;
	}

	/**
	 * Phương thức tính toán các số trang cần hiển thị.
	 * 
	 * @param totalRecords
	 *            Tổng số bản ghi phù hợp với điều kiện tìm kiếm.
	 * @param limit
	 *            Số bản ghi hiển thị trên 1 page.
	 * @param currentPage
	 *            Page hiện tại.
	 * @return Một List chứa các số trang cần hiển thị.
	 */
	public static List<Integer> getListPaging(int totalRecords, int limit, int currentPage) {
		List<Integer> listPaging = new ArrayList<>();
		int totalPage = getTotalPage(totalRecords, limit);
		int numPage = getNumPage();
		if (totalPage == 0) {
			return listPaging;
		}
		// if (currentPage > totalPage || currentPage <= 0) {
		// currentPage = 1;
		// }
		int startPage = getStartPage(currentPage, numPage);
		int endPage = getEndPage(currentPage, numPage);
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			listPaging.add(i);
		}

		return listPaging;
	}

	/**
	 * phương thức lấy trang cuối
	 * 
	 * @param currentPage
	 * @param numPage
	 * @return số trang
	 */
	private static int getEndPage(int currentPage, int numPage) {
		return (int) (Math.ceil(currentPage / (float) numPage) * numPage);

	}

	/**
	 * phương thức lấy trang đầu
	 * 
	 * @param currentPage
	 * @param numPage
	 * @return số trang
	 */
	private static int getStartPage(int currentPage, int numPage) {
		return (int) ((Math.ceil(currentPage / (float) numPage) - 1) * numPage + 1);

	}

	/**
	 * Phương thức tính vị trí offset của bản ghi cần lấy trong DB.
	 * 
	 * @param currentPage
	 *            Trang hiện tại.
	 * @param limit
	 *            Số bản ghi hiển thị trên 1 page.
	 * @return Vị trí offset của bản ghi cần lấy trong DB
	 */
	public static int getOffset(int currentPage, int limit) {
		return (currentPage - 1) * limit;
	}

	/**
	 * Phương thức lấy tổng số bản ghi cần hiển thị trong file config.
	 * 
	 * @return Số bản ghi hiển thị trên 1 page.
	 */
	public static int getLimit() {
		configProperties = new ConfigProperties();
		configProperties.getConfigProperties();
		return configProperties.getLimit();
	}

	/**
	 * phương thức lấy số trang trong một đoạn
	 * 
	 * @return
	 */
	public static int getNumPage() {
		return configProperties.getNumPage();
	}

	/**
	 * Phương thức tính tổng số page.
	 * 
	 * @param totalUser
	 *            Tổng số bản ghi dữ liệu.
	 * @param limit
	 *            Số bản ghi hiển thị trên 1 page.
	 * @return Tổng số page.
	 */
	public static int getTotalPage(int totalUser, int limit) {
		int totalPages = (int) Math.ceil(totalUser * 1.0 / limit);
		return totalPages;
	}

	/**
	 * Phương thức trả về danh sách năm để hiển thị trên combobox
	 * 
	 * @param start
	 *            năm bắt đầu
	 * @param end
	 *            năm kết thúc
	 * @return danh sách năm
	 */
	public static ArrayList<Integer> getListYear(int start, int end) {
		return getListInt(start, end);
	}

	/**
	 * Phương thức trả về danh sách tháng
	 * 
	 * @return danh sách tháng
	 */
	public static ArrayList<Integer> getListMonth() {
		return getListInt(Constant.MONTH_START, Constant.MONTH_END);
	}

	/**
	 * Phương thức trả về danh sách ngày
	 * 
	 * @return danh sách ngày
	 */
	public static ArrayList<Integer> getListDay() {
		return getListInt(Constant.DAY_START, Constant.DAY_END);
	}

	/**
	 * Phương thức trả về danh sách số nguyên trong một khoảng giới hạn
	 * 
	 * @param start
	 *            điểm bắt đầu
	 * @param end
	 *            điểm kết thúc
	 * @return danh sách số nguyên
	 */
	public static ArrayList<Integer> getListInt(int start, int end) {
		ArrayList<Integer> listInt = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			listInt.add(i);
		}
		return listInt;
	}

	/**
	 * phương thức lấy năm hiện tại
	 * 
	 * @return
	 */
	public static int getYearNow() {
		Calendar now = Calendar.getInstance(); // Gets the current date and time
		int year = now.get(Calendar.YEAR);
		return year;
	}

	/**
	 * Phương thức lấy về ngày/ tháng/ năm hiện tại
	 * 
	 * @return mảng lưu ngày/ tháng/ năm hiện tại
	 */
	public static int[] getCurrentDate() {
		Calendar now = Calendar.getInstance();
		int year = getYearNow();
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		int[] currentDate = new int[3];
		currentDate[0] = year;
		currentDate[1] = month;
		currentDate[2] = day;
		return currentDate;
	}

	/**
	 * phương thức chuyển giá trị ngày tháng năm nhập vào thành chuỗi
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return chuỗi ngày
	 */
	public static String inputDateToString(String year, String month, String day) {
		return year + Constant.SLASH_CHARACTER + month + Constant.SLASH_CHARACTER + day;
	}

	/**
	 * chuyển ngày dạng chuỗi thành mảng
	 * 
	 * @param userBirthday
	 * @return mảng ngày
	 */
	public static String[] dateStringToArray(String userBirthday) {
		return userBirthday.split(Constant.SLASH_CHARACTER);
	}

	/**
	 * phương thức tạo sessionId
	 * 
	 * @return
	 */
	public static String generateSessionId() {
		return UUID.randomUUID().toString();
	}

}
