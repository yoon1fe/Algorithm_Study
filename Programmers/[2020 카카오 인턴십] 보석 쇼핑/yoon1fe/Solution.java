package Programmers;

import java.util.*;

public class Solution {
	static class Node implements Comparable<Node> {
		int start, end;
		Node(int s, int e){
			this.start = s; this.end = e;
		}

		public int compareTo(Node o) {
			return (this.end - this.start) - (o.end - o.start);
		}
	}
	
	public static int[] solution(String[] gems) {
		int[] answer = new int[2];
		Map<String, Integer> gemCount = new HashMap<>();
		int start = 0, end = 0, total = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for(int i = 0; i < gems.length; i++) {
			if(!gemCount.containsKey(gems[i])) {
				gemCount.put(gems[i], 0);
			}
		}
		
		// 처음 가능한 경우 구하기
		for(int i = 0; i < gems.length; i++) {
			if(gemCount.get(gems[i]) == 0) total++;
			gemCount.replace(gems[i], gemCount.get(gems[i])+1);
			if(total == gemCount.size()) {
				end = i; break;
			}
		}
		
		while(true) {
			if(total == gemCount.size()) {			// 보석이 다 포함된 범위
				pq.add(new Node(start, end));
				gemCount.replace(gems[start], gemCount.get(gems[start]) - 1);
				if(gemCount.get(gems[start]) == 0) total--;
				start++;
				continue;
			}
			
			end++;
			if(end >= gems.length) break;
			gemCount.replace(gems[end], gemCount.get(gems[end]) + 1);
			if(gemCount.get(gems[end]) == 1) total++;
		}

		answer[0] = pq.peek().start + 1; answer[1] = pq.peek().end + 1;
		return answer;
	}

	public static void main(String[] args) {
		String[] g = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" , "RUBY"};
//		String[] g = {"XYZ", "XYZ", "XYZ"};
		
		System.out.println(solution(g));
	}
}