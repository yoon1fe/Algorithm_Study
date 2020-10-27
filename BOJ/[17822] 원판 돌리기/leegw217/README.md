# [17822] 원판 돌리기 - Java

###  :octocat: 분류

> 구현
> 시뮬레이션

### :octocat: 코드

```java
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
public class week10_원판돌리기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int T = sc.nextInt();
		int[] di = {-1, 0, 1, 0};
		int[] dj = {0, 1, 0, -1};
		int num_sum = 0;
		int num_cnt = 0;
		List<Integer>[] board = new LinkedList[N+1];
		for(int n=0; n<=N; n++) board[n] = new LinkedList<Integer>();
		for(int n=1; n<=N; n++) 
			for(int m=0; m<M; m++) {
				int num = sc.nextInt();
				board[n].add(num);
				num_sum += num;
				num_cnt++;
			}
		for(int t=0; t<T; t++) {
			int x = sc.nextInt();
			int d = sc.nextInt();
			int k = sc.nextInt();
			// 원판 회전
			for(int i=1; x*i<=N; i++) { 
				for(int j=0; j<k; j++) {
					if(d==0) 
						board[x*i].add(0, board[x*i].remove(board[x*i].size()-1));
					else
						board[x*i].add(board[x*i].remove(0));
				}
			}
			// 인접 영역 검사
			int total = 0;
			for(int i=1; i<=N; i++) { // 모든 원판 모든 숫자 검사
				for(int j=0; j<M; j++) {
					int num = board[i].get(j);
					if(num == 0) continue;
					// bfs로 인접 숫자 지우기 시작
					// 시작점 q에 넣기
					Queue<int[]> q = new LinkedList<int[]>();
					q.add(new int[] {i,j,num});
					board[i].set(j, 0);
					num_sum -= num;
					num_cnt--;
					
					int cnt = 0;
					while(!q.isEmpty()) {
						int[] p = q.poll();
						for(int l=0; l<4; l++) {
							int ni = p[0] + di[l];
							int nj = p[1] + dj[l];
							if(ni==0||ni>N) continue;
							if(nj<0) nj = M-1;
							else if(nj==M) nj = 0;
							int temp = board[ni].get(nj);
							if(temp != p[2]) continue;
							q.add(new int[] {ni,nj,temp});
							board[ni].set(nj, 0);
							num_sum -= temp;
							num_cnt--;
							cnt++;
						}
					}
					// 인접 같은 숫자가 없으면 다시 원상복귀
					if(cnt == 0) { 
						board[i].set(j, num);
						num_sum += num;
						num_cnt++;
					} 
					total += cnt;
				}
			}
			// 회전 하고 숫자 변화가 없으면
			if(total == 0) {
				float avg = (float)num_sum/(float)num_cnt;
				for(int i=1; i<=N; i++) {
					for(int j=0; j<M; j++) {
						int num = board[i].get(j);
						if(num == 0) continue;
						if((float)num > avg) {
							board[i].set(j, num-1);
							num_sum--;
						} else if((float)num < avg) {
							board[i].set(j, num+1);
							num_sum++;
						}
					}
				}
			}
		}
		System.out.println(num_sum);
	}
}
```

### :octocat: 풀이 방법

1. 원판은 연결리스트 배열로 만들어서 회전하기 쉽게 만든다.
2. 시계방향 회전은 맨뒤 때서 맨앞으로 이동, 반대방향은 반대로 하면 된다. 
그래서 삽입, 삭제가 빠른 연결리스트를 선택!
3. 원판회전하고 BFS를 이용해서 인접한 같은 숫자를 지우고 0으로 바꾼다.
4. 이때 원판에 써있는 숫자 총합과 0이 아닌 숫자 개수를 미리 체크해놓고 
숫자를 지울때마다 관리해서 나중에 숫자 변화가 없을 때 쉽게 평균을 구하고 결과 출력도 쉽게 한다.

### :octocat: 후기

처음에 인접한 같은 숫자 지우는거를 사방탐색으로만 했는데 그렇게 하니까 안지워지는 부분이 생겨서 테케를 틀렸다..
원판 다 출력해가면서 보니까 2차원 배열에서 BFS로 인접한 같은 숫자 지우는거랑 똑같길래 그렇게 풀었다!
시간초과 걸릴거 같았는데 제출하니 한번에 통과해서 좋았다!
