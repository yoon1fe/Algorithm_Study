#include <iostream>
#include <vector>
#include <map>
#pragma warning(disable:4996)
#define MAX 4
#define SHARK -1
#define EMPTY 0
using namespace std;

struct Fish {
	int num;
	int row;
	int col;
	int dir;
};

int dx[8] = { -1, -1, 0, 1, 1, 1, 0, -1 };
int dy[8] = { 0, -1, -1, -1, 0, 1, 1, 1 };
Fish shark;
int ans;

void solution(int sum, Fish board[4][4], map<int, Fish> fishMap) {
	Fish tmpBoard[4][4];
	for (int i = 0; i < MAX; i++)
		for (int j = 0; j < MAX; j++)
			tmpBoard[i][j] = board[i][j];

	for (auto cur : fishMap) {
		Fish fish = cur.second;
		while (true) {
			int nx = fish.row + dx[fish.dir];
			int ny = fish.col + dy[fish.dir];

			if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) {
				fish.dir = (fish.dir + 1) % 8;
				continue;
			}

			if (tmpBoard[nx][ny].num == SHARK) {
				fish.dir = (fish.dir + 1) % 8;
				continue;
			}

			if (tmpBoard[nx][ny].num >= 0) {
				Fish tmp = tmpBoard[nx][ny];

				tmp.row = fish.row; tmp.col = fish.col;
				fish.row = nx; fish.col = ny;

				tmpBoard[nx][ny] = fish;
				tmpBoard[tmp.row][tmp.col] = tmp;

				if (tmp.num != 0)
					fishMap[tmp.num] = tmp;
				fishMap[fish.num] = fish;
				break;
			}
		}
	}


	for (int i = 1; i < MAX; i++) {
		int nx = shark.row + (dx[shark.dir] * i);
		int ny = shark.col + (dy[shark.dir] * i);

		if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX)
			break;

		if (tmpBoard[nx][ny].num > 0) {
			tmpBoard[shark.row][shark.col] = { EMPTY, shark.row, shark.col, -1 };
			Fish bakShark = shark;
			Fish tmp = tmpBoard[nx][ny];

			shark.row = nx;
			shark.col = ny;
			shark.dir = tmp.dir;
			tmpBoard[nx][ny] = shark;
			fishMap.erase(tmp.num);

			solution(sum + tmp.num, tmpBoard, fishMap); 

			fishMap[tmp.num] = tmp;
			shark = bakShark;
			tmpBoard[shark.row][shark.col] = shark;
			tmpBoard[nx][ny] = tmp;
		}
	}

	if (ans < sum)
		ans = sum;
}


int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	freopen("input.txt", "rt", stdin);
	Fish board[4][4];
	map<int, Fish> fishMap;
	for (int i = 0; i < MAX; i++) {
		int num, dir;
		for (int j = 0; j < MAX; j++) {
			cin >> num >> dir;
			board[i][j] = { num, i, j, dir - 1 };
			fishMap[num] = { num, i, j, dir - 1 };
		}
	}

	int sum = board[0][0].num;
	fishMap.erase(board[0][0].num);
	shark = { SHARK, 0, 0, board[0][0].dir };
	board[0][0] = shark;
	
	solution(sum, board, fishMap);

	cout << ans;

	return 0;
}