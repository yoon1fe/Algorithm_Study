# [10868] 최솟값 - JAVA

## 분류
> 세그먼트 트리

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];

        int height = (int)Math.ceil(Math.log(N)/Math.log(2)) + 1;
        int size = (int)Math.pow(2, height);
        tree = new int[size];

        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        makeSegTree(1, 1, N);
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            System.out.println(find(1, 1, N, left, right));
        }

    }

    public static int makeSegTree(int node, int start, int end){
        if(start == end)
            return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = Math.min(makeSegTree(node*2, start, mid), makeSegTree(node*2+1, mid+1, end));
    }

    public static int find(int node, int start, int end, int left, int right){
        if(left > end || right < start)
            return Integer.MAX_VALUE;
        if(left <= start && end <= right)
            return tree[node];
        int mid = (start + end) / 2;
        return Math.min(find(node*2, start, mid, left, right), find(node*2+1, mid+1, end, left, right));
    }
}
```

## 문제 풀이
주어진 구간에서의 최솟값을 찾기 위해 세그먼트 트리를 이용해서 풀 수 있습니다!

세그먼트 트리를 만들기
   - 리프노드는 입력 받은 배열의 원소 값을 설정합니다.
   - 내부노드는 자식 노드 중에서 가장 작은 값을 저장합니다.
   - 물론 내부 노드가 담당하는 범위 내에서 가장 작은 값을 가지게 됩니다.

세그먼트 트리에서 값 찾기
   - 주어진 범위가 현재 노드가 담당하는 범위가 아니라면 Integer 범위에서 가장 큰 값을 리턴해서 해당 노드가 답에 포함되지 않도록 합니다.
   - 주어진 범위가 현재 노드를 완전히 포함한다면 더 이상 아래로 내려갈 필요 없이 현재 노드의 값을 리턴합니다.
   - 주어진 범위가 현재 노드에 겹쳐져 있다면 즉, start-end 구간의 어느정도만 포함된다면 중앙값을 기준으로 둘로 나누어서 탐색을 진행한 다음에 둘 중에 가장 작은 값을 리턴합니다.
   - 세그먼트 트리는 Full Binary Tree이기 때문에 무조건 자식이 없거나 둘이므로 둘로 나누어서 탐색하는 것이 가능합니다.
   - 자식이 없으면 더 이상 내려가지 않을 것이고, 자식이 둘이라면 나누어서 탐색을 진행할 것입니다.

## 후기
세 번째가 되지 코드를 보지 않고 짤 수 있었습니다!

자료구조를 구현하는 재미! 꿀잼~