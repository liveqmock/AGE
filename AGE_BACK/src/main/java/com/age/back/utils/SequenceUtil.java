package com.age.back.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cfca.util.GUID;

import com.age.core.utils.RandomNumberGenerator;

public class SequenceUtil {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");

	/**
	 * 获得贷款申请流水号
	 * @return
	 */
	public static synchronized String getLoanSeqNo() {
		String str = simpleDateFormat.format(Calendar.getInstance().getTime());
		return str + RandomNumberGenerator.getRandomNumber(8);
	}
	/**
	 * 获得交易流水号
	 * @return
	 */
	public static synchronized String getSerialNumber(){
		return GUID.getTxNo();
	}
}
