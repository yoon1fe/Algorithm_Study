import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int[] A;
	static int[] inputOp = new int[4];
	static int[] op;
	static int[] isSelected;
	static int max = -1000000001;
	static int min = 1000000001;
	
	static void calc() {
		List<Integer> expr = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			expr.add(A[i]);
			if(i == N - 1) break;
			expr.add(op[i]);
		}
		
		int result = expr.get(0);
		
		for(int i = 1; i < (op.length * 2); i+=2) {
			switch(expr.get(i)) {
			case 0: result += (expr.get(i+1)); break;
			case 1: result -= (expr.get(i+1)); break;
			case 2: result *= (expr.get(i+1)); break;
			case 3: result /= (expr.get(i+1)); break;
			}
		}
		
		max = Math.max(max, result);
		min = Math.min(min, result);
	}
	
	static void makeComb(int cnt) {
		if(cnt == N-1) {
			calc();
			return;
		}
		
		for(int i = 0; i< 4; i++) {
			if(isSelected[i] < 1) continue;
			isSelected[i]--;
			op[cnt] = i;
			makeComb(cnt+1);
			isSelected[i]++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		A = new int[N];
		op = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 4; i++) inputOp[i] = Integer.parseInt(st.nextToken());
		isSelected = inputOp.clone();
		
		makeComb(0);
				
		System.out.println(max + "\n" + min);
	}
}