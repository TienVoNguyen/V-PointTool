package com.vpoint.vpointtool.models.request;

public class CategoryForm {
    String name;

    public CategoryForm(String name) {
        this.name = name;
    }

    public CategoryForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
