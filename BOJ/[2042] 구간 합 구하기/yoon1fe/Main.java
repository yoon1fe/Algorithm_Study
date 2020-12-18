import java.io.*;
import java.util.*;

class SegmentTree {
	long[] tree;
	
	SegmentTree(int N, long[] list) {
		tree = new long[N*4];
		init(0, N-1, 1, list);
	}
	
	/**
	 * @param start - 시작 인덱스
	 * @param end - 끝 인덱스
	 */
	long init(int start, int end, int node, long[] list) {
		// 리프 노드
		if(start == end) return tree[node] = list[start];
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(start, mid, node*2, list) + init(mid+1, end, node*2 + 1, list);
	}

	/**
	 * @param left, right - 구간 합 구하고자 하는 범위
	 * @return - 구간 합
	 */
	long sum(int node, int start, int end, int left, int right) {
		// 범위를 벗어난 경우 종료
		if(left > end || right < start) return 0;
		// 범위 안에 start - end 가 포함된 경우 node의 자식도 모두 포함되기 때문에 tree[node] 리턴
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		// 위 두 경우가 아닌 경우 자식 노드들 탐색
		return sum(node*2, start, mid, left, right) + sum(node*2 + 1, mid+1, end, left, right);
	}
	
	/**
	 * @param idx - 수정할 인덱스
	 * @param diff - 바뀐 정도(차)
	 */
	void update(int node, int start, int end, int idx, long diff) {
		// 범위를 벗어난 경우 종료
		if(idx < start || end < idx) return;
		// 노드 찾아 내려가면서 갱신
		tree[node] += diff;
		// 리프 노드가 아닌 경우 자식 노드도 갱신
		if(start != end) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, idx, diff);
			update(node * 2 + 1, mid+1, end, idx, diff);
		}
	}
}

public class Main {
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		input();
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		
		long[] list = new long[N];
		
		for(int i = 0; i < N; i++) {
			list[i] = Long.parseLong(br.readLine());
		}
		
		// segment tree 초기화
		SegmentTree segTree = new SegmentTree(N, list);

		while(K-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			long c = Long.parseLong(st.nextToken());
			
			switch(a) {
			case 1: 
				segTree.update(1, 0, N-1, b, c - list[b]); 
				list[b] = c;
				break;
			case 2: sb.append(segTree.sum(1, 0, N-1, b, (int)(c-1))).append("\n"); break;
			}
		}
		
		System.out.println(sb.toString());
	}
}