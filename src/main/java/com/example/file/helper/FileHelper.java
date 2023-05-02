package com.example.file.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHelper {
	final String UPLOAD_DIR = "C:\\Users\\91777\\Documents\\workspace-spring-tool-suite-4-4.17.1.RELEASE\\FileSendingApi\\src\\main\\resources\\static\\image";

	public boolean uploadFile(MultipartFile multipartFile) {
		boolean f = false;
		try {
			InputStream is = multipartFile.getInputStream();
			byte data[] = new byte[is.available()];
			is.read(data);

			FileOutputStream fos = new FileOutputStream(
					UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename());
			fos.write(data);
			fos.flush();
			fos.close();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
