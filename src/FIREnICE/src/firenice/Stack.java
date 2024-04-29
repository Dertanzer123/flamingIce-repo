package firenice;

public class stack {
	private int top;
	private Object[] elements;
public stack(int capa) {
	top=-1;
	elements=new Object[capa];
}
	
public void push(Object pushed) {
	if(isFull()) {System.out.println("stack overflow");}
	else{top++;
	elements[top]=pushed;}
}
public Object pop() {
	
	if (isEmpty()) {
		System.out.println("stack is empty");
		return (Integer) null;
		
	}
	else {
	
	Object data = elements[top];
	elements[top]=(Integer) null;
	top--;
	return data;}
}
public int size() {
	return top+1;
}
public boolean isFull() {
	
	return (elements.length==top+1) ;
		
		
	
}


public boolean isEmpty() {

	return (top==-1);	
	
	
}

public Object Peek() {
	
	if (isEmpty()) {
		System.out.println("stack is empty");
		return (Integer) null;
		
	}else {
		return elements[top];
	}
	
	
}


}
