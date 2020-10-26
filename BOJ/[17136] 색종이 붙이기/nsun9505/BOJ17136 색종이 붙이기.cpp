#include <iostream>
#include <vector>
#pragma warning(disable:4996)
#define MAX 10

using namespace std;

struct ColorPaper {
	int size;
	int cnt;
};

vector<ColorPaper> papers = { {1, 5}, {2, 5}, {3, 5}, {4, 5}, {5, 5} };
vector<pair<int, int>> ones;
int board[MAX][MAX];
int ans = 2147000000;

bool isOutOfIndex(int row, int col) {
	if (row < 0 || col < 0 || row >= MAX || col >= MAX)
		return true;
	return false;
}

bool check(int row, int col, int size) {
	if (row + size > MAX || col + size > MAX)
		return false;

	if (board[row][col] == -1)
		return false;

	for (int i = row; i < row + size; i++)
		for (int j = col; j < col + size; j++)
			if (board[i][j] == 0 || board[i][j] == -1)
				return false;
	return true;
}

void putPaper(int row, int col, int size) {
	for (int i = row; i < row + size; i++)
		for (int j = col; j < col + size; j++)
			board[i][j] = -1;
}

vector<vector<int>> getBackupArea(int row, int col, int size) {
	vector<vector<int>> backupArea;
	for (int i = row; i < row + size; i++) {
		vector<int> tmp;
		for (int j = col; j < col + size; j++)
			tmp.push_back(board[i][j]);
		backupArea.push_back(tmp);
	}
	return backupArea;
}

void recoveryArea(int row, int col, int size, vector<vector<int>> bakArea) {
	int s = 0;
	for (int i = row; i < row + size; i++, s++) {
		int t = 0;
		for (int j = col; j < col + size; j++, t++)
			board[i][j] = bakArea[s][t];
	}
}

void DFS(int idx) {
	if (idx >= ones.size()) {
		int cnt = 0;
		for (int i = 0; i < papers.size(); i++)
			cnt += (5 - papers[i].cnt);
		if (cnt < ans)
			ans = cnt;
		return;
	}
	int row = ones[idx].first;
	int col = ones[idx].second;
	if (board[row][col] == -1) {
		DFS(idx + 1);
	}
	else {
		for (int i = 0; i < 5; i++) {
			if (papers[i].cnt > 0 && check(row, col, papers[i].size)) {
				vector<vector<int>> bakArea = getBackupArea(row, col, papers[i].size);
				putPaper(row, col, papers[i].size);
				papers[i].cnt -= 1;
				DFS(idx + 1);
				papers[i].cnt += 1;
				recoveryArea(row, col, papers[i].size, bakArea);
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(0);
	freopen("input.txt", "rt", stdin);

	for (int i = 0; i < MAX; i++)
		for (int j = 0; j < MAX; j++) {
			cin >> board[i][j];
			if (board[i][j] == 1)
				ones.push_back({ i, j });
		}

	if (ones.size() == 0)
		cout << 0;
	else {
		DFS(0);
		if (ans == 2147000000)
			cout << -1;
		else
			cout << ans;
	}

	return 0;
}