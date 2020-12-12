# [2018 KAKAO BLIND RECRUITMENT] 다트 게임 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
public class week18_다트게임 {
    public int solution(String dartResult) {
        int answer = 0;
        char[] arr = dartResult.toCharArray();
        int[] score = new int[3];
        int idx = -1;
        for(int i=0; i<arr.length; i++) {
        	if(arr[i]-'0' >=0 && arr[i]-'0' <= 9) {
        		idx++;
        		if(arr[i]-'0' == 1) {
        			if(arr[i+1]-'0' == 0) {
        				score[idx] = 10;
        				i++;
        				continue;
        			}
        		}
        		score[idx] = arr[i]-'0';
        	} else if(arr[i] == 'D') score[idx] = (int)Math.pow(score[idx], 2);
        	else if(arr[i] == 'T') score[idx] = (int)Math.pow(score[idx], 3);
        	else if(arr[i] == '#') score[idx] *= -1;
        	else if(arr[i] == '*') {
        		score[idx] *= 2;
        		if(idx != 0) score[idx-1] *= 2;
        	}
        }
        for(int i=0; i<3; i++) answer += score[i];
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 숫자면 점수칸에 넣는다. 이때 10인지 체크해줘야함.
2. D면 해당 점수 제곱, T면 세제곱 해준다.
3. #이면 해당 점수 곱하기 -1
4. *이면 해당 점수 곱하기 2하고 0번칸이 아니면 그 전 점수도 곱하기 2 해준다.

### :octocat: 후기

예전에도 한번 풀어봤던 문제
