package com.moonz.javapractice.CrackingCodingInterview.ch9;

import lombok.Getter;

import java.util.LinkedList;

/**
 * 탐색 경로 정보를 갖는 클래스
 */
public class PathNode {
    private final PathNode previousNode;
    @Getter
    private final Person person;

    public PathNode(Person p, PathNode previous) {
        person = p;
        previousNode = previous;
    }
    /* pathNode.collapse(false) => 두 노드가 만났는데, 인자에 따라 현재 루트노드가 아니므로 루트노드까지 가는 while문을 타면서 경로를 추가해서 반환? X*/
    public LinkedList<Person> collapse(boolean startsWithRoot) {
        System.out.println("PathNode.collapse 호출 (인자: " + startsWithRoot+")");
        LinkedList<Person> path = new LinkedList<>();
        PathNode node = this;   /* 도착지 노드부터 앞 노드까지 ㄱㄱ? */
        System.out.println("현재 노드 id: "+ this.getPerson().getID());
        while (node != null) {
            if (startsWithRoot) {       /* 루트에서 시작했으면 마지막에 add */
                path.addLast(node.person);
            } else {                    /* 루트에서 시작 안했으면 첫번째에 add */
                path.addFirst(node.person);
            }
            node = node.previousNode;   // TODO 이전 노드로 왜 가지??
        }
        return path;
    }

}


