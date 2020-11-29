# [1197] 최소 스패닝 트리 - Java

## 분류
> MST

## 코드
```java
package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1197 {
    static class Node implements Comparable<Node>{
        int u;
        int v;
        int weight;

        Node(int u, int v, int w){
            this.u = u;
            this.v = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight >= n.weight ? 1 : -1;
        }
    }

    static int V, E;
    static int[] parent;
    static int[] rank;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    public static void solution(){
        int nodeCnt = 0;
        int ans = 0;
        while(nodeCnt < V-1){
            Node node = pq.poll();

            int u = find(node.u);
            int v = find(node.v);

            if(union(u, v)){
                nodeCnt += 1;
                ans += node.weight;
            }
        }
        System.out.println(ans);
    }

    public static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int u, int v){
        u = find(u);
        v = find(v);

        if(u == v)
            return false;

        if(rank[u] > rank[v]){
                int tmp = u;
                u = v;
                v = tmp;
        }
        parent[u] = v;
        if(rank[u] == rank[v])
            rank[u]++;

        return true;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        rank = new int[V+1];
        pq =  new PriorityQueue<>();

        for(int i=1; i<=V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Node(u, v, w));
        }
    }
}
```

## 문제 풀이
Kruskal을 사용하여 MST를 만들면 된다.

1. Edge들을 입력받아서 모두 Priority Queue에 넣어준다.
- 우선순위 큐는 가중치에 따라 min heap 형태로 한다.

1. 우선순위 큐에서 top의 값을 받아오면서 union을 수행한다.
- union의 리턴 값이 true이면, edge의 개수를 늘린다.
- 추가된 edge의 수가 V-1개라면 while문을 종료하고 추가된 edge들의 가중치의 합을 출력하면 된다.
- MST가 되면 반드시 edge의 수는 V-1이므로 추가된 edge의 수가 V-1이면 MST가 만들어진 것이므로 종료하면 된다.

## 후기
MST를 다시 공부할 수 있었음!