package com.jasonwang.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Jason-Wang
 * @date Aug 19, 2017
 *
 */

@Entity
@Table(name="fileMetaInfo")
public class FileMetaInfo implements Serializable {

	private static final long serialVersionUID = 9095284753688475570L;

	private long id;
	private String userId;
	private String fileName;
	private Long fileSize;
	private String uploadDate;
	private String fileLocation;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Column
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}	
	
	@Column
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	public String toString() {
		return "File info: "
				+ getUserId() + ", "
				+ getFileName() + ", "
				+ getUploadDate();
	}
}