package com.mannanlive.model.borrow;

import java.util.List;

public class Borrowings {
    private List<BorrowData> data;

    public Borrowings() {
    }

    public Borrowings(List<BorrowData> data) {
        this.data = data;
    }

    public List<BorrowData> getData() {
        return data;
    }

    public void setData(List<BorrowData> data) {
        this.data = data;
    }
}
