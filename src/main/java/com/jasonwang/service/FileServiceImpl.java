package com.jasonwang.service;

import java.io.File;
import java.io.IOException;
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
	
	private static final String UPLOAD_PATH = "d://upload/";
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
		if (!new File(UPLOAD_PATH).exists()) {
    		new File(UPLOAD_PATH).mkdir();
    	}
		// check the root folder's permission
		File rootFolder = new File(UPLOAD_PATH);
		if (!rootFolder.canWrite() || !rootFolder.canExecute() || !rootFolder.canRead()) {
			rootFolder.setWritable(true, false);
			rootFolder.setExecutable(true, false);
			rootFolder.setReadable(true, false);
		}
    	
		// check if sub-file directory exists
    	String filePath = UPLOAD_PATH + userId;
    	if (!new File(filePath).exists()) {
    		new File(filePath).mkdir();
    	}
    	// check the sub-folder's permission
    	File subFolder = new File(filePath);
    	if (!subFolder.canWrite() || !subFolder.canExecute() || !subFolder.canRead()) {
    		subFolder.setWritable(true, false);
    		subFolder.setExecutable(true, false);
    		subFolder.setReadable(true, false);
    	}
    	
		try {
            file.transferTo(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();		
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
		fileMetaInfo.setUploadDate(System.currentTimeMillis());
		fileMetaInfo.setFileLocation(filePath);
		
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