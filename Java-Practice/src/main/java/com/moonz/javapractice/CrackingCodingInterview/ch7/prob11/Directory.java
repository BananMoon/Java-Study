package com.moonz.javapractice.CrackingCodingInterview.ch7.prob11;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
/*
- 하위 파일 및 디렉토리 ~
  - 갯수 구하기
  - 크기 구하기
  - 추가하기
  - 삭제하기
 */
@Getter
public class Directory extends Entry {
    public List<Entry> contents;

    public Directory(Directory parent, String name) {
        super(parent, name);
        contents = new ArrayList<>();   // 빈 디렉토리
    }

    @Override
    public void delete() {
        for (Entry entry: contents) {
            deleteEntry(entry);
        }
    }

    /*
    파일 크기 구한다.
     */
    public int getSize() {
        int totalDirectorySize=0;
        for (Entry entry : contents) {
            totalDirectorySize += entry.getSize();
        }
        return totalDirectorySize;
    }

    /*
    파일 갯수 구한다.
    - 디렉토리라면 해당 디렉토리 내 파일 갯수를 구한다. (재귀)
    - instanceof로 구분하거나, 하위 디렉토리와 파일들을 구분한 두개의 List 필드를 두거나
     */
    public int numberOfEntry() {
        int count = 0;
        for (Entry e : contents) {
            if (e instanceof File) {
                count++;
            } else if (e instanceof Directory) {
                Directory d = (Directory)e;
                count += d.numberOfEntry();  /* Entry가 아닌 Directory에만 numberOfEntry()가 있기 때문에 형변환 필요 */
            }
        }
        return count;
    }

    /*
    Etnry 삭제
     */
    public void deleteEntry(Entry entry) {
        contents.remove(entry);
    }

    /*
    Entry 추가
     */
    public boolean addEntry(Entry entry) {
        return contents.add(entry);
    }
}