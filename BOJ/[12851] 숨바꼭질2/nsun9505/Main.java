import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] isVisited = new boolean[MAX];
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int targetDist = Integer.MAX_VALUE;
        int targetCnt = 0;

        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(N, 0));

        while(!queue.isEmpty()){
            Element elem = queue.poll();

            if(targetDist < elem.dist)
                continue;

            isVisited[elem.num] = true;

            if(elem.num == M){
                targetDist = Math.min(elem.dist, targetDist);
                targetCnt += 1;
                continue;
            }

            if(elem.num - 1 >= 0 && !isVisited[elem.num - 1])
                queue.offer(new Element(elem.num -1, elem.dist + 1));
            if(elem.num + 1 < MAX && !isVisited[elem.num + 1])
                queue.offer(new Element(elem.num +1, elem.dist + 1));
            if(elem.num * 2 < MAX && !isVisited[elem.num * 2])
                queue.offer(new Element(elem.num * 2, elem.dist + 1));
        }

        System.out.println(targetDist + " " + targetCnt);
    }

    static class Element{
        int num;
        int dist;

        public Element(int num, int dist){
            this.num = num;
            this.dist = dist;
        }
    }
}