import java.util.*;

public class DirectedGraph<E extends Edge> {

    //*************** Variables ****************
    private int nbrOfNodes;
    private List<E>[] nodeList; //lista som kommer innehålla "noder" som listor. innehåller edges senare

    //Dijkstranode inner class
    class DijkstraNode {
        protected int value;
        protected double distance;
        protected List<E> path;

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

        public List<E> getPath() {
            return path;
        }

    }
    private class CompDijkstraPath implements Comparator<DijkstraNode>{
        @Override
        public int compare(DijkstraNode o1, DijkstraNode o2) {
            if (o1.getDistance() < o2.getDistance()) {
                return -1;
            } else if (o1.getDistance() > o2.getDistance()) {
                return 1;
            } else {
                return 0;
            }
        }
    }


    public DirectedGraph(int nbrOfNodes) {
        this.nbrOfNodes = nbrOfNodes;
        nodeList = (List<E>[]) new List[nbrOfNodes];
        for (int i = 0; i < nbrOfNodes; i++) {
            nodeList[i] = new LinkedList<E>();
        }
    }

    //Lägg till alla edges i listorna i listan
    public void addEdge(E e) {
        nodeList[e.from].add(e);
    }

    /**
     * Computes the shortest path between to vertixes ("nodes")
     *
     * @param from the source vertix
     * @param to the vertix we want to find the shortest path to
     * @return an iterator of the path
     */
    public Iterator<E> shortestPath(int from, int to) { //Basicly Dijkstras
        PriorityQueue<DijkstraNode> queue = new PriorityQueue<DijkstraNode>(new CompDijkstraPath()); //Our path queue
        boolean[] visited = new boolean[nbrOfNodes];    //our array that keeps track of visited nodes,
                                                        // doesnt have to be filled with false values since then initilaized it is allready filled with false values
        DijkstraNode source = new DijkstraNode(from, 0, new LinkedList<E>()); //source node with a new list as path
        queue.add(source); //add the source to the queue

        while (!(queue.isEmpty())){
            //hämta första i queuen och tag bort den
            DijkstraNode node = queue.poll();
            while(!(visited[node.getValue()])){
                if (node.getValue() == to){ //ifall vi kommer till den noden vi ska till, så returna nodens path.iterator
                    return node.getPath().iterator();
                }
                visited[node.getValue()] = true;
                //hämta in de edges ifrån som grannoder har mellan den noden vi är på just nu och lägg in dem i queuen
                for(Edge e : nodeList[node.getValue()]){
                    //lägg in den pathen vi har hittils i "path"
                    LinkedList<E> path = new LinkedList<E>(node.getPath());
                    //lägg till edgen
                    path.add((E) e);
                    //skapar en new nod med pathen och lägger in den i queuen
                    DijkstraNode tmp = new DijkstraNode(e.getDest(), node.getDistance(), path);
                    queue.add(tmp);
                }
            }
        }
        return null;
    }

    /**
     * Computes the mst of a graphs
     *
     * @return an iterator of the mst
     */
    public Iterator<E> minimumSpanningTree() {
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

            //spara edgesens from och to i variabler
            int from = edge.from;
            int to = edge.to;

            //om from och to i listan inte är samma lista
            if (cc[from] != cc[to]) {
                //få för alla edges i cc[from] att peka på cc[to]
                if (cc[from].size() < cc[to].size()) {
                    for (E e : cc[from]) {
                        cc[to].add(e);
                        cc[e.from] = cc[e.to] = cc[to];
                    }
                    cc[from] = cc[to];
                }
                //få för alla edges i cc[to] att peka på cc[from]
                else {
                    for (E e : cc[to]) {
                        cc[from].add(e);
                        cc[e.from] = cc[e.to] = cc[from];
                    }
                    cc[to] = cc[from];
                }
                //lägga till sista noden. to och nod pekar på samma så spelar
                //ingen roll om det är to eller from i bracketsen
                cc[to].add(edge);
            }
        }
        return cc[0].iterator();
    }
}
  
