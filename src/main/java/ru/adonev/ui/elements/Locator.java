package ru.adonev.ui.elements;

public enum Locator {
    //
    // PageObject

    LOGIN("//*[@id=\"mailbox\"]/div[1]/button"),
    FIRST_NAME("//*[@id=\"fname\"]"),
    LAST_NAME("//*[@id=\"lname\"]"),
    MALE_CSS("div > form > * input[name=\"gender\"][value=\"male\"]"),
    FEMALE_CSS("div > form > * input[name=\"gender\"][value=\"female\"]"),
    MAIL("//*[@id=\"aaa__input\"]"),
    PASSWORD("//*[@id=\"password\"]"),
    PHONE_NUMBER("//*[@id=\"phone-number__phone-input\"]"),
    ;

    public final String locator;

    Locator(String locator) {
        this.locator = locator;
    }
}
