# [2696] 중앙값 구하기

## 분류
> 우선순위 큐

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		solution();
	}
	
	public static void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			PriorityQueue<Integer> minPq = new PriorityQueue<>();
			PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
			
			int M = Integer.parseInt(br.readLine());
			if(M <= 10) M = 1;
			else M = (M / 10) + 1;
			
			int cnt = 1;
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int len = st.countTokens();
				
				for(int j=0; j<len; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(maxPq.size() == minPq.size())
						maxPq.offer(num);
					else
						minPq.offer(num);
					
					if(!minPq.isEmpty() && !maxPq.isEmpty() && minPq.peek() < maxPq.peek()) {
						int min = maxPq.poll();
						int max = minPq.poll();
						
						maxPq.offer(max);
						minPq.offer(min);
					}
					
					if(cnt++ % 2 == 1)
						list.add(maxPq.peek());
				}
			}
			System.out.println(list.size());
			for(int i=1; i<=list.size(); i++) {
				System.out.print(list.get(i-1) + " ");
				if(i % 10 == 0) System.out.println();
			}
			if(list.size() < 10)
				System.out.println();
		}
	}
}
```

## 문제 풀이
보자마자 전에 풀었던 문제가 생각이 났고, MinHeap과 MaxHeap으로 풀면되겠다는 것을 알았습니다.

1. minPq와 maxPq의 사이즈를 비교해서 같으면 maxPq에 넣고, 다르면 minPq에 넣습니다.
   - 그러면 매번 데이터를 넣고 나면 maxPq와 minPq는 크기가 1차이가 납니다.
1. 만약 minPq, maxPq 둘 다 Empty가 아니고, minPq의 top값이 maxPq의 top값보다 작다면 서로의 top 값을 변경합니다.
   - 변경해줌으로써 중앙값을 제대로 찾을 수 있습니다.
1. 출력은 홀수번째마다 list에 담은 뒤에, for문이 끝나면 list의 크기를 출력하고, list에 있는 원소들을 출력하다가 10번째마다 개행을 해주면 끝!


## 후기
이번에는 답을 보지말고 해보자고 열심히 손으로 풀어봤지만 길이가 다를 때 어떻게 넣어줘여 한다는 거만 알고 헤매다가 풀었던거 보고 해결 ㅠ

이래서 복습이 중요한 것 같습니다.