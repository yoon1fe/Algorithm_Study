# [3190] 뱀

## 분류
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <deque>
//#pragma warning(disable:4996)

using namespace std;

int N, K, numOfDirection;
int board[101][101];
char L[10001];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

class Snake {
public:
	deque<pair<int, int>> body;
	int dir;

	Snake(int row, int col, int dir) {
		body.push_front({ row, col });
		this->dir = dir;
	}

	void rotateLeft() {
		this->dir -= 1;
		if (this->dir < 0)
			this->dir = 3;
	}

	void rotateRight() {
		this->dir = (this->dir + 1) % 4;
	}

	bool move(char headDir) {
		pair<int, int> newHead = this->body[0];
    // 새로운 머리
		newHead.first += dx[this->dir];
		newHead.second += dy[this->dir];

    // out of index 
		if (newHead.first < 1 || newHead.second < 1 || newHead.first > N || newHead.second > N)
			return false;

    // 이동한 곳에 몸이 있는지
		for (auto cur : this->body)
			if (newHead.first == cur.first && newHead.second == cur.second)
				return false;

    // 위 조건들을 모두 통과한다면 새로운 위치 deque 앞에 추가
		this->body.push_front(newHead);
    // 사과를 만난 경우 꼬리를 pop하지 않기에 길이가 하나 길어짐
		if (board[newHead.first][newHead.second] == 1)
			board[newHead.first][newHead.second] = 0;
    // 사과가 아닌 경우 꼬리 부분을 pop 함으로써 이동 구현
		else 
			this->body.pop_back();

    // X초 이후에 방향을 바꾼다? 이동후에 방향을 바꾼다로 해석하여
    // 이동 완료 시 현재 second에서 어느 방향으로 머리를 회전시켜야 하는지 결정
		if (headDir == 'L')
			this->rotateLeft();
		else if(headDir == 'D')
			this->rotateRight();

		return true;
	}
};

int main(void) {
	cin >> N;
	cin >> K;

	Snake s(1,1,1);

	for (int i = 0; i < K; i++) {
		int x, y;
		cin >> x >> y;
		board[x][y] = 1;
	}

	cin >> numOfDirection;
	int sec = 0;
	for (int i = 0; i < numOfDirection; i++) {
		char dir;
		cin >> sec >> dir;
		L[sec] = dir;
	}

	for (int i = 1; i <= 10000; i++) {
		if (!s.move(L[i])) {
			cout << i;
			return 0;
		}
	}

	cout << 10000;

	return 0;
}
```

## 풀이방법
Snake라는 클래스를 선언한여 구현했다.
입력을 받을 때 방향을 전환하는 초와 방향을 입력 받을 때 크기가 10000인 char 배열을 선언해서 매초마다 해당 초에서 방향을 바꾸는지 검사하면서 뱀을 이동시켰다.

### Snake 설명
- Snake(int row, int col, int dir)
  - 시작 위치와 방향을 입력으로 받는다.
- rotateXXX()
  - X초 후에 방향을 L또는 D 방향으로 전환할 때
  - 오른쪽이면 현재 방향 + 1 % 4를 하고, 
  - 왼쪽이면 현재 방향 -= 1을 한 뒤에 음수라면 3으로 바꿔준다.
- move(char headDir)
  - 이 문제를 푸는데 핵심
  - headDir 매개변수는 X초 이후에 방향을 변경해야 할 경우 사용하기 위한 변수이다.
  - 함수는 먼저 머리 부분(deque의 0번째 원소)을 따로 저장한다.
  - 따로 저장한 부분에 뱀이 현재 움직이는 방향으로 한 칸 움직이다.
  - 움직인 곳이 N x N을 벗어나거나, 뱀의 다른 부분들과 겹친다면 게임을 끝내기 위해 false를 리턴
  - false를 리턴하지 않았다면, 이동한 곳에 사과가 있다면 pop을 하지 않고 해당 위치의 값을 변경해준다.
  - 이동한 곳에 사과가 없다면 deque의 뒷 부분(꼬리)을 pop함으로써 이동을 구현했다.
  - 그리고 마지막에 이동을 마쳤으므로 X초 후에 방향을 전환한다를 구현하기 위해 현재 headDir의 값을 보고 방향을 변경했다.

## 후기
시뮬레이션은 재미있다..
