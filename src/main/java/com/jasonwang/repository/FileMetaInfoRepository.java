package com.jasonwang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jasonwang.entity.FileMetaInfo;

/**
 * @author Jason-Wang
 * @date Aug 19, 2017
 *
 */
public interface FileMetaInfoRepository extends JpaRepository<FileMetaInfo, Integer> {

	/**
	 * @param userId
	 * @return
	 */
	List<FileMetaInfo> findFileMetaInfoByUserId(String userId);

//	@Query("SELECT f FROM fileMetaInfo f WHERE f.userId = ?userId")
//	List<FileMetaInfo> findByUserId(String userId);

}