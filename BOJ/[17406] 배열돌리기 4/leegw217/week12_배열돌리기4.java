import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class week12_배열돌리기4 {
	static int N, M, K;
	static int[][] map;
	static int[][] rotate;
	static int[] result;
	static int[] arr;
	static boolean[] visited;
	static int min = 999999;
	
	static void makePermutation(int cnt) {
		if(cnt == result.length) {
			int[][] tempMap = new int[N][M];
			for(int i=0; i<N; i++)
				System.arraycopy(map[i], 0, tempMap[i], 0, map[i].length);
			 
			tempMap = rotateMap(tempMap);
			
			for(int i=0; i<N; i++) {
				int sum = 0;
				for(int j=0; j<M; j++) 
					sum += tempMap[i][j];
				if(min > sum) min = sum;
			}
			return;
		}
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				result[cnt] = arr[i];
				visited[i] = true;
				makePermutation(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	static int[][] rotateMap(int[][] tempMap) {
		for(int k=0; k<K; k++) {
			int[][] tm = new int[N][M];
			for(int i=0; i<N; i++)
				System.arraycopy(tempMap[i], 0, tm[i], 0, tempMap[i].length);
			int r = rotate[result[k]][0]-1;
			int c = rotate[result[k]][1]-1;
			int s = rotate[result[k]][2];
			for(int d=1; d<=s; d++) {
				for(int j=c-d; j<c+d; j++)
					tm[r-d][j+1] = tempMap[r-d][j];
				for(int i=r-d; i<r+d; i++)
					tm[i+1][c+d] = tempMap[i][c+d];
				for(int j=c+d; j>c-d; j--)
					tm[r+d][j-1] = tempMap[r+d][j];
				for(int i=r+d; i>r-d; i--)
					tm[i-1][c-d] = tempMap[i][c-d];
			}
			for(int i=0; i<N; i++)
				System.arraycopy(tm[i], 0, tempMap[i], 0, tm[i].length);
		}
		return tempMap;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		rotate = new int[K][3];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			for(int l=0; l<3; l++) 
				rotate[k][l] = Integer.parseInt(st.nextToken());
		}
		arr = new int[K];
		result = new int[K];
		visited = new boolean[K];
		for(int i=0; i<K; i++) arr[i] = i;
		makePermutation(0);
		System.out.println(min);
	}
}