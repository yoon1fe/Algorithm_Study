## [1655] 가운데를 말해요 - Java

### :two: 분류

> 이분 탐색



### :three: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		input();
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		while(N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			System.out.println(solve(num));
		}
	}
	
	public static int solve(int n) {
		// 이분탐색으로 정렬된 리스트 구하기
		int left = 0, right = list.size() - 1, mid = (left + right) / 2;
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(list.get(mid) > n) {
				right = mid - 1;
			}else {
				left = mid + 1;
				mid++;
			}
		}
		
		list.add(mid, n);
		
		return list.size() % 2 == 0 ? list.get((list.size() / 2) - 1) : list.get(list.size() / 2);
	}
}
```



### :five: 풀이 방법

수빈이가 외치는 수 중에서 가운데에 있는 수를 구하는 문제입니다.

입력을 하나 받을 때마다 구해줘야 합니당.

 

입력으로 주어지는 수들을 계속 정렬된 상태를 유지하도록 한다면 size / 2 로 간단하게 가운데의 수를 구할 수 있겠져.

이것을 이분 탐색으로 구현해씀니다. 

 

입력을 받을 때마다 이분 탐색을 통해 새로 들어온 수가 들어갈 위치를 구하는 것임다. 

들어갈 위치를 잘 구해서 list 에 고대로 넣어주면 됩니다. 그리고 list의 사이즈가 홀수냐 짝수냐에 따라 필요한 가운데 수를 출력해주면 끝!!



### :seven: 후기

사실 맨 처음 문제를 보자마자 이분 탐색을 쓰면 되겠다 생각했는데 문제 분류를 보니 PQ라고 돼있어서 멘붕해씀다;;

이걸 PQ로 우째 풀지!? 함 찾아봐야게쓰다...

감사합니다!!

