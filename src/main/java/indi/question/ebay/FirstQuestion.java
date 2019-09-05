package indi.question.ebay;


import java.util.Scanner;

/**
 * @author zhoujian
 * @date 2019/9/4 21:30
 */
public class FirstQuestion {

	/**
	 * 环形链表
	 * current定位当前节点
	 * 从环中移除当前节点
	 * 仅针对n较小时，当n足够大时，使用ArrayList结构更好
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.close();
		if (n > 15 || m > 5 || n <= 0 || m <= 0) {
			System.out.println("condition is not right,n <= 15 && m >= 5");
		}
		Node head = new Node(1), tail = head, current = head;
		for (int i = 2; i <= n; i++) {
			tail.next = new Node(i);
			tail.next.pre = tail;
			tail = tail.next;
		}
		tail.next = head;
		head.pre = tail;
		int mul = 1, tmp;
		for (int i = 1; i < n; i++) {
			mul *= m;
			tmp = mul % (n - i + 1) - 1;
			if (tmp == -1) {
				current = current.pre;
			} else if (tmp > (n - i + 1) / 2) {
				tmp = n - i + 1 - tmp;
				while (tmp > 0) {
					current = current.pre;
					tmp--;
				}
			} else {
				while (tmp > 0) {
					current = current.next;
					tmp--;
				}
			}
			current.pre.next = current.next;
			current.next.pre = current.pre;
			current = current.next;
		}
		System.out.println(current.value);
	}
}

class Node {
	Node pre;
	Node next;
	int value;

	public Node(int value) {
		this.value = value;
	}
}