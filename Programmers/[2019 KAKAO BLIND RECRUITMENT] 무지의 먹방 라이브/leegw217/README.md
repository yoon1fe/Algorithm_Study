# [2019 KAKAO BLIND RECRUITMENT] 무지의 먹방 라이브 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.Arrays;
import java.util.Comparator;
public class week06_무지의먹방라이브 {
	class Node{
		int index;
		int value;
		Node(int index, int value){
			this.index = index;
			this.value = value;
		}
	}
	public int solution(int[] food_times, long k) {
        Node[] nodes = new Node[food_times.length];
        for(int i=0; i<food_times.length; i++)
        	nodes[i] = new Node(i+1, food_times[i]);
        
        Arrays.sort(nodes, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.value - o2.value;
			}
		});
        
        long pre = 0;
        for(int i=0; i<food_times.length; i++) {
        	long minus  = nodes[i].value - pre;
        	if(minus != 0) {
        		minus *= (food_times.length-i);
        		if(minus <= k) {
        			k -= minus;
        			pre = nodes[i].value;
        		} else {
        			k %= food_times.length-i;
        			Node[] temp = Arrays.copyOfRange(nodes, i, food_times.length);
        			Arrays.sort(temp, new Comparator<Node>() {
        				@Override
        				public int compare(Node o1, Node o2) {
        					return o1.index - o2.index;
        				}
        			});
        			return temp[(int)k].index;
        		}
        	}
        }
        return -1;
    }
}
```

### :octocat: 풀이 방법

1. 먼저 index와 value를 담은 Node 배열을 만든다.
2. value를 오름차순으로 정렬한다.
3. (T[i] - T[i-1)*(N-i) 한 값이 남은 k값보다 클때까지 k에서 계산한 값을 계속 빼준다.
4. k값보다 커지면 남은 k값에서 (N-i)한 값을 나눈 나머지를 구한다.
5. 배열에서 i부터 N까지에 해당하는 index를 다시 오름차순으로 정렬한다.
6. Node[구한 나머지].index 값이 결과다. 제한시간 전에 모든 음식을 먹을 수 있으면 -1

### :octocat: 후기

분명 예전에 완기랑 같이 풀면서 이해했던 문제였는데 다시 풀어보니 절대 생각이 안났다.
카카오테크 블로그 보고 풀었는데 나머지 관련된 말을 너무 어렵게 해놔서 이해하는데 오래걸렸다;;
