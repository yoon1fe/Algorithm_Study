#include <iostream>
#include <queue>
#include <vector>
#pragma warning(disable:4996)
using namespace std;

struct Virus {
	int row;
	int col;
	int t;
};

bool isVisited[51][51];
int board[51][51];
int N, M;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
vector<Virus> virusList;
int zeroCnt = 0;
int ans = 2147000000;

void DFS(int cur, int cnt, vector<Virus> list) {
	if (cnt == M) {
		queue<Virus> Q;
		for (int i = 0; i < M; i++) {
			Q.push(list[i]);
			isVisited[list[i].row][list[i].col] = true;
		}

		int ret = 0;
		int tmpCnt = zeroCnt;
		while (!Q.empty()) {
			Virus virus = Q.front();
			Q.pop();

			for (int dir = 0; dir < 4; dir++) {
				int nx = virus.row + dx[dir];
				int ny = virus.col + dy[dir];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (isVisited[nx][ny] || board[nx][ny] == 1)
					continue;

				Q.push({ nx, ny, virus.t + 1 });
				isVisited[nx][ny] = true;
				if (board[nx][ny] == 0 && ret < virus.t + 1)
					ret = virus.t + 1;
				if(board[nx][ny] == 0)
					tmpCnt--;
			}
		}
		if (tmpCnt == 0 && ret < ans)
			ans = ret;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isVisited[i][j] = false;
		return;
	}
	if (cur >= virusList.size())
		return;
	
	list.push_back(virusList[cur]);
	DFS(cur + 1, cnt + 1, list);
	list.pop_back();
	DFS(cur + 1, cnt, list);
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> board[i][j];
			if (board[i][j] == 2)
				virusList.push_back({ i, j, 0 });
			if (board[i][j] == 0)
				zeroCnt++;
		}
	}
	vector<Virus> list;
	DFS(0, 0, list);

	if (ans == 2147000000)
		cout << -1;
	else
		cout << ans;
	return 0;
}