package BOJ_2042;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long[] arr;
	static long[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new long[N+1];
		
		for(int i=1; i<=N; i++)
			arr[i] = Long.parseLong(br.readLine());
		
		int height = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
		int size = (int)Math.pow(2, height);
		tree = new long[size];
		
		makeSegTree(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int cond = Integer.parseInt(st.nextToken());
			
			if(cond == 1) {
				int index = Integer.parseInt(st.nextToken());
				long newValue = Long.parseLong(st.nextToken());
				long diff = newValue - arr[index];
				arr[index] = newValue;
				update(index, 1, 1, N, diff);
			} else {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				sb.append(find(1, 1, N, left, right) + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	
	public static long find(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return 0;
		if(left <= start && end <= right)
			return tree[node];
		int mid = (start+end)/2;
		return find(node*2, start, mid, left, right)
				+ find((node*2)+1, mid+1, end, left, right);
	}
	
	public static void update(int index, int node, int start, int end, long diff) {
		if(index < start || index > end)
			return;
		
		tree[node] += diff;
		if(start == end)
			return;
		
		int mid = (start+end)/2;
		update(index, node*2, start, mid, diff);
		update(index, node*2 + 1, mid + 1, end, diff);
	}
	
	public static long makeSegTree(int node, int start, int end) {
		if(start == end)
			return tree[node] = arr[start];
		int mid = (start+end)/2;
		return tree[node] = makeSegTree(node * 2, start, mid) 
				+ makeSegTree(node*2 + 1, mid + 1, end);
	}
}
