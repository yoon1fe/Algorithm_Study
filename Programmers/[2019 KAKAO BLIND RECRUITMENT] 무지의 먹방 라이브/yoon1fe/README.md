## [2019 KAKAO BLIND RECRUITMENT] 무지의 먹방 라이브 - Java

###    :rabbit: 분류

> 구현



###  :rabbit: 코드

```java
import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
		long idx, time;

		Node(long idx, long time) {
			this.idx = idx;
			this.time = time;
		}

		public int compareTo(Node n) {
			return (int) (this.time - n.time);
		}
	}

	public static int solution(int[] food_times, long k) {
		long answer = 0;
		Node[] food = new Node[food_times.length];

		for (int i = 0; i < food_times.length; i++) {
			food[i] = new Node(i + 1, food_times[i]);
		}
		Arrays.sort(food);

		long sum = 0;
		long remainder = 0;
		int size = food.length;
		int idx = 0; // 처리된 음식순서 다음번 index
		boolean flag = false; // k가 food_times를 넘어갈경우

		for (int i = 0; i < size; i++) {
			long perv = sum;

			if (i == 0) {
				sum += food[i].time * size;
			} else {
				sum += (food[i].time - food[i - 1].time) * (size - i);
			}

			if (sum > k) {
				remainder = k - perv;
				idx = i;
				flag = true;
				break;
			}
			food[i].idx = -1; // 처리된 음식
		}

		if (!flag)
			return -1;

		Arrays.sort(food, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return (int) (o1.idx - o2.idx);
			}
		});

		int len = size - idx;
		int count = 0;
		int ans = 0;
		answer = remainder % (long) len;

		for (int i = 0; i < size; i++) {
			if (food[i].idx == -1)
				continue;
			if (count == answer) {
				ans = i;
				break;
			}
			count++;
		}

		return (int) food[ans].idx;

	}
}
```



### :rabbit: 풀이 방법

효율성 통과 실패!!!!!!!!!!!

기술 블로그 보고 했는데도 실패!!!!!!!!!!!!!!!!

한참 찾았는데도 해결하지 못해서 구글링해서 제출.....



###  :rabbit: 후기

대실패!!!!!