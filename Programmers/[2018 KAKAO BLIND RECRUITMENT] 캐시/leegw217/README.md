# [2018 KAKAO BLIND RECRUITMENT] 캐시 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.LinkedList;
import java.util.Queue;
public class week17_캐시 {
	public int solution(int cacheSize, String[] cities) {
		if(cacheSize == 0)  return cities.length * 5;
        int answer = 0;
        Queue<String> q = new LinkedList<String>();
        for(int i=0; i<cities.length; i++) {
        	String city = cities[i].toLowerCase();
        	// cache hit
        	if(q.contains(city)) {
        		q.remove(city);
        		q.offer(city);
        		answer += 1;
        	} else {
        		if(q.size() < cacheSize) {
        			q.offer(city);
        			answer += 5;
        		} else {
        			q.poll();
        			q.offer(city);
        			answer += 5;
        		}
        	}
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 캐시 사이즈가 0이면 cities 길이 * 5를 출력한다.
2. cities에 들어있는 문자열들을 소문자로 바꾸고 순서대로 큐에 넣는다.
3. 만약 큐에 해당 문자열이 들어있으면 지우고 다시 새로 넣는다. (cache hit라서 +1)
4. 큐에 없을때 현재 큐 사이즈가 캐시 사이즈보다 작으면 그냥 넣는다. (cache miss라서 +5)
5. 큐 사이즈가 캐시 사이즈와 같으면 가장 먼저 넣었던 값을 지우고 넣는다. (cache miss라서 +5)

### :octocat: 후기

처음에 다중 연결리스트로 만들고 각 문자열 위치를 해쉬맵에 보관해서 하는 전형적인 LRU 캐시를 만들까했는데 
생각보다 입력크기가 작아서 시간초과 안날삘이라 그냥 큐로 만들어서 했다. 바로 통과!
