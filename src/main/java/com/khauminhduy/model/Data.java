package com.khauminhduy.model;

public class Data {

	private String date;
	private String shopId;
	private String shiftBig;
	private String shiftSmall;
	private String shiftName;
	private String headCount;
	private String timeShiftSmall;
	private String timeShiftBig;
	private String jobName;
	private String typeWork;
	private String propertiesWork;
	private String listWorkers;
	private String minutesFinishWork;
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

	public String getShiftBig() {
		return shiftBig;
	}

	public void setShiftBig(String shiftBig) {
		this.shiftBig = shiftBig;
	}

	public String getShiftSmall() {
		return shiftSmall;
	}

	public void setShiftSmall(String shiftSmall) {
		this.shiftSmall = shiftSmall;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getHeadCount() {
		return headCount;
	}

	public void setHeadCount(String headCount) {
		this.headCount = headCount;
	}

	public String getTimeShiftSmall() {
		return timeShiftSmall;
	}

	public void setTimeShiftSmall(String timeShiftSmall) {
		this.timeShiftSmall = timeShiftSmall;
	}

	public String getTimeShiftBig() {
		return timeShiftBig;
	}

	public void setTimeShiftBig(String timeShiftBig) {
		this.timeShiftBig = timeShiftBig;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getLoaiCV() {
		return typeWork;
	}

	public void setLoaiCV(String loaiCV) {
		this.typeWork = loaiCV;
	}

	public String getTcCV() {
		return propertiesWork;
	}

	public void setTcCV(String tcCV) {
		this.propertiesWork = tcCV;
	}

	public String getSoNguoiLam() {
		return listWorkers;
	}

	public void setSoNguoiLam(String soNguoiLam) {
		this.listWorkers = soNguoiLam;
	}

	public String getSoPhutHoanThanh() {
		return minutesFinishWork;
	}

	public void setSoPhutHoanThanh(String soPhutHoanThanh) {
		this.minutesFinishWork = soPhutHoanThanh;
	}

	public String getGioBD() {
		return hourStart;
	}

	public void setGioBD(String gioBD) {
		this.hourStart = gioBD;
	}

	public String getPhutBD() {
		return minuteStart;
	}

	public void setPhutBD(String phutBD) {
		this.minuteStart = phutBD;
	}

	public String getGioKT() {
		return hourEnd;
	}

	public void setGioKT(String gioKT) {
		this.hourEnd = gioKT;
	}

	public String getPhutKT() {
		return minuteEnd;
	}

	public void setPhutKT(String phutKT) {
		this.minuteEnd = phutKT;
	}

	@Override
	public String toString() {
		return "Data [date=" + date + ", shopId=" + shopId + ", shiftBig=" + shiftBig + ", shiftSmall=" + shiftSmall
				+ ", shiftName=" + shiftName + ", headCount=" + headCount + ", timeShiftSmall=" + timeShiftSmall
				+ ", timeShiftBig=" + timeShiftBig + ", jobName=" + jobName + ", loaiCV=" + typeWork + ", tcCV=" + propertiesWork
				+ ", soNguoiLam=" + listWorkers + ", soPhutHoanThanh=" + minutesFinishWork + ", gioBD=" + hourStart
				+ ", phutBD=" + minuteStart + ", gioKT=" + hourEnd + ", phutKT=" + minuteEnd + "]";
	}
	
	

}
