import java.io.*;
import java.util.*;

public class Main {
	static class Node{
		int to, weight;
		Node(int to, int weight){
			this.to = to; this.weight = weight;
		}
	}
	
	static int V, E;
	static List<Node>[] graph;
	public static void main(String[] args) throws Exception {
		input();
		
		// Prim's Alg
		System.out.println(prim());
	}
	
	public static long prim() {
		boolean[] v = new boolean[graph.length];
		int[] minEdge = new int[graph.length];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		int minVertex;
		long min, result = 0;
		minEdge[1] = 0;
		
		for(int c = 1;  c < graph.length; c++) {
			min = Long.MAX_VALUE;
			minVertex = 0;
			
			for(int i = 0; i < graph.length; i++) {
				if(!v[i] && minEdge[i] < min) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			result += min;
			v[minVertex] = true;
			
			for(int i = 0; i < graph[minVertex].size(); i++) {
				Node next = graph[minVertex].get(i);
				if(!v[next.to] && next.weight < minEdge[next.to]) {
					minEdge[next.to] = next.weight;
				}
			}
		}
		
		return result;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
		graph = new List[V + 1];
		for(int i = 1; i <= V; i++) graph[i] = new ArrayList<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, weight));
			graph[to].add(new Node(from, weight));
		}
	}
}