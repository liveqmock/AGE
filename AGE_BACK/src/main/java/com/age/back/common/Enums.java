package com.age.back.common;

import com.age.core.utils.StrUtils;

public class Enums {

	/**
	 * 企业类别  002=施工；005=监理；004=勘察设计；001=试验检测；003=货物采购
	 * 
	 * @author xuchao
	 * 
	 */
	public enum EType {
		CONSTRUCTION("002", "施工"), OBSERVE("005", "监理"), PROSPECTING("004", "勘察设计"), EXAMINATION("001", "试验检测"), PURCHASE("003", "材料采购");

		String key;

		String displayName;

		private EType(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static EType getEType(String key) {
			if (key.equals(CONSTRUCTION.getKey())) {
				return CONSTRUCTION;
			} else if (key.equals(OBSERVE.getKey())) {
				return OBSERVE;
			} else if (key.equals(PROSPECTING.getKey())) {
				return PROSPECTING;
			} else if (key.equals(EXAMINATION.getKey())) {
				return EXAMINATION;
			} else {
				return PURCHASE;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

	}

	/**
	 * 资审类型：0=资格预审；1=资格后审
	 * 
	 * @author xuchao
	 * 
	 */
	public enum BidCheckType {
		PREQUALIFICATION("001", "资格预审"), POST_QUALIFICATION("002", "资格后审");

		String key;

		String displayName;

		private BidCheckType(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static BidCheckType getBidCheckType(String key) {
			if (key.equals(PREQUALIFICATION.getKey())) {
				return PREQUALIFICATION;
			} else {
				return POST_QUALIFICATION;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

	}

	/**
	 * 当前步骤
	 * 
	 * @author xuchao
	 * 
	 */
	public enum CurrentStep {
		OUT_ONE("01", "放贷-基本户验证代扣"), OUT_TWO("02", "放贷-贷款划拨至基本户"), OUT_THREE("03", "放贷-保证金代扣"), OUT_FOUR("04", "放贷-结算至招标人账户"), RETURN_ONE("11", "还贷-基本户验证代扣"), RETURN_TWO("12", "还贷-结算至投标人基本户"), RETURN_THREE("13", "还贷-保证金代扣"), RETURN_FOUR("14",
				"还贷-结算至贷款户"), RETURN_TWO_ONE("21", "还贷-自行还款支付"), RETURN_TWO_TWO("22", "还贷-自行还款结算至贷款户");

		String key;

		String displayName;

		private CurrentStep(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static CurrentStep getCurrentStep(String key) {
			if (key.equals(OUT_ONE.getKey())) {
				return OUT_ONE;
			} else if (key.equals(OUT_TWO.getKey())) {
				return OUT_TWO;
			} else if (key.equals(OUT_THREE.getKey())) {
				return OUT_THREE;
			} else if (key.equals(OUT_FOUR.getKey())) {
				return OUT_FOUR;
			} else if (key.equals(RETURN_ONE.getKey())) {
				return RETURN_ONE;
			} else if (key.equals(RETURN_TWO.getKey())) {
				return RETURN_TWO;
			} else if (key.equals(RETURN_THREE.getKey())) {
				return RETURN_THREE;
			} else {
				return RETURN_FOUR;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	public enum FlowStatus {
		INITIALIZE("0", "初始"), IN_HAND("1", "处理中"), SUCCESS("2", "处理成功"), FAILURE("3", "处理失败");

		String key;

		String displayName;

		private FlowStatus(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static FlowStatus getFlowStatus(String key) {
			if (key.equals(INITIALIZE.getKey())) {
				return INITIALIZE;
			} else if (key.equals(IN_HAND.getKey())) {
				return IN_HAND;
			} else if (key.equals(SUCCESS.getKey())) {
				return SUCCESS;
			} else {
				return FAILURE;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	/**
	 * 银行编码--银行名称
	 * @author 
	 *
	 */
	public enum Bank {
		ZGYZCXYX_1("100", "中国邮政储蓄银行"),

		ZGGSYX_3("102", "中国工商银行"),

		ZGNYYX_5("103", "中国农业银行"),

		ZGYX_7("104", "中国银行"),

		ZGJSYX_9("105", "中国建设银行"),

		JTYX_11("301", "交通银行"),

		ZXYX_13("302", "中信银行"),

		ZGGDYX_15("303", "中国光大银行"),

		HXYX_17("304", "华夏银行"),

		ZGMSYX_19("305", "中国民生银行"),

		GFYX_21("306", "广发银行"),

		PAYX_23("307", "平安银行"),

		ZSYX_25("308", "招商银行"),

		XYYX_27("309", "兴业银行"),

		SHPDFZYX_29("310", "上海浦东发展银行"),

		HFYX_31("311", "恒丰银行"),

		ZSYX_33("316", "浙商银行"),

		BHYX_35("317", "渤海银行"),

		SHYX_37("401", "上海银行"),

		XMYX_39("402", "厦门银行"),

		BJYX_41("403", "北京银行"),

		YTYX_43("404", "烟台银行"),

		FJHXYX_45("405", "福建海峡银行"),

		NBYX_47("408", "宁波银行"),

		QLYX_49("409", "齐鲁银行"),

		JZSSYYX_51("411", "焦作市商业银行"),

		WZYX_53("412", "温州银行"),

		GZYX_55("413", "广州银行"),

		HKYX_57("414", "汉口银行"),

		SJYX_59("417", "盛京银行"),

		LYYX_61("418", "洛阳银行"),

		LYYX_63("419", "辽阳银行"),

		DLYX_65("420", "大连银行"),

		SZYX_67("421", "苏州银行"),

		HBYX_69("422", "河北银行"),

		HZYX_71("423", "杭州银行"),

		NJYX_73("424", "南京银行"),

		DGYX_75("425", "东莞银行"),

		JHYX_77("426", "金华银行"),

		WLMQSSYYX_79("427", "乌鲁木齐市商业银行"),

		SXYX_81("428", "绍兴银行"),

		CDYX_83("429", "成都银行"),

		FSYX_85("430", "抚顺银行"),

		LSYX_87("431", "临商银行"),

		HBYX_89("432", "湖北银行"),

		HLDYX_91("433", "葫芦岛银行"),

		TJYX_93("434", "天津银行"),

		ZZYX_95("435", "郑州银行"),

		NXYX_97("436", "宁夏银行"),

		ZHHRYX_99("437", "珠海华润银行"),

		QSYX_101("438", "齐商银行"),

		JZYX_103("439", "锦州银行"),

		HSYX_105("440", "徽商银行"),

		ZQYX_107("441", "重庆银行"),

		HEBYX_109("442", "哈尔滨银行"),

		GYSSYYX_111("443", "贵阳市商业银行"),

		XAYX_113("444", "西安银行"),

		DDYX_115("446", "丹东银行"),

		LZYX_117("447", "兰州银行"),

		NCYX_119("448", "南昌银行"),

		JSYX_121("449", "晋商银行"),

		QDYX_123("450", "青岛银行"),

		JLYX_125("451", "吉林银行"),

		JJYX_127("454", "九江银行"),

		RZYX_129("455", "日照银行"),

		ASSSYYX_131("456", "鞍山市商业银行"),

		QHDSSYYX_133("457", "秦皇岛市商业银行"),

		QHYX_135("458", "青海银行"),

		TZYX_137("459", "台州银行"),

		CSYX_139("461", "长沙银行"),

		WFYX_141("462", "潍坊银行"),

		GZYX_143("463", "赣州银行"),

		QZYX_145("464", "泉州银行"),

		YKYX_147("465", "营口银行"),

		FDYX_149("466", "富滇银行"),

		FXYX_151("467", "阜新银行"),

		JXYX_153("470", "嘉兴银行"),

		LFYX_155("472", "廊坊银行"),

		ZJTLSYYX_157("473", "浙江泰隆商业银行"),

		NMGYX_159("474", "内蒙古银行"),

		HZYX_161("475", "湖州银行"),

		GXBBWYX_163("478", "广西北部湾银行"),

		BSYX_165("479", "包商银行"),

		WHSSYYX_167("481", "威海市商业银行"),

		PZHSSYYX_169("483", "攀枝花市商业银行"),

		MYSSYYX_171("485", "绵阳市商业银行"),

		LZSSYYX_173("486", "泸州市商业银行"),

		DTSSYYX_175("487", "大同市商业银行"),

		SMXYX_177("488", "三门峡银行"),

		GDNYYX_179("489", "广东南粤银行"),

		ZJKSSYYX_181("490", "张家口市商业银行"),

		GLYX_183("491", "桂林银行"),

		JSCJSYYX_185("493", "江苏长江商业银行"),

		LZYX_187("495", "柳州银行"),

		NCSSYYX_189("496", "南充市商业银行"),

		LSYX_191("497", "莱商银行"),

		DYYX_193("498", "德阳银行"),

		LPSSSYYX_195("500", "六盘水市商业银行"),

		QJSSYYX_197("502", "曲靖市商业银行"),

		KLYX_199("701", "昆仑银行"),

		SHNCSYYX_201("1401", "上海农村商业银行"),

		KSNCSYYX_203("1402", "昆山农村商业银行"),

		JSCSNCSYYX_205("1403", "江苏常熟农村商业银行"),

		SZNCSYYX_207("1404", "深圳农村商业银行"),

		GZNCSYYX_209("1405", "广州农村商业银行"),

		ZJXSNCHZYX_211("1406", "浙江萧山农村合作银行"),

		GDNHNCSYYX_213("1407", "广东南海农村商业银行"),

		FSSDNCSYYX_215("1408", "佛山顺德农村商业银行"),

		KMSNCXYHZSLHS_217("1409", "昆明市农村信用合作社联合社"),

		HBSNCXYSLHS_219("1410", "湖北省农村信用社联合社"),

		XZSSJNCXYHZSLHS_221("1411", "徐州市市郊农村信用合作社联合社"),

		JSJYNCSYYX_223("1412", "江苏江阴农村商业银行"),

		ZQNCSYYX_225("1413", "重庆农村商业银行"),

		SDSNCXYSLHS_227("1414", "山东省农村信用社联合社"),

		DGNCSYYX_229("1415", "东莞农村商业银行"),

		ZJGNCSYYX_231("1416", "张家港农村商业银行"),

		FJSNCXYSLHS_233("1417", "福建省农村信用社联合社"),

		BJNCSYYX_235("1418", "北京农村商业银行"),

		TJNCHZYX_237("1419", "天津农村合作银行"),

		NBJZNCHZYX_239("1420", "宁波鄞州农村合作银行"),

		FSSSSQNCXYHZSLHS_241("1421", "佛山市三水区农村信用合作社联合社"),

		CDSNCXYHZSLHS_243("1422", "成都市农村信用合作社联合社"),

		CZSNCXYHZSLHS_245("1423", "沧州市农村信用合作社联合社"),

		JSSNCXYHZSLHS_247("1424", "江苏省农村信用合作社联合社"),

		JMSXHNCXYHZSLHS_249("1425", "江门市新会农村信用合作社联合社"),

		GYSNCXYHZSLHS_251("1426", "高要市农村信用合作社联合社"),

		FSNCSYYX_253("1427", "佛山农村商业银行"),

		WJNCSYYX_255("1428", "吴江农村商业银行"),

		ZJSNCXYSLHS_257("1429", "浙江省农村信用社联合社"),

		JSDWNCSYYX_259("1430", "江苏东吴农村商业银行"),

		ZHNSYX_261("1431", "珠海农商银行"),

		ZSNCXYHZSLHS_263("1432", "中山农村信用合作社联合社"),

		TCNCSYYX_265("1433", "太仓农村商业银行"),

		LFSYDSNCXYHZSLHS_267("1434", "临汾市尧都市农村信用合作社联合社"),

		GZSNCXYSLHS_269("1436", "贵州省农村信用社联合社"),

		WXNCSYYX_271("1437", "无锡农村商业银行"),

		HNSNCXYSLHS_273("1438", "湖南省农村信用社联合社"),

		JXSNCXYSLHS_275("1439", "江西省农村信用社联合社"),

		SXSNCXYSLHS_277("1442", "陕西省农村信用社联合社"),

		JSYX_279("1501", "江苏银行"),

		HDSSYYX_281("1502", "邯郸市商业银行"),

		XTYX_283("1503", "邢台银行"),

		CDYX_285("1504", "承德银行"),

		CZYX_287("1505", "沧州银行"),

		JCSSYYX_289("1506", "晋城市商业银行"),

		EEDSYX_291("1507", "鄂尔多斯银行"),

		SRYX_293("1508", "上饶银行"),

		DYSSYYX_295("1509", "东营市商业银行"),

		JNYX_297("1510", "济宁银行"),

		TASSYYX_299("1511", "泰安市商业银行"),

		DZYX_301("1512", "德州银行"),

		KFSSYYX_303("1513", "开封市商业银行"),

		LHSSYYX_305("1514", "漯河市商业银行"),

		SQSSYYX_307("1515", "商丘市商业银行"),

		NYSSYYX_309("1516", "南阳市商业银行"),

		ZJMTSYYX_311("1517", "浙江民泰商业银行"),

		LJYX_313("1518", "龙江银行"),

		ZJCZSYYX_315("1519", "浙江稠州商业银行"),

		AHSNCXYLS_317("1520", "安徽省农村信用联社"),

		GXZZZZQNCXYSLHS_319("1521", "广西壮族自治区农村信用社联合社"),

		HNSNCXYSLHS_321("1522", "海南省农村信用社联合社"),

		YNSNCXYSLHS_323("1523", "云南省农村信用社联合社"),

		NXHHNCSYYX_325("1524", "宁夏黄河农村商业银行"),

		QTCSSYYX_327("9990", "其他城市商业银行"),

		QTNCSYYX_329("9991", "其他农村商业银行"),

		QTNCHZYX_331("9992", "其他农村合作银行"),

		QTCSXYS_333("9993", "其他城市信用社"),

		QTNCXYS_335("9994", "其他农村信用社"),

		HFYX_337("3000", "汇丰银行"),

		DYYX_339("3001", "东亚银行"),

		NYSYYX_341("3002", "南洋商业银行"),

		HSYXZGYXGS_343("3003", "恒生银行(中国)有限公司"),

		ZGYXXGYXGS_345("3004", "中国银行（香港）有限公司"),

		JYYXYXGS_347("3005", "集友银行有限公司"),

		CXYXYXGS_349("3006", "创兴银行有限公司"),

		XZYXZGYXGS_351("3007", "星展银行（中国）有限公司"),

		YHYXZGYXGS_353("3008", "永亨银行（中国）有限公司"),

		YLYX_355("3009", "永隆银行"),

		HQYXZGYXGS_357("3010", "花旗银行（中国）有限公司"),

		MGYXYXGS_359("3011", "美国银行有限公司"),

		MGDTYXZGYXGS_361("3012", "摩根大通银行(中国)有限公司"),

		SLDJRLYXZGYXGS_363("3013", "三菱东京日联银行(中国）有限公司"),

		RBSJZYYXGFYXGS_365("3014", "日本三井住友银行股份有限公司"),

		RSSYYXZGYXGS_367("3015", "瑞穗实业银行（中国）有限公司"),

		RBSKYXGFYXGS_369("3016", "日本山口银行股份有限公司"),

		WHYXZGYXGS_371("3017", "外换银行（中国）有限公司"),

		YLYXZGYXGS_373("3018", "友利银行(中国)有限公司"),

		HGCYYX_375("3021", "韩国产业银行"),

		XHYXZGYXGS_377("3022", "新韩银行(中国)有限公司"),

		HGZXQYYX_379("3023", "韩国中小企业银行"),

		HYYXZGYXGS_381("3024", "韩亚银行（中国）有限公司"),

		HQYXZGYXGS_383("3025", "华侨银行（中国）有限公司"),

		DHYXZGYXGS_385("3026", "大华银行（中国）有限公司"),

		TGPGYXDZYXGS_387("3028", "泰国盘谷银行(大众有限公司)"),

		ADLZYHZYXGFYXGS_389("3029", "奥地利中央合作银行股份有限公司"),

		BLSLHYXGFYXGS_391("3030", "比利时联合银行股份有限公司"),

		BLSFTYXYXGS_393("3031", "比利时富通银行有限公司"),

		HLYX_395("3032", "荷兰银行"),

		HLAZYXGFYXGS_397("3033", "荷兰安智银行股份有限公司"),

		ZDYX_399("3034", "渣打银行"),

		YGSGLHJYXGZYXGS_401("3035", "英国苏格兰皇家银行公众有限公司"),

		FGXYYXZGYXGS_403("3036", "法国兴业银行（中国)有限公司"),

		FGDFHLYXGFYXGS_405("3037", "法国东方汇理银行股份有限公司"),

		FGWMYXGFYXGS_407("3038", "法国外贸银行股份有限公司"),

		DGDLSDYXGFGS_409("3039", "德国德累斯登银行股份公司"),

		DYZYXZGYXGS_411("3040", "德意志银行（中国）有限公司"),

		DGSYYXGFYXGS_413("3041", "德国商业银行股份有限公司"),

		DGXDYXGFYXGS_415("3042", "德国西德银行股份有限公司"),

		DGBFLYZYX_417("3043", "德国巴伐利亚州银行"),

		DGBDYZZYX_419("3044", "德国北德意志州银行"),

		YDLLHSBLYXGFYXGS_421("3045", "意大利联合圣保罗银行股份有限公司"),

		RSXDYXGFYXGS_423("3046", "瑞士信贷银行股份有限公司"),

		RSYX_425("3047", "瑞士银行"),

		JNDFYYXYXGS_427("3048", "加拿大丰业银行有限公司"),

		JNDMTLEYXYXGS_429("3049", "加拿大蒙特利尔银行有限公司"),

		ADLYHXXLYXJTYXGS_431("3050", "澳大利亚和新西兰银行集团有限公司"),

		MGSDLGJYXZGYXGS_433("3051", "摩根士丹利国际银行（中国）有限公司"),

		LHYXZGYXGS_435("3052", "联合银行(中国)有限公司"),

		HLHZYXYXGS_437("3053", "荷兰合作银行有限公司"),

		XMGJYX_439("3054", "厦门国际银行"),

		FGBLYXZGYXGS_441("3055", "法国巴黎银行（中国）有限公司"),

		HSYX_443("3056", "华商银行"),

		HYYX_445("3057", "华一银行"), OTHER("0", "未知");

		String key;

		String displayName;

		private Bank(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		/**
		 * 根据编号取得名称
		 * @param key 编号
		 * @return
		 */
		public static Bank getBankByKey(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(ZGYZCXYX_1.getKey())) {
				return ZGYZCXYX_1;
			} //中国邮政储蓄银行

			if (key.equals(ZGGSYX_3.getKey())) {
				return ZGGSYX_3;
			} //中国工商银行

			if (key.equals(ZGNYYX_5.getKey())) {
				return ZGNYYX_5;
			} //中国农业银行

			if (key.equals(ZGYX_7.getKey())) {
				return ZGYX_7;
			} //中国银行

			if (key.equals(ZGJSYX_9.getKey())) {
				return ZGJSYX_9;
			} //中国建设银行

			if (key.equals(JTYX_11.getKey())) {
				return JTYX_11;
			} //交通银行

			if (key.equals(ZXYX_13.getKey())) {
				return ZXYX_13;
			} //中信银行

			if (key.equals(ZGGDYX_15.getKey())) {
				return ZGGDYX_15;
			} //中国光大银行

			if (key.equals(HXYX_17.getKey())) {
				return HXYX_17;
			} //华夏银行

			if (key.equals(ZGMSYX_19.getKey())) {
				return ZGMSYX_19;
			} //中国民生银行

			if (key.equals(GFYX_21.getKey())) {
				return GFYX_21;
			} //广发银行

			if (key.equals(PAYX_23.getKey())) {
				return PAYX_23;
			} //平安银行

			if (key.equals(ZSYX_25.getKey())) {
				return ZSYX_25;
			} //招商银行

			if (key.equals(XYYX_27.getKey())) {
				return XYYX_27;
			} //兴业银行

			if (key.equals(SHPDFZYX_29.getKey())) {
				return SHPDFZYX_29;
			} //上海浦东发展银行

			if (key.equals(HFYX_31.getKey())) {
				return HFYX_31;
			} //恒丰银行

			if (key.equals(ZSYX_33.getKey())) {
				return ZSYX_33;
			} //浙商银行

			if (key.equals(BHYX_35.getKey())) {
				return BHYX_35;
			} //渤海银行

			if (key.equals(SHYX_37.getKey())) {
				return SHYX_37;
			} //上海银行

			if (key.equals(XMYX_39.getKey())) {
				return XMYX_39;
			} //厦门银行

			if (key.equals(BJYX_41.getKey())) {
				return BJYX_41;
			} //北京银行

			if (key.equals(YTYX_43.getKey())) {
				return YTYX_43;
			} //烟台银行

			if (key.equals(FJHXYX_45.getKey())) {
				return FJHXYX_45;
			} //福建海峡银行

			if (key.equals(NBYX_47.getKey())) {
				return NBYX_47;
			} //宁波银行

			if (key.equals(QLYX_49.getKey())) {
				return QLYX_49;
			} //齐鲁银行

			if (key.equals(JZSSYYX_51.getKey())) {
				return JZSSYYX_51;
			} //焦作市商业银行

			if (key.equals(WZYX_53.getKey())) {
				return WZYX_53;
			} //温州银行

			if (key.equals(GZYX_55.getKey())) {
				return GZYX_55;
			} //广州银行

			if (key.equals(HKYX_57.getKey())) {
				return HKYX_57;
			} //汉口银行

			if (key.equals(SJYX_59.getKey())) {
				return SJYX_59;
			} //盛京银行

			if (key.equals(LYYX_61.getKey())) {
				return LYYX_61;
			} //洛阳银行

			if (key.equals(LYYX_63.getKey())) {
				return LYYX_63;
			} //辽阳银行

			if (key.equals(DLYX_65.getKey())) {
				return DLYX_65;
			} //大连银行

			if (key.equals(SZYX_67.getKey())) {
				return SZYX_67;
			} //苏州银行

			if (key.equals(HBYX_69.getKey())) {
				return HBYX_69;
			} //河北银行

			if (key.equals(HZYX_71.getKey())) {
				return HZYX_71;
			} //杭州银行

			if (key.equals(NJYX_73.getKey())) {
				return NJYX_73;
			} //南京银行

			if (key.equals(DGYX_75.getKey())) {
				return DGYX_75;
			} //东莞银行

			if (key.equals(JHYX_77.getKey())) {
				return JHYX_77;
			} //金华银行

			if (key.equals(WLMQSSYYX_79.getKey())) {
				return WLMQSSYYX_79;
			} //乌鲁木齐市商业银行

			if (key.equals(SXYX_81.getKey())) {
				return SXYX_81;
			} //绍兴银行

			if (key.equals(CDYX_83.getKey())) {
				return CDYX_83;
			} //成都银行

			if (key.equals(FSYX_85.getKey())) {
				return FSYX_85;
			} //抚顺银行

			if (key.equals(LSYX_87.getKey())) {
				return LSYX_87;
			} //临商银行

			if (key.equals(HBYX_89.getKey())) {
				return HBYX_89;
			} //湖北银行

			if (key.equals(HLDYX_91.getKey())) {
				return HLDYX_91;
			} //葫芦岛银行

			if (key.equals(TJYX_93.getKey())) {
				return TJYX_93;
			} //天津银行

			if (key.equals(ZZYX_95.getKey())) {
				return ZZYX_95;
			} //郑州银行

			if (key.equals(NXYX_97.getKey())) {
				return NXYX_97;
			} //宁夏银行

			if (key.equals(ZHHRYX_99.getKey())) {
				return ZHHRYX_99;
			} //珠海华润银行

			if (key.equals(QSYX_101.getKey())) {
				return QSYX_101;
			} //齐商银行

			if (key.equals(JZYX_103.getKey())) {
				return JZYX_103;
			} //锦州银行

			if (key.equals(HSYX_105.getKey())) {
				return HSYX_105;
			} //徽商银行

			if (key.equals(ZQYX_107.getKey())) {
				return ZQYX_107;
			} //重庆银行

			if (key.equals(HEBYX_109.getKey())) {
				return HEBYX_109;
			} //哈尔滨银行

			if (key.equals(GYSSYYX_111.getKey())) {
				return GYSSYYX_111;
			} //贵阳市商业银行

			if (key.equals(XAYX_113.getKey())) {
				return XAYX_113;
			} //西安银行

			if (key.equals(DDYX_115.getKey())) {
				return DDYX_115;
			} //丹东银行

			if (key.equals(LZYX_117.getKey())) {
				return LZYX_117;
			} //兰州银行

			if (key.equals(NCYX_119.getKey())) {
				return NCYX_119;
			} //南昌银行

			if (key.equals(JSYX_121.getKey())) {
				return JSYX_121;
			} //晋商银行

			if (key.equals(QDYX_123.getKey())) {
				return QDYX_123;
			} //青岛银行

			if (key.equals(JLYX_125.getKey())) {
				return JLYX_125;
			} //吉林银行

			if (key.equals(JJYX_127.getKey())) {
				return JJYX_127;
			} //九江银行

			if (key.equals(RZYX_129.getKey())) {
				return RZYX_129;
			} //日照银行

			if (key.equals(ASSSYYX_131.getKey())) {
				return ASSSYYX_131;
			} //鞍山市商业银行

			if (key.equals(QHDSSYYX_133.getKey())) {
				return QHDSSYYX_133;
			} //秦皇岛市商业银行

			if (key.equals(QHYX_135.getKey())) {
				return QHYX_135;
			} //青海银行

			if (key.equals(TZYX_137.getKey())) {
				return TZYX_137;
			} //台州银行

			if (key.equals(CSYX_139.getKey())) {
				return CSYX_139;
			} //长沙银行

			if (key.equals(WFYX_141.getKey())) {
				return WFYX_141;
			} //潍坊银行

			if (key.equals(GZYX_143.getKey())) {
				return GZYX_143;
			} //赣州银行

			if (key.equals(QZYX_145.getKey())) {
				return QZYX_145;
			} //泉州银行

			if (key.equals(YKYX_147.getKey())) {
				return YKYX_147;
			} //营口银行

			if (key.equals(FDYX_149.getKey())) {
				return FDYX_149;
			} //富滇银行

			if (key.equals(FXYX_151.getKey())) {
				return FXYX_151;
			} //阜新银行

			if (key.equals(JXYX_153.getKey())) {
				return JXYX_153;
			} //嘉兴银行

			if (key.equals(LFYX_155.getKey())) {
				return LFYX_155;
			} //廊坊银行

			if (key.equals(ZJTLSYYX_157.getKey())) {
				return ZJTLSYYX_157;
			} //浙江泰隆商业银行

			if (key.equals(NMGYX_159.getKey())) {
				return NMGYX_159;
			} //内蒙古银行

			if (key.equals(HZYX_161.getKey())) {
				return HZYX_161;
			} //湖州银行

			if (key.equals(GXBBWYX_163.getKey())) {
				return GXBBWYX_163;
			} //广西北部湾银行

			if (key.equals(BSYX_165.getKey())) {
				return BSYX_165;
			} //包商银行

			if (key.equals(WHSSYYX_167.getKey())) {
				return WHSSYYX_167;
			} //威海市商业银行

			if (key.equals(PZHSSYYX_169.getKey())) {
				return PZHSSYYX_169;
			} //攀枝花市商业银行

			if (key.equals(MYSSYYX_171.getKey())) {
				return MYSSYYX_171;
			} //绵阳市商业银行

			if (key.equals(LZSSYYX_173.getKey())) {
				return LZSSYYX_173;
			} //泸州市商业银行

			if (key.equals(DTSSYYX_175.getKey())) {
				return DTSSYYX_175;
			} //大同市商业银行

			if (key.equals(SMXYX_177.getKey())) {
				return SMXYX_177;
			} //三门峡银行

			if (key.equals(GDNYYX_179.getKey())) {
				return GDNYYX_179;
			} //广东南粤银行

			if (key.equals(ZJKSSYYX_181.getKey())) {
				return ZJKSSYYX_181;
			} //张家口市商业银行

			if (key.equals(GLYX_183.getKey())) {
				return GLYX_183;
			} //桂林银行

			if (key.equals(JSCJSYYX_185.getKey())) {
				return JSCJSYYX_185;
			} //江苏长江商业银行

			if (key.equals(LZYX_187.getKey())) {
				return LZYX_187;
			} //柳州银行

			if (key.equals(NCSSYYX_189.getKey())) {
				return NCSSYYX_189;
			} //南充市商业银行

			if (key.equals(LSYX_191.getKey())) {
				return LSYX_191;
			} //莱商银行

			if (key.equals(DYYX_193.getKey())) {
				return DYYX_193;
			} //德阳银行

			if (key.equals(LPSSSYYX_195.getKey())) {
				return LPSSSYYX_195;
			} //六盘水市商业银行

			if (key.equals(QJSSYYX_197.getKey())) {
				return QJSSYYX_197;
			} //曲靖市商业银行

			if (key.equals(KLYX_199.getKey())) {
				return KLYX_199;
			} //昆仑银行

			if (key.equals(SHNCSYYX_201.getKey())) {
				return SHNCSYYX_201;
			} //上海农村商业银行

			if (key.equals(KSNCSYYX_203.getKey())) {
				return KSNCSYYX_203;
			} //昆山农村商业银行

			if (key.equals(JSCSNCSYYX_205.getKey())) {
				return JSCSNCSYYX_205;
			} //江苏常熟农村商业银行

			if (key.equals(SZNCSYYX_207.getKey())) {
				return SZNCSYYX_207;
			} //深圳农村商业银行

			if (key.equals(GZNCSYYX_209.getKey())) {
				return GZNCSYYX_209;
			} //广州农村商业银行

			if (key.equals(ZJXSNCHZYX_211.getKey())) {
				return ZJXSNCHZYX_211;
			} //浙江萧山农村合作银行

			if (key.equals(GDNHNCSYYX_213.getKey())) {
				return GDNHNCSYYX_213;
			} //广东南海农村商业银行

			if (key.equals(FSSDNCSYYX_215.getKey())) {
				return FSSDNCSYYX_215;
			} //佛山顺德农村商业银行

			if (key.equals(KMSNCXYHZSLHS_217.getKey())) {
				return KMSNCXYHZSLHS_217;
			} //昆明市农村信用合作社联合社

			if (key.equals(HBSNCXYSLHS_219.getKey())) {
				return HBSNCXYSLHS_219;
			} //湖北省农村信用社联合社

			if (key.equals(XZSSJNCXYHZSLHS_221.getKey())) {
				return XZSSJNCXYHZSLHS_221;
			} //徐州市市郊农村信用合作社联合社

			if (key.equals(JSJYNCSYYX_223.getKey())) {
				return JSJYNCSYYX_223;
			} //江苏江阴农村商业银行

			if (key.equals(ZQNCSYYX_225.getKey())) {
				return ZQNCSYYX_225;
			} //重庆农村商业银行

			if (key.equals(SDSNCXYSLHS_227.getKey())) {
				return SDSNCXYSLHS_227;
			} //山东省农村信用社联合社

			if (key.equals(DGNCSYYX_229.getKey())) {
				return DGNCSYYX_229;
			} //东莞农村商业银行

			if (key.equals(ZJGNCSYYX_231.getKey())) {
				return ZJGNCSYYX_231;
			} //张家港农村商业银行

			if (key.equals(FJSNCXYSLHS_233.getKey())) {
				return FJSNCXYSLHS_233;
			} //福建省农村信用社联合社

			if (key.equals(BJNCSYYX_235.getKey())) {
				return BJNCSYYX_235;
			} //北京农村商业银行

			if (key.equals(TJNCHZYX_237.getKey())) {
				return TJNCHZYX_237;
			} //天津农村合作银行

			if (key.equals(NBJZNCHZYX_239.getKey())) {
				return NBJZNCHZYX_239;
			} //宁波鄞州农村合作银行

			if (key.equals(FSSSSQNCXYHZSLHS_241.getKey())) {
				return FSSSSQNCXYHZSLHS_241;
			} //佛山市三水区农村信用合作社联合社

			if (key.equals(CDSNCXYHZSLHS_243.getKey())) {
				return CDSNCXYHZSLHS_243;
			} //成都市农村信用合作社联合社

			if (key.equals(CZSNCXYHZSLHS_245.getKey())) {
				return CZSNCXYHZSLHS_245;
			} //沧州市农村信用合作社联合社

			if (key.equals(JSSNCXYHZSLHS_247.getKey())) {
				return JSSNCXYHZSLHS_247;
			} //江苏省农村信用合作社联合社

			if (key.equals(JMSXHNCXYHZSLHS_249.getKey())) {
				return JMSXHNCXYHZSLHS_249;
			} //江门市新会农村信用合作社联合社

			if (key.equals(GYSNCXYHZSLHS_251.getKey())) {
				return GYSNCXYHZSLHS_251;
			} //高要市农村信用合作社联合社

			if (key.equals(FSNCSYYX_253.getKey())) {
				return FSNCSYYX_253;
			} //佛山农村商业银行

			if (key.equals(WJNCSYYX_255.getKey())) {
				return WJNCSYYX_255;
			} //吴江农村商业银行

			if (key.equals(ZJSNCXYSLHS_257.getKey())) {
				return ZJSNCXYSLHS_257;
			} //浙江省农村信用社联合社

			if (key.equals(JSDWNCSYYX_259.getKey())) {
				return JSDWNCSYYX_259;
			} //江苏东吴农村商业银行

			if (key.equals(ZHNSYX_261.getKey())) {
				return ZHNSYX_261;
			} //珠海农商银行

			if (key.equals(ZSNCXYHZSLHS_263.getKey())) {
				return ZSNCXYHZSLHS_263;
			} //中山农村信用合作社联合社

			if (key.equals(TCNCSYYX_265.getKey())) {
				return TCNCSYYX_265;
			} //太仓农村商业银行

			if (key.equals(LFSYDSNCXYHZSLHS_267.getKey())) {
				return LFSYDSNCXYHZSLHS_267;
			} //临汾市尧都市农村信用合作社联合社

			if (key.equals(GZSNCXYSLHS_269.getKey())) {
				return GZSNCXYSLHS_269;
			} //贵州省农村信用社联合社

			if (key.equals(WXNCSYYX_271.getKey())) {
				return WXNCSYYX_271;
			} //无锡农村商业银行

			if (key.equals(HNSNCXYSLHS_273.getKey())) {
				return HNSNCXYSLHS_273;
			} //湖南省农村信用社联合社

			if (key.equals(JXSNCXYSLHS_275.getKey())) {
				return JXSNCXYSLHS_275;
			} //江西省农村信用社联合社

			if (key.equals(SXSNCXYSLHS_277.getKey())) {
				return SXSNCXYSLHS_277;
			} //陕西省农村信用社联合社

			if (key.equals(JSYX_279.getKey())) {
				return JSYX_279;
			} //江苏银行

			if (key.equals(HDSSYYX_281.getKey())) {
				return HDSSYYX_281;
			} //邯郸市商业银行

			if (key.equals(XTYX_283.getKey())) {
				return XTYX_283;
			} //邢台银行

			if (key.equals(CDYX_285.getKey())) {
				return CDYX_285;
			} //承德银行

			if (key.equals(CZYX_287.getKey())) {
				return CZYX_287;
			} //沧州银行

			if (key.equals(JCSSYYX_289.getKey())) {
				return JCSSYYX_289;
			} //晋城市商业银行

			if (key.equals(EEDSYX_291.getKey())) {
				return EEDSYX_291;
			} //鄂尔多斯银行

			if (key.equals(SRYX_293.getKey())) {
				return SRYX_293;
			} //上饶银行

			if (key.equals(DYSSYYX_295.getKey())) {
				return DYSSYYX_295;
			} //东营市商业银行

			if (key.equals(JNYX_297.getKey())) {
				return JNYX_297;
			} //济宁银行

			if (key.equals(TASSYYX_299.getKey())) {
				return TASSYYX_299;
			} //泰安市商业银行

			if (key.equals(DZYX_301.getKey())) {
				return DZYX_301;
			} //德州银行

			if (key.equals(KFSSYYX_303.getKey())) {
				return KFSSYYX_303;
			} //开封市商业银行

			if (key.equals(LHSSYYX_305.getKey())) {
				return LHSSYYX_305;
			} //漯河市商业银行

			if (key.equals(SQSSYYX_307.getKey())) {
				return SQSSYYX_307;
			} //商丘市商业银行

			if (key.equals(NYSSYYX_309.getKey())) {
				return NYSSYYX_309;
			} //南阳市商业银行

			if (key.equals(ZJMTSYYX_311.getKey())) {
				return ZJMTSYYX_311;
			} //浙江民泰商业银行

			if (key.equals(LJYX_313.getKey())) {
				return LJYX_313;
			} //龙江银行

			if (key.equals(ZJCZSYYX_315.getKey())) {
				return ZJCZSYYX_315;
			} //浙江稠州商业银行

			if (key.equals(AHSNCXYLS_317.getKey())) {
				return AHSNCXYLS_317;
			} //安徽省农村信用联社

			if (key.equals(GXZZZZQNCXYSLHS_319.getKey())) {
				return GXZZZZQNCXYSLHS_319;
			} //广西壮族自治区农村信用社联合社

			if (key.equals(HNSNCXYSLHS_321.getKey())) {
				return HNSNCXYSLHS_321;
			} //海南省农村信用社联合社

			if (key.equals(YNSNCXYSLHS_323.getKey())) {
				return YNSNCXYSLHS_323;
			} //云南省农村信用社联合社

			if (key.equals(NXHHNCSYYX_325.getKey())) {
				return NXHHNCSYYX_325;
			} //宁夏黄河农村商业银行

			if (key.equals(QTCSSYYX_327.getKey())) {
				return QTCSSYYX_327;
			} //其他城市商业银行

			if (key.equals(QTNCSYYX_329.getKey())) {
				return QTNCSYYX_329;
			} //其他农村商业银行

			if (key.equals(QTNCHZYX_331.getKey())) {
				return QTNCHZYX_331;
			} //其他农村合作银行

			if (key.equals(QTCSXYS_333.getKey())) {
				return QTCSXYS_333;
			} //其他城市信用社

			if (key.equals(QTNCXYS_335.getKey())) {
				return QTNCXYS_335;
			} //其他农村信用社

			if (key.equals(HFYX_337.getKey())) {
				return HFYX_337;
			} //汇丰银行

			if (key.equals(DYYX_339.getKey())) {
				return DYYX_339;
			} //东亚银行

			if (key.equals(NYSYYX_341.getKey())) {
				return NYSYYX_341;
			} //南洋商业银行

			if (key.equals(HSYXZGYXGS_343.getKey())) {
				return HSYXZGYXGS_343;
			} //恒生银行(中国)有限公司

			if (key.equals(ZGYXXGYXGS_345.getKey())) {
				return ZGYXXGYXGS_345;
			} //中国银行（香港）有限公司

			if (key.equals(JYYXYXGS_347.getKey())) {
				return JYYXYXGS_347;
			} //集友银行有限公司

			if (key.equals(CXYXYXGS_349.getKey())) {
				return CXYXYXGS_349;
			} //创兴银行有限公司

			if (key.equals(XZYXZGYXGS_351.getKey())) {
				return XZYXZGYXGS_351;
			} //星展银行（中国）有限公司

			if (key.equals(YHYXZGYXGS_353.getKey())) {
				return YHYXZGYXGS_353;
			} //永亨银行（中国）有限公司

			if (key.equals(YLYX_355.getKey())) {
				return YLYX_355;
			} //永隆银行

			if (key.equals(HQYXZGYXGS_357.getKey())) {
				return HQYXZGYXGS_357;
			} //花旗银行（中国）有限公司

			if (key.equals(MGYXYXGS_359.getKey())) {
				return MGYXYXGS_359;
			} //美国银行有限公司

			if (key.equals(MGDTYXZGYXGS_361.getKey())) {
				return MGDTYXZGYXGS_361;
			} //摩根大通银行(中国)有限公司

			if (key.equals(SLDJRLYXZGYXGS_363.getKey())) {
				return SLDJRLYXZGYXGS_363;
			} //三菱东京日联银行(中国）有限公司

			if (key.equals(RBSJZYYXGFYXGS_365.getKey())) {
				return RBSJZYYXGFYXGS_365;
			} //日本三井住友银行股份有限公司

			if (key.equals(RSSYYXZGYXGS_367.getKey())) {
				return RSSYYXZGYXGS_367;
			} //瑞穗实业银行（中国）有限公司

			if (key.equals(RBSKYXGFYXGS_369.getKey())) {
				return RBSKYXGFYXGS_369;
			} //日本山口银行股份有限公司

			if (key.equals(WHYXZGYXGS_371.getKey())) {
				return WHYXZGYXGS_371;
			} //外换银行（中国）有限公司

			if (key.equals(YLYXZGYXGS_373.getKey())) {
				return YLYXZGYXGS_373;
			} //友利银行(中国)有限公司

			if (key.equals(HGCYYX_375.getKey())) {
				return HGCYYX_375;
			} //韩国产业银行

			if (key.equals(XHYXZGYXGS_377.getKey())) {
				return XHYXZGYXGS_377;
			} //新韩银行(中国)有限公司

			if (key.equals(HGZXQYYX_379.getKey())) {
				return HGZXQYYX_379;
			} //韩国中小企业银行

			if (key.equals(HYYXZGYXGS_381.getKey())) {
				return HYYXZGYXGS_381;
			} //韩亚银行（中国）有限公司

			if (key.equals(HQYXZGYXGS_383.getKey())) {
				return HQYXZGYXGS_383;
			} //华侨银行（中国）有限公司

			if (key.equals(DHYXZGYXGS_385.getKey())) {
				return DHYXZGYXGS_385;
			} //大华银行（中国）有限公司

			if (key.equals(TGPGYXDZYXGS_387.getKey())) {
				return TGPGYXDZYXGS_387;
			} //泰国盘谷银行(大众有限公司)

			if (key.equals(ADLZYHZYXGFYXGS_389.getKey())) {
				return ADLZYHZYXGFYXGS_389;
			} //奥地利中央合作银行股份有限公司

			if (key.equals(BLSLHYXGFYXGS_391.getKey())) {
				return BLSLHYXGFYXGS_391;
			} //比利时联合银行股份有限公司

			if (key.equals(BLSFTYXYXGS_393.getKey())) {
				return BLSFTYXYXGS_393;
			} //比利时富通银行有限公司

			if (key.equals(HLYX_395.getKey())) {
				return HLYX_395;
			} //荷兰银行

			if (key.equals(HLAZYXGFYXGS_397.getKey())) {
				return HLAZYXGFYXGS_397;
			} //荷兰安智银行股份有限公司

			if (key.equals(ZDYX_399.getKey())) {
				return ZDYX_399;
			} //渣打银行

			if (key.equals(YGSGLHJYXGZYXGS_401.getKey())) {
				return YGSGLHJYXGZYXGS_401;
			} //英国苏格兰皇家银行公众有限公司

			if (key.equals(FGXYYXZGYXGS_403.getKey())) {
				return FGXYYXZGYXGS_403;
			} //法国兴业银行（中国)有限公司

			if (key.equals(FGDFHLYXGFYXGS_405.getKey())) {
				return FGDFHLYXGFYXGS_405;
			} //法国东方汇理银行股份有限公司

			if (key.equals(FGWMYXGFYXGS_407.getKey())) {
				return FGWMYXGFYXGS_407;
			} //法国外贸银行股份有限公司

			if (key.equals(DGDLSDYXGFGS_409.getKey())) {
				return DGDLSDYXGFGS_409;
			} //德国德累斯登银行股份公司

			if (key.equals(DYZYXZGYXGS_411.getKey())) {
				return DYZYXZGYXGS_411;
			} //德意志银行（中国）有限公司

			if (key.equals(DGSYYXGFYXGS_413.getKey())) {
				return DGSYYXGFYXGS_413;
			} //德国商业银行股份有限公司

			if (key.equals(DGXDYXGFYXGS_415.getKey())) {
				return DGXDYXGFYXGS_415;
			} //德国西德银行股份有限公司

			if (key.equals(DGBFLYZYX_417.getKey())) {
				return DGBFLYZYX_417;
			} //德国巴伐利亚州银行

			if (key.equals(DGBDYZZYX_419.getKey())) {
				return DGBDYZZYX_419;
			} //德国北德意志州银行

			if (key.equals(YDLLHSBLYXGFYXGS_421.getKey())) {
				return YDLLHSBLYXGFYXGS_421;
			} //意大利联合圣保罗银行股份有限公司

			if (key.equals(RSXDYXGFYXGS_423.getKey())) {
				return RSXDYXGFYXGS_423;
			} //瑞士信贷银行股份有限公司

			if (key.equals(RSYX_425.getKey())) {
				return RSYX_425;
			} //瑞士银行

			if (key.equals(JNDFYYXYXGS_427.getKey())) {
				return JNDFYYXYXGS_427;
			} //加拿大丰业银行有限公司

			if (key.equals(JNDMTLEYXYXGS_429.getKey())) {
				return JNDMTLEYXYXGS_429;
			} //加拿大蒙特利尔银行有限公司

			if (key.equals(ADLYHXXLYXJTYXGS_431.getKey())) {
				return ADLYHXXLYXJTYXGS_431;
			} //澳大利亚和新西兰银行集团有限公司

			if (key.equals(MGSDLGJYXZGYXGS_433.getKey())) {
				return MGSDLGJYXZGYXGS_433;
			} //摩根士丹利国际银行（中国）有限公司

			if (key.equals(LHYXZGYXGS_435.getKey())) {
				return LHYXZGYXGS_435;
			} //联合银行(中国)有限公司

			if (key.equals(HLHZYXYXGS_437.getKey())) {
				return HLHZYXYXGS_437;
			} //荷兰合作银行有限公司

			if (key.equals(XMGJYX_439.getKey())) {
				return XMGJYX_439;
			} //厦门国际银行

			if (key.equals(FGBLYXZGYXGS_441.getKey())) {
				return FGBLYXZGYXGS_441;
			} //法国巴黎银行（中国）有限公司

			if (key.equals(HSYX_443.getKey())) {
				return HSYX_443;
			} //华商银行

			if (key.equals(HYYX_445.getKey())) {
				return HYYX_445;
			} //华一银行
			else {
				return OTHER;
			}
		}

		/**
		 * 根据名称取得编号
		 * @param displayName 银行名称
		 * @param blank 空串
		 * @return
		 */

		public static Bank getBankByName(String displayName) {
			if (displayName.equals(ZGYZCXYX_1.getDisplayName())) {
				return ZGYZCXYX_1;
			} //中国邮政储蓄银行

			if (displayName.equals(ZGGSYX_3.getDisplayName())) {
				return ZGGSYX_3;
			} //中国工商银行

			if (displayName.equals(ZGNYYX_5.getDisplayName())) {
				return ZGNYYX_5;
			} //中国农业银行

			if (displayName.equals(ZGYX_7.getDisplayName())) {
				return ZGYX_7;
			} //中国银行

			if (displayName.equals(ZGJSYX_9.getDisplayName())) {
				return ZGJSYX_9;
			} //中国建设银行

			if (displayName.equals(JTYX_11.getDisplayName())) {
				return JTYX_11;
			} //交通银行

			if (displayName.equals(ZXYX_13.getDisplayName())) {
				return ZXYX_13;
			} //中信银行

			if (displayName.equals(ZGGDYX_15.getDisplayName())) {
				return ZGGDYX_15;
			} //中国光大银行

			if (displayName.equals(HXYX_17.getDisplayName())) {
				return HXYX_17;
			} //华夏银行

			if (displayName.equals(ZGMSYX_19.getDisplayName())) {
				return ZGMSYX_19;
			} //中国民生银行

			if (displayName.equals(GFYX_21.getDisplayName())) {
				return GFYX_21;
			} //广发银行

			if (displayName.equals(PAYX_23.getDisplayName())) {
				return PAYX_23;
			} //平安银行

			if (displayName.equals(ZSYX_25.getDisplayName())) {
				return ZSYX_25;
			} //招商银行

			if (displayName.equals(XYYX_27.getDisplayName())) {
				return XYYX_27;
			} //兴业银行

			if (displayName.equals(SHPDFZYX_29.getDisplayName())) {
				return SHPDFZYX_29;
			} //上海浦东发展银行

			if (displayName.equals(HFYX_31.getDisplayName())) {
				return HFYX_31;
			} //恒丰银行

			if (displayName.equals(ZSYX_33.getDisplayName())) {
				return ZSYX_33;
			} //浙商银行

			if (displayName.equals(BHYX_35.getDisplayName())) {
				return BHYX_35;
			} //渤海银行

			if (displayName.equals(SHYX_37.getDisplayName())) {
				return SHYX_37;
			} //上海银行

			if (displayName.equals(XMYX_39.getDisplayName())) {
				return XMYX_39;
			} //厦门银行

			if (displayName.equals(BJYX_41.getDisplayName())) {
				return BJYX_41;
			} //北京银行

			if (displayName.equals(YTYX_43.getDisplayName())) {
				return YTYX_43;
			} //烟台银行

			if (displayName.equals(FJHXYX_45.getDisplayName())) {
				return FJHXYX_45;
			} //福建海峡银行

			if (displayName.equals(NBYX_47.getDisplayName())) {
				return NBYX_47;
			} //宁波银行

			if (displayName.equals(QLYX_49.getDisplayName())) {
				return QLYX_49;
			} //齐鲁银行

			if (displayName.equals(JZSSYYX_51.getDisplayName())) {
				return JZSSYYX_51;
			} //焦作市商业银行

			if (displayName.equals(WZYX_53.getDisplayName())) {
				return WZYX_53;
			} //温州银行

			if (displayName.equals(GZYX_55.getDisplayName())) {
				return GZYX_55;
			} //广州银行

			if (displayName.equals(HKYX_57.getDisplayName())) {
				return HKYX_57;
			} //汉口银行

			if (displayName.equals(SJYX_59.getDisplayName())) {
				return SJYX_59;
			} //盛京银行

			if (displayName.equals(LYYX_61.getDisplayName())) {
				return LYYX_61;
			} //洛阳银行

			if (displayName.equals(LYYX_63.getDisplayName())) {
				return LYYX_63;
			} //辽阳银行

			if (displayName.equals(DLYX_65.getDisplayName())) {
				return DLYX_65;
			} //大连银行

			if (displayName.equals(SZYX_67.getDisplayName())) {
				return SZYX_67;
			} //苏州银行

			if (displayName.equals(HBYX_69.getDisplayName())) {
				return HBYX_69;
			} //河北银行

			if (displayName.equals(HZYX_71.getDisplayName())) {
				return HZYX_71;
			} //杭州银行

			if (displayName.equals(NJYX_73.getDisplayName())) {
				return NJYX_73;
			} //南京银行

			if (displayName.equals(DGYX_75.getDisplayName())) {
				return DGYX_75;
			} //东莞银行

			if (displayName.equals(JHYX_77.getDisplayName())) {
				return JHYX_77;
			} //金华银行

			if (displayName.equals(WLMQSSYYX_79.getDisplayName())) {
				return WLMQSSYYX_79;
			} //乌鲁木齐市商业银行

			if (displayName.equals(SXYX_81.getDisplayName())) {
				return SXYX_81;
			} //绍兴银行

			if (displayName.equals(CDYX_83.getDisplayName())) {
				return CDYX_83;
			} //成都银行

			if (displayName.equals(FSYX_85.getDisplayName())) {
				return FSYX_85;
			} //抚顺银行

			if (displayName.equals(LSYX_87.getDisplayName())) {
				return LSYX_87;
			} //临商银行

			if (displayName.equals(HBYX_89.getDisplayName())) {
				return HBYX_89;
			} //湖北银行

			if (displayName.equals(HLDYX_91.getDisplayName())) {
				return HLDYX_91;
			} //葫芦岛银行

			if (displayName.equals(TJYX_93.getDisplayName())) {
				return TJYX_93;
			} //天津银行

			if (displayName.equals(ZZYX_95.getDisplayName())) {
				return ZZYX_95;
			} //郑州银行

			if (displayName.equals(NXYX_97.getDisplayName())) {
				return NXYX_97;
			} //宁夏银行

			if (displayName.equals(ZHHRYX_99.getDisplayName())) {
				return ZHHRYX_99;
			} //珠海华润银行

			if (displayName.equals(QSYX_101.getDisplayName())) {
				return QSYX_101;
			} //齐商银行

			if (displayName.equals(JZYX_103.getDisplayName())) {
				return JZYX_103;
			} //锦州银行

			if (displayName.equals(HSYX_105.getDisplayName())) {
				return HSYX_105;
			} //徽商银行

			if (displayName.equals(ZQYX_107.getDisplayName())) {
				return ZQYX_107;
			} //重庆银行

			if (displayName.equals(HEBYX_109.getDisplayName())) {
				return HEBYX_109;
			} //哈尔滨银行

			if (displayName.equals(GYSSYYX_111.getDisplayName())) {
				return GYSSYYX_111;
			} //贵阳市商业银行

			if (displayName.equals(XAYX_113.getDisplayName())) {
				return XAYX_113;
			} //西安银行

			if (displayName.equals(DDYX_115.getDisplayName())) {
				return DDYX_115;
			} //丹东银行

			if (displayName.equals(LZYX_117.getDisplayName())) {
				return LZYX_117;
			} //兰州银行

			if (displayName.equals(NCYX_119.getDisplayName())) {
				return NCYX_119;
			} //南昌银行

			if (displayName.equals(JSYX_121.getDisplayName())) {
				return JSYX_121;
			} //晋商银行

			if (displayName.equals(QDYX_123.getDisplayName())) {
				return QDYX_123;
			} //青岛银行

			if (displayName.equals(JLYX_125.getDisplayName())) {
				return JLYX_125;
			} //吉林银行

			if (displayName.equals(JJYX_127.getDisplayName())) {
				return JJYX_127;
			} //九江银行

			if (displayName.equals(RZYX_129.getDisplayName())) {
				return RZYX_129;
			} //日照银行

			if (displayName.equals(ASSSYYX_131.getDisplayName())) {
				return ASSSYYX_131;
			} //鞍山市商业银行

			if (displayName.equals(QHDSSYYX_133.getDisplayName())) {
				return QHDSSYYX_133;
			} //秦皇岛市商业银行

			if (displayName.equals(QHYX_135.getDisplayName())) {
				return QHYX_135;
			} //青海银行

			if (displayName.equals(TZYX_137.getDisplayName())) {
				return TZYX_137;
			} //台州银行

			if (displayName.equals(CSYX_139.getDisplayName())) {
				return CSYX_139;
			} //长沙银行

			if (displayName.equals(WFYX_141.getDisplayName())) {
				return WFYX_141;
			} //潍坊银行

			if (displayName.equals(GZYX_143.getDisplayName())) {
				return GZYX_143;
			} //赣州银行

			if (displayName.equals(QZYX_145.getDisplayName())) {
				return QZYX_145;
			} //泉州银行

			if (displayName.equals(YKYX_147.getDisplayName())) {
				return YKYX_147;
			} //营口银行

			if (displayName.equals(FDYX_149.getDisplayName())) {
				return FDYX_149;
			} //富滇银行

			if (displayName.equals(FXYX_151.getDisplayName())) {
				return FXYX_151;
			} //阜新银行

			if (displayName.equals(JXYX_153.getDisplayName())) {
				return JXYX_153;
			} //嘉兴银行

			if (displayName.equals(LFYX_155.getDisplayName())) {
				return LFYX_155;
			} //廊坊银行

			if (displayName.equals(ZJTLSYYX_157.getDisplayName())) {
				return ZJTLSYYX_157;
			} //浙江泰隆商业银行

			if (displayName.equals(NMGYX_159.getDisplayName())) {
				return NMGYX_159;
			} //内蒙古银行

			if (displayName.equals(HZYX_161.getDisplayName())) {
				return HZYX_161;
			} //湖州银行

			if (displayName.equals(GXBBWYX_163.getDisplayName())) {
				return GXBBWYX_163;
			} //广西北部湾银行

			if (displayName.equals(BSYX_165.getDisplayName())) {
				return BSYX_165;
			} //包商银行

			if (displayName.equals(WHSSYYX_167.getDisplayName())) {
				return WHSSYYX_167;
			} //威海市商业银行

			if (displayName.equals(PZHSSYYX_169.getDisplayName())) {
				return PZHSSYYX_169;
			} //攀枝花市商业银行

			if (displayName.equals(MYSSYYX_171.getDisplayName())) {
				return MYSSYYX_171;
			} //绵阳市商业银行

			if (displayName.equals(LZSSYYX_173.getDisplayName())) {
				return LZSSYYX_173;
			} //泸州市商业银行

			if (displayName.equals(DTSSYYX_175.getDisplayName())) {
				return DTSSYYX_175;
			} //大同市商业银行

			if (displayName.equals(SMXYX_177.getDisplayName())) {
				return SMXYX_177;
			} //三门峡银行

			if (displayName.equals(GDNYYX_179.getDisplayName())) {
				return GDNYYX_179;
			} //广东南粤银行

			if (displayName.equals(ZJKSSYYX_181.getDisplayName())) {
				return ZJKSSYYX_181;
			} //张家口市商业银行

			if (displayName.equals(GLYX_183.getDisplayName())) {
				return GLYX_183;
			} //桂林银行

			if (displayName.equals(JSCJSYYX_185.getDisplayName())) {
				return JSCJSYYX_185;
			} //江苏长江商业银行

			if (displayName.equals(LZYX_187.getDisplayName())) {
				return LZYX_187;
			} //柳州银行

			if (displayName.equals(NCSSYYX_189.getDisplayName())) {
				return NCSSYYX_189;
			} //南充市商业银行

			if (displayName.equals(LSYX_191.getDisplayName())) {
				return LSYX_191;
			} //莱商银行

			if (displayName.equals(DYYX_193.getDisplayName())) {
				return DYYX_193;
			} //德阳银行

			if (displayName.equals(LPSSSYYX_195.getDisplayName())) {
				return LPSSSYYX_195;
			} //六盘水市商业银行

			if (displayName.equals(QJSSYYX_197.getDisplayName())) {
				return QJSSYYX_197;
			} //曲靖市商业银行

			if (displayName.equals(KLYX_199.getDisplayName())) {
				return KLYX_199;
			} //昆仑银行

			if (displayName.equals(SHNCSYYX_201.getDisplayName())) {
				return SHNCSYYX_201;
			} //上海农村商业银行

			if (displayName.equals(KSNCSYYX_203.getDisplayName())) {
				return KSNCSYYX_203;
			} //昆山农村商业银行

			if (displayName.equals(JSCSNCSYYX_205.getDisplayName())) {
				return JSCSNCSYYX_205;
			} //江苏常熟农村商业银行

			if (displayName.equals(SZNCSYYX_207.getDisplayName())) {
				return SZNCSYYX_207;
			} //深圳农村商业银行

			if (displayName.equals(GZNCSYYX_209.getDisplayName())) {
				return GZNCSYYX_209;
			} //广州农村商业银行

			if (displayName.equals(ZJXSNCHZYX_211.getDisplayName())) {
				return ZJXSNCHZYX_211;
			} //浙江萧山农村合作银行

			if (displayName.equals(GDNHNCSYYX_213.getDisplayName())) {
				return GDNHNCSYYX_213;
			} //广东南海农村商业银行

			if (displayName.equals(FSSDNCSYYX_215.getDisplayName())) {
				return FSSDNCSYYX_215;
			} //佛山顺德农村商业银行

			if (displayName.equals(KMSNCXYHZSLHS_217.getDisplayName())) {
				return KMSNCXYHZSLHS_217;
			} //昆明市农村信用合作社联合社

			if (displayName.equals(HBSNCXYSLHS_219.getDisplayName())) {
				return HBSNCXYSLHS_219;
			} //湖北省农村信用社联合社

			if (displayName.equals(XZSSJNCXYHZSLHS_221.getDisplayName())) {
				return XZSSJNCXYHZSLHS_221;
			} //徐州市市郊农村信用合作社联合社

			if (displayName.equals(JSJYNCSYYX_223.getDisplayName())) {
				return JSJYNCSYYX_223;
			} //江苏江阴农村商业银行

			if (displayName.equals(ZQNCSYYX_225.getDisplayName())) {
				return ZQNCSYYX_225;
			} //重庆农村商业银行

			if (displayName.equals(SDSNCXYSLHS_227.getDisplayName())) {
				return SDSNCXYSLHS_227;
			} //山东省农村信用社联合社

			if (displayName.equals(DGNCSYYX_229.getDisplayName())) {
				return DGNCSYYX_229;
			} //东莞农村商业银行

			if (displayName.equals(ZJGNCSYYX_231.getDisplayName())) {
				return ZJGNCSYYX_231;
			} //张家港农村商业银行

			if (displayName.equals(FJSNCXYSLHS_233.getDisplayName())) {
				return FJSNCXYSLHS_233;
			} //福建省农村信用社联合社

			if (displayName.equals(BJNCSYYX_235.getDisplayName())) {
				return BJNCSYYX_235;
			} //北京农村商业银行

			if (displayName.equals(TJNCHZYX_237.getDisplayName())) {
				return TJNCHZYX_237;
			} //天津农村合作银行

			if (displayName.equals(NBJZNCHZYX_239.getDisplayName())) {
				return NBJZNCHZYX_239;
			} //宁波鄞州农村合作银行

			if (displayName.equals(FSSSSQNCXYHZSLHS_241.getDisplayName())) {
				return FSSSSQNCXYHZSLHS_241;
			} //佛山市三水区农村信用合作社联合社

			if (displayName.equals(CDSNCXYHZSLHS_243.getDisplayName())) {
				return CDSNCXYHZSLHS_243;
			} //成都市农村信用合作社联合社

			if (displayName.equals(CZSNCXYHZSLHS_245.getDisplayName())) {
				return CZSNCXYHZSLHS_245;
			} //沧州市农村信用合作社联合社

			if (displayName.equals(JSSNCXYHZSLHS_247.getDisplayName())) {
				return JSSNCXYHZSLHS_247;
			} //江苏省农村信用合作社联合社

			if (displayName.equals(JMSXHNCXYHZSLHS_249.getDisplayName())) {
				return JMSXHNCXYHZSLHS_249;
			} //江门市新会农村信用合作社联合社

			if (displayName.equals(GYSNCXYHZSLHS_251.getDisplayName())) {
				return GYSNCXYHZSLHS_251;
			} //高要市农村信用合作社联合社

			if (displayName.equals(FSNCSYYX_253.getDisplayName())) {
				return FSNCSYYX_253;
			} //佛山农村商业银行

			if (displayName.equals(WJNCSYYX_255.getDisplayName())) {
				return WJNCSYYX_255;
			} //吴江农村商业银行

			if (displayName.equals(ZJSNCXYSLHS_257.getDisplayName())) {
				return ZJSNCXYSLHS_257;
			} //浙江省农村信用社联合社

			if (displayName.equals(JSDWNCSYYX_259.getDisplayName())) {
				return JSDWNCSYYX_259;
			} //江苏东吴农村商业银行

			if (displayName.equals(ZHNSYX_261.getDisplayName())) {
				return ZHNSYX_261;
			} //珠海农商银行

			if (displayName.equals(ZSNCXYHZSLHS_263.getDisplayName())) {
				return ZSNCXYHZSLHS_263;
			} //中山农村信用合作社联合社

			if (displayName.equals(TCNCSYYX_265.getDisplayName())) {
				return TCNCSYYX_265;
			} //太仓农村商业银行

			if (displayName.equals(LFSYDSNCXYHZSLHS_267.getDisplayName())) {
				return LFSYDSNCXYHZSLHS_267;
			} //临汾市尧都市农村信用合作社联合社

			if (displayName.equals(GZSNCXYSLHS_269.getDisplayName())) {
				return GZSNCXYSLHS_269;
			} //贵州省农村信用社联合社

			if (displayName.equals(WXNCSYYX_271.getDisplayName())) {
				return WXNCSYYX_271;
			} //无锡农村商业银行

			if (displayName.equals(HNSNCXYSLHS_273.getDisplayName())) {
				return HNSNCXYSLHS_273;
			} //湖南省农村信用社联合社

			if (displayName.equals(JXSNCXYSLHS_275.getDisplayName())) {
				return JXSNCXYSLHS_275;
			} //江西省农村信用社联合社

			if (displayName.equals(SXSNCXYSLHS_277.getDisplayName())) {
				return SXSNCXYSLHS_277;
			} //陕西省农村信用社联合社

			if (displayName.equals(JSYX_279.getDisplayName())) {
				return JSYX_279;
			} //江苏银行

			if (displayName.equals(HDSSYYX_281.getDisplayName())) {
				return HDSSYYX_281;
			} //邯郸市商业银行

			if (displayName.equals(XTYX_283.getDisplayName())) {
				return XTYX_283;
			} //邢台银行

			if (displayName.equals(CDYX_285.getDisplayName())) {
				return CDYX_285;
			} //承德银行

			if (displayName.equals(CZYX_287.getDisplayName())) {
				return CZYX_287;
			} //沧州银行

			if (displayName.equals(JCSSYYX_289.getDisplayName())) {
				return JCSSYYX_289;
			} //晋城市商业银行

			if (displayName.equals(EEDSYX_291.getDisplayName())) {
				return EEDSYX_291;
			} //鄂尔多斯银行

			if (displayName.equals(SRYX_293.getDisplayName())) {
				return SRYX_293;
			} //上饶银行

			if (displayName.equals(DYSSYYX_295.getDisplayName())) {
				return DYSSYYX_295;
			} //东营市商业银行

			if (displayName.equals(JNYX_297.getDisplayName())) {
				return JNYX_297;
			} //济宁银行

			if (displayName.equals(TASSYYX_299.getDisplayName())) {
				return TASSYYX_299;
			} //泰安市商业银行

			if (displayName.equals(DZYX_301.getDisplayName())) {
				return DZYX_301;
			} //德州银行

			if (displayName.equals(KFSSYYX_303.getDisplayName())) {
				return KFSSYYX_303;
			} //开封市商业银行

			if (displayName.equals(LHSSYYX_305.getDisplayName())) {
				return LHSSYYX_305;
			} //漯河市商业银行

			if (displayName.equals(SQSSYYX_307.getDisplayName())) {
				return SQSSYYX_307;
			} //商丘市商业银行

			if (displayName.equals(NYSSYYX_309.getDisplayName())) {
				return NYSSYYX_309;
			} //南阳市商业银行

			if (displayName.equals(ZJMTSYYX_311.getDisplayName())) {
				return ZJMTSYYX_311;
			} //浙江民泰商业银行

			if (displayName.equals(LJYX_313.getDisplayName())) {
				return LJYX_313;
			} //龙江银行

			if (displayName.equals(ZJCZSYYX_315.getDisplayName())) {
				return ZJCZSYYX_315;
			} //浙江稠州商业银行

			if (displayName.equals(AHSNCXYLS_317.getDisplayName())) {
				return AHSNCXYLS_317;
			} //安徽省农村信用联社

			if (displayName.equals(GXZZZZQNCXYSLHS_319.getDisplayName())) {
				return GXZZZZQNCXYSLHS_319;
			} //广西壮族自治区农村信用社联合社

			if (displayName.equals(HNSNCXYSLHS_321.getDisplayName())) {
				return HNSNCXYSLHS_321;
			} //海南省农村信用社联合社

			if (displayName.equals(YNSNCXYSLHS_323.getDisplayName())) {
				return YNSNCXYSLHS_323;
			} //云南省农村信用社联合社

			if (displayName.equals(NXHHNCSYYX_325.getDisplayName())) {
				return NXHHNCSYYX_325;
			} //宁夏黄河农村商业银行

			if (displayName.equals(QTCSSYYX_327.getDisplayName())) {
				return QTCSSYYX_327;
			} //其他城市商业银行

			if (displayName.equals(QTNCSYYX_329.getDisplayName())) {
				return QTNCSYYX_329;
			} //其他农村商业银行

			if (displayName.equals(QTNCHZYX_331.getDisplayName())) {
				return QTNCHZYX_331;
			} //其他农村合作银行

			if (displayName.equals(QTCSXYS_333.getDisplayName())) {
				return QTCSXYS_333;
			} //其他城市信用社

			if (displayName.equals(QTNCXYS_335.getDisplayName())) {
				return QTNCXYS_335;
			} //其他农村信用社

			if (displayName.equals(HFYX_337.getDisplayName())) {
				return HFYX_337;
			} //汇丰银行

			if (displayName.equals(DYYX_339.getDisplayName())) {
				return DYYX_339;
			} //东亚银行

			if (displayName.equals(NYSYYX_341.getDisplayName())) {
				return NYSYYX_341;
			} //南洋商业银行

			if (displayName.equals(HSYXZGYXGS_343.getDisplayName())) {
				return HSYXZGYXGS_343;
			} //恒生银行(中国)有限公司

			if (displayName.equals(ZGYXXGYXGS_345.getDisplayName())) {
				return ZGYXXGYXGS_345;
			} //中国银行（香港）有限公司

			if (displayName.equals(JYYXYXGS_347.getDisplayName())) {
				return JYYXYXGS_347;
			} //集友银行有限公司

			if (displayName.equals(CXYXYXGS_349.getDisplayName())) {
				return CXYXYXGS_349;
			} //创兴银行有限公司

			if (displayName.equals(XZYXZGYXGS_351.getDisplayName())) {
				return XZYXZGYXGS_351;
			} //星展银行（中国）有限公司

			if (displayName.equals(YHYXZGYXGS_353.getDisplayName())) {
				return YHYXZGYXGS_353;
			} //永亨银行（中国）有限公司

			if (displayName.equals(YLYX_355.getDisplayName())) {
				return YLYX_355;
			} //永隆银行

			if (displayName.equals(HQYXZGYXGS_357.getDisplayName())) {
				return HQYXZGYXGS_357;
			} //花旗银行（中国）有限公司

			if (displayName.equals(MGYXYXGS_359.getDisplayName())) {
				return MGYXYXGS_359;
			} //美国银行有限公司

			if (displayName.equals(MGDTYXZGYXGS_361.getDisplayName())) {
				return MGDTYXZGYXGS_361;
			} //摩根大通银行(中国)有限公司

			if (displayName.equals(SLDJRLYXZGYXGS_363.getDisplayName())) {
				return SLDJRLYXZGYXGS_363;
			} //三菱东京日联银行(中国）有限公司

			if (displayName.equals(RBSJZYYXGFYXGS_365.getDisplayName())) {
				return RBSJZYYXGFYXGS_365;
			} //日本三井住友银行股份有限公司

			if (displayName.equals(RSSYYXZGYXGS_367.getDisplayName())) {
				return RSSYYXZGYXGS_367;
			} //瑞穗实业银行（中国）有限公司

			if (displayName.equals(RBSKYXGFYXGS_369.getDisplayName())) {
				return RBSKYXGFYXGS_369;
			} //日本山口银行股份有限公司

			if (displayName.equals(WHYXZGYXGS_371.getDisplayName())) {
				return WHYXZGYXGS_371;
			} //外换银行（中国）有限公司

			if (displayName.equals(YLYXZGYXGS_373.getDisplayName())) {
				return YLYXZGYXGS_373;
			} //友利银行(中国)有限公司

			if (displayName.equals(HGCYYX_375.getDisplayName())) {
				return HGCYYX_375;
			} //韩国产业银行

			if (displayName.equals(XHYXZGYXGS_377.getDisplayName())) {
				return XHYXZGYXGS_377;
			} //新韩银行(中国)有限公司

			if (displayName.equals(HGZXQYYX_379.getDisplayName())) {
				return HGZXQYYX_379;
			} //韩国中小企业银行

			if (displayName.equals(HYYXZGYXGS_381.getDisplayName())) {
				return HYYXZGYXGS_381;
			} //韩亚银行（中国）有限公司

			if (displayName.equals(HQYXZGYXGS_383.getDisplayName())) {
				return HQYXZGYXGS_383;
			} //华侨银行（中国）有限公司

			if (displayName.equals(DHYXZGYXGS_385.getDisplayName())) {
				return DHYXZGYXGS_385;
			} //大华银行（中国）有限公司

			if (displayName.equals(TGPGYXDZYXGS_387.getDisplayName())) {
				return TGPGYXDZYXGS_387;
			} //泰国盘谷银行(大众有限公司)

			if (displayName.equals(ADLZYHZYXGFYXGS_389.getDisplayName())) {
				return ADLZYHZYXGFYXGS_389;
			} //奥地利中央合作银行股份有限公司

			if (displayName.equals(BLSLHYXGFYXGS_391.getDisplayName())) {
				return BLSLHYXGFYXGS_391;
			} //比利时联合银行股份有限公司

			if (displayName.equals(BLSFTYXYXGS_393.getDisplayName())) {
				return BLSFTYXYXGS_393;
			} //比利时富通银行有限公司

			if (displayName.equals(HLYX_395.getDisplayName())) {
				return HLYX_395;
			} //荷兰银行

			if (displayName.equals(HLAZYXGFYXGS_397.getDisplayName())) {
				return HLAZYXGFYXGS_397;
			} //荷兰安智银行股份有限公司

			if (displayName.equals(ZDYX_399.getDisplayName())) {
				return ZDYX_399;
			} //渣打银行

			if (displayName.equals(YGSGLHJYXGZYXGS_401.getDisplayName())) {
				return YGSGLHJYXGZYXGS_401;
			} //英国苏格兰皇家银行公众有限公司

			if (displayName.equals(FGXYYXZGYXGS_403.getDisplayName())) {
				return FGXYYXZGYXGS_403;
			} //法国兴业银行（中国)有限公司

			if (displayName.equals(FGDFHLYXGFYXGS_405.getDisplayName())) {
				return FGDFHLYXGFYXGS_405;
			} //法国东方汇理银行股份有限公司

			if (displayName.equals(FGWMYXGFYXGS_407.getDisplayName())) {
				return FGWMYXGFYXGS_407;
			} //法国外贸银行股份有限公司

			if (displayName.equals(DGDLSDYXGFGS_409.getDisplayName())) {
				return DGDLSDYXGFGS_409;
			} //德国德累斯登银行股份公司

			if (displayName.equals(DYZYXZGYXGS_411.getDisplayName())) {
				return DYZYXZGYXGS_411;
			} //德意志银行（中国）有限公司

			if (displayName.equals(DGSYYXGFYXGS_413.getDisplayName())) {
				return DGSYYXGFYXGS_413;
			} //德国商业银行股份有限公司

			if (displayName.equals(DGXDYXGFYXGS_415.getDisplayName())) {
				return DGXDYXGFYXGS_415;
			} //德国西德银行股份有限公司

			if (displayName.equals(DGBFLYZYX_417.getDisplayName())) {
				return DGBFLYZYX_417;
			} //德国巴伐利亚州银行

			if (displayName.equals(DGBDYZZYX_419.getDisplayName())) {
				return DGBDYZZYX_419;
			} //德国北德意志州银行

			if (displayName.equals(YDLLHSBLYXGFYXGS_421.getDisplayName())) {
				return YDLLHSBLYXGFYXGS_421;
			} //意大利联合圣保罗银行股份有限公司

			if (displayName.equals(RSXDYXGFYXGS_423.getDisplayName())) {
				return RSXDYXGFYXGS_423;
			} //瑞士信贷银行股份有限公司

			if (displayName.equals(RSYX_425.getDisplayName())) {
				return RSYX_425;
			} //瑞士银行

			if (displayName.equals(JNDFYYXYXGS_427.getDisplayName())) {
				return JNDFYYXYXGS_427;
			} //加拿大丰业银行有限公司

			if (displayName.equals(JNDMTLEYXYXGS_429.getDisplayName())) {
				return JNDMTLEYXYXGS_429;
			} //加拿大蒙特利尔银行有限公司

			if (displayName.equals(ADLYHXXLYXJTYXGS_431.getDisplayName())) {
				return ADLYHXXLYXJTYXGS_431;
			} //澳大利亚和新西兰银行集团有限公司

			if (displayName.equals(MGSDLGJYXZGYXGS_433.getDisplayName())) {
				return MGSDLGJYXZGYXGS_433;
			} //摩根士丹利国际银行（中国）有限公司

			if (displayName.equals(LHYXZGYXGS_435.getDisplayName())) {
				return LHYXZGYXGS_435;
			} //联合银行(中国)有限公司

			if (displayName.equals(HLHZYXYXGS_437.getDisplayName())) {
				return HLHZYXYXGS_437;
			} //荷兰合作银行有限公司

			if (displayName.equals(XMGJYX_439.getDisplayName())) {
				return XMGJYX_439;
			} //厦门国际银行

			if (displayName.equals(FGBLYXZGYXGS_441.getDisplayName())) {
				return FGBLYXZGYXGS_441;
			} //法国巴黎银行（中国）有限公司

			if (displayName.equals(HSYX_443.getDisplayName())) {
				return HSYX_443;
			} //华商银行

			if (displayName.equals(HYYX_445.getDisplayName())) {
				return HYYX_445;
			} //华一银行

			else {
				return OTHER;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	/**
	 * 是否是基本户
	 * 
	 * @author 
	 * 
	 */
	public enum Account {
		IS_BASIC_ACCOUNT("0", "贷款户"), NOT_BASIC_ACCOUNT("1", "基本户"), NOT_KNOW_ACCOUNT("3", "未知类型");

		String key;

		String displayName;

		private Account(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static Account getAccount(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(IS_BASIC_ACCOUNT.getKey())) {
				return IS_BASIC_ACCOUNT;
			} else if (key.equals(NOT_BASIC_ACCOUNT.getKey())) {
				return NOT_BASIC_ACCOUNT;
			} else {
				return NOT_KNOW_ACCOUNT;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	/**
	 * 经办动作0=添加；1=启用；2=停用 recAction
	 * 
	 * @author 
	 * 
	 */
	public enum RecAction {

		ADD_ACTION("0", "添加"), ENABLE_ACTION("1", "启用"), DISABLE_ACTION("2", "停用"), NOT_KNOW_ACCOUNT("3", "未知动作");
		String key;

		String displayName;

		private RecAction(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static RecAction getRecAction(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(ADD_ACTION.getKey())) {
				return ADD_ACTION;
			} else if (key.equals(ENABLE_ACTION.getKey())) {
				return ENABLE_ACTION;
			} else if (key.equals(DISABLE_ACTION.getKey())) {
				return DISABLE_ACTION;
			} else {
				return NOT_KNOW_ACCOUNT;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	/**
	 * 账户状态
	 * 
	 * @author 
	 * 
	 */
	public enum AccFlag {

		UN_ENABLED("0", "停用"), IS_ENABLED("1", "启用"), NOT_KNOW_STATE("2", "未知状态");
		String key;

		String displayName;

		private AccFlag(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static AccFlag getAccFlag(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(UN_ENABLED.getKey())) {
				return UN_ENABLED;
			} else if (key.equals(IS_ENABLED.getKey())) {
				return IS_ENABLED;
			} else {
				return NOT_KNOW_STATE;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	/**
	 * 审核状态
	 * 
	 * @author 
	 * 
	 */
	public enum AuditStatus {

		INITIAL_STATE("0", "初始"), REVIEWING("1", "审批中"), APPROVED("2", "审批通过"), REJECTED("3", "审批不通过"), NOT_KNOW_STATE("4", "未知状态");
		String key;

		String displayName;

		private AuditStatus(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static AuditStatus getAuditStatus(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(INITIAL_STATE.getKey())) {
				return INITIAL_STATE;
			} else if (key.equals(REVIEWING.getKey())) {
				return REVIEWING;
			} else if (key.equals(APPROVED.getKey())) {
				return APPROVED;
			} else if (key.equals(REJECTED.getKey())) {
				return REJECTED;
			} else {
				return NOT_KNOW_STATE;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	/**
	 * 审核状态
	 * 
	 * @author 
	 * 
	 */
	public enum ApplyCheckType {

		PASS("0", "通过"), NOT_PASS("1", "不通过"), NOT_KNOW_STATE("2", "未知状态");
		String key;

		String displayName;

		private ApplyCheckType(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static ApplyCheckType getApplyCheckType(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(NOT_PASS.getKey())) {
				return NOT_PASS;
			} else if (key.equals(PASS.getKey())) {
				return PASS;
			} else {
				return NOT_KNOW_STATE;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	/**
	 * 协议类别
	 * 
	 * @author xuchao
	 * 
	 */
	public enum TempFlowNo {
		F_XY_KJ("F_XY_KJ", "框架协议"), F_XY_DK("F_XY_DK", " 贷款协议"), F_XY_ZL("F_XY_ZL", "资金划拨协议"), NOT_KNOWN("4", "未知");

		String key;

		String displayName;

		private TempFlowNo(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static TempFlowNo getTempFlowNo(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.startsWith(F_XY_KJ.getKey())) {
				return F_XY_KJ;
			} else if (key.startsWith(F_XY_DK.getKey())) {
				return F_XY_DK;
			} else if (key.startsWith(F_XY_ZL.getKey())) {
				return F_XY_ZL;
			} else {
				return NOT_KNOWN;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}

	/**
	 * 协议处理状态：0=初始；1=签署中；2=签署完成；
	 * 
	 */
	public enum BizStatus {
		INIT("0", "初始"), SIGN_NOW("1", " 签署中"), SING_OVER("2", "签署完成"), NOT_KNOWN("3", "未知");

		String key;

		String displayName;

		private BizStatus(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static BizStatus getBizStatus(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(INIT.getKey())) {
				return INIT;
			} else if (key.equals(SIGN_NOW.getKey())) {
				return SIGN_NOW;
			} else if (key.equals(SING_OVER.getKey())) {
				return SING_OVER;
			} else {
				return NOT_KNOWN;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

	}

	/**
	* 未通过原因：0 =申请信息不符；1 =贷款资质不符；99 =其他 ；
	*/
	public enum NoPassResean {
		APP_INFO_NOT_MATCH("0 ", "申请信息不符"), LOAN_QUALITY_DISCREPANCY("1 ", "贷款资质不符"), OTHER("99", "其他"), NOT_KNOW("", "");

		String key;

		String displayName;

		private NoPassResean(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static NoPassResean getNoPassResean(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(APP_INFO_NOT_MATCH.getKey())) {
				return APP_INFO_NOT_MATCH;
			} else if (key.equals(LOAN_QUALITY_DISCREPANCY.getKey())) {
				return LOAN_QUALITY_DISCREPANCY;
			} else if (key.equals(OTHER.getKey())) {
				return OTHER;
			} else {
				return NOT_KNOW;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

	}

	/**
	 * 当前环节处理状态：0 =初始；1 =处理中；2=完成；3=退回；
	 * 
	 */
	public enum StepStatus {
		INIT("0", "初始"), HANDEL_NOW("1", "处理中"), HANDEL_OVER("2", "完成"), HANDEL_BACK("3", "退回"), NOT_KNOWN("4", "未知");

		String key;

		String displayName;

		private StepStatus(String key, String displayName) {
			this.key = key;
			this.displayName = displayName;
		}

		public static StepStatus getStepStatus(String key) {
			key = StrUtils.changeNullToStr(key);
			if (key.equals(INIT.getKey())) {
				return INIT;
			} else if (key.equals(HANDEL_NOW.getKey())) {
				return HANDEL_NOW;
			} else if (key.equals(HANDEL_OVER.getKey())) {
				return HANDEL_OVER;
			} else if (key.equals(HANDEL_BACK.getKey())) {
				return HANDEL_BACK;
			} else {
				return NOT_KNOWN;
			}
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
	}
}
