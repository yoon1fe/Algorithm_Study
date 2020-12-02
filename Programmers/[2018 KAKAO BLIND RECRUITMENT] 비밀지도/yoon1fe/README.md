## [2018 KAKAO BLIND RECRUITMENT] 비밀지도 - Java

###    :world_map: ​분류

> 비트마스킹

​

###  :world_map: 코드

```java
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];

		for(int i = 0; i < n; i++) {
			answer[i] = String.format("%" + n + "s", toBinary(arr1[i] | arr2[i]));
		}
		
		return answer;
	}
	
	public String toBinary(int num) {
		StringBuilder sb = new StringBuilder();
		
		while(num > 0) {
			sb.append((num & 1) == 1 ? '#' : ' ');
			num = num >> 1;
		}
		
		return sb.reverse().toString();
	}
}import java.util.*;

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



### :world_map: ​풀이 방법

비트마스킹을 이용하면 아주 간단히 풀 수 있는 문제랍니다.

비트마스킹 연습합시다!!



아아주 간단합니다. 

 

두 지도를 겹치는데, 둘 중 하나라도 벽인 부분은 전체 지도에서도 벽이다 -> |(or) 연산을 사용하면 됩니다.!!

그럼 십진수로 된 새로운 지도를 구했습니다. 얘를 " ### #" 요런 식으로 바꾸어 주어야 겠죠. 이것도 비트마스킹을 사용했습니다. 

 

(num & 1) 의 값을 보고 홀수인지 짝수인지 판별할 수 있겠죠. 그럼 이걸로 이진수도 만들어 낼 수가 있습니당. 

근디 요렇게 나누면 지도 모양 반대가 되겠죵. 또한 지도는 n * n 의 크기를 갖고 있기 때문에 reverse()하고 왼쪽에다가 패딩을 주면 됩니다!!

 

###  :world_map: 후기 

작년에 맨 처음 풀었을 때는 눈물의 똥꼬쑈를 했었는데....

비트마스킹 연습합시다!!!

 

\* 참고: `Integer.toBinaryString(int i)` 라는 메소드가 있네여^^;;; 짱이네 진짜

