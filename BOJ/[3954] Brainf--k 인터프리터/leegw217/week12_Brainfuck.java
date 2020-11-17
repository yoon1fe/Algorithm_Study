package week12;

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