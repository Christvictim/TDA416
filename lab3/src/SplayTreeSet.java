public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {
    class Node {
        private E value; //its value
        protected Node parent; //this nodes parent
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

        //not used yet lol
        public boolean isRightChild() {
            return ((this.parent != null) && (this.parent.rightChild == this));
        }
    }

    //*********** Variables *************
    private int size; //antalet noder i trädet
    private Node root = null;

    //***********************************

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E x) {
        if (root == null) {
            root = new Node(x);
            size++;
            return true;
        }

        // "A light in the darkness"
        Node tmp = root;
        Node toAdd = new Node(x);

        while (true) { //Stay true! While-loop implementation - Obama approves!
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

    private void removeHelper() {
        return;
    }

    @Override
    public boolean remove(E x) {
        Node nodeToRemove = new Node(x);
        nodeToRemove = findNode(nodeToRemove);
        Node tmp;
        if (nodeToRemove != null) {

            if (nodeToRemove.leftChild != null) {


            } else if (nodeToRemove.rightChild != null) {

            } else {
                if (nodeToRemove.isLeftChild()) {
                    nodeToRemove.parent.leftChild = null;
                    nodeToRemove.parent = null;
                    nodeToRemove = nodeToRemove.parent;
                } else {
                    nodeToRemove.parent.rightChild = null;
                    nodeToRemove.parent = null;
                    nodeToRemove = nodeToRemove.parent;
                }
            }
            splay(nodeToRemove);
            return true;
        } else {
            return false;
        }
    }

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

    public Node findNode(Node node) {
        Node tmp = root;
        Node nullNode = null;
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

    public void splay(Node node) {
        while (node != root) {
            //see if there exists a grandparent
            if (node.parent != root) {
                //parent is leftchild
                if (node.parent.isLeftChild()) {
                    //left - left
                    if (node.isLeftChild()) {
                        lZigZig(node);
                    }
                    //left - right
                    else {
                        lZigZag(node);
                    }
                }
                //parent is a rightchild
                else {
                    //right - left
                    if (node.isLeftChild()) {
                        rZigZag(node);
                    }
                    //right - right
                    else {
                        rZigZig(node);
                    }
                }
            }
            //hand the left and right cases
            else {
                //left
                if (node.isLeftChild()) {
                    rotateLeft(node);
                }
                //right
                else {
                    rotateRight(node);
                }
            }

        }
        return;
    }

    public void rotateRight(Node node) {
        //Does it exist a rightchild??
        if (node.rightChild != null) {
            //Set this node's parent to be parent of this nodes rightchild
            //(node and node.rightchild has same parent now)
            node.rightChild.parent = node.parent;
        }
        //Set the leftchild of our parent to now point at nodes rightchild
        node.parent.leftChild = node.rightChild;

        //Set nodes rightchild to be the parent
        node.rightChild = node.parent;

        //ifall detta är sista rotationen ska rooten nu sättas till noden (ska alltid ske vid en splay)
        if (node.parent.parent == null) {
            root = node;
        } else {
            if (node.parent.isLeftChild()) {
                node.parent.parent.leftChild = node;
            } else {
                node.parent.parent.rightChild = node;
            }
            //sätter nodens grandparent till min parent
            node.parent = node.parent.parent;
        }

        //Set the new rightchild, who was my parent to have node as parent
        node.rightChild.parent = node;
        return;
    }

    public void rotateLeft(Node node) {
        // Does it exist a leftchild??
        if (node.leftChild != null) {
            //Same behavior as rotateright but reverse
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
            node.parent = node.parent.parent;
        }
        node.leftChild.parent = node;
        return;
    }

    //node vänsterbarn, förälder högerbarn
    public void rZigZag(Node node) {
        rotateRight(node);
        rotateLeft(node);
        return;
    }

    //node högerbarn, förälder vänsterbarn
    public void lZigZag(Node node) {
        rotateLeft(node);
        rotateRight(node);
        return;
    }

    //node högerbarn, förälder högerbarn
    public void rZigZig(Node node) {
        rotateRight(node.parent);
        rotateRight(node);
        return;
    }

    //node vänsterbarn, förälder vänsterbarn
    public void lZigZig(Node node) {
        rotateLeft(node.parent);
        rotateLeft(node);
        return;
    }
}