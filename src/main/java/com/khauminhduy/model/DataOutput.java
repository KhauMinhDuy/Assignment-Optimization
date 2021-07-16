package com.khauminhduy.model;

public class DataOutput {

	private String date;
	private Integer shopId;
	private Integer shiftBig;
	private String workerName;
	private boolean isManager;
	private boolean isCashier;
	private Integer shiftSmall;
	private String jobName;
	private Integer totalMinute;
	private Integer hourStart;
	private Integer minuteStart;

	public DataOutput() {
		super();
	}

	public DataOutput(String date, Integer shopId, Integer shiftBig, String workerName, boolean isManager,
			boolean isCashier, Integer shiftSmall, String jobName, Integer totalMinute, Integer hourStart,
			Integer minuteStart) {
		super();
		this.date = date;
		this.shopId = shopId;
		this.shiftBig = shiftBig;
		this.workerName = workerName;
		this.isManager = isManager;
		this.isCashier = isCashier;
		this.shiftSmall = shiftSmall;
		this.jobName = jobName;
		this.totalMinute = totalMinute;
		this.hourStart = hourStart;
		this.minuteStart = minuteStart;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getShiftBig() {
		return shiftBig;
	}

	public void setShiftBig(Integer shiftBig) {
		this.shiftBig = shiftBig;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public boolean isCashier() {
		return isCashier;
	}

	public void setCashier(boolean isCashier) {
		this.isCashier = isCashier;
	}

	public Integer getShiftSmall() {
		return shiftSmall;
	}

	public void setShiftSmall(Integer shiftSmall) {
		this.shiftSmall = shiftSmall;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getTotalMinute() {
		return totalMinute;
	}

	public void setTotalMinute(Integer totalMinute) {
		this.totalMinute = totalMinute;
	}

	public Integer getHourStart() {
		return hourStart;
	}

	public void setHourStart(Integer hourStart) {
		this.hourStart = hourStart;
	}

	public Integer getMinuteStart() {
		return minuteStart;
	}

	public void setMinuteStart(Integer minuteStart) {
		this.minuteStart = minuteStart;
	}

	@Override
	public String toString() {
		return "DataOutput [date=" + date + ", shopId=" + shopId + ", shiftBig=" + shiftBig + ", workerName="
				+ workerName + ", isManager=" + isManager + ", isCashier=" + isCashier + ", shiftSmall=" + shiftSmall
				+ ", jobName=" + jobName + ", totalMinute=" + totalMinute + ", hourStart=" + hourStart
				+ ", minuteStart=" + minuteStart + "]";
	}

}
