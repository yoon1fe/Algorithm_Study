# [Level2] 베스트앨범 - Java

###  :octocat: 분류

> 해시

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class week25_베스트앨범 {
	public int[] solution(String[] genres, int[] plays) {
        List<Integer> ans = new ArrayList<Integer>();
        Map<String, Integer> genreCnt = new HashMap<String, Integer>();
        Map<String, PriorityQueue<int[]>> playCnt = new HashMap<String, PriorityQueue<int[]>>();
        
        for(int i=0; i<genres.length; i++) {
        	if(!genreCnt.containsKey(genres[i])) genreCnt.put(genres[i], plays[i]);
        	else genreCnt.put(genres[i], genreCnt.get(genres[i]) + plays[i]);
        	
        	if(!playCnt.containsKey(genres[i])) {
        		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						if(o1[1] == o2[1]) return o1[0] - o2[0];
						else return o2[1] - o1[1];
					}
        		});
        		playCnt.put(genres[i], pq);
        	} 
        	playCnt.get(genres[i]).offer(new int[] {i, plays[i]});
        }
        
        List<Entry<String, Integer>> entries = new ArrayList<Map.Entry<String,Integer>>(genreCnt.entrySet());
        Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
        
        for(Entry<String, Integer> entry : entries) {
        	int size = 0;
        	if(playCnt.get(entry.getKey()).size() >= 2) size = 2;
        	else size = 1;
        	for(int i=0; i<size; i++) 
        		ans.add(playCnt.get(entry.getKey()).poll()[0]);        		
        }
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++) answer[i] = ans.get(i);
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 먼저 장르별로 총 플레이 수를 저장하는 hashmap인 playCnt를 만든다.
2. 장르별로 플레이가 많이 된 노래 순으로 출력하는 우선순위큐를 담는 hashmap genreCnt를 만든다. 이때 우선순위큐에는 해당 노래의 고유번호와 플레이수가 배열로 들어간다.
3. playCnt에서 플레이 수를 내림차순정렬해서 key값을 뽑아낸다.
4. 해당 key값(genre이름)에 해당하는 우선순위큐를 genreCnt에서 뽑는다. 개수가 2개 이상이면 높은 순으로 2개만 뽑고 아니면 1개만 뽑아서 answer에 담는다.  

### :octocat: 후기

예전에 파이썬으로 풀었는데 거기서 딕셔너리 정렬써서 개쉽게 풀었던 기억이나서 찾아보니 hashmap도 정렬할 수 있떠라! 
그래도 완전 새로 짠거치고 빨리 풀었는데 entry라는걸 처음 써봐서 좀 어색했따.
