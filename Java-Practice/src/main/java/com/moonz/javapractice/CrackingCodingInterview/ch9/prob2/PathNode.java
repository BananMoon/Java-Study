package com.moonz.javapractice.CrackingCodingInterview.ch9.prob2;

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
    /* pathNode.collapse(false) => 두 노드가 만났는데, 만난 지점이 도착지점이면 false, 출발지점이면 true */
    public LinkedList<Person> collapse(boolean startsWithRoot) {
        System.out.println("PathNode.collapse 호출 (인자: " + startsWithRoot+")");
        System.out.println("현재 노드 id: "+ this.getPerson().getID());
        LinkedList<Person> path = new LinkedList<>();
        PathNode node = this;   /* 도착지 노드부터 앞 노드까지 ㄱㄱ? */
        while (node != null) {
            if (startsWithRoot) {       /* 루트에서 시작했으면 마지막에 add */
                path.addLast(node.person);
            } else {                    /* 루트에서 시작 안했으면 첫번째에 add */
                path.addFirst(node.person);
            }
            node = node.previousNode;   // TODO 이전 노드로..
        }
        return path;
    }

}


