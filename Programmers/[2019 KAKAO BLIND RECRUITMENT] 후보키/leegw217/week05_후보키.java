package week05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class week05_후보키 {
	List<List<Integer>> candidate = new ArrayList<>();
	void makeComb(int n, int begin, int cnt, int[] arr, List<Integer> result, String[][] relation) {
		if(cnt == n) {
			if(checkMinimality(result)) // 최소성 검사
				checkUniqueness(relation, result); // 유일성 검사
			return;
		}
		
		for(int i=begin; i<arr.length; i++) {
			result.add(arr[i]);
			makeComb(n, i+1, cnt+1, arr, result, relation);
			result.remove(result.indexOf(arr[i]));
		}
	}
	
	boolean checkMinimality(List<Integer> result) {
		for(int i=0; i<candidate.size(); i++) {
			if(candidate.get(i).size() == result.size()) break;
			boolean flag = true;
			for(int j=0; j<candidate.get(i).size(); j++)
				if(!result.contains(candidate.get(i).get(j))) {
					flag = false;
					break;
				}
			if(flag) return false;
		}
		return true;
	}
	
	void checkUniqueness(String[][] relation, List<Integer> result) {
		HashSet<String> set = new HashSet<String>();
		for(int j=0; j<relation.length; j++) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<result.size(); i++)
				sb.append(relation[j][result.get(i)]);
			set.add(sb.toString());
		}
		if(set.size() == relation.length) {
			List<Integer> temp = new ArrayList(result);
			candidate.add(temp);
		}
	}

	public int solution(String[][] relation) {
        int[] arr = new int[relation[0].length];
        for(int i=0; i<relation[0].length; i++) arr[i] = i;
        for(int i=1; i<=relation[0].length; i++) {
        	List<Integer> result = new ArrayList<Integer>();
        	makeComb(i, 0, 0, arr, result, relation);
        }
        return candidate.size();
    }
}