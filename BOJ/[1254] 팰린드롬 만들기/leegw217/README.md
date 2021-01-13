# [1254] 팰린드롬 만들기 - Java

###  :octocat: 분류

> 문자열

### :octocat: 코드

```java
import java.util.Scanner;
public class week21_팰린드롬만들기 {
	static boolean check(String s) {
		int start = 0;
		int end = s.length()-1;
		while(start < end) {
			if(s.charAt(start) != s.charAt(end)) return false;
			start++;
			end--;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		if(check(s)) System.out.println(s.length());
		else {
			for(int i=0; i<s.length(); i++) {
				String t = s.substring(0, i);
				StringBuffer sb = new StringBuffer(t);
				t = s + sb.reverse().toString();
				if(check(t)) {
					System.out.println(t.length());
					break;
				}
			}
		}
	}
}
```

### :octocat: 풀이 방법

1. 입력받은 문자열이 팰린드롬인지 검사
2. 아니면 앞에서부터 1글자씩 늘려가면서 짤라 뒤집어서 뒤에 붙이고 팰린드롬 검사
3. 가장 먼저 팰린드롬이 만들어지면 가장 짧은 팰린드롬 만들기 성공!

### :octocat: 후기

생각보다 간단한 문제였는데 처음에 생각을 잘못해서 팰린드롬을 만들기 위한 중간점 찾는다고 헛짓거리함 ㅜ
