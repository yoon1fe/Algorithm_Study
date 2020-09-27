# [17144] 미세먼지 안녕! - C++

## 분류
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
#include <queue>
#include <fstream>

using namespace std;

struct vacuum {
	int row;
	int col;
	vector<pair<int, int>> cleanArea;
};

int N, M, T;
int board[51][51];
vector<vacuum> vacuums;

int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

int moveUpOrDown(int row, int col, int inc, vector<pair<int, int>>& cleanArea) {
	for (int r = row + inc; ; r+=inc) {
		if (r < 0 || r >= N)
			break;
		if (board[r][col] == -1)
			break;

		cleanArea.push_back({ r, col });
		row = r;
	}
	return row;
}

int moveLeftOrRight(int row, int col, int inc, vector<pair<int, int>>& cleanArea) {
	for (int c = col + inc; ; c += inc) {
		if (c < 0 || c >= M)
			break;
		cleanArea.push_back({ row, c });
		col = c;
	}
	return col;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	ifstream in("input.txt");

	in >> N >> M >> T;
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			in >> board[i][j];
			if (board[i][j] == -1) {
				vector<pair<int, int>> cleanArea;
				int row = i, col = j;
				if (cnt == 0) {
					col = moveLeftOrRight(row, col, 1, cleanArea);
					row = moveUpOrDown(row, col, -1, cleanArea);
					col = moveLeftOrRight(row, col, -1, cleanArea);
					moveUpOrDown(row, col, 1, cleanArea);
				}
				else {
					col = moveLeftOrRight(row, col, 1, cleanArea);
					row = moveUpOrDown(row, col, 1, cleanArea);
					col = moveLeftOrRight(row, col, -1, cleanArea);
					moveUpOrDown(row, col, -1, cleanArea);
				}
				vacuums.push_back({ i, j, cleanArea });
				cnt++;
			}
		}
	}

	int tmp[51][51];
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			tmp[i][j] = 0;


	for (int t = 0; t < T; t++) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] <= 0)
					continue;

				int addDust = board[r][c] / 5;
				
				if (addDust == 0)
					continue;

				int cnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = r + dx[dir];
					int ny = c + dy[dir];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;

					if (board[nx][ny] == -1)
						continue;

					tmp[nx][ny] += addDust;
					cnt++;
				}
				board[r][c] -= (addDust * cnt);
			}
		}

		for (int r = 0; r < N; r++)
			for (int c = 0; c < M; c++) {
				board[r][c] += tmp[r][c];
				tmp[r][c] = 0;
			}

		for (auto v : vacuums) {
			int save = 0;
			int move = 0;
			for (auto pos : v.cleanArea) {
				save = board[pos.first][pos.second];
				board[pos.first][pos.second] = move;
				move = save;
			}
		}
	}
	long long sum = 0;
	for(int i=0; i<N;i++)
		for (int j = 0; j < M; j++) {
			if (board[i][j] == -1)
				continue;
			sum += board[i][j];
		}

	cout << sum;
	return 0;
}
```

## 문제 풀이
NxM에 대해서 입력을 받으면서 미세먼지 청소기가 위치한 곳을 발견하면 해당 청소기에 의해 이동되는 구역을 구한다.
- 상단 청소기는 우, 상, 좌, 하로 탐색하여 바람이 부는 곳의 위치를 저장
- 하단 청소기는 우, 하, 좌, 상으로 탐색하여 바람이 부는 곳의 위치를 저장

입력을 다 받았다면 T만큼 미세먼지를 확장시키고, 청소기로 바람을 불어서 미세먼지들을 이동시키면 된다.

미세먼지를 확장시킬 때는 확장되는 미세먼지는 tmp에 저장했다가 모든 미세먼지가 확장이 되었을 때 tmp에 있는 값을 board에 더해준다.
- 확장할 때 다른 칸에 영향을 주지 않아야 하기에 확장되는 미세먼지의 양을 tmp에 저장했음.
- 영향을 주도록하면 값이 다르게 나옴.
- (r, c)에서 미세먼지가 확장된다면, (r,c)는 확장되었을 때의 식에 따라 board[r][c]에서 값이 변하지만
- (r, c)에서 확장되는 위치인 (r+1, c), (r, c+1), (r-1, c), (r, c-1)은 board[r][c]/5의 값을 더해준다.
- 그리고 확장이 끝나면 board에 tmp의 각 위치의 값을 더해주면 된다.

청소기에 의해 미세먼지 이동
- 입력 받을 때 저장한 각 청소기에 의해 이동하는 위치를 저장한 cleanArea를 사용한다.
- move에는 다음 칸으로 이동할 값이 들어간다.
- save에는 현재 위치의 값을 저장하고, move가 이전 위치의 값을 현재 위치에 넣었다면, move에 save를 넣어서 다음 칸으로 미세먼지가 이동하게 한다.

## 후기
전형적인 시뮬레이션 문제였다.

기차에서 실제 시험이다 생각하고 풀었는데 나름 괜찮았다.