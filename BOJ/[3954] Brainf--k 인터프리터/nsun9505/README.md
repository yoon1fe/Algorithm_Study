# [3954] Brainf**k 인터프리터 - C++

## 분류
> 구현

## 코드
```c++
#include <iostream>
#include <vector>
#include <stack>
#include <string>
#include <map>
#pragma warning(disable:4996)

using namespace std;

int T, Sm, Sc, Si;
int arrPtr, strPtr;
int arr[100001];
char inputStr[4097];
vector<char> cmds;

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> Sm >> Sc >> Si;
		arrPtr = 0;
		strPtr = 0;
		cmds.clear();
		for (int i = 0; i < Sm; i++)
			arr[i] = 0;

		char cmd;
		stack<int> st;
		map<int, int> loopPair;
		for (int i = 0; i < Sc; i++) {
			cin >> cmd;
			cmds.push_back(cmd);

			if (cmd == '[')
				st.push(i);
			else if (cmd == ']') {
				loopPair[st.top()] = i;
				loopPair[i] = st.top();
				st.pop();
			}
		}

		cin >> inputStr;
		
		int cmdIdx = 0;
		for(int i=0; i<50000000; i++) {
			if (cmdIdx == cmds.size())
				break;

			char curCmd = cmds[cmdIdx];

			if (curCmd == '-') {
				arr[arrPtr] -= 1;
				if (arr[arrPtr] < 0)
					arr[arrPtr] = 255;
			}
			else if (curCmd == '+') {
				arr[arrPtr] += 1;
				if (arr[arrPtr] > 255)
					arr[arrPtr] = 0;
			}
			else if (curCmd == '<') {
				arrPtr -= 1;
				if (arrPtr < 0)
					arrPtr = Sm - 1;
			}
			else if (curCmd == '>') {
				arrPtr += 1;
				if (arrPtr == Sm)
					arrPtr = 0;
			}
			else if (curCmd == '[') {
				if (arr[arrPtr] == 0)
					cmdIdx = loopPair[cmdIdx];
				else
					st.push(cmdIdx);
			}
			else if (curCmd == ']') {
				if (arr[arrPtr] != 0)
					cmdIdx = loopPair[cmdIdx];
				else
					st.pop();
			}
			else if (curCmd == ',') {
				if (strPtr >= Si)
					arr[arrPtr] = 255;
				else
					arr[arrPtr] = (int)inputStr[strPtr++];
			}
			cmdIdx++;
		}
		if (cmdIdx < cmds.size()) {
			int start = st.top();
			while (!st.empty()) {
				start = st.top();
				st.pop();
			}
			cout << "Loops " << start << ' ' << loopPair[start] << '\n';
		}
		else
			cout << "Terminates\n";
	}
	return 0;
}
```

## 문제 풀이
문제에 나와 있는 것처럼 - + < > [ ] . , 에 대한 기능을 구현하면 된다.

신경을 써야 할 부분은 [ ]인데 [ + [ + - ] ] 와 같은 입력이 주어졌다면, 무한루프는 안쪽 루프가 아니라 바깥쪽 루프로 출력해줘야 한다.
- 그래서 나는 stack을 써서 [를 만나면 루프 안으로 들어가는 조건이 맞으면 [의 인덱스를 스택에 넣는다.
- ]를 만나게 되면 루프를 탈출할 조건이 되면다면 stack에서 pop하고, 아니라면 ]와 쌍을 이루는 [로 이동하면 된다.
- 마지막에 5천번 이상 명령어를 수행해서 무한 루프를 찾은 경우는 stack의 bottom에 있는 [의 인덱스와 쌍을 이루는 ]의 인덱스를 출력하면 된다.

## 후기
틀렸습니다 13번.. 왜 틀렸냐 > , 할 때 인덱스를 너무 너무 바보같이 체크했다...

고치니 바로 되더라...
- \> 할때 sm-1이 되면 0으로 돌렸다.. 당연히 틀리지 멍청아..
- 그리고 ,면 문자열 인덱스가 si가 됐을 때 255를 넣었어야 하는데 si-1이면 넣는다는 둥.. 뭔 생각으로 코딩을 하는지 참..

나는 멍청이다

나는 멍청이다

나는 멍청이다

반성합니다.