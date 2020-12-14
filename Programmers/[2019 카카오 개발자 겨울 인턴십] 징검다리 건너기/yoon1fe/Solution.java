package Programmers;

import java.util.*;

class Solution {
	public int solution(int[] stones, int k) {
		// Parametric Search
		return parametric_search(stones, k);
	}

	public int parametric_search(int[] stones, int k) {
		int l = Arrays.stream(stones).min().getAsInt();
        int r = Arrays.stream(stones).max().getAsInt();

        if(l == r) return l;
        
		while(l < r) {
			int mid = l + (r - l) / 2;
			if(canGo(stones, mid, k)) {
				l = mid + 1;
			}else {
				r = mid;
			}
		}
		return r - 1;
	}

	private boolean canGo(int[] stones, int mid, int k) {
		boolean start = false;
		int contiguous = 0;
		
		for(int s : stones) {
			int stone = s - mid + 1;
			if(stone <= 0) {
				if(start == false) {
					start = true;
				}
				contiguous++;
			}
			else {
				if(start == true) {
					start = false;
					if(k <= contiguous) return false;
					contiguous = 0;
				}
			}
		}
		return k <= contiguous ? false : true;
	}

	public static void main(String[] args) {
		Solution s = new Solution();

		System.out.println(s.solution(new int[] { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
	}
}