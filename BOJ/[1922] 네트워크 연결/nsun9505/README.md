# [1922] 네트워크 연결 - Java

## 분류
> MST

## 코드
```java
package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922 {
    static class Node implements Comparable<Node>{
        int cur;
        int next;
        int weight;

        Node(int cur, int n, int w){
            this.cur = cur;
            this.next = n;
            this.weight = w;
        }

        @Override
        public int compareTo(Node node) {
            return (this.weight - node.weight);
        }
    }

    public static int N;
    public static int M;
    public static PriorityQueue<Node> pq;
    public static int[] parent;
    public static int[] rank;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    public static int solution(){
        int cnt = 0;
        int ret = 0;
        while(cnt < N-1 && !pq.isEmpty()){
            Node node = pq.poll();

            if(!union(node.cur, node.next))
                continue;

            cnt++;
            ret += node.weight;
        }
        return ret;
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

        if(rank[u] < rank[v])
            parent[u] = v;
        else if(rank[u] > rank[v])
            parent[v] = u;
        else{
            parent[v] = u;
            rank[u] += 1;
        }

        return true;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        rank = new int[N+1];

        for(int i=1; i<=N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        pq = new PriorityQueue<>();

        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Node(u, v, w));
        }
    }
}
```

## 문제 풀이
Kruskal을 사용해서 풀기!
1. 각 엣지에 대해서 입력을 받아서 Priority Queue에 담습니다.

1. PQ에서 엣지 정보를 받아온다.
   - Union-Find를 사용하여 x, y가 동일한 parent를 갖는지 체크
      - x, y가 동일한 parent를 갖는다면 사이클이 존재하므로 MST에 추가하지 않음(return false)
      - x, y가 동일한 parent를 갖지 않는다면 사이클이 존재하지 않으므로 MST에 추가(return true)
   - Find
      - 현재 x 값과 parent[x]가 같다면 return x
      - 현재 x 값과 parent[x]가 같지 않다면, 즉 부모가 존재한다면 return parent[x] = find(parent[x])를 한다.
      - parent[x] = find(parent[x])를 하는 이유는 최악의 상황에서 길게 이어지는데 이것을 방지하기 위해서 트리의 높이를 압축시키기 위함이다.
   - Union
      - 각 u, v에 대해서 find를 수행한다.
      - find(u) == find(v)라면 부모가 동일하므로 사이클이 생긴다는 것이다. 그러므로 return false
      - find(u) != find(v)라면 rank 값을 비교하게 되는데 작은 트리를 큰 트리 루트에 붙이기 위해 rank[u], rank[v]를 비교하여 작은 쪽의 parent를 큰 쪽의 노드번호로 설정한다.
      - 만약 rank가 같다면, 그냥 v의 부모를 u로 한다.(어느 쪽을 하든 상관없음) 그리고 rank[u] += 1을 해서 높이가 1증가 했다는 것을 표시한다. rank[v]랑 높이가 같고 rank[v]가 붙으면 높이가 1증가하기 때문이다.
    
1. MST에 추가한 경우 ret에 해당 엣지의 weight를 더한다.
1. cnt가 N-1(총 노드 수 - 1)라면 멈추고 ret을 리턴한다.

## 후기
MST는 언제나 재미있는 문제~