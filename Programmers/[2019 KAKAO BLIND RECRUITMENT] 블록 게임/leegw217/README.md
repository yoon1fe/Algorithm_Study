# [2019 KAKAO BLIND RECRUITMENT] 블록 게임 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.List;
public class week06_블록게임 {
	int answer = 0;
	int checkBlock(int x, int y, int[][] board) {
		int color = board[x][y];
		if(checkMap(x+1, y+2, board))
			if(board[x+1][y] == color)
				if(board[x+1][y+1] == color) 
					if(board[x+1][y+2] == color) return 1; // 오른쪽 긴ㄴ 모양
		if(checkMap(x+1, y-2, board))
			if(board[x+1][y] == color)
				if(board[x+1][y-1] == color) 
					if(board[x+1][y-2] == color) return 2; // 왼쪽 긴ㄴ 모양
		if(checkMap(x+1,y-1,board) && checkMap(x+1,y+1,board))
			if(board[x+1][y] == color)
				if(board[x+1][y-1] == color) 
					if(board[x+1][y+1] == color) return 3; // ㅗ 모양
		if(checkMap(x+2,y+1,board))
			if(board[x+1][y] == color)
				if(board[x+2][y] == color)
					if(board[x+2][y+1] == color) return 4; // 오른쪽 짧은ㄴ모양
		if(checkMap(x+2,y-1,board))
			if(board[x+1][y] == color)
				if(board[x+2][y] == color)
					if(board[x+2][y-1] == color) return 5; // 왼쪽 짧은ㄴ모양
		return -1;
	}
	
	boolean checkMap(int x, int y, int[][] board) {
		if(x<0||x>=board.length||y<0||y>=board.length) return false;
		return true;
	}
	
	boolean checkUp(int blockNum, int x, int y, int[][] board, List<Integer> cantDelete) {
		int xstart=0, ystart=0, yend=0, color=board[x][y];
		switch(blockNum) {
		case 1: ystart=y+1; yend=y+2; xstart=x; break;
		case 2: ystart=y-2; yend=y-1; xstart=x; break;
		case 3: ystart=y-1; yend=y+1; xstart=x; break;
		case 4: ystart=y+1; yend=y+1; xstart=x+1; break;
		case 5: ystart=y-1; yend=y-1; xstart=x+1; break;
		}
		for(int i=ystart; i<=yend; i++) // 지울 수 있는지 검사
			for(int j=xstart; j>=0; j--) 
				if(board[j][i]!=color&&board[j][i]!=0) return false;

		for(int i=0; i<board.length; i++) // 지우기
			for(int j=0; j<board.length; j++) 
				if(board[i][j] == color) 
					board[i][j] = 0;
		answer++;
		return true;
	}
	
	public int solution(int[][] board) {
        List<Integer> cantDelete = new ArrayList<Integer>();
        while(true) { // 검은돌 한줄 내려서 지울 수 있는 블록 지우기
        	int cnt = 0;
        	for(int i=0; i<board.length; i++)
            	for(int j=0; j<board.length; j++) 
            		if(board[i][j] != 0) {
            			if(cantDelete.contains(board[i][j])) continue;
            			int blockNum = checkBlock(i, j, board);
            			if(blockNum == -1) cantDelete.add(board[i][j]);
            			else if(checkUp(blockNum, i, j, board, cantDelete)) cnt++;
            		}
        	cantDelete.clear();
        	if(cnt == 0) break;
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 검은 블록을 떨어뜨려서 지울 수 있는 블록 종류는 5개밖에 없다.
2. 보드 검사 시작해서 5종류 외 다른 블록은 지울 수 없는 블록리스트에 넣는다.
3. 지울 수 있는 블록이면 위에 막는 블록이 있는지 검사해서 없으면 지우고 있으면 넘어간다.
4. 더이상 지울 수 있는 블록이 없을때까지 반복돌린다.

### :octocat: 후기

테트리스 블록가지고 푸는 문제는 언제나 하드코딩이 되버린다...
좀더 빨리 지우고싶어서 지울 수 있는 블록 위에 또 다른 지울 수 있는 블록이 있을 경우 재귀로
타고들어가 지울려고 했는데 지울 수 있는 블록 찾는 함수를 하드코딩해놔서 계속 실패했다...
그냥 검은블록 한줄씩 내렸을때 지울 수 있는것만 지워가면서 돌려도 시간초과가 안났다.. 진작 그렇게할걸..
