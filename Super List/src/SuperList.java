import java.util.EmptyStackException;

public class SuperList<E> {
	
	ListNode<E> root;
	ListNode<E> end;
	int size;
	
	public SuperList () {
		root = null;
		end = null;
		size = 0;
	}
	
	public SuperList (E object) {
		this.root = new ListNode<E>(object);
		this.end = this.root;
		size = 1;
	}
	
	public void add (E object) {
		ListNode<E> newNode = new ListNode<E>(object);
		if (this.isEmpty()) {
			this.root = newNode;
			this.end = this.root;
		} else {
			this.end.setNext(newNode);
			newNode.setPrevious(this.end);
			this.end = newNode;
		}
		size++;
	}
	
	public void add (int index, E object) {
		ListNode<E> toAdd = new ListNode<E>(object);
		if (index < 0 || index > this.size()) {
			throw (new ArrayIndexOutOfBoundsException());
		}
		else if (index == this.size()) {
			this.add(object);
		}
		else if (index == 0) {
			this.root.setPrevious(toAdd);
			toAdd.setNext(this.root);
			this.root = toAdd;
			size++;
		} else {
			ListNode<E> toShift = this.getNode(index);
			toAdd.setNext(toShift);
			toAdd.setPrevious(toShift.getPrevious());
			toAdd.getPrevious().setNext(toAdd);
			toShift.setPrevious(toAdd);
			size++;
		}
		
	}
	
	public E remove (int index) {
		if (index < 0 || index > this.size()) {
			throw (new ArrayIndexOutOfBoundsException());
		}
		else if (index == 0) {
			return this.poll();
		}
		else if (index == this.size()-1) {
			return this.pop();
		} else {
			ListNode<E> temp = this.getNode(index);
			temp.getNext().setPrevious(temp.getPrevious());
			temp.getPrevious().setNext(temp.getNext());
			size--;
			return temp.getValue();
		}
	}
	
	public void clear () {
		this.root = null;
		this.end = null;
		size = 0;
	}
	
	public E get (int index) {
		if (index < 0 || index > this.size()-1) {
			throw (new ArrayIndexOutOfBoundsException());
		}
		ListNode<E> temp = this.getNode(index);
		
		return temp.getValue();
	}
	
	public ListNode<E> getNode (int index) {
		if (index < 0 || index > this.size()-1) {
			throw (new ArrayIndexOutOfBoundsException());
		}
		ListNode<E> temp = this.root;
		for (int i = 0; i <= index; i++) {
			if (i > 0) {
				temp = temp.getNext();
			}
		}
		return temp;
	}
	
	public String toString () {
		String str = "[";
		
		ListNode<E> temp = this.root;
		for (int i = 0; i < this.size(); i++) {
			str += temp.getValue();
			if (i < size-1) {
				str += ", ";
			}
			temp = temp.getNext();
		}
		str += "]";
		return str;
	}
	
	public int size() {
		return this.size;
	}
	
	public void push (E object) {
		this.add(object);
	}
	
	public E pop () {
		if (size > 1) {
			ListNode<E> temp = this.end;
			this.end = this.end.getPrevious();
			this.end.setNext(null);
			this.size--;
			return temp.getValue();
		}
		else if (size == 1) {
			ListNode<E> temp = this.end;
			this.clear();
			return temp.getValue();
		} else {
			throw (new EmptyStackException());
		}
	}
	
	public E poll () {
		if (size > 1) {
			ListNode<E> temp = this.root;
			this.root = this.root.getNext();
			this.root.setPrevious(null);
			this.size--;
			return temp.getValue();
		}
		else if (size == 1) {
			ListNode<E> temp = this.root;
			this.clear();
			return temp.getValue();
		} else {
			return null;
		}
	}
	
	public E stackPeek () {
		return this.end.getValue();
	}
	
	public E queuePeek () {
		return this.root.getValue();
	}
	
	public boolean isEmpty () {
		if (this.root == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean contains (E object) {
		boolean doesContain = false;
		ListNode<E> node = this.root;
		for (int i = 0; i < this.size(); i++) {
			if (node.getValue().equals(object)) {
				doesContain = true;
				break;
			} else {
				node = node.getNext();
			}
		}
		
		return doesContain;
	}
	
	public class ListNode<E> {
		
		private E object;
		private ListNode<E> previous;
		private ListNode<E> next;
		
		public ListNode (E object) {
			this.object = object;
			previous = null;
			next = null;
		}
		
		public E getValue () {
			return this.object;
		}
		
		public void setValue (E object) {
			this.object = object;
		}
		
		public ListNode<E> getPrevious () {
			
			return this.previous;
		}
		
		public ListNode<E> getNext () {
			
			return this.next;
		}
		
		public void setPrevious (ListNode<E> prev) {
			this.previous = prev;			
		}
		
		public void setNext (ListNode<E> nextNode) {
			this.next = nextNode;
		}
		
		public boolean hasPrevious () {
			if (previous.equals(null)) {
				return false;
			} else {
				return true;
			}
		}
		
		public boolean hasNext () {
			if (next.equals(null)) {
				return false;
			} else {
				return true;
			}
		}
	}
}
