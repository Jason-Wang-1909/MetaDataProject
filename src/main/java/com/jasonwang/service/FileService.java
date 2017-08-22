package com.jasonwang.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import com.jasonwang.entity.FileMetaInfo;

/**
 * @author Jason-Wang
 * @date Aug 19, 2017
 *
 */
public interface FileService {
	FileMetaInfo saveFile(MultipartFile file, String userId);
	
	List<FileMetaInfo> findAll();
	
	List<FileMetaInfo> findByUserId(String userId);
}
