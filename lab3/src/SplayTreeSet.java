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
            return this.parent != null && this.parent.leftChild == this;
        }

        //not used yet lol
        public boolean isRightChild() {
            return this.parent != null && this.parent.rightChild == this;
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

    @Override
    public boolean remove(E x) {
        Node tmp = new Node(x);
        tmp = findNode(tmp);
        if(tmp != null){

            //Do some crap
            splay(tmp);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean contains(E x) {
        Node tmp = new Node(x);
        tmp = findNode(tmp);
        if(tmp != null){
            splay(tmp);
            return true;
        }else {
            return false;
        }
    }

    public Node findNode(Node node){
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
            } else if(node.getValue().equals(tmp.getValue())){
                return tmp;
            }else{
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
            //hand the lZig and rZig cases
            else {
                //left
                if (node.isLeftChild()){
                    lZig(node);
                }
                //right
                else {
                    rZig(node);
                }
            }

        }
        return;
    }

    //left zig
    public void rZig(Node node) {
        return;
    }
    //right zig
    public void lZig(Node node) {
        return;
    }

    public void rZigZag(Node node) { return; }

    public void lZigZag(Node node) { return; }

    public void rZigZig(Node node) {
        return;
    }

    public void lZigZig(Node node) {
        return;
    }
}