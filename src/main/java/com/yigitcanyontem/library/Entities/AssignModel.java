package com.yigitcanyontem.library.Entities;

public class AssignModel {
    private Integer customerId;
    private Integer bookId;

    @Override
    public String toString() {
        return "AssignModel{" +
                "customerId=" + customerId +
                ", bookId=" + bookId +
                '}';
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public AssignModel(Integer customerId, Integer bookId) {
        this.customerId = customerId;
        this.bookId = bookId;
    }
}
