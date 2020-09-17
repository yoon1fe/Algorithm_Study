import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
public class week06_길찾기게임 {
	class Node{
		int index;
		int x, y;
		Node leftChild = null;
		Node rightChild = null;
		Node(int x, int y, int index){
			this.x = x; this.y = y;
			this.index =index;
		}
	}
	
	void insertTree(Node root, Node ins) {
		if(root.x > ins.x) {
			if(root.leftChild != null)
				insertTree(root.leftChild, ins);
			else root.leftChild = ins;
		} else {
			if(root.rightChild != null)
				insertTree(root.rightChild, ins);
			else root.rightChild = ins;
		}
	}
	
	void preOrder(Node root, List<Integer> list) {
		list.add(root.index);
		if(root.leftChild != null) preOrder(root.leftChild, list);
		if(root.rightChild != null) preOrder(root.rightChild, list);
	}
	
	void postOrder(Node root, List<Integer> list) {
		if(root.leftChild != null) postOrder(root.leftChild, list);
		if(root.rightChild != null) postOrder(root.rightChild, list);
		list.add(root.index);
	}
	
	public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        Node[] nodes = new Node[nodeinfo.length];
        
        for(int i=0; i<nodeinfo.length; i++) 
        	nodes[i] = new Node(nodeinfo[i][0],nodeinfo[i][1],i+1);
        
        Arrays.sort(nodes, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o2.y == o1.y) return o1.x - o2.x;
				else return o2.y - o1.y;
			}
		});
        
        Node root = nodes[0];
        
        for(int i=1; i<nodeinfo.length; i++)
        	insertTree(root, nodes[i]);
        
        List<Integer> prelist = new ArrayList<Integer>();
        List<Integer> postlist = new ArrayList<Integer>();
        preOrder(root, prelist);
        postOrder(root, postlist);
        for(int i=0; i<nodeinfo.length; i++) {
        	answer[0][i] = prelist.get(i);
        	answer[1][i] = postlist.get(i);
        }
        return answer;
    }
}