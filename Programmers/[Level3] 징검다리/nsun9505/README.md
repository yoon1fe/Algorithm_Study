# [Level3 이분탐색] 징검다리 - JAVA

## 분류
> 이분탐색

## 코드
```java
import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);
        int[] sortedRocks = new int[rocks.length+2];
        sortedRocks[0] = 0;
        sortedRocks[rocks.length+1] = distance;
        for(int i=1; i<=rocks.length; i++)
            sortedRocks[i] = rocks[i-1];

        int left = 1;
        int right = distance;
        while(left <= right){
            int mid = (left + right) / 2;
            int cnt = 0;
            int start = 0;
            for(int i=1; i<sortedRocks.length; i++){
                if(sortedRocks[start] + mid > sortedRocks[i])
                    cnt++;
                else{
                    start = i;
                }
            }
            if(cnt <= n){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}
```

## 문제 풀이
바위의 위치를 오름차순으로 정렬합니다.

그래서 얼마나 건너뛰는지를 기반으로 이분탐색을 합니다!
   - 가장 최대로 뛸 수 있는 것은 distance만큼일 것입니다.

left는 1, right는 distance로 설정합니다.

mid를 계산하고, 한 번에 뛸 수 있는 거리는 mid가 됩니다.

start위치에서 mid만큼 뛸 때 건너뛰게 되는 바위 수를 체크합니다!

start위치에서 mid 만큼 뛰었는데 i번째의 값보다 크다면 카운트!

같거나 작다면 해당 i번째를 start로 바꾸고 다시 mid만큼 건너뜁니다!

카운트한 수 = 건너뛴 바위의 수가 주어진 n(제가할 바위 수)보다 작거나 같다면 left를 mid+1로 해서 mid가 지금보다 큰 값으로 설정해서 좀 더 길게 뛰어봅니다.

건너뛴 바위의 수가 주어진 n보다 크다면! right를 mid-1로 해서 한 번에 건너뛰는 거리를 작게해서 답을 찾아가면 됩니다!

## 후기
이분탐색은 먼가 재미있어집니다!