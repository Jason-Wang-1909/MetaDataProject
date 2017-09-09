package com.jasonwang.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jasonwang.entity.FileMetaInfo;
import com.jasonwang.exception.EmptyFileUploadException;
import com.jasonwang.repository.FileMetaInfoRepository;
import com.jasonwang.service.FileServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UnitTestDao {

	@Mock
	private FileMetaInfoRepository fileMetaInfoRepository;
	
	@InjectMocks
	private FileServiceImpl fileServiceImpl;
	
	@Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
    }
	
	@Test(expected=EmptyFileUploadException.class)
	public void testFileServiceUploadEmptyFile() throws IOException {
		MockMultipartFile mf = new MockMultipartFile("newName", new byte[0]);
		fileServiceImpl.saveFile(mf, "");
	}
	
	@Test
	public void testFindAllFileMetaInfo() throws Exception{		
		List<FileMetaInfo> resList = new ArrayList<FileMetaInfo>();
		resList.add(new FileMetaInfo());		
		when(fileMetaInfoRepository.findAll()).thenReturn(resList);
		assertEquals(1, fileServiceImpl.findAll().size());				
	}
	
	@Test
	public void testFindFileMetaInfoByID() throws Exception{		
		List<FileMetaInfo> resList = new ArrayList<FileMetaInfo>();
		FileMetaInfo fmi = new FileMetaInfo();
		fmi.setUserId("userId");
		resList.add(fmi);		
		fileMetaInfoRepository.save(resList);
		when(fileMetaInfoRepository.findFileMetaInfoByUserId("userId")).thenReturn(resList);
		assertEquals(fmi.getUserId(), fileServiceImpl.findByUserId("userId").get(0).getUserId());				
	}
}