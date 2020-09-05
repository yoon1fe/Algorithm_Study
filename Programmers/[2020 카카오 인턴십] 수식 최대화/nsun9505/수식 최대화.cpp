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