package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ1655 {
    static int N;
    static PriorityQueue<Integer> minHeap;
    static PriorityQueue<Integer> maxHeap;
    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<N; i++) {
            int value = Integer.parseInt(br.readLine());
            if(minHeap.size() == maxHeap.size())
                maxHeap.offer(value);
            else
                minHeap.offer(value);

            if(!maxHeap.isEmpty() && !minHeap.isEmpty() && (maxHeap.peek() > minHeap.peek())){
                int minVal = minHeap.poll();
                int maxVal = maxHeap.poll();

                minHeap.offer(maxVal);
                maxHeap.offer(minVal);
            }
            System.out.println(maxHeap.peek());
        }
    }
}