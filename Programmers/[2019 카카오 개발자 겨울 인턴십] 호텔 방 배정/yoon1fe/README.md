## [2019 카카오 개발자 겨울 인턴십] 호텔 방 배정 - Java

### :hotel: 분류

> Map 활용
>
> DFS



###  :hotel: 코드

```java
import java.util.*;

class Solution {
    Map<Long, Long> map = new HashMap<>();
	
	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		
		int idx = 0;
		for(long rn : room_number) {
			answer[idx++] = findRoom(rn);
		}
        
		return answer;
	}

	public long findRoom(long rn) {
		if(map.containsKey(rn) == false) {
			map.put(rn, rn+1);
			return rn;
		}

		map.put(rn, findRoom(map.get(rn)));
		return map.get(rn);
	}
}
```



### :hotel: 풀이 방법

효율성까지 채점되는 문제입니다. 

아이디어는 Map 활용!



먼저 map의 용도는 **<요청하는 방의 번호 : 배정받을 수 있는 방>** 입니다. 

그리고 findRoom() 메소드는 방 번호를 요청했을 때 배정받을 방을 리턴해주는 메소드입니다. 그럼 findRoom() 메소드를 살펴봅시당.

 

먼저 map.containsKey(rn) == false 요 부분이 기저 부분이 될 수 있겠습니다. 맨 처음 들어갔을 때 일 수 있겠지요. 맨먼저 1번 방을 요청하면 고대로 1번 방을 주면 됩니다. 그럼 이때는 map에 <1 : 2> 가 되어야겠지요.

그 밑 부분은 실질적으로 배정받을 수 있는 방을 찾아가는 과정입니다. DFS 죠. 

 

아래는 테스트 케이스에서 마지막 1이 들어왔을 때의 map 입니다.



![img](https://blog.kakaocdn.net/dn/douTef/btqP0t6CuSz/jiDrk8mKS5d7ZWSIZ1FuEK/img.png)



여기서 1이 들어온다면 



![img](https://blog.kakaocdn.net/dn/b7yekT/btqPZKgsbiJ/h6NvPTJFUvsKrHWfkrEuY1/img.png)



이렇게 업데이트가 됨으로써 순차적으로 탐색하지 않아도 되는 것이지용.



###  :hotel: 후기

효율성을 요하는 문제인데 특별한 알고리즘이 필요하진 않았습니다.

그래도 꾸준히 공부 많이 해야겠습니다..

감사합니다!!