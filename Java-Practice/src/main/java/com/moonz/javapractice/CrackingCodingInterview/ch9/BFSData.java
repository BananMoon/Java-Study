package com.moonz.javapractice.CrackingCodingInterview.ch9;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 너비우선탐색에 필요한 정보들을 저장하고 있는 클래스
 * 경로 표현 클래스 PathNode를 큐로 갖는다.
 * 연결된 사람 정보를 Map으로 갖는다.
 */
public class BFSData {
    public Queue<PathNode> adj = new LinkedList<>();    /* 인접 경로를 큐에 넣어서 BFS 탐색에 사용 */
    public Map<Integer, Person> visited = new HashMap<>();  /* 방문한 사람 정보 */
    public BFSData(Person root) {
        PathNode sourcePath = new PathNode(root, null);
        adj.add(sourcePath);
        visited.put(root.getID(), root);
    }

    public boolean isFinished() {
        return adj.isEmpty();
    }
}
