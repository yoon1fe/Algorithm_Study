package Programmers;

import java.util.*;

class Solution {
	public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];

		for(int i = 0; i < n; i++) {
			answer[i] = toBinary(arr1[i] | arr2[i], n);
		}
		
		return answer;
	}
	
	public String toBinary(int num, int n) {
		StringBuilder sb = new StringBuilder();
		
		while(num > 0) {
			sb.append((num & 1) == 1 ? '#' : ' ');
			num = num >> 1;
		}
		
		return String.format("%" + n + "s", sb.reverse().toString());
	}

	public static void main(String[] args) {
		Solution s = new Solution();

//		int[] a1 = {9, 20, 28, 18, 11};
//		int[] a2 = {30, 1, 21, 17, 28};
		int[] a1 = {46, 33, 33, 22, 31, 50};
		int[] a2 = {27, 56, 19, 14, 14, 10};
		
		System.out.println(s.solution(6, a1, a2));
	}
}