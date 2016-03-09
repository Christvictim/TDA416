import java.util.*;

public class DirectedGraph<E extends Edge> {

    //*************** Variables ****************
    private int nbrOfNodes;
    private List<E>[] nodeList; //List that will contain "LinkedList" that will contain edges :)

    //Dijkstranode inner class
    class DijkstraNode {
        private int value;
        private double distance;
        private List<E> path;

        public DijkstraNode(int value, double distance, List<E> path) {
            this.value = value;
            this.distance = distance;
            this.path = path;
        }

        public int getValue() {
            return this.value;
        }

        public double getDistance() {
            return this.distance;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != this.getClass()) {
                return false;
            }
            return false; //TODO eller något annat test?
        }

    }

    public DirectedGraph(int nbrOfNodes) {
        this.nbrOfNodes = nbrOfNodes;
        nodeList = (List<E>[]) new List[nbrOfNodes];
        for (int i = 0; i < nbrOfNodes; i++) {
            nodeList[i] = new LinkedList<E>();
        }
    }

    //Add edges into the list
    public void addEdge(E e) {
        nodeList[e.getSource()].add(e);
    }

    public Iterator<E> shortestPath(int from, int to) { //Basicly Dijkstras
        /*
        StartDijjkstra
            known = {1}; // known == S in book
                for all v in V-known // initialize
                    p(v) = 1
                    ssf(v) = cost(1,v); // ssf==d in book
                end loop;
                while V-known not empty
                    find the smallest ssf(w) ∀w in V-known
                    add w to known
                    remove w from V-known
                    for all v in EL(w) and in V-known
                        compare cost by way of w
                        ssf(v) = min[ ssf(v), ssf(w) + cost(w,v) ]
                        set p(v) to w if
                        ssf(w)+cost(w,v) < smallest
                    end loop
                end loop
        end dijkstra
        //all values in list is false to begin with
        boolean[] visisted = new boolean[nbrOfNodes];
        PriorityQueue<DijkstraNode> queue = new PriorityQueue<DijkstraNode>();
        queue.add(new DijkstraNode(from, 0, ))
        */
        return null;
    }

    public Iterator<E> minimumSpanningTree() {
        // Set up an array of spanning cc. In the beginning each element in
        // the array represents one node.
        List<E>[] cc = new List[this.nbrOfNodes];
        for (int i = 0; i < cc.length; i++) {
            cc[i] = new LinkedList<E>();
        }

        //Lägg in alla bågar i en prioritetskö
        PriorityQueue<E> queue = new PriorityQueue<E>(nbrOfNodes, new CompKruskalEdge());
        //lägg till edges i queue
        for (int j = 0; j < nodeList.length; j++) {
            for (int k = 0; k < nodeList[j].size(); k++) {
                queue.add(nodeList[j].get(k));
            }
        }

        //Så länge pq, ej är tom && |cc| < n
        while (!(queue.isEmpty()) && (cc[0].size() < this.nbrOfNodes)) {
            // Remove the edge with the least weight from the queue
            E edge = queue.poll();

            //hämta e = (from, to) från kön
            int from = edge.from;
            int to = edge.to;

            //om from och to i listan inte är samma lista
            if (cc[from] != cc[to]) {
                merge((List<E>[]) cc, from, to);
                //lägg in den borttagna edgen
                cc[to].add(edge);
            }
        }

        return cc[0].iterator();
    }

    // *********** Help classes **********
    private void merge(List<E>[] cc, int from, int to) {
        System.out.println("debug1");
        //få för alla edges i cc[from] att peka på cc[to]
        if (cc[from].size() < cc[to].size()) {
            System.out.println("debug2");
            for (E e : cc[from]) {
                cc[to].add(e);
                cc[e.from] = cc[e.to] = cc[to] = cc[to];
            }
            cc[from] = cc[to];
        }
        //få för alla edges i cc[to] att peka på cc[from]
        else {
            System.out.println("debug3");
            for (E e : cc[to]) {
                cc[from].add(e);
                cc[e.from] = cc[e.to] = cc[from];
            }
            cc[to] = cc[from];
        }
    }


    //********* Comparator classes **********
    class CompKruskalEdge implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.getWeight() < o2.getWeight()) {
                return -1;
            } else if (o1.getWeight() > o2.getWeight()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    //Ska compara distances looool :)
    class CompDijkstraPath implements Comparator<Edge> {

        //Compare distances between edges
        @Override
        public int compare(Edge o1, Edge o2) {
            return 0;
        }
    }
}
  
