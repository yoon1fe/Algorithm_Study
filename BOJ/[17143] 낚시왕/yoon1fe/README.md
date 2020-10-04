# [17143] 낚시왕 - Java

###  :shark: 분류

> 시뮬레이션
>



### :shark: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static class Shark implements Comparable<Shark>{
		Dir loc;
		int size, d, s;

		Shark(int y, int x, int s, int d, int size) {
			this.loc = new Dir(y, x); this.size = size; this.d = d; this.s = s;
		}
		
		public int compareTo(Shark o) {
			if(this.loc.x == o.loc.x) return this.loc.y - o.loc.y;
			return this.loc.x - o.loc.x;
		}
	}
	
	static int R, C, M;
	static List<Shark> sharks = new ArrayList<>();
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			sharks.add(new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(sharks);

		System.out.println(solve());
	}

	public static int solve() {
		int answer = 0;
		
		for(int fisherman = 1; fisherman <= C; fisherman++) {
			List<Shark>[][] map = new ArrayList[R+1][C+1];
			for(int i = 1; i <= R; i++) {
				for(int j = 1; j <= C; j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			// 상어 잡음
			for(int i = 0; i < sharks.size(); i++) {
				if(sharks.get(i).loc.x > fisherman) break;		// 낚시꾼이 있는 열에 상어가 없는 경우
				if(sharks.get(i).loc.x == fisherman) {
					answer += sharks.get(i).size;
					sharks.remove(i); break;
				}
			}
			
			// 상어 이동(새로운 위치 구하고 map에 먼저 넣자)
			for(Shark shark : sharks) {
				int mod = 0, ny = shark.loc.y, nx = shark.loc.x, nd = shark.d;
				switch(shark.d) {
				case 1: case 2: mod = shark.s % ((R - 1) * 2); break;	// 위아래 이동, R 고려
				case 3: case 4: mod = shark.s % ((C - 1) * 2);			// 좌우 이동, C 고려
				}
				
				for(int i = 0; i < mod; i++) {
					ny += dy[nd];
					nx += dx[nd];
					
					if(nx <= 0 || ny <= 0 || ny >= R+1 || nx >= C+1) {
						nd = (nd == 1 || nd == 2) ? (nd == 1 ? 2 : 1) : (nd == 3 ? 4 : 3); 
						ny += dy[nd];
						nx += dx[nd];
						i--;
					}
				}
				map[ny][nx].add(new Shark(ny, nx, shark.s, nd, shark.size));
			}
			
			// 큰놈이 작은놈 잡아먹고 난 뒤의 map으로 sharks 리스트 갱신
			sharks.clear();
			for(int i = 1; i <= R; i++) {
				for(int j = 1; j <= C; j++) {
					if(map[i][j].size() > 0) {
						Collections.sort(map[i][j], new Comparator<Shark>() {
							public int compare(Shark o1, Shark o2) {
								return o2.size - o1.size;
							}
						});
						sharks.add(map[i][j].get(0));
					}
				}
			}
			Collections.sort(sharks);
		}
		return answer;
	}
}
```



### :shark: 풀이 방법

휴.. 삼성st 빡 구현입니다...

와 한시간 반만에 못풀엇넹 ㅎㅎ;;



자료구조를 많이많이 고민해봤는데... 아무리 생각해도 List와 2차원 배열을 같이 써야 풀리더라구여 ,, ^^;

상어들의 정보를 쭉 보면서 새로운 위치를 찾아줘야 하기 때문에 sharks 라는 List를 썼고, 

한 위치에 두 마리 이상의 상어를 놔둬야 해서 List 2차원 배열(map)을 썼습니다. 

여기서 map 배열은 상어의 새로운 위치이므로 처음부터 만들어 놓을 필욘 없답니다.

 

sharks 리스트를 x좌표->y좌표 순으로 오름차순 정렬해서 낚시꾼이 잡을 수 있는 상어는 쉽게 구했습니다.

x좌표를 O(n) 으로 봤는데, 요걸 이분 탐색으로 O(log n)으로 구할 수도 있겠습니다. 하지만 최대 10000개니깐 PASS..

 

낚시꾼이 상어 한 마리를 잡으면 그다음에는 상어들을 이동시켜줍니다.

6개월 전에 풀었을 때 시간초과가 떴었는데, 여기서 문제가 있었습니다. 속력의 최댓값이 1000이라서 하나하나 왔다리 갔다리 하니깐 터지는거 있져. 그래서 나머지 연산을 해줬습니다. 

상어가 한바쿠를 돌아서 다시 원래 자리로 오는데는 가로or세로 길이 - 1의 두배가 됩니다. 속도를 얘로 나눈 나머지만큼만 이동시켜주면 되는 것이죠!

이 때 맵을 벗어난다면 위치 바꿔주는 걸 잊지 마시고... 또 같은 공간에 여러 마리의 상어가 있을 수 있으므로 List 2차원 배열에 담았습니다. 

 

상어를 모두 이동시켰으면 map의 정보를 바탕으로 죽일 상어를 죽여주고 ㅜㅜ 다시 sharks 리스트를 갱신해주어야겠지요. 왜냐면 낚시꾼이 잡을 상어를 정할 때 sharks 리스트갖고 고르니깐요.

 

기존의 sharks 리스트를 깔끔하게 비워주고, map[i][j] 위치에 상어가 한 마리 이상 있는 경우 크기에 대하여 내림차순으로 정렬한 후 맨 앞(.get(0))에 있는 친구를 sharks 리스트에 넣어줍시다.

 

낚시꾼이 C까지 가고 나서 총 얻은 answer를 바로 출력하면 끝입니다!!



### :shark: 후기

switch 문에서 break; 하나 깜빡하고 안적어서 틀렸습니다... 사소한 실수를 하지 않도록 꼼꼼히 코딩합시당~~

감사합니다~!~!