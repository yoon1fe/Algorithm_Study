# [12015] 가장 긴 증가하는 부분 수열 2 - Java

###   :ballot_box_with_check:분류

> 이분 탐색



:ballot_box_with_check:코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer> sequence = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		input();

		System.out.println(sequence.size() - 1);
	}	
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sequence.add(0);
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(sequence.get(sequence.size() - 1) < num) sequence.add(num);
			// 넣을 인덱스 구해서 넣기
			else binarySearch(num, 0, sequence.size() - 1);
		}
	}
	
	public static void binarySearch(int num, int left, int right) {
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(sequence.get(mid) >= num) right = mid;
			else left = mid + 1;
		}
		sequence.set(right, num);
	}
	
}
```



### :ballot_box_with_check: 풀이 방법

PS에서 유명한 시리즈인 LIS 를 구하는 문제입니다.

보통 DP로 많이 푸는데 이 문제는 인풋 사이즈가 커서 N^2을 돌리면 터집니다.



인풋을 받으면서 sequence 리스트에 바로 LIS를 만들어 줄겁니다. 먼저 가장 작은 수인 0을 넣고 시작합니다.

인풋을 받을 때 해야 하는 동작은 크게 두 가지 입니다.

1. sequence 리스트의 가장 오른쪽에 있는 수보다 큰 수가 들어오면 바로 뒤에 넣어줍니다. 결국 오름차순인 리스틀르 만드는거니깐여.
2. 그렇지 않다면 이분 탐색으로 넣을 자리를 구합니다. 구한 위치의 값을 새로 들어온 값으로 갱신하면 됩니다.

인풋을 다 받으면 sequence의 크기 - 1이 정답이 되겠져. 왜냐면 맨 첨에 0을 넣어놨으니깐



###  :ballot_box_with_check:후기

사실 혼자 힘으로 풀지 못했씁니다.. 이분 탐색 연습을 많이 해야겠습니당.

감사합니다!~!