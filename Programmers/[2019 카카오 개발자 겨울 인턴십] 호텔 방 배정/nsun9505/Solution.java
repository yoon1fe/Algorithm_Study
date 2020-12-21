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
