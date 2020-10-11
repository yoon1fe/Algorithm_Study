# [11559] Puyo Puyo - C++

## 분류
> BFS

## 코드
```c++
#include <iostream>
#include <vector>
#include <queue>
#pragma warning(disable:4996)
#define ROW 12
#define COL 6

using namespace std;

char board[12][6];
bool isVisited[12][6];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

vector<pair<int, int>> BFS(int row, int col, char color) {
	queue<pair<int, int>> Q;
	Q.push({ row, col });
	isVisited[row][col] = true;

	vector<pair<int, int>> ret;
	ret.push_back(Q.front());

	while (!Q.empty()) {
		pair<int, int> pos = Q.front();
		Q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int nx = pos.first + dx[dir];
			int ny = pos.second + dy[dir];

			if (nx < 0 || ny < 0 || nx >= ROW || ny >= COL)
				continue;

			if (isVisited[nx][ny] || board[nx][ny] == '.' || board[nx][ny] != color)
				continue;

			Q.push({ nx, ny });
			ret.push_back({ nx, ny });
			isVisited[nx][ny] = true;
		}
	}

	return ret;
}

void movePuyo() {
	for (int i = 10; i >= 0; i--) {
		for (int j = 0; j < COL; j++) {
			if (board[i][j] == '.')
				continue;

			int nextRow = i;
			bool flag = false;
			for (int mul = 1; ; mul++) {
				int nRow = i + (dx[2] * mul);

				if (nRow > ROW || board[nRow][j] != '.')
					break;

				nextRow = nRow;
				flag = true;
			}

			if (flag) {
				board[nextRow][j] = board[i][j];
				board[i][j] = '.';
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	freopen("input.txt", "rt", stdin);

	for (int i = 0; i < 12; i++)
		for (int j = 0; j < 6; j++) 
			cin >> board[i][j];

	int ans = 0;
	int cnt = 0;
	do {
		cnt = 0;
		vector<vector<pair<int, int>>> list;

		for (int i = 0; i < ROW; i++)
			for (int j = 0; j < COL; j++)
				isVisited[i][j] = false;

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (board[i][j] == '.' || isVisited[i][j])
					continue;

				vector<pair<int, int>> tmp = BFS(i, j, board[i][j]);
				if (tmp.size() >= 4)
					list.push_back(tmp);
			}
		}

		if (list.size() > 0)
			cnt++;

		for (int i = 0; i < list.size(); i++) {
			vector<pair<int, int>> cur = list[i];

			for (int idx = 0; idx < cur.size(); idx++)
				board[cur[idx].first][cur[idx].second] = '.';
		}

		movePuyo();

		ans += cnt;
	} while (cnt);

	cout << ans;
	return 0;
}
```

## 문제 풀이
뿌요들이 쌓여 있는 보드를 입력받는다.

전체탐색을 한다.
- 만약 현재 칸이 '.'이거나 이미 BFS를 돌린 영역에 포함된 영역이라면 현재 칸에서 BFS로 탐색을 하지 않는다.
- 그렇지 않으면 현재 칸의 색과 상하좌우에 연결된 뿌요들 중에 같은 색이고 이 뿌요들이 4개 이상인지를 알기 위해 BFS를 돌린다.
- BFS를 돌리고 나서 연결된 뿌요들이 담긴 벡터를 리턴받는다.
- 만약 벡터의 크기가 4이상이라면 해당 벡터를 터뜨릴 리스트에 넣는다.

4개 이상의 같은 색 쀼요가 있는 리스트가 1개 이상이라면 모두 터뜨린다.
- 그리고 밑으로 이동할 수 있는 뿌요들을 이동시킨다.

다시 같은 색이고 연결된 4개 이상의 뿌요가 있는지 찾아보고 있다면 위의 과정을 다시 하면 되고, 없다면 프로그램을 종료한다.

## 후기
재미있는 BFS 문제!