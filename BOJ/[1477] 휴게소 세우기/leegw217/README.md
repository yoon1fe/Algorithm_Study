# [1477] 휴게소 세우기 - Java

###  :octocat: 분류

> 이분탐색

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class week19_휴게소세우기 {
	static int N, M, L;
	static int[] restArea;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		restArea = new int[N+2];
		st = new StringTokenizer(br.readLine());
		restArea[0] = 0;
		for(int i=1; i<=N; i++) restArea[i] = Integer.parseInt(st.nextToken());
		restArea[N+1] = L;
		Arrays.sort(restArea);
		int left = 0;
		int right = 0;
		for(int i=1; i<=N+1; i++) right = Math.max(right, restArea[i]-restArea[i-1]+1);
		while(left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;
			
			for(int i=1; i<N+2; i++) 
				if(restArea[i-1] < restArea[i])
					sum += (restArea[i] - restArea[i-1] - 1) / mid;
			
			if(sum > M) left = mid + 1;
			else right = mid - 1;
		}
		System.out.println(left);
	}
}
```

### :octocat: 풀이 방법

1. 가장 앞은 0, 가장 뒤는 L인 배열 사이에 휴게소 위치를 오름차순 순으로 넣는다.
2. 시작점을 0, 끝점을 휴게소 사이가 가장 긴 부분으로 해서 중간값을 정한다.
3. 구한 중간값은 설치할 휴게소들 사이의 간격이 된다.
4. 휴게소들 사이에 중간값으로 설정한 간격만큼 휴게소를 설치한다.
5. 만약 설치한 개수가 M보다 많으면 시작점을 다음 휴게소로 옮긴다. 
즉 더 많이 설치됐으니 간격을 늘려 개수를 줄인다.
6. M보다 작거나 같으면 더 설치해야하니 끝점을 한칸 앞으로 옮겨 간격을 줄인다. 
7. 시작점이 끝점보다 커지거나 같아지면 종료하고 시작점 출력

### :octocat: 후기

처음에는 단순하게 가장 큰 간격을 찾아서 반으로 줄이는 작업을 반복해주면 될거라 생각했는데
한 간격에 여러 휴게소를 설치하는 경우를 생각안해서 틀렸다.. 난 이분탐색을 너무 못하는것같다 ㅜ
