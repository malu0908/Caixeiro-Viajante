package Lista;

public class Node {
	public int value;
	public Node next;
	public Node previous;
	
    //Alem de passar o seu proximo (next) e o seu valor (value) agora também eh passado seu anterior (previous)    
	public Node(int v, Node n, Node l) {
		this.value = v;
		this.next = n;
		this.previous = l;
	}	
}
