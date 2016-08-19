package com.github.danshannon.ckb.model;

import java.util.Calendar;

public class Client {
	private Long id;
	private String name;
	private byte[] logo;
	private boolean active = Boolean.TRUE;
	private String address;
	private String industry;
	private String website;
	private Calendar createDate;

	public Client() {
		// Required for Jackson serialisation
	}

	public Client(final Long id, final String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(final byte[] logo) {
		this.logo = logo;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean isActive) {
		this.active = isActive;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(final String industry) {
		this.industry = industry;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(final String website) {
		this.website = website;
	}

	public Calendar getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(final Calendar createDate) {
		this.createDate = createDate;
	}

}
