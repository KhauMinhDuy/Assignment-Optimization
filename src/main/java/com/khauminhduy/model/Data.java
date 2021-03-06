package com.khauminhduy.model;

public class Data {

	private String date;
	private Integer shopId;
	private Integer shiftBig;
	private Integer shiftSmall;
	private String shiftName;
	private Integer headCount;
	private Integer timeShiftSmall;
	private Integer timeShiftBig;
	private String jobName;
	private Integer typeWork;
	private Integer propertiesWork;
	private Integer listWorkers;
	private Integer minutesFinishWork;
	private Integer hourStart;
	private Integer minuteStart;
	private Integer hourEnd;
	private Integer minuteEnd;

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

	public Integer getShiftSmall() {
		return shiftSmall;
	}

	public void setShiftSmall(Integer shiftSmall) {
		this.shiftSmall = shiftSmall;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public Integer getHeadCount() {
		return headCount;
	}

	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}

	public Integer getTimeShiftSmall() {
		return timeShiftSmall;
	}

	public void setTimeShiftSmall(Integer timeShiftSmall) {
		this.timeShiftSmall = timeShiftSmall;
	}

	public Integer getTimeShiftBig() {
		return timeShiftBig;
	}

	public void setTimeShiftBig(Integer timeShiftBig) {
		this.timeShiftBig = timeShiftBig;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getLoaiCV() {
		return typeWork;
	}

	public void setLoaiCV(Integer loaiCV) {
		this.typeWork = loaiCV;
	}

	public Integer getTcCV() {
		return propertiesWork;
	}

	public void setTcCV(Integer tcCV) {
		this.propertiesWork = tcCV;
	}

	public Integer getTypeWork() {
		return typeWork;
	}

	public void setTypeWork(Integer typeWork) {
		this.typeWork = typeWork;
	}

	public Integer getPropertiesWork() {
		return propertiesWork;
	}

	public void setPropertiesWork(Integer propertiesWork) {
		this.propertiesWork = propertiesWork;
	}

	public Integer getListWorkers() {
		return listWorkers;
	}

	public void setListWorkers(Integer listWorkers) {
		this.listWorkers = listWorkers;
	}

	public Integer getMinutesFinishWork() {
		return minutesFinishWork;
	}

	public void setMinutesFinishWork(Integer minutesFinishWork) {
		this.minutesFinishWork = minutesFinishWork;
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

	public Integer getHourEnd() {
		return hourEnd;
	}

	public void setHourEnd(Integer hourEnd) {
		this.hourEnd = hourEnd;
	}

	public Integer getMinuteEnd() {
		return minuteEnd;
	}

	public void setMinuteEnd(Integer minuteEnd) {
		this.minuteEnd = minuteEnd;
	}

	@Override
	public String toString() {
		return "Data [date=" + date + ", shopId=" + shopId + ", shiftBig=" + shiftBig + ", shiftSmall=" + shiftSmall
				+ ", shiftName=" + shiftName + ", headCount=" + headCount + ", timeShiftSmall=" + timeShiftSmall
				+ ", timeShiftBig=" + timeShiftBig + ", jobName=" + jobName + ", typeWork=" + typeWork
				+ ", propertiesWork=" + propertiesWork + ", listWorkers=" + listWorkers + ", minutesFinishWork="
				+ minutesFinishWork + ", hourStart=" + hourStart + ", minuteStart=" + minuteStart + ", hourEnd="
				+ hourEnd + ", minuteEnd=" + minuteEnd + "]";
	}

}
