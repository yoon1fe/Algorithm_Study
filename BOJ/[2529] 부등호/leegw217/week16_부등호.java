import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week16_부등호 {
	static int N;
	static char[] sign;
	static int[] number;
	static boolean[] visited;
	static StringBuilder sb;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	static boolean maxDFS(int cnt) {
		if(cnt == N+1) {
			System.out.println(sb.toString());
			return true;
		}
		
		for(int i=9; i>=0; i--) {
			if(visited[i]) continue;
			if(cnt == 0) {
				sb.append(i);
				visited[i] = true;
				if(maxDFS(cnt+1)) return true;
				visited[i] = false;
				sb.deleteCharAt(cnt);			
			} else if(check(cnt, i)) {
				sb.append(i);
				visited[i] = true;
				if(maxDFS(cnt+1)) return true;
				visited[i] = false;
				sb.deleteCharAt(cnt);
			}
		}
		return false;
	}
	
	static boolean minDFS(int cnt) {
		if(cnt == N+1) {
			System.out.println(sb.toString());
			return true;
		}
		
		for(int i=0; i<10; i++) {
			if(visited[i]) continue;
			if(cnt == 0) {
				sb.append(i);
				visited[i] = true;
				if(minDFS(cnt+1)) return true;
				visited[i] = false;
				sb.deleteCharAt(cnt);			
			} else if(check(cnt, i)) {
				sb.append(i);
				visited[i] = true;
				if(minDFS(cnt+1)) return true;
				visited[i] = false;
				sb.deleteCharAt(cnt);
			}
		}
		return false;
	}
	
	static boolean check(int cnt, int num) {
		switch(sign[cnt-1]) {
		case '<':
			if(sb.charAt(cnt-1)-'0' < num) return true;
			break;
		case '>':
			if(sb.charAt(cnt-1)-'0' > num) return true;
			break;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		sign = new char[N];
		for(int i=0; i<N; i++) sign[i] = st.nextToken().charAt(0);
		number = new int[10];
		for(int i=0; i<10; i++) number[i] = i;
		visited = new boolean[10];
		sb = new StringBuilder();
		maxDFS(0);
		visited = new boolean[10];
		sb = new StringBuilder();
		minDFS(0);
	}
}