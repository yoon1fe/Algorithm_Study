# [9095] 1, 2, 3 더하기 - JAVA

## 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[11];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        for(int i=4; i<11; i++)
            arr[i] = arr[i-1] + arr[i-2] + arr[i-3];

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            System.out.println(arr[N]);
        }
    }

    /*public static void solution(int num, int N){
        if(num >= N) {
            if(num == N)
                ans++;
            return;
        }
        solution(num+1, N);
        solution(num+2, N);
        solution(num+3, N);
    }*/
}
```

## 문제 풀이
DP는 내가 푼 게 맞는지 알아보기 위해서 해결한 뒤에 답을 찾아 봤다.

### 첫 번째 방법
```java
    int[] arr = new int[11];
    arr[1] = 1;
    arr[2] = 2;
    arr[3] = 4;

    for(int i=4; i<11; i++)
        arr[i] = arr[i-1] + arr[i-2] + arr[i-3];

    for(int i=0; i<T; i++){
        int N = Integer.parseInt(br.readLine());
        System.out.println(arr[N]);
    }
```
   - 1을 만드는 방법은 1 하나이다.
   - 2를 만드는 방법은 1+1, 2 두 개이다.
   - 3을 만드는 방법은 1+1+1, 1+2, 2+1, 3 네 개이다.
   - 문제는 주어지는 N을 1,2,3으로 만드는 것이므로 미리 1,2,3에 대해서 만들어 놓는다.(작은 것부터 해결!)
   - 예를 들어, 4를 만드는 방법은 1을 만드는 방법에 +3, 2를 만드는 방법에 +2씩, 3을 만드는 방법에 +1씩하면 4를 만들 수 있는 것이다.
   - N을 만들기 위해서는 arr[N-1], arr[N-2], arr[N-3]만 있으면 만들 수 있는 것이다.
   - 그러면 N을 구하기 전에 N-1까지 배열에 저장되어 있어야 하므로 여기서는 최대 11미만으로 입력되므로 10까지 미리 구해서 입력으로 주어지는 N을 출력만 하면 된다!
<br><br>

### 두 번째 방법 : 직관적이다..
```java
public static void solution(int num, int N){
        if(num >= N) {
            if(num == N)
                ans++;
            return;
        }
        solution(num+1, N);
        solution(num+2, N);
        solution(num+3, N);
    }
```
   - `solution(0, N)`을 호출
   - num에 +1, +2, +3을 해서 주어진 N보다 크다면 진행하지 않는다.
   - num이 N이라면 카운트
   - 아직 num이 N에 다다르지 않았다면, 다시 +1, +2, +3을 해서 진행한다.
   - 완전탐색인 것이다. 주어지는 범위가 작아서 충분히 풀리기는 하나 DP를 연습하는 것은 아니지 싶다.
<br><br>

## 후기
DP로 풀기 위해 생각하는 연습을 하자!