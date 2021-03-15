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