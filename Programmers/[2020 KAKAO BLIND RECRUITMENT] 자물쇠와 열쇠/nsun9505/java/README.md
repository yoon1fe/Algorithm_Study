# [2020 KAKAO BLIND RECURITMENT] 자물쇠와 열쇠

## 분류
> 완전탐색
>
> 구현

## 코드
```java
import java.util.ArrayList;

public class Solution {
	static ArrayList<int[][]> keyList = new ArrayList<>();
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        int size = N + (M-1)*2;
        int[][] map = new int[size][size];
        for(int i=M-1, row=0; i<N+(M-1); i++,row++) {
        	for(int j=M-1, col=0; j<N+(M-1); j++, col++) {
        		map[i][j] = lock[row][col];
        	}
        }
        
        keyList.add(key);
        for(int i=1; i<4; i++)
        	keyList.add(getRotate(keyList.get(i-1), M));
        
        for(int row=0; row<N+(M-1); row++) {
        	for(int col=0; col<N+(M-1); col++) {
        		for(int[][] rotateKey : keyList) {
        			applyKey(rotateKey, map, row, col);
        			boolean ret = check(M-1, N+(M-1), map);
        			if(ret)
        				return true;
        			recoveryKey(rotateKey, map, row, col);
        		}
        	}
        }
        
        
        return false;
    }
    
    public static boolean check(int start, int end, int[][] map) {
    	for(int i=start; i<end; i++) {
    		for(int j=start; j<end; j++) {
    			if(map[i][j] == 0 || map[i][j] == 2)
    				return false;
    		}
    	}
    	return true;
    }
    
    public static void applyKey(int[][] key, int[][] map, int row, int col) {
    	for(int i=0; i<key.length; i++) {
    		for(int j=0; j<key.length; j++) {
    			map[row+i][col+j] += key[i][j];
    		}
    	}
    }
    
    public static void recoveryKey(int[][] key, int[][] map, int row, int col) {
    	for(int i=0; i<key.length; i++) {
    		for(int j=0; j<key.length; j++) {
    			map[row+i][col+j] -= key[i][j];
    		}
    	}
    }
    
    public static int[][] getRotate(int[][] origin, int M){
    	int[][] ret = new int[M][M];
    	for(int i=0, col = M-1; i<M; i++, col--) {
    		for(int j=0, row=0; j<M; j++, row++) {
    			ret[row][col] = origin[i][j];
    		}
    	}
    	return ret;
    }
}
```

## 문제 풀이
자물쇠와 열쇠(0도, 90도, 180도, 270도 회전한 것들)와 겹쳐서 자물쇠의 홈 부분이 모두 꽉 차도록 하면 됩니다.

자물쇠와 열쇠가 겹친다는 것은 부분적으로 겹치거나 완전히 겹치게 되는 경우가 있습니다.
   - 그러면 열쇠가 자물쇠에 완전히 겹치는 경우에는 범위 체크가 상관없지만
   - 부분적으로 겹치는 경우에는 열쇠가 자물쇠의 범위를 넘어서게 됩니다.

그래서 자물쇠와 열쇠가 겹치거나 부분접으로 겹치는 전체적인 범위의 2차원 배열을 선언하였습니다.
   - 2차원 배열 중앙에는 자물쇠 정보가 들어가고, 그 외 변두리에는 열쇠(회전한 것들 포함)를 넣어보고 체크하는 식으로 문제를 풀었습니다.
   - 즉, 자물쇠 (0,0)과 키의 (M-1,M-1)이 겹치는 부분부터 자물쇠의 (N-1,N-1)과 키의 (0, 0)이 겹치는 부분까지 모든 경우를 생각할 수 있는 배열이 되는 것입니다.

자물쇠의 크기를 N, 열쇠의 크기를 M이라고 하면 새로 생성한 배열의 크기는 N + (M-1)*2의 크기를 갖습니다.
   - 내부 중앙에 자물쇠가 들어가므로 N 크기 필요
   - 그외 변두리에 자물쇠와 키가 최소 1칸은 겹치도록 하므로 M-1 * 2 크기가 필요

배열만 만들었다면 이제 (0, 0) ~ (N+M-2) 각 위치를 기준으로 key를 넣어보고 자물쇠 부분이 모두 1로 차는지 검사하면 됩니다!
   - 맞지 않다면(0 또는 2) 복구시켜야 합니다.

## 후기
배열의 범위를 잘못 설정해서 시간을 잡아먹었습니다ㅠ