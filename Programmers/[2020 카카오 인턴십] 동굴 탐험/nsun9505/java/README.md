# [2020 카카오 인턴십] 동굴 탐험 - JAVA

## 분류
> BFS

## 코드
```java
import java.util.*;

public class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0; i<graph.length; i++)
            graph[i] = new ArrayList<>();

        HashMap<Integer, Integer> orders = new HashMap<>();
        HashMap<Integer, Integer> readyMap = new HashMap<>();
        for(int i=0; i<order.length; i++)
            orders.put(order[i][1], order[i][0]);

        if(orders.containsKey(0) && orders.get(0) != 0)
            return false;

        for(int i=0; i<path.length; i++){
            graph[path[i][0]].add(path[i][1]);
            graph[path[i][1]].add(path[i][0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[0] = true;
        queue.offer(0);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            if(readyMap.containsKey(cur)){
                int next = readyMap.get(cur);
                visited[next] = true;
                queue.offer(next);
            }

            for(int next : graph[cur]){
                if(visited[next])
                    continue;

                if(orders.containsKey(next)){
                    int prev = orders.get(next);
                    if(!visited[prev]) {
                        readyMap.put(prev, next);
                        continue;
                    }

                    visited[next] = true;
                    queue.offer(next);
                } else {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        for(int i=0; i<visited.length; i++)
            if(!visited[i])
                return false;
        return true;
    }
}
```

## 문제 풀이
약간 위상정렬 같은 느낌이였지만, order가 인접한 방이 아닐 수도 있으므로 위상정렬로 푸는 것은 아닌거 같습니다.

그래서 BFS로 방문할 수 있는 방들은 무조건 방문을 합니다!

그러다가 아직 조건 충족이 되지 않아 방문을 하지 못하는 애들은 readyMap에 넣어서 방문을 하려고 헀지만! 조건이 충족되지 않아 방문하지 못한 방으로 남겨둡니다.

queue에서 pop한 방이 readyMap에 있는 방 중에 어떤 방의 먼저 방문해야 하는 방이라면 readyMap에서 현재 방문한 방을 키 값으로 현재 방을 방문하였을 때 방문할 수 있는

방을 queue에 넣어줍니다!

이렇게 진행해서 모든 방을 방문했다면 true를 리턴하고, 아직 방문하지 못한 방이 있다면 false를 리턴합니다.

## 후기
푸는 방법이 머리에 남아 있어요.. 어떡하죠..

여기서 어떻게 하면 될까 하다가 아 이렇게 하면 되겠다가 예전에 했던 방식이랑 비슷하네유..