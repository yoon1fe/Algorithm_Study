## [2018 KAKAO BLIND RECRUITMENT] 캐시 - Java

###    :bookmark_tabs: ​분류

> 구현

​

###  :bookmark_tabs: 코드

```java
import java.util.*;

class Solution {
    final int HIT = 1;
	final int MISS = 5;
	
	public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> cache = new LinkedList<>();
        
        for(int i = 0; i < cities.length; i++) {
        	String city = cities[i].toLowerCase();
        	// hit
        	if(cache.contains(city)) {
        		answer += HIT;
        		cache.remove(city);
        		cache.add(city);
        	}
        	// miss
        	else {
        		answer += MISS;
        		if(cacheSize != 0) {
        			// 캐시가 가득 찬 경우 LRU 알고리즘 사용
        			if(cache.size() == cacheSize) {
        				cache.remove(0);
        			}  			
        			cache.add(city);
        		}
        	}
        }
        
        return answer;
    }
}
```



### :bookmark_tabs: ​풀이 방법

캐시란 문제 이름만 보고 쫄았는데 별거 없었습니다.

캐시나 페이지 교체 알고리즘 중 LRU 알고리즘에 대한 이해만 있으면 매우 쉽게 풀 수 있는 문제입니다.

캐시를 리스트로 만들었습니다. age를 갖는 클래스들로 우선순위 큐를 만들어서 구현할 수도 있겠단 생각이 듭니다.. 하지만 귀찮으니깐 패스

 

뭐 로직이랄게 없습니다. cities 문자열 배열을 돌면서 리스트에 포함되어 있으면 hit이고, 없으면 miss져.

LRU 알고리즘을 사용하기 때문에 hit 했으면 age를 0으로 만들어주면 되겠고.. 저는 리스트를 썼으니깐 걔를 고대로 똑 떼와서 맨 끝에 넣어줬습니다. 이렇게 하면 인덱스가 작은 친구일수록 오랫동안 hit 되지 않은 친구라고 볼 수 있겠습니다.

miss 인 경우에는 캐시 사이즈를 봐야 합니다. 마지막 테케를 보면 캐시 사이즈가 0이기 때문에 없애고 새로 넣고 할게 없습니다. 주의하세욤.



###  :bookmark_tabs: 후기 

이게 1번 문제였을까여 굉장히 순한 맛이었습니다.

감사합니다!!!