# [14891] 톱니바퀴 - Java

###  :gear: 분류

> 시뮬레이션



### :gear: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Info{
		int n, dir;
		Info(int n, int dir){
			this.n = n; this.dir = dir;
		}
	}
	
	static String[] gear = new String[5];		// 톱니바퀴
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<Info> order = new ArrayList<>();
		int answer = 0;
		
		for(int i = 1; i <= 4; i++) {
			gear[i] = br.readLine();
		}
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			order.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))); // 회전할 톱니 번호
		}
		
		for(Info i : order) {
			action(i);
		}
		
		for(int i = 1; i <= 4; i++) {
			answer += (gear[i].charAt(0) - '0') * Math.pow(2, i-1);
		}
		
		System.out.println(answer);
	}

	public static void action(Info start) {
		Queue<Info> q = new LinkedList<>();					// 톱니바퀴 q
		boolean[] v = new boolean[5];
		List<Info> rotateAction = new ArrayList<>();
		int[] d = {1, -1};
		
		q.offer(start);
		v[start.n] = true;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			rotateAction.add(cur);
			
			for(int i = 0; i < 2; i++) {
				Info next = new Info(cur.n + d[i], cur.dir * -1);
				if(next.n < 1 || next.n > 4 || v[next.n]) continue;
				
				if(i == 0) {				// 오른쪽
					if(gear[cur.n].charAt(2) == gear[next.n].charAt(6)) continue;
				}else {						// 왼쪽
					if(gear[cur.n].charAt(6) == gear[next.n].charAt(2)) continue;
				}
				
				q.offer(next);
				v[next.n] = true;
			}
		}
		
		for(Info i : rotateAction) rotate(i);
	}
	
	public static void rotate(Info g) {
		StringBuilder sb = new StringBuilder();
		if(g.dir == 1) {					// 시계 방향
			sb.append(gear[g.n].charAt(7)).append(gear[g.n].substring(0, 7));
		}else {								// 반시계 방향
			sb.append(gear[g.n].substring(1, 8)).append(gear[g.n].charAt(0));
		}
		gear[g.n] = sb.toString();
	}
}
```



### :gear: 풀이 방법

삼성 역테 기출로 돌아왔습니다.

쫄깃한 시뮬레이션 문제입니다. 하라는 대로 말 잘들으면 맞출 수 있습니다.

항상 느끼는 거지만 자바는 입력받는게 너무 지저분합니다 ㅜ.ㅜ

 

바퀴의 번호 n과 회전 방향 dir를 담은 클래스를 선언해서 사용했습니다.

 

프로그램의 로직입니다. 

1. BFS를 써서 인접한 톱니가 돌 수 있는지를 체크해줍니다. 회전할 수 있으면 rotateAction 리스트에 넣어줍니다.
2. rotateAction 리스트를 반복하면서 rotate() 메소드를 호출합니다. rotate() 메소드는 해당 톱니를 돌리는 동작을 수행합니다.
3. 마지막으로 맨 위에 있는 숫자를 판별해서 answer를 구해서 출력하면 끝!



### :gear: 후기

얼핏 보면 복잡해 보이지만 하라는 대로 하면 됩니당. ^^;;

다시 화이팅!!!