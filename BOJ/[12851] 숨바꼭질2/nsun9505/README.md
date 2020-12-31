# [12851] 숨바꼭질 2 - JAVA

## 분류
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] isVisited = new boolean[MAX];
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int targetDist = Integer.MAX_VALUE;
        int targetCnt = 0;

        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(N, 0));

        while(!queue.isEmpty()){
            Element elem = queue.poll();

            if(targetDist < elem.dist)
                continue;

            isVisited[elem.num] = true;

            if(elem.num == M){
                targetDist = Math.min(elem.dist, targetDist);
                targetCnt += 1;
                continue;
            }

            if(elem.num - 1 >= 0 && !isVisited[elem.num - 1])
                queue.offer(new Element(elem.num -1, elem.dist + 1));
            if(elem.num + 1 < MAX && !isVisited[elem.num + 1])
                queue.offer(new Element(elem.num +1, elem.dist + 1));
            if(elem.num * 2 < MAX && !isVisited[elem.num * 2])
                queue.offer(new Element(elem.num * 2, elem.dist + 1));
        }

        System.out.println(targetDist + " " + targetCnt);
    }

    static class Element{
        int num;
        int dist;

        public Element(int num, int dist){
            this.num = num;
            this.dist = dist;
        }
    }
}
```

## 문제 풀이
BFS를 통해서 중복 방문을 허용하여 문제는 해결합니다.

BFS를 사용해서 풀었던 문제들과 다른 점은 일단 방문 표시를 큐에서 꺼낸 다음에 하는 것입니다.

```java
    if(targetDist < elem.dist)
        continue;
```
- 현재까지 움직인 횟수가 목표지점에 도달하기 위해 움직인 횟수보다 크다면 답에 영향을 미치지 않기에 더 이상 진행하지 않는다.
- 만약에 무진장 앞으로 갔을 때(*2가 여러번) -1을 또 다시 여러번 해서 목표지점에 도달했다고 치자. 아주 오래걸렸다고 치자!
   - 근데 BFS를 통해서 가장 최단 거리를 찾기 때문에 타겟을 찾지 못한 상태에서는 지금 찾은 값이 최소 거리가 될 것이다.
   - 그러면 방금 찾은 거리가 범위의 최소가 될 것이고, 이를 넘어거는 탐색 횟수들은 무시할 수 있게 된다.

```java
if(elem.num == M){
    targetDist = Math.min(elem.dist, targetDist);
    targetCnt += 1;
    continue;
}
```
- 목표지점에 도달한 경우 가장 작은 값으로 갱신!

## 후기
DP가 있길래 배열로 슬라이딩 윈도우로 어케 풀지하다가 결국엔 BFS로 푸는 방법으로 하다가 

답 찾아보고 공부하고 해결했숩니다!

2020 안녕~