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