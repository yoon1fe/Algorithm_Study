## [2020 KAKAO BLIND RECRUITMENT] 괄호 변환 - Java

###   :face_with_thermometer: 분류

> 문자열 처리



###  :face_with_thermometer: ​코드

```java
class Solution {
    public boolean isAlright(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++) {
			if(!stack.isEmpty() && stack.peek() == '(' && s.charAt(i) == ')') stack.pop();
			else stack.push(s.charAt(i));
		}
		return stack.isEmpty() == true ? true : false;
	}
	
	public String solution(String p) {		// 인풋으로 항상 균형잡인 괄호 문자열이 들어온다.
        String answer = "";
        if(isAlright(p)) return p;
        
        int lParen = 0;
        int rParen = 0;
        String u = "", v = "";
        for(int i = 0; i < p.length(); i++) {
        	if(p.charAt(i) == '(') lParen++;
        	else rParen++;
        	
        	if(lParen == rParen) {
        		u = p.substring(0, i + 1);
        		v = p.substring(i + 1);
        		break;
        	}
        }
        
        if(isAlright(u)) {
        	answer = u + solution(v);
        }
        
        else {
        	StringBuilder sb = new StringBuilder("(" + solution(v) + ")");
        	for(int i = 1; i < u.length() - 1; i++) sb.append(u.charAt(i) == '(' ? ')' : '(');
        	answer = sb.toString();
        }
        return answer;
    }
}
```



### :face_with_thermometer: 풀이 방법

괄호를 다루는 문제입니다. 

인풋으로 들어오는 파라미터의 조건이 많아서 고려해줘야 할게 비교적 적어서 쉽게 풀 수 있었씁니다.

문제에 나와있는 흐름 그대로 코드를 작성해주면 됩니다. 입력으로 들어오는 p가 항상 균형잡힌 괄호 문자열이기 때문에 올바른 괄호 문자열인지만 따로 체크해주면 됩니다.

 

올바른 괄호 문자열인지는 스택을 사용해서 체크해줬습니다. 하나씩 스택에 넣다가 스택의 top에 있는 친구와 새로 들어올 친구가 올바른 괄호 짝지라면 top에 있는 친구를 쏙 빼주면 올바른 괄호 문자열이라면 p의 길이만큼 다 돌면 스택에 아무것도 남아있지 않을겁니다.

 

입력으로 들어온 p가 올바른 괄호 문자열인지 체크해주고 그 밑에는 문제에서 하라는 대로 고오오오대로 짜면 됩니다.

 

###  :face_with_thermometer: 후기

문제에서 걸어주는 조건이 많아서 생각보다 스근하게 풀 수 있었습니다. 재귀에 대한 눈이 조금 더 떠졌다고 느껴져서 좋습니다.