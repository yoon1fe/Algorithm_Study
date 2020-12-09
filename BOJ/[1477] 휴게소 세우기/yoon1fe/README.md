## [1477] 휴게소 세우기 - Java

### :house: 분류

> 우선순위 큐



### :house: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Rest {
		// 휴게소없는 구간의 길이, 그 안에 있는 휴게소 수 (cnt - 1)개
		int distance, cnt;
		
		Rest(int d, int c) {
			this.distance = d; this.cnt = c;
		}
	}
	
	static int N, M, L;
	static List<Integer> pos = new ArrayList<>();
	static PriorityQueue<Rest> pq = new PriorityQueue<>(new Comparator<Rest>(){
		public int compare(Rest o1, Rest o2) {
			int a = o1.distance / o1.cnt;
			int b = o2.distance / o2.cnt;
			
			if(o1.distance % o1.cnt != 0) a++;
			if(o2.distance % o2.cnt != 0) b++;
			
			return Integer.compare(b, a);
		}
	});
	
	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(solve());
	}
	
	public static int solve() {
		while(M-- > 0) {
			Rest cur = pq.poll();
			
			pq.offer(new Rest(cur.distance, cur.cnt+1));
		}
		
		Rest top = pq.poll();
		// 소수점 아래 있으면 +1 해서 출력
		return top.distance % top.cnt == 0 ? top.distance / top.cnt : (top.distance / top.cnt) + 1;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			pos.add(Integer.parseInt(st.nextToken()));
		}
		pos.add(0);
		pos.add(L);

		Collections.sort(pos);
		
		for(int i = 0; i < pos.size() - 1; i++) {
			pq.offer(new Rest(pos.get(i+1) - pos.get(i), 1));
		}
	}
}
```



### :house: 풀이 방법

 PQ를 써서 풀 수 있습니다.

 아니 근데 C++ 코드랑 똑같은데 계속 틀렸다고 뜨네 내 코드는

이해가 안된다 진짜 세네시간을 붙잡고 있었네



암튼 위의 코드는..휴게소 사이의 거리들을 PQ에 넣고 거기 안에 휴게소를 새로 세운 개수를 갖고 풉니다... 

맨첨에 거리 내림차순으로 PQ에 넣고 빼면서 반으로 놈갈라서 다시 넣는 식으로 햇는데..

이러면 안됩니다.

반례:

2 3 600

300 500



만약 그냥 반으로 띵가먹는 식으로 하면

0-150-300-400-500 이렇게 해서 150이 되겠져

하지만 0-100-200-300-400-500 이 정답이 됩니당...

그래서 휴게소가 없는 공간에 휴게소를 새로 세울 수 있으면 여러개를 놓음으로써 분모를 늘려주는 것이죵...



### :house: 후기

아니 진짜 이해할 수가 없ㄷ ㅏㅡㅡㅡㅡㅡㅡ