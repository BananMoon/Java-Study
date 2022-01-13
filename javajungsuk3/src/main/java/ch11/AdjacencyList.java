package ch11;
// ArrayList를 이용하여 양방향 인접리스트 구현

import java.util.ArrayList;

public class AdjacencyList {
    private ArrayList<ArrayList<Integer>> listGraph;

    //그래프 초기화 (생성자 이용)
    public AdjacencyList(int initSize) {
        this.listGraph = new ArrayList<ArrayList<Integer>>();
        // 노드는 0부터 시작하므로 반복문을 길이+1까지 돌도록 한다.
        // initSize = 3
        // graph[0]
        // graph[1] -> 2 -> 3
        // graph[2] -> 1 -> 3 -> 4
        // graph[3] -> 1 -> 2 -> 4 -> 5
        for (int i=0; i<initSize+1; i++ )
            listGraph.add(new ArrayList<Integer>());
    }

    // 그래프 리턴
    public ArrayList<ArrayList<Integer>> getListGraph() {
        return this.listGraph;
    }
    // 그래프 추가(양방향)
    public void put(int x, int y) {
        listGraph.get(x).add(y);
        listGraph.get(y).add(x);
    }

    // 그래프 추가 (단방향)
    public void putSingle(int x, int y) {
        listGraph.get(x).add(y);
    }
    // 그래프 출력 (인접 리스트)
    public void printListGraph() {
        for (int i=0; i<listGraph.size(); i++) {
            System.out.print("정점 " + i+ "의 인접 리스트 ");

            for (int j=0; j< listGraph.get(i).size(); j++) {
                System.out.print("-> " + listGraph.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int initSize = 6;
        AdjacencyList adjList = new AdjacencyList(initSize);

        adjList.put(1, 2);
        adjList.put(1, 3);
        adjList.put(2, 3);
        adjList.put(2, 4);
        adjList.put(3, 4);
        adjList.put(4, 5);
        adjList.put(4, 6);

        adjList.printListGraph();
    }
}
