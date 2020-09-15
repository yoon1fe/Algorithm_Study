#include <iostream>
#include <string>
#include <vector>
#include <stack>
#include <map>
using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    map<int, stack<int>> boardMap;
    for (int i = board.size() - 1; i >= 0; i--) {
        for (int j = 0; j < board.size(); j++) {
            if(board[i][j] != 0)
                boardMap[j + 1].push(board[i][j]);
        }
    }

    
    stack<int> basket;
    for (int move : moves) {
        if (boardMap[move].empty())
            continue;

        if (basket.empty())
            basket.push(boardMap[move].top());
        else if (basket.top() == boardMap[move].top()) {
            basket.pop();
            answer += 2;
        }
        else {
            basket.push(boardMap[move].top());
        }

        boardMap[move].pop();
    }

    return answer;
}

int main(void) {
    vector<vector<int>> board = {
        {0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 }, { 3, 5, 1, 3, 1 }
    };
    vector<int> moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
    cout << solution(board, moves);
    return 0;
}