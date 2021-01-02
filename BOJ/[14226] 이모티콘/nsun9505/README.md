# [14226] 이모티콘 - JAVA

## 분류
> DP
>
> BFS

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[2*N+1][2*N+1];
        int ans = Integer.MAX_VALUE;
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(1, 0, 0));
        visited[1][0] = true;

        while(!queue.isEmpty()){
            Element elem = queue.poll();

            if(elem.num == N){
                System.out.println(elem.cnt);
                break;
            }

            if(elem.num + elem.num <= 2*N && !visited[elem.num][elem.num]) {
                queue.offer(new Element(elem.num, elem.cnt + 1, elem.num));
                visited[elem.num][elem.num] = true;
            }
            if(elem.num + elem.clipboard <= 2*N && !visited[elem.num + elem.clipboard][elem.clipboard]) {
                queue.offer(new Element(elem.num + elem.clipboard, elem.cnt + 1, elem.clipboard));
                visited[elem.num + elem.clipboard][elem.clipboard] = true;
            }
            if(elem.num - 1 > 0 && !visited[elem.num - 1][elem.clipboard]) {
                queue.offer(new Element(elem.num - 1, elem.cnt + 1, elem.clipboard));
                visited[elem.num-1][elem.clipboard] = true;
            }
        }
    }

    static class Element{
        int num;
        int cnt;
        int clipboard;

        public Element(int num, int cnt, int clipboard){
            this.num = num;
            this.cnt = cnt;
            this.clipboard = clipboard;
        }
    }
}
```

## 문제 풀이
최소 시간을 구하는 문제이니깐 BFS로 풀면 되겠다고 생각했습니다.
   - 그리고 각 클립보드와 이모티콘에 대한 상태를 어떻게 표현해야 할까 고민했습니다.

이모티콘의 개수로는 중복인지 아닌지를 체크하기가 어렵기 때문에 visited 배열을 2차원 배열로 선언했습니다.
   - 그래서 [이모티콘개수][클립보드에 있는 이모티콘 개수]로 방문 표시를 하면 이모티콘의 개수는 같아도 클립보드에 있는 이모티콘의 개수가 다르다면
   - 이모티콘개수 + 클립보드에 있는 이모티콘의 개수로 다른 상태를 가질 수 있습니다.

그리고 `` 이모티콘개수 + 클립보드에 있는 이모티콘의 개수 또는 이모티콘 개수 + 이모티콘 개수 ``가 N*2가 되지 않는 경우에만 큐에 넣어줍니다.
   - 이모티콘을 만드는데 1개를 클립보드에 넣고 계속해서 붙인 경우 N개를 만드는데 N초가 걸릴 것이다.
   - 그러면 N*2을 넘는 이모티콘의 개수는 필요가 없을 것이다.
   - 왜냐하면 N*2개에서 1개씩 지우면서 오는 것도 N초가 넘게 걸리므로 어떤 수에서든지 N초 이하로 해결할 수 있다.
   - 그러므로 N초를 넘기는 상태를 저장할 필요는 없기 때문이다.
   - 테스트를 해보니 N초로 하면 틀리고, N*2로 검사하면 맞음!

## 후기
거의 다 풀었는데 2*N을 생각 못해서ㅠ

2*N!!! 문제를 읽고 깊게 생각해야겠습니다!

파이팅!!!