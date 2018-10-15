package com.carlyadam.retrofit.Data;

public class Book {
    private int ID;
    private String Title;
    private String Description;
    private int PageCount;
    private String Excerpt;
    private String PublishDate;

    public Book(int ID, String title, String description, int pageCount, String excerpt, String publishDate) {
        this.ID = ID;
        Title = title;
        Description = description;
        PageCount = pageCount;
        Excerpt = excerpt;
        PublishDate = publishDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public String getExcerpt() {
        return Excerpt;
    }

    public void setExcerpt(String excerpt) {
        Excerpt = excerpt;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }
}
