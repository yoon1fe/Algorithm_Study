## [2019 카카오 개발자 겨울 인턴십] 징검다리 건너기 - Java

### :bridge_at_night: 분류

> 이분 탐색
>
> Parametric Search

​

###  :bridge_at_night: 코드

```java
import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        // Parametric Search
        return parametric_search(stones, k);
    }

    public int parametric_search(int[] stones, int k) {
        int l = Arrays.stream(stones).min().getAsInt();
        int r = Arrays.stream(stones).max().getAsInt();

        if(l == r) return l;

        while(l < r) {
            int mid = l + (r - l) / 2;
            if(canGo(stones, mid, k)) {
                l = mid + 1;
            }else {
                r = mid;
            }
        }
        return r - 1;
    }

    private boolean canGo(int[] stones, int mid, int k) {
        boolean start = false;
        int contiguous = 0;

        for(int s : stones) {
            int stone = s - mid + 1;
            if(stone <= 0) {
                if(start == false) {
                    start = true;
                }
                contiguous++;
            }
            else {
                if(start == true) {
                    start = false;
                    if(k <= contiguous) return false;
                    contiguous = 0;
                }
            }
        }
        return k <= contiguous ? false : true;
    }
}
```



### :bridge_at_night: 풀이 방법

오우 만만치 않은 문제입니다.

마찬가지로 효율성을 확보해야 하는 문제입니다.



문제의 답은 이분 탐색의 응용인 파라메트릭 서치로 답을 구하면 됩니다. 이 때 범위에 해당하는 값은 m 번째 친구입니다. 건널 수 있는 니니즈의 가장 큰 수를 구해야 하기 때문에 m 번째 친구가 건널 수 있다면 m+1~끝 까지 탐색을 하는 식이죵.

요 때 범위는 stones.min() ~ stones.max() 가 됩니다. 만약 stones가 {5, 5, 5, 5} 라면 아무리 날고 기어봤자 최대 다섯명까지 건널 수 있을테니깐용. 

 

그리고 건널 수 있는지 여부 판단은 간단합니다. k 번째 건너는 친구의 징검다리 번호는 stones[] - m 가 됩니다. 얘를 갖고 연속된 0 이하의 개수가 k 개 이상인 경우 건널 수 없게 되는 것입니당!



###  :bridge_at_night: 후기

요것이 어퍼바운드...? 맞나 모르것네

인덱스를 잘 고민하고 짭시다!!

감사합니다!!!