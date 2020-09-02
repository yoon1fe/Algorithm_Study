package Programmers;

import java.util.*;

public class Solution {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	public int getDist(Dir hand, Dir key) {
		return Math.abs(hand.y - key.y) + Math.abs(hand.x - key.x);
	}
	
	public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        Dir left = new Dir(3, 0);
        Dir right = new Dir(3, 2);
        Map<Integer, Dir> keyPad = new HashMap<>();
        int n = 1;
        for(int i = 0; i < 3; i++) {
        	for(int j = 0; j < 3; j++) {
        		keyPad.put(n++, new Dir(i, j));
        	}
        }
        keyPad.put(0, new Dir(3, 1));
     
        for(int i = 0; i < numbers.length; i++) {
        	if(numbers[i] % 3 == 1) {								// 왼손
        		left = keyPad.get(numbers[i]);
        		answer.append("L");
        	}else if(numbers[i] != 0 && numbers[i] % 3 == 0) {		// 오른손
        		right = keyPad.get(numbers[i]);
        		answer.append("R");
        	}else {
        		Dir key = keyPad.get(numbers[i]);
        		if(getDist(left, key) > getDist(right, key)) {
        			right = key;
        			answer.append("R");
        		}
        		else if(getDist(left, key) < getDist(right, key)) {
        			left = key;
        			answer.append("L");
        		}
        		else {
        			if(hand.equals("left")) {
        				left = key;
        				answer.append("L");
        			}
        			else {
        				right = key;
        				answer.append("R");
        			}
        		}
        	}
        }
        return answer.toString();
    }
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] n = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String h = "right";
		System.out.println(sol.solution(n, h));
	}
}
