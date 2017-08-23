package com.jasonwang.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jasonwang.entity.FileMetaInfo;
import com.jasonwang.repository.FileMetaInfoRepository;

/**
 * @author Jason-Wang
 * @date Aug 19, 2017
 *
 */

@Service
public class FileServiceImpl implements FileService {
	
	private static final String UPLOAD_ROOT_PATH = System.getProperty("user.dir") + "\\upload\\";
	private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

	@Autowired
	FileMetaInfoRepository fileMetaInfoRepository;
	
	@Override
	@Transactional
	public FileMetaInfo saveFile(MultipartFile file, String userId) {
		return saveToDisk(file, userId);
	}

	@Transactional
	private FileMetaInfo saveToDisk(MultipartFile file, String userId) {
		if (!new File(UPLOAD_ROOT_PATH).exists()) {
    		new File(UPLOAD_ROOT_PATH).mkdir();
    	}

		// check if sub-file directory exists
    	String filePath = UPLOAD_ROOT_PATH + userId;
    	System.out.println(filePath);
    	if (!new File(filePath).exists()) {
    		new File(filePath).mkdir();
    	}    	
    	
		try {
            file.transferTo(new File(filePath));
		} catch (IOException e) {
//			e.printStackTrace();
			e.getMessage();
		}
		
		LOGGER.info("File saved to " + filePath);
		
		return saveFileMetaInfo(file, filePath, userId);
	}

	@Transactional
	private FileMetaInfo saveFileMetaInfo(MultipartFile file, String filePath, String userId) {
		if (file == null) {
			return null;
		}
		
		FileMetaInfo fileMetaInfo = new FileMetaInfo();
		fileMetaInfo.setFileName(file.getOriginalFilename());
		fileMetaInfo.setFileSize(file.getSize());
		fileMetaInfo.setUserId(userId);
		fileMetaInfo.setFileLocation(filePath);
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm"); 
		fileMetaInfo.setUploadDate(sdf.format(new Date(System.currentTimeMillis())));
		
		fileMetaInfoRepository.save(fileMetaInfo);
		
		LOGGER.info("File meta-info saved to in-memory database successfully");

		return fileMetaInfo;
	}

	@Override
	@Transactional
	public List<FileMetaInfo> findAll() {
		// TODO Auto-generated method stub
		return fileMetaInfoRepository.findAll();
	}

	@Override
	@Transactional
	public List<FileMetaInfo> findByUserId(String userId) {
		return fileMetaInfoRepository.findFileMetaInfoByUserId(userId);
	}
}