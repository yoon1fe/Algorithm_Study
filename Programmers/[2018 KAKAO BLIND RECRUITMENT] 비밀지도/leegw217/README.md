# [2018 KAKAO BLIND RECRUITMENT] 비밀지도 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.LinkedList;
public class week18_비밀지도 {
	LinkedList<Integer> makeBinary(int n, int num) {
		LinkedList<Integer> binary = new LinkedList<Integer>();
		int q = num;
		int r = 0;
		while(true) {
			if(q < 2) {
				binary.addFirst(q);
				break;
			}
			r = q % 2;
			binary.addFirst(r);
			q = q / 2;
		}
		int m = n - binary.size();
		for(int i=0; i<m; i++) binary.addFirst(0);
		return binary;
	}
	
	public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i=0; i<n; i++) {
        	LinkedList<Integer> l1 = makeBinary(n, arr1[i]);
        	LinkedList<Integer> l2 = makeBinary(n, arr2[i]);
        	String s = "";
        	for(int j=0; j<n; j++) {
        		if(l1.get(j) == 0 && l2.get(j) == 0) s += " ";
        		else s += "#";
        	}
        	answer[i] = s;
        }        
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 각 인덱스에 해당하는 숫자들을 이진수로 바꿔서 연결리스트에 담는다.
2. 각 연결리스트 인덱스들을 비교하면서 둘다 0이면 공백, 아니면 #을 문자열에 담는다.
3. 문자열을 정답 배열에 넣는다.

### :octocat: 후기

그냥 arraylist써도 될거같긴한데 좀더 시간줄여볼려고 연결리스트를 써보았다!
역시 1번문제!
