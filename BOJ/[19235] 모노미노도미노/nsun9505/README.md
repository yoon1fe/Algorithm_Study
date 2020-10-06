# [19235] 모노미노도미노 - C++

## 분류
> 구현
>
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
#include <map>
#include <set>
#pragma warning(disable:4996)

using namespace std;

struct Block {
	int idx;
	int row;
	int col;
};

int N;
int score;
int numOfTile;
int blueBoard[6][4];
int greenBoard[6][4];
map<int, vector<Block>> blueMap;
map<int, vector<Block>> greenMap;

vector<Block> moveTile(int board[6][4], vector<Block> tile) {
	vector<Block> move = tile;
	while (true) {
		for (int i = 0; i < tile.size(); i++)
			tile[i].row += 1;

		for (auto t : tile)
			if (t.row > 5 || board[t.row][t.col] != 0)
				return move;

		for (int i = 0; i < move.size(); i++) {
			move[i].row = tile[i].row;
			move[i].col = tile[i].col;
		}
	}
}

vector<int> checkRow(int board[6][4]) {
	int idx = -1;
	vector<int> removeRows;
	for (int i = 5; i >= 0; i--) {
		int cnt = 0;
		for (int j = 0; j < 4; j++)
			if (board[i][j] != 0)
				cnt++;
		if (cnt == 4)
			removeRows.push_back(i);
	}
	return removeRows;
}

void removeRow(int board[6][4], int row, map<int, vector<Block>>& blockMap) {
	for (int col = 0; col < 4; col++) {
		if (board[row][col] == 0)
			continue;
		
		int idx = board[row][col];
		vector<Block> block = blockMap[idx];
		for (vector<Block>::iterator it = block.begin(); it != block.end(); it++) {
			if (it->row == row && it->col == col) {
				block.erase(it);
				board[row][col] = 0;
				break;
			}
		}

		if (block.size() == 0)
			blockMap.erase(idx);
		else
			blockMap[idx] = block;
	}
}

void printBoard(int board[6][4]) {
	for (int i = 0; i < 6; i++) {
		for (int j = 0; j < 4; j++)
			cout << board[i][j] << ' ';
		cout << '\n';
	}
	cout << '\n';
}

void moveAllTile(int board[6][4], map<int, vector<Block>>& blockMap) {
	for (auto cur : blockMap) {
		for (auto pos : cur.second)
			board[pos.row][pos.col] = 0;
		blockMap[cur.first] = moveTile(board, cur.second);
		for (auto pos : blockMap[cur.first])
			board[pos.row][pos.col] = pos.idx;
	}
}

void checkFullRow(int board[6][4], map<int, vector<Block>>& blockMap) {
	while (true) {
		vector<int> removeRows = checkRow(board);

		if (removeRows.size() == 0)
			break;

		for (auto rIdx : removeRows)
			removeRow(board, rIdx, blockMap);

		moveAllTile(board, blockMap);
		score += removeRows.size();
	}
}

void checkLightColor(int board[6][4], map<int, vector<Block>>& blockMap) {
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 4; j++) {
			if (board[i][j] == 0)
				continue;
			removeRow(board, 5, blockMap);
			break;
		}

		for (auto cur : blockMap) {
			for (auto pos : cur.second)
				board[pos.row][pos.col] = 0;
			blockMap[cur.first] = moveTile(board, cur.second);
			for (auto pos : blockMap[cur.first])
				board[pos.row][pos.col] = pos.idx;
		}
	}
}

void putTile(int idx, int board[6][4], vector<Block> tiles, map<int, vector<Block>>& blockMap) {
	vector<Block> tile = moveTile(board, tiles);
	for (auto t : tile)
		board[t.row][t.col] = t.idx;
	blockMap[idx] = tile;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	freopen("input.txt", "rt", stdin);
	cin >> N;
	int t, x, y;
	int idx = 1;
	for (int loop = 0; loop < N; loop++, idx++) {
		cin >> t >> x >> y;
		vector<Block> blueTile;
		vector<Block> greenTile;

		if (t == 1) {
			blueTile.push_back({ idx, 0, 3 - x });
			greenTile.push_back({ idx, 0, y });
		}
		else if (t == 2) {
			blueTile.push_back({ idx, 1, 3 - x });
			blueTile.push_back({ idx, 0, 3 - x });
			greenTile.push_back({ idx, 0, y});
			greenTile.push_back({ idx, 0, y + 1 });
		}
		else if (t == 3) {
			blueTile.push_back({ idx, 0, 3 - x });
			blueTile.push_back({ idx, 0, 3 - (x + 1) });
			greenTile.push_back({ idx, 1, y });
			greenTile.push_back({ idx, 0, y });
		}

		putTile(idx, blueBoard, blueTile, blueMap);
		putTile(idx, greenBoard, greenTile, greenMap);

		// 행과 열 검사 후 꽉 차있는 곳 제거
		checkFullRow(blueBoard, blueMap);
		checkFullRow(greenBoard, greenMap);

		// 연한 부분에 있는지 체크 후 이동
		checkLightColor(blueBoard, blueMap);
		checkLightColor(greenBoard, greenMap);
	}
	cout << score << '\n';
	int cnt = 0;
	for (auto block : greenMap)
		cnt += block.second.size();
	for (auto block : blueMap)
		cnt += block.second.size();
	cout << cnt;
	return 0;
}
```

## 문제 풀이
파란색을 열로 지우는 것이 아니라 행으로 지우기 위해 입력 받은 블록을 조정하여 파란색 판에 놓았다.
- 그러면 초록색 판과 동일하게 행으로 연산이 가능하다.
- 파란색 판을 바라보는 것은 아래 사진과 같다.
<img src="https://user-images.githubusercontent.com/43994964/95188874-0addca00-0808-11eb-83d9-bb2e78394828.png" width="400" height="600">

예를 들어 **2 3 0**을 입력으로 받았다고 가정하면, 초록색 판은 그대로 내려가면 된다.<br>
파란색 판은 위의 그림에서 보면 열값이 바꾸고, 열이 빨간색의 행에 대응하기에 타일 모양에 따라 알맞게 열을 지정해주면 된다.
- t = 1 : (0, 3-x) 
- t = 2 : (0, 3 - x), (1, 3 - x)
- t = 3 : (0, 3 - x), (0, 3 - x + 1)

### 1. 타일 이동
해당 타일이 끝에 도달하거나 다른 타일로 인해 이동하지 못할 때까지 이동시킨다.

실제 이동은 하지 않고, 이동할 수 있는 위치를 리턴받는다.

리턴받은 위치로 초록색/파란색 판에 기록하고 부여한 인덱스를 사용해서 map에 기록한다.

map에 기록하는 이유는 나중에 삭제가 일어났을 때 블록이 이동할 때 같은 타일끼리 한 번에 이동시키기 위해서이다.

### 2. 꽉차있는 행들을 vector에 담아서 한 번에 삭제
초록색/파란색 판에서 행들을 살펴본다.

만약 꽉 찬 행이 있다면, 삭제할 행을 저장하는 vector에 저장한다.

꽉 찬 행이 있다면, vector에서 삭제할 행들을 받아오면서 먼저 다 지운다.
- 모두 지운 뒤에 나머지 타일들을 끝까지 또는 이동할 수 있는 곳까지 이동시킨다.
- map에는 들어온 인덱스 순으로 정렬되어 있기에 순서대로 받아서 이동시키므로 뒤에 것이 먼저 이동하는데 앞에 것이 이동하지 않아서 이동하지 못하고 끝나는 경우는 없다.
- 이동하기 전에 미리 타일들이 있는 위치를 0으로 초기화 하고, 이동할 수 있는 곳을 알아낸 다음에 그 위치에 해당 타일을 놓는다.
- 삭제한 행만큼 score에 더한다.

없다면, 삭제 후의 이동이 일어나지 않고 연한 색 부분에 타일이 있는지 살펴본다.

### 3. 색이 연한 부분에 타일이 있는지 살펴본다.
0 행을 살펴보고, 만약 0 행에 타일이 있다면 5 행의 타일들을 지우다. 없다면 지우지 않는다.
- 0 행에 타일이 있어서 5행을 지운 경우 나머지를 타일들을 한칸씩 밑으로 내린다.

1 행을 살펴보고, 만약 1행에 타일이 있다면 5 행의 타일들을 지운다. 없다면 지우지 않는다.
- 1 행에 타일이 있어서 5행을 지운 경우 나머지 타일들을 한칸씩 밑으로 내린다.

### 4. score를 출력하고, 초록색/파란색 판에 타일의 개수를 카운트하여 출력한다.

## 후기
꽉찬 행이 여러 개일 때 하나씩 지우고 이동시키는 것이 아니라 한 번에 다 삭제하고 이동시켜야 했다.

반례를 찾아보고 해서 통과할 수 있었다.

시뮬은 역시나 재미있다.

과연 이 문제가 나왔으면 풀 수 있었을까 싶다...