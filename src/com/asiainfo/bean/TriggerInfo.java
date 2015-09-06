package com.asiainfo.bean;

import java.util.Date;

public class TriggerInfo {
	private String activeCode;
	private String triggerType;
	private String triggerMs;
	private String status;
	private String useId;
	private String searchType;
	private Date createTime;
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public String getTriggerType() {
		return triggerType;
	}
	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}
	public String getTriggerMs() {
		return triggerMs;
	}
	public void setTriggerMs(String triggerMs) {
		this.triggerMs = triggerMs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


}
