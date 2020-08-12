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

		newHead.first += dx[this->dir];
		newHead.second += dy[this->dir];

		if (newHead.first < 1 || newHead.second < 1 || newHead.first > N || newHead.second > N)
			return false;

		for (auto cur : this->body)
			if (newHead.first == cur.first && newHead.second == cur.second)
				return false;

		this->body.push_front(newHead);
		if (board[newHead.first][newHead.second] == 1)
			board[newHead.first][newHead.second] = 0;
		else 
			this->body.pop_back();

		if (headDir == 'L')
			this->rotateLeft();
		else if(headDir == 'D')
			this->rotateRight();

		return true;
	}
};

int main(void) {
//	freopen("input.txt", "rt", stdin);
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