# [2020 KAKAO BLIND RECRUITMENT] 기둥과 보 설치 - Java

###  :octocat: 분류

> 시뮬레이션

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.Comparator;

public class week03_기둥과보설치 {
	public boolean insert(boolean[][][] map, int[] input) {
		int x = input[0];
		int y = input[1];
		int a = input[2];
		
		if(a == 0) { // 기둥 설치
			if(y == 0) return true; // 바닥에 설치하는 건지 체크
			if(y>0 && map[x][y-1][0]) return true; // 바로 밑에 기둥이 있는지  체크
			if((x>0 && map[x-1][y][1]) || map[x][y][1]) return true; // 밑에 보가 있는지 체크
		} else { // 보 설치
			if(map[x][y-1][0] || map[x+1][y-1][0]) return true; // 양쪽 끝에 기둥이 있는지 체크
			if(x>0 && map[x-1][y][1] && map[x+1][y][1]) return true; // 양쪽 끝에 보가 있는지 체크
		}
		return false;
	}
	
	public boolean delete(int n, boolean[][][] map, int[] input) {
		int x = input[0];
		int y = input[1];
		int a = input[2];
		
		if(a == 0) { // 기둥 삭제
			if(y<n-1 && map[x][y+1][0]) // 위에 기둥이 있을 때 -> 위 기둥 밑에 보가 있는지 검사
				if(!(x<n && map[x][y+1][1] || (x>0 && map[x-1][y+1][1]))) return false;
			if(map[x][y+1][1]) // 위 오른쪽 보 있을 때 -> 오른쪽 아래 기둥이 있는지, 양옆에 보가 있는지 검사
				if(!(map[x+1][y][0] || (x>0 && x<n-1 && map[x-1][y+1][1] && map[x+1][y+1][1]))) return false;
			if(x>0 && map[x-1][y+1][1]) // 위 왼쪽 보 있을 때 -> 왼쪽 아래 기둥이 있는지, 양옆에 보가 있는지 검사
				if(!(map[x-1][y][0] || (x>1 && map[x-2][y+1][1] && map[x][y+1][1]))) return false;
		} else { // 보 삭제
			if(map[x][y][0]) // 왼족 위 기둥이 있을 때 -> 왼쪽 아래 기둥, 왼쪽 보 검사
				if(!(map[x][y-1][0] || (x>0 && map[x-1][y][1]))) return false;
			if(map[x+1][y][0]) // 오른쪽 위 기둥 있을 때 -> 오른쪽 아래 기둥, 오른쪽 보 검사
				if(!(map[x+1][y-1][0] || map[x+1][y][1])) return false;
			if(x>0 && map[x-1][y][1]) // 왼쪽 보 있을 때 -> 왼쪽 아래 기둥, 더 왼쪽 아래 기둥 검사
				if(!(map[x][y-1][0] || map[x-1][y-1][0])) return false;
			if(map[x+1][y][1]) // 오른쪽 보 있을 때 -> 오른쪽 아래 기둥, 더 오른쪽 아래 기둥 검사
				if(!(map[x+1][y-1][0] || map[x+2][y-1][0])) return false;
		}
		return true;
	}
	
	public int[][] solution(int n, int[][] build_frame) {
		boolean[][][] map = new boolean[n+1][n+1][2];
		ArrayList<int[]> answer = new ArrayList<int[]>();
		
		for(int i=0; i<build_frame.length; i++) {
			if(build_frame[i][3] == 1) { // 설치
				if(insert(map, build_frame[i])) {
					map[build_frame[i][0]][build_frame[i][1]][build_frame[i][2]] = true;
					answer.add(new int[] {build_frame[i][0], build_frame[i][1], build_frame[i][2]});
				}
			} else { // 삭제
				if(delete(n, map, build_frame[i])) {
					map[build_frame[i][0]][build_frame[i][1]][build_frame[i][2]] = false;
					int x = build_frame[i][0];
					int y = build_frame[i][1];
					int a = build_frame[i][2];
					for(int j=0; j<answer.size(); j++) {
						if(answer.get(j)[0] == x && answer.get(j)[1] == y && answer.get(j)[2] == a)
							answer.remove(j);
					}
				}
			}
		}
		answer.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] - o2[0] == 0) {
					if(o1[1] - o2[1] == 0) {
						return o1[2] - o2[2];
					}
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
			
		});
		int[][] result = new int[answer.size()][3];
		result = answer.toArray(result);
		return result;
	}
}
```

### :octocat: 풀이 방법

boolean 3차원 배열을 하나 만들어서 거기에 기둥과 보를 설치한다.
설치하는 함수에서는 문제에서 주어진 조건에 맞게 if문을 작성해서 설치할 수 있는지 없는지 체크한다.
<삭제하는 함수에서 기둥을 삭제할 때 조건>
1. 삭제할 기둥(A) 위에 기둥(B)이 있는경우 -> B기둥 밑에 보가 있는지 검사(양방향) // 없으면 A를 삭제할 수 없다
2. 삭제할 기둥(A) 위 오른쪽에 보(B)가 있는 경우 -> B보 오른쪽 아래 기둥이 있는지, B보 양옆에 보가 있는지 검사
3. 삭제할 기둥 위 왼쪽에 보가 있는 경우 -> 2번 조건과 방향만 반대로 검사

<삭제하는 함수에서 보를 삭제할 때 조건>
1. 삭제할 보(A) 왼쪽 위 기둥(B)가 있는경우 -> B기둥 밑에 기둥이 있는지, B기둥 왼쪽에 보가 있는지 검사
2. 삭제할 보 오른쪽 위 기둥이 있는 경우 -> 1번 조건과 방향 반대로 검사
3. 삭제할 보(A) 왼쪽에 보(B)가 있는 경우 -> B보 기준 왼쪽아래와 오른쪽아래 기둥이 있는지 검사
4. 삭제할 보 오른쪽에 보가 있는 경우 -> 3번 조건과 방향 반대로 검사
### :octocat: 후기

어마어마하게 많은 조건이 필요한 문제였다..! 그래도 전에 한번 풀어봤던 문제라서 빨리 풀 수 있었다!
