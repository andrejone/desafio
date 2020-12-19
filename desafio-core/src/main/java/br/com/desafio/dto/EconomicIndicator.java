package br.com.desafio.dto;

public class EconomicIndicator {

	private Indicator indicator;
	private Country country;
	private String countryiso3code;
	private String date;
	private String value;
    private String unit;
    private String obs_status;
    private Integer decimal;

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCountryiso3code() {
		return countryiso3code;
	}

	public void setCountryiso3code(String countryiso3code) {
		this.countryiso3code = countryiso3code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getObs_status() {
		return obs_status;
	}

	public void setObs_status(String obs_status) {
		this.obs_status = obs_status;
	}

	public Integer getDecimal() {
		return decimal;
	}

	public void setDecimal(Integer decimal) {
		this.decimal = decimal;
	}
}
