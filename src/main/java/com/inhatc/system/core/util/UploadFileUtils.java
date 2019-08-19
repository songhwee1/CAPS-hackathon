package com.inhatc.system.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
   
	 
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	private static Integer WIDTH_SIZE = 100;

	public static Integer getWIDTH_SIZE() {
		return WIDTH_SIZE;
	}

	public static void setWIDTH_SIZE(Integer wIDTH_SIZE) {
		WIDTH_SIZE = wIDTH_SIZE;
	}

	// 1.파일의 저장 경로(uploadPath), 2.원본 파일의 이름(originalName), 3.파일 데이터(byte[])
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

		// ★ 1. 고유값 생성
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		System.out.println(uploadPath);
		// ★ 2. 년/월/일' 정보 생성
	

		// ★ 3. 원본파일 저장
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);

		// ★ 4. 이미지 일경우 썸네일 이미지 생성 후 url 주소로 반환
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;

		if (MediaUtils.getMediaType(formatName) != null) {
			// 이미지일 경우 썸네일 생성 후 이미지 이름 반환 ( 경로+년월일+s_이름)
			uploadedFileName = makeThumbnail(uploadPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedName);
		}

		// 파일 경로를 -> url 경로로 변경해서 반환
		return uploadedFileName;
	}

	 // ckeditor 전용 업로드 파일 반환 된값이 썸네일이 아닌 원본 이미지
	 // 1.파일의 저장 경로(uploadPath), 2.원본 파일의 이름(originalName), 3.파일 데이터(byte[])
	 public static String ckuploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

		// ★ 1. 고유값 생성
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;

		// ★ 2. 년/월/일' 정보 생성
		

		// ★ 3. 원본파일 저장
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);

		// ★ 4. 이미지 생성 후 url 주소로 반환
		String uploadedFileName = makeIcon(uploadPath, savedName);

		// 파일 경로를 -> url 경로로 변경해서 반환
		return uploadedFileName;
	}

	 
	 
	// 이미지가 아닐경우 단지 파일 경로를 -> url 경로로 변경해서 반환
	private static String makeIcon(String uploadPath, String savedName) {
		String iconName = uploadPath+ File.separator + savedName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	

	// 썸네일 이미지 생성하기
	// 1.파일 경로 2. 년월일 경로, 3. 파일 이름
	private static String makeThumbnail(String uploadPath, String fileName) throws Exception {
		// 파일 존재하는 이미지를 메모리상 이미지에 올려 붙인다. 즉 메모리에 로딩시킨다.
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath, fileName));

		// 메모리상 이미지를 정해진 크기에 맞게 복사한다.
		BufferedImage destImage = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, WIDTH_SIZE);

		// 썸네일 이미지 이름을 정한다. 썸네일 이미지를 앞에 s_ 붙인다.
		String thumbnailName = uploadPath + File.separator + "s_" + fileName;

		// 파일 경로의 객체를 생성한다.
		File newFile = new File(thumbnailName);

		// 경로의 마지막 에 . 으로 중심으로 분리해서 .jpg, png, jpeg gif 의 문자를 추출한다.
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		// 실질적인 썸네일 이미지를 복사한다.
		ImageIO.write(destImage, formatName.toUpperCase(), newFile);

		// 파일 경로인 역슬러시를 url 의 경로인 슬러시로 변경해서 해서 반환시킨다.
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}