# [2020 카카오 인턴십] 보석 쇼핑 - Java

###  :octocat: 분류

> 투포인터

### :octocat: 코드

```java
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
public class week04_보석쇼핑 {
	public int[] solution(String[] gems) {
        HashMap<String, Integer> gem = new HashMap<String, Integer>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]-o1[0] == o2[1]-o2[0]) 
					return o1[0] - o2[0];
				else
					return (o1[1]-o1[0]) - (o2[1]-o2[0]);
			}
		});
        for(int i=0; i<gems.length; i++) gem.put(gems[i],0);
        int startIdx = 0, endIdx = 0;
        while(true) { // 처음 보석 다 찾는 경우
        	if(gem.containsValue(0)) {
        		gem.put(gems[endIdx], gem.get(gems[endIdx])+1);
        		endIdx++;
        	}
        	if(!gem.containsValue(0)) {
        		pq.add(new int[] {startIdx+1, endIdx--});
        		break;
        	}
        }       
        int cnt = gem.size();
        while(true) { // 가장 짧고 시작 idx가 빠른 부분 찾기
        	if(cnt == gem.size()) { // 찾았다!
        		pq.add(new int[] {startIdx+1,endIdx+1});
        		gem.put(gems[startIdx], gem.get(gems[startIdx])-1);
        		if(gem.get(gems[startIdx]) == 0) cnt--;
        		startIdx++;
        	} else { // 못찾았다!
        		endIdx++;
        		if(endIdx==gems.length) break;
        		if(gem.get(gems[endIdx]) == 0) cnt++;
        		gem.put(gems[endIdx], gem.get(gems[endIdx])+1);
        	}
        }
        return pq.poll();
    }
}
```

### :octocat: 풀이 방법

1. hashMap을 사용해서 보석별로 갯수를 체크한다.
2. 시작지점은 0으로 시작, 종료지점을 이동시키면서 보석 갯수 체크
3. 처음으로 보석을 종류별로 한개 이상씩 찾으면 시작지점과 종료지점을 우선순위큐에 담는다.
4. 이후 다시 반복돌면서 보석들이 한개 이상씩 있으면 큐에 담고 시작지점을 증가시키고
5. 갯수가 0개인 보석이 있으면 종료지점을 증가시키면서 종료지점이 배열의 끝을 넘어가면 종료

### :octocat: 후기

처음 풀었을 때는 while 반복문 하나로 hashmap의 value중 0이 있는지 없는지를 이용해서
시작점과 종료점을 이동시키고 큐에 담고 했는데 효율성 2개가 시간초과... 진짜 화나서 죽을뻔했지만
아마 계속 0이 있는지 없는지 찾으니 시간초과가 난거같다. 어떻게할까하다가 원철님 코드 참고해서 수정했다 히히
원철님 멋져용!
