package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int no, count;
		Node(int no, int cnt){
			this.no = no; this.count = cnt;
		}
		public int compareTo(Node o) {
			if(this.count == o.count) return this.no - o.no;
			return this.count - o.count;
		}
	}
	
	static int r, c, k, rowNum = 3, colNum = 3;
	static int[][] A = new int[101][101];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= 3; j++) A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve());
	}
	public static int solve() {
		for(int cnt = 0; cnt <= 100; cnt++) {
			if(A[r][c] == k) return cnt;
			if(rowNum >= colNum) {							// R 연산
				int newColNum = 0;
				for(int i = 1; i <= rowNum; i++) {
					Map<Integer, Integer> sortMap = new HashMap<>();
					List<Node> list = new ArrayList<>();
					for(int j = 1; j <= colNum; j++) {
						if(A[i][j] == 0) continue;
						if(!sortMap.containsKey(A[i][j])) sortMap.put(A[i][j], 1);
						else sortMap.replace(A[i][j], sortMap.get(A[i][j]) + 1);
						A[i][j] = 0;
					}

					for(int key : sortMap.keySet()) {
						list.add(new Node(key, sortMap.get(key)));
					}
					newColNum = Math.max(newColNum, list.size() * 2);
					Collections.sort(list);
					
					for(int j = 0, idx = 1; j < list.size(); j++, idx += 2) {
						if(idx > 100) break;
						A[i][idx] = list.get(j).no;
						A[i][idx + 1] = list.get(j).count;
					}
				}
				colNum = newColNum;
			}else {											// C 연산
				int newRowNum = 0;
				for(int i = 1; i <= colNum; i++) {
					Map<Integer, Integer> sortMap = new HashMap<>();
					List<Node> list = new ArrayList<>();
					for(int j = 1; j <= rowNum; j++) {
						if(A[j][i] == 0) continue;
						if(!sortMap.containsKey(A[j][i])) sortMap.put(A[j][i], 1);
						else sortMap.replace(A[j][i], sortMap.get(A[j][i]) + 1);
						A[j][i] = 0;
					}
					
					for(int key : sortMap.keySet()) {
						list.add(new Node(key, sortMap.get(key)));
					}
					newRowNum = Math.max(newRowNum, list.size() * 2);
					Collections.sort(list);
					
					for(int j = 0, idx = 1; j < list.size(); j++, idx += 2) {
						if(idx > 100) break;
						A[idx][i] = list.get(j).no;
						A[idx + 1][i] = list.get(j).count;
					}
				}
				rowNum = newRowNum;
			}
		}

		return -1;
	}
}