# [5373] 큐빙 - C++

## 분류
> 구현
>
> 시뮬레이션

## 코드
```c++
#include <cstdio>
#include <vector>
#pragma warning(disable:4996)
#define TOP		0
#define BOTTOM	1
#define	FRONT	2
#define BACK	3
#define LEFT	4
#define RIGHT	5

using namespace std;

char cube[6][3][3];

void init(int idx, char color) {
	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			cube[idx][i][j] = color;
}

void rotateTopCW() {
	vector<int> tmp = {cube[BACK][0][0], cube[BACK][0][1], cube[BACK][0][2] };

	for (int i = 0; i < 3; i++)
		cube[BACK][0][i] = cube[LEFT][0][i];
	for (int i = 0; i < 3; i++)
		cube[LEFT][0][i] = cube[FRONT][0][i];
	for (int i = 0; i < 3; i++)
		cube[FRONT][0][i] = cube[RIGHT][0][i];
	for (int i = 0; i < 3; i++)
		cube[RIGHT][0][i] = tmp[i];
}

void rotateTopCCW() {
	vector<int> tmp = { cube[BACK][0][0], cube[BACK][0][1], cube[BACK][0][2] };

	for (int i = 0; i < 3; i++)
		cube[BACK][0][i] = cube[RIGHT][0][i];
	for (int i = 0; i < 3; i++)
		cube[RIGHT][0][i] = cube[FRONT][0][i];
	for (int i = 0; i < 3; i++)
		cube[FRONT][0][i] = cube[LEFT][0][i];
	for (int i = 0; i < 3; i++)
		cube[LEFT][0][i] = tmp[i];
}

void rotateBottomCW() {
	vector<int> tmp = { cube[BACK][2][0], cube[BACK][2][1], cube[BACK][2][2] };

	for (int i = 0; i < 3; i++)
		cube[BACK][2][i] = cube[RIGHT][2][i];
	for (int i = 0; i < 3; i++)
		cube[RIGHT][2][i] = cube[FRONT][2][i];
	for (int i = 0; i < 3; i++)
		cube[FRONT][2][i] = cube[LEFT][2][i];
	for (int i = 0; i < 3; i++)
		cube[LEFT][2][i] = tmp[i];
}

void rotateBottomCCW() {
	vector<int> tmp = { cube[BACK][2][0], cube[BACK][2][1], cube[BACK][2][2] };

	for (int i = 0; i < 3; i++)
		cube[BACK][2][i] = cube[LEFT][2][i];
	for (int i = 0; i < 3; i++)
		cube[LEFT][2][i] = cube[FRONT][2][i];
	for (int i = 0; i < 3; i++)
		cube[FRONT][2][i] = cube[RIGHT][2][i];
	for (int i = 0; i < 3; i++)
		cube[RIGHT][2][i] = tmp[i];
}

void rotateLeftCW() {
	vector<int> tmp = { cube[TOP][0][0], cube[TOP][1][0], cube[TOP][2][0] };
	
	for (int i = 0, j = 2 ; i < 3; i++, j--)
		cube[TOP][i][0] = cube[BACK][j][2];
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[BACK][i][2] = cube[BOTTOM][j][0];
	for (int i = 0; i < 3; i++)
		cube[BOTTOM][i][0] = cube[FRONT][i][0];
	for (int i = 0; i < 3; i++)
		cube[FRONT][i][0] = tmp[i];
}

void rotateLeftCCW() {
	vector<int> tmp = { cube[TOP][2][0], cube[TOP][1][0], cube[TOP][0][0] };
	for (int i = 0; i < 3; i++)
		cube[TOP][i][0] = cube[FRONT][i][0];
	for (int i = 0; i < 3; i++)
		cube[FRONT][i][0] = cube[BOTTOM][i][0];
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[BOTTOM][i][0] = cube[BACK][j][2];
	for (int i = 0; i < 3; i++)
		cube[BACK][i][2] = tmp[i];
}

void rotateRightCW() {
	vector<int> tmp = { cube[TOP][2][2], cube[TOP][1][2], cube[TOP][0][2] };

	for (int i = 0; i < 3; i++)
		cube[TOP][i][2] = cube[FRONT][i][2];
	for (int i = 0; i < 3; i++)
		cube[FRONT][i][2] = cube[BOTTOM][i][2];
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[BOTTOM][i][2] = cube[BACK][j][0];
	for (int i = 0; i < 3; i++)
		cube[BACK][i][0] = tmp[i];
}

void rotateRightCCW() {
	vector<int> tmp = { cube[TOP][0][2], cube[TOP][1][2], cube[TOP][2][2] };
	
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[TOP][i][2] = cube[BACK][j][0];
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[BACK][i][0] = cube[BOTTOM][j][2];
	for (int i = 0; i < 3; i++)
		cube[BOTTOM][i][2] = cube[FRONT][i][2];
	for (int i = 0; i < 3; i++)
		cube[FRONT][i][2] = tmp[i];
}

void rotateFrontCW() {
	vector<int> tmp = { cube[TOP][2][0], cube[TOP][2][1], cube[TOP][2][2] };
	
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[TOP][2][i] = cube[LEFT][j][2];
	for (int i = 0; i < 3; i++)
		cube[LEFT][i][2] = cube[BOTTOM][0][i];
	for (int i = 0, j =2; i < 3; i++, j--)
		cube[BOTTOM][0][i] = cube[RIGHT][j][0];
	for (int i = 0; i < 3; i++)
		cube[RIGHT][i][0] = tmp[i];
}

void rotateFrontCCW() {
	vector<int> tmp = {cube[TOP][2][2], cube[TOP][2][1], cube[TOP][2][0]};
	for (int i = 0; i < 3; i++)
		cube[TOP][2][i] = cube[RIGHT][i][0];
	for (int i = 0,j=2; i < 3; i++, j--)
		cube[RIGHT][i][0] = cube[BOTTOM][0][j];
	for (int i = 0; i < 3; i++)
		cube[BOTTOM][0][i] = cube[LEFT][i][2];
	for (int i = 0; i < 3; i++)
		cube[LEFT][i][2] = tmp[i];
}

void rotateBackCW() {
	vector<int> tmp = { cube[TOP][0][2], cube[TOP][0][1], cube[TOP][0][0] };
	for (int i = 0; i < 3; i++)
		cube[TOP][0][i] = cube[RIGHT][i][2];
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[RIGHT][i][2] = cube[BOTTOM][2][j];
	for (int i = 0; i < 3; i++)
		cube[BOTTOM][2][i] = cube[LEFT][i][0];
	for (int i = 0; i < 3; i++)
		cube[LEFT][i][0] = tmp[i];

}

void rotateBackCCW() {
	vector<int> tmp = { cube[TOP][0][0], cube[TOP][0][1], cube[TOP][0][2] };
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[TOP][0][i] = cube[LEFT][j][0];
	for (int i = 0; i < 3; i++)
		cube[LEFT][i][0] = cube[BOTTOM][2][i];
	for (int i = 0, j = 2; i < 3; i++, j--)
		cube[BOTTOM][2][i] = cube[RIGHT][j][2];
	for (int i = 0; i < 3; i++)
		cube[RIGHT][i][2] = tmp[i];
}

void clockwise(int idx) {
	int tmp[3][3];
	for (int i = 0; i < 3; i++) {
		int k = 0;
		for (int j = 2; j >= 0; j--, k++)
			tmp[i][k] = cube[idx][j][i];
	}

	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			cube[idx][i][j] = tmp[i][j];
}


void counterClockwise(int idx) {
	int tmp[3][3];
	int k = 0;
	for (int i = 2; i >= 0; i--, k++) {
		for (int j = 0; j < 3; j++)
			tmp[k][j] = cube[idx][j][i];
	}

	for (int i = 0; i < 3; i++)
		for (int j = 0; j < 3; j++)
			cube[idx][i][j] = tmp[i][j];
}


int main(void) {
	freopen("input.txt", "rt", stdin);
	vector<char> colors = { 'w', 'y', 'r', 'o', 'g', 'b' };
	for (int i = 0; i < colors.size(); i++)
		init(i, colors[i]);

	char tmp[6][3][3];
	for (int idx = 0; idx < 6; idx++)
		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++)
				tmp[idx][row][col] = cube[idx][row][col];

	int N, M;
	scanf("%d ", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d ", &M);

		for (int idx = 0; idx < 6; idx++)
			for (int row = 0; row < 3; row++)
				for (int col = 0; col < 3; col++)
					cube[idx][row][col] = tmp[idx][row][col];

		for (int j = 0; j < M; j++) {
			char idx, dir;
			scanf("%c %c ", &idx, &dir);
			if (idx == 'U') {
				if (dir == '+') {
					rotateTopCW();
					clockwise(TOP);
				}
				else if (dir == '-') {
					rotateTopCCW();
					counterClockwise(TOP);
				}
			}
			else if (idx == 'D') {
				if (dir == '+') {
					rotateBottomCW();
					clockwise(BOTTOM);
				}
				else if (dir == '-') {
					rotateBottomCCW();
					counterClockwise(BOTTOM);
				}
			}
			else if (idx == 'F') {
				if (dir == '+') {
					rotateFrontCW();
					clockwise(FRONT);
				}
				else if (dir == '-') {
					rotateFrontCCW();
					counterClockwise(FRONT);
				}
			}
			else if (idx == 'B') {
				if (dir == '+') {
					rotateBackCW();
					clockwise(BACK);
				}
				else if (dir == '-') {
					rotateBackCCW();
					counterClockwise(BACK);
				}
			}
			else if (idx == 'L') {
				if (dir == '+') {
					rotateLeftCW();
					clockwise(LEFT);
				}
				else if (dir == '-') {
					rotateLeftCCW();
					counterClockwise(LEFT);
				}
			}
			else if (idx == 'R') {
				if (dir == '+') {
					rotateRightCW();
					clockwise(RIGHT);
				}
				else if (dir == '-') {
					rotateRightCCW();
					counterClockwise(RIGHT);
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				printf("%c", cube[TOP][i][j]);
			printf("\n");
		}
	}

	return 0;
}
```

## 문제 풀이
문제 풀이라고 할게 있을까..?

큐브는 아래처럼 펼치고 풀면 쉽게 풀 수 있음.
```
         [012]
         [012]
         [012]
    [012][012][012][012]
    [012][012][012][012]
    [012][012][012][012]
         [012]
         [012]
         [012]
```
1. 한 면을 회전시킬 때 상, 하, 좌, 우에 해당하는 인덱스를 잘 살피고
   - 회전하는 면도 +90, -90도로 회전시켜 줘야 한다.
1. 하나의 면이 회전할 때 어느 면의 어떤 인덱스들이 움직이게 될지 잘 살펴야함.
   - 그냥 리얼 빡구현

## 후기
리얼.. 처음 만나보는 문제