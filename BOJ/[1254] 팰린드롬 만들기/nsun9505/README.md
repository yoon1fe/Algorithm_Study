# [1254] 팰린드롬 만들기 - JAVA

## 분류
> 완전탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static Set<Character> chars = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string = br.readLine();
		for(int i=0; i<string.length(); i++)
			chars.add(string.charAt(i));
		
		int ans = string.length();
		for(int i=0; i<=string.length(); i++) {
			if(isPalindrome(string + getReverse(string.substring(0,i)))) {
				System.out.println(ans + i);
				break;
			}
		}
	}
	
	public static boolean isPalindrome(String str) {
		for(int i=0, j=str.length()-1; i<str.length(); i++, j--) {
			if(str.charAt(i) != str.charAt(j))
				return false;
		}
		return true;
	}
	
	public static String getReverse(String str) {
		String ret = "";
		for(int i = str.length()-1; i>=0; i--)
			ret += String.valueOf(str.charAt(i));
		return ret;
	}
}
```

## 문제 풀이
입력 받은 문자열에서 0~i(i = 0, 1, ... , N)번째까지의 문자열을 추출합니다.(N은 입력 받은 문자열의 길이)

그리고 역전시켜서 기존 입력 받은 문자열에 붙여서 팰린드롬이 맞는지 확인해보면 됩니다.

## 후기
간단한데 너무 어렵게 생각했네용..ㅎ