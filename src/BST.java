
class BSTNode<T> {
	public String key;
	public T data;
	public BSTNode<T> left, right;

	public BSTNode(String key, T data) {
		this.key = key;
		this.data = data;
		left = right = null;
	}
	
}

public class BST<T> {

	private BSTNode<T> root, current;
	private int elementNb = 0;


	public BST() {
		current = root = null;
	}

	public void clear() {
		current = root = null;
	}

	public boolean empty() {
		return getRoot() == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve() {
		return current.data;
	}

	public boolean findKey(String k) {
		elementNb=0;
		
		BSTNode<T> p = getRoot();
		while (p != null) {
			current = p;
			if (k.compareTo(p.key) == 0) {
				elementNb++;
				return true;
			} else if (k.compareTo(p.key) < 0) {
				elementNb++;
				p = p.left;
			} else {
				elementNb++;
				p = p.right;
			}
		}
	
		return false;
	}

	public boolean insert(String k, T val) {
		if (getRoot() == null) {
			
			current = root = new BSTNode<T>(k, val);
			
			return true;
		}

		BSTNode<T> p = current;
		if (findKey(k)) {
			current = p;
			return false;
		}

		BSTNode<T> tmp = new BSTNode<T>(k, val);
		if (k.compareTo(current.key) < 0) {
			current.left = tmp;
		} else {
			current.right = tmp;
		}
		
		current = tmp;
		
		return true;
	}

	public boolean removeKey(String k) {

		// Search for k
		String k1 = k;
		BSTNode<T> p = getRoot();
		BSTNode<T> q = null; // Parent of p
		while (p != null) {

			if (k1.compareTo(p.key) < 0) {
				q = p;
				p = p.left;
			} else if (k1.compareTo(p.key) > 0) {
				q = p;
				p = p.right;
			} else { // Found the key
				
				// Check the three cases
				if ((p.left != null) && (p.right != null)) { // Case 3: two
					// children

					// Search for the min in the right subtree
					BSTNode<T> min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;
					// Now fall back to either case 1 or 2
				}

				// The subtree rooted at p will change here
				if (p.left != null) { // One child
					p = p.left;
				} else { // One or no children
					p = p.right;
				}

				if (q == null) { // No parent for p, root must change
					root = p;
				} else {
					if (k1.compareTo(q.key) < 0) {
						q.left = p;
					} else {
						q.right = p;
					}
				}
				current = getRoot();
				return true;

			}
		}

		return false; // Not found
	}
	

	public int getElementNb() {
		return elementNb;
	}


	public T getTagData(String tag) {
		if (findKey(tag)) {
			return current.data;
		}
		return null;
	}


	public BSTNode<T> getRoot() {
		return root;
	}
	



}