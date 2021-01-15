import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week22_구간곱구하기 {
	static long[] number;
	static long[] tree;
	
	static long init(int node, int start, int end) {
		if(start == end) return tree[node] = number[start];
		else return tree[node] = init(node*2,start,(start+end)/2) * init(node*2+1,(start+end)/2+1,end) % 1000000007;
	}
	
	static long multi(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 1;
		if(left <= start && end <= right) return tree[node];
		return multi(node*2, start, (start+end)/2, left, right) * multi(node*2+1, (start+end)/2+1, end, left, right) % 1000000007;
	}
	
	static void update(int node, int start, int end, int index, long diff) {
		if(index < start || index > end) return;
		if(start == end) {
			if(start == index) tree[node] = diff;
			return;
		}
		update(node*2, start, (start+end)/2, index, diff);
		update(node*2+1, (start+end)/2+1, end, index, diff);
		tree[node] = tree[node*2] * tree[node*2+1] % 1000000007;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		number = new long[N+1];
		tree = new long[20000000];
		for(int i=1; i<=N; i++) number[i] = Long.parseLong(br.readLine());
		init(1, 1, N);
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				update(1, 1, N, b, c);
				number[b] = c;
			}
			else System.out.println(multi(1, 1, N, b, (int)c) % 1000000007);
		}
	}
}