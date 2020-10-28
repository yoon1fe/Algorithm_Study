package week10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class week10_주사위윷놀이 {
	static int answer = Integer.MIN_VALUE;
	static int[] arr = new int[4];
	static int[] result = new int[10];
	static int[] move = new int[10];
	static List<Integer>[] map = new ArrayList[4];
	static void makePerm(int cnt) {
		if(cnt == result.length) {
			int[][] pos = new int[4][2];
			int sum = 0;
			boolean[] end = new boolean[4];
			
			for(int i=0; i<result.length; i++) {
				if(end[result[i]]) return; // 종료지점 넘어간 말을 또 이동하려하면 종료
				pos[result[i]][1] += move[i];
				// 도착지점 넘어가면
				if(pos[result[i]][1] >= map[pos[result[i]][0]].size()) {
					end[result[i]] = true;
					continue;
				}
				// 말 이동
				int num = map[pos[result[i]][0]].get(pos[result[i]][1]);
				sum += num;
				if(pos[result[i]][0]==0 && num == 10) {
					pos[result[i]][0] = 1;
					pos[result[i]][1] = 0;
				} else if(pos[result[i]][0]==0 && num == 20) {
					pos[result[i]][0] = 2;
					pos[result[i]][1] = 0;
				} else if(pos[result[i]][0]==0 && num == 30) {
					pos[result[i]][0] = 3;
					pos[result[i]][1] = 0;
				}
				// 말이 이동한 위치에 다른 말이 있는 경우
				for(int j=0; j<4; j++) { 
					if(j == result[i]) continue;
					if(end[j]) continue;
					if(pos[j][0]==pos[result[i]][0] && pos[j][1]==pos[result[i]][1]) return; // 같은 좌표 검사
					if(pos[j][0]>=1 && pos[result[i]][0]>=1) // 25번 칸부터 겹치는지 검사
						if(pos[j][1]>=1 && pos[result[i]][1]>=1) 
							if(num == map[pos[j][0]].get(pos[j][1])) return;
					if(num==40 && map[pos[j][0]].get(pos[j][1])==40) return; // 40번 칸 겹치는지 검사
				}
			}
			if(answer < sum) answer = sum;
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			result[cnt] = arr[i];
			makePerm(cnt+1);
		}
	}
	
	public static void main(String[] args) {
		map[0] = new ArrayList<Integer>(Arrays.asList(new Integer[]{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40}));
		map[1] = new ArrayList<Integer>(Arrays.asList(new Integer[]{0,13,16,19,25,30,35,40}));
		map[2] = new ArrayList<Integer>(Arrays.asList(new Integer[]{0,22,24,25,30,35,40}));
		map[3] = new ArrayList<Integer>(Arrays.asList(new Integer[]{0,28,27,26,25,30,35,40}));
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<10; i++)
			move[i] = sc.nextInt();
		for(int i=0; i<4; i++) arr[i] = i;
		makePerm(0);
		System.out.println(answer);
	}
}