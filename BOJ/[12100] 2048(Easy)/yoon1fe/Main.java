import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[] moveOrder = new int[5];		//최대 다섯번 이동-> 중복 순열
	static int[] dy = {1, -1, 0, 0};			//D U R L
	static int[] dx = {0, 0, 1, -1};
	static int answer = 0;
	
	public static int combine() {
		int[][] temp = new int[N][N];
		int max = 0;
		for(int i = 0; i< N; i++) System.arraycopy(map[i], 0, temp[i], 0, map[i].length);

		for(int t = 0; t < 5; t++) {
			switch(moveOrder[t]) {
			case 0:		// Down
				for(int i = 0; i < N; i++) {
					Stack<Integer> s = new Stack<Integer>();
					Stack<Integer> resultS = new Stack<Integer>();
					for(int j = N-1; j>=0; j--) {
						if(temp[j][i] == 0) continue;
						if(s.isEmpty()) s.push(temp[j][i]);
						else {
							if(s.peek() == temp[j][i]) {
								s.pop();
								resultS.push(temp[j][i] * 2);
							}else {
								resultS.push(s.pop());
								s.push(temp[j][i]);
							}
						}
						temp[j][i] = 0;
					}
					while(!s.isEmpty()) resultS.push(s.pop());
					for(int j = N - resultS.size(); j < N; j++) temp[j][i] = resultS.pop();
					
				}
				break;
			case 1:		// Up
				for(int i = 0; i < N; i++) {
					Stack<Integer> s = new Stack<Integer>();
					Stack<Integer> resultS = new Stack<Integer>();
					for(int j = 0; j < N; j++) {
						if(temp[j][i] == 0) continue;
						if(s.isEmpty()) s.push(temp[j][i]);
						else {
							if(s.peek() == temp[j][i]) {
								s.pop();
								resultS.push(temp[j][i] * 2);
							}else {
								resultS.push(s.pop());
								s.push(temp[j][i]);
							}
						}
						temp[j][i] = 0;
					}
					while(!s.isEmpty()) resultS.push(s.pop());
					for(int j = resultS.size() - 1; j >= 0; j--) temp[j][i] = resultS.pop();
					
				}
				break;
				
			case 2:		// Right
				for(int i = 0; i < N; i++) {
					Stack<Integer> s = new Stack<Integer>();
					Stack<Integer> resultS = new Stack<Integer>();
					for(int j = N-1; j>=0; j--) {
						if(temp[i][j] == 0) continue;
						if(s.isEmpty()) s.push(temp[i][j]);
						else {
							if(s.peek() == temp[i][j]) {
								s.pop();
								resultS.push(temp[i][j] * 2);
							}else {
								resultS.push(s.pop());
								s.push(temp[i][j]);
							}
						}
						temp[i][j] = 0;
					}
					while(!s.isEmpty()) resultS.push(s.pop());
					for(int j = N - resultS.size(); j < N; j++) temp[i][j] = resultS.pop();
				}
				break;
				
			case 3:		// Left
				for(int i = 0; i < N; i++) {
					Stack<Integer> s = new Stack<Integer>();
					Stack<Integer> resultS = new Stack<Integer>();
					for(int j = 0; j < N; j++) {
						if(temp[i][j] == 0) continue;
						if(s.isEmpty()) s.push(temp[i][j]);
						else {
							if(s.peek() == temp[i][j]) {
								s.pop();
								resultS.push(temp[i][j] * 2);
							}else {
								resultS.push(s.pop());
								s.push(temp[i][j]);
							}
						}
						temp[i][j] = 0;
					}
					while(!s.isEmpty()) resultS.push(s.pop());
					for(int j = resultS.size() - 1; j >= 0; j--) temp[i][j] = resultS.pop();
				}
				break;
			}
		}
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, temp[i][j]);
			}
		}
		return max;
	}
	
	public static void perm(int cnt) {
		if(cnt == 5) {
			answer = Math.max(answer,  combine());
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			moveOrder[cnt] = i;
			perm(cnt+1);
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i< N; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());        		
        	}
        }
        
        perm(0);
        
        System.out.println(answer);
    }
}