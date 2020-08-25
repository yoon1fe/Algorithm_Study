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