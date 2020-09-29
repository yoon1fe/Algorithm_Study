package week08;

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