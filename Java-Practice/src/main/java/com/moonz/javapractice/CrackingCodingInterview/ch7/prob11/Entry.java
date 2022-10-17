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
     * 부모가 없으면 자신 삭제 X
     * 부모가 있으면 부모의 자식(==자신) 삭제
     * 이때 부모.deleteEntry(~)는 무조건 자식 Directory의 메서드 호출.
     */
    public boolean delete() {
        if (parent == null) return false;
        // (부모가 있다면) 부모의 자식들 삭제 (이때 부모는 무조건 Directory)
        return parent.deleteEntry(this);
    }
}