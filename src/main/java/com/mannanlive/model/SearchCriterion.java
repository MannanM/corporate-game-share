package com.mannanlive.model;

public class SearchCriterion {
    private String key;
    private String secondaryKey;
    private String operation;
    private Object value;

    public SearchCriterion(String key, String secondaryKey, String operation, Object value) {
        this.key = key;
        this.secondaryKey = secondaryKey;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getSecondaryKey() {
        return secondaryKey;
    }

    public void setSecondaryKey(String secondaryKey) {
        this.secondaryKey = secondaryKey;
    }
}
