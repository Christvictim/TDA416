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
    }


    //*********** VARIABLES *************
    private int size = 0;
    public Node root = null;

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

        //declare temporary variables used to locating where we are in the tree while searching downwards
        Node tmp = root;
        Node toAdd = new Node(x);
        Node test = findNode(toAdd);
        if(test != null){
            return false;
        }

        //first element
        if (this.root == null) {
            root = toAdd;
            size++;
            return true;
        }

        //locates where to put the added node and splays it up to the root
        while(true){
            if (toAdd.getValue().compareTo(tmp.getValue()) < 0) {
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
            } else {
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
        if(contains(x) && size > 1){
            Node rootOfDisconnectedTree = null;
            Node newRoot;

            // If left subtree exists, save the right subtree with a new root
            // The lefts subtrees largest number is splayed to the top and
            // then we "ignore" the node we want to remove that contains the value x
            if(root.leftChild != null){
                rootOfDisconnectedTree = root.rightChild;
                newRoot = root.leftChild;
                while(newRoot.rightChild != null){
                    newRoot = newRoot.rightChild;
                }
                splay(newRoot);
                root.rightChild = rootOfDisconnectedTree;
            }
            // Mirror the if above
            else if(root.rightChild != null){
                rootOfDisconnectedTree = root.leftChild;
                newRoot = root.rightChild;
                while(newRoot.leftChild != null){
                    newRoot = newRoot.leftChild;
                }
                splay(newRoot);
                root.leftChild = rootOfDisconnectedTree;
            }
            // if the disconnected subtree exist, it is connected to the new root.
            if(rootOfDisconnectedTree != null){
                rootOfDisconnectedTree.parent = root;
            }
            size--;
            return true;
        }
        if(contains(x) && size == 1){
            root = null;
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

        while (true) { //Stay true!
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
        }
    }

    private void rotateRight(Node node) {
        if (node.rightChild != null) {
            node.rightChild.parent = node.parent;
        }
        node.parent.leftChild = node.rightChild;
        node.rightChild = node.parent;

        if (node.parent.parent == null) {
            root = node;
        } else {
            if (node.parent.isLeftChild()) {
                node.parent.parent.leftChild = node;
            } else {
                node.parent.parent.rightChild = node;
            }
        }
        node.parent = node.parent.parent;
        node.rightChild.parent = node;
        return;
    }
    //Reverse of rotate right
    private void rotateLeft(Node node) {
        if (node.leftChild != null) {
            node.leftChild.parent = node.parent;
        }
        node.parent.rightChild = node.leftChild;
        node.leftChild = node.parent;
        if (node.parent.parent == null) {
            root = node;
        } else {
            if (node.parent.isLeftChild()) {
                node.parent.parent.leftChild = node;
            } else {
                node.parent.parent.rightChild = node;
            }
        }
        node.parent = node.parent.parent;
        node.leftChild.parent = node;
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