## [2042] 구간 합 구하기 - Java

### :two: 분류

> 세그먼트 트리



### :three: 코드

```java
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
}import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		input();
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		while(N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			System.out.println(solve(num));
		}
	}
	
	public static int solve(int n) {
		// 이분탐색으로 정렬된 리스트 구하기
		int left = 0, right = list.size() - 1, mid = (left + right) / 2;
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(list.get(mid) > n) {
				right = mid - 1;
			}else {
				left = mid + 1;
				mid++;
			}
		}
		
		list.add(mid, n);
		
		return list.size() % 2 == 0 ? list.get((list.size() / 2) - 1) : list.get(list.size() / 2);
	}
}
```



### :five: 풀이 방법

세그먼트 트리를 공부하고 풀기 좋은 첫 문제입니다.

그래도 아직 어렵네여 ㅜ



이 문제는 prefix sum을 이용해서 구간합을 구할 수 없습니다. 있긴 한데 list의 숫자를 바꿔주는 연산이 있기 때문에 O(N)씩 보면서 갱신해나가는 오버헤드가 크기 때문에 통과할 수 없겠지요.

 

우선 세그 트리는 이진 트리로 표현합니당. SegmentTree 란 클래스로 나타내보았습니다.

다음은 메소드들에 대한 설명입니다.

- init() - long[] tree 초기화하는 메소드
- sum() - 주어진 범위 구간의 합을 구하는 메소드
- update() - l주어진 인덱스의 값과 tree를 갱신하는 메소드

세 메소드 모두 큰 로직은 비슷합니다. 기저 파트를 정의하고 이진 트리니깐 왼쪽 - 오른쪽 범위를 정의해서 재귀적으로 수행합니당.

update() 해야 할 때 별도로 list[] 의 값도 갱신해주어야 하는 것을 조심합시다!!!

 

### :seven: 후기

세그먼트 트리에 대해서는 다음번에 자세히 공부하고 정리해야겠습니다.

문제도 많이 풀어봐야겠습니다...

감사합니다!!!