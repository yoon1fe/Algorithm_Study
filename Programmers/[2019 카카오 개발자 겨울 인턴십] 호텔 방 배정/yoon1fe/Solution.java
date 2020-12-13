package Programmers;

import java.util.*;

class Solution {
	Map<Long, Long> map = new HashMap<>();
	
	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		
		int idx = 0;
		for(long rn : room_number) {
			answer[idx++] = findRoom(rn);
		}
		System.out.println(Arrays.toString(answer));
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

	public static void main(String[] args) {
		Solution s = new Solution();
		
		System.out.println(s.solution(10, new long[] {1, 3, 4, 1, 2, 1}));
	}
}