# [1967] 트리의 지름 - JAVA

## 분류
> BFS

## 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static int ans = 0;
    static int N;
    static ArrayList<Element> graph[];
    static boolean[] isVisited;
    static Queue<Element> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        isVisited = new boolean[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[parent].add(new Element(child, weight));
            graph[child].add(new Element(parent, weight));
        }

        Element startVertex = BFS(1);
        Element endVertex = BFS(startVertex.vertex);

        sb.append(endVertex.dist);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static Element BFS(int start) {
        Arrays.fill(isVisited, false);
        isVisited[start] = true;
        queue.offer(new Element(start, 0));

        Element max = new Element(1, 0);
        while (!queue.isEmpty()) {
            Element elem = queue.poll();

            for (Element next : graph[elem.vertex]) {
                if (isVisited[next.vertex])
                    continue;

                isVisited[next.vertex] = true;
                queue.offer(new Element(next.vertex, next.dist + elem.dist));
                if (max.dist < next.dist + elem.dist) {
                    max.dist = next.dist + elem.dist;
                    max.vertex = next.vertex;
                }
            }
        }
        return max;
    }

    static class Element{
        int vertex;
        int dist;

        public Element(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}
```

## 문제 풀이
트리의 지름을 구하는 것은 임의의 노드에서 가장 먼 거리를 구한 다음에 거기서 다시 BFS를 돌리면 된다고 생각했습니다.

근데 여기의 문제는 거리보다는 가중치의 합이 젤 큰 경로가 트리의 지름입니다.

그래서 1번이 루트노드이므로 여기서 BFS를 돌려서 가중치의 합이 가장 큰 노드를 구합니다.

구한 노드에서 다시 BFS를 돌려서 가중치의 합이 가장 큰 노드까지의 가중치 합을 출력하면 됩니다.

그래서 총 BFS 두 번을 돌리면 됩니다.
   - 1번에서 출발해서 경로의 가중치 합이 가장 큰 vertex 찾기
   - 찾은 vertex에서 BFS를 돌려서 가중치의 합을 답으로 출력

## 후기
트리의 지름~

오늘도 화이팅!