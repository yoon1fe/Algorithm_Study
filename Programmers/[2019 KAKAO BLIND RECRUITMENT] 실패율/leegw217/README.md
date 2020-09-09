# [2019 KAKAO BLIND RECRUITMENT] 실패율 - Java

###  :octocat: 분류

> 배열

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class week05_실패율 {
	public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] peopleInStage = new int[N+1];
        int[] reach = new int[N];
        ArrayList<double[]> failureLate = new ArrayList<double[]>();
        for(int i=0; i<stages.length; i++)
        	peopleInStage[stages[i]-1]++;
        reach[0] = stages.length;
        for(int i=1; i<N; i++)
        	reach[i] = reach[i-1] - peopleInStage[i-1];
        for(int i=0; i<N; i++)
        	failureLate.add(new double[] {(double)peopleInStage[i]/(double)reach[i], i});
        Collections.sort(failureLate, new Comparator<double[]>() {
			@Override
			public int compare(double[] o1, double[] o2) {
				if(o2[0] - o1[0] > 0) return 1;
				else if(o2[0] - o1[0] < 0) return -1;
				else return 0;
			}
		});
        for(int i=0; i<N; i++) answer[i] = (int) failureLate.get(i)[1]+1;
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. stages 배열을 읽으면서 각 스테이지 별로 머물러있는 사람 수를 체크한다(테케1에서 1번부터 1, 3, 2, 1, 0)
2. 각 스테이지 별로 그 스테이지를 지나간 사람 수를 체크한다.
1번스테이지는 전원, 2번스테이지는 (1번스테이지 지나간 사람 수 - 1번에 머물러 있는 사람 수) ...
3. 실패율은 각 스테이지 (머물러 있는 사람 수 / 지나간 사람 수)
4. 실패율 구하고 sorting해서 스테이지 번호 출력하면 끝!

### :octocat: 후기

뭔가 알고리즘적인 사고보다 수학적인 사고를 더 필요로 하는 문제인듯했당.. 히히
