# [2019 KAKAO BLIND RECRUITMENT] 길 찾기 게임

## 분류
> DFS
>
> 구현

## 코드
```java
import java.util.*;

public class Solution {
    List<Node> nodes = new ArrayList<>();
    int[][] ans;
    int preOrderIndex = 0;
    int posOrderIndex = 0;
    HashMap<Integer, ArrayList<Node>> levelMap = new HashMap<>();
    public int[][] solution(int[][] nodeinfo) {
        ans = new int[2][nodeinfo.length];

        for(int i=0; i<nodeinfo.length; i++)
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));

        Collections.sort(nodes);
        for(Node node : nodes){
            if(!levelMap.containsKey(node.y))
                levelMap.put(node.y, new ArrayList<Node>());
            ArrayList<Node> list = levelMap.get(node.y);
            list.add(node);
        }

        createTree(nodes.get(0), 0, 100001);

        DFS(nodes.get(0));
        return ans;
    }

    public void createTree(Node cur, int minX, int maxX) {
        ArrayList<Node> list = null;
        
        ans[0][preOrderIndex++] = cur.index;

        for(int level=cur.y-1; level>=0; level--){
            if(levelMap.containsKey(level)){
                list = levelMap.get(level);
                break;
            }
        }

        if(list == null){
            ans[1][posOrderIndex++] = cur.index;
            return;
        }

        for(Node child : list){
            if(minX <= child.x && child.x < cur.x){
                cur.leftChild = child;
                createTree(child, minX, cur.x);
                break;
            }
        }

        for(Node child : list){
            if(cur.x < child.x && child.x < maxX){
                cur.rightChild = child;
                createTree(child, cur.x+1, maxX);
            }
        }

        ans[1][posOrderIndex++] = cur.index;
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int index;
        Node leftChild;
        Node rightChild;

        public Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.leftChild = null;
            this.rightChild = null;
        }

        @Override
        public int compareTo(Node o) {
            if(this.y < o.y)
                return 1;
            else if(this.y == o.y)
                return Integer.compare(this.x, o.x);
            return -1;
        }
    }
}
```

## 문제 풀이
DFS로 트리를 만드는게 스택을 터지게 하지 않을까 싶었지만, 잘 통과했습니다!

DFS말고 다른 방법을 생각하다가, 이리저리 방법을 생각해봤지만 쉽지 않았네요!

각 노드를 y에 대해서 내림차순, x에 대해서 오름차순으로 정렬합니다.

그리고 레벨에 따라 각 노드를 분리 합니다.

레벨 L을 가지는 노드는 자신 보다 아래에 있는 노드, 즉 자식 노드 후보들이 담긴 리스트를 가져옵니다.

찾았다면, 왼쪽 자식 노드를 결정하기 위해서는 minX <= 자식의 x 값 < X를 만족하는 노드를 찾습니다.
   - 왼쪽 자식 노드이므로 minX는 조상의 x 값이 될 수도 있고, 조상이 root라면 0이 될것입니다.
   - X는 현재 노드의 x 값입니다.
   - 그래서 minX와 X 사이에 존재해야 왼쪽 자식 노드가 될 수 있습니다.
   - 왼쪽 자식 노드를 찾았다면 해당 자식 노드에도 연결할 트리들을 연결해줍니다.

오른쪽 자식 노드를 결정하기 위해서는 X < 자식의 x 값 <= maxX를 만족하는 노드를 찾습니다.
    - 오른쪽 자식 노드이므로 조상 노드의 x 값이거나, 루트 노드의 X 값이 되겠습니다. 또는 100000 이하입니다.
    - 오른쪽 자식 노드를 찾앗다면, 해당 자식 노드에도 연결할 서브 트리들을 연결해줍니다.

트리를 만들면서 왼쪽 서브 트리를 만든 다음에 오른쪽 서브 트리를 만들기 때문에 전위탐색, 후위탐색이 가능합니다.
   - 처음 노드를 방문 했을 때 preorder 배열에 현재 노드의 인덱스를 넣어주면 됩니다.
   - left와 right를 만들어 준 다음 또는 현재 노드의 레벨 아래에 더 이상 노드가 없는 경우에 preorder 배열에 현재 노드의 인덱스를 넣어주면 됩니다.

## 후기
편향 트리로 길이가 10000개인 친구가 들어오면 터지지 않을까 싶었는데.. 흠 멀까유!

메모리에 대한 공부를 좀 해봐야 겠습니다.

오늘 하루도 파이팅!