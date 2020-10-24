# [16637] 괄호 추가하기 - C++

## 분류
> 완전탐색

## 코드
```c++
#include <iostream>
#include <vector>
#include <string>
#include <stack>

using namespace std;
int ans = -2147000000;

int calculator(int a, int b, char op) {
	switch (op) {
	case '+':
		return a + b;
	case '-':
		return a - b;
	case '*':
		return a * b;
	}
}

string expString;
vector<int> numbers;
void makeGwalho(int idx, vector<pair<int, int>> gwalhoPair) {
	if (idx >= expString.length()) {
		string result = expString;
		int add = 0;
		for (int i = 0; i < gwalhoPair.size(); i++, add += 2) {
			int startIdx = gwalhoPair[i].first + add;
			int endIdx = gwalhoPair[i].second + add;
			result = result.substr(0, startIdx) + "(" + result.substr(startIdx, endIdx - startIdx + 1) + ")" + result.substr(endIdx + 1);
		}
		vector<int> exp;
		for (int i = 0; i < result.length();) {
			if (result[i] == '(') {
				exp.push_back(calculator(result[i + 1] - 48, result[i + 3] - 48, result[i + 2]));
				i += 5;
			}
			else {
				if (result[i] >= '0' && result[i] <= '9')
					exp.push_back(result[i] - 48);
				else
					exp.push_back(result[i]);
				i++;
			}
		}
		int ret = exp[0];
		for (int i = 1; i < exp.size(); i+=2)
			ret = calculator(ret, exp[i + 1], exp[i]);

		if (ans < ret)
			ans = ret;

		return;
	}

	if (idx + 2 < expString.length()) {
		gwalhoPair.push_back({ idx, idx + 2 });
		makeGwalho(idx + 4, gwalhoPair);
		gwalhoPair.pop_back();
	}
	makeGwalho(idx + 2, gwalhoPair);
}

int main(void) {
	int N;
	cin >> N;
	cin >> expString;
	vector<pair<int, int>> gwalhoPair;

	if (N == 1)
		cout << expString;
	else if (N == 3)
		cout << calculator(expString[0] - 48, expString[2] - 48, expString[1]);
	else {
		makeGwalho(0, gwalhoPair);
		cout << ans;
	}
	return 0;
}
```

## 문제 풀이
DFS를 사용해서 문제를 해결

괄호가 중복되지 않기에 괄호 쌍을 추가했다면 다음에 추가할 수 있는 위치(다다음 피연자)로 인덱스를 변경한다.
- 괄호를 추가하지 않은 경우는 다음 피연산자의 위치를 가리키면 된다.
- 그러므로 현재 피연산자를 가리키는 인덱스를 idx라고 할 때, idx+2가 아직 인풋 스트링의 길이보다 작다면 괄호를 만들 수 있으므로 괄호를 추가

괄호를 만들기 위해 괄호를 추가할 수 있는 위치의 인덱스 쌍을 생성한다.
- pair로 들어가게 되는데, first는 ( 가 뒤에 오고, second는 )의 앞에 오는 위치를 말한다.
- 예를 들어서 3+8+2라고 할 때, 3은 0번 인덱스, 8은 2번 인덱스이고, 괄호를 생성할 수 있으므로, 괄호를 만들 수 있다는 표시로 0, 2를 pair로 해서 vector에 넣는다.

괄호가 0개 이상 만들어진 경우(즉, 입력 문자열을 모두 본 경우)
1. 먼저 여는 괄호와 닫는 괄호가 생길 위치가 저장된 vector를 사용해서 괄호를 실제로 입력 문자열 복사본에 추가한다.
   - 괄호를 추가하면 경우 전체 문자열의 길이가 2증가하기에 기존의 괄호를 생성하겠다고 표시했던 인덱스에 +2를 하여 위치를 제대로 찾아가도록 한다.
   - 처음에는 0으로 되어 있기에 문제가 되지 않는다.

1. 괄호가 생성된 문자열에서 간단하게 괄호가 씌어져 있는 곳은 계산해서 vector에 넣는다.
   - 괄호가 씌어져 있지 않은 곳은 그냥 vector에 넣는다.

1. 괄호가 씌어진 곳들이 계산되었으므로 왼쪽부터 시작해서 연산자에 따라 계산하면 끝!

## 후기
후아후아!