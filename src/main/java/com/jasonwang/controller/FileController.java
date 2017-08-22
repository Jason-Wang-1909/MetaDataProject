package com.jasonwang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jasonwang.entity.FileMetaInfo;
import com.jasonwang.service.FileService;

/**
 * @author Jason-Wang
 * @date Aug 19, 2017
 *
 */
@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@PostMapping(value="/upload", produces = "application/json")
	public FileMetaInfo uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("Id") String userId) {
		return fileService.saveFile(file, userId);
	}
	
	@GetMapping(value="/findAll", produces = "application/json")
	public List<FileMetaInfo> findAll() {
		return fileService.findAll();
	}
	
	@GetMapping(value="/findByUserId/{Id}", produces = "application/json")
	public List<FileMetaInfo> findByUserId(@PathVariable("Id") String Id) {
		return fileService.findByUserId(Id);
	}
}
