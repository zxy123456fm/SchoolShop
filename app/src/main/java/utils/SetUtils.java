package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SetUtils {
	private static SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
	public static String formatTimeForFile(Date date) {
		return fileDateFormat.format(date);
	}
	/**
	 * 无图模式
	 * @param context
	 * @param checked
	 */
	public  static void saveNoImage(Context context, boolean checked) {
        SharedPreferences sp = context.getSharedPreferences("NoImage", Context.MODE_PRIVATE);
        sp.edit().putBoolean("NoImage", checked).commit();
	}
	public static boolean isNoImage(Context context) {
		SharedPreferences sp = context.getSharedPreferences("NoImage", Context.MODE_PRIVATE);
		boolean isNoImage = sp.getBoolean("NoImage", false);
		return isNoImage;
	}
	
	/**
	 * @param context
	 */
	public  static void saveUser(Context context, String JGId) {
		SharedPreferences sp = context.getSharedPreferences("JGId", Context.MODE_PRIVATE);
		sp.edit().putString("JGId", JGId).commit();
	}
	public static String GetUser(Context context) {
		SharedPreferences sp = context.getSharedPreferences("JGId", Context.MODE_PRIVATE);
		return sp.getString("JGId",null);
	}
	/**
	 * @param context
	 */
	public  static void saveNickName(Context context, String NickName) {
		SharedPreferences sp = context.getSharedPreferences("NickName", Context.MODE_PRIVATE);
		sp.edit().putString("NickName", NickName).commit();
	}
	public static String GetNickName(Context context) {
		SharedPreferences sp = context.getSharedPreferences("NickName", Context.MODE_PRIVATE);
		return sp.getString("NickName","");
	}
	/**
	 * @param context
	 */
	public  static void savePassword(Context context, String Password) {
		SharedPreferences sp = context.getSharedPreferences("Password", Context.MODE_PRIVATE);
		sp.edit().putString("Password", Password).commit();
	}
	public static String GetPassword(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Password", Context.MODE_PRIVATE);
		return sp.getString("Password",null);
	}
	public  static void saveId(Context context, String UserId) {
		SharedPreferences sp = context.getSharedPreferences("Id", Context.MODE_PRIVATE);
		sp.edit().putString("Id", UserId).commit();
	}
	public static String GetId(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Id", Context.MODE_PRIVATE);
		return sp.getString("Id",null);
	}
	public  static void savePhone1(Context context, String Phone1) {
		SharedPreferences sp = context.getSharedPreferences("Phone1", Context.MODE_PRIVATE);
		sp.edit().putString("Phone1", Phone1).commit();
	}
	public static String GetPhone1(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Phone1", Context.MODE_PRIVATE);
		return sp.getString("Phone1",null);
	}
	/**
	 * 地址
	 * @param context
	 */
	public  static void saveAdderss(Context context, String Adderss) {
		SharedPreferences sp = context.getSharedPreferences("Adderss", Context.MODE_PRIVATE);
		sp.edit().putString("Adderss", Adderss).commit();
	}
	public static String GetAdderss(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Adderss", Context.MODE_PRIVATE);
		return sp.getString("Adderss",null);
	}
	//phone
	public  static void savePhone(Context context, String Phone) {
		SharedPreferences sp = context.getSharedPreferences("Phone", Context.MODE_PRIVATE);
		sp.edit().putString("Phone", Phone).commit();
	}
	public static String GetPhone(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Phone", Context.MODE_PRIVATE);
		return sp.getString("Phone",null);
	}
	//Name
	public  static void saveName(Context context, String Name) {
		SharedPreferences sp = context.getSharedPreferences("Name", Context.MODE_PRIVATE);
		sp.edit().putString("Name", Name).commit();
	}
	public static String GetName(Context context) {
		SharedPreferences sp = context.getSharedPreferences("Name", Context.MODE_PRIVATE);
		return sp.getString("Name","");
	}
	//ImgHead
	public  static void saveImgHead(Context context, String ImgHead) {
		SharedPreferences sp = context.getSharedPreferences("ImgHead", Context.MODE_PRIVATE);
		sp.edit().putString("ImgHead", ImgHead).commit();
	}
	public static String GetImgHead(Context context) {
		SharedPreferences sp = context.getSharedPreferences("ImgHead", Context.MODE_PRIVATE);
		return sp.getString("ImgHead",null);
	}

	//State
	public  static void saveState(Context context, String State) {
		SharedPreferences sp = context.getSharedPreferences("State", Context.MODE_PRIVATE);
		sp.edit().putString("State", State).commit();
	}
	public static String GetState(Context context) {
		SharedPreferences sp = context.getSharedPreferences("State", Context.MODE_PRIVATE);
		return sp.getString("State",null);
	}
	
	/**
	 * 大号字体模式
	 * @param context
	 * @param checked
	 */
	public  static void saveSize(Context context, boolean checked) {
		SharedPreferences sp = context.getSharedPreferences("BigSize", Context.MODE_PRIVATE);
		sp.edit().putBoolean("BigSize", checked).commit();
	}
	public static boolean isBigSize(Context context) {
		SharedPreferences sp = context.getSharedPreferences("BigSize", Context.MODE_PRIVATE);
		boolean isBigSize = sp.getBoolean("BigSize", false);
		return isBigSize;
	}
	/**
	 * IsManager
	 * @param context
	 * @param IsManager
	 */
	public  static void saveIsManager(Context context, boolean IsManager) {
		SharedPreferences sp = context.getSharedPreferences("IsManager", Context.MODE_PRIVATE);
		sp.edit().putBoolean("IsManager", IsManager).commit();
	}
	public static boolean IsManager(Context context) {
		SharedPreferences sp = context.getSharedPreferences("IsManager", Context.MODE_PRIVATE);
		boolean IsManager = sp.getBoolean("IsManager", false);
		return IsManager;
	}

	/**
	 * 大号字体模式
	 * @param context
	 * @param IsLogin
	 */
	public  static void saveIsLogin(Context context, boolean IsLogin) {
		SharedPreferences sp = context.getSharedPreferences("IsLogin", Context.MODE_PRIVATE);
		sp.edit().putBoolean("IsLogin", IsLogin).commit();
	}
	public static boolean IsLogin(Context context) {
		SharedPreferences sp = context.getSharedPreferences("IsLogin", Context.MODE_PRIVATE);
		boolean IsLogin = sp.getBoolean("IsLogin", false);
		return IsLogin;
	}
	/**
	 * 夜间模式
	 * @param context
	 */
	 public  static void saveMode(Context context) {
	        SharedPreferences sp = context.getSharedPreferences("model_color", Context.MODE_PRIVATE);
	        boolean isDark = sp.getBoolean("isDark", false);
	        sp.edit().putBoolean("isDark", !isDark).commit();
		}
	public static boolean readMode(Context context) {
		SharedPreferences sp = context.getSharedPreferences("model_color", Context.MODE_PRIVATE);
		boolean isDark = sp.getBoolean("isDark", false);
		return isDark;
	}
	/**
	 * 点赞 模式
	 * @param context
	 */
	public  static void saveZan(Context context) {
		SharedPreferences sp = context.getSharedPreferences("model_zan", Context.MODE_PRIVATE);
		boolean isZan = sp.getBoolean("isZan", false);
		sp.edit().putBoolean("isZan", !isZan).commit();
	}
	public static boolean readZan(Context context) {
		SharedPreferences sp = context.getSharedPreferences("model_zan", Context.MODE_PRIVATE);
		boolean isZan = sp.getBoolean("isZan", false);
		return isZan;
	}
	public static String SHA1(Context context) {
		try {
			PackageInfo info =context.getPackageManager().getPackageInfo(
					"com.example.phonesecurity.mobilephonesecurity", PackageManager.GET_SIGNATURES);
			byte[] cert = info.signatures[0].toByteArray();
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] publicKey = md.digest(cert);
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < publicKey.length; i++) {
				String appendString = Integer.toHexString(0xFF & publicKey[i])
						.toUpperCase(Locale.US);
				if (appendString.length() == 1)
					hexString.append("0");
				hexString.append(appendString);
				hexString.append(":");
			}
			String result=hexString.toString();
			return result.substring(0, result.length()-1);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
