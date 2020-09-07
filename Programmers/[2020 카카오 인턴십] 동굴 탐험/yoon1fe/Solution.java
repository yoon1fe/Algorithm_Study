package Programmers;

import java.util.*;

public class Solution {
	public static 
	boolean solution(int n, int[][] path, int[][] order) {
        List<Integer>[] graph = new ArrayList[n];
        
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        
        for(int i = 0; i < path.length; i++) {			// path 배열로 그래프 생성
        	graph[path[i][0]].add(path[i][1]);
        	graph[path[i][1]].add(path[i][0]);
        }
        
        return topological_sort(bfs(graph), order);		// bfs 돌아서 그래프를 방향있는 그래프로 바꾼 뒤에 order를 붙여서 topological sort
    }
	
	public static List<Integer>[] bfs(List<Integer>[] graph) {
		Queue<Integer> q = new LinkedList<>();
		List<Integer>[] directedGraph = new ArrayList[graph.length];
		for(int i = 0; i < directedGraph.length; i++) directedGraph[i] = new ArrayList<>();
		boolean[] v = new boolean[directedGraph.length];
		
		q.offer(0);
		v[0] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(v[next]) continue;
				
				directedGraph[cur].add(next);
				v[next] = true;
				q.offer(next);
			}
		}
		return directedGraph;
	}
	
	
	public static boolean topological_sort(List<Integer>[] graph, int[][] order) {
        Queue<Integer> q = new LinkedList<>();
        int[] inDegree = new int [graph.length];
        
        for(int i = 0; i < graph.length; i++) {
        	for(Integer next : graph[i]) {
        		inDegree[next]++;
        	}
        }
        
		for (int i = 0; i < order.length; i++) {
			graph[order[i][0]].add(order[i][1]);
			inDegree[order[i][1]]++;
		}
        
        for(int i = 0; i < graph.length; i++) {
        	if(inDegree[i] == 0) q.offer(i);
        }
        
        int cnt = 0;
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	
//        	System.out.print(cur + " ");
        	cnt++;
        	
        	for(int i = 0; i < graph[cur].size(); i++) {
        		int next = graph[cur].get(i);
        		
        		if(--inDegree[next] == 0) {
        			q.offer(next);
        		}
        	}
        }
        
        return cnt == graph.length ? true : false;			// 싸이클이 없는 경우 graph.lengh 만큼 나옴
	}

	public static void main(String[] args) {
		int n = 9;
		int[][] p = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
//		int[][] p = {{8,1},{0,1},{1,2},{0,7},{4,7},{0,3},{7,5},{3,6}};
//		int[][] p = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
		
		int[][] d = {{8,5},{6,7},{4,1}};
//		int[][] d = {{4,1},{5,2}};
//		int[][] d = {{4,1},{8,7},{6,5}};
		System.out.println(solution(n, p, d));
		
	}
}