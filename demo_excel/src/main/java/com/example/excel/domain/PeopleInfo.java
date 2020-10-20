package com.example.excel.domain;

import com.example.excel.util.excel.TranslationField;
import org.springframework.util.StringUtils;

import java.util.Date;

public class PeopleInfo extends ExcelObject {
    private String name;
    private Integer age;
    private String gender; //setter、getter
    private String certificatetype;
    private String birth;

    //此处只是简单的校验
    @Override
    public boolean isEmpty() {
        return StringUtils.isEmpty(this.name)
                && StringUtils.isEmpty(this.gender)
                && StringUtils.isEmpty(this.certificatetype);
    }

    public String getCertificatetype() {
        return certificatetype;
    }

    public void setCertificatetype(String certificatetype) {
        this.certificatetype = TranslationField.getFieldTran().get(certificatetype);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "PeopleInfo{" +
                "row='" + super.getRow() + '\'' +
                ",name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", certificatetype='" + certificatetype + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}