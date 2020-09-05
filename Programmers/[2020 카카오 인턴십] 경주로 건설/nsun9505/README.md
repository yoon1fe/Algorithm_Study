# [2020 카카오 인턴십] 경주로 건설 - C++

## 분류
> BFS
> 
> 다익스트라..?

## 코드
```c++
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

typedef struct _path {
    int row;
    int col;
    int cost;

    _path(int r, int c, int co)
        :row(r), col(c), cost(co) {
    }
}Path;

int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int N;
int minCost[25][25];

int solution(vector<vector<int>> board) {
    int answer = 2147000000;
    N = board.size();
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            minCost[i][j] = 2147000000;

    minCost[0][0] = 0;
    queue<pair<Path, int>> Q;
    Q.push(make_pair(Path(0, 0, 0), -1));

    while (!Q.empty()) {
        pair<Path, int> pos = Q.front();
        Q.pop();

        for (int dir = 0; dir < 4; dir++) {
            int nx = pos.first.row + dx[dir];
            int ny = pos.first.col + dy[dir];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                continue;

            if (board[nx][ny] == 1)
                continue;

            Path p = { nx, ny, pos.first.cost };
            p.cost += 100;
            if (pos.second != -1 && dir != pos.second)
                p.cost += 500;

            if (p.cost <= minCost[nx][ny]) { 
                minCost[nx][ny] = p.cost;
                if (nx == N - 1 && ny == N - 1 && p.cost < answer)
                    answer = p.cost;
                Q.push(make_pair(p, dir));
            }
        }
    }
    return answer;
}

int main(void) {
    vector<vector<int>> board;
    board.push_back({ 0, 0, 1, 0 });
    board.push_back({ 0, 0, 0, 0 });
    board.push_back({ 0, 1, 0, 1 });
    board.push_back({ 1, 0, 0, 0 });

    cout << solution(board);
    return 0;
}
```

## 문제풀이
최소 경로하면 BFS라는 생각이 들었다.
- 다른 풀이들을 보니 다익스트라로 푼 경우도 있었지만, 일단 경험이 많은 BFS로 문제를 풀었다.

기존의 BFS와 다른점은
- 따로 방문했다는 표시를 하지 않는다. 
- 대신 방문하는 곳까지의 경로값이 작다면 큐에 담아서 다음에 탐색하도록 했다.

직선경로는 커브를 만들거나 만들지 않아도 하나 생기므로 기본적으로 +100을 해준다.
- 만약 이동하는 다음 칸이 현재 위치에서의 방향과 다르면 커브를 생성해야 하므로 + 500을 한다.
- 그리고 nx, ny의 값과 비교하여 작다면 갱신하고 큐에 넣는다.
- 만약 nx, ny가 N-1,N-1이면 answer 값을 변경하면서 답을 찾아간다.

## 후기
회고할 때는 다익스트라로 접근해서 풀어보자.