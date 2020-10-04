# [17837] 새로운 게임 2 - C++

## 분류
> 구현
>
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
#include <map>
#pragma warning(disable:4996);
using namespace std;

struct Element {
	int idx;
	int row;
	int col;
	int dir;
};

int board[14][14];
vector<int> elemBoard[13][13];
int dx[5] = { 0, 0, 0, -1, 1 };
int dy[5] = { 0, 1, -1, 0, 0 };
int N, K;
map<int, Element> elemMap;

void init() {
	for (int i = 0; i <= N+1; i++)
		for (int j = 0; j <= N+1; j++)
			board[i][j] = 2;
}

int reverseDirection(int dir) {
	if (dir == 1)
		return 2;
	else if (dir == 2)
		return 1;
	else if (dir == 3)
		return 4;
	else if (dir == 4)
		return 3;
}

bool moveWhite(Element elem, int nx, int ny) {
	int idx = 0;
	for (int i = 0; i < elemBoard[elem.row][elem.col].size(); i++) {
		if (elemBoard[elem.row][elem.col][i] == elem.idx) {
			idx = i;
			break;
		}
	}
	int cnt = 0;
	for (int i = idx; i < elemBoard[elem.row][elem.col].size(); i++) {
		cnt++;
		int index = elemBoard[elem.row][elem.col][i];
		elemMap[index].row = nx;
		elemMap[index].col = ny;
		elemBoard[nx][ny].push_back(index);
	}

	if (elemBoard[nx][ny].size() >= 4)
		return true;

	for (int i = 0; i < cnt; i++)
		elemBoard[elem.row][elem.col].pop_back();

	return false;
}

bool moveRed(Element elem, int nx, int ny) {
	int idx = 0;
	for (int i = 0; i < elemBoard[elem.row][elem.col].size(); i++) {
		if (elemBoard[elem.row][elem.col][i] == elem.idx) {
			idx = i;
			break;
		}
	}

	int cnt = 0;
	vector<int> tmp;
	for (int i = idx; i < elemBoard[elem.row][elem.col].size(); i++) {
		cnt++;
		int index = elemBoard[elem.row][elem.col][i];
		elemMap[index].row = nx;
		elemMap[index].col = ny;
		tmp.push_back(index);
	}

	for (int i = tmp.size() - 1; i >= 0; i--)
		elemBoard[nx][ny].push_back(tmp[i]);

	if (elemBoard[nx][ny].size() >= 4)
		return true;

	for (int i = 0; i < cnt; i++)
		elemBoard[elem.row][elem.col].pop_back();

	return false;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	cin >> N >> K;
	init();
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> board[i][j];

	int r, c, d;
	for (int i = 0; i < K; i++) {
		cin >> r >> c >> d;
		elemMap[i] = { i, r, c, d };
		elemBoard[r][c].push_back(i);
	}

	for (int t = 1; t <= 1000; t++) {
		for (auto cur : elemMap) {
			Element elem = cur.second;
			
			int nx = elem.row + dx[elem.dir];
			int ny = elem.col + dy[elem.dir];

			if (board[nx][ny] == 0 && moveWhite(elem, nx, ny)) {
				cout << t;
				return 0;
			}
			else if (board[nx][ny] == 1 && moveRed(elem, nx, ny)) {
				cout << t;
				return 0;
			}
			else if (board[nx][ny] == 2) {
				elem.dir = reverseDirection(elem.dir);
				elemMap[elem.idx].dir = elem.dir;

				nx = elem.row + dx[elem.dir];
				ny = elem.col + dy[elem.dir];


				if (board[nx][ny] == 0 && moveWhite(elem, nx, ny)) {
					cout << t;
					return 0;
				}
				else if (board[nx][ny] == 1 && moveRed(elem, nx, ny)) {
					cout << t;
					return 0;
				}
			}
		}
	}
	cout << -1;
	return 0;
}
```

## 문제 풀이

### 이동하는 칸이 흰색 칸인 경우
1. 해당 vector에서 움직이는 말의 index를 찾는다.
1. 움직이는 말 위에 쌓인 말들이 있다면, 움직이는 말의 이동위치로 row, col을 바꿔준다. 그리고 이동하는 곳의 vector에 순서대로 넣는다.
1. 만약 이동한 위치의 vector 크기가 4이상이면 true를 리턴하여 프로그램을 종료하도록 한다.
1. 4보다 작다면, 이동하기 전의 위치에서 움직이는 말과 그 위로 쌓인 말들을 제거한다.

### 이동하는 칸이 빨간색 칸인 경우
1. 해당 vector에서 움직이는 말의 index를 찾는다.
1. 움직이는 말 위에 쌓인 말들이 있다면, 움직이는 말의 이동위치로 row, col을 바꿔준다.
1. 움직이는 말과 위에 쌓인 말들을 tmp vector에 담는다.
1. tmp vector를 이동하는 곳의 vector에 역순으로 넣는다.
1. 만약 이동한 위치의 vector 크기가 4이상이면 true를 리턴하여 프로그램을 종료하도록 한다.
1. 4보다 작다면, 이동하기 전의 위치에서 tmp vector에 넣었던 움직이는 말과 그 위로 쌓인 말들을 제거한다.


### 이동하는 칸이 파란색 칸인 경우
1. 방향을 반대로 변경한 후에 이동할 위치를 다시 구한다.
1. 만약 그 위치가 빨간색, 흰색인 경우 위의 경우를 그대로 따르면 된다.
1. 파란색인 경우는 움직이지 않는다.

체스판(?)을 벗어나는 경우에는 파란색 칸과 동일하다고 하여 체스판의 테두리를 파란색 칸으로 채워서

칸이 벗어나는 경우를 체크하지 않고, 파란색으로 이동하는 것으로 통일했다.

## 후기
처음에는 부모와 자식 관계로 이동시킬까 하였지만 그냥 vector에 넣어서 움직이는 것 이후의 인덱스를 모두 옮기도록 하여 구현하였다.

옮기는 도중에 4 이상이 되는지 확인하는 것을 턴이 끝난 후에 검사해서 마지막 테케를 하나하나 해보느라 시간이 조금 걸렸다..

확실히 골드2 구현은 리얼 빡구현이다...