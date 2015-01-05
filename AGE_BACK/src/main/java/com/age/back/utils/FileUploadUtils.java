package com.age.back.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.age.back.common.Constants;
import com.age.core.utils.IdUtils;
import com.age.core.utils.spring.SpringPropertyConfigurer;

public class FileUploadUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

	public static String getUploadPath(HttpServletRequest request) {
		return getApplicationPath(request) + Constants.UPLOAD_FOLDER;
	}

	public static String getApplicationPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

	/**
	 * @author 郑永标
	 * @Description: 保存文件
	 * @param multipartFile
	 * @param request
	 * @throws IOException
	 * @throws
	 */
	public static String saveUploadFileToDisk(MultipartFile multipartFile, HttpServletRequest request, String tempDir) throws Exception {
		InputStream is = null;
		OutputStream os = null;
		String filePath = null;
		String fileName = null;
		String suffix = null;
		String tempName = null;
		try {
			is = multipartFile.getInputStream();
			fileName = multipartFile.getOriginalFilename();
			suffix = fileName.substring(fileName.lastIndexOf("."));
			tempName = IdUtils.uuid32() + suffix;
			filePath = createFile(request, tempName, tempDir);
			os = new FileOutputStream(filePath);
			byte[] b = new byte[1024];
			while (is.read(b, 0, b.length) > -1) {
				os.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.flush();
				os.close();
			}
			if (is != null) {
				is.close();
			}
		}
		logger.info("上传文件路径：" + filePath);
		return tempDir + File.separator + tempName; //相对路径
	}

	/***
	 * 
	 * @author 郑永标
	 * @Description: 创建文件
	 * @param request
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws
	 */
	private static String createFile(HttpServletRequest request, String fileName, String tempDir) throws Exception {
		//String tempDir = UUID.randomUUID().toString();
		String path = getFilePath() + File.separator + tempDir;
		logger.info("临时文件夹：" + path);
		File tempFile = new File(path);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		String diskFileStr = path + File.separator + fileName;
		logger.info("上传文件路径：" + path);
		File diskFile = new File(diskFileStr);
		if (!diskFile.exists()) {
			diskFile.createNewFile();
		}
		return diskFileStr;
	}

	/**
	 * @author 郑永标
	 * @Description: 配置文件中读取保存上传文件的文件路径
	 * @return
	 * @throws
	 */
	public static String getFilePath() {
		String path = null;
		try {
			path = SpringPropertyConfigurer.getProperty("file.upload.path");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
}
