package com.moonz.javapractice.CrackingCodingInterview.ch7.prob3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
CD 플레이 방식의 주크 박스
총 100장의 CD를 교체, 보관한다.
버튼 조작을 통해 원하는 CD의 원하는 트랙을 재생한다.
 */
@AllArgsConstructor
public class JukeBox {
    private Song song;
    private User user;
    private CDPlayer cdPlayer;   // CDPlayer : 하나의 CD만 갖을 수 있다.
    private Set<CD> cdSet = new HashSet<>();   // 여러개의 CD를 갖고있을 수 있다.
    private SongSelector songSelector;    // CDChanger ??..

    public Song getNowSong() {
        return songSelector.getCurrentSong();
    }
}

@Getter
@Setter
class Song {
    private long id;
    private CD cd;
    private String title;
    private int playLength;
}

@Getter
@Setter
class CD {
    private int id;
    private List<String> artists;
    private List<Song> songs;
}

@Getter
@Setter
class SongSelector {
    private Song currentSong;
    public SongSelector(Song s) {
        currentSong = s;
    }
}

@Getter
@Setter
@AllArgsConstructor
class User {
    private long id;
    private String name;
    public static User addUser(long id, String name) {
        return new User(id, name);
    }
}