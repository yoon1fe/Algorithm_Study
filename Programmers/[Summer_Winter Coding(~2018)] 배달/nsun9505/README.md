# [Summber\Winter Coding(~2018)] 배달 - JAVA

## 분류
> 다익스트라

## 코드
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        ArrayList<Node>[] graph = new ArrayList[N];
        int dist[] = new int[N];
        for(int i=0; i<N; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<road.length; i++){
            graph[road[i][0]-1].add(new Node(road[i][1]-1, road[i][2]));
            graph[road[i][1]-1].add(new Node(road[i][0]-1, road[i][2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(dist[node.ver] < node.dist)
                continue;

            for(int i = 0; i < graph[node.ver].size(); i++){
                Node nextNode = graph[node.ver].get(i);
                int nextDist = node.dist + nextNode.dist;
                if(dist[nextNode.ver] > nextDist){
                    pq.offer(new Node(nextNode.ver, nextDist));
                    dist[nextNode.ver] = nextDist;
                }
            }
        }

        for(int i=0; i<N; i++){
            System.out.println(dist[i]);
            if(dist[i] <= K)
                answer++;
        }

        return answer;
    }

    static class Node implements Comparable<Node>{
        int ver;
        int dist;

        public Node(int ver, int weight) {
            this.ver = ver;
            this.dist = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(o.dist, this.dist);
        }
    }
}
```

## 문제 풀이
다익스트라로 풀면 금방 풀 수 있습니다!

1번에서 각 노드로의 거리? 시간이 최소가 되도록 해야 하기 때문에 다익스트라를 사용해서 문제를 풀었습니다.

우선순위 큐를 사용해서 다익스트라를 구현했습니다.

## 후기
다익스트라는 거의 뭐.. 머리에 박힌거 같습니다..