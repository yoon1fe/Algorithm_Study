#include<iostream>
#include<vector>
using namespace std;
vector<char> v;
void change(char map[][3], char dir) { // 해당 면 돌리기
	char tmp[3][3];
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			tmp[i][j] = map[i][j];
		}
	}
	if (dir == '+') {
		int cnt = 0;
		for (int j = 2; j >= 0; j--) {
			for (int i = 0; i < 3; i++) {
				map[i][j] = tmp[cnt][i];
			}
			cnt++;
		}
	}
	else {
		for (int j = 0; j < 3; j++) {
			int cnt = 0;
			for (int i = 2; i >= 0; i--) {
				map[i][j] = tmp[j][cnt];
				cnt++;
			}
		}
	}

}

int main() {
	int n;
	cin >> n;
	for (int test = 0; test < n; test++) {
		char up[3][3];
		char down[3][3];
		char right[3][3];
		char left[3][3];
		char back[3][3];
		char front[3][3];
		for (int i = 0; i < 3; i++) {
			fill_n(up[i], 3, 'w');
			fill_n(down[i], 3, 'y');
			fill_n(right[i], 3, 'b');
			fill_n(left[i], 3, 'g');
			fill_n(back[i], 3, 'o');
			fill_n(front[i], 3, 'r');
		}
		//input
		int m;		cin >> m;
		for (int i = 0; i < m; i++) {
			char d, dir;
			cin >> d >> dir;
			switch (d)
			{
			case 'U':
				change(up, dir);
				if (dir == '+') {
					swap(left[0][0], front[0][0]);
					swap(left[0][1], front[0][1]);
					swap(left[0][2], front[0][2]);
					swap(front[0][0], right[0][0]);
					swap(front[0][1], right[0][1]);
					swap(front[0][2], right[0][2]);
					swap(right[0][0], back[0][0]);
					swap(right[0][1], back[0][1]);
					swap(right[0][2], back[0][2]);
				}
				else {
					swap(right[0][0], back[0][0]);
					swap(right[0][1], back[0][1]);
					swap(right[0][2], back[0][2]);
					swap(front[0][0], right[0][0]);
					swap(front[0][1], right[0][1]);
					swap(front[0][2], right[0][2]);
					swap(left[0][0], front[0][0]);
					swap(left[0][1], front[0][1]);
					swap(left[0][2], front[0][2]);
				}break;
			case 'D':
				change(down, dir);
				if (dir == '+') {
					swap(left[2][0], back[2][0]);
					swap(left[2][1], back[2][1]);
					swap(left[2][2], back[2][2]);
					swap(back[2][0], right[2][0]);
					swap(back[2][1], right[2][1]);
					swap(back[2][2], right[2][2]);
					swap(right[2][0], front[2][0]);
					swap(right[2][1], front[2][1]);
					swap(right[2][2], front[2][2]);
				}
				else {
					swap(right[2][0], front[2][0]);
					swap(right[2][1], front[2][1]);
					swap(right[2][2], front[2][2]);
					swap(back[2][0], right[2][0]);
					swap(back[2][1], right[2][1]);
					swap(back[2][2], right[2][2]);
					swap(left[2][0], back[2][0]);
					swap(left[2][1], back[2][1]);
					swap(left[2][2], back[2][2]);
				}break;
			case 'R':
				change(right, dir);
				if (dir == '+') {
					swap(down[0][2], front[0][2]);
					swap(down[1][2], front[1][2]);
					swap(down[2][2], front[2][2]);
					swap(back[0][0], down[0][2]);
					swap(back[1][0], down[1][2]);
					swap(back[2][0], down[2][2]);
					swap(down[0][2], down[2][2]);
					swap(up[0][2], back[0][0]);
					swap(up[1][2], back[1][0]);
					swap(up[2][2], back[2][0]);
					swap(back[0][0], back[2][0]);
				}
				else {
					swap(up[0][2], back[0][0]);
					swap(up[1][2], back[1][0]);
					swap(up[2][2], back[2][0]);
					swap(up[0][2], up[2][2]);
					swap(back[0][0], down[0][2]);
					swap(back[1][0], down[1][2]);
					swap(back[2][0], down[2][2]); // down 끝
					swap(back[0][0], back[2][0]);
					swap(down[0][2], front[0][2]);
					swap(down[1][2], front[1][2]);
					swap(down[2][2], front[2][2]); // front 끝
				}break;

			case 'L':
				change(left, dir);
				if (dir == '+') {
					swap(back[0][2], down[0][0]);
					swap(back[1][2], down[1][0]);
					swap(back[2][2], down[2][0]);
					swap(back[0][2], back[2][2]); // back 끝
					swap(down[0][0], front[0][0]);
					swap(down[1][0], front[1][0]);
					swap(down[2][0], front[2][0]); // down 끝
					swap(front[0][0], up[0][0]);
					swap(front[1][0], up[1][0]);
					swap(front[2][0], up[2][0]); // front 끝
					swap(up[0][0], up[2][0]); // up 끝
				}
				else {
					swap(up[0][0], front[0][0]);
					swap(up[1][0], front[1][0]);
					swap(up[2][0], front[2][0]);
					swap(front[0][0], down[0][0]);
					swap(front[1][0], down[1][0]);
					swap(front[2][0], down[2][0]);
					swap(down[0][0], back[0][2]);
					swap(down[1][0], back[1][2]);
					swap(down[2][0], back[2][2]);
					swap(down[0][0], down[2][0]);
					swap(back[0][2], back[2][2]);
				}break;
			case 'F':
				change(front, dir);
				if (dir == '+') {
					swap(left[0][2], down[0][0]);
					swap(left[1][2], down[0][1]);
					swap(left[2][2], down[0][2]);
					swap(down[0][0], right[0][0]);
					swap(down[0][1], right[1][0]);
					swap(down[0][2], right[2][0]);
					swap(down[0][0], down[0][2]);
					swap(right[0][0], up[2][0]);
					swap(right[1][0], up[2][1]);
					swap(right[2][0], up[2][2]);
					swap(up[2][0], up[2][2]);
				}
				else {
					swap(right[0][0], up[2][0]);
					swap(right[1][0], up[2][1]);
					swap(right[2][0], up[2][2]);
					swap(down[0][0], right[0][0]);
					swap(down[0][1], right[1][0]);
					swap(down[0][2], right[2][0]);
					swap(right[0][0], right[2][0]);
					swap(left[0][2], down[0][0]);
					swap(left[1][2], down[0][1]);
					swap(left[2][2], down[0][2]);
					swap(left[0][2], left[2][2]);
				}break;
			case 'B':
				change(back, dir);
				if (dir == '+') {
					swap(right[0][2], down[2][0]);
					swap(right[1][2], down[2][1]);
					swap(right[2][2], down[2][2]);
					swap(right[0][2], right[2][2]);
					swap(down[2][0], left[0][0]);
					swap(down[2][1], left[1][0]);
					swap(down[2][2], left[2][0]);
					swap(left[0][0], up[0][0]);
					swap(left[1][0], up[0][1]);
					swap(left[2][0], up[0][2]);
					swap(left[0][0], left[2][0]);
				}
				else {
					swap(left[0][0], up[0][0]);
					swap(left[1][0], up[0][1]);
					swap(left[2][0], up[0][2]);
					swap(up[0][0], up[0][2]);
					swap(down[2][0], left[0][0]);
					swap(down[2][1], left[1][0]);
					swap(down[2][2], left[2][0]);
					swap(right[0][2], down[2][0]);
					swap(right[1][2], down[2][1]);
					swap(right[2][2], down[2][2]);
					swap(down[2][0], down[2][2]);
				}
				break;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				cout << up[i][j];
			cout << endl;
		}
	}
	return 0;
}