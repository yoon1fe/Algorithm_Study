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