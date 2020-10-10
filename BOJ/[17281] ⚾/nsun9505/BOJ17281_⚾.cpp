#include <iostream>
#include <vector>
#include <algorithm>
#pragma warning(disable:4996)

using namespace std;
int N;
bool isUsed[10];
int order[10];
int innings[51][10];
int ans = -2147000000;

void solution(int cur) {
	if (cur > 9) {
		if (order[4] != 1)
			return;

		int hitter = 1;
		int score = 0;
		for (int i = 0; i < N; i++) {
			int outCnt = 0;
			int base[4] = { 0, 0, 0, 0 };
			while (outCnt < 3) {
				if (innings[i][order[hitter]] == 0)
					outCnt++;
				else {
					base[0] = 1;
					for (int pos = 3; pos >= 0; pos--) {
						if (base[pos] == 0)
							continue;

						if (pos + innings[i][order[hitter]] >= 4) {
							base[pos] = 0;
							score++;
						}
						else {
							base[pos + innings[i][order[hitter]]] = 1;
							base[pos] = 0;
						}
					}
				}
			
				hitter += 1;
				if (hitter >= 10)
					hitter = 1;
			}
		}

		if (ans < score)
			ans = score;
		return;
	}

	for (int i = 1; i <= 9; i++) {
		if (!isUsed[i]) {
			isUsed[i] = true;
			order[cur] = i;
			solution(cur + 1);
			isUsed[i] = false;
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	freopen("input.txt", "rt", stdin);

	cin >> N;
	for (int i = 0; i < N; i++)
		for (int j = 1; j < 10; j++)
			cin >> innings[i][j];

	solution(1);
	cout << ans;

	return 0;
}