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