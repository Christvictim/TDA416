/**
 * Doubly-linked list with user access to nodes
 */

public class DLList<E> {
    public class Node {
        /**
         * The contents of the node is public
         */
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
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public E getValue() {
            return this.elt;
        }
    }

    /**
     * first and last nodes in list, null when list is empty
     */
    Node first, last;

    DLList() {
        first = last = null;
    }

    /**
     * inserts an element at the beginning of the list
     *
     * @param e the new element value
     * @return the node holding the added element
     */
    public Node addFirst(E elt) {
        Node node = new Node(elt);
        if (first == null) {
            last = node;
        } else {
            node.next = first;
            first.prev = node;
        }
        first = node;
        return node;
    }

    /**
     * inserts an element at then end of the list
     *
     * @param e the new element
     * @return the node holding the added element
     */
    public Node addLast(E elt) {
        Node node = new Node(elt);
        if (last == null) {
            first = node;

        } else {
            node.prev = last;
            last.next = node;
        }
        last = node;
        return node;
    }

    /**
     * @return the node of the list's first element, null if list is empty
     */
    public Node getFirst() {
        return first;
    }

    /**
     * @return the node of the list's last element, null if list is empty
     */
    public Node getLast() {
        return last;
    }

    /**
     * inserts a new element after a specified node
     *
     * @param e the new element
     * @param l the node after which to insert the element, must be non-null and a node belonging to this list
     * @return the node holding the inserted element
     */
    public Node insertAfter(E elt, Node l) {
        Node node = new Node(elt);
        node.prev = l;
        node.next = l.next;
        l.next = node;
        if (node.next == null) {
            last = node;
        } else {
            node.next.prev = node;
        }
        return node;
    }

    /**
     * inserts a new element before a specified node
     *
     * @param e the new element
     * @param l the node before which to insert the element, must be non-null and a node belonging to this list
     * @return the node holding the inserted element
     */
    public Node insertBefore(E elt, Node l) {
        Node node = new Node(elt);
        if (l.prev == null) {
            first = node;
        } else {
            l.prev.next = node;
        }
        node.prev = l.prev;
        node.next = l;
        l.prev = node;
        return node;
    }

    /**
     * removes an element
     *
     * @param l then node containing the element that will be removed, must be non-null and a node belonging to this list
     */
    public void remove(Node l) {
        if (l != first && l != last) {
            l.prev.next = l.next;
            l.next.prev = l.prev;
        }
        if (l == first && l == last) {
            first = last = null;
        }
        if (l == last && l != first) {
            last = l.prev;
            l.prev.next = null;
        }
        if (l == first && l != last) {
            first = l.next;
            first.prev = null;
        }
    }
}
