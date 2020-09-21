# [14890] 경사로 - Java

###  :arrow_right: 분류

> 시뮬레이션

​

### :arrow_right: 코드

```java
import java.util.Scanner;

class Main{
    static int[][] map;
    static int L;
    static int ans = 0;

    static void solve(int[] line) {
        int start = line[0];
        boolean[] checked = new boolean[line.length];
        boolean possible = true;

        outer:
        for(int i = 1; i< line.length; i++){
            int diff = Math.abs(start - line[i]);
            if(diff > 1) {
                possible = false; break;
            }
            if(start > line[i]){
                for(int j = i; j < i+L; j++){
                    if(j >= line.length || checked[j] || line[j] != line[i]){
                        possible = false;  break outer;
                    }
                    checked[j] = true;
                }
            }else if(start == line[i]) continue;
            else{
                for(int j = i-1; j > i-1 - L; j--){
                    if(j < 0 || checked[j] || line[j] != start){
                        possible = false; break outer;
                    }
                    checked[j] = true;
                }
            }
            start = line[i];
        }

        ans = possible == true ? ans+1 : ans;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        L = sc.nextInt();
        map = new int[N*2][N];

        for(int i= 0; i< N ;i ++)
            for(int j = 0; j< N; j++)
                map[i][j] = sc.nextInt();

        for(int i = 0; i< N; i++)
            for(int j = 0; j< N; j++)
                map[i+N][j] = map[j][i];

        for(int i = 0; i< 2*N; i++) solve(map[i]);

        System.out.println(ans);
    }
}
```



### :arrow_right: 풀이 방법

삼성전자 코테 기출문제입니다.. 특별한 알고리즘을 요구하지는 않는 시뮬레이션 문젠데 이것저것 고려해야 할게 넘 많아서 예전에 못풀었다가 요번에 풀어씀다.. 꽤 빨리 풀긴 했는데 코드가 쪼까 지저분합니다 ㅜ



`map`의 행, 열을 봐줘야 하기 때문에 열들을 걍 행 밑에 넣어주고 `N\*2`만큼 돌렸슴다..

`checked` 배열에 경사로를 놓았는지 여부를 저장해줍니다..

`start`와 `line\[i\]`의 차가 1이 넘으면 경사로를 놓을 수 없기 때문에 바로 반복문을 나오고,

아닌 경우에는 세가지 경우로 나눠서 풀었씁니다

`start`가 크면 뒤에 경사로를 놓을 수 있는지 확인을 해주고,

`line\[i\]`가 크면 앞에 경사로를 놓을 수 있는지 확인을 해줍니다.

이 때, `1) 인덱스가 배열을 벗어나는지, 2) 경사로가 이미 놓여있는지, 3) 경사로를 놓을 자리들의 높이가 같은지` 확인을 해주어야 합니다.



지나갈 수 있는지 여부를 저장하는 `boolean` 변수 `possible`을 두고 `ans`를 업데이트해주면 됩니다.



### :arrow_right: 후기

쫌 더 간단하게 풀 수 있을거같은데....
