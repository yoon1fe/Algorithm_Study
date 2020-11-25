# [2606] 바이러스 - JAVA

## 분류
> BFS

## 코드
```java
package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2606 {
    public static int V;
    public static int E;
    public static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(BFS());
    }

    public static int BFS(){
        int ret = 0;
        boolean[] isVisited = new boolean[V+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        isVisited[1] = true;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int i=0; i<graph[cur].size(); i++){
                int next = graph[cur].get(i);
                if(isVisited[next])
                    continue;

                queue.offer(next);
                isVisited[next] = true;
                ret++;
            }
        }
        return ret;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<E; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
    }
}
```

## 문제 풀이
BFS를 통해서 문제해결 가능
- 1번부터 시작하는 BFS를 돌려서 방문한 노드의 개수를 출력하면 끝!

## 후기
실버 3!ㅋㅋㅋㅋㅋㅋㅋ