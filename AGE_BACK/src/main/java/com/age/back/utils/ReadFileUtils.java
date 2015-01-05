package com.age.back.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.age.core.utils.spring.SpringPropertyConfigurer;

public class ReadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(ReadFileUtils.class);

	/**
	 * 读取文件
	 * @param filePath
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static String readFileFromServer(String filePath, HttpServletRequest request, HttpServletResponse response) {
		InputStream fis = null;
		try {
			logger.info("查看文件路径：" + filePath);
			//获取图片根目录
			File file = new File(getFloder() + File.separator + filePath);
			String filename = file.getName();
			// 以流的形式下载文件。
			fis = new BufferedInputStream(new FileInputStream(file));
			//byte[] buffer = new byte[fis.available()];
			byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB  
			int len = 0;

			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			//获取输出流
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			//response.setContentType("application/jpeg");
			//在页面上输出图片
			while ((len = fis.read(bytes)) != -1) {
				toClient.write(bytes, 0, len);
			}

			toClient.flush();
			toClient.close();
			logger.info("查看文件成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("查看文件失败!");
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;
	}

	public static String getFloder() {
		String path = null;
		try {
			path = SpringPropertyConfigurer.getProperty("file.upload.path");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
}
