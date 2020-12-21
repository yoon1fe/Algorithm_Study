## [1254] 팰린드롬 만들기수 - Java

### :one: 분류

> 완전 탐색



### :one: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println(solve(input()));
	}
	
	public static int solve(String input) {
		// 입력받은 문자열이 이미 팰린드롬일때
		if(input.equals(new StringBuilder(input).reverse().toString())) {
			return input.length();
		}
		
		for(int i = 1; i < input.length(); i++) {
			StringBuilder sb = new StringBuilder(input);

			// 앞에서부터 길이 1씩 늘려가면서 뒤에 붙여보자
			sb.append(new StringBuilder(input.substring(0, i)).reverse());
			
			// 팰린드롬인지 체크
			if(sb.toString().equals(sb.reverse().toString())) {
				return sb.length();
			}
		}

		return -1;
	}
		
	public static String input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
}
```



### :one: 풀이 방법

입력받은 문자열 뒤에 문자열을 추가해서 팰린드롬을 만드는 문제입니다. 

맨 뒤에만 붙이면 되기 때문에 그리 어렵지 않게 풀 수 있습니다.



먼저 팰린드롬이란 무엇이냐

앞뒤가 똑같은 전화본호 같은겁니다.

슈퍼주니어 로꾸거같은거요

 

암튼 요걸 만들면 됩니다. 그럼 우째 만들것이냐??

앞에 있는 친구들을 뒤집어서 반대로 붙여보면 됩니다.

 

문자열을 뒤집는 메소드 reverse()를 쓰기 위해서 StringBuilder로 바꿔서 사용했습니다!!

만약 "abcd" 이런 식으로 온다면 결국 뒤에 "abc"의 반대인 "cba"를 붙여서 "abcdcba"가 되겠죵. 따라서 -1을 리턴할 일은 없겠습니다 하하



### :one: 후기

아주 간단한 문제!!

감사합니다!!