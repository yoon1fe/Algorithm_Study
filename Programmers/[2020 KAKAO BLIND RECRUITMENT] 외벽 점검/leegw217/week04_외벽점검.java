import java.util.ArrayList;
import java.util.List;

public class week04_외벽점검 {
	static int[] result;
	static boolean[] visited;
	static int answer;
	static List<int[]> perms;
	static List<Integer> perm;
	
	static void makePerm(int cnt, int n, int[] weak, int[] dist) {
		if(cnt == result.length) {
			perms.add(result.clone());
			return;
		}
		for(int i=0; i<dist.length; i++) {
			if(!visited[i]) {
				result[cnt] = dist[i];
				visited[i] = true;
				makePerm(cnt+1, n, weak, dist);
				visited[i] = false;
			}
		}
	}
	
	public int solution(int n, int[] weak, int[] dist) {
		for(int f=1; f<=dist.length; f++) {
			perms = new ArrayList<int[]>();
			result = new int[f];
			visited = new boolean[dist.length];
			makePerm(0,n,weak,dist);
			for(int p=0; p<perms.size(); p++) {
				perm = new ArrayList<Integer>();
				for(int start=0; start<weak.length; start++) {
					List<Integer> wall = new ArrayList<Integer>();
					for(int i=0; i<f; i++) perm.add(perms.get(p)[i]);
					for(int i=start; i<weak.length; i++) wall.add(weak[i]);
					for(int j=0; j<start; j++) wall.add(weak[j]+n);
					while(wall.size()!=0 && perm.size()!=0) {
						int cur = wall.remove(0);
						int di = perm.remove(0);
						int cover = cur + di;
						while(wall.size()!=0 && wall.get(0)<=cover)
							wall.remove(0);
					}
					if(wall.size() == 0) return f;
				}
			}
			perms.clear();
		}
		return -1;
    }
}