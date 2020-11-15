# [1516] 게임 개발 - C++

## 분류
> 위상정렬

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

public class BOJ1516 {
    static int N;
    static int[] W;
    static int[] inDegree;
    static int[] result;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        init();
        solution();
        for(int i=1; i<=N; i++)
            System.out.println(result[i]);
    }

    public static void solution(){
        Queue<Integer> Q = new LinkedList<Integer>();
        for(int i=1; i<=N; i++) {
            if (inDegree[i] == 0) {
                Q.offer(i);
                result[i] = W[i];
            }
        }

        while(!Q.isEmpty()){
            int cur = Q.poll();

            for(int i=0; i<graph[cur].size(); i++){
                int next = graph[cur].get(i);
                inDegree[next] -= 1;
                result[next] = Math.max(result[next], result[cur] + W[next]);
                if(inDegree[next] == 0)
                    Q.add(next);
            }
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        W = new int[N+1];
        inDegree = new int[N+1];
        result = new int[N+1];

        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<Integer>();

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            while(true){
                int prev = Integer.parseInt(st.nextToken());
                if(prev == -1)
                    break;
                graph[prev].add(i);
                inDegree[i] += 1;
            }
        }
    }
}
```

## 문제 풀이
저번에 풀었던 ACM Craft와 동일하다.

위상정렬을 위한 세팅을 한다.(Indegree 계산)
- 각 지점에 도착하기 위한 최대 비용을 계산하면 되는 것이다.
- 각 정점으로 가기 위한 최댓값을 계산하고, 마지막에 문제에서 원하는 노드에 대해서 위상정렬을 통해 계산된 배열에서 해당 정점 인덱스에 있는 값을 출력하면 된다.

## 후기
자바로 처음 풀어본 문제!

런타임 에러를 오지게 띄웠다..ㅋㅋㅋㅋㅋ

graph[i]에 필요할 때마다 ArrayList를 추가하니 런타임에러..

그 전에 graph[1~N]까지 각각 ArrayList를 할당하고 입력을 받으니 잘 되네.. 이건 뭘까