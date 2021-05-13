package com.wipro.iaf.emms.form;

import java.io.File;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class TableStatusForm {
	
	private String recordId;
	private String tableOrder;
	private MultipartFile zip1;
	
	
	
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	

	public MultipartFile getZip1() {
		return zip1;
	}

	public void setZip1(MultipartFile zip1) {
		this.zip1 = zip1;
	}

	public String getTableOrder() {
		return tableOrder;
	}

	public void setTableOrder(String tableOrder) {
		this.tableOrder = tableOrder;
	}
}
