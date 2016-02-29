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

        Node tmp = root;
        Node toAdd = new Node(x);


        if (this.root == null) {
            System.out.println("Adding: " + toAdd.getValue());
            setRoot(toAdd);
            this.root = toAdd;
            size++;
            return true;
        }

        //System out prints is only for us to check where the program is while testing the method
        while (toAdd.rightChild == null || toAdd.leftChild == null) {
            if (toAdd.getValue().compareTo(tmp.getValue()) < 0) {
                if (tmp.leftChild != null) {
                    System.out.println("Continuing search down the leftchild");
                    tmp = tmp.leftChild;
                } else {
                    System.out.println("Adding: " + toAdd.getValue());
                    tmp.leftChild = toAdd;
                    toAdd.parent = tmp;
                    size++;
                    splay(toAdd);
                    return true;
                }
            } else if (toAdd.getValue().compareTo(tmp.getValue()) > 0) {
                if (tmp.rightChild != null) {
                    System.out.println("Continuing search down the rightchild");
                    tmp = tmp.rightChild;
                } else {
                    System.out.println("Adding: " + toAdd.getValue());
                    tmp.rightChild = toAdd;
                    toAdd.parent = tmp;
                    size++;
                    splay(toAdd);
                    return true;
                }
            } else {
                System.out.println("Probably trying to add the same value");
                return false;
            }
        }
        System.out.println("Came to last return, the element wasnt added and something must have gone wrong");
        return false;
    }

    @Override
    public boolean remove(E x) {
        Node nodeToRemove = new Node(x);
        nodeToRemove = findNode(nodeToRemove);
        Node tmp;
        Node nodeToSplay;
        if (nodeToRemove != null) {
            if (nodeToRemove.leftChild != null) {
                tmp = nodeToRemove.leftChild;
                while (tmp.rightChild != null) {
                    tmp = tmp.rightChild;
                }
                nodeToRemove.value = tmp.value;
                nodeToRemove = tmp;
                if (nodeToRemove.leftChild != null) {
                    nodeToRemove.leftChild.parent = nodeToRemove.parent;
                    nodeToRemove.parent.leftChild = nodeToRemove.leftChild;
                }

            } else if (nodeToRemove.rightChild != null) {
                tmp = nodeToRemove.rightChild;
                while (tmp.leftChild != null) {
                    tmp = tmp.leftChild;
                }
                nodeToRemove.value = tmp.value;
                nodeToRemove = tmp;
                if (nodeToRemove.rightChild != null) {
                    nodeToRemove.rightChild.parent = nodeToRemove.parent;
                    nodeToRemove.parent.leftChild = nodeToRemove.rightChild;
                }
            }
            if (nodeToRemove.isLeftChild()) {
                nodeToRemove.parent.leftChild = null;
                nodeToRemove.parent = null;
                nodeToSplay = nodeToRemove.parent;
            } else {
                nodeToRemove.parent.rightChild = null;
                nodeToRemove.parent = null;
                nodeToSplay = nodeToRemove.parent;
            }

            splay(nodeToSplay);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(E x) {
        System.out.println("Contains");
        Node nodeToFind = new Node(x);
        if (findNode(nodeToFind) != null) {
            System.out.println("Contains - if - return true");
            splay(nodeToFind);
            return true;
        }
        System.out.println("Contains - return false");
        return false;
    }

    private Node findNode(Node node) {
        System.out.println("FindNode");
        //startnode to check
        Node tmp = root;

        //while we dont encounter a null we keep looking, otherwise - jump out and return null
        while (tmp != null) {
            System.out.println("FindNode - while");
            //if they are the same, return tmp so that contains can splay it and return true
            if (tmp.getValue().compareTo(node.getValue()) == 0) {
                System.out.println("FindNode - if - return node");
                return tmp;
            } else if (tmp.getValue().compareTo(node.getValue()) < 0) {
                System.out.println("FindNode - else if");
                tmp = tmp.leftChild;
            } else {
                System.out.println("FindNode - else");
                tmp = tmp.rightChild;
            }
        }
        System.out.println("FindNode - return null");
        return null;
    }

    private void splay(Node node) {
        System.out.println("Splay");
        if (node == root) { //We have splayed the node to the root, we are done.
            System.out.println("Splay - if root == null - return");
            return;
        }

        if (node.parent == root) { //The node is at level 2
            System.out.println("Splay - if");
            //depending on what child it is, perform zig(right rotation) or zag(left rotation)
            if (node.isLeftChild()) {
                System.out.println("Splay - Zig");
                zig(node);
            } else if (node.isRightChild()) {
                System.out.println("Splay - Zag");
                zag(node);
            }
        } else {
            if (node.isLeftChild() && node.parent.isLeftChild()) {
                System.out.println("Splay - ZigZig");
                zigzig(node);
            } else if (node.isRightChild() && node.parent.isRightChild()) {
                System.out.println("Splay - ZagZag");
                zagzag(node);
            } else if (node.isRightChild() && node.parent.isLeftChild()) {
                System.out.println("Splay - ZigZag");
                zigzag(node);
            } else if (node.isLeftChild() && node.parent.isRightChild()) {
                System.out.println("Splay - ZagZig");
                zagzig(node);
            }
        }
    }

    //Lots of Syso, for us to test where the program is while running
    private void rotateRight(Node node) {
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
        //System.out.println("Before rotateright - return");
        return;
    }

    private void rotateLeft(Node node) {
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

    private void zig(Node node) {
        this.rotateRight(node);
    }

    private void zag(Node node) {
        this.rotateLeft(node);
    }

    private void zigzig(Node node) {
        zig(node.parent);
        zig(node);
    }

    private void zagzag(Node node) {
        zag(node.parent);
        zag(node);
    }

    private void zigzag(Node node) {
        zag(node);
        zig(node);
    }

    private void zagzig(Node node) {
        zig(node);
        zag(node);
    }

    //Method to setRoot when needed
    private void setRoot(Node node) {
        this.root = node;
        if (node != null) {
            node.parent = null;
        }
    }
}