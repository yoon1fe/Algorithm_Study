import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] list, maxTree, minTree;
	
	public static void main(String[] args) throws Exception {
		input();
	}
	
	public static int initMax(int node, int start, int end) {
		if(start == end) return maxTree[node] = list[start];
		
		int mid = (start + end) / 2;
		
		return maxTree[node] = Math.max(initMax(node*2, start, mid), initMax(node*2 + 1, mid+1, end));
	}
	
	public static int initMin(int node, int start, int end) {
		if(start == end) return minTree[node] = list[start];
		
		int mid = (start + end) / 2;
		
		return minTree[node] = Math.min(initMin(node*2, start, mid), initMin(node*2 + 1, mid+1, end));
	}
	
	public static int findMax(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 0;

		if(left <= start && end <= right) return maxTree[node];
		
		int mid = (start + end) / 2;
		
		return Math.max(findMax(node*2, start, mid, left, right), findMax(node*2 + 1, mid+1, end, left, right));
	}
	
	public static int findMin(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 1000000001;

		if(left <= start && end <= right) return minTree[node];
		
		int mid = (start + end) / 2;
		
		return Math.min(findMin(node*2, start, mid, left, right), findMin(node*2 + 1, mid+1, end, left, right));
	}
		
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new int[N];
		maxTree = new int[N*4];
		minTree = new int[N*4];
		
		for(int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		initMax(1, 0, N-1);
		initMin(1, 0, N-1);
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			bw.write(String.valueOf(findMin(1, 0, N-1, a, b)));
			bw.write(" ");
			bw.write(String.valueOf(findMax(1, 0, N-1, a, b)));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}