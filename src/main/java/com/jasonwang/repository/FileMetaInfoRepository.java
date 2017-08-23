package com.jasonwang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jasonwang.entity.FileMetaInfo;

/**
 * @author Jason-Wang
 * @date Aug 19, 2017
 *
 */
public interface FileMetaInfoRepository extends JpaRepository<FileMetaInfo, Integer> {

	List<FileMetaInfo> findFileMetaInfoByUserId(String userId);
}