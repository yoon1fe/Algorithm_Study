#include <iostream>
#include <string>
#include <vector>
#include <map>
#pragma warning(disable:4996)
using namespace std;

vector<vector<int>> realBoard;

bool isRemove(int row, int col, int rowLimit, int colLimit) {
    map<int, vector<pair<int, int>>> blockCnt;
    for (int i = row; i < row + rowLimit; i++) {
        for (int j = col; j < col + colLimit; j++) {
            blockCnt[realBoard[i][j]].push_back({ i, j });
        }
    }

    if (blockCnt[0].size() != 2)
        return false;

    bool flag = false;
    int removeNum = 0;
    for (auto block : blockCnt)
        if (block.first != 0 && block.second.size() == 4) {
            flag = true;
            removeNum = block.first;
        }

    if (!flag)
        return false;

    for (auto emptyPos : blockCnt[0])
        for (int i = 0; i < emptyPos.first; i++)
            if (realBoard[i][emptyPos.second])
                return false;

    for (auto removePos : blockCnt[removeNum])
        realBoard[removePos.first][removePos.second] = 0;

    return true;
}

int solution(vector<vector<int>> board) {
    int answer = 0;
    int cnt = 0;
    int N = board.size();
    realBoard = board;
    do{
        cnt = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (row <= N - 2 && col <= N - 3 && isRemove(row, col, 2, 3))
                    cnt++;
                if (row <= N - 3 && col <= N - 2 && isRemove(row, col, 3, 2))
                    cnt++;
            }
        }
        answer += cnt;
    } while (cnt);

    return answer;
}

int main(void) {
    freopen("input.txt", "rt", stdin);
    vector<vector<int>> board;
    
    for (int i = 0; i < 10; i++) {
        int val;
        vector<int> tmp;
        for (int j = 0; j < 10; j++) {
            cin >> val;
            tmp.push_back(val);
        }
        board.push_back(tmp);
    }
    int ret = solution(board);
    cout << ret;
   
    return 0;
}