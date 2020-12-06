package Programmers;

import java.util.*;

class Solution {
	Map<Integer, Character> antilog = new HashMap<>();

	public String solution(int n, int t, int m, int p) {
		StringBuilder list = new StringBuilder();	// 말해야 하는 숫자 리스트
		StringBuilder answer = new StringBuilder();	// 튜브가 말해야 하는 숫자 리스트
		
		for(int i = 0; i < 16; i++) {
			if(10 <= i)	antilog.put(i, (char)(55+i));
			else antilog.put(i, (char)(48+i));
		}
		
		// 넉넉히 리스트 생성
		for(int i = 0; i < 30000; i++) {
			list.append(convert(i, n));
		}
		
		for(int i = 0; i < list.length(); i++) {
			if(answer.length() == t) break;
			if(i % m == (p-1)) answer.append(list.charAt(i));
		}
		
        return answer.toString();
    }
	
	public String convert(int num, int n) {
		StringBuilder sb = new StringBuilder();
		if(num == 0) return "0";
		
		while(num > 0) {
			sb.append(antilog.get(num % n));
			num /= n;
		}
		
		return sb.reverse().toString();
	}
	
	
	public static void main(String[] args) {
		Solution s = new Solution();
//		System.out.println(s.solution(2, 4, 2, 1));
//		System.out.println(s.solution(16, 16, 2, 1));
//		System.out.println(s.solution(16, 16, 2, 2));
		System.out.println(s.solution(16, 1000, 100, 100));
	}
}