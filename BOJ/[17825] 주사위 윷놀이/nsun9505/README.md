# [17825] 주사위 윷놀이 - C++

## 분류
> 구현
> 
> 완전탐색
>
> 백트래킹

## 코드
```c++
#include <iostream>
#include <vector>
#include <map>
#pragma warning(disable:4996)
using namespace std;

struct Node {
	int index;
	int value;
	int red;
	int blue;
};

map<int, Node> nodeMap;
int dice[10];
int markers[4];
int ans = -2147000000;

void solution(int idx, int sum) {
	if (idx >= 10) {
		if (ans < sum)
			ans = sum;
		return;
	}

	for (int i = 0; i < 4; i++) {
		int start = markers[i];
		int next = nodeMap[start].red;
		if (nodeMap[start].blue)
			next = nodeMap[start].blue;

		for (int i = 1; i < dice[idx]; i++) {
			next = nodeMap[next].red;
		}

		bool flag = true;
		for (int mIdx = 0; mIdx < 4; mIdx++)
			if (next != 21 && markers[mIdx] == next)
				flag = false;

		if (flag) {
			markers[i] = next;
			solution(idx + 1, sum + nodeMap[markers[i]].value);
			markers[i] = start;
		}
	}
}

void makeMap() {
	int idx = 0;
	nodeMap[idx] = { idx, 0, idx + 1, 0 };
	idx++;
	for (int i = 2; i <= 40; i+=2) {
		Node node = { idx, i, idx + 1, 0 };	
		nodeMap[idx++] = node;
	}
	nodeMap[idx] = { idx, 0, idx, idx };

	nodeMap[5].blue = 22;
	nodeMap[22] = { 22, 13, 23, 0 };
	nodeMap[23] = { 23, 16, 24, 0 };
	nodeMap[24] = { 24, 19, 30, 0 };

	nodeMap[15].blue = 25;
	nodeMap[25] = { 25, 28, 26, 0 };
	nodeMap[26] = { 26, 27, 27, 0 };
	nodeMap[27] = { 27, 26, 30, 0 };

	nodeMap[10].blue = 28;
	nodeMap[28] = { 28, 22, 29, 0 };
	nodeMap[29] = { 29, 24, 30, 0 };
	nodeMap[30] = { 30, 25, 31, 0 };
	nodeMap[31] = { 31, 30, 32, 0 };
	nodeMap[32] = { 32, 35, 20, 0 };
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	makeMap();
	for (int i = 0; i < 10; i++)
		cin >> dice[i];

	solution(0, 0);

	cout << ans;

	return 0;
}
```

## 문제 풀이
### 윷놀이 게임판 생성
![사진](https://user-images.githubusercontent.com/43994964/95042363-90804d80-0714-11eb-9614-588d097f264a.jpg)
- 사진 출처 : https://www.acmicpc.net/problem/17825
- makeMap() 함수를 통해 위의 사진 처럼 각 칸에 인덱스를 부여한다.
- 파란색으로 이동하는 경로가 있다면 blue에 다음 칸의 인덱스를 넣어준다.
- 빨간색으로 이동하는 경로는 red에 다음 칸의 인덱스를 넣어준다.
- 즉, 하드코딩하는 것이다. 
- Node에는 인덱스, 값, 빨간색 경로, 파란색 경로로 표현된다.

### 말 이동
말을 이동하는 것은 순열을 적용해서 하나의 말만 움직일 수 있고, 아니면 여러 말이 움직일 수 있게 했다.

주사위 숫자에 의해 말이 움직이는데 
- 말을 이동하기 전에 출발지점에서 파란색 경로가 있는 경우
   - 빨간색 경로로 가지 않고 파란색 경로로 이동한다.
- 파란색 경로가 없다면 빨간색 경로로 이동한다.

이동이 끝난 뒤에 해당 위치에 다른 말이 있는지 살펴본다.
- 이동이 끝난 위치가 도착 지점이 아니면서 이미 말이 있는 경우에는 DFS를 진행하지 않는다.
- 이동이 끝난 위치가 도착 지점이라면 다른 말이 있어도 이동이 가능하다.
- 이동이 끝난 위치가 도착지점이 아니고 다른 말이 없다면 이동이 끝난 위치의 값을 sum에 더해서 DFS를 진행한다.

그리고 이동한 뒤에 다시 다른 말을 이동시킨 경우도 확인하므로 움직이기 전의 위치를 따로 저장했다가 DFS가 끝났을 때 다시 위치를 원래대로 변경해야 한다.

입력 받은 10회의 주사위 값을 모두 적용했을 때의 합인 sum이 출력할 답인 ans보다 크다면 ans를 sum으로 갱신한다.

## 후기
게임판을 어떻게 만들지에 대한 고민이 많았다. 

구글링해서 다른 사람들은 어떻게 게임판을 만들었는지 보니 하드코딩을 했길래 나도 하드코딩을 하고 말이 움직이는 로직을 짰다.

게임판을 만드는 방법이 고민이 되었고, 말을 움직이는 로직은 쉬웠다.