## [2020 KAKAO BLIND RECRUITMENT] 기둥과 보 설치 - Java

###    :house:분류

> 시뮬레이션



###  :house: 코드

```java
class Solution {
    static int[][] map;
	static int n = 0;
	static int cnt;
	static boolean[][] cols, bos;
	public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        Solution.n = n;
        Solution.cols = new boolean[n+3][n+3];
        Solution.bos = new boolean[n+3][n+3];
        
        for(int i = 0; i < build_frame.length; i++) {
        	int x = build_frame[i][0] + 1;
        	int y = build_frame[i][1] + 1;
        	int a = build_frame[i][2];						// 0: 기둥		1: 보
        	int b = build_frame[i][3];						// 0: 삭제		1: 생성
        	
        	switch(a) {
        	case 0: colFunc(x, y, b); break;
        	case 1: boFunc(x, y, b); break;
        	}
        }

        // 리턴 배열
        answer = new int[cnt][3];
        int idx = 0;
        for(int i = 1;  i <= n + 1; i++) {
        	for(int j = 1; j <= n + 1; j++) {
        		if(cols[i][j]) answer[idx++] = new int[] {i-1, j-1, 0};
        		if(bos[i][j]) answer[idx++] = new int[] {i-1, j-1, 1};
        	}
        }
        
        return answer;
    }

	void boFunc(int x, int y, int b) {
		switch(b) {
		case 0:				// 삭제
			if(canRemove(x, y, 1)) {
				bos[x][y] = false;
				Solution.cnt--;
			}
			break;
		case 1:				// 생성
			if(canMakeBo(x, y)) {
				bos[x][y] = true;
				Solution.cnt++;
			}
			break;
		}
	}

	void colFunc(int x, int y, int b) {
		switch(b) {
		case 0:				// 삭제
			if(canRemove(x, y, 0)) {
				cols[x][y] = false;
				Solution.cnt--;
			}
			break;
		case 1:				// 생성
			if(canMakeCol(x, y)) {
				cols[x][y] = true;
				Solution.cnt++;
			}
			break;
		}
		
	}
	
	boolean canMakeCol(int x, int y) {
		return y == 1 || cols[x][y - 1] || bos[x][y] || bos[x-1][y];
	}
	
	boolean canMakeBo(int x, int y) {
		return cols[x][y-1] || cols[x+1][y-1] || (bos[x-1][y] && bos[x+1][y]);
	}
	
	boolean canRemove(int x, int y, int type) {
		boolean result = true;
		// type == 0 : 기둥 			type == 1 : 보
		if(type == 0) cols[x][y] = false;
		else bos[x][y] = false;
		
		outer:
		for (int i = 1; i <= n + 1; i++) {
			for (int j = 1; j <= n + 1; j++) {
				if (cols[i][j] && !canMakeCol(i, j)) {
					result = false;
					break outer;
				}
				if (bos[i][j] && !canMakeBo(i, j)) {
					result = false;
					break outer;
				}
			}
		}
		
		if(type == 0) cols[x][y] = true;
		else bos[x][y] = true;

		return result;
	}
}
```



### :house: 풀이 방법

시뮬레이션인데... 결국 혼자 힘으로 못풀었습니다...

스트레스 이빠이...

특별한 알고리즘을 요하는 문제가 아님에도 못풀어서 분합니다...

인터넷에서 찾아보고 풀었씁니다...

기둥과 보를 만들 수 있는지 여부를 boolean으로 리턴하는 메소드를 따로 뺄 걸 왜 생각을 못했을까ㅜㅜㅜㅜㅜㅜㅜ

계속 || 를 && 로 바꾸고 난리부르스를 쳐부렀습니다....

휴.. 카카오 어렵다....

 

###  :house: 후기

진짜.....

한번 꼬이면... 돌아올 수 엄ㄷ따...