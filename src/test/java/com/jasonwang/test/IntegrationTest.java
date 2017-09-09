package com.jasonwang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jasonwang.entity.FileMetaInfo;
import com.jasonwang.repository.FileMetaInfoRepository;
import com.jasonwang.service.FileService;

/**
 * @author Jason-Wang
 * @date Sep 9, 2017
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IntegrationTest {
	@Autowired
	private FileMetaInfoRepository fileMetaInfoRepository;
	
	@Autowired
	private FileService fileService;
	
	@Test
	public void testFindAllFileMetaInfoNotNull() throws Exception {
		fileMetaInfoRepository.save(new FileMetaInfo());
		assertNotNull(fileService.findAll().size());				
	}
	
	@Test
	public void testFindAllFileMetaInfo() throws Exception {
		fileMetaInfoRepository.save(new FileMetaInfo());
		assertEquals(1, fileService.findAll().size());				
	}
}