# [2020 카카오 인턴십] 수식 최대화 - JAVA

## 분류
> 스택
>
> 순열

## 코드
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    static HashMap<String, Integer> opMap = new HashMap<>();
    static Stack<String> stack = new Stack<>();
    static ArrayList<String> list = new ArrayList<>();
    static ArrayList<String> exp = new ArrayList<>();
    static String[] operators;
    static long ans = 0;
    public long solution(String expression) {
        Pattern pattern = Pattern.compile("\\d{1,3}");
        Matcher matcher = pattern.matcher(expression);

        while(matcher.find())
            list.add(matcher.group());

        pattern = Pattern.compile("\\D");
        matcher = pattern.matcher(expression);
        HashSet<String> opSet = new HashSet<>();
        int idx = 0;
        while(matcher.find()) {
            String op = matcher.group();
            opSet.add(op);
            exp.add(list.get(idx++));
            exp.add(op);
        }
        exp.add(list.get(idx));

        operators = new String[opSet.size()];
        idx = 0;
        for(String op : opSet)
            operators[idx++] = op;

        perm(0);

        return ans;
    }

    public void perm(int depth){
        if(depth == operators.length){
            for(int i=0; i<operators.length; i++)
                opMap.put(operators[i], i);

            list.clear();
            stack.clear();
            for(int i=0; i<exp.size(); i++){
                String str = exp.get(i);
                if(str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*")){
                    while(!stack.isEmpty()){
                        if(opMap.get(stack.peek()) < opMap.get(str))
                            break;
                        list.add(stack.pop());
                    }
                    stack.push(str);
                } else {
                    list.add(str);
                }
            }

            while(!stack.isEmpty())
                list.add(stack.pop());

            for(String str : list){
                if(str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*")){
                    long op2 = Long.parseLong(stack.pop());
                    long op1 = Long.parseLong(stack.pop());

                    if(str.equals("+"))
                        stack.push(String.valueOf(op1 + op2));
                    else if(str.equals("-"))
                        stack.push(String.valueOf(op1 - op2));
                    else if(str.equals("*"))
                        stack.push(String.valueOf(op1 * op2));
                    else
                        stack.push(String.valueOf(op1 / op2));
                } else {
                    stack.push(str);
                }
            }

            long sum = Math.abs(Long.parseLong(stack.pop()));
            ans = Math.max(ans, sum);

            return;
        }

        for(int i=depth; i<operators.length; i++){
            swap(depth, i);
            perm(depth+1);
            swap(depth, i);
        }
    }

    public void swap(int x, int y){
        String tmp = operators[x];
        operators[x] = operators[y];
        operators[y] = tmp;
    }
}
```

## 문제 풀이
먼저 연산자와 피연산자를 알아오기 위해서 정규 표현식을 사용해서 파싱했습니다.
   - "\\d" : 숫자만 가져오기
   - "\\D" : 숫자가 아닌 애들 가져오기

순열을 사용해서 연산자의 순서를 정하고 정해진 순서에 따라 Map에 우선순위를 저장하였습니다.

계산하는 방법은 중위 표현식을 후위표현식으로 변경하여 계산하였습니다.

## 후기
후위 표현식과 순열을 사용하면 쉽게 풀 수 있는 문제였습니다!

후위 표현식은 코드 안 보고 짜봤습니다!

싸피가 도움이 되긴 하네요..ㅎ