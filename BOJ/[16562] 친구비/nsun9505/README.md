# [16562] 친구비 - JAVA

## 분류
> Union-find

## 코드
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int parent[];
	static int money[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		money = new int[N+1];
		parent = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			union(x, y);
		}
		
		for(int i=1; i<=N; i++)
			find(i);
		
		boolean[] check = new boolean[N+1];
		int sum = 0;
		for(int i=1; i<=N; i++) {
			int p = parent[i];
			if(check[p])
				continue;
			sum += money[p];
			check[p] = true;
		}
		
		if(sum <= K)
			sb.append(sum);
		else
			sb.append("Oh no");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int find(int x) {
		if(x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return;
		
		if(money[x] > money[y])
			parent[x] = y;	
		else 
			parent[y] = x;
	}
}
```

## 문제 풀이
유니온 파인드를 사용하면 금방 풀 수 있습니다!

유니온 파인드로 친구들 간에 집합을 만들어 줍니다.

집합을 만들어 줄 때 돈이 가장 적은 애를 루트로 정해주면 됩니다.

대신, 주의할 점이 있다고 하겠습니다.
   - x와 y가 있을 때 money[x] < money[y]라면 y의 부모를 x로 설정해야 합니다.
   - 이때 y에 자식들도 부모를 x로 바꿔줘야 합니다.
   - 이 작업을 M만큼 다 돈 뒤에 하면 답을 정확하게 뽑을 수 있습니다!

## 후기
오늘도 파이팅!!