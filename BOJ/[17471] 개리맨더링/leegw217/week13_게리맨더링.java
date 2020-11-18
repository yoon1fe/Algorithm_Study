import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class week13_게리맨더링 {
	static int N;
	static int[] people;
	static ArrayList<Integer>[] adjlist;
	static int[] arr; 
	static ArrayList<Integer> listA;
	static ArrayList<Integer> listB;
	static int min = Integer.MAX_VALUE;
	static boolean flag = false;
	
	static boolean bfs(int start, ArrayList<Integer> list) {
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int p = q.poll();

			for(int i=0; i<adjlist[p].size(); i++) {
				if(visited[adjlist[p].get(i)]) continue;
				if(!list.contains(adjlist[p].get(i))) continue;
				q.offer(adjlist[p].get(i));
				visited[adjlist[p].get(i)] = true;
			}
		}
		int cnt = 0;
		for(int i=0; i<N; i++) 
			if(visited[i]) cnt++;
		return cnt == list.size() ? true : false;
	}
	
	static void makeProduct(int begin, int cnt, int n) {
		if(cnt == n) {
			for(int i=0; i<N; i++)
                if(!listA.contains(i)) listB.add(i);
			
			if(bfs(listA.get(0), listA) && bfs(listB.get(0), listB)) {
				flag = true;
				int aSum = 0, bSum = 0;
				for(int a=0; a<listA.size(); a++) 
					aSum += people[listA.get(a)];
				for(int b=0; b<listB.size(); b++) 
					bSum += people[listB.get(b)];
				if(min > Math.abs(aSum - bSum)) min = Math.abs(aSum - bSum);
			}
			listB.clear();
			return;
		}
		
		for(int i=begin; i<arr.length; i++) {
			listA.add(arr[i]);
			makeProduct(i+1, cnt+1, n);
			listA.remove(listA.size()-1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		people = new int[N];
		adjlist = new ArrayList[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			adjlist[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++)
				adjlist[i].add(Integer.parseInt(st.nextToken())-1);
		}
		arr = new int[N];
		for(int i=0; i<N; i++) arr[i] = i;
		for(int k=1; k<=N/2; k++) {
			listA = new ArrayList<Integer>();
			listB = new ArrayList<Integer>();
			makeProduct(0, 0, k);
		}
		
		if(!flag) System.out.println(-1);
		else System.out.println(min);
	}
}