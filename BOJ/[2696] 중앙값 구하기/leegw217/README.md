# [2696] 중앙값 구하기 - Java

###  :octocat: 분류

> 우선순위 큐

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class week19_중앙값구하기 {
	static List<Integer> answer;
	static PriorityQueue<Integer> maxHeap;
	static PriorityQueue<Integer> minHeap;
	
	static void input(int n) {
		if(maxHeap.size() == minHeap.size()) {
			maxHeap.offer(n);
			if((!maxHeap.isEmpty() && !minHeap.isEmpty()) && 
					maxHeap.peek() < minHeap.peek()) {
				int temp = minHeap.poll();
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(temp);
			}
			answer.add(maxHeap.peek());
		} else {
			minHeap.offer(n);
			if((!maxHeap.isEmpty() && !minHeap.isEmpty()) &&
					maxHeap.peek() < minHeap.peek()) {
				int temp = minHeap.poll();
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(temp);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			answer = new ArrayList<Integer>();
			maxHeap = new PriorityQueue<Integer>(
					new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o1 - o2;
						}
			});
			minHeap = new PriorityQueue<Integer>(
					new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2 - o1;
						}
			});
			int q = N / 10;
			int r = N % 10;
			for(int i=0; i<q; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<10; j++) {
					int n = Integer.parseInt(st.nextToken());
					input(n);
				}
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<r; i++) {
				int n = Integer.parseInt(st.nextToken());
				input(n);
			}
			System.out.println(N/2 + 1);
			for(int i=0; i<answer.size(); i++) {
				System.out.print(answer.get(i)+" ");
				if((i+1) % 10 == 0) System.out.println();
			}
			System.out.println();
		}
	}
}
```

### :octocat: 풀이 방법

1. maxHeap과 minHeap을 우선순위 큐로 만든다.
2. 숫자를 하나씩 읽어오면서 maxHeap과 minHeap 사이즈가 같으면 maxHeap에 넣고
다르면 minHeap에 값을 넣는다.
3. 값을 넣고 maxHeap의 최댓값과 minHeap의 최솟값을 비교해 최솟값이 더 크면 둘을 바꿔준다.
4. maxH와 minH 사이즈가 같으면 홀수번째기 때문에 maxH의 최댓값이 중간값이 되고 answer에 넣어준다.

### :octocat: 후기

전에 풀었던 가운데를 말해요랑 거의 흡사한 문제라 금방 풀었다! 근데 재체점 때문에 가운데를 말해요 틀렸던데
다시 풀어야할려나..
