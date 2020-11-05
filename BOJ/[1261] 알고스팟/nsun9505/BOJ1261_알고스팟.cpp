#include <iostream>
#include <queue>
#define INF 2147000000;

using namespace std;

struct Element {
	int row;
	int col;
	int d;
};

struct comp {
	bool operator()(Element s, Element t) {
		return s.d < t.d;
	}
};

int N, M;
int graph[101][101];
int dist[101][101];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
bool isVisited[101][101];

bool isOutOfIndex(int row, int col) {
	if (row < 1 || col < 1 || row > N || col > M)
		return true;
	return false;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	
	cin >> M >> N;
	char input;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++) {
			cin >> input;
			graph[i][j] = (input == '1') ? 1 : 0;
			dist[i][j] = INF;
		}

	dist[1][1] = 0;
	priority_queue<pair<int, pair<int, int>>> pq;
	pq.push({ 0,{1,1} });

	while (!pq.empty()) {
		pair<int, pair<int, int>> cur = pq.top();
		int curRow = cur.second.first;
		int curCol = cur.second.second;
		int distance = -cur.first;
		pq.pop();

		if (isVisited[curRow][curCol])
			continue;
		isVisited[curRow][curCol] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nx = curRow + dx[dir];
			int ny = curCol + dy[dir];

			if (isOutOfIndex(nx, ny))
				continue;

			int nextDistance = distance + graph[nx][ny];

			if (nextDistance < dist[nx][ny]) {
				dist[nx][ny] = nextDistance;
				pq.push({ -dist[nx][ny], {nx, ny}});
			}
		}
	}

	cout << dist[N][M] << '\n';
	return 0;
}