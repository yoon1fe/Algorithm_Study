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