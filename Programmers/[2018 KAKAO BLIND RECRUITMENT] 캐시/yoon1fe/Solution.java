package Programmers;

import java.util.*;

class Solution {
	final int HIT = 1;
	final int MISS = 5;
	
	public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> cache = new ArrayList<>(cacheSize);
        
        for(int i = 0; i < cities.length; i++) {
        	String city = cities[i].toLowerCase();
        	// hit
        	if(cache.contains(city)) {
        		answer += HIT;
        		cache.remove(city);
        		cache.add(city);
        	}
        	// miss
        	else {
        		answer += MISS;
        		if(cacheSize != 0) {
        			// 캐시가 가득 찬 경우 LRU 알고리즘 사용
        			if(cache.size() == cacheSize) {
        				cache.remove(0);
        			}  			
        			cache.add(city);
        		}
        	}
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();

//		String[] c = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//		String[] c = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		String[] c = {"Jeju", "Pangyo", "NewYork", "newyork"};
		System.out.println(s.solution(2, c));
	}
}