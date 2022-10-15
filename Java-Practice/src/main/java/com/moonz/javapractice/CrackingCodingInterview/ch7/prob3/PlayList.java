package com.moonz.javapractice.CrackingCodingInterview.ch7.prob3;

import lombok.AllArgsConstructor;

import java.util.Queue;
@AllArgsConstructor
public class PlayList {
    private Song song;
    private Queue<Song> queue;

    public Song getNextSnogToPlay() {
        return queue.peek();
    }
    public void addSongToPlay(Song song) {
        queue.add(song);
    }
}
