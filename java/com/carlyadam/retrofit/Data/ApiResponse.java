package com.carlyadam.retrofit.Data;

import java.util.List;

public class ApiResponse {
    private String message;
    private List<Book> books;

    public ApiResponse(String message, List<Book> books) {
        this.message = message;
        this.books = books;
    }


    public String getMesssage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
