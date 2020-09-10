package Programmers;

import java.util.*;

public class Solution {

	static class Node implements Comparable<Node>{
		int x, y, no;
		Node(int x, int y, int no){
			this.x = x; this.y = y; this.no = no;
		}
		
		public int compareTo(Node n){
			if(this.y == n.y) return this.x - n.x;
			return n.y - this.y;
		}
	}
	
	static int maxX;
	static int[][] answer;
	static List<Integer> pre = new ArrayList<>();
	static List<Integer> post = new ArrayList<>();
	static List<Node> nodeList;
	
    public static int[][] solution(int[][] nodeinfo) {
    	nodeList = new ArrayList<>();
    	
    	answer = new int[2][nodeinfo.length];
        for(int i = 0; i < nodeinfo.length; i++) {
        	maxX = Math.max(maxX, nodeinfo[i][0]);
        }
        
        for(int i = 0; i < nodeinfo.length; i++) {
        	nodeList.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }

        Collections.sort(nodeList);
        
        preorder(0, 0, maxX+1);
        postorder(0, 0, maxX+1);
        
        for(int i = 0; i < pre.size(); i++) {
        	answer[0][i] = pre.get(i);
        	answer[1][i] = post.get(i);
        }
        
        return answer;
    }
    
    public static void preorder(int idx, int left, int right) {
    	if(idx != -1) {
    		pre.add(nodeList.get(idx).no);
    		preorder(leftChild(idx, left), left, nodeList.get(idx).x);
    		preorder(rightChild(idx, right), nodeList.get(idx).x, right);
    	}
    }
    
    public static void postorder(int idx, int left, int right) {
    	if(idx != -1) {
    		postorder(leftChild(idx, left), left, nodeList.get(idx).x);
    		postorder(rightChild(idx, right), nodeList.get(idx).x, right);
    		post.add(nodeList.get(idx).no);
    	}
    }
    
    public static int leftChild(int idx, int left) {
    	for(int i = idx + 1; i < nodeList.size(); i++) {
    		if(left <= nodeList.get(i).x && nodeList.get(i).x < nodeList.get(idx).x) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public static int rightChild(int idx, int right) {
    	for(int i = idx + 1; i < nodeList.size(); i++) {
    		if(nodeList.get(idx).x < nodeList.get(i).x && nodeList.get(i).x <= right) {
    			return i;
    		}
    	}
    	return -1;
    }
    
	public static void main(String[] args) {
		int[][] n = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
		
		
		System.out.println(solution(n));
	}
}