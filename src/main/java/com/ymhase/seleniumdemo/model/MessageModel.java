package com.ymhase.seleniumdemo.model;

public class MessageModel {

	private String selector;
	private String text;

	public MessageModel(String selector, String text) {
		super();
		this.selector = selector;
		this.text = text;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
