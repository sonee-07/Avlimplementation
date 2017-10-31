


/* 
 */

/**
 * @author Sonee Paghdar
 *
 */
public class AvlTree {

	/**
	 * @param args
	 */
	static Node root;	
	
	static AvlTree avt=new AvlTree(); 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//root		
		avt.insertNode(new Node(null,null,13));
		//nodes
		avt.insertNode(new Node(null,null,10));
		avt.insertNode(new Node(null,null,11));
		avt.insertNode(new Node(null,null,15));
		avt.insertNode(new Node(null,null,20));
		avt.insertNode(new Node(null,null,5));
		avt.insertNode(new Node(null,null,8));
		avt.insertNode(new Node(null,null,4));
		avt.insertNode(new Node(null,null,25));
		//avt.calBalFac(root);
	
	//	avt.insertNode(new Node(null,null,23));
		avt.displayTree(root);
		
		//avt.delete(n3);
	//	avt.insertNode(n9);
		//avt.balTree(n8);
		
//		 System.out.print(n6.left.value );
		Node test=root;
		
		
	}
	
	public void displayTree(Node test)
	{
		if (test==null)
			return;
		displayTree(test.left);
		System.out.println(test.value+" "+test.left+" "+test.right+" "+test.balFact+" "+test.height+" "+test.min+ " "+ test .max+" "+test.mingap);
		displayTree(test.right);
		 
	}	
	public void insertNode(Node node) {
		
		if(root==null) {
			root=node;
		}
		else {
		Node x=root;
		Node y=null;
		while(x!=null)
		{
			y=x;
			if(node.value>x.value) {
				System.out.println(x.value+" "+x.max);
				if(node.max>x.max)
					x.max=node.max;
				x=x.right;
			}
			else {
				if(node.min<x.min)
					x.min=node.min;
				x=x.left;
			}
			
		}
		node.parent=y;
		System.out.println(y.value+" "+y.max);
		node.balFact=0;
		node.height=0;
		int gap=java.lang.Math.abs(y.value-node.value);
		System.out.println("gap:"+gap);
		if(gap<y.mingap) {
			y.mingap=gap;
			updateMinGap(y);
		}
		if(node.value>y.value) {
			y.right=node;
		}
		else y.left=node;

		if (y.height!=1)
			y.height=1;
		} 
		balTree(node);
	}
	
	private void updateMinGap(Node n) {
		// TODO Auto-generated method stub
		if(n==null || n.parent==null) return;
		if(n.mingap<n.parent.mingap) {
			n.parent.mingap=n.mingap;
			updateMinGap(n.parent);
		}
		
	}

	public void delete(Node n)
	{
		if(root==null) {
			return;
		}
		else {
			
			Node y=n.parent;
			if(n.left==null && n.right==null) {
				if(n.value>n.parent.value)
					n.parent.right=null;
				else n.parent.left=null;
			}
			else if(n.left==null)
			{
				n.right.parent=n.parent;
				n=n.right;
			//	System.out.print(n.right.value);
				
		
			}
			else if(n.right==null)
			{
				n.left.parent=n.parent;
				n=n.left;
				//	System.out.print(n.left.value);
				//n.parent=null; n.left=null;
				
			}
			else {
				Node p=successor(n);
				if(p.value>p.parent.value)
				{
					p.parent.right=null;
					p.parent.balFact-=1;
				}
				else {
					p.parent.left=null;
					p.parent.balFact+=1;
				}
				n.value=p.value; p.parent=null;
				
			//	System.out.print(n.value);
			}

			updateBalFact(y);
			balTree(n);
		}
	}
	
	public static Node successor(Node n)
	{
		Node x=null;
		n=n.right;
		while(n!=null) {
			x=n;
			n=n.left;
		}
		
		return x;
	}
	public void balTree(Node y) {
		
		
		Node x= y.parent;
			if (x==null)
				return;
		updateBalFact(x);		
		
		if(y.balFact<-1 || y.balFact>1)
		{
			
			if(y.balFact==-2 && y.left.balFact==-1)
				leftLeft(y);
			else if(y.balFact==2 && y.right.balFact==1)
					rightRight(y);
			else if(y.balFact==-2 && y.left.balFact==1)
						leftRight(y);
			else if(y.balFact==2 && y.right.balFact==-1)
							rightLeft(y);
			
			
			updateBalFact(x);
		
			
		}
		
		System.out.println("=>"+" "+x.value+" "+x.balFact +" "+y.value +" "+y.balFact);
		
		balTree(x);
	}
	
	public void leftLeft(Node x) {
	
		if(x.value>x.parent.value)
			x.parent.right=x.left;
		else x.parent.left=x.left;
		Node n=x.left;
		n.parent.left=n.right;
		n.right=n.parent;
		n.parent=n.right.parent;
		n.right.parent=n;

		updateBalFact(x);
		
	}
	
	public void leftRight(Node x) {
		
		Node n=x.left; Node y=n.right;
		if(x.value>x.parent.value)
			x.parent.right=y;
		else x.parent.left=y;

		n.right=n.right.left;
		y.left=n;
		x.left=y.right;
	//	System.out.println("new left of 11"+x.left);
		y.right=x;
		y.parent=x.parent;
		x.parent=y; n.parent=y;
		updateBalFact(n);
		updateBalFact(x);
		updateBalFact(y);
	}
public void rightRight(Node x) {
		
	if(x.value>x.parent.value)
		x.parent.right=x.right;
	else x.parent.left=x.right;
	Node n=x.right;
		n.parent.right=n.left;
		n.left=n.parent;
		n.parent=n.left.parent;
		n.left.parent=n;

		updateBalFact(x);
		
	}
	
	public void rightLeft(Node x) {
		
		Node n=x.right; Node y=n.left;
		if(x.value>x.parent.value)
			x.parent.left=y;
		else x.parent.right=y;

		n.left=n.left.right;
		y.right=n;
		x.right=y.left;
		//System.out.println("new left of 11"+x.left);
		y.left=x;
		y.parent=x.parent;
		x.parent=y; n.parent=y;
		updateBalFact(n);
		updateBalFact(x);
		updateBalFact(y);
	}

public void updateBalFact(Node y) {
	
	if(y.left==null && y.right==null) {
		y.balFact=0;
		y.height=0;}
	else if(y.left == null){

		y.height=1;
		y.balFact=y.right.balFact+1;
	}
	else if(y.right == null) {
		y.height=1;
		y.balFact=y.left.balFact-1;
	}
	else {
		if(y.right.height>y.left.height)
			y.height=y.right.height+1;
		else y.height=y.left.height+1;
			y.balFact=y.right.height-y.left.height;
		}
	System.out.println(y.value+" "+y.balFact+" "+y.height);
}
public void calBalFac(Node root) {
	
	
	Node x=root;
	if(x==null)
		return;
	if(x.left==null && x.right==null)
	{
		x.height=0;
		x.balFact=0;
		return;
	}
	calBalFac(x.left);
	calBalFac(x.right);
	if(x.left == null){

		x.height=x.right.height+1;
		x.balFact=x.right.balFact+1;
	}
	else if(x.right == null){
		x.height=x.left.height+1;
		x.balFact=x.left.balFact-1;
	}else {
			if(x.left.height>x.right.height)
				x.height=(x.left.height)+1;
			else x.height=(x.right.height)+1;
			x.balFact=x.right.height-x.left.height;
		}

	//System.out.println(root.value+" "+root.balFact +" "+root.height);
}}
class Node{
	
	Node left;
	Node right;
	Node parent=null;
	int height;
	int value;
	int balFact=0;
	int min;
	int max;
	int mingap=999;
	public Node(Node l, Node r,int val)
	{
		left=l;
		right=r;
		value=val;
		min=val;
		max=val;
	}
	public void setParent(Node p)
	{
		parent=p;
	}
	
	
}
