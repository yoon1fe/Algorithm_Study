# [2020 KAKAO BLIND RECRUITMENT] 외벽점검 - Java

###  :octocat: 분류

> 완전탐색
> 
> 순열

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

점검하는 친구 수를 1명부터 늘려가면서 점검한다.
점검하는 친구들은 순열을 사용해 순서를 바꿔준다.
점검해야할 지점은 원형으로 이어져있기 때문에 첫번째 점검할 지점을 옮길때 앞부분은 +n 해줘서 뒤로 붙여
원형처럼 만든다.
현재 점검하는 위치에서 친구가 이동할 수 있는 지점까지 이동하고 지나간 지점에 점검해야할 위치가 있으면 삭제한다.
모든 지점을 점검했다면 친구 수를 출력한다.
1명부터 시작했기 때문에 점검이 끝났을 때 친구 수를 출력한다면 그것이 최소값이다!

### :octocat: 후기

혼자힘으로 풀었을 때 92점에서 더이상 진행이 안됐다... 너무 시간을 많이 잡아먹어서 그냥 다른사람들이 푼거 보고
수정해서 제출했다 .ㅜㅜ 넘모 슬픈 문제였다.
