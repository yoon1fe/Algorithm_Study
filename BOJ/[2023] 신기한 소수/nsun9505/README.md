# [2023] 신기한 소수 - JAVA

## 분류
> 백트랙킹

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2023 {
	public static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		int[] arr = {2,3,5,7};
		for(int i : arr) {
			String result = String.valueOf(i);
			solution(1, result);	
		}
		
	}
	
	public static void solution(int cur, String result) {
		if(cur >= N) {
			System.out.println(result);
			return;
		}
		
		for(int i=1; i<10; i+=2) {
			String pnum = result + String.valueOf(i);
			int num = Integer.parseInt(pnum);
			if(isPrimeNumber(num))
				solution(cur+1, pnum);
		}
	}
	
	public static boolean isPrimeNumber(int prime) {
		if(prime <= 1)
			return false;
		
		if(prime%2 == 0)
			return prime == 2 ? true : false;
		
		for(int i = 3; i <=Math.sqrt(prime); i+=2)
			if(prime % i == 0)
				return false;
		return true;
	}
	
	
	public static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	}
}
```

## 문제 풀이
처음에는 에라토스테네스의 체로 풀려다가 메모리가 터져서 더 쉽게 풀기 위해 고민!

1자리에서 소수로 판별나기 위해서는 2,3,5,7 밖에 없기 때문에 main에서 1자리는 2, 3, 5, 7 중 하나를 선택해서 DFS를 돌린다.
- 1자리 이후에 들어갈 수 있는 숫자들은 1, 3, 5, 7, 9만 가능하다! (짝수는 2로 나눠지기 때문에~)
- i번째 자리수에 1, 3, 5, 7, 9를 하나씩 넣어보면서 소수인지 판별한다.
- 소수라면 다음 자리수로 이동하고, 아니라면 1, 3, 5, 7, 9 중에서 아직 선택하지 못한 수가 있다면 그 수를 선택하고 다시 소수 검사를 한다.
- 만약 해당 자릿수에 1, 3, 5, 7, 9를 한 번씩 다 했는데 다 소수가 아니라면 더 이상 진행하지 않고 뒤로 넘어간다.
- 그렇게 자릿수 위치가 N번째까지 가면 해당 소수는 N자리수의 소수이고, 문제에서 원하는 신기한 소수이므로 출력하고 끝낸다.

## 후기
에라토스테네스의 체로 풀면 메모리가 터져유~
