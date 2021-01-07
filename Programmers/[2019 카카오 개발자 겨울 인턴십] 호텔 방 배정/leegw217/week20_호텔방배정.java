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