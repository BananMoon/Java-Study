package com.moonz.javapractice.CrackingCodingInterview.ch7.prob11;

import java.time.LocalDateTime;
/*
File과 Directory의 공통 부분 클래스
- 전체 경로 얻기
- 이름 변경
- 삭제
- 크기 구하기
 */
public abstract class Entry {
    protected Directory parent;
    protected String name;
    protected LocalDateTime createDate;
    protected LocalDateTime lastAccessedDate;
    protected LocalDateTime lastUpdatedDate;

    public abstract int getSize();

    public Entry(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
        createDate = LocalDateTime.now();
        lastAccessedDate = LocalDateTime.now();
        lastUpdatedDate = LocalDateTime.now();
    }

    public void changeName(String name) {
        this.name = name;
    }

    public String getFullPath() {
        if (parent == null) {
            return name;
        }
        return parent.getFullPath() + "/" + name;
    }

    /*
    *  파일은 그냥 자신 삭제
    * 디렉토리는 아래 자식들 삭제하고 자신 삭제
     */
    public abstract void delete();

}

