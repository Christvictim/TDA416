public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {
    class Node {
        private E value;
        protected Node parent;
        protected Node rightChild;
        protected Node leftChild;

        public Node(E value) {
            this.value = value;
            this.parent = null;
            this.rightChild = null;
            this.leftChild = null;
        }

        public E getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        public boolean isLeftChild() {
            return ((this.parent != null) && (this.parent.leftChild == this));
        }

        public boolean isRightChild() {
            return ((this.parent != null) && (this.parent.rightChild == this));
        }

        public void setLeftChild(Node node) {
            this.leftChild = node;
            if (node != null) {
                node.parent = this;
            }
        }
        public void setRightChild(Node node) {
            this.rightChild = node;
            if (node != null) {
                node.parent = this;
            }
        }
    }

    private int size = 0;
    public Node root = null;

    private void setRoot(Node node) {
        this.root = node;
        if (node != null) {
            node.parent = null;
        }
    }


    /**
     * Method to get the size of the SplayTree
     *
     * @return the ammount of objects in the splaytree
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Method to add a node to the tree, then Splays the element the corresponding right way to the root of the tree
     *
     * @param x
     * @return true if the nodes been added and splayed to the root, false if it didnt
     */
    @Override
    public boolean add(E x) {
        Node tmp = root;
        Node toAdd = new Node(x);
        /*Node test = findNode(toAdd);
        if(test != null){
        	return false;
        }*/

        if (root == null) {
            setRoot(toAdd);
            size++;
            return true;
        }

        //locates where to put the added node and splays it up to the root
        while(true){
            if(toAdd.getValue().compareTo(tmp.getValue()) == 0){
                return false;
            }
            else if (toAdd.getValue().compareTo(tmp.getValue()) < 0) {
                if (tmp.leftChild != null) {
                    tmp = tmp.leftChild;
                } else {
                    tmp.leftChild = toAdd;
                    toAdd.parent = tmp;
                    size++;
                    splay(toAdd);
                    return true;
                }
            } else if (toAdd.getValue().compareTo(tmp.getValue()) > 0) {
                if (tmp.rightChild != null) {
                    tmp = tmp.rightChild;
                } else {
                    tmp.rightChild = toAdd;
                    toAdd.parent = tmp;
                    size++;
                    splay(toAdd);
                    return true;
                }
            } else{
                return false;
            }
        }
    }

    /**
     * Method to remove a node, if it is found in the tree
     *
     * @param x
     * @return true if the element has been found and deleted, false if the node didn't exist in the splaytree
     */
    @Override
    public boolean remove(E x) {
        if(contains(x)){
            Node rootOfDisconnectedTree = root.rightChild;
            Node newRoot;

            if(root.leftChild == null){
                setRoot(root.rightChild);
            }else if(root.rightChild == null){
                setRoot(root.leftChild);
            }
            else{
                newRoot = root.leftChild;
                while(newRoot.rightChild != null){
                    newRoot = newRoot.rightChild;
                }
                splay(newRoot);
                root.setRightChild(rootOfDisconnectedTree);
            }
            size--;
            return true;
        }
        return false;
    }

    /**
     * Method to check wether the node we are searching for is in the splaytree
     *
     * @param x
     * @return true if node is found and then splays the node to the root - false if the node isnt found
     */
    @Override
    public boolean contains(E x) {
        Node tmp = new Node(x);
        tmp = findNode(tmp);
        if (tmp != null) {
            splay(tmp);
            return true;
        } else {
            return false;
        }
    }

    //************* HELP METHODS ******************
    private Node findNode(Node node) {

        Node tmp = root;
        Node nullNode = null;

        if(size == 0){
            return nullNode;
        }

        while (true) {
            if (node.getValue().compareTo(tmp.getValue()) < 0) {
                if (tmp.leftChild != null) {
                    tmp = tmp.leftChild;
                } else {
                    return nullNode;
                }
            } else if (node.getValue().compareTo(tmp.getValue()) > 0) {
                if (tmp.rightChild != null) {
                    tmp = tmp.rightChild;
                } else {
                    return nullNode;
                }
            } else if (node.getValue().equals(tmp.getValue())) {
                return tmp;
            } else {
                return nullNode;
            }
        }

    }

    private void splay(Node node) {
        if (node == root) {
            return;
        }
        if (node.parent == root) { //The node is at level 2
            if (node.isLeftChild()) {
                right(node);
            } else if(node.isRightChild()) {
                left(node);
            }
        } else {
            if (node.isLeftChild() && node.parent.isLeftChild()) {
                rightRight(node);
            } else if (node.isRightChild() && node.parent.isRightChild()) {
                leftLeft(node);
            } else if (node.isRightChild() && node.parent.isLeftChild()) {
                leftRight(node);
            } else if(node.isLeftChild() && node.parent.isRightChild()) {
                rightLeft(node);
            }
            splay(node);
        }
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;

        if (parent.parent == null) {
            setRoot(node);
        } else {
            node.parent = parent.parent;
            if (parent.isLeftChild()) {
                parent.parent.leftChild = node;
            } else {
                parent.parent.rightChild = node;
            }
        }
        parent.setLeftChild(node.rightChild);
        node.setRightChild(parent);
        return;
    }
    //Reverse of rotate right
    private void rotateLeft(Node node) {
        Node parent = node.parent;

        if (parent.parent == null) {
            setRoot(node);
        } else {
            node.parent = parent.parent;
            if (parent.isLeftChild()) {
                parent.parent.leftChild = node;
            } else {
                parent.parent.rightChild = node;
            }
        }
        parent.setRightChild(node.leftChild);
        node.setLeftChild(parent);
        return;
    }

    private void right(Node node) {
        rotateRight(node);
    }

    private void left(Node node) {
        rotateLeft(node);
    }

    private void leftLeft(Node node) {
        left(node.parent);
        left(node);
    }

    private void rightRight(Node node) {
        right(node.parent);
        right(node);
    }

    private void rightLeft(Node node) {
        right(node);
        left(node);
    }

    private void leftRight(Node node) {
        left(node);
        right(node);
    }

}