# [1477] 휴게소 세우기 - JAVA

## 분류
> 이분탐색
>
> 우선순위 큐

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	public static void solution() {
		int start = 1;
		int end = L+1;
		while(start < end) {
			int mid = (start + end) / 2;
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				if(arr[i] - arr[i-1] <= mid)
					continue;
				cnt += (arr[i]-arr[i-1]-1)/mid;
			}
			if(cnt <= M)
				end = mid;
			else
				start = mid + 1;
		}
		System.out.println(start);
	}
	
	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N+2];
		arr[0] = 0;
		arr[N+1] = L;
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
	}
}
```

## 문제 풀이
[출처](https://github.com/encrypted-def/BOJ/blob/master/1477.cpp)
<br>

1. 입력 받은 휴게소 위치를 정렬!
   - 처음 부분과 끝 부분에 0, L을 추가하여 마지막과 처음 부분에 휴게소를 추가하지 못하도록!
1. 파라메트릭 서치를 통해서 간격이 mid 이하이기 위해 필요한 추가 휴게소의 개수를 구한다.
   - cnt에 (arr[i]-arr[i-1]-1)/mid를 해서 추가할 수 있는 휴게소의 개수를 구함.
   - 그리고 cnt가 M과 같거나 작다면 end를 mid로 조정하여 다시 이분탐색!
   - cnt가 M보다 크가면 start를 mid + 1로 조정하여 다시 이분탐색!
1. 결과는 start으로 한 곳이 최솟값이 된다.

적고도 이게 무슨 소린지.. 참..

이분탐색 공부 필!!!!

## 후기
이분 탐색을 좀 많이 아니 더 많이 풀어봐야할 것 같습니다..

파라메트릭 서치로 풀면 된다고 하는데.. 흐음
- 솔직히 처음 들어봐서... 공부하겠습니다!
- 최적화 문제를 결정 문제로 바꾸어 푸는 것이라는데.. 후움 ㅎㅎ