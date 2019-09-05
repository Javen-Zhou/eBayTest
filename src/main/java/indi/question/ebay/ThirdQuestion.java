package indi.question.ebay;

import java.util.*;

/**
 * @author zhoujian
 * @Date 2019/9/5 12:17
 */
public class ThirdQuestion {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] line;
		Map<Integer, TreeNode> map = new HashMap<>();
		int p, c;
		TreeNode pNode, cNode, hNode = null;
		for (int i = 0; i < n; i++) {
			line = sc.next().split(",");
			p = Integer.parseInt(line[0]);
			c = Integer.parseInt(line[1]);
			pNode = map.get(p);
			cNode = map.get(c);
			if (cNode == null) {
				cNode = new TreeNode(c);
				map.put(c, cNode);
			}
			if (pNode == null) {
				pNode = new TreeNode(p);
				map.put(p, pNode);
			}
			if (cNode.parent == null) {
				cNode.parent = pNode;
				pNode.childList.add(cNode);
			} else if (cNode.parent.val != pNode.val) {
				hNode = null;
				break;
			}
			if (pNode.parent == null) {
				hNode = pNode;
			}
		}
		sc.close();
		if (hNode == null) {
			System.out.println("Not a tree");
			return;
		}
		ArrayDeque<TreeNode> queue = new ArrayDeque<>();
		queue.add(hNode);
		while(!queue.isEmpty()){
			TreeNode node = queue.remove();
			System.out.println(node.val);
			if(node.childList != null && node.childList.size()>0){
				queue.addAll(node.childList);
			}
		}
	}
}

class TreeNode {
	TreeNode parent;
	List<TreeNode> childList;
	int val;

	TreeNode(int val) {
		this.val = val;
		this.childList = new ArrayList<>();
	}
}
