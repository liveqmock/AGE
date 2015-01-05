package com.age.back.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 
 * @author zhou
 * @create on 2014年5月16日
 * @desribe
 */
public class OnlinePaymentUtil {

	private static Logger logger = Logger.getLogger(OnlinePaymentUtil.class);

	/**
	 * get the md5 hash of a string
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {

		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			return str;
		} catch (UnsupportedEncodingException e) {
			return str;
		}
		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}
	/**
	 * 
	 * @author zhou
	 * @create on 2014年5月28日
	 * @param record
	 * @return Map<String,Object> paymentMap
	 * @desribe
	 */
//	public static Map<String,Object> getPaymentParameters(TradeRecord record){
//		Map<String,Object> map = new HashMap<String, Object>();
//		String halfP = record.getTransNum() + record.getAmount().toString();
////		String encrypt_key = PropertyUtil.getProperty(CommonConstants.ENCRYPT_KEY);
////		String signature = OnlinePaymentUtil.md5(halfP + OnlinePaymentUtil.md5(encrypt_key));
////		String merid = PropertyUtil.getProperty(CommonConstants.MER_ID);
////		String notificationUrl = PropertyUtil.getProperty(CommonConstants.NOTIFICATION_URL);
////		String paymentUrl = PropertyUtil.getProperty(CommonConstants.PAYMENT_URL);
////		map.put("signature", signature);
////		map.put("merid", merid);
////		map.put("notificationUrl", notificationUrl);
////		map.put("paymentUrl", paymentUrl);
//		return map;
//	}
}
