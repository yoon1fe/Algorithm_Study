# [1655] 가운데를 말해요 - Java

## 분류
> 우선순위 큐

## 코드
```java
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
```

## 문제 풀이
중간값 구하기 알고리즘은 다음과 같다.
1. 최대 힙의 크기는 최소 힙의 크기와 같거나, 하나 더 크다.
2. 최대 힙의 top은 최소 힙의 top보다 작거나 같다.
   - 이때 알고리즘에 맞지 않다면 최대 힙, 최소 힙의 가장 위의 값을 swap해준다.

[결과] 이때 이 두가지 규칙을 유지해 준다면 항상 최대 힙 top값이 중간값이 된다.

출처: https://www.crocus.co.kr/625

## 후기
처음에는 이분탐색으로 풀어볼까 하면서 위치를 찾은 다음에 넣어주고 출력하는 방법!

그래서 어떻게 구현할까 고민하다가 분류를 보고 우선순위 큐로 풀 수도 있다고 하길래 처음에는 우선순위 큐에 넣고 거기서 중간 위치의 값을 출력했지만 heap 내에서 top이 min 또는 max이지 안에 있는 값은 어떻게 정렬이 되어있지 않아서 안 된다는 것을 알게 되었다.

그러면 이분탐색으로는 어떻게 풀까 고민하다가 위치를 찾고 배열처럼 중간에 삽입하면 N타임이 걸리니깐 이래도 되나 싶어서 고민하다가 분류에 있는 우선순위 큐를 보고 고민하다가 위의 출처에서 답을 찾아보고 이해했지만 다시 또 봐야할 것 같다.