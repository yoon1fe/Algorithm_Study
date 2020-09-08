## [2019 KAKAO BLIND RECRUITMENT] 실패율 - Java

###    :video_game: ​분류

> 우선 순위 큐



###  :video_game: 코드

```java
import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int no;
        double failRate;
        Node(int no, double failRate){
            this.no = no; this.failRate = failRate;
        }

        public int compareTo(Node o) {
            if(o.failRate > this.failRate ) return 1;
            else if(o.failRate < this.failRate) return -1;

            return this.no - o.no;
        }
    }

    public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] stageNum = new int[N+2];
        int total = stages.length;

        for(int n : stages) {
            stageNum[n]++;
        }

        for(int i = 1; i <= N; i++){
            pq.offer(new Node(i, (double)stageNum[i] / total));
            total -= stageNum[i];
        }

        answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()){
            answer[idx++] = pq.poll().no;
        }

        return answer;
    }
}
```



### :video_game: ​풀이 방법

실패율을 계산해서 정렬해서 리턴하는 문제입니다.

각 스테이지의 번호와 실패율을 가지는 노드들을 우선 순위 큐에 넣어서 간단하게 해결했습니다.



인풋으로 들어오는 stages 배열을 갖고 각 스테이지별로 도전중인 사람의 수를 가진 stageNum 배열을 만들어 줍니다.

그리고 스테이지 1부터 N까지 실패율을 계산해서 PQ에 넣어줍니다.

 

이때 노드의 compareTo() 메소드를 보시면 우선 순위는 실패율이 됩니다. 만약 실패율이 같으면 스테이지 번호가 작은게 먼저 오도록 합니당.

정답이 들어갈 answer 배열에 pq의 값을 뽑아서 넣어주면 끝!!



###  :video_game: 후기

휴 아직은 쉽다 쉬워~!