# [3954] Brainf**k 인터프리터 - Java

###  :octocat: 분류

> 구현
> 
> 스택

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
public class week12_Brainfuck {
	static int Sm, Sc, Si;
	static int[] memory;
	static char[] cmd;
	static char[] input;
	static int[] loop;
	static int[] touch;
	static int c_idx, i_idx, point, cnt;
	static int MOD = 256;
	static int LIMIT = 50000000;
	
	static void findPair() {
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<cmd.length; i++) {
			if(cmd[i] == '[') stack.push(i);
			else if(cmd[i] == ']') {
				int idx = stack.pop();
				loop[idx] = i;
				loop[i] = idx;
			}
		}
	}
	
	static void findLoop() {
		for(int i=Sc-1; i>=0; i--) {
			if(touch[i] > 0) {
				System.out.println("Loops "+loop[i]+" "+i);
				break;
			}
		}
	}
	
	static void calc() {
		while(cnt < LIMIT && c_idx < Sc) {
			cnt++;
			switch(cmd[c_idx]) {
			case '-':
				memory[point] = (memory[point] + 255) % MOD; break;
			case '+':
				memory[point] = (memory[point] + 1) % MOD; break;
			case '<':
				point = (point-1)==-1 ? Sm-1 : point-1; break;
			case '>':
				point = (point+1)==Sm ? 0 : point+1; break;
			case '[':
				if(memory[point] == 0) {
					c_idx = loop[c_idx];
					continue;
				}
				break;
			case ']':
				if(memory[point] != 0) {
					touch[c_idx]++;
					c_idx = loop[c_idx];
					continue;
				}
				break;
			case ',':
				if(i_idx == Si) memory[point] = 255;
				else memory[point] = input[i_idx++];
				break;
			}
			c_idx++;
		}
		if(cnt == LIMIT) findLoop();
		else System.out.println("Terminates");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			Sm = Integer.parseInt(st.nextToken());
			Sc = Integer.parseInt(st.nextToken());
			Si = Integer.parseInt(st.nextToken());
			memory = new int[Sm];
			cmd = new char[Sc];
			input = new char[Si];
			loop = new int[Sc];
			touch = new int[Sc];
			c_idx = 0; i_idx = 0; point = 0; cnt = 0;
			cmd = br.readLine().toCharArray();
			input = br.readLine().toCharArray();
			findPair();
			calc();
		}
	}	
}
```

### :octocat: 풀이 방법

1. 루프를 서로 짝짖기 위해 스택을 이용해서 루프의 시작위치에 끝위치를 넣고 끝위치에 시작위치를 넣는다.
2. 명령을 순서대로 따르면서 무한루프가 있는지 검사한다.
3. 이때 포인터가 가리키는 수를 1 감소시킬때 메모리가 8비트 unsigned-bit이기 때문에 +255하고 256으로 mod 해준다.
4. 포인터를 이동시킬때도 메모리의 범위 밖을 벗어나면 반대편 위치로 이동시킨다.
5. 5천만번 반복 중 모든 명령을 완료하면 terminates, 무한루프에 빠지면 해당 루프의 시작점과 끝점을 출력한다.

### :octocat: 후기

진짜 문제 이해하는데도 한참 걸린 문제였다;;; 도저히 이해가 안돼서 결국 검색해서 코드보고 짰다 ㅜㅜ
뇌가 굳은 것인가...
