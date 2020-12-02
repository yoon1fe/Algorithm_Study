# [1655] 가운데를 말해요 - Java

###  :octocat: 분류

> 우선순위 큐

### :octocat: 코드

```java
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
public class week14_가운데를말해요 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		for(int i=0; i<N; i++) {
			int num = sc.nextInt();
			
			if(maxHeap.size() == minHeap.size()) maxHeap.offer(num);
			else minHeap.offer(num);
			
			if(!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if(maxHeap.peek() > minHeap.peek()) {
					int temp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(temp);
				}
			}
			System.out.println(maxHeap.peek());
		}
	}
}
```

### :octocat: 풀이 방법

1. maxHeap과 minHeap을 우선순위 큐를 이용해 만든다.
2. maxHeap과 minHeap의 사이즈가 같으면 입력받은 값을 maxHeap에 넣고
다르면 minHeap에 넣는다.
3. maxHeap의 top에 있는 수와 minHeap의 top에 있는 수를 비교해 maxHeap의 top의
값이 더 크면 둘을 스왑한다.
4. maxHeap에는 중간위치값보다 작거나 같은수들이 있고 minHeap에는 중간위치값보다
큰거나 같은수들이 있다. 
5. 입력받을 때마다 연산하고 maxHeap의 top을 출력하면 그게 중간위치 값이 된다.

### :octocat: 후기

처음 배열을 두개 만들어서 작은값과 큰값을 분류하고 정렬할려고 했는데 생각해보니 거기 딱
맞는게 우선순위 큐!
