package com.ymhase.seleniumdemo.testcases;

import com.ymhase.seleniumdemo.SeleniumUtils;

public class GmailFormtesting {

	public static void main(String[] args) {
		SeleniumUtils.maximizeWindow();
		SeleniumUtils.waitTillVisible(30, "#accountDetailsNext > content");
		SeleniumUtils.clickOnElement("#accountDetailsNext > content");
	}
}
