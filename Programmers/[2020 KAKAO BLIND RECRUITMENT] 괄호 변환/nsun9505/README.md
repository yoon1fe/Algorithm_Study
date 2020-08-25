# [2020 KAKAO BLIND RECRUITMENT] 괄호 변환 - C++
## 분류
> 문자열 처리

## 코드
```c++
#include <iostream>
#include <string>
#include <vector>
#include <stack>
using namespace std;

string getBalanceStr(string str) {
	int cnt = 0;
	string balance = "";
	for (int i = 0; i < (int)str.size(); i++) {
		if (str[i] == '(') {
			cnt += 1;
			balance += "(";
		}
		else {
			cnt -= 1;
			balance += ")";
		}
		if (cnt == 0)
			return balance;
		
	}
	return str;
}

bool isCorrectStr(string str) {
	stack<char> st;
	for (int i = 0; i < (int)str.length(); i++) {
		if (str[i] == '(')
			st.push(str[i]);
		else {
			if (!st.empty())
				st.pop();
			else
				return false;
		}
	}

	if (!st.empty())
		return false;
	return true;
}

string solution(string p) {
	if (p == "")
		return "";
	string u = getBalanceStr(p);
	string v = p.substr(u.length());

	if (isCorrectStr(u))
		return u + solution(v);

	string tmp;
	tmp = "(" + solution(v) + ")";
	int len = u.length();
	for (int i = 1; i < len - 1; i++)
		tmp += u[i] == '(' ? ")" : "(";
	return tmp;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie();

	string input;
	cin >> input;

	cout << solution(input);
	return 0;
}
```

## 풀이 방법
문제에서 말하는대로 구현하면 된다.
1. u는 균형잡인 괄호 문자열, v는 나머지
   - u를 구하는 방법은 여는 괄호와 닫는 괄호에 따라서 더하기 빼기를 하면서 0값이 되면 해당 인덱스까지의 문자열을 리턴하면 된다.
   - v는 u이후의 문자열이므로 기존 p의 문자열에서 u의 길이 이후의 문자열을 저장한다.
1. 만약 u가 올바른 괄호 문자열이라면 v를 1번부터 다시 재귀로 구현한 후 u에 더해서 리턴하면 된다.
1. 만약 u가 올바른 괄호 문자열이 아니라면 문제에 나와있는 그대로 구현하면 된다. 

## 후기
- 시뮬레이션 문제 같다.