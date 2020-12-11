package Programmers;

import java.util.*;

class Solution {
	
	public int[] solution(String s) {
        List<List<Integer>> sets = new ArrayList<>();
        String[] arr = s.replaceAll("[{}]", " ").trim().split(" , ");
        int[] answer = new int[arr.length];
        
        Arrays.sort(arr, (o1, o2)->{ return o1.length() - o2.length(); });

        for(String str : arr) {
        	List<Integer> temp = new ArrayList<>();
        	for(String n : str.split(",")) {
        		temp.add(Integer.parseInt(n));
        	}
        	sets.add(temp);
        }
        

        for(int i = 0; i < sets.size(); i++) {
        	int num = sets.get(i).get(0);
        	answer[i] = num;
        	
        	for(int j = i + 1; j < sets.size(); j++) {
        		sets.get(j).remove((Integer)num);
        	}
        }

        return answer;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		
//		System.out.println(s.solution(new String("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
//		System.out.println(s.solution(new String("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
//		System.out.println(s.solution(new String("{{20,111},{111}}")));
		System.out.println(s.solution(new String("{{4,2,3},{3},{2,3,4,1},{2,3}}")));
	}
}