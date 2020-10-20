package com.example.excel.domain;

import com.example.excel.util.excel.NameToField;

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
