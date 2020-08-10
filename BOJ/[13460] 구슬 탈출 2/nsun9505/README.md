# [13460] 구슬 탈출 2 - C++

## 분류
> BFS
>
> 시뮬레이션

## 코드
```c++
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
```

## 풀이 방법
> https://js1jj2sk3.tistory.com/105 참조
BOARD가 어떻게 주어지는지 모르기에 완전탐색이 필요합니다. O가 있는 위치까지 가는데 Board를 최소한으로 돌리는 횟수를 요구하기에 BFS가 생각이 났습니다.
문제에서 공이 2개만 존재합니다. 오직 빨간 공(red)과 파란 공(blue)만이 존재한다.

문제를 풀기 위해서 먼저
- 두 구슬을 굴린다. 굴린다는 것은 board를 기울인다는 것이다. 상하좌우로 보드를 기울이면서 공들을 움직이게 한다.
  - 구슬들이 굴리기 위해 기울이는 방향은 상하좌우이다. 임의의 위치에 구슬들이 위치하고 거기서 상하좌우로 기울였을 때는 도착하는 위치는 기울이기 전 위치하던 구슬들에서 파생된 위치이므로 큐에 넣으면 된다. (BFS)
  - 큐에 넣을 때는 해당 위치를 방문 했는지 안 했는지 확인한다.(4차원 배열을 사용 : [red_X][red_Y][blue_X][blue_X]와 같이 하면 해당 위치를 방문했는지 점검이 가능함.)
- 빨간 공이든 파란 공이든 홀에 빠질 수 있다. 문제에서는 파란 공이 빠지면 안 된다고 했으니 파란 공이 빠지는 경우에는 해당 방향으로 기울인 경우는 continue 등을 사용해서 탐색을 하지 않아야 한다.
- 실세계에서는 두 공이 겹치는 일이 없겠지만, 여기서는 겹치게 생각하는 것이 문제를 푸는 데에 도움을 줬다.
  - 그러면 겹쳤을 경우에 어떻게 복구시키는지가 중요한 점이다.
  - \[ |  |  | R | B ](Original) 일 때, 왼쪽으로 기울인다 -> \[ RB |  |  |  |  ] 처럼 겹치게 되도록 하고
  - \[ RB |  |  |  ]를 Original의 순서를 바탕으로 원상복구 시키면 \[ R | B |  |  |  ]가 된다. 
  - 이것은 왼쪽으로 기울였을 때의 경우이고, 다른 방향으로도 기울여서 구슬들이 이동했을 때 빨간 구슬과 파란 구슬이 겹쳐있는지를 보고 원래에는 어느 순서로 있었는지 확인하여 원상복구한다.
- 구슬이 굴러가면서 'O'를 만나면 거기서 멈추면 된다.

## 후기
생각보다 어려웠습니다.
BFS가 생각이 났지만 어떻게 접근해야 할지가 생각이 쉽게 들지 않았습니다.
