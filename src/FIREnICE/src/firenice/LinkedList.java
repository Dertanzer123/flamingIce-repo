package firenice;

import fireAndice.ComRobot;

public class LinkedList {

	private Node Head;

	public LinkedList() {
		Head = null;
	}

	public void add(Comrobot comrobot) {
		if (Head == null) {
			Head = new Node(comrobot);

		} else {
			Node temp = Head;
			while (temp.getLink() != null) {
				temp = temp.getLink();
			}
			Node lastnode = new Node(comrobot);
			temp.setLink(lastnode);
		}

	}

	public int Size() {
		Node temp = Head;
		int size = 0;
		while (temp != null) {
			temp = temp.getLink();
			size++;
		}
		return size;

	}

	public Node getHead() {
		return Head;
	}

	public void setHead(Node head) {
		Head = head;
	}
	

	

}
