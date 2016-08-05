package com.mushan.learn;

/**
 * Created by mazhibin on 16/8/2
 */
public class Child extends Parent {

    private Book book;

    public Class<?> hi(){
        logger.debug("fuck");
        return this.getClass();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
