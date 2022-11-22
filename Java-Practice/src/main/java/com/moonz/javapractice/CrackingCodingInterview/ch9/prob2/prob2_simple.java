package com.moonz.javapractice.CrackingCodingInterview.ch9.prob2;

import java.util.*;

/**
 * github code
 */
public class prob2_simple {
    public static LinkedList<Person> findPathBiBFS(Map<Integer, Person> people, int source, int destination) {
        Queue<PathNode> adj = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        /* 출발 노드 add */
        adj.add(new PathNode(people.get(source), null));
        visited.add(source);

        while (!adj.isEmpty()) {
            PathNode curr = adj.poll();
            Person currPerson = curr.getPerson();
            /* 꺼낸 person이 도착 노드이면 */
            if (currPerson.getID() == destination) {
                return curr.collapse(false);    // 출발지에서 출
            }

            /* 아직 못찾았으니 다시 인접 노드(friend)를 추가  */
            for (Integer friend : currPerson.getFriends()) {
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    Person adjPerson = people.get(friend);
                    adj.add(new PathNode(adjPerson, curr));
                }
            }
        }
        /* 친구 못만났으면 */
        return null;
    }

    public static void printPath (List<Person> path) {
        if (path == null) {
            System.out.println("No Path!");
        } else {
            for (Person p : path) {
                System.out.println(p.getID());
            }
        }
    }

    public static void main(String[] args) {
        int nPeople = 11;
        Map<Integer, Person> peopleMap = new HashMap<>();
        for (int i = 0; i < nPeople; i++) {
            Person person = new Person(i);
            peopleMap.put(i, person);
        }
        // people 연결
        int[][] edges = {{1,4}, {1,2}, {1,3}, {3,2}, {4,6}, {3,7}, {6,9}, {9,10}, {5,10}, {2,5}, {3,7}};
        for (int[] edge : edges) {
            Person source = peopleMap.get(edge[0]);
            source.addFriend(edge[1]);

            Person dest = peopleMap.get(edge[1]);
            dest.addFriend(edge[0]);
        }
        int s = 1;
        int d = 10;
        LinkedList<Person> path = findPathBiBFS(peopleMap, s, d);
        System.out.println("id = " + s+"인 친구와 id = " + d+"인 친구 사이의 경로(path)");
        printPath(path);


        LinkedList<Person> reversePath = findPathBiBFS(peopleMap, d, s);
        System.out.println("id = " + d+"인 친구와 id = " + s+"인 친구 사이의 경로(path)");
        printPath(reversePath);
    }

}
