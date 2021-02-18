# [2252] 줄 세우기 - JAVA

## 분류
> 위상정렬

## 코드
```java
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indegree = new int[N+1];
        LinkedList<Integer>[] graph = new LinkedList[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new LinkedList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            indegree[v] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        while(!queue.isEmpty()){
            int cur = queue.poll();

            sb.append(cur + " ");

            for(int next : graph[cur]){
                indegree[next] -= 1;
                if(indegree[next] == 0)
                    queue.offer(next);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```

## 문제풀이
위상정렬을 공부한 덕분에 따로 코드를 안 보고 풀 수 있었습니다.

A B 입력을 받을 때 A -> B 방향으로 생각하고 방향 그래프를 만듭니다.

그리고 B의 indegree를 하나 증가시킵니다.

위상정렬을 하기 위해서 indegree가 0인 정점들을 모두 큐에 담습니다.

큐가 빌 때까지 while문을 돕니다.
   - 큐에서 꺼내온 정점은 indegree가 0이므로 출력합니다.
   - 그리고 해당 정점에서 갈 수 있는 다음 정점들의 indegree를 하나씩 감소시킵니다.
   - 다음 정점의 indegree를 1 감소시켜서 indegree가 0이된다면 큐에 넣어서 다음 위상정렬의 대상이 되도록 합니다.

이렇게 위상정렬을 돌면서 출력하면 그게 바로 답입니다.

## 후기
위상정렬만 쓴다면 아주 쉽게 풀립니다!