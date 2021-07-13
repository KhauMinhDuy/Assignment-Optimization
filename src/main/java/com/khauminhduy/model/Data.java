package com.khauminhduy.model;

public class Data {

	private String date;
	private String shopId;
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
	private String hourStart;
	private String minuteStart;
	private String hourEnd;
	private String minuteEnd;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
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

	public String getHourStart() {
		return hourStart;
	}

	public void setHourStart(String hourStart) {
		this.hourStart = hourStart;
	}

	public String getMinuteStart() {
		return minuteStart;
	}

	public void setMinuteStart(String minuteStart) {
		this.minuteStart = minuteStart;
	}

	public String getHourEnd() {
		return hourEnd;
	}

	public void setHourEnd(String hourEnd) {
		this.hourEnd = hourEnd;
	}

	public String getMinuteEnd() {
		return minuteEnd;
	}

	public void setMinuteEnd(String minuteEnd) {
		this.minuteEnd = minuteEnd;
	}

	@Override
	public String toString() {
		return "Data [date=" + date + ", shopId=" + shopId + ", shiftBig=" + shiftBig + ", shiftSmall=" + shiftSmall
				+ ", shiftName=" + shiftName + ", headCount=" + headCount + ", timeShiftSmall=" + timeShiftSmall
				+ ", timeShiftBig=" + timeShiftBig + ", jobName=" + jobName + ", loaiCV=" + typeWork + ", tcCV="
				+ propertiesWork + ", soNguoiLam=" + listWorkers + ", soPhutHoanThanh=" + minutesFinishWork + ", gioBD="
				+ hourStart + ", phutBD=" + minuteStart + ", gioKT=" + hourEnd + ", phutKT=" + minuteEnd + "]";
	}

}
