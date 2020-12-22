# [2019 카카오 개발자 겨울 인턴십] 징검다리 건너기 - JAVA

## 분류
> 이분탐색

## 코드
```java
public class Solution {	
	 public int solution(int[] stones, int k) {
		 int answer = 0;
		 int start = 1;
		 int end = 200000000;
		 while(start <= end) {
			 int mid = (start + end) / 2;
			 if(check(stones, k, mid)) {
				 start = mid + 1;
				 if(answer < mid)
					 answer = mid;
			 } else {
				 end = mid - 1;
			 }
		 }
		 return answer;
	 }
	 
	 public static boolean check(int[] stones, int k, int mid) {
		 int cnt = 0;
		 for(int i=0; i<stones.length; i++) {
			 if(stones[i] < mid)
				 cnt++;
			 else
				 cnt = 0;
			 if(cnt >= k)
				 return false;
		 }
		 return true;
	 }
}
```

## 문제 풀이
문제는 돌을 k번 건너뛰지 않으면서 최대 몇 명까지 건너갈 수 있느냐인 것 같습니다.

그래서 이분탐색을 사용(물론 검색해서 알아냄ㅎㅎ)

초기에는 start는 1, end는 2억으로 초기화 해서 mid 값을 구합니다.

mid 값보다 작은 돌들을 카운트합니다.(check)
- 대신, k번 동안 연속되는지 체크하기 위해 cnt를 증가시키는데
- 만약 cnt가 k에 도달하면 해당 mid만큼은 통과할 수 없음을 의미하므로 false를 리턴하여 end를 mid - 1해서 범위를 줄입니다.
- 만약 cnt가 증가하다가 stones[i]가 mid보다 크거나 같다면 k만큼 건너뛰지 않으므로 cnt를 0으로 초기화합니다.
- 그래서 stones를 다 탐색할 때까지 cnt가 k와 같거나 커질 일이 없다면! true를 리턴한다.

check 함수가 true를 리턴한 경우
- 현재 stones에서 mid만큼 친구들이 건너갈 수 있다는 것입니다.
- 만약 answer보다 mid가 크다면 answer를 mid로 갱신합니다.
- 더 갈 수 있는지 알아보기 위해 start를 mid + 1로 해서 다시 이분탐색을 돌립니다.

## 후기
참 이분탐색은 어렵!! 어렵!!! 왜어렵!!!

mid를 정해서 저렇게 stones를 전체탐색해도 되는가 싶었지만 아주 잘 돌아가네용!