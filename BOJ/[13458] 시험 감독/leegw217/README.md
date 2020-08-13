# [13458] 시험 감독 - Java

###  :octocat: 분류

> 시뮬레이션

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class week01_시험감독 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		List<Integer> arr = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr.add(Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long answer = 0;
		for(int i=0; i<N; i++) {
			answer += 1;
			int n = arr.get(i) - B;
			if(n>0) {
				if(n%C > 0) answer += n/C + 1;
				else answer += n/C;
			}
		}
		System.out.println(answer);
	}
}
```

### :octocat: 풀이 방법

N개의 시험장에 들어가는 감독관들의 수를 계산하는 문제입니다.
1. 총감독관은 시험장마다 딱1명씩 들어가기 때문에 각 시험장별 응시생들 수에서 총감독관이 감시할 수 있는 수를 뺍니다.
2. 남은 수가 양수일경우 남은 응시생 수에서 부감독관이 감시할 수 있는 수를 나눈 나머지가 양수면 몫+1, 딱 떨어질 경우 그냥 몫이 부감독관 수가 됩니다.
3. 각 시험장 응시자 수 별로 총감독관 1명과 부감독관 수를 계산해 더해주고 출력하면 끝!

### :octocat: 후기

쉬운 문제였으나 정답의 자료형을 int로 했었어서 틀렸었다... 제한조건을 잘 읽어보고 자료형을 선택하자!
