# [17825] 주사위 윷놀이 - Java

###  :octocat: 분류

> 구현
> 브루트포스
> 중복허용 순열

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. 10개의 주사위 숫자를 각각 이동할 말을 중복 허용하는 순열로 만든다.
2. 윷놀이 판을 4개의 arraylist로 만드는데 0번은 빨간 화살표 따라 도착지점까지 가는경우
1번은 10점칸에서 출발하는 경우, 2번은 20점, 3번은 30점칸에서 도착지점으로 가는 경우이다.
3. 4*2행렬을 이용해 4개의 말이 어떤 판의 몇번째 칸에 위치하고 있는지 저장한다.
4. 말이 도착지점을 통과한경우를 체크하기 위해 boolean 배열을 만들어 체크한다.
5. 순열에 따라 해당 말이 이동했을때 도착지점을 통과하면 체크하고 다음 주사위로 넘어간다.
6. 도착지점이 아니면 말을 이동시키고 이동한 칸에 다른 말이 있는지 체크해준다.
7. 말이 이동한 후 도착한 칸의 점수를 더해가면서 최댓값을 찾아 출력한다.

### :octocat: 후기

4^10번 연산이 100만번정도인데 시간초과 날거같다는 잘못된 생각을 했다ㅜ
그래도 로직 생각난거 금방금방 구현했고 말 이동 후 겹치는지 검사하는 부분에 실수를 해서
찾는데 고생했다.. 그래도 중단점에 조건줘서 디버깅하는 방법을 사용할줄 알게 된 의미있는 문제였다~~
