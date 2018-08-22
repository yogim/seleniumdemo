package com.ymhase.seleniumdemo.model;

public class MsgCsvFormatModel {

	private String type;
	private String tag;
	private String identifier;
	private String text;

	public MsgCsvFormatModel(String type, String tag, String identifier,
			String text) {
		super();
		
		this.type = type;
		this.tag = tag;
		this.identifier = identifier;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
}
