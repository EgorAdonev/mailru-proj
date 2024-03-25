package ru.adonev.ui.elements;

public enum Buttons {
    LOGIN("//*[@id=\"mailbox\"]/div[1]/button");

    public final String locator;

    private Buttons(String locator) {
        this.locator = locator;
    }
}
