#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>
#pragma warning(disable:4996)

using namespace std;

struct Element {
	int row;
	int col;
	int dist;
};

int N, M;
int tRow, tCol, fuel;
int board[21][21];
bool isVisited[21][21];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
map<pair<int, int>, pair<int, int>> srcToDest;

bool cmp(Element a, Element b) {
	if (a.dist < b.dist)
		return true;
	else if (a.dist == b.dist) {
		if (a.row < b.row)
			return true;
		else if (a.row == b.row)
			return a.col < b.col;
		else
			return false;
	}
	else
		return false;
}

int getSrcToDestDist(int sRow, int sCol, int dRow, int dCol, int curFuel) {
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			isVisited[i][j] = false;

	queue<Element> Q;
	Q.push({ sRow, sCol, 0 });
	isVisited[sRow][sCol] = true;

	while (!Q.empty()) {
		Element pos = Q.front();
		Q.pop();

		if (pos.dist + 1 > curFuel)
			continue;

		for (int dir = 0; dir < 4; dir++) {
			int nx = pos.row + dx[dir];
			int ny = pos.col + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			if (isVisited[nx][ny] || board[nx][ny] == 1)
				continue;

			if (nx == dRow && ny == dCol)
				return pos.dist + 1;

			Q.push({ nx, ny, pos.dist + 1 });
			isVisited[nx][ny] = true;
		}
	}
	return -1;
}

Element getPassenger() {
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			isVisited[i][j] = false;

	queue<Element> Q;
	Q.push({ tRow, tCol, 0 });
	isVisited[tRow][tCol] = true;

	vector<Element> pList;
	if (board[tRow][tCol] != 0)
		return { tRow, tCol, 0 };

	while (!Q.empty()) {
		Element pos = Q.front();
		Q.pop();

		if (pos.dist + 1 > fuel)
			continue;
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = pos.row + dx[dir];
			int ny = pos.col + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			if (isVisited[nx][ny] || board[nx][ny] == 1)
				continue;

			Q.push({ nx, ny, pos.dist + 1 });
			isVisited[nx][ny] = true;
			if (board[nx][ny] != 0)
				pList.push_back({ nx, ny, pos.dist + 1 });
		}
	}

	if (pList.size() == 0)
		return { -1, -1, -1 };

	sort(pList.begin(), pList.end(), cmp);

	return pList[0];
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	freopen("input.txt", "rt", stdin);

	cin >> N >> M >> fuel;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> board[i][j];

	cin >> tRow >> tCol;
	tRow -= 1;
	tCol -= 1;
	int sRow, sCol, dRow, dCol;
	for (int i = 0; i < M; i++) {
		cin >> sRow >> sCol >> dRow >> dCol;
		srcToDest[{sRow-1, sCol-1}] = { dRow-1, dCol-1 };
		board[sRow-1][sCol-1] = 2;
	}
	
	bool flag = false;
	while (!srcToDest.empty()) {
		Element src = getPassenger();
		if (src.row == -1 && src.col == -1) {
			flag = false;
			break;
		}

		fuel -= src.dist;
		pair<int, int> dest = srcToDest[{src.row, src.col}];
		int dist = getSrcToDestDist(src.row, src.col, dest.first, dest.second, fuel);
		if (dist == -1) {
			flag = false;
			break;
		}

		fuel += dist;
		srcToDest.erase({ src.row, src.col });
		tRow = dest.first;
		tCol = dest.second;
		board[src.row][src.col] = 0;
		flag = true;
	}

	if (flag)
		cout << fuel;
	else
		cout << -1;

	return 0;
}