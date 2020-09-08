package week05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class week05_동굴탐험 {
	ArrayList<Integer>[] adjList;
	ArrayList<Integer> st = new ArrayList<Integer>();
	int[] indegree;
	
	void makeGraph(int n, int[][] order) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[n];
		q.offer(0);
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int p = q.poll();
			
			for(int i=adjList[p].size()-1; i>=0; i--) { 
				if(!visited[adjList[p].get(i)]) {
					visited[adjList[p].get(i)] = true;
					q.offer(adjList[p].get(i));
					adjList[p].remove(i);
				}
			}
		}
		
		for(int i=0; i<order.length; i++)
			adjList[order[i][1]].add(order[i][0]);
	}
	
	public boolean solution(int n, int[][] path, int[][] order) {
        adjList = new ArrayList[n];
        for(int i=0; i<n; i++) adjList[i] = new ArrayList<Integer>();
        for(int i=0; i<path.length; i++) {
        	adjList[path[i][0]].add(path[i][1]);
        	adjList[path[i][1]].add(path[i][0]);
        }
        makeGraph(n, order);
        indegree = new int[n];
        
        for(int i=0; i<n; i++)
        	for(int j=0; j<adjList[i].size(); j++)
        		indegree[adjList[i].get(j)]++;
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=0; i<n; i++)
        	if(indegree[i] == 0) q.offer(i);
        int cnt = 0;
        while(!q.isEmpty()) {
        	int p = q.poll();
        	cnt++;
        	for(int i=0; i<adjList[p].size(); i++) {
        		indegree[adjList[p].get(i)]--;
        		if(indegree[adjList[p].get(i)] == 0)
        			q.offer(adjList[p].get(i));
        	}
        }
        if(cnt != n) return false;
        return true;
    }
}