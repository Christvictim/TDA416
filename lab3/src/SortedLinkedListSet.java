public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E> {
    class Node {
        private E value; //its value
        protected Node next; //this nodes next node.

        public Node(E value) {
            this.value = value;
            this.next = null;
        }

        public E getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private int size;
    private Node head; //head of list

    public SortedLinkedListSet() {
        this.size = 0;
        this.head = null; //make sure its null, because we want an empty set upon creating
    }

    @Override
    public int size() {
        return size; //obvious
    }

    private int counter = 1;

    @Override
    public boolean add(E x) {
        counter++;
        //handle if head is null
        if (head == null) {
            head = new Node(x);
            head.next = null;
            size++;
            return true;
        }

        //create one node to represent the head, "prev" to use while i locate, and the one to be added
        Node currentNode = head;
        Node prev = null;
        Node newNode = new Node(x);

        //check if the new nodes value is less than the heads
        if (newNode.getValue().compareTo(currentNode.getValue()) < 0) {
            newNode.next = currentNode;
            head = newNode;
            size++;
            return true;
        }

        while (newNode.getValue().compareTo(currentNode.getValue()) >= 0) {
            //Check if the newnodes value if equal to the currentnodes
            if (newNode.getValue().equals(currentNode.getValue())) {
                return false;
            }

            //we have come to the last node in the set and havent found the right place to put the newnode in, lets put it here
            if (currentNode.next == null) {
                currentNode.next = newNode;
                newNode.next = null;
                size++;
                return true;
            }

            //keep locating where the new node should be
            prev = currentNode;
            currentNode = prev.next;
        }

        //if it comes here it has found where to put the new node and inserts it before returning
        prev.next = newNode;
        newNode.next = currentNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(E x) {
        Node curr = head;
        Node prev = null;
        Node toRemove = new Node(x);
        while (curr != null) {
            //if values are the same we check if 1) is head, 2) just an element in the set.
            //just rearrange so they are coupled the right way and subtract the size
            if (curr.getValue().equals(toRemove.getValue())) {
                if (curr == head) {
                    head = curr.next;
                    curr.next = null;
                } else {
                    prev.next = curr.next;
                    curr.next = null;
                }
                size--;
                return true;
            }
            //moving forward in the set
            prev = curr;
            curr = prev.next;

        }
        //the element doesnt exist in the set
        return false;
    }

    @Override
    public boolean contains(E x) {
        Node curr = head;
        Node toCheck = new Node(x);
        while (curr != null) {
            if (curr.getValue().equals(toCheck.getValue())) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public String toString() {
        Node curr = head;
        String str = "";
        while (curr != null) {
            str += curr.toString() + " ";
            curr = curr.next;
        }
        return str;
    }
}