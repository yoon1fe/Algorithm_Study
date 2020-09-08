package Programmers;

import java.util.*;

public class Solution {
	static class Node implements Comparable<Node> {
        int no;
        double failRate;
        Node(int no, double failRate){
            this.no = no; this.failRate = failRate;
        }
        
		public int compareTo(Node o) {
			if(o.failRate > this.failRate ) return 1;
			else if(o.failRate < this.failRate) return -1;
			
			return this.no - o.no;
		}
    }
    
    public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] stageNum = new int[N+2];
        int total = stages.length;
        
        for(int n : stages) {
            stageNum[n]++;
        }
        
        for(int i = 1; i <= N; i++){
            pq.offer(new Node(i, (double)stageNum[i] / total));
            total -= stageNum[i];
        }
        
        answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()){
        	answer[idx++] = pq.poll().no;
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
//		int[] s = {2, 1, 2, 6, 2, 4, 3, 3};
		int[] s = {4,4,4,4,4};
		
		System.out.println(solution(4, s));
	}
}