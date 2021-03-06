package genericTrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TreeTest {

	public static void main(String[] args) {
		TreeNode<Integer> root = takeInput();
		printLevelWise(root);
	}

	// ----------------------------------------------------------------------------//
	// Taking Input level wise - first root, then no. of children of root,
	// then enter value for each children and this goes on....
	// ----------------------------------------------------------------------------//
	public static TreeNode<Integer> takeInput() {
		Scanner sc = new Scanner(System.in);
		Queue<TreeNode<Integer>> pendingNodes = new LinkedList<>();

		System.out.println("Enter root data: ");
		int rootData = sc.nextInt();
		if (rootData == -1) {
			sc.close();
			return null;
		}

		TreeNode<Integer> root = new TreeNode<>(rootData);
		pendingNodes.add(root);

		while (!pendingNodes.isEmpty()) {
			TreeNode<Integer> front = pendingNodes.poll();
			System.out.println("Enter no. of children for " + front.data + " : ");
			int numChild = sc.nextInt();

			for (int i = 0; i < numChild; i++) {
				System.out.println("For " + front.data + ",  Enter child " + (i + 1) + " : ");
				int childData = sc.nextInt();
				TreeNode<Integer> child = new TreeNode<Integer>(childData);
				front.children.add(child);
				pendingNodes.add(child);
			}

		}

		sc.close();
		return root;
	}

	// ----------------------------------------------------------------------------//
	// Search for a node in a generic tree
	// ----------------------------------------------------------------------------//
	public static boolean checkIfContainsX(TreeNode<Integer> root, int x) {

		// Write your code here
		if (root.data == x) {
			return true;
		}
		boolean b = false;
		for (int i = 0; i < root.children.size(); i++) {
			b = b || checkIfContainsX(root.children.get(i), x);
		}

		return b;

	}

	// ----------------------------------------------------------------------------//
	// Print Tree level wise , newline for each level
	// ----------------------------------------------------------------------------//

	public static void printLevelWise(TreeNode<Integer> root) {

		System.out.println("Level Order :");

		Queue<TreeNode<Integer>> pendingNodes = new LinkedList<>();
		pendingNodes.add(root);
		pendingNodes.add(null);
		if (root == null) {
			return;
		}

		while (!pendingNodes.isEmpty()) {
			TreeNode<Integer> curr = pendingNodes.poll();

			if (curr == null) {
				if (!pendingNodes.isEmpty()) {
					pendingNodes.add(null);
					System.out.println();
				}
			} else {
				for (int i = 0; i < curr.children.size(); i++) {
					pendingNodes.add(curr.children.get(i));
				}
				System.out.print(curr.data + " ");
			}
		}

	}

	// ----------------------------------------------------------------------------//
	// Number of nodes greater than a value 'x'
	// ----------------------------------------------------------------------------//
	public static int numNodeGreater(TreeNode<Integer> root, int x) {

		if (root == null) {
			return 0;
		}

		int count = 0;
		if (root.data > x) {
			count++;
		}

		for (int i = 0; i < root.children.size(); i++) {
			int smallCount = numNodeGreater(root.children.get(i), x);
			count += smallCount;
		}

		return count;
	}

	// ----------------------------------------------------------------------------//
	// Height of a generic tree
	// ----------------------------------------------------------------------------//

	public static int getHeight(TreeNode<Integer> root) {

		if (root == null) {
			return 0;
		}

		int height = 0;

		for (int i = 0; i < root.children.size(); i++) {
			int smallHeight = getHeight(root.children.get(i));
			height = Math.max(height, smallHeight);
		}

		return 1 + height;

	}
	// ----------------------------------------------------------------------------//
	// Count leaf nodes
	// ----------------------------------------------------------------------------//

	public static int countLeafNodes(TreeNode<Integer> root) {

		// Write your code here
		if (root == null) {
			return 0;
		}

		if (root.children.size() == 0) {
			return 1;
		}

		int leafNodes = 0;
		for (int i = 0; i < root.children.size(); i++) {
			int tinyLeaves = countLeafNodes(root.children.get(i));
			leafNodes += tinyLeaves;
		}

		return leafNodes;

	}

	// ----------------------------------------------------------------------------//
	// Post Order Traversal
	// ----------------------------------------------------------------------------//

	public static void printPostOrder(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}

		for (int i = 0; i < root.children.size(); i++) {
			printPostOrder(root.children.get(i));
		}

		System.out.print(root.data + " ");

	}

	// ----------------------------------------------------------------------------//
	// PRE-order Traversal
	// ----------------------------------------------------------------------------//

	public static void printTreePreOrder(TreeNode<Integer> root) {
		if (root == null) {
			return;
		}

		System.out.print(root.data + " : ");

		for (int i = 0; i < root.children.size(); i++) {
			System.out.print(root.children.get(i).data + " ");
		}
		System.out.println();

		for (int i = 0; i < root.children.size(); i++) {
			printTreePreOrder(root.children.get(i));
		}

	}
	// ----------------------------------------------------------------------------//
	// Number of nodes
	// ----------------------------------------------------------------------------//

	public static int noOfNodes(TreeNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int count = 1;
		for (int i = 0; i < root.children.size(); i++) {
			int childCount = noOfNodes(root.children.get(i));
			count += childCount;
		}

		return count;
	}

	// ----------------------------------------------------------------------------//
	// Node having sum of children and node is max
	// ----------------------------------------------------------------------------//
	static class Pair {
		TreeNode<Integer> maxNode;
		int maxSum;

		public Pair(TreeNode<Integer> element, int sum) {
			this.maxNode = element;
			this.maxSum = sum;
		}
	}

	public static Pair maxSumNodeHelper(TreeNode<Integer> root) {
		if (root == null) {
			Pair ans = new Pair(null, Integer.MIN_VALUE);
			return ans;
		}

		int sum = root.data;
		for (int i = 0; i < root.children.size(); i++) {
			sum += root.children.get(i).data;
		}

		Pair max = new Pair(root, sum);

		for (int i = 0; i < root.children.size(); i++) {
			Pair ans = maxSumNodeHelper(root.children.get(i));
			if (ans.maxSum > max.maxSum) {
				max.maxSum = ans.maxSum;
				max.maxNode = ans.maxNode;
			}
		}

		return max;

	}

	public static TreeNode<Integer> maxSumNode(TreeNode<Integer> root) {

		Pair ans = maxSumNodeHelper(root);
		return ans.maxNode;

	}

	// ============================================================================

	/*
	 * -----------------------------------------------------------------------------
	 * ----- Structurally identical
	 * -----------------------------------------------------------------------------
	 * -----
	 */

	public static boolean checkIdentical(TreeNode<Integer> root1, TreeNode<Integer> root2) {

		// Write your code here
		if (root1.data != root2.data) {
			return false;
		}

		if (root1.children.size() != root2.children.size()) {
			return false;
		}

		for (int i = 0; i < root1.children.size(); i++) {
			int num1 = root1.children.get(i).data;
			int num2 = root2.children.get(i).data;
			if (num1 != num2)
				return false;
		}

		boolean ans = true;
		for (int i = 0; i < root1.children.size(); i++) {
			ans = ans && checkIdentical(root1.children.get(i), root2.children.get(i));
		}

		return ans;

	}

	// =====================================================================================//

	/*
	 * -----------------------------------------------------------------------------
	 * ----- Next larger element
	 * -----------------------------------------------------------------------------
	 * -----
	 */
	public static TreeNode<Integer> findNextLargerNode(TreeNode<Integer> root, int n) {

		if (root == null) {
			return null;
		}

		TreeNode<Integer> maxNode = null;
		if (root.data > n) {
			maxNode = root;
		}

		for (int i = 0; i < root.children.size(); i++) {
			TreeNode<Integer> temp = findNextLargerNode(root.children.get(i), n);
			if (temp != null) {
				if (maxNode == null) {
					maxNode = temp;
				} else {
					if (temp.data > n && temp.data < maxNode.data) {
						maxNode = temp;
					}
				}
			}
		}

		return maxNode;

	}

	// =====================================================================================//
	/*
	 * -----------------------------------------------------------------------------
	 * ----- Second Largest Element
	 * -----------------------------------------------------------------------------
	 * -----
	 */

	static class LargePair {
		TreeNode<Integer> max;
		TreeNode<Integer> secondMax;

		public LargePair(TreeNode<Integer> max, TreeNode<Integer> secondMax) {
			this.max = max;
			this.secondMax = secondMax;
		}
	}

	public static LargePair findSecondLargestHelper(TreeNode<Integer> root) {
		if (root == null) {
			LargePair ans = new LargePair(null, null);
			return ans;
		}

		if (root.children.size() == 0) {
			LargePair ans = new LargePair(root, null);
			return ans;
		}

		LargePair ans = new LargePair(root, null);

		for (int i = 0; i < root.children.size(); i++) {
			LargePair temp = findSecondLargestHelper(root.children.get(i));

			if (ans.max.data < temp.max.data) {
				TreeNode<Integer> x = ans.max;
				ans.max = temp.max;
				if (ans.secondMax == null && temp.secondMax == null) {
					ans.secondMax = x;
				} else if (ans.secondMax != null && temp.secondMax == null) {
					ans.secondMax = x;
				} else if (ans.secondMax == null && temp.secondMax != null) {
					if (temp.secondMax.data > x.data) {
						ans.secondMax = temp.secondMax;
					} else {
						ans.secondMax = x;
					}
				} else {
					if (x.data > temp.secondMax.data) {
						ans.secondMax = x;
					} else {
						ans.secondMax = temp.secondMax;
					}
				}
			} else if (ans.max.data > temp.max.data) {
				if (ans.secondMax == null) {
					ans.secondMax = temp.max;
				} else {
					if (ans.secondMax.data < temp.max.data) {
						ans.secondMax = temp.max;
					}
				}
			} else {
				if (ans.secondMax == null) {
					ans.secondMax = temp.secondMax;
				}
			}

		}

		return ans;

	}

	public static TreeNode<Integer> findSecondLargest(TreeNode<Integer> root) {
		LargePair ans = findSecondLargestHelper(root);
		return ans.secondMax;
	}

	// =====================================================================================//

	/*
	 * -----------------------------------------------------------------------------
	 * ----- Replace node with depth
	 * -----------------------------------------------------------------------------
	 * -----
	 */

	public static void replaceWithDepthValue(TreeNode<Integer> root, int level) {
		if (root == null) {
			return;
		}

		root.data = level;

		for (int i = 0; i < root.children.size(); i++) {
			replaceWithDepthValue(root.children.get(i), level + 1);
		}

	}

	public static void replaceWithDepthValue(TreeNode<Integer> root) {
		replaceWithDepthValue(root, 0);
	}

	// =====================================================================================//

}
