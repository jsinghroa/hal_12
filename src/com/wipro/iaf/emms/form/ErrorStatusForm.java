package com.wipro.iaf.emms.form;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ErrorStatusForm {

	private String concatId;
	private String userId;
	private String zipName;
	private String xmlName;
	private String errors;
	private String timestamp;

	public String getConcatId() {
		return concatId;
	}

	public void setConcatId(String concatId) {
		this.concatId = concatId;
	}

	public String getZipName() {
		return zipName;
	}

	public void setZipName(String zipName) {
		this.zipName = zipName;
	}

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String string) {
		this.timestamp = string;
	}

	@Override
	public String toString() {
		return "ErrorStatusForm [concatId=" + concatId + ", userId=" + userId
				+ ", zipName=" + zipName + ", xmlName=" + xmlName + ", errors="
				+ errors + ", timestamp=" + timestamp + "]";
	}

}
