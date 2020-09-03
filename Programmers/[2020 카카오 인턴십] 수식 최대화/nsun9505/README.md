# [2020 카카오 인턴십] 수식 최대화 - C++

## 분류
> 완전탐색

## 코드
```c++
#include <algorithm>
#include <map>
#include <string>
#include <vector>

using namespace std;

long long solution(string expression) {
    long long answer = 0;
    map<char, int> expMap;
    vector<long long> numbers;
    vector<char> operators;
    string tmp = "";
    for (int i = 0; i < expression.length(); i++) {
        if (expression[i] == '*' || expression[i] == '+' || expression[i] == '-') {
            expMap[expression[i]] = 1;
            numbers.push_back(stoll(tmp) + 0L);
            tmp = "";
            operators.push_back(expression[i]);
        }
        else {
            tmp += expression[i];
        }
    }
    numbers.push_back(stoll(tmp) + 0L);

    vector<char> expList;
    for (auto exp : expMap)
        expList.push_back(exp.first);

    long long ret = 0;
    do {
        vector<long long> tmpNum = numbers;
        vector<char> tmpOp = operators;
        for (int op = 0; op < expList.size(); op++) {
            for (int i = 0; i < tmpOp.size(); i++) {
                if (expList[op] != tmpOp[i])
                    continue;

                if (expList[op] == '*')
                    tmpNum[i] = tmpNum[i] * tmpNum[i + 1];
                else if (expList[op] == '+')
                    tmpNum[i] = tmpNum[i] + tmpNum[i + 1];
                else
                    tmpNum[i] = tmpNum[i] - tmpNum[i + 1];
                tmpNum.erase(tmpNum.begin() + i + 1);
                tmpOp.erase(tmpOp.begin() + i);
                i -= 1;
            }
       }
       if(answer < abs(tmpNum[0]))
           answer = abs(tmpNum[0]);
    } while (next_permutation(expList.begin(), expList.end()));

    return answer;
}

int main(void) {
    cout << solution("50*6-3*2");
	return 0;
}
```

## 문제 풀이
입력 받은 수식을 숫자와 연산자로 분리한다.
- 예를 들어 1+2+3+4+5라면, 
- numbers라는 벡터에는 1,2,3,4,5가 들어가고, operators에는 +,+,+,+가 들어간다.
- 계산하는 것은 operators에서 첫 번째 +의 인덱스를 i라고 하면 numbers[i] + numbers[i+1]을 하면 된다.
- 그리고 결과를 numbers[i]에 저장하고, i+1 인덱스를 numbers에서 삭제하면 된다.

문제를 풀기 위해서 모든 연산자의 우선순위를 고려해야 한다.
- 그래서 map을 사용해서 수식에서 나타나는 연산자를 map에 저장하고
- map을 vector로 저장한 뒤에 next_permutaion으로 순열을 생성해서 
- 해당 순열로 수식을 풀었을 때 절댓값이 가장 크다면 답으로 채택한다.

## 후기
간단하게 생각하면 간단하게 풀리고

어렵게 생각하면 어렵게 풀리는 문제같다.

초반 문제는 간단하게 생각을 해봐야겠다.