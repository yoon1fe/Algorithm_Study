#include <iostream>
#include <vector>

using namespace std;
struct Element {
	int r;
	int c;
	int s;
};
int N, M, K;
int board[51][51];
int tmpBoard[51][51];
int tmp[51][51];
bool isUsed[6];
vector<Element> ops;
int ans = 2147000000;

void DFS(int cnt, vector<Element> op) {
	if (cnt >= ops.size()) {
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++)
				tmpBoard[i][j] = board[i][j];
		
		for (int i = 0; i < op.size(); i++) {
			Element elem = op[i];
			int startRow = elem.r - elem.s;
			int startCol = elem.c - elem.s;
			int endRow = elem.r + elem.s;
			int endCol = elem.c + elem.s;
			int loop = (endRow - startRow + 1) / 2;

			for (int l = 0; l < loop; l++) {
				int sRow = startRow + l;
				int sCol = startCol + l;
				int eRow = endRow - l;
				int eCol = endCol - l;

				// 오른쪽
				for (int col = sCol; col < eCol; col++)
					tmp[sRow][col + 1] = tmpBoard[sRow][col];
				// 아래
				for (int row = sRow; row < eRow; row++)
					tmp[row + 1][eCol] = tmpBoard[row][eCol];
				// 왼쪽
				for (int col = eCol; col > sCol; col--)
					tmp[eRow][col - 1] = tmpBoard[eRow][col];
				// 위
				for (int row = eRow; row > sRow; row--)
					tmp[row - 1][sCol] = tmpBoard[row][sCol];
			}
			int rowSize = (endRow - startRow + 1);
			int colSize = (endCol - startCol + 1);
			if (rowSize == colSize && rowSize % 2 && colSize % 2) {
				rowSize = rowSize / 2;
				colSize = colSize / 2;
				tmp[startRow + rowSize][startCol + colSize] = tmpBoard[startRow + rowSize][startCol + colSize];
			}

			for (int i = startRow; i <= endRow; i++)
				for (int j = startCol; j <= endCol; j++)
					tmpBoard[i][j] = tmp[i][j];
		}

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= M; j++)
				sum += tmpBoard[i][j];
			if (sum < ans)
				ans = sum;
		}

		return;
	}

	for (int i = 0; i < ops.size(); i++) {
		if (isUsed[i])
			continue;

		isUsed[i] = true;
		op.push_back(ops[i]);
		DFS(cnt + 1, op);
		op.pop_back();
		isUsed[i] = false;
	}
}


int main(void) {
	ios_base::sync_with_stdio(0);
	cin >> N >> M >> K;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= M; j++)
			cin >> board[i][j];

	int r, c, s;
	for (int i = 0; i < K; i++) {
		cin >> r >> c >> s;
		ops.push_back({ r, c, s });
	}
	vector<Element> op;
	DFS(0, op);
	cout << ans << '\n';

	return 0;
}