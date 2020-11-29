package Programmers;

import java.util.*;


class Solution {
	public int solution(String str1, String str2) {
        int intersection = 0, union = 0;
        
        // 대소문자 구별 X
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        Map<String, Integer> map1 = makeMap(str1);
        Map<String, Integer> map2 = makeMap(str2);

        // 합집합이 0인 경우 J(A, B) = 1
        if(map1.size() == 0 && map2.size() == 0) return (1<<16);
        
		for(String k : map1.keySet()) {
			if(map2.containsKey(k)) {
				intersection += Math.min(map1.get(k), map2.get(k));		// 교집합
				union += Math.max(map1.get(k), map2.get(k));			// 합집합
				map2.remove(k);
			}else {
				union += map1.get(k);
			}
		}
		
		for(String k : map2.keySet()) {
			union += map2.get(k);
		}
		
        return (int)(intersection / (double)union * (1 << 16));
    }
	
	public Map<String, Integer> makeMap(String str) {
		Map<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i < str.length() - 1; i++) {
			String temp = str.substring(i, i+2);
			// 숫자, 특수문자 제외
			if(97 > temp.charAt(0) || temp.charAt(0) > 122 || temp.charAt(1) < 97 || temp.charAt(1) > 122) continue;
			
			if(map.containsKey(temp)) map.replace(temp, map.get(temp) + 1);
			else map.put(temp, 1);
		}
		
		return map;
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		String str1 = "E=M*C^2";
		String str2 = "shake hands";
		
		System.out.println(s.solution(str1, str2));
		
	}
}