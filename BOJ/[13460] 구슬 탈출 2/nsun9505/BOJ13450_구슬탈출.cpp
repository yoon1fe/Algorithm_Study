#include <iostream>
#include <queue>

#define ROW first
#define COL second

using namespace std;

typedef struct marble {
	int depth;
	int red_row, red_col;
	int blue_row, blue_col;
}Marbles;

int N, M, ans = -1;
char board[11][11];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
bool isVisited[11][11][11][11];

int idx_red_row, idx_red_col;
int idx_blue_row, idx_blue_col;
int hall_row, hall_col;

pair<int, int> moveMarble(int row, int col, int dir) {
	pair<int, int> ret = { row, col };
	while (true) {
		ret.ROW += dx[dir];
		ret.COL += dy[dir];

		if (board[ret.ROW][ret.COL] == '#') {
			ret.ROW -= dx[dir];
			ret.COL -= dy[dir];
			break;
		}
		else if (board[ret.ROW][ret.COL] == 'O')
			break;
	}
	return ret;
}


int main(void) {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
			if (board[i][j] == 'R') {
				idx_red_row = i;
				idx_red_col = j;
			}
			else if (board[i][j] == 'B') {
				idx_blue_row = i;
				idx_blue_col = j;
			}
			else if (board[i][j] == 'O') {
				hall_row = i;
				hall_col = j;
			}
		}
	}

	queue<Marbles> Q;
	Marbles init = { 0, idx_red_row, idx_red_col, idx_blue_row, idx_blue_col };
	Q.push(init);
	isVisited[idx_red_row][idx_red_col][idx_blue_row][idx_blue_col] = true;

	while (!Q.empty()) {
		Marbles elem = Q.front();
		Q.pop();

		if (elem.depth > 10)
			break;

		if (elem.red_row == hall_row && elem.red_col == hall_col) {
			ans = elem.depth;
			break;
		}

		for (int dir = 0; dir < 4; dir++) {
			int red_row = elem.red_row; 
			int red_col = elem.red_col;

			int blue_row = elem.blue_row;
			int blue_col = elem.blue_col;

			pair<int, int> red = moveMarble(red_row, red_col, dir);
			pair<int, int> blue = moveMarble(blue_row, blue_col, dir);

			if (blue.ROW == hall_row && blue.COL == hall_col)
				continue;

			if (red.ROW == blue.ROW && red.COL == blue.COL) {
				if (dir == 0)
					elem.red_row < elem.blue_row ? blue.ROW++ : red.ROW++;
				else if (dir == 2)
					elem.red_row < elem.blue_row ? red.ROW-- : blue.ROW--;
				else if (dir == 1)
					elem.red_col < elem.blue_col ? red.COL-- : blue.COL--;
				else
					elem.red_col < elem.blue_col ? blue.COL++ : red.COL++;
			}

			if (!isVisited[red.ROW][red.COL][blue.ROW][blue.COL]) {
				Marbles next = { elem.depth + 1, red.ROW, red.COL, blue.ROW, blue.COL };
				Q.push(next);
				isVisited[red.ROW][red.COL][blue.ROW][blue.COL] = true;
			}
		}
	}
	cout << ans;

	return 0;
}