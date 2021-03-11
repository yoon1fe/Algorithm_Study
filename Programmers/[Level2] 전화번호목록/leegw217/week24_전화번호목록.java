import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class week24_전화번호목록 {
	class Node {
		String character;
		boolean isfinal;
		List<Node> child;
		
		public Node(String character) {
			this.character = character;
			this.isfinal = false;
			this.child = new ArrayList<Node>();
		}
	}

	class Trie {
		Node root;
		public Trie() {
			this.root = new Node("");
		}
		
		public boolean insert(String key) {
			Node temp = root;
			loop:
			for(int i=0; i<key.length(); i++) {
				char c = key.charAt(i);
				for(int j=0; j<temp.child.size(); j++) {
					if(temp.child.get(j).character.equals(String.valueOf(c))) {
						temp = temp.child.get(j);
						if(temp.isfinal) return false;
						continue loop;
					}
				}
				Node node = new Node(String.valueOf(c));
				temp.child.add(node);
				temp = node;
			}
			temp.isfinal = true;
			return true;
		}
	}
	
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}        	
		});
        Trie trie = new Trie();
        for(int i=0; i<phone_book.length; i++)
        	if(!trie.insert(phone_book[i])) return false;
        return answer;
    }
	
	public static void main(String[] args) {
		week24_전화번호목록 m = new week24_전화번호목록();
		String[] phone_book = {"12","123","1235","567","88"};
		System.out.println(m.solution(phone_book));
	}
}