package com.jasonwang.exception;

/**
 * @author Jason-Wang
 * @date Sep 9, 2017
 *
 */

public class EmptyFileUploadException extends RuntimeException {

	private static final long serialVersionUID = -5899905501828154825L;

	public EmptyFileUploadException(String e) {
		super(e);
	}
}
