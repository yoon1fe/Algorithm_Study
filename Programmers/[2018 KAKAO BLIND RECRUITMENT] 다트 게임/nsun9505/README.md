# [2018 KAKAO BLIND RECRUITMENT] 다트 게임 - JAVA

## 분류
> 구현

## 코드
```java
class Solution {
    public int solution(String dartResult) {
		int answer = 0;
		int[] arr = new int[3];
		String score = "";
		int idx = 0;
		for(int i=0; i<dartResult.length(); i++) {
			char ch = dartResult.charAt(i);
			if(ch >= '0' && ch <= '9')
				score += String.valueOf(ch);
			else if(ch == 'S' || ch == 'D' || ch == 'T') {
				arr[idx++] = calcScore(Integer.parseInt(score), ch);
				score = "";
			} else if(ch == '*' || ch == '#') {
				int curIdx = idx - 1;
				if(ch == '*') {
					arr[curIdx] *= 2;
					if(curIdx-1 >= 0)
						arr[curIdx-1] *= 2;
				} else 
					arr[curIdx] *= (-1);
			}
		}
		
		for(int i=0; i<3; i++)
			answer += arr[i];
		return answer;
	}
	
	public static int calcScore(int score, char area) {
		if(area == 'S')
			return score;
		else if(area == 'D')
			return score * score;
		return score * score * score;
	}
}
```

## 문제 풀이
1. 점수는 score에 저장하는데, S, D, T가 나올 때까지 숫자를 score에 문자열형태로 저장합니다.

1. S, D, T가 나왔다면 이제 해당 기회?에서 구한 기본 점수에 S, D, T에 따라 점수를 제곱합니다.
- 제곱한 후에는 i번째 기회는 끝났으므로 idx를 증가시킵니다.
- idx는 현재 기회를 가리키는 인덱스입니다.
- arr[i]에 i번째 게임 점수를 기록하고, 만약 * 이나 #가 나온다면 인덱스를 사용하여 이전의 점수를 *2하거나 현재 점수를 *(-1)하면 됩니다.

1. 만약 S, D, T가 나온 뒤에 *이나 #이 나온 경우 문제에서 요구하는대로 현재 점수에 *2 또는 *(-1)을 합니다.
- 대신 S, D, T가 나온 뒤 점수를 계산할 때 idx가 1증가하므로 idx-1값을 현재 인덱스(curIdx)라는 곳에 저장합니다.
- *이면 arr[curIdx] *= 2를 하고, curIdx-1이 0보다 크거나 같다면 arr[curIdx-1] *= 2도 합니다.
- #이면 arr[curIdx] *= (-1)을 하면 됩니다.

1. 마지막으로 arr에 저장된 값을 answer에 더하고 리턴함으로써 문제를 마칩니다.

## 후기
오랜만에 풀어보는 알고문제!

가볍게 워밍업할 수 있어서 좋았습니다!

2021 상반기를 위하여 파이팅!!!