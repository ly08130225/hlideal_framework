package com.hlideal.common.functions.hlideal.utils.result;

public class FileWriteResult extends ResultBase {
	private static final long serialVersionUID = -5341241688823722526L;
	private String fileName;
	private String filePath;

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}