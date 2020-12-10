## [2696] 중앙값 구하기 - Java

### :two: 분류

> 우선순위 큐



### :three: 코드

```java
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		input();

	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			int M = Integer.parseInt(br.readLine());
			int cnt = 0;

			System.out.println((M+1)/2);	// 중앙값 개수
			
			for (int i = 0; i < M; i++) {
				if(i % 10 == 0) {
					st = new StringTokenizer(br.readLine(), " ");
				}
				
				int n = Integer.parseInt(st.nextToken());
				
				if(maxHeap.size() == minHeap.size()) {
					maxHeap.offer(n);
				} else {
					minHeap.offer(n);
				}
				
				// maxHeap에는 중간값 이하의 수만 존재하도록
				if(!minHeap.isEmpty()) {
					if(maxHeap.peek() > minHeap.peek()) {
						int t1 = maxHeap.poll();
						int t2 = minHeap.poll();
						
						maxHeap.offer(t2);
						minHeap.offer(t1);
					}
				}
				
				if(i % 2 == 0) {
					if(cnt == 9 || i == M - 1) {
						System.out.println(maxHeap.peek());
						cnt = 0;
					}else {
						System.out.print(maxHeap.peek() + " ");
					}
					cnt++;
				}
			}
		}
	}
}
```



### :five: 풀이 방법

먼젓번에 풀었던 [가운데를 말해요](https://yoon1fe.tistory.com/166?category=899857) 랑 비슷한 문제입니다. 다만 요번에는 우선순위 큐를 이용해서 풀었씁니다.



중앙값을 갖기 위해 두 개의 우선순위 큐가 필요합니다. 하나는 최대 힙, 하나는 최소 힙입니다.

최대 힙에는 중앙값 이하의 수만, 그리고 최소 힙에는 중앙값 초과의 수만 존재하게 합니다. 그럼 최대 힙의 root에는 항상 중앙값이 존재하게 되는 것이죠.

 

먼저 숫자를 입력받을 때 각각 최대 힙, 최소 힙에 하나씩 넣는 식으로 넣어줍니다. 그러고 각각의 peek() 에 있는 수를 비교해가며 minHeap.peek() (= 가장 작은 값, a) 이 maxHeap.peek() (= 가장 큰 값, b) 보다 작은 경우 중앙값은 a가 되기 때문에 이 둘을 바꿔주는 것입니다.

 

입출력 조건에 한 줄에 10개씩 입출력되는 얄궂은 조건이 있습니다. 조심하세여.



### :seven: 후기

이제 좀 쉬려고 했지만 코테 준비를 위해!!

감사합니다!!

