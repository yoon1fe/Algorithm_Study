# [17136] 색종이 붙이기 - C++

## 분류
> 완전탐색
>
> 백트래킹

## 코드
```c++
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
```

## 문제 풀이
DFS로 풀은 문제!

### 1. 1인 곳을 탐색한다.
만약 1인 곳이 이미 색종이가 붙여져 있다면 다음 색종이를 붙일 곳으로 이동

아직 색종이가 붙어있지 않다면 색종이를 1x1, 2x2, 3x3, 4x4, 5x5를 붙이고 다음에 1이 있는 인덱스로 이동한다.

NxN 색종이의 개수가 1개 이상이고, 색종이를 붙일 수 있는지 위해 검사(N = 1 ~ 5)
- NxN 색종이를 붙인다고 할 때, 격자를 벗어날 경우 붙이지 않는다.
- 또한, 색종이가 겹쳐진다면(board[i][j] == -1) NxN 색종이를 붙이지 않는다.
- 당연히 0인 곳을 붙일 수 없기에, NxN 안에 0이 들어온다면 색종이를 붙일 수 없다.

색종이를 붙일 수 있는 검사를 통과한 경우
1. NxN 색종이 크기에 맞게 백업한다.
1. 색종이 사용 개수를 하나 줄인다.
1. 색종이를 붙인다.(색종이를 붙였다는 의미로 board[i][j]에 -1을 대입)
1. 다음 1이 있는 인덱스로 이동
1. 다시 돌아왔다면, 색종이 사용 개수를 하나 증가 시키고, NxN 색종이를 붙였던 위치들을 원래대로 되돌린다.

색종이를 붙있을 수 있을만큼 붙인 경우
- 색종이가 얼마나 사용되었는지를 체크하여 현재의 값보다 작은지 체크하고 작다면 답으로 채택한다.

## 후기
음 단순히 구현문제 같았다. 그동안 삼성문제를 많이 풀어봐서 문제를 푸는데 도움이 많이 되었다.