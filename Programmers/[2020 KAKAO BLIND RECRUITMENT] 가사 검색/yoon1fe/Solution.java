package Programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	static class TrieNode{
		Map<Character, TrieNode> childNodes = new HashMap<>();
		boolean isLastChar;
		int childNo;
	}

	static class Trie {
		TrieNode rootNode;
		Trie(){
			rootNode = new TrieNode();
		}
		
		void insert(String word) {
			TrieNode thisNode = this.rootNode;
			for(int i = 0; i < word.length(); i++) {
//				computeIfAbsent(): key를 사용하여 차례대로 value를 반환하는 함수형 인터페이스
//				key(word.charAt(i))가 없는 경우에만 TrieNode 생성
//				thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), key -> new TrieNode());
				char c = word.charAt(i);
				if(thisNode.childNodes.get(c) == null) {
					thisNode.childNodes.put(c, new TrieNode());
				}
				thisNode.childNo++;
				thisNode = thisNode.childNodes.get(c);
			}
			
			thisNode.isLastChar = true;
		}
		
		int getCount(String word) {
			TrieNode thisNode = this.rootNode;
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				TrieNode node = thisNode.childNodes.get(c);
				if(node == null) return 0;
				
				thisNode = node;
			}
			
			return thisNode.childNo;
		}	
	}
	
	public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie[] ascRoots = new Trie[10001];
        Trie[] descRoots = new Trie[10001];
        
        for(int i = 1; i < 10001; i++) {
        	ascRoots[i] = new Trie();
        	descRoots[i] = new Trie();
        }
        
        for(int i = 0; i < words.length; i++) {
        	ascRoots[words[i].length()].insert(words[i]);
        	descRoots[words[i].length()].insert(reverse(words[i]));
        }
        
        for(int i = 0; i < queries.length; i++) {
        	String subQuery = queries[i].replace("?", "");
        	if(queries[i].charAt(0) != '?') {														// ascRoot
        		answer[i] = ascRoots[queries[i].length()].getCount(subQuery);
        	}else {																					// descRoot
        		answer[i] = descRoots[queries[i].length()].getCount(reverse(subQuery));
        	}
        }
        
        return answer;
    }
	
	public String reverse(String word) {
		StringBuilder ret = new StringBuilder();
		for(int i = word.length()-1; i>=0; i--) ret.append(word.charAt(i));
		return ret.toString();
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] k = {"dd", "fd", "sd", "sdf"};//{"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] l = {"?d", "s?", "?df"};//{"fro??", "????o", "fr???", "fro???", "pro?"};
		int[] s = sol.solution(k, l);
		
		System.out.println(Arrays.toString(s));
	}

}
