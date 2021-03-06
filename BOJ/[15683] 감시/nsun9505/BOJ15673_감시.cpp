#include <iostream>
#include <vector>
#include <map>
#pragma warning(disable:4996)

using namespace std;

int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
map<int, vector<int>> cctvDir;
vector<vector<int>> office;
int N, M;
int ans = 2147000000;

vector<int> cctv1() {
	vector<int> dir;
	dir.push_back(0);
	return dir;
}

vector<int> cctv2() {
	vector<int> dir = { 3, 1 };
	return dir;
}

vector<int> cctv3() {
	vector<int> dir = { 0, 1 };
	return dir;
}

vector<int> cctv4() {
	vector<int> dir = { 3, 0, 1 };
	return dir;
}

vector<int> cctv5() {
	vector<int> dir = { 0, 1, 2, 3 };
	return dir;
}

vector<int> rotate(vector<int> dir) {
	for (int i = 0; i < dir.size(); i++)
		dir[i] = (dir[i] + 1) % 4;
	return dir;
}

void initDirection(vector<pair<int, int>> cctvs) {
	for (auto cctvPos : cctvs) {
		int cctv = office[cctvPos.first][cctvPos.second];
		if (cctv == 1 && cctvDir.count(cctv) == 0)
			cctvDir[cctv] = cctv1();
		else if (cctv == 2 && cctvDir.count(cctv) == 0)
			cctvDir[cctv] = cctv2();
		else if (cctv == 3 && cctvDir.count(cctv) == 0)
			cctvDir[cctv] = cctv3();
		else if (cctv == 4 && cctvDir.count(cctv) == 0)
			cctvDir[cctv] = cctv4();
		else if (cctv == 5 && cctvDir.count(cctv) == 0)
			cctvDir[cctv] = cctv5();
	}
}

void solve(vector<vector<int>> curOffice, vector<pair<int, int>> cctvs, int idx) {
	if (idx == cctvs.size()) {
		int cnt = 0;
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < M; j++)
				if (curOffice[i][j] == 0)
					cnt++;
		
		if (cnt < ans)
			ans = cnt;

		return;
	}

	pair<int, int> curPos = cctvs[idx];
	int cctvNum = office[curPos.first][curPos.second];
	vector<int> curDir = cctvDir[cctvNum];
	pair<int, int> bakPos = curPos;

	for (int i = 0; i < 4; i++) {
		vector<vector<int>> tmpOffice(curOffice);
		for (auto dir : curDir) {
			while (true) {
				int nx = curPos.first + dx[dir];
				int ny = curPos.second + dy[dir];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					break;

				if (tmpOffice[nx][ny] == 6)
					break;

				if (tmpOffice[nx][ny] == 0)
					tmpOffice[nx][ny] = -1;
				curPos.first = nx;
				curPos.second = ny;
			}
			curPos = bakPos;
		}
		curDir = rotate(curDir);
		solve(tmpOffice, cctvs, idx + 1);
	}
}

int main(void) {
	freopen("input.txt", "rt", stdin);
	ios_base::sync_with_stdio(0);
	vector<pair<int, int>> cctvs;
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		vector<int> tmp;
		int val;
		for (int j = 0; j < M; j++) {
			cin >> val;
			if (val >= 1 && val <= 5)
				cctvs.push_back({ i, j });
			tmp.push_back(val);
		}
		office.push_back(tmp);
	}
	initDirection(cctvs);
	solve(office, cctvs, 0);

	cout << ans;

	return 0;
}