package com.age.etp;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		File file = new File("F:\\\\GitHub\\\\AGE\\\\AGE_BACK");
		delDotsvn(file);
	}

	public static void delDotsvn(File f) {
		File[] files = f.listFiles();
		if (files != null)
			for (File file : files)
				if (".svn".equals(file.getName()))
					del(file);
				else
					delDotsvn(file);
	}

	/**
	 * 删除文件或目录
	 */
	public static void del(File file) {
		File[] files = file.listFiles();
		if (files != null)
			for (File f : files)
				del(f);
		file.delete();
	}

}
