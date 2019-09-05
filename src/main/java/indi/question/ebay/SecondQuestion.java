package indi.question.ebay;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhoujian
 * @date 2019/9/4 23:07
 */
public class SecondQuestion {
	/**
	 * 满足条件：同时存在，即需要有交集
	 * 推论：区间越小越密集
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Barrage> list = new ArrayList<Barrage>();
		for (int i = 0; i < n; i++) {
			list.add(new Barrage(sc.nextInt(), sc.nextInt()));
		}
		sc.close();
		list.sort((o1, o2) -> {
			if (o1.start < o2.start) {
				return -1;
			} else if (o1.start > o2.start) {
				return 1;
			}
			if (o1.end < o2.end) {
				return -1;
			} else if (o1.end > o2.end) {
				return 1;
			}
			return 0;
		});
		Barrage tmp, next;
		BarrageNode node, tmpNode;
		List<Barrage> result = new ArrayList<>();
		int countMax = 0, j, count;
		for (int i = 0; i < list.size(); i++) {
			tmp = list.get(i);
			node = new BarrageNode(tmp);
			j = i + 1;
			count = 1;
			if (j < list.size()) {
				while (j < list.size()) {
					next = list.get(j);
					if (next.start >= node.barrage.start && next.end < node.barrage.end) {
						tmpNode = node;
						node = new BarrageNode(next);
						node.pre = tmpNode;
						count++;
					} else if (next.start >= node.barrage.end) {
						if (count > countMax) {
							result.clear();
							result.add(node.barrage);
							countMax = count;
						} else if (count == countMax) {
							result.add(node.barrage);
						}
						if (node.pre != null) {
							node = node.pre;
							count--;
							continue;
						} else {
							break;
						}
					} else {
						tmpNode = node;
						node = new BarrageNode(new Barrage(next.start, Math.min(tmpNode.barrage.end, next.end)));
						node.pre = tmpNode;
						count++;

					}
					j++;
					if (j == list.size()) {
						if (count > countMax) {
							result.clear();
							result.add(node.barrage);
							countMax = count;
						} else if (count == countMax) {
							result.add(node.barrage);
						}
					}
				}

			} else {
				if (count > countMax) {
					result.clear();
					result.add(node.barrage);
					countMax = count;
				} else if (count == countMax) {
					result.add(node.barrage);
				}
			}
		}

		result.forEach(b -> System.out.println(b.start + "   " + b.end));
	}

}

class Barrage {
	int start, end;

	Barrage(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

class BarrageNode {
	BarrageNode pre;
	Barrage barrage;

	public BarrageNode(Barrage barrage) {
		this.barrage = barrage;
	}
}