# [2020 KAKAO BLIND RECRUITMENT] 괄호 변환

## 분류
> 문자열
>
> 스택

## 코드
```java
import java.util.Stack;

public class Solution {
    Stack<Character> stack = new Stack<>();

    public String solution(String p){
        if(isCorrect(p))
            return p;
        return recursive(p);
    }
    public String recursive(String str){
        if(str.length() == 0)
            return "";

        String u = getBalance(str);
        String v = str.substring(u.length());
        v = recursive(v);

        String ret = "";
        if(isCorrect(u))
            ret = u + v;
        else{
            ret = "(" + v + ")";
            for(int i=1; i<u.length()-1; i++)
                ret += u.charAt(i) == '(' ? ")" : "(";
        }
        return ret;
    }

    public String getBalance(String str){
        int open = 0;
        int close = 0;
        int idx = 0;

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(') open++;
            else close++;

            if(open == close){
                idx = i;
                break;
            }
        }

        return str.substring(0, idx+1);
    }

    public boolean isCorrect(String str){
        stack.clear();
        String ret = "";
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '(')
                stack.push(ch);
            else{
                if(stack.isEmpty())
                    return false;
                stack.pop();
            }
        }
        if(stack.isEmpty())
            return true;
        return false;
    }
}
```

## 문제 풀이
문제에서 말하는 그대로 구현하면 되는 문제입니다.

isCorrect로 올바른 문자열인지 확인하였습니다.
   - stack 사용

getBalance()는 더 이상 분해되지 않는 균형잡힌 문자열을 리턴합니다.
   - 즉, 여는 괄호의 개수와 닫는 괄호의 개수가 똑같아지는 그 순간 바로 리턴하면 됩니다.

recursive(String str)
   - str가 ""이면 ""을 리턴합니다.
   - str이 ""가 아니라면 u와 v로 나누어서 문제를 해결합니다.
   - 이 또한, 문제에서 말하는 그대로 구현하였습니다.
   
## 후기
오늘도 화이팅!