package com.example.demo.model;

public class LocationStats {
	private String state;
	private String country;
	private String latestTotalCases;
	private int difffromPrevDay;

	public int getDifffromPrevDay() {
		return difffromPrevDay;
	}
	public void setDifffromPrevDay(int difffromPrevDay) {
		this.difffromPrevDay = difffromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(String latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	
}
