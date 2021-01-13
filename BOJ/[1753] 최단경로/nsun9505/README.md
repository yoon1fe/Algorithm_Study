# [1753] 최단 경로 - JAVA

## 분류
> 다익스트라

## 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        Node(int v, int w){
            this.vertex = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine()) - 1;
        int[] dist = new int[N];
        ArrayList<Node>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if(dist[cur.vertex] <= cur.weight)
                continue;

            for(int i=0; i<graph[cur.vertex].size(); i++){
                Node next = graph[cur.vertex].get(i);
                int nextDist = cur.weight + next.weight;
                if(nextDist < dist[next.vertex]){
                    dist[next.vertex] = nextDist;
                    pq.offer(new Node(next.vertex, nextDist));
                }
            }
        }

        for(int i=0; i<N; i++) {
            if (dist[i] == INF)
                sb.append("INF\n");
            else
                sb.append(dist[i] + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제 풀이
다익스트라를 사용해서 풀 수 있는 문제입니다.

주의할점
   - V의 숫자가 크기 때문에 weight[][]와 같이 2차원 배열을 선언하면 메모리초과가 뜹니다.
   - 정점 사이에 간선이 여러 개 들어올 수 있다고 해서 있는지 체크 했는데 빼니깐 시간 초과를 패스합니다.. 머지?

다익스트라는 한 지점에서 나머지 지점들로의 최소 거리를 표시할 수 있습니다!

이번 문제에서는 우선순위 큐를 사용했습니다.
   - 그래서 Comparable의 compare을 구현하여 가중치의 합으로 오름차순으로 정렬하였습니다.

dist라는 배열도 모두 INF로 초기화해줍니다.
   - start부터의 최소 거리를 의미합니다.

처음 S를 우선순위 큐에 넣을 때는 weight 값을 0으로 넣습니다.
   - 출발지부터 출발지까지의 거리는 0이기 때문에!
   - 그러면 S에 연결된 곳들을 방문할 것입니다.

우선순위 큐에서 거리를 기준으로 내림차순한 결과를 하나씩 뽑아옵니다.
   - 우선순위 큐로부터 받은 Node X 정보를 토대로 큐에 넣을지를 결정합니다.
   - 출발지부터 X까지의 거리(X.weight)가 만약에 현재 dist[X.vertex]보다 크다면 더 이상 진행하지 않습니다.
   - 왜냐하면, X.weight를 넣을 때는 dist[X.vertex]보다 작아서 X가 우선순위 큐에 들어갔지만
   - 이후에 dist[X.vertex]가 변경되었을 수 있습니다. 다른 노드들을 경유하면서!
   - 그러므로 더 이상 진행하지 않음으로써 시간을 아낄 수 있습니다.

우선순위 큐로부터 받아온 Node X에 연결된 정보를 봅니다!
   - 만약에 X.weight + graph[X.vertex][i].weight가 dist[i]보다 작다면 S에서 i로 가는 것보다, X를 거쳐서 가는 것이 거리가 짧다는 의미이므로 dist[i]를 X.weight + graph[X.vertex][i].weight로 변경해줍니다.
   - 그리고 i번 정점에서도 다른 노드들이 연결되어 있을 수 있고 S- > X -> i를 경유해서 가는 것이 더 짧은 경로가 나올 수 있으므로 큐에 i와 i번째까지의 거리를 우선순위 큐에 넣어주면 됩니다.

우선순위 큐가 다 비워지면 while문을 빠져나오고 dist를 출력하면 끝!

## 후기
후아 일단 틀리고 시간초과 뜨면서 시행착오 끝에 풀었습니다.

다익스트라 코드 안 보고 개념보면서 코드짜고 제출하다보니 이렇게 된거 같습니다ㅠ

언제나 예제는 맞아도 틀릴수 있다는 것 또한 잊지 맙시다!