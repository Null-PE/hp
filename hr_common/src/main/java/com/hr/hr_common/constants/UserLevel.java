package com.hr.hr_common.constants;

public enum UserLevel {
    SAASADMIN("saasAdmin"),
    COADMIN("coAdmin"),
    USER("user");

    private final String value;

    UserLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
