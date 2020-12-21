# [2019 카카오 개발자 겨울 인턴십] 호텔 방 배정 - JAVA

## 분류
> 유니온파인드

## 코드
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	static Map<Long, Long> usedRoom = new HashMap<>();
	public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        for(int i=0; i<room_number.length; i++)
        	answer[i] = getNotUsedMinRoom(room_number[i]);
        return answer;
    }
	
	public static long getNotUsedMinRoom(long x) {
		if(!usedRoom.containsKey(x)) {
			usedRoom.put(x, x+1);
			return x;
		}
		usedRoom.put(x, getNotUsedMinRoom(usedRoom.get(x)));
		return usedRoom.get(x);
	}
}
```

## 문제 풀이
유니온 파인드를 이용해서 고객이 원하는 방이 이미 배정되어 있는 경우 해당 방번호보다 크면서 비어있는 방 중 가장 작은 방을 배정합니다.
   - 해당 방이 이미 배정되어 있다고, 가장 작은 방을 가져오는 것이 아니라.. 해당 방번호보다 크면서 비어있는 방이면 되는 것입니다.

1. answer는 room_number 크기와 동일하게 생성
1. getNotUsedMinRoom()을 통해서 room_number[i](현재 방 번호)가 이미 배정되어 있다면 room_number[i] 보다 크면서 비어있는 가장 작은 방번호를 리턴합니다.
   - 만약 배정되어 있지 않다면, usedRoom에 room_number[i] + 1 값을 넣어서 다음에 room_number[i]가 배정받을 수 있는지 보도록 합니다.
   - 물론, room_number[i]+1도 이미 배정되어 있다면 (room_number[i] + 1)의 다음으로 할당 가능한 번호를 찾아서 리턴하면 됩니다.
1. answer에 답을 저장해서 리턴!

## 후기
처음에는 이분탐색으로 풀릴까 고민도 해보고 우선순위 큐를 사용해볼까 고민도 하였지만 잘 안 풀려서 구글링~

일단 코드는 안 보고 사람들이 어떻게 풀었는지 보고 유니온파인드를 썼길래 나름대로 find를 구현했지만, if문 이후로 잘못 구현해서 틀려부림!

이해가 젤 잘되는 코드를 찾아보고 풀어 볼 수 있었습니다!

그리고 가장 큰 문제는 **4.고객이 원하는 방이 이미 배정되어 있으면 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정합니다.** 이 조건을 제대로 안 읽었다는 거~ 제발 문제 좀 잘 읽자ㅠ

이것 때문에 방이 배정되었을 때 젤 앞인 1번부터 찾아보느라 바로 틀린거 같다ㅠ
- 그래서 방이 배정되지 않았을 때 getNotUsedMinRoom에 1을 넘기지 않고 room_number[i]를 넘기는 것으로 수정해니 풀렸다ㅠㅠ

## 문제를 잘 읽자!!!!