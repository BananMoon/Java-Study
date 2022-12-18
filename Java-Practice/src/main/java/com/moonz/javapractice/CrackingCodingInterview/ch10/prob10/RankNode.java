package com.moonz.javapractice.CrackingCodingInterview.ch10.prob10;

public class RankNode {
    RankNode left, right;
    int data;
    int leftSize = 0;
    public RankNode (int d) {
        data = d;
    }

    /**
     * 스트림으로 읽은 수를 트리에 삽입
     * 이때 this는 루트이다.
     */
    public void insert(int d) {
        if (this.data < d) {    // 오른쪽 삽입
            if (this.right != null) {
                this.right.insert(d);
            } else {
                this.right = new RankNode(d);
            }
        } else {    // 기준 노드보다 같거나 작은 경우, 왼쪽 삽입
            if (this.left != null) {
                this.left.insert(d);
            } else {
                this.left = new RankNode(d);
            }
            this.leftSize++;  // 기준 노드보다 작은 값이 들어오면 현재 노드의 leftSize 증가!
        }
    }

    /**
     * x의 랭킹 (x보다 같거나 작은 수의 개수, 자신 제외)을 반환한다.
     */
    public int getRank (int d) {
        if (this.data == d) {
            return this.leftSize;

        } else if (this.data < d) { /* 현재 노드 값보다 큰 값이면 */
            int rightSize = this.right == null? -1 : this.right.getRank(d); // 값이 없다면 -1 반환 처리!!! 아니라면 오른쪽 트리로 이동
            /* 현재 노드의 오른쪽 트리 중 d를 찾으면서 계산한 값 right_total에 대한 체크 */
            if (rightSize == -1) {  // 값을 발견하지 못한 경우
                return -1;
            } else {    // 자신의 왼쪽 트리 크기 + 본인 1 + 오른쪽 트리에서의 값 d 찾기까지의 크기
                return this.leftSize + 1 + rightSize;
            }

        } else {    /* 현재 노드 값보다 작은 값이면 */
            if (this.left == null) {    // 찾는 값이 없으면!
                return -1;
            } else {
                return this.left.getRank(d);    // 현재 노드의 왼쪽 트리의 랭크 재귀 호출
            }
        }
    }
}
