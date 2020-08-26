#include <iostream>
#include <vector>
#pragma warning(disable:4996)

using namespace std;

vector<vector<int>> rotate(vector<vector<int>> origin) {
	vector<vector<int>> ret;
	for (int i = 0; i < origin.size(); i++) {
		vector<int> tmp;
		for (int j = (int)origin.size()-1; j >= 0; j--)
			tmp.push_back(origin[j][i]);
		ret.push_back(tmp);
	}
	return ret;
}

bool isUnlock(vector<vector<int>> key, vector<vector<int>> board, int row, int col, int lockSize) {
	for (int loop = 0; loop < 4; loop++) {

		vector<vector<int>> boardTmp;
		for (int i = 0; i < board.size(); i++)
			boardTmp.push_back(board[i]);

		for (int i = 0; i < key.size(); i++)
			for (int j = 0; j < key.size(); j++)
				boardTmp[row + i][col + j] += key[i][j];

		int cnt = 0;
		for (int i = key.size() - 1; i <= boardTmp.size() - key.size(); i++)
			for (int j = key.size() - 1; j <= boardTmp.size() - key.size(); j++)
				if (boardTmp[i][j] == 1)
					cnt += 1;
		
		if (cnt == lockSize*lockSize)
			return true;

		key = rotate(key);
	}
	return false;
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
	bool answer = true;
	int keySize = key.size();
	int lockSize = lock.size();

	int boardSize = (keySize-1)*2 + lockSize;
	vector<vector<int>> board;
	for (int i = 0; i < boardSize; i++) {
		vector<int> tmp;
		for (int j = 0; j < boardSize; j++)
			tmp.push_back(0);
		board.push_back(tmp);
	}

	for(int i=keySize-1; i<=boardSize-keySize; i++)
		for (int j = keySize-1; j <= boardSize - keySize; j++)
			board[i][j] = lock[i - keySize + 1][j - keySize + 1];

	for (int i = 0; i <= boardSize - keySize; i++)
		for (int j = 0; j <= boardSize - keySize; j++)
			if (isUnlock(key, board, i, j, lockSize))
				return true;
	return false;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie();

	vector<vector<int>> key;
	vector<vector<int>> lock;
	int N, M;

	cin >> M >> N;

	for (int i = 0; i < M; i++) {
		vector<int> tmp;
		int val;
		for (int j = 0; j < M; j++) {
			cin >> val;
			tmp.push_back(val);
		}
		key.push_back(tmp);

	}

	for (int i = 0; i < N; i++) {
		int val;
		vector<int> tmp;
		for (int j = 0; j < N; j++) {
			cin >> val;
			tmp.push_back(val);
		}
		lock.push_back(tmp);
	}

	cout << (solution(key, lock) ? "true" : "false");

	return 0;
}