# [2019 카카오 개발자 겨울 인턴십] 호텔 방 배정 - Java

###  :octocat: 분류

> 재귀

### :octocat: 코드

```java
import java.util.HashMap;
import java.util.Map;
public class week20_호텔방배정 {
	Map<Long, Long> room;
	long parent;
	long[] answer;
	
	void findEmptyRoom(long rNum, int idx) {
		// 빈방이면
		if(!room.containsKey(rNum)) {
			parent = rNum+1;
			answer[idx] = rNum;
		} else findEmptyRoom(room.get(rNum), idx);
		// 부모 업데이트
		room.put(rNum, parent);
	}
	
	public long[] solution(long k, long[] room_number) {
        answer = new long[room_number.length];
        room = new HashMap<Long, Long>();
        for(int i=0; i<room_number.length; i++) {
        	parent = room_number[i]+1;
        	findEmptyRoom(room_number[i], i);
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 배정할려는 방이 빈방이면 할당하고 노드를 만들어 배정된 방번호+1을 부모로 가지게한다.
2. 빈방이 아니면 부모를 따라가면서 빈방을 찾는다. 빈방을 찾으면 부모 할당하고 지나갔던 방의
부모들도 업데이트한다.

### :octocat: 후기

어떻게 효율성을 통과할까 고민하다가 머리아파서 기술블로그를 보고 10분만에 풀었다!!!
코드도 겁나 짧고 이거 돌아가나 긴가민가 했는데 한번에 통과해서 놀람 ㄷㄷ
심지어 k는 필요도 없음 덜덜
