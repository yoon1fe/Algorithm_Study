# [10868] 최솟값 - Java

###  :bus: 분류

> 세그먼트 트리



### :bus: 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] list, segTree;

    static int findMin(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 1000000001;
        if (left <= start && end <= right) return segTree[node];

        int mid = (start + end) / 2;

        return Math.min(findMin(node * 2, start, mid, left, right), findMin(node * 2 + 1, mid + 1, end, left, right));
    }

    static int init(int node, int start, int end) {
        if (start == end) return segTree[node] = list[start];

        int mid = (start + end) / 2;

        return segTree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
    }

    public static void main(String[] args) throws Exception {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new int[N];
        segTree = new int[N * 4];

        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        init(1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(findMin(1, 0, N - 1, a - 1, b - 1));
        }
    }

}

```



### :bus: 풀이 방법

이 문제도 세그먼트 트리를 이용해서 푸는 문제입니다.

먼젓번에 푼 문제처럼 구간합이 아니라 최솟값을 각 노드에 저장하면 됩니다.

사실 걍 저번에 푼거 열심히 보고 풀었습니다 ;;



 init() 메소드에서 세그먼트 트리를 초기화합니다. 각 구간의 최솟값으로 트리를 만들어야 하므로 min(왼쪽 노드, 오른쪽 노드) 요렇게 진행되었습니다.

 

그 다음 findMin() 메소드에서는 구간(left, right)를 파라미터로 받아서 구간의 최솟값을 리턴하는 메소드입니다. 최솟값을 리턴해야 하므로 범위를 벗어나면 겁내 큰 수를 리턴해주고.. left와 right 사이에 있는 값을 리턴해주면 됩니다.



### :bus: 후기

오랜만에 하려니깐 표준 입출력도 잘 생각이 안납디다... ^^;;

역시 꾸준함이 짱이여

 

감사합니다 !!