package  util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ��ȡϵͳʱ��
 * 
 * @version 1.0 2007-11-30
 * @author puzg
 */
public class DateUtil {
	/* ��־���� */
	// private static Logger logger = Logger.getLogger(SystemUtil.class);
	/* ��ȡ��� */
	public static final int YEAR = 1;
	/* ��ȡ���� */
	public static final int YEARMONTH = 2;
	/* ��ȡ������ */
	public static final int YEARMONTHDAY = 3;
	/* ��ȡ�����գ�Сʱ */
	public static final int YMD_HOUR = 4;
	/* ��ȡ�����գ�Сʱ������ */
	public static final int YMD_HOURMINUTE = 5;
	/* ��ȡ�����գ�ʱ���� */
	public static final int FULL = 6;
	/* ��ȡ������ʱ���� ��ʽ��yyyyMMddHHmmss */
	public static final int UTILTIME = 7;

	/**
	 * ����ָ��ʱ���ʽ���͵õ���ǰʱ��
	 * 
	 * @param type
	 *            ʱ������
	 * @return String �ַ���ʱ��
	 */
	public static synchronized String getCurrentTime(int type) {
		String format = getFormat(type);
		SimpleDateFormat timeformat = new SimpleDateFormat(format);
		Date date = new Date();
		return timeformat.format(date);
	}

	/**
	 * ���ص�ǰϵͳʱ���������
	 * 
	 * @return
	 */
	public static synchronized String getCurrentTime() {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return timeformat.format(date);
	}
	/**
	 * ���ݲ�����ʽ����ʽ����ǰ����
	 * @param format
	 * @return
	 */
	public static synchronized String getDateString(String format) {
		SimpleDateFormat timeformat = new SimpleDateFormat(format);
		Date date = new Date();
		return timeformat.format(date);
	}

	/**
	 * ����ָ��ʱ���ʽ���ͣ���ʽ��ʱ���ʽ
	 * 
	 * @param type
	 *            ʱ���ʽ����
	 * @return
	 */
	private static String getFormat(int type) {
		String format = "";
		if (type == 1) {
			format = "yyyy";
		} else if (type == 2) {
			format = "yyyy-MM";
		} else if (type == 3) {
			format = "yyyy-MM-dd";
		} else if (type == 4) {
			format = "yyyy-MM-dd HH";
		} else if (type == 5) {
			format = "yyyy-MM-dd HH:mm";
		} else if (type == 6) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 7) {
			format = "yyyyMMddHHmmss";
		} else {
			throw new RuntimeException("���ڸ�ʽ��������");
		}
		return format;
	}

	public static int getYear(String dateString) {
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dd.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.YEAR);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static int getMonth(String dateString) {
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dd.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.MONTH)+1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int getDay(String dateString) {
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dd.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Date StringToDate(String dateStr, String formatStr) {
		SimpleDateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * ��ǰ���ںͲ������ھ����Сʱ�� ���ڸ�ʽ��yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static double getHours(String date) {
		SimpleDateFormat timeformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			Date d = new Date();
			Date d1 = timeformat.parse(date);

			long temp = d.getTime() - d1.getTime();
			double f = temp / 3600000d;
			BigDecimal b = new BigDecimal(f);
			double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void main(String a[]) {
		try {
			int aa = getYear("2012-01-08");
			System.out.println(aa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
