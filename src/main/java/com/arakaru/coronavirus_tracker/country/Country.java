package com.arakaru.coronavirus_tracker.country;

public class Country {

	private String state;
	private String countryName;
	private String count;
	private String lastCount;

	@Override
	public String toString() {
		return "Country [state=" + state + ", countryName=" + countryName + ", count=" + count + "]";
	}

	public String getLastCount() {
		return lastCount;
	}

	public void setLastCount(String lastCount) {
		this.lastCount = lastCount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
