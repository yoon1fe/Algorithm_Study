package Programmers;

import java.util.*;

public class Solution {
	static List<Integer> combination = new ArrayList<>();
	static List<List<Integer>> candidateCols = new ArrayList<>();
	static List<Integer> cols = new ArrayList<>();
	
	public static int solution(String[][] relation) {
        for(int i = 0; i < relation[0].length; i++) {
        	cols.add(i);
        }
        
        for(int i = 1; i <= cols.size(); i++) {
        	makeComb(0, 0, i, relation);
        }

        return candidateCols.size();
    }
	
	public static void makeComb(int cnt, int idx, int depth, String[][] relation) {
		if(cnt == depth) {
			// 조합 생성 완료
			
			// 최소성 체크
			for(int i = 0; i < candidateCols.size(); i++) {
				int dupCnt = 0;
				List<Integer> temp = candidateCols.get(i);

				for(int j = 0; j < temp.size(); j++) {
					if(combination.contains(temp.get(j))) dupCnt++;
				}
				if(dupCnt == temp.size()) return;
			}
			
			// 유일성 체크
			if(checkCandidateKey(relation)) {
//				System.out.println(combination.toString());
				List<Integer> temp = new ArrayList(combination);
				candidateCols.add(temp);
			}
		}
		
		for(int i = idx; i < cols.size(); i++) {
			combination.add(cols.get(i));
			makeComb(cnt+1, i+1, depth, relation);
			combination.remove((Integer)cols.get(i));
		}
	}

	public static boolean checkCandidateKey(String[][] relation) {
		Set<String> hashSet = new HashSet<>();

		for(int i = 0; i < relation.length; i++) {
			StringBuilder str = new StringBuilder();
			for(int j = 0; j < combination.size(); j++) {
				str.append(relation[i][combination.get(j)]);
			}
			hashSet.add(str.toString());
		}

		return hashSet.size() == relation.length ? true : false;
	}

	public static void main(String[] args) {
		String[][] r = {
				{"100","ryan","music","2"},
				{"200","apeach","math","2"},
				{"300","tube","computer","3"},
				{"400","con","computer","4"},
				{"500","muzi","music","3"},
				{"600","apeach","music","2"}
		};
		

//		String[][] r = {{"b","2","a","a","b"},
//				{"b","2","7","1","b"},
//				{"1","0","a","a","8"},
//				{"7","5","a","a","9"},
//				{"3","0","a","f","9"}};
		
		System.out.println(solution(r));
	}
}