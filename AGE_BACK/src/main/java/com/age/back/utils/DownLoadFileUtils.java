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

public class DownLoadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(DownLoadFileUtils.class);

	/**
	 * 下载文件
	 * @param filePath
	 * @param request
	 * @param response
	 * @param tempDir
	 * @return
	 * @throws Exception
	 */
	public static String downLoadFileFromServer(String filePath, HttpServletRequest request, HttpServletResponse response) throws Exception {
		InputStream fis = null;
		try {
			logger.info("下载文件的路径：" + filePath);
			File file = new File(getFloder() + File.separator + filePath);
			String filename = file.getName();
			// 以流的形式下载文件。
			fis = new BufferedInputStream(new FileInputStream(file));
			byte bytes[] = new byte[1024];//设置缓冲区为1024个字节，即1KB  
			int len = 0;
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			while ((len = fis.read(bytes)) != -1) {
				toClient.write(bytes, 0, len);
			}
			toClient.flush();
			toClient.close();
			logger.info("文件下载成功!");
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("文件下载失败!");
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
