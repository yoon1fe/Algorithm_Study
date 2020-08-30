package Programmers;

import java.util.*;

public class Solution {
	boolean[] v;
	int answer;
	List<Integer> perm = new LinkedList<>();
	List<Integer> between;
	
	public void wallCheck() {
		List<Integer> temp = new ArrayList<>(between);
    	
    	for(int k = 0; k < temp.size(); k++) {
    		int idx = 0;
    		int sum = 0;
    		boolean flag = true;
    		for(int j = 0; j < temp.size(); j++) {
        		int cur = temp.get(j);
        		sum += cur;
        		if(idx >= perm.size()) {
        			flag = false;
        			break;
        		}
        		if(perm.get(idx) < sum) {
        			if(idx >= perm.size()) {
        				flag = false;
        				break;
        			}
        			idx++;
        			sum = 0;
        		}
        	}
    		if(flag) {
    			answer = Math.min(answer, perm.size());
    			break;
    		}
    		temp.add(temp.remove(0));
    	}
    }        
	
	
	public void comb(int cnt, int max, int[] dist) {
		if(cnt == max) {
			wallCheck();
			return;
		}
		
		for(int i = 0; i < dist.length; i++) {
			if(v[i]) continue;
			v[i] = true;
			perm.add(dist[i]);
			comb(cnt+1, max, dist);
			v[i] = false;
			perm.remove((Integer)dist[i]);
		}
		
	}
	
	public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        between = new ArrayList<>();
        for(int i = 0; i < weak.length-1; i++) {
        	between.add(weak[(i + 1) % n] - weak[(i + n) % n]);
        }
        between.add(weak[0] + n - weak[weak.length-1]);
        
        for(int i = 1; i <= dist.length; i++) {
        	v = new boolean[dist.length];
        	comb(0, i, dist);
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		
		int[] w = {1, 5, 6, 10};
//		int[] w = {0, 10, 50, 80, 120, 160, 199};
		int[] d = {1,2,3,4};
//		int[] d = {1, 10, 5, 40, 30};
		
		
		System.out.println(sol.solution(12, w, d));
		
		
	}

}
