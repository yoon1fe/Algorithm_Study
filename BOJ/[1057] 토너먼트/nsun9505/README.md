# [1057] 토너먼트 - JAVA

## 분류
> 완전탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[2];
		arr[0] = Integer.parseInt(st.nextToken());
		arr[1] = Integer.parseInt(st.nextToken());
		int ans = 1;
		
		while(true) {
			arr[0] = calcNext(arr[0]);
			arr[1] = calcNext(arr[1]);
			if(arr[0] == arr[1])
				break;
			ans++;
		}
		System.out.println(ans);
	}
	
	public static int calcNext(int num) {
		if(num % 2 == 0)
			return num / 2;
		return num / 2 + 1;
	}
}
```

## 문제 풀이
arr[0]과 arr[1]에 담겨있는 정보는 김XX과 임XX의 번호입니다.

둘은 무조건 이겨서 다음 라운드로 올라가기 때문에 같은 번호를 가지면 이전 라운드에서 만난 것입니다.

그래서 다음 라운드에 진출하여 주어진 번호를 계산하는 것은 
   - 홀수이면 num / 2 + 1, 짝수이면 num / 2가 다음 라운드에서의 번호입니다.
   - 이 번호가 같아지면 while을 멈추고 증가시켰던 ans를 출력하면 끝!

## 후기
오늘도 내일도 파이팅~

메리 크리스마스~