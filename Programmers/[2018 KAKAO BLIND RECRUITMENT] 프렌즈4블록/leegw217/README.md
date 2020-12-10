# [2018 KAKAO BLIND RECRUITMENT] 프렌즈4블록 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.List;
public class week17_프렌즈4블록 {
	void moveBlock(int m, int n, char[][] map) {
		for(int x=m-2; x>=0; x--) {
			for(int y=0; y<n; y++) {
				if(map[x][y] == '0') continue;
				int nx = x;
				while(true) {
					nx++;
					if(nx >= m || map[nx][y] != '0') {
						nx--;
						if(nx != x) {
							map[nx][y] = map[x][y];
							map[x][y] = '0';
						}
						break;
					}
				}
			}
		}
	}
	public int solution(int m, int n, String[] board) {
        int answer = 0;
        int[] dx = {0, 1, 1}; // 오른쪽, 아래, 왼쪽 이동
        int[] dy = {1, 1, 0};
        char[][] map = new char[m][n];
        for(int i=0; i<m; i++) map[i] = board[i].toCharArray();
        while(true) {
        	List<int[]> deleteList = new ArrayList<int[]>();
        	// 2x2 같은 블록 찾기
        	for(int x=0; x<m-1; x++) {
        		loop:
        		for(int y=0; y<n-1; y++) {
        			if(map[x][y] == '0') continue;
        			char color = map[x][y];
        			for(int d=0; d<3; d++) {
        				int nx = x + dx[d];
        				int ny = y + dy[d];
        				if(color != map[nx][ny]) continue loop;
        			}
        			deleteList.add(new int[] {x, y});
        			for(int d=0; d<3; d++) {
        				int nx = x + dx[d];
        				int ny = y + dy[d];
        				deleteList.add(new int[] {nx, ny});
        			}
        		}
        	}
        	// 같은 블록 지우고 개수 세기
        	if(deleteList.isEmpty()) break;
        	for(int i=deleteList.size()-1; i>=0; i--) {
        		int[] p = deleteList.remove(i);
        		if(map[p[0]][p[1]] != '0') {
        			map[p[0]][p[1]] = '0';
        			answer++;
        		}
        	}
        	// 블럭 내리기
        	moveBlock(m, n, map);
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 맵 다 순회하면서 2x2 같은 블록을 다 찾아서 리스트에 좌표를 담는다.
2. 리스트에 담긴 좌표들에 해당하는 칸을 0으로 바꿔준다. 이때 중복으로 들어간 좌표는 무시
3. 맵에 블록들을 내려준다.
4. 더이상 지울게 없을때까지 반복

### :octocat: 후기

아주 간단한 구현문제! 순삭컷!
