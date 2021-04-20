/**
 * 
 */
package spelling;

import java.util.LinkedList;
import java.util.List;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a
 * Breadth First Search of Nearby words to create a path between two words.
 * 
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class WPTree implements WordPath {

	// this is the root node of the WPTree
	private WPTreeNode root;
	// used to search for nearby Words
	private NearbyWords nw;

	// This constructor is used by the Text Editor Application
	// You'll need to create your own NearbyWords object here.
	public WPTree() {
		this.root = null;
		// TODO initialize a NearbyWords object
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		this.nw = new NearbyWords(d);
	}

	// This constructor will be used by the grader code
	public WPTree(NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}

	// see method description in WordPath interface
	public List<String> findPath(String word1, String word2) {
		// TODO: Implement this method.
		boolean word2Found = false;
		WPTreeNode word2Node = null;
		WPTreeNode newChild = null;
		List<WPTreeNode> queue = new LinkedList<WPTreeNode>();
		// TODO: Add a visited list, and perhaps a threshold
		List<String> visited = new LinkedList<String>();

		// add word1 node to queue and as child of root
		queue.add(new WPTreeNode(word1, root));
		visited.add(word1);
		while (!queue.isEmpty() && !word2Found) {
			WPTreeNode curr = queue.remove(0);
			List<String> newWords = nw.distanceOne(curr.getWord(), true);
			for (String s : newWords) {
				if (s.equals(word2)) {
					word2Found = true;
					newChild = curr.addChild(s);
//					System.out.println("found word2: " + s);

				} else if (!visited.contains(s) && !word2Found) {
					visited.add(s);
					newChild = curr.addChild(s);
					queue.add(newChild);
				}
			}
		}

		List<String> resultingPath = newChild.buildPathToRoot();
//		resultingPath.add(newChild.getWord());

		System.out.println(resultingPath.toString());
		if (word2Found) {
//			System.out.println("found word2");
			return resultingPath;
		} else {
//			System.out.println("did not find word2");
			return null;
		}
	}

	// Method to print a list of WPTreeNodes (useful for debugging)
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";

		for (WPTreeNode w : list) {
			ret += w.getWord() + ", ";
		}
		ret += "]";
		return ret;
	}

	public static void main(String[] Args) {
//		Dictionary d = new DictionaryHashSet();
//		DictionaryLoader.loadDictionary(d, "data/dict.txt");
//		NearbyWords nw = new NearbyWords(d);
//		List<String> myList = nw.distanceOne("jello", true);
//		System.out.println(myList.toString());
//
		WPTree myTree = new WPTree();
		System.out.println(myTree.findPath("pool", "spoon").toString());
		System.out.println("hi");

	}

}

/*
 * Tree Node in a WordPath Tree. This is a standard tree with each node having
 * any number of possible children. Each node should only contain a word in the
 * dictionary and the relationship between nodes is that a child is one
 * character mutation (deletion, insertion, or substitution) away from its
 * parent
 */
class WPTreeNode {

	private String word;
	private List<WPTreeNode> children;
	private WPTreeNode parent;

	/**
	 * Construct a node with the word w and the parent p (pass a null parent to
	 * construct the root)
	 * 
	 * @param w The new node's word
	 * @param p The new node's parent
	 */
	public WPTreeNode(String w, WPTreeNode p) {
		this.word = w;
		this.parent = p;
		this.children = new LinkedList<WPTreeNode>();
	}

	/**
	 * Add a child of a node containing the String s precondition: The word is not
	 * already a child of this node
	 * 
	 * @param s The child node's word
	 * @return The new WPTreeNode
	 */
	public WPTreeNode addChild(String s) {
		WPTreeNode child = new WPTreeNode(s, this);
		this.children.add(child);
		return child;
	}

	/**
	 * Get the list of children of the calling object (pass a null parent to
	 * construct the root)
	 * 
	 * @return List of WPTreeNode children
	 */
	public List<WPTreeNode> getChildren() {
		return this.children;
	}

	/**
	 * Allows you to build a path from the root node to the calling object
	 * 
	 * @return The list of strings starting at the root and ending at the calling
	 *         object
	 */
	public List<String> buildPathToRoot() {
		WPTreeNode curr = this;
		List<String> path = new LinkedList<String>();
		while (curr != null) {
			path.add(0, curr.getWord());
			curr = curr.parent;
		}
		return path;
	}

	/**
	 * Get the word for the calling object
	 *
	 * @return Getter for calling object's word
	 */
	public String getWord() {
		return this.word;
	}

	/**
	 * toString method
	 *
	 * @return The string representation of a WPTreeNode
	 */
	public String toString() {
		String ret = "Word: " + word + ", parent = ";
		if (this.parent == null) {
			ret += "null.\n";
		} else {
			ret += this.parent.getWord() + "\n";
		}
		ret += "[ ";
		for (WPTreeNode curr : children) {
			ret += curr.getWord() + ", ";
		}
		ret += (" ]\n");
		return ret;
	}

}
