import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static int[] maxTree;
	static int[] minTree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		int height = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
		int size = (int)Math.pow(2, height);
		maxTree = new int[size];
		minTree = new int[size];
		
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		makeMaxTree(1, 1, N);
		makeMinTree(1, 1, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			sb.append(findMin(1, 1, N, left, right) + " " + findMax(1, 1, N, left, right) + "\n");		
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int findMax(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return 0;
		if(left <= start && end <= right)
			return maxTree[node];
		int mid = (start+end)/2;
		int leftMax = findMax(node*2, start, mid, left, right);
		int rightMax = findMax(node*2+1, mid+1, end, left, right);
		return leftMax < rightMax ? rightMax : leftMax;
	}
	
	public static int findMin(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return Integer.MAX_VALUE;
		if(left <= start && end <= right)
			return minTree[node];
		int mid = (start+end)/2;
		int leftMin = findMin(node*2, start, mid, left, right);
		int rightMin = findMin(node*2+1, mid+1, end, left, right);
		return leftMin < rightMin ? leftMin : rightMin;
	}
	
	public static int makeMaxTree(int node, int start, int end) {
		if(start == end)
			return maxTree[node] = arr[start];
		int mid = (start+end) / 2;
		int left = makeMaxTree(node*2, start, mid);
		int right = makeMaxTree(node*2+1, mid+1, end);
		return maxTree[node] = left < right ? right : left;
	}
	
	public static int makeMinTree(int node, int start, int end) {
		if(start == end)
			return minTree[node] = arr[start];
		int mid = (start+end) / 2;
		int left = makeMinTree(node*2, start, mid);
		int right = makeMinTree(node*2+1, mid+1, end);
		return minTree[node] = left < right ? left : right;
	}
}