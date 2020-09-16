#include <iostream>
#include <vector>

using namespace std;
int N, L;
int map[101][101];
bool isPut[101][101];

void rotate() {
	int tmpMap[101][101];
	for (int i = 0; i < N; i++) {
		int k = 0;
		for (int j = N - 1; j >= 0; j--)
			tmpMap[i][k++] = map[j][i];
	}

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			map[i][j] = tmpMap[i][j];
}

int main(void) {
	ios_base::sync_with_stdio(false);
	freopen("input.txt", "rt", stdin);
	cin >> N >> L;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> map[i][j];

	int ret = 0;
	for (int loop = 0; loop < 2; loop++) {
		for (int i = 0; i < N; i++) {
			bool flag = true;
			for (int cur = 0; cur < N; cur++) {
				if (cur + 1 < N) {
					if (map[i][cur] == map[i][cur + 1])
						continue;
					else if (map[i][cur] - map[i][cur + 1] == 1) {
						if (cur + L < N) {
							int cnt = 0;
							for (int k = cur + 1; k <= cur + L; k++)
								if (map[i][cur] - map[i][k] == 1 && !isPut[i][k])
									cnt++;
							if (cnt != L) {
								flag = false;
								break;
							}
							for (int k = cur + 1; k <= cur + L; k++)
								isPut[i][k] = true;
							cur += L-1;
						}
						else {
							flag = false;
							break;
						}
					}
					else if (map[i][cur] - map[i][cur + 1] == -1) {
						if (cur - L + 1 >= 0) {
							int cnt = 0;
							for (int k = cur - L + 1; k <= cur; k++)
								if (map[i][k] - map[i][cur+1] == -1 && !isPut[i][k])
									cnt++;
							if (cnt != L) {
								flag = false;
								break;
							}

							for (int k = cur - L + 1; k <= cur; k++)
								isPut[i][k] = true;
						}
						else {
							flag = false;
							break;
						}
					}
					else {
						flag = false;
						break;
					}
				}
				else
					break;
			}
			if (flag)
				ret += 1;
		}
		rotate();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isPut[i][j] = false;
	}
	cout << ret;

	return 0;
}