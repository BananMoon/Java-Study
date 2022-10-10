package com.moonz.javapractice.CrackingCodingInterview.ch7.prob5;

import lombok.Getter;
/*
온라인 리더기 시스템
- 몸통 역할로, 내부에 컴포넌트 분리함.
- 책 관리 Library, 사용자 관리 UserManager, 화면 관리 Display
- 데이터 저장하는 용도의 클래스 Book, User
 */
public class OnlieReaderSystem {
    @Getter
    private Library library;
    @Getter
    private UserManager userManager;
    @Getter
    private Display display;

    @Getter
    private Book activeBook;
    @Getter
    private User activeUser;

    public OnlieReaderSystem() {
        library = new Library();
        userManager = new UserManager();
        display = new Display();
    }

    public void setActiveUser(User user) {
        activeUser = user;
        display.displayUser(user);
    }

    public void setActiveBook(Book book) {
        activeBook = book;
        display.displayBook(book);
    }
}
