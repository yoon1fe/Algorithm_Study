# [2019 KAKAO BLIND RECRUITMENT] 길 찾기 게임 - C++

## 분류
> 단순 구현

## 코드
```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <tuple>

using namespace std;
int N;

typedef struct node {
    int x;
    int y;
    int num;
    struct node* leftChild;
    struct node* rightChild;
    
    node(int x, int y, int n) : x(x), y(y), num(n) {
        this->leftChild = NULL;
        this->rightChild = NULL;
    }
}Node;

typedef struct binary {
    Node* head;
    int size;
    binary() : head(NULL), size(0) {};
}BinTree;

BinTree binary;
vector<int> pre;
vector<int> post;

bool comp(Node * a, Node * b) {
    if (a->y > b->y)
        return true;
    else if (a->y == b->y)
        return a->x < b->x;
    return false;
}

void preorder(Node * node) {
    if (node == NULL)
        return;
    
    pre.push_back(node->num);
    preorder(node->leftChild);
    preorder(node->rightChild);
}

void postorder(Node * node) {
    if (node == NULL)
        return;

    postorder(node->leftChild);
    postorder(node->rightChild);
    post.push_back(node->num);
}

void insertNode(Node* cur, Node* newNode) {
    if (newNode->x < cur->x) {
        if (cur->leftChild)
            insertNode(cur->leftChild, newNode);
        else
            cur->leftChild = newNode;
    }
    else {
        if (cur->rightChild)
            insertNode(cur->rightChild, newNode);
        else
            cur->rightChild = newNode;
    }
}

void insert(Node* node) {
    if (binary.head == NULL)
        binary.head = node;
    else
        insertNode(binary.head, node);
    binary.size += 1;
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<vector<int>> answer;
    int idx = 1;
    vector<Node*> tmp;

    for (auto node : nodeinfo)
        tmp.push_back(new Node( node[0], node[1], idx++ ));
    sort(tmp.begin(), tmp.end(), comp);

    binary.head = NULL;
    binary.size = 0;

    for (auto node : tmp)
        insert(node);

    preorder(binary.head);
    postorder(binary.head);

    answer.push_back(pre);
    answer.push_back(post);

    return answer;
}

int main(void) {
    vector<vector<int>> nodeinfo;
    nodeinfo.push_back({ 5,3 });
    nodeinfo.push_back({ 11,5 });
    nodeinfo.push_back({ 13,3 });
    nodeinfo.push_back({ 3,5 });
    nodeinfo.push_back({ 6,1 });
    nodeinfo.push_back({ 1,3 });
    nodeinfo.push_back({ 8,6 });
    nodeinfo.push_back({ 7,2 });
    nodeinfo.push_back({ 2,2 });

    vector<vector<int>> ret = solution(nodeinfo);
    for (auto cur : ret) {
        for (auto node : cur)
            cout << node << ' ';
        cout << '\n';
    }

    return 0;
}
```

## 문제 풀이
1. 먼저 입력 받은 nodeinfo를 comp 함수를 이용해서 정렬한다.
- y값으로 내림차순이고, y값이 같다면 x 값으로 오름차순으로 정렬한다면
- 문제에서 원하는 그래프를 그릴 수 있다.

1. 정렬된 값을 이용해서 binary search tree를 만든다.

1. postorder, preorder 함수를 사용해서 binary search Tree를 탐색한다.

## 후기
오랜만에 binary search tree를 구현할라고 하니 까먹어서 찾아봤다..ㅋㅋㅋ

자료구조 수업 때는 공장같이 찍어냈었는데 덕분에 오랜만에 복습할 수 있었음!