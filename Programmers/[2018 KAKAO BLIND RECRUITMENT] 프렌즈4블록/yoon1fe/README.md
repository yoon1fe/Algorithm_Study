## [2018 KAKAO BLIND RECRUITMENT] 프렌즈4블록 - Java

###    :duck: ​분류

> 구현

​

###  :duck: 코드

```java
import java.util.*;

class Solution {
    char[][] map;
	int answer = 0;
	
	public int solution(int m, int n, String[] board) {
		// char[][] 배열로 다룸
        map = new char[m][n];
        
        for(int i = 0; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		map[i][j] = board[i].charAt(j);
        	}
        }
        
        // 지워질 블럭이 있는 경우 반복
        while(updateBlocks(m, n));
        
        return answer;
    }
	
	public boolean updateBlocks(int m, int n) {
		boolean[][] c = new boolean[m][n];
		int cnt = 0;
		
		// 체크
		for(int i = 0; i < m-1; i++) {
			for(int j = 0; j < n-1; j++) {
				if(map[i][j] == '0') continue;
				if(check(i, j) == true) {
					c[i][j] = true; c[i][j+1] = true;
					c[i+1][j] = true; c[i+1][j+1] = true;
				}
			}
		}
		
		// 갱신
		for(int i = 0; i < n; i++) {
			List<Character> temp = new ArrayList<>();
			for(int j = m-1; j >= 0; j--) {
				if(c[j][i] == true) {
					cnt++;
					continue;
				}
				temp.add(map[j][i]);
			}
			
			for(int j = m-1, k = 0; j >= 0; j--, k++) {
				if(k < temp.size())	map[j][i] = temp.get(k);
				else map[j][i] = '0';
			}
		}
		
		answer += cnt;
		return cnt > 0 ? true : false;
	}

	public boolean check(int i, int j) {
		char std = map[i][j];
		
		if(map[i][j+1] == std && map[i+1][j] == std && map[i+1][j+1] == std) return true;
		return false;
	}
}
```



### :duck: ​풀이 방법

앞에 문제 풀다가 이 문제를 보니 선녀가 따로 없습니다. 마찬가지로 특별한 알고리즘없이 하라는 대로 하면 됩니다.

이 문제의 핵심은 그겁니다. **게임판을 갱신하고 또 갱신할 수 있으면 계속 이어나가야 하는 것**이죵. 

예전에 풀었던 [뿌요뿌요](https://yoon1fe.tistory.com/150) 문제랑 비슷하지용!

마찬가지로 체크 -> 갱신하는 로직을 메소드로 두고, 없앨 블록이 있는지 없는지를 리턴해서 반복문 종료 조건으로 걸었습니다.

 

뿌요뿌요와 달리 이 문제에서는 **2x2** 블록만 봐주면 되기 때문에 따로 BFS를 돌릴 필요도 없습니다. 간단하져.

블록을 내리는 부분도 리스트를 이용해서 구현했습니다. 여기서 빈 공간은 '0'으로 표현했답니다. 지금 보니 뿌요뿌요 코드에서 블록 내리는 코드는 굉장히 지저분하네요. 또 한뼘 성장했나봄니다 허허~~

 

아 리턴해야할 정답은 제거된 블록의 수입니다! 이걸 체크할 때 개수를 세준다면 중복이 발생하므로 갱신할 때 해주는 것이 옳습니다. 그리고 마지막으로 cnt가 0보다 크다면 갱신이 되었다는 의미니깐 updateBlocks() 에서 true를 리턴해줌으로써 다음번에도 한 번 더 updateBlocks() 메소드에 들어가도록 해주면 됩니다.



###  :duck: 후기 

열심히 공부해서 카카오 다음 채용때는 잘 풀고 싶습니다 ㅜㅜ

감사합니다!!!!