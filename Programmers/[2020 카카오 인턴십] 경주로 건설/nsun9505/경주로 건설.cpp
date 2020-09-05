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