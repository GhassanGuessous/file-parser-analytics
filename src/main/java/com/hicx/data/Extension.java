package com.hicx.data;

public enum Extension {

    TXT("txt"),
    PDF("pdf"),
    DOC("doc");

    private String code;

    Extension(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
