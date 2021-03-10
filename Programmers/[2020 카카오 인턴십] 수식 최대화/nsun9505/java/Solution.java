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