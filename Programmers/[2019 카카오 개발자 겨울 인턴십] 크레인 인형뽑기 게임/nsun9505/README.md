# [2019 카카오 개발자 겨울 인턴십] 크레인 인형뽑기 게임 - C++

## 분류
> 시뮬레이션

## 코드
```c++
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
```

## 문제풀이
문제 풀이 과정은 다음과 같다.
1. board에 있는 인형들을 열값을 키로 같는 map에 저장
   - moves의 값들은 map에 키값이 된다.
   - board의 각 열에 존재하는 값들은 열을 키로하는 map의 stack에 쌓인다.
1. moves의 열값을 map의 키로 사용하여 이전에 저장한 인형들이 쌓여져 있는 stack의 정보를 가져온다.
   - 스택이 비어있다면 바구니에 담지 않고
   - 스택의 탑값과 바구니의 탑값이 같으면 두 개의 인형을 제거한다.
   - 값이 같지 않으면 바구니 스택에 넣어준다.
1. moves를 모두 탐색했다면 answer를 리턴

## 후기
간단한 문제!