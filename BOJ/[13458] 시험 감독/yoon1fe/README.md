## [13458]  시험 감독 - Java

### :memo: 분류

> 시뮬레이션



### :memo: 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] students = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i =0 ; i< N; i++) students[i] = Integer.parseInt(st.nextToken());			
		st = new StringTokenizer(br.readLine(), " ");

		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long ans = N;
		
		for(int i = 0; i < N; i++) {
			students[i] -= B;
			if(students[i] <= 0) continue;
			if(students[i] % C == 0) ans+= students[i] / C;
			else ans += (students[i] / C) + 1;
		}
		System.out.println(ans);
	}
}
```



### :memo: 풀이 방법

기본적인 로직은 어렵지 않은 문제입니다. 다만 변수에 들어갈 수 있는 최댓값을 고려해서 자료형을 정해줘야 합니다.

맨 처음 풀 때 각 교실마다 반복문을 돌려서 빼주면서 카운트를 셌더니 **시간 초과가** 떴습니다.

그래서 **나눈 몫**을 구해서 답을 구해줬습니다.

이 때 만약 남은 학생 수가 `C`로 나누어 떨어진다면 **나뉘는 `몫`**을, 그렇지 않다면 **`몫 + 1` 이** 되어야 합니다.



### :memo: 후기

어떤 변수에 들어갈 수 있는 최댓값을 잘 생각하고 범위를 벗어나지 않도록 변수 자료형을 정하는데 더 신경을 써야겠습니다.