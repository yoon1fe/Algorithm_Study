## [2019 KAKAO BLIND RECRUITMENT] 길 찾기 게임 - Java

###    :video_game: ​분류

> 구현



###  :video_game: 코드

```java
import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
		int x, y, no;
		Node(int x, int y, int no){
			this.x = x; this.y = y; this.no = no;
		}
		
		public int compareTo(Node n){
			if(this.y == n.y) return this.x - n.x;
			return n.y - this.y;
		}
	}
	
	static int maxX;
	static int[][] answer;
	static List<Integer> pre = new ArrayList<>();
	static List<Integer> post = new ArrayList<>();
	static List<Node> nodeList;
	
    public static int[][] solution(int[][] nodeinfo) {
    	nodeList = new ArrayList<>();
    	
    	answer = new int[2][nodeinfo.length];
        for(int i = 0; i < nodeinfo.length; i++) {
        	maxX = Math.max(maxX, nodeinfo[i][0]);
        }
        
        for(int i = 0; i < nodeinfo.length; i++) {
        	nodeList.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }

        Collections.sort(nodeList);
        
        preorder(0, 0, maxX+1);
        postorder(0, 0, maxX+1);
        
        for(int i = 0; i < pre.size(); i++) {
        	answer[0][i] = pre.get(i);
        	answer[1][i] = post.get(i);
        }
        
        return answer;
    }
    
    public static void preorder(int idx, int left, int right) {
    	if(idx != -1) {
    		pre.add(nodeList.get(idx).no);
    		preorder(leftChild(idx, left), left, nodeList.get(idx).x);
    		preorder(rightChild(idx, right), nodeList.get(idx).x, right);
    	}
    }
    
    public static void postorder(int idx, int left, int right) {
    	if(idx != -1) {
    		postorder(leftChild(idx, left), left, nodeList.get(idx).x);
    		postorder(rightChild(idx, right), nodeList.get(idx).x, right);
    		post.add(nodeList.get(idx).no);
    	}
    }
    
    public static int leftChild(int idx, int left) {
    	for(int i = idx + 1; i < nodeList.size(); i++) {
    		if(left <= nodeList.get(i).x && nodeList.get(i).x < nodeList.get(idx).x) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public static int rightChild(int idx, int right) {
    	for(int i = idx + 1; i < nodeList.size(); i++) {
    		if(nodeList.get(idx).x < nodeList.get(i).x && nodeList.get(i).x <= right) {
    			return i;
    		}
    	}
    	return -1;
    }
}
```



### :video_game: ​풀이 방법

맨 첨에 문제에 나온 대로 2차원 배열 이쁘게 만들어서 풀었는데...

채점하자마자 무수한 메모리 초과 세례가 떴습니다

왜그렁고보니 좌표값의 최댓값이 10만이어씀다...

10만 * 10만 = 100억??

도랏멘

암튼 그래서... **좌표값과 노드의 번호**를 갖는 클래스를 선언해서 얘들로 리스트만들어서 풀었습니다...

탐색을 편하게 해주기 위해 리스트를 먼저 y좌표 내림차순으로 정렬하고, y 좌표가 같으면 x좌표 오름차순으로 정렬했습니다.

이렇게 하면 리스트에 i번째로 들어 있는 노드의 자식 노드를 찾으려면 i+1번째 부터 살펴봐주면 되겠져.

preorder의 파라미터로는

- **해당 노드의 리스트에서의 인덱스**

- **해당 노드 밑에 올 수 있는 x 좌표의 최솟값**
- **해당 노드 밑에 올 수 있는 x 좌표의 최댓값**

입니다.

 

그럼 그 밑에 재귀적으로 preorder를 호출할 때는

- **왼쪽으로 가려면 preorder(leftChild, left, cur.x);**

- **오른쪽으로 가려면 preorder(rightChild, cur.x+1, right);**

로 가주면 되겠주.

 

그리고 자식노드를 찾아줄 땐

- **leftChild 찾을 땐 left <= x < cur.x**
-  **rightChild 찾을 땐 cur.x < x <= right**

에 해당되는 친구를 찾아주면 되겠쬬!



###  :video_game: 후기 

항상!!

인풋 데이터의 최댓값을 주의합시다!!!!!!!!!!!!!!!!

ㅜㅜㅜㅜ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!