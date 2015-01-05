package com.age.back.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {

	public static final String CURRENT_USER = "shiro_current_user";

	public static final String UPLOAD_FOLDER = "upload";

	/***附件类型***/
	//营业执照
	public static final String BUSINESS_LICENSE = "1";
	//税务登记证
	public static final String TAXREG_CERTIFICATE = "2";
	//组织机构代码
	public static final String ENTORGNO_CERTIFICATE = "3";
	//银行开户许可证
	public static final String BANKACCOUNT_LICENCE = "4";

	/*****审核状态*****/
	//初始状态
	public static final String INITIAL_STATE = "0";
	//审核中状态
	public static final String REVIEWING = "1";
	//审批通过
	public static final String APPROVED = "2";
	//审批不通过
	public static final String REJECTED = "3";

	/******是否是基本户********/
	//基本户
	public static final String IS_BASIC_ACCOUNT = "1";
	//不是基本户
	public static final String NOT_BASIC_ACCOUNT = "0";

	/*******帐号是否启用**********/
	//启用
	public static final String IS_ENABLED = "1";
	//停用
	public static final String UN_ENABLED = "0";

	/*******贷款状态**********/
	//初始
	public static final String LOAN_INITIAL_STATE = "0";
	//放款中
	public static final String LOAN_OUT_STATE = "1";
	//还款中
	public static final String LOAN_RETURN_STATE = "2";
	//完成
	public static final String LOAN_FINISHI_STATE = "3";

	/****放 / 还 款状态****/
	//初始
	public static final String PAYMENT_INIT = "0";
	//放款中
	public static final String PAYMENT_DOING = "1";
	//成功
	public static final String PAYMENT_SUCCESS = "2";
	//失败
	public static final String PAYMENT_FAIL = "3";

	/** 放贷记录节点**/
	public static final Map<String, String> curStep_OUT = new LinkedHashMap<String, String>();
	static {
		curStep_OUT.put("01", "基本户验证代扣");
		curStep_OUT.put("02", "贷款划拨至基本户");
		curStep_OUT.put("03", "保证金代扣");
		curStep_OUT.put("04", "结算至招标人账户");
	}
	/** 还贷记录节点**/
	public static final Map<String, String> curStep_RETURN = new LinkedHashMap<String, String>();
	static {
		curStep_RETURN.put("11", "基本户验证代扣");
		curStep_RETURN.put("12", "结算至投标人基本户");
		curStep_RETURN.put("13", "保证金代扣");
		curStep_RETURN.put("14", "结算至贷款户");
		curStep_RETURN.put("21", "自行还款支付");
		curStep_RETURN.put("22", "自行还款结算至贷款户");
	}

	/** 下一个记录节点**/
	public static String curStep_NEXT_KEY(String key) {
		char[] charArray = key.toCharArray();
		int last = Integer.parseInt(String.valueOf(charArray[1]));
		if (last == 4)
			last = 3;
		String nextKey = String.valueOf(charArray[0]) + (last + 1);
		return nextKey;
	}

	/** 还贷记录节点**/
	public static final Map<String, String> curStep_STATUS = new LinkedHashMap<String, String>(4);
	static {
		curStep_STATUS.put("0", "初始");
		curStep_STATUS.put("1", "进行中");
		curStep_STATUS.put("2", "成功");
		curStep_STATUS.put("3", "失败");
	}
	/** 拥有者类型**/
	public static final Map<String, String> ownerType = new LinkedHashMap<String, String>(3);
	static {
		//ownerType.put("0", "角色");隐藏
		ownerType.put("1", "机构");
		ownerType.put("2", "人员");
	}
	/****** 机构号 *****/
	public static final String INSTITUTION = "001319";
	/*******账号类型(0：贷款用户；1：信贷人员) accountType**********/
	//贷款用户
	public static final String LOAN_USER = "0";
	//信贷人员
	public static final String CREDIT_USER = "1";

	/*****审核状态(贷款用户注册)*****/
	//初始状态
	public static final String INIT_STATE = "0";
	//审批通过
	public static final String PASS_STATE = "1";
	//审批不通过
	public static final String FAIL_STATE = "2";

	/*****经办动作0=添加；1=启用；2=停用 recAction*****/
	//添加
	public static final String ADD_ACTION = "0";
	//启用
	public static final String ENABLE_ACTION = "1";
	//停用
	public static final String DISABLE_ACTION = "2";

	/**
	 * 流水号类别
	 */
	//申贷编号
	public static final String APPLYNO = "0";
	//放贷订单号
	public static final String FDORDERNO = "1";
	//还贷订单号
	public static final String HDORDERNO = "2";
	//贷款流水号
	public static final String LOANNO = "3";

	/*****是否成功发送：0=初始；1=成功；2=失败*****/
	//初始
	public static final String MAIL_INIT = "0";
	//成功
	public static final String MAIL_SUCCESS = "1";
	//失败
	public static final String MAIL_FAIL = "2";

	//协议标识：0=无效；1=有效
	public static final String FILE_STATUS_INVALID = "0";//无效
	public static final String FILE_STATUS_VALID = "1";//有效

	//环节编号编码规则：S_SJ = 上传环节S_QS = 签署环节
	public static final String S_SJ = "S_SJ";//上传环节
	public static final String S_QS = "S_QS";//签署环节

	///签署状态： 0=未签署 1=已签署
	public static final String SIGNINFO_SIGN = "1";// 已签署
	public static final String SIGNINFO_NO_SIGN = "0";//未签署

	//流程状态：0=初始；1=已启动；2=已结束；3=已暂停flowStatus
	public static final String FLOWSTATUS_INIT = "0";
	public static final String FLOWSTATUS_START = "1";
	public static final String FLOWSTATUS_END = "2";
	public static final String FLOWSTATUS_BREAK = "3";

	// 协议处理状态：0=初始；1=签署中；2=签署完成；bizStatus
	public static final String BIZSTATUS_INIT = "0";
	public static final String BIZSTATUS_START = "1";
	public static final String BIZSTATUS_END = "2";

	//当前环节处理状态：0 =初始；1 =处理中；2=完成；3=退回；
	public static final String STEPSTATUS_INIT = "0";
	public static final String STEPSTATUS_START = "1";
	public static final String STEPSTATUS_END = "2";
	public static final String STEPSTATUS_BACK = "3";

	//拥有者类型 0=角色；1=部门；2:=人员
	public static final String OWNERTYPE_ROLE = "0";
	public static final String OWNERTYPE_ORG = "1";
	public static final String OWNERTYPE_PERSON = "2";
	//贷款企业 机构名称
	public static final String ORGNAME_LOANENT = "贷款企业";

	public static final String LOAN_RETURN = "1";//还贷
}
