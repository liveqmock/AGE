
/**
 * 返回连接url参数 1到10的随机整数
 * 消除浏览器缓存问题
 * @returns
 */
function GetUrlConNum(){   
		return "&num="+GetRandomNum(1,10);
}

/**
 * 返回1到10的随机整数
 * @returns
 */
function GetOneToTenNum(){   
		return GetRandomNum(1,10);
}

function GetRandomNum(Min,Max){   
	var Range = Max - Min;   
	var Rand = Math.random();   
	return (Min + Math.round(Rand * Range));   
} 