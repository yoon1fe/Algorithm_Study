# [2020 KAKAO BLIND RECRUITMENT] 자물쇠와 열쇠 - Java

###  :octocat: 분류

> 문자열 처리

### :octocat: 코드

```java
public class week03_자물쇠와열쇠 {
	public int[][] rotateKey(int[][] key){ // 열쇠 회전시키기
		int m = key.length;
		int[][] newKey = new int[m][m];
		for(int i=0; i<m; i++)
			for(int j=0; j<m; j++) 
				newKey[m-j-1][i] = key[i][j];
		return newKey;
	}
	
	public boolean checkMap(int N, int M, int[][] map) { // 열쇠로 자물쇠 열었는지 체크
		for(int i=M-1; i<M+N-1; i++) 
        	for(int j=M-1; j<M+N-1; j++) 
        		if(map[i][j] == 2 || map[i][j] == 0) return false;
        return true;
	}
	
	public boolean insertKey(int N, int M, int[][] map, int[][] key) { // 자물쇠에 열쇠 넣기
		int[][] tempMap = new int[N+2*M-2][N+2*M-2];
		for(int i=0; i<N+M-1; i++) {
        	for(int j=0; j<N+M-1; j++) {
        		for(int d=0; d<N+2*M-2; d++) 
    				System.arraycopy(map[d], 0, tempMap[d], 0, map[d].length);
        		for(int x=0; x<M; x++) 
        			for(int y=0; y<M; y++) 
        				tempMap[i+x][j+y] += key[x][y];
        		if(checkMap(N, M, tempMap)) return true;
        	}
		}
		return false;
	}
	
	public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;
        int[][] map = new int[N+2*M-2][N+2*M-2];
        for(int i=M-1; i<M+N-1; i++) // 자물쇠가 중앙에 있는 거대한 맵 생성
        	for(int j=M-1; j<M+N-1; j++) 
        		map[i][j] = lock[i-(M-1)][j-(M-1)];
        
        for(int k=0; k<4; k++) { // 반시계방향으로 열쇠를 회전시키면서 열쇠 삽입
        	key = rotateKey(key); // 열쇠회전
        	if(insertKey(N,M,map,key)) return true; // 열쇠 삽입!
        }
        return false;
    }
}
```

### :octocat: 풀이 방법

1. 열쇠를 자물쇠에 넣을 수 있는 모든 방향에서 삽입해본다.
2. 열쇠를 회전시켜서 다시 넣어본다.
3. 한번이라도 열면 종료!

### :octocat: 후기

그냥 가능한 모든 경우의 수를 다 따져서 돌렸는데 시간초과가 안떴다. 오예!
