package ch11;

public class AdjacencyMatrix {
    private int[][] matrixGraph;    // 연결은 1, 연결되지 않은 노드끼리는 0

    // 생성자로 초기화
    public AdjacencyMatrix(int initSize) {
        // 배열의 인덱스는 0부터 시작이므로 행과 열 size +1로 초기화
        this.matrixGraph = new int[initSize + 1][initSize + 1];
    }

    // 그래프 리턴
    public int[][] getMatrixGraph() {
        return this.matrixGraph;
    }

    // 그래프 추가 (양방향)
    public void put (int x, int y) {
        matrixGraph[x][y] = matrixGraph[y][x] = 1;
    }

    // 그래프 추가 (단방향)
    public void putSingle (int x, int y) {
        matrixGraph[x][y] = 1;
    }
    // 그래프 출력 (인접 행렬)
    public void printAdjacencyMatrix() {
        for (int i=0; i<matrixGraph.length; i++) {
            System.out.print(i+ "| ");

            for (int j=0; j<matrixGraph[i].length; j++) {
                System.out.print(" " + matrixGraph[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int initSize = 6;
        AdjacencyMatrix adjMatrix = new AdjacencyMatrix(initSize);

        adjMatrix.put(1, 2);
        adjMatrix.put(1, 3);
        adjMatrix.put(2, 3);
        adjMatrix.put(2, 4);
        adjMatrix.put(3, 4);
        adjMatrix.put(3, 5);
        adjMatrix.put(4, 5);
        adjMatrix.put(4, 6);

        adjMatrix.printAdjacencyMatrix();
    }
}
