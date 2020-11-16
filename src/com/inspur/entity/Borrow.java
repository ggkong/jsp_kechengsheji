package com.inspur.entity;

public class Borrow {
    private Integer id;
    private Book book;
    private Reader reader;
    private String borrowTime;
    private String returnTime;
    private Integer state;

    public Borrow(Integer id, Book book, Reader reader, String borrowTime, String returnTime, Integer state) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.borrowTime = borrowTime;
        this.returnTime = returnTime;
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
