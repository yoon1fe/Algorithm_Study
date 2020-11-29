## [2023] 신기한 소수 - Java

### :two: 분류

> 백트래킹



### :three: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer> num = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		input();
		
		dfs();
	}
	
	public static void dfs() {
		if(num.size() == N) {
			System.out.println(listToInt(num));
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			num.add(i);
			if(isPrime(listToInt(num)) == true) {
				dfs();
			}
			num.remove(num.size() - 1);
		}
	}
	
	public static int listToInt(List<Integer> num) {
		StringBuilder sb = new StringBuilder();
		for(int i : num) sb.append(i);
		
		return Integer.parseInt(sb.toString());
	}
	
	public static boolean isPrime(int num) {
		if(num < 2) return false;
		
		for(int i = 2; i * i <= num; i++) {
			if(num % i == 0) return false;
		}

		return true;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	}
}
```



### :five: 풀이 방법

 

N 자리 신기한 소수들을 구하는 문제입니다. 신기한 소수란 왼쪽부터 1자리, 2자리, ... , N자리 수 모두 소수인 수입니다.

신기하긴 한데 소수를 갖고 논다니 수빈이도 정상은 아닙니다,

 

첫 시도에는 메모리 초과가 떴습니다... ^^;; 

그도 그럴 것이 에라토스테네스?? 맞나??? 의 체를 써서 isPrime 이란 boolean 배열을 만들어서 소수인지를 저장했었는데.. N이 최대 8이니깐 isPrime 의 길이가 무려 1억이 되는 것임니다... 허허

그래서 소수인지 판별하는 메소드를 만들어서 바로 통과했습니다.

 

N 자리인 수만 보면 되니깐 dfs 들어가는 depth가 N 까지만 봐주면 됩니다. 이 때 list에 1부터 값을 넣어주고, list에 든 값을 숫자로 바꿔서 얘가 소수인지를 판별해서 소수인 경우에만 다음 depth로 들어가도록 해줍니다. 

어차피 작은 수부터 보기 때문에 N 자리의 신기한 소수를 찾는 순서가 오름차순이 된답니다.



### :seven: 후기

오랜만에 술술 풀린 DFS ... 

감사합니다...!!