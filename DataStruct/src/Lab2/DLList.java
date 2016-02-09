/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {
  	public class Node {
    	/** The contents of the node is public */
    	public E elt;

    	protected Node prev, next;

    	Node() {
      		this(null);
    	}
    	Node(E elt) {
      		this.elt = elt;
      		prev = next = null;
    	}

    	public Node getNext() {
      		// ************************** TODO **************************
      		return this.next;
    	}

    	public Node getPrev() {
      		// ************************** TODO **************************
      		return this.prev;
    	}
  	}
  
  	/** first and last nodes in list, null when list is empty */
  	Node first, last;
  
  	DLList() {
    	first = last = null;
  	}
  
  	/** inserts an element at the beginning of the list
   	* @param e   the new element value
   	* @return    the node holding the added element
   	*/
  	public Node addFirst(E e) {
    	// ************************** TODO **************************
 		Node tmp = new Node(e);
    	if(first == null){
    		first = tmp;
    	}
    	else {
    		first.prev = tmp;
    		tmp.next = first;
    		first = tmp;
    	}
  		return tmp;
  	}

  	/** inserts an element at then end of the list
   	* @param e   the new element
   	* @return    the node holding the added element
   	*/
  	public Node addLast(E e) {
      	// ************************** TODO **************************
      	Node tmp = new Node(e);
      	if(last == null){
      		last = tmp;
      	}
      	else {
      		last.next = tmp;
      		tmp.prev = last;
      		last = tmp;
      	}
      	return tmp;
  	}
  
  	/**
   	* @return    the node of the list's first element, null if list is empty
   	*/
  	public Node getFirst() {
      	// ************************** TODO **************************
      	return first;
  	}
  
  	/**
   	* @return    the node of the list's last element, null if list is empty
   	*/
  	public Node getLast() {
      	// ************************** TODO **************************
      	return last;
  	}
  
  	/** inserts a new element after a specified node
    * @param e   the new element
    * @param l   the node after which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  	public Node insertAfter(E e, Node l) {
      	// ************************** TODO **************************
      Node tmp = new Node(e);
      
  	}

  	/** inserts a new element before a specified node
    * @param e   the new element
    * @param l   the node before which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  	public Node insertBefore(E e, Node l) {
      	// ************************** TODO **************************
  	}

  	/** removes an element
    * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
    */
  	public void remove(Node l) {
      	// ************************** TODO **************************
  	}
}
