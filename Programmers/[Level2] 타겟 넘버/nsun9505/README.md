# [Level2] 타겟 넘버 - JAVA

## 분류
> DFS

## 코드
```java
class Solution {
    static int cnt = 0;
    public int solution(int[] numbers, int target) {
        DFS(numbers, target, 0, 0);
        return cnt;
    }
    
    public static void DFS(int[] numbers, int target, int idx, int sum){
        if(numbers.length <= idx){
            if(sum == target)
                cnt++;
            return;
        }
        
        DFS(numbers, target, idx+1, sum + numbers[idx]);
        DFS(numbers, target, idx+1, sum - numbers[idx]);
    }
}
```

## 문제 풀이
DFS를 활용해서 현재까지 sum에 numbers[idx]를 뺀 값과 더한 값으로 재귀호출!

sum이 target과 동일하면 카운트!

## 후기
죄송합니다..

프로그래머스 고득점 킷에서 Level2부터 찬찬히 풀어보자는 생각으로 넣었는데..

쉽죠...?

남은 시간 좋은 시간 보내시길 바랍니다!

2021 화이팅!