#include <iostream>
#include <vector>
#include <string>
#include <stack>
using namespace std;

int solution(string s) {
	int ans = s.length();
	for (int cmpSize = 1; cmpSize < s.length(); cmpSize++) {
		stack<string> st;
		int idx = 0;
		while(true) {
			if (idx + cmpSize < s.length()) {
				st.push(s.substr(idx, cmpSize));
				idx += cmpSize;
			}
			else {
				st.push(s.substr(idx));
				break;
			}
		}
		
		stack<pair<string, int>> cur;
		cur.push({ st.top(), 0 });
		while(!st.empty()){
			if (st.top() == cur.top().first) {
				cur.top().second += 1;
				st.pop();
			}
			else {
				cur.push({ st.top(), 0 });
			}
		}

		int tmp = 0;
		while (!cur.empty()) {
			pair<string, int> p = cur.top();
			cur.pop();
			tmp += p.first.length();
			if (p.second != 1)
				tmp += (int)(to_string(p.second).length());
		}

		if (tmp < ans)
			ans = tmp;
	}

	return ans;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie();

	string input;
	cin >> input;

	cout << solution(input);

	return 0;
}