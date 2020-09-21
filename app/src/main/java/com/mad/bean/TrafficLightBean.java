package com.mad.bean;

public class TrafficLightBean extends BaseBean {
	private int roadId;
	private int RedTime;
	private int YellowTime;
	private int GreenTime;
	public int getRoadId() {
		return roadId;
	}
	public void setRoadId(int roadId) {
		this.roadId = roadId;
	}
	public int getRedTime() {
		return RedTime;
	}
	public void setRedTime(int redTime) {
		RedTime = redTime;
	}
	public int getYellowTime() {
		return YellowTime;
	}
	public void setYellowTime(int yellowTime) {
		YellowTime = yellowTime;
	}
	public int getGreenTime() {
		return GreenTime;
	}
	public void setGreenTime(int greenTime) {
		GreenTime = greenTime;
	}

}
