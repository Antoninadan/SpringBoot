package com.mainacad.model;

import lombok.Getter;

@Getter
public enum Status {
    OPEN("open"),
    TO_BE_CLOSED("tobeclosed"),
    CLOSED("closed");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public Status getByValue(String value){
       return Status.valueOf(value);
    }
 }
