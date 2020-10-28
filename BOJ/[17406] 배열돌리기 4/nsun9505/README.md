# [17406] 배열 돌리기 4 - C++

## 분류
> 구현
>
> 완전탐색

## 코드
```c++
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
```

## 문제 풀이
### 회전 연산 (r, c, s)에 대한 조합!
회전 연산에 대한 조합으로 연산을 어떤 순서로 할지 정한다.

### 회전
회전은 row 시작 부분, col 시작 부분, row 끝나는 부분, col 끝나는 부분으로 정해서 이 범위 안에서만 배열을 돌리도록 한다.
- 오른쪽, 아래, 왼쪽, 위 방향을 순서대로 돌린다.
- 대신 tmp 배열에 배열을 돌린 값을 저장한다.

만약 돌려야 하는 범위에서의 크기를 rowsize, colsize라고 하면 rowsize, colsize가 홀수이고 서로 같다면 정중앙에 있는 값을 따로 복사를 해준다.
- 왜냐하면 내가 짠 로직에서 배열이 돌아가는 것은 돌려야하는 크기에서 row와 col의 최대 사이즈가 홀수이고 같은 경우에서 정중앙 값은 복사가 안 되기 때문이다.

## 후기
배열만 잘 돌리면 끝!