package com.example.spring_test.domain;


import com.example.spring_test.util.excel.NameToField;

public class ExcelObject implements NameToField {
    private Integer row;

    public Integer getRow() {
        return row;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
