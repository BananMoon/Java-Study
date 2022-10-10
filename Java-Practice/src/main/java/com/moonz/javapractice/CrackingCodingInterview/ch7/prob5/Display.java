package com.moonz.javapractice.CrackingCodingInterview.ch7.prob5;

public class Display {
    private Book activeBook;
    private User activeUser;
    private int pageNum;

    public void displayUser(User user) {
        activeUser = user;
        refreshUser();
    }

    public void displayBook(Book book) {
        activeBook = book;
        pageNum = 0;

        refreshTitle();
        refreshDetails();
        refreshPage();
    }

    public void turnNextPage() {
        pageNum++;
        refreshPage();
    }
    public void turnBeforePage() {
        pageNum--;
        refreshPage();
    }

    private void refreshUser() {
    }
    private void refreshTitle() {
    }
    private void refreshDetails() {
    }
    private void refreshPage() {
    }
}
