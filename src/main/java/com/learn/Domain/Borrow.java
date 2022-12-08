package com.learn.Domain;

import java.sql.Timestamp;

/**
 * @author DingTian
 * @version 1.0
 */
public class Borrow {
    private int bookId;
    private int userId;
    private Timestamp borrowTime;

    @Override
    public String toString() {
        return "BorrowDao{" +
                "bookId=" + bookId +
                ", userId=" + userId +
                ", borrowTime=" + borrowTime +
                '}';
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Timestamp borrowTime) {
        this.borrowTime = borrowTime;
    }
}
