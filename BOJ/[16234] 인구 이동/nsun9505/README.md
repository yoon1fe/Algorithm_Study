# [16234] 인구 이동 - C++

## 분류
> 시뮬레이션
>
> 완전탐색

## 코드
```c++
#include <iostream>
#include <vector>
#include <queue>
#pragma warning(disable:4996)

using namespace std;
int N, L, R;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int board[51][51];
bool isVisited[51][51];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie();
	freopen("input.txt", "rt", stdin);
	int ans = 0;
	
	cin >> N >> L >> R;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> board[i][j];
	bool flag = 0;
	do {
		flag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isVisited[i][j])
					continue;

				queue<pair<int, int>> Q;
				vector<pair<int, int>> countries;
				int sum = board[i][j];
				Q.push({ i, j });
				countries.push_back({ i, j });
				isVisited[i][j] = true;

				while (!Q.empty()) {
					pair<int, int> pos = Q.front();
					Q.pop();

					for (int dir = 0; dir < 4; dir++) {
						int nx = pos.first + dx[dir];
						int ny = pos.second + dy[dir];

						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue;

						int tmp = abs(board[pos.first][pos.second] - board[nx][ny]);
						if (isVisited[nx][ny] || tmp < L || tmp > R)
							continue;

						isVisited[nx][ny] = true;
						countries.push_back({ nx, ny });
						sum += board[nx][ny];
						Q.push({ nx, ny });
					}
				}

				if (countries.size() == 1)
					isVisited[i][j] = false;
				else {
					int avg = sum / countries.size();
					for (auto pos : countries)
						board[pos.first][pos.second] = avg;
					flag = true;
				}
			}
		}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isVisited[i][j] = false;
		if(flag)
			ans += 1;
	} while (flag);

	cout << ans;

	return 0;
}
```

## 문제 풀이
BFS를 사용해서 현재 위치의 상하좌우의 국가와의 차이가 L이상 R이하라면 Queue에 넣고 탐색한다.
- 현재 위치에 인접한 국가와 차이가 L이상 R이하라면 해당 인접 국가를 리스트에 넣는다.

BFS로 탐색한 뒤에 인구 이동을 한다.
- 해당 연합에 속하는 국가가 1개이면 인구 이동은 일어나지 않음.
- 해당 연합에 속하는 국가가 2개 이상이면 인구 이동이 일어난다.

인구 이동이 일어나는 경우
- countries안에 있는 국가의 수를 BFS로 탐색하며 연합을 이루는 국가들의 인구수를 더한 sum값을 나누어 인구이동 후의 인구 수가 된다.
- 인구 이동이 일어났으므로 flag에는 true로하여 답에 +1해준다.
- 또한, flag가 true라는 것은 인구 이동이 일어난 것이고, 인구 이동이 일어난 후에 각 국가에서 인접한 국가와의 인구 수 차이가 L이상 R이하가 될 수 있으므로 다시 한 번 BFS를 하기 위해 사용한다.
- 만약 flag가 0이라면 더 이상의 인구 이동이 없으므로 더 이상 BFS를 돌릴 필요가 없다.

## 후기
더 빨리 정확하게 푸는 연습!