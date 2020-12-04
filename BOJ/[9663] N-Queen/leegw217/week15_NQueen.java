import java.util.Scanner;
public class week15_NQueen {
	static int N;
	static int[] col;
	static int ans = 0;
	
	static void queens(int i) {
		if(i == N) {
			ans++;
			return;
		}		
		for(int j=0; j<N; j++) {
			col[i] = j;
			if(promising(i))
				queens(i+1);
		}
	}
	
	static boolean promising(int i) {
		int k = 0;
		boolean promising = true;
		while(k < i && promising) {
			if((col[i] == col[k]) || Math.abs(col[i]-col[k])==Math.abs(i-k))
				promising = false;
			k++;
		}
		return promising;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N];
		queens(0);
		System.out.println(ans);
	}
}