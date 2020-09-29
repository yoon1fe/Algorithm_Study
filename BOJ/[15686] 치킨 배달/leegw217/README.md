# [15686] 치킨 배달 - Java

###  :octocat: 분류

> 구현
>
> 조합

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class week08_치킨배달 {
	static int N, M;
	static List<Point> chicken = new ArrayList<Point>();
	static List<Point> house = new ArrayList<Point>();
	static int[] arr;
	static int[] result;
	static int min = Integer.MAX_VALUE;
	
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static void makeComb(int begin, int cnt) {
		if(cnt == result.length) {
			int r = calcChickenLength(result);
			if(min > r) min = r;
			return;
		}
		
		for(int i=begin; i<arr.length; i++) {
			result[cnt] = arr[i];
			makeComb(i+1, cnt+1);
		}
	}
	
	static int calcChickenLength(int[] result) {
		int cityLen = 0;
		for(int i=0; i<house.size(); i++) {
			int len = Integer.MAX_VALUE;
			for(int j=0; j<result.length; j++) {
				int x = Math.abs(house.get(i).x - chicken.get(result[j]).x);
				int y = Math.abs(house.get(i).y - chicken.get(result[j]).y);
				if(len > x+y) len = x+y;
			}
			cityLen += len;
		}
		return cityLen;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 1) house.add(new Point(i,j));
				if(n == 2) chicken.add(new Point(i,j));
			}
		}
		arr = new int[chicken.size()];
		result = new int[M];
		for(int i=0; i<arr.length; i++) arr[i] = i;
		
		makeComb(0,0);
		System.out.println(min);
	}
}
```

### :octocat: 풀이 방법

1. 폐업시키지 않을 치킨집 M개를 조합으로 만든다.
2. 조합에 해당하는 치킨집 빼고 다 폐업시킨 다음 치킨 거리를 계산한다.
3. 치킨거리중 최솟값을 찾아 출력!

### :octocat: 후기

싸피에서 풀었던 문제라 코드 짰던거 읽어보고 바로 제출!
