package firenice;


public class circularqueue {

	private Object[] elements;
	private int rear, front;

	public circularqueue(int capacity) {

		elements = new Object[capacity];
		rear = -1;
		front = 0;
	

	}

	public void enqueue(Object data) {
		if (isFull()) {
			System.out.println("queue is full");
		} else {
			
			rear = (rear + 1) % elements.length;
			elements[rear] = data;

		}
	}

	public Object dequeue() {
		if (isEpmty()) {
			System.out.println("queue is empty");
			return null;
		} else {
			Object data = elements[front];
			elements[front] = null;
			front = (front + 1) % elements.length;
		
			return data;
		}
	}

	public Object peek() {
		if (isEpmty()) {
			System.out.println("queue is empty");
			return null;
			
		}

		else	return elements[front];

	}

	public boolean isFull() {

		return (front==(rear+1)%elements.length 
				&& elements[front]!=null
				&&elements[rear]!=null);	}

	public boolean isEpmty() {

		return elements[front]==null;

	}

	public int size() {

		if (rear >= front) {

			return rear - front + 1;
		} else {
			return elements.length - (front - rear) + 1;
		}

	}

}
