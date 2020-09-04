## [2020 카카오 인턴십] 보석 쇼핑 - Java

###     :gem: 분류

> 투 포인터



###  :gem: 코드

```java
package Programmers;

import java.util.*;

public class Solution {
    static class Node implements Comparable<Node> {
        int start, end;
        Node(int s, int e){
            this.start = s; this.end = e;
        }

        public int compareTo(Node o) {
            return (this.end - this.start) - (o.end - o.start);
        }
    }

    public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        Map<String, Integer> gemCount = new HashMap<>();
        int start = 0, end = 0, total = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i = 0; i < gems.length; i++) {
            if(!gemCount.containsKey(gems[i])) {
                gemCount.put(gems[i], 0);
            }
        }

        // 처음 가능한 경우 구하기
        for(int i = 0; i < gems.length; i++) {
            if(gemCount.get(gems[i]) == 0) total++;
            gemCount.replace(gems[i], gemCount.get(gems[i])+1);
            if(total == gemCount.size()) {
                end = i; break;
            }
        }

        while(true) {
            if(total == gemCount.size()) {            // 보석이 다 포함된 범위
                pq.add(new Node(start, end));
                gemCount.replace(gems[start], gemCount.get(gems[start]) - 1);
                if(gemCount.get(gems[start]) == 0) total--;
                start++;
                continue;
            }

            end++;
            if(end >= gems.length) break;
            gemCount.replace(gems[end], gemCount.get(gems[end]) + 1);
            if(gemCount.get(gems[end]) == 1) total++;
        }

        answer[0] = pq.peek().start + 1; answer[1] = pq.peek().end + 1;
        return answer;
    }
}
```



### :gem: 풀이 방법

딱 보니 효율성도 검사를 한다니 N^2 돌리면 안되겠구나 싶었습니다.

회전초밥 문제를 풀었던 것처럼 풀면 어떨까 생각이 떠올라서 조금 고민해보니 비슷하게 투 포인터로 풀면 되겠구나 생각이 났씁니다.



사실 투 포인터가 뭔지 모릅니다 ^^;;;

그냥 **시작 인덱스**, **끝 인덱스** 두 개갖고 봐주면 되겠구나 싶었는데 이게 투 포인터인가 봅니다.

 

먼저 모든 보석 종류가 포함된 첫 범위(start~end)를 구했습니다.

**gemCount**는 그 범위 내에 잇는 보석들의 개수 입니다.

**total 변수**는 그 범위 내에 있는 보석의 종류의 수입니다.

 

이 첫 범위를 갖고 반복문을 돌려줍니다.

- **total이 보석 종류의 총 개수와 같을 때** - start를 하나 올리고 원래 start 위치에 있던 보석에 대한 계산을 해줍니다. gemCount를 하나 빼주어야 겠져. 만약 뺐는데 0이 되면 그 범위 내에는 그 보석이 하나도 없다는 의미니깐 total의 수도 갱신해줘야 합니다.
- **total이 보석 종류의 총 개수와 다를 때** - end를 하나 올려서 범위를 늘려줘야 합니다. 마찬가지로 더해지는 보석에 대한 계산들을 해줍니다.

이렇게 반복문을 돌면서, total이 보석 종류의 총 개수와 같을 때마다 { start, end }를 **우선 순위 큐**에 넣어줍니다. 우선 순위 큐에 넣어서 반복문 다돌면 젤 위에 있는 친구만 쏙 빼오면 되는 것이지요!!



###  :gem: 후기 

몇 주전까지만 해도 완전 탐색말고는 어떻게 해야할지 감도 안왔었는데..

항상 발전함을 느끼는건 기분이 좋습니다 하하

 

항상 화이팅 화이팅!!