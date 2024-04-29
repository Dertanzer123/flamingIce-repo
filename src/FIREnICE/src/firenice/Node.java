package firenice;

public class Node {

	private Node link;
	private Comrobot data;
	
	public Node(Comrobot data) 
	{
		this.data=data;
		link=null;
	}

	public Node getLink() {
		return link;
	}

	public void setLink(Node link) {
		this.link = link;
	}

	public Comrobot getData() {
		return data;
	}

	public void setData(Comrobot data) {
		this.data = data;
	}
	
	
}
