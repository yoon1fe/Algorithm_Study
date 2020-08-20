#include <iostream>
#include <algorithm>

using namespace std;

int N;
int input[11];
int op[4] = { 0, };
int maxval = -1000000001, minval = 1000000001;

void dfs(int s, int result) {
	int t = 0;

	if (s == N - 1) {
		maxval = max(maxval, result);
		minval = min(minval, result);
		return;
	}
	
	for (int i = 0; i < 4; i++) {
		if (op[i] == 0)
			continue;
		switch (i) {
		case 0:	t = result + input[s + 1]; break;
		case 1:	t = result - input[s + 1]; break;
		case 2:	t = result * input[s + 1]; break;
		case 3:	t = result / input[s + 1]; break;
		}
		
		op[i]--;
		dfs(s + 1, t);
		op[i]++;
	}
}

int main() {
	cin >> N;

	for (int i = 0; i < N; i++) cin >> input[i];
	for (int i = 0; i < 4; i++) cin >> op[i];

	dfs(0, input[0]);

	cout << maxval << endl << minval;

	return 0;
}