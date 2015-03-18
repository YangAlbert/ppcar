package com.pp.rentcar.entity;

import java.io.Serializable;

public class OrderTimeLineEntity implements Serializable{
	/**
	 * ����ʱ����ʵ��
	 */
	private static final long serialVersionUID = -663262261358773854L;
	private String logo="";//ͷ��
	private String msg="";//������Ϣ
	private String time="";//����ʱ��
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
