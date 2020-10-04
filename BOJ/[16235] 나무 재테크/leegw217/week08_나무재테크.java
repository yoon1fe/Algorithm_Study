package week08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class week08_나무재테크 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N+1][N+1];
		int[][] A = new int[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++) {
				map[i][j] = 5;
				A[i][j] = sc.nextInt();
			}
		
		List<Integer>[][] treeList = new ArrayList[N+1][N+1];
		for(int i=0; i<=N; i++)
			for(int j=0; j<=N; j++)
				treeList[i][j] = new ArrayList<Integer>();
		for(int i=0; i<M; i++)
			treeList[sc.nextInt()][sc.nextInt()].add(sc.nextInt());
	
		for(int k=0; k<K; k++) {
			// 봄~여름
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(treeList[i][j].isEmpty()) continue;
					if(treeList[i][j].size()>1) Collections.sort(treeList[i][j]);
					List<Integer> temp = new ArrayList<Integer>();
					int deleteSum = 0;
					for(int t=0; t<treeList[i][j].size(); t++) {
						if(map[i][j] >= treeList[i][j].get(t)) {
							map[i][j] -= treeList[i][j].get(t);
							temp.add(treeList[i][j].get(t)+1);
						} else deleteSum += treeList[i][j].get(t)/2;
					}
					map[i][j] += deleteSum;
					treeList[i][j].clear();
					treeList[i][j].addAll(temp);
				}
			}
			// 가을
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(treeList[i][j].isEmpty()) continue;
					for(int t=0; t<treeList[i][j].size(); t++) {
						if(treeList[i][j].get(t) % 5 == 0) {
							for(int d=0; d<8; d++) {
								int nx = i + dx[d];
								int ny = j + dy[d];
								if(nx<=0||nx>N||ny<=0||ny>N) continue;
								treeList[nx][ny].add(1);
							}
						}
					}
				}
			}
			// 겨울
			for(int i=1; i<=N; i++) 
				for(int j=1; j<=N; j++) 
					map[i][j] += A[i][j];
		}
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++) 
				answer += treeList[i][j].size();
		System.out.println(answer);
	}
}