package org.javachina.login.dto;

import java.sql.Date;

public class ShangpinDto {
	private int id;
	private String name;
	private double price;
	private String address;
	private Date inputDate;
	private int leixingId;
	private String description;
	private String leixingName;
	public String getLeixingName() {
		return leixingName;
	}
	public void setLeixingName(String leixingName) {
		this.leixingName = leixingName;
	}
	public int getId() {
		return id;
	}
	public ShangpinDto(int id, String name, double price, String address, Date inputDate, int leixingId,
			String description,String leixingName) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.address = address;
		this.inputDate = inputDate;
		this.leixingId = leixingId;
		this.description = description;
		this.leixingName = leixingName;
	}
	public ShangpinDto(String name, double price, String address, Date inputDate, int leixingId, String description) {
		super();
		this.name = name;
		this.price = price;
		this.address = address;
		this.inputDate = inputDate;
		this.leixingId = leixingId;
		this.description = description;
	}
	public ShangpinDto() {
		super();
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public int getLeixingId() {
		return leixingId;
	}
	public void setLeixingId(int leixingId) {
		this.leixingId = leixingId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
