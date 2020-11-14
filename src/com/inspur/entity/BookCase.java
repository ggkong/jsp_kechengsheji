package com.inspur.entity;

public class BookCase {
    private Integer id;
    private String name;

    public BookCase(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
