import java.util.Scanner;
public class Main_16637 {
	static int N;
	static int[] num;
	static char[] oper;
	static boolean[] par;
	static int max = Integer.MIN_VALUE;
	
	static void makePar(int idx) {
		if(idx == N/2) {
			execute();
			return;
		}
		
		if(idx==0 || par[idx-1]==false) {
			par[idx] = true;
			makePar(idx+1);
		}
		par[idx] = false;
		makePar(idx+1);
	}
	
	static void execute() {
		int result = 0;
		for(int i=0; i<N/2+1; i++) {
			if(par[i] == false) {
				if(i==0 || par[i-1]==false)
					result = calc(result, oper[i], num[i]);
			} else {
				int n = calc(num[i], oper[i+1], num[i+1]);
				result = calc(result, oper[i], n);
			}
		}
		max = Math.max(max, result);
	}
	
	static int calc(int a, char op, int b) {
		int result = 0;
		switch(op) {
		case '+': result = a + b; break;
		case '-': result = a - b; break;
		case '*': result = a * b;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N/2+1];
		oper = new char[N/2+1];
		par = new boolean[N/2+1];
		String s = sc.next();
		int idx = 0;
		oper[0] = '+';
		for(int i=0; i<N; i=i+2) {
			num[idx++] = s.charAt(i) - '0';
			if(i+1 != N) oper[idx] = s.charAt(i+1);
		}
		makePar(0);
		System.out.println(max);
	}
}