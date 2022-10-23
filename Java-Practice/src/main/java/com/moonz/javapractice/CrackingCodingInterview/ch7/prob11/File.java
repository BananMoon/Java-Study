package com.moonz.javapractice.CrackingCodingInterview.ch7.prob11;
/*
- 파일 크기 구하기
 */
public class File extends Entry {
    private int size;
    private String content;

    public File(Directory parent, String name, int size, String content) {
        super(parent, name);
        this.size = size;
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void delete() {
        size = 0;
        content = null;
    }

    // 파일은 자신 삭제 => 크기 0, 빈 내용으로 만들기만.

}