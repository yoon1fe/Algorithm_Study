#include <iostream>
#include <vector>
#pragma warning(disable:4996)

using namespace std;
int N, M;
int map[51][51];
int ans = 2147000000;
vector<pair<int, int>> houses;
vector<pair<int, int>> chickens;

void solution(int cur, int cnt, vector<pair<int, int>> list) {
	if (cnt >= M) {
		if (cnt == M) {
			int sum = 0;
			for (auto house : houses) {
				int dist = 2147000000;
				for (auto chicken : list) {
					int tmp = abs(chicken.first - house.first) + abs(chicken.second - house.second);
					if (tmp < dist)
						dist = tmp;
				}
				sum += dist;
			}

			if (sum < ans)
				ans = sum;
		}
		return;
	}

	if (chickens.size() <= cur)
		return;

	list.push_back(chickens[cur]);
	solution(cur + 1, cnt + 1, list);
	list.pop_back();
	solution(cur + 1, cnt, list);
}

int main(void) {
	ios_base::sync_with_stdio(false);
	freopen("input.txt", "rt", stdin);
	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
			if (map[i][j] == 1)
				houses.push_back({ i, j });
			else if (map[i][j] == 2)
				chickens.push_back({ i,j });
		}
	}

	vector<pair<int, int>> list;
	solution(0, 0, list);

	cout << ans;

	return 0;
}