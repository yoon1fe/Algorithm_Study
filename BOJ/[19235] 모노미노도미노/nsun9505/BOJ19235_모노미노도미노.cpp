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