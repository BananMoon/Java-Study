package com.moonz.javapractice.CrackingCodingInterview.ch7.prob3;

import lombok.Getter;
import lombok.Setter;
/*
CD 플레이어는 한번에 하나의 CD만 저장할 수 있다.
플레이리스트에는 여러 노래가 큐로 저장되어있다.
 */
@Getter
@Setter
public class CDPlayer {
    private PlayList playList;
    private CD cd;

    public CDPlayer(PlayList playList, CD cd) {
        this.playList = playList;
        this.cd = cd;
    }

    public CDPlayer(PlayList playList) {
        this.playList = playList;
    }

    public CDPlayer(CD cd) {
        this.cd = cd;
    }
}
