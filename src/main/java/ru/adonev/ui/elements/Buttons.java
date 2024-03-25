package ru.adonev.ui.elements;

public enum Buttons {
    LOGIN("//*[@id=\"mailbox\"]/div[1]/button"),
    CREATE_MAIL_ACC("#root > div > div > div > div.wrapper-0-2-5 > div > div > form > div:nth-child(3) > div.JZPUNosGIyW2bqTAo1bv > div > div > a"),
    CREATE_MAIL_ACC_XPATH("//*[@id=\"root\"]/div/div/div/div[2]/div/div/form/div[3]/div[2]/div/div/a"),
    FIRST_NAME("//*[@id=\"fname\"]"),
    LAST_NAME("//*[@id=\"lname\"]"),
    DAY("//*[@id=\"root\"]/div/div[4]/div[3]/div/div/div/div/form/div[10]/div[2]/div/div/div/div[1]/div/div/select"),
    MONTH("//*[@id=\"root\"]/div/div[4]/div[3]/div/div/div/div/form/div[10]/div[2]/div/div/div/div[3]/div/select"),
    YEAR("//*[@id=\"root\"]/div/div[4]/div[3]/div/div/div/div/form/div[10]/div[2]/div/div/div/div[5]/div/div/select"),
    MALE_CSS("#root > div > div.-iGylzk8u50zKdna3C_sh._3f0XrR7XuLmcI0KcslAAhi > div:nth-child(3) > div > div > div > div > form > div:nth-child(13) > div:nth-child(2) > div > label:nth-child(1) > div.radio-0-2-331 > div.border-0-2-333"),
    FEMALE_CSS("#root > div > div.-iGylzk8u50zKdna3C_sh._3f0XrR7XuLmcI0KcslAAhi > div:nth-child(3) > div > div > div > div > form > div:nth-child(13) > div:nth-child(2) > div > label:nth-child(3) > div.radio-0-2-331 > div.border-0-2-333"),
    MAIL("//*[@id=\"aaa__input\"]"),
    PASSWORD("//*[@id=\"password\"]"),
    PHONE_NUMBER("//*[@id=\"phone-number__phone-input\"]"),
    SUBMIT_MAIL_CREATION("//*[@id=\"root\"]/div/div[4]/div[3]/div/div/div/div/form/button");

    public final String locator;

    Buttons(String locator) {
        this.locator = locator;
    }
}
