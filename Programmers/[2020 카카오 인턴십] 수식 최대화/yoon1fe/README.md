## [2020 카카오 인턴십] 수식 최대화 - Java

###    :heavy_plus_sign: 분류

> 스택
>
> 문자열 처리
>
> 구현



###  :heavy_minus_sign: 코드

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
    static int[] priority = new int[3];    //[0]: + [1]: - [2]: * 의 우선순위    값은 1~3
    static boolean[] isSelected = new boolean[4];
    static List<Integer> numbers;
    static List<Character> operators;
    static long answer = 0;
    static void perm(int cnt) {
        if(cnt == 3) {
            makePostfix();
            return;
        }

        for(int i = 1; i <= 3; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            priority[cnt] = i;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }

    static int convert(char op) {
        switch(op) {
        case '+': return 0;
        case '-': return 1;
        case '*': return 2;
        }
        return -1;
    }

    static void makePostfix() {
        List<String> prefix = new ArrayList<>();
        Stack<Character> s = new Stack<>();

        for(int i = 0; i < numbers.size(); i++) {
            prefix.add(numbers.get(i).toString());
            if(i == numbers.size() - 1) break;
            char curOp = operators.get(i);
            if(s.isEmpty()) s.push(curOp);
            else {
                if(priority[convert(s.peek())] < priority[convert(curOp)]) {
                    s.push(curOp);
                }else {
                    inner:
                    do {
                        prefix.add(s.pop().toString());
                        if(s.isEmpty()) break inner;
                    } while(priority[convert(s.peek())] >= priority[convert(curOp)]);
                    s.push(curOp);
                }
            }
        }

        while(!s.isEmpty()) {
            prefix.add(s.pop().toString());
        }

        calculate(prefix);
    }

    private static void calculate(List<String> prefix) {
        Stack<Long> s = new Stack<>();
        for(int i = 0; i < prefix.size(); i++) {
            String cur = prefix.get(i);
            if(cur.equals("+") || cur.equals("-") || cur.equals("*")) {
                long op2 = s.pop();
                long op1 = s.pop();
                switch(cur) {
                case "+": s.add(op1 + op2); break;
                case "-": s.add(op1 - op2); break;
                case "*": s.add(op1 * op2); break;
                }
            }else {
                s.add(Long.parseLong(cur));
            }
        }

        answer = Math.max(answer, Math.abs(s.pop()));
    }

    public static long solution(String expression) {
        StringTokenizer st = new StringTokenizer(expression, "+|-|*");
        numbers = new ArrayList<Integer>();
        operators = new ArrayList<Character>();
        for(int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) - '0' < 0 || expression.charAt(i) - '0' > 9) operators.add(expression.charAt(i));
        }

        while(st.hasMoreTokens()) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
        perm(0);

        return answer;
    }
}
```



### :heavy_multiplication_x: 풀이 방법

아직까진 무난합니다....

스택 + 후위표기식 연습해보겠답시고 이렇게 풀었는데.. 코테때 이렇게 짜면 시간 엄청 잡아 먹을듯



휴.. 후위표기식만들 때 부호 하나 반대로 해서 한참 찾았습니다....ㅜ

먼저 우선순위를 정해줍니다.

연산자는 +, -, * 세개이므로 총 6가지가 나옵니다. 순열을 이용해서 만들었습니다.



다 만들고 나서 이 우선순위를 토대로 후위 표기식을 만듭니다. 이 때, 연산자 리스트를 반복해 돌면서 계산하면 훨씬 더 편리합니다. 문제에 나와있는 입출력 예에 있는 연산 순서대로 하면 되는 거져..

 

전 열심히 후위 표기식을 만들고... 이걸로 계산해줬습니다...

코드는 상당히 길지만 수월하게 풀었습니다... ^^;;



###  :heavy_division_sign: 후기

빨리 풀 수 있을까?? 풀 수 있을까???? 잘 모르겠습니다 아직 ㅜㅜ

화이팅!!!!