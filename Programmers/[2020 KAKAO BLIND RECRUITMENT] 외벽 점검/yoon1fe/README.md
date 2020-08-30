## [2020 KAKAO BLIND RECRUITMENT] 외벽 점검 - Java

###    :ballot_box_with_check: ​분류

> 완전 탐색
>
> 순열



###  :ballot_box_with_check: 코드

```java
import java.util.*;

class Solution {
    boolean[] v;
    int answer;
    List<Integer> perm = new LinkedList<>();
    List<Integer> between;

    public void wallCheck() {
        List<Integer> temp = new ArrayList<>(between);

        for(int k = 0; k < temp.size(); k++) {
            int idx = 0;
            int sum = 0;
            boolean flag = true;
            for(int j = 0; j < temp.size(); j++) {
                int cur = temp.get(j);
                sum += cur;
                if(idx >= perm.size()) {
                    flag = false;
                    break;
                }
                if(perm.get(idx) < sum) {
                    if(idx >= perm.size()) {
                        flag = false;
                        break;
                    }
                    idx++;
                    sum = 0;
                }
            }
            if(flag) {
                answer = Math.min(answer, perm.size());
                break;
            }
            temp.add(temp.remove(0));
        }
    }        


    public void comb(int cnt, int max, int[] dist) {
        if(cnt == max) {
            wallCheck();
            return;
        }

        for(int i = 0; i < dist.length; i++) {
            if(v[i]) continue;
            v[i] = true;
            perm.add(dist[i]);
            comb(cnt+1, max, dist);
            v[i] = false;
            perm.remove((Integer)dist[i]);
        }

    }

    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        between = new ArrayList<>();
        for(int i = 0; i < weak.length-1; i++) {
            between.add(weak[(i + 1) % n] - weak[(i + n) % n]);
        }
        between.add(weak[0] + n - weak[weak.length-1]);

        for(int i = 1; i <= dist.length; i++) {
            v = new boolean[dist.length];
            comb(0, i, dist);
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
```



### :ballot_box_with_check: 풀이 방법

힘드네여...

원형 모양을 어떻게 표현할지는 쉽게 생각났는데,,,, 휴..



먼저 between이란 list를 두었습니다.

취약 지점간의 사이를 저장하는 리스트입니다. 외벽이 원형이기 때문에 끝에 하나를 더 넣었습니다.

 

그리고 dist의 최대 길이가 8이기 때문에 완전 탐색으로 구할 수 있습니다.

근데 여기서 순열을 만들어야 하는데 무슨 생각인건지 부분집합을 만들어서 풀어서 계속 하나가 안되는검니다..

근데 또 희한하게 딱 하나 빼고는 다 통과가 돼서 골치가 많이 아팠습니다...

 

아니 근데 당연히 dist에서 뽑는데 순서가 상관있는데 무슨 생각으로 부분집합을 만든거지??? 

비트마스킹 뽕이 차서 아무 생각없이 그렇듯

 

암튼.. nC1, nC2, ... , nCd (d는 dist의 길이) 이렇게 순열들을 구해주고 이 친구들을 갖고 탐색을 해줍니다...

친구들은 시계방향/반시계방향 모두 이동할 수 있습니다.

 

이에 대한 아이디어는 역시 카카오 기술 블로그에서 얻었습니다....

 

예를 들어 between 배열이 

{4, 3, 4, 5, 1} 이라면

 ```
{4, 3, 4, 5, 1} 상태에서 한 번,

{3, 4, 5, 1, 4} 상태에서 한 번,

{4, 5, 1, 4, 3} 상태에서 한 번,

{5, 1, 4, 3, 4} 상태에서 한 번,

{1, 4, 3, 4, 5} 상태에서 한 번 
 ```

봐주는 겁니다.

세상엔 진짜 똑똑한 사람 많습니다..

 

wallCheck() 메소드도 쪼끔 희한한데

취약 지점간의 거리를 구해놓았기 때문에 순열에 든 값이랑 between 배열의 값이랑 비교해주면서 체크합니다...

 

###   :ballot_box_with_check: 후기

코테에 문제 이렇게 나오면 절대 통과 못할 거 같은데....

돌겠다ㅜㅜ