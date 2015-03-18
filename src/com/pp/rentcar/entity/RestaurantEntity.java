package com.pp.rentcar.entity;

import java.io.Serializable;

public class RestaurantEntity implements Serializable{

	private static final long serialVersionUID = -6453430021123563721L;
	public String logo = "";//�̻�logo
	public String name= "";//�̻����
	public int rate_numbers= 0;//��������
	public String buy_nums= "";//�۳�����
	public String item_msg= "";//�̻���Ϣ
	public String promotion= "";//�̻��ƹ���Ϣ
	public Boolean is_rest= false;//�̻��Ƿ���Ϣ
	public Boolean is_favor= false;//�Ƿ���ӹ�ע
	public Boolean is_half= false;//�Ƿ���
	public Boolean is_mins= false;//�Ƿ����
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRate_numbers() {
		return rate_numbers;
	}
	public void setRate_numbers(int rate_numbers) {
		this.rate_numbers = rate_numbers;
	}
	public String getBuy_nums() {
		return buy_nums;
	}
	public void setBuy_nums(String buy_nums) {
		this.buy_nums = buy_nums;
	}
	public String getItem_msg() {
		return item_msg;
	}
	public void setItem_msg(String item_msg) {
		this.item_msg = item_msg;
	}
	public Boolean getIs_rest() {
		return is_rest;
	}
	public void setIs_rest(Boolean is_rest) {
		this.is_rest = is_rest;
	}
	public Boolean getIs_favor() {
		return is_favor;
	}
	public void setIs_favor(Boolean is_favor) {
		this.is_favor = is_favor;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public Boolean getIs_half() {
		return is_half;
	}
	public void setIs_half(Boolean is_half) {
		this.is_half = is_half;
	}
	public Boolean getIs_mins() {
		return is_mins;
	}
	public void setIs_mins(Boolean is_mins) {
		this.is_mins = is_mins;
	}
	

}
