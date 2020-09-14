# [2019 KAKAO BLIND RECRUITMENT] 블록 게임

## 분류
> 완전탐색
> 
> 시뮬레이션

## 코드
```c++
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
```

## 문제 풀이
블록을 가득 채울 수 있는 방법은 2x3, 3x2 직사각형이 꽉 찰 경우이다.
- 번호가 같은 4개의 정사각형 작은 블록이 있고, 빈칸이 0인 정사각형 작은 블록이 2개인 경우를 체크한다.
- 해당 블록을 찾으면 그 블록은 제거할 수도 있는 2x3 또는 3x2 직사각형 블록이다.

제거 가능한 블록
- 빈칸에 1x1 검은색 블록이 2개가 올 수 있고 같은 숫자로 된 1x1 블록이 4개가 있다면
- 해당 블록을 제거한다.
- 블록을 제거한다는 것은 4개의 블록을 0으로 채워주면 해당 블록을 제거하는 것
- 그리고 cnt를 하나 늘려주고 NxN을 다 돌았을 때 answer에 더한다.
- cnt가 0이라면 제거할 수 있는 블록이 없으므로 do-while문을 지우고 answer를 리턴하면 된다.

## 후기
재미있는 문제