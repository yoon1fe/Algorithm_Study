# ## [2020 KAKAO BLIND RECRUITMENT] 자물쇠와 열쇠 - C++

## 분류
> 문자열 처리

## 코드
```c++
#include <iostream>
#include <vector>

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
```

## 문제 풀이
배열을 잘 다루는 것이 핵심인 것 같다. 또한, 자물쇠와 열쇠를 겹치게 해서 열쇠가 자물쇠의 범위를 벗어나는 문제도 없고 모든 경우의 수를 생각할 수 있다.

이 문제를 풀기 위해서는 자물쇠와 열쇠를 포함할 수 있는 큰 배열이 필요하다. 
  - board라는 이차원 배열을 사용한다.
  - board의 중앙에는 자물쇠의 원소들이 들어가고
  - 나머지 부분에는 열쇠와 자물쇠가 최소 1칸 이상 겹치는 지점부터 열쇠를 시계방향으로 90도씩 돌려가면서 체크하면 된다.
  - 열쇠와 자물쇠가 최소 1칸 이상 겹치게 하기 위해서 board의 크기는 자물쇠크기 * (열쇠크기-1)*2만큼의 배열이 필요하다.

예를 들어, 문제와 같이 열쇠 크기가 3이고, 자물쇠의 크기가 3이라고 할 때
- 제일 처음 시작할 때 겹치는 부분은 자물쇠의 (0,0)과 열쇠의 (2,2)가 겹치는 것부터 시작한다.
- 처음에는 열쇠의 마지막 행이 겹치기 시작해서 마지막에는 열쇠의 첫 번째 행을 겹치도록 하면 열쇠와 자물쇠가 겹치는 것을 모든 경우의 수를 구할 수 있다.
- 그러면 열쇠의 마지막 행과 열쇠의 첫번째 행이 겹치는 것을 시작으로 해서 열쇠의 마지막행과 자물쇠의 마지막 행이 겹치기까지의 크기를 계산하면 다음과 같다.
   - board size =  (자물쇠 크기) + (열쇠 사이즈-1) * 2

board의 크기를 구하고 board의 중앙에 자물쇠를 세팅했다면
- board의 (0,0)부터 열쇠의 크기만큼 열쇠를 더해주고
- 자물쇠의 위치에서 자물쇠 각 원소값이 1인 경우를 카운트했을 때 
- 카운트한 값이 자물쇠 크기와 같으면 true를 반환한다.
- 아니라면 열쇠를 90도로 돌려보고, 그래도 안 되면 다음 칸으로 이동해서 처음부터 열쇠를 0, 90, 180, 270도 돌려서 맞는지 확인해보면 된다.

## 후기
이와 비슷한 문제를 풀었던 기억이 있다. (삼성 기출문제 중에 있었던거 같은데... 무슨 방역머시기인데..)
하지만 열쇠가 자물쇠의 범위를 벗아나도 된다는 점이 어렵게 다가와서 문제에 접근하기 어려웠다.

그래서 어떻게 풀면 되는 것인지 구글링을 통해서 찾아보고
구현은 내힘으로 했다. 하지만 boar의 크기 잡는 것에 대한 이해를 잘못해서 문제를 푸는데 오래 걸렸다.