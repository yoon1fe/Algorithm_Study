# [14888] 연산자 끼워넣기 - C++

## 분류
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>

using namespace std;

vector<int> list;
vector<pair<char, int>> op;
vector<char> useOperator;
int maxRet = -214700000;
int minRet = 214700000;
int N;

int eval() {
	int sum = list[0];
	int idx = 1;
	for (int i = 0; i < useOperator.size(); i++, idx++) {
		switch (useOperator[i]) {
		case '+':
			sum += list[idx]; break;
		case '-':
			sum -= list[idx]; break;
		case '*':
			sum *= list[idx]; break;
		case '/':
			sum /= list[idx]; break;
		}
	}
	return sum;
}

void DFS(int depth) {
	if (depth == N-1) {
		int ret = eval();
		if (maxRet < ret)
			maxRet = ret;
		if (ret < minRet)
			minRet = ret;
		return;
	}

	for (int i = 0; i < 4; i++) {
		if (0 < op[i].second) {
			op[i].second -= 1;
			useOperator.push_back(op[i].first);
			DFS(depth + 1);
			useOperator.pop_back();
			op[i].second += 1;
		}
	}
}

int main(void) {
	cin >> N;
	int tmp;
	for (int i = 0; i < N; i++) {
		cin >> tmp;
		list.push_back(tmp);
	}
	op.push_back({ '+', 0 });
	op.push_back({ '-', 0 });
	op.push_back({ '*', 0 });
	op.push_back({ '/', 0 });

	for (int i = 0; i < 4; i++) {
		cin >> tmp;
		op[i].second = tmp;
	}
	DFS(0);
	cout << maxRet << '\n' << minRet;

	return 0;
}
```

## 풀이 방법
문제에서 원하는 대로 시뮬레이션을 돌리면 된다.
- DFS로 탐색을 하면서 사용할 수 있는 연산자의 수가 0보다 크다면 해당 연산자를 사용한다.
  - depth가 N-1이 되면 사용한 연산자를 이용해서 입력 받은 수열에 적용한다.
  - 가장 큰 값과 작은 값은 도출한다.

## 후기
- 지금 듣고 있는 강의에서 방금 풀어봄.
- 우선순위가 없는 것이 난이도를 매우 낮추었다고 생각함.