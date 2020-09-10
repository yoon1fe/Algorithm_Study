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