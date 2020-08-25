## [2020 KAKAO BLIND RECRUITMENT] 자물쇠와 열쇠 - Java

###    :closed_lock_with_key:분류

> 문자열 처리



###  :closed_lock_with_key: 코드

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    	static int M;
	static int N;
	
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	public boolean check(int[][] entireMap) {
		for(int i = M-1; i < M - 1 + N; i++) {
			for(int j = M-1; j < M - 1 + N; j++) {
				if(entireMap[i][j] == 0 || entireMap[i][j] == 2) return false;
			}
		}
		return true;
	}
	
	public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        int mapSize = 2*(M-1) + N;
        int[][] map = new int[mapSize][mapSize];
    	
        for(int r = 0; r < 4; r++) {
        	List<Dir> keyDir = new ArrayList<>();
            
            for(int i = 0; i < M; i++) {
            	for(int j = 0; j < M; j++) {
            		if(key[i][j] == 1) keyDir.add(new Dir(i, j));
            	}
            }
            
            
            for(int i = 0; i < N; i++) {
            	for(int j = 0; j < N; j++) {
            		map[i + M -1][j + M - 1] = lock[i][j];
            	}
            }
            
            for(int i = 0; i < M - 1 + N; i++) {
            	for(int j = 0; j < M - 1 + N; j++) {
            		for(int k = 0; k < keyDir.size(); k++) {
            			Dir cur = keyDir.get(k);
            			if(cur.y + i < M-1 || cur.y + i > M+N-2 || cur.x + j < M-1 || cur.x + j > M+N-2) continue;

            			map[cur.y + i][cur.x + j]++;
            		}
            		
            		if(check(map)) return true;
            		
            		for(int k = 0; k < keyDir.size(); k++) {
            			Dir cur = keyDir.get(k);
            			if(map[cur.y + i][cur.x + j] == 0) continue;
            			map[cur.y + i][cur.x + j]--;
            		}
            	}
            }
            
            rotate(key);
        }
        
        return false;
    }

	private void rotate(int[][] key) {
		int[][] rotatedKey = new int[M][M];
		for(int i = 0; i < M; i++) {
			int[] hori = key[i].clone();
			for(int j = 0; j < M; j++) {
				rotatedKey[j][M - i - 1] = hori[j];
			}
		}
		for(int i = 0; i < M; i++) {
			key[i] = rotatedKey[i].clone();
		}
	}
}
```



### :closed_lock_with_key: 풀이 방법

BFS로도 깨작거려보고 비트마스킹도 끄적거려봤는데 실패해씀다...

그래도 시도해봄으로써 얻은게 분명 있다고 믿습니다 ^.^

 

2(M-1) + N * 2(M-1) + N 만큼의 2차원 배열을 만들어주고 반복문으로 쭈루룩 이동해가면서 체크해줬습니다.



![img](https://blog.kakaocdn.net/dn/x0Iid/btqHceDYXPv/UzRiNjNK1eptDupKSamwY1/img.png)



이런 식으로요.

열쇠 안에서의 돌기 부분 위치 값을 따로 저장해서 체크했습니다.

돌기 부분을 ++ 다 해주고 나서 자물쇠가 열리는지 check() 메소드를 통해 체크해줍니다.

여기서 풀 수 있으면 걍 바로 true 리턴하고 끝내면 되고, 아니면 ++ 했던 친구들을 원상태로 복구시켜줘야 합니다.

 

열쇠를 90도씩 돌릴 수 있으므로 총 4번 반복하면 됩니다.



###  :closed_lock_with_key: 후기

그리 까다로운 문제는 아닌데 첨에 넘무 쫄아서 접근 방법을 희한하게 생각해서 푸는데 오래 걸렸네여 ㅜㅜ

그리고 인덱스 부분도 항상 집중 빡 하고 고려해야겠습니다... 머리가 너무 아퍼 