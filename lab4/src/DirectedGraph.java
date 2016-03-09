import java.util.*;

public class DirectedGraph<E extends Edge> {

    //*************** Variables ****************
    private int nbrOfNodes;
    private List<E>[] nodeList; //List that will contain "LinkedList" that will contain edges :)

    //Dijkstranode inner class
    class DijkstraNode {
        private int value;

        public DijkstraNode(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null){
                return false;
            }
            if (obj == this) {
                return true;
            }
            if(obj.getClass() != this.getClass()){
                return false;
            }
            return false; //TODO eller något annat test?
        }

    }

    public DirectedGraph(int nbrOfNodes) {
        this.nbrOfNodes = nbrOfNodes;
        for (int i = 0; i < nbrOfNodes; i++) {
            this.nodeList[i] = new LinkedList<E>();
        }
    }

    //Add edges into the linkedlists inside edgeList
    public void addEdge(E e) {
        nodeList[e.getSource()].add(e);
    }

    public Iterator<E> shortestPath(int from, int to) { //Basicly Dijkstras
        return null;
    }

    public Iterator<E> minimumSpanningTree() { //Basicly Kruskal
        //skapa ett fält cc som för varje nod
        //innehåller en egen tom lista
        List<E>[] mst = new List[this.nbrOfNodes];
        for (int i = 0; i < cc.length; i++) {
            cc[i] = new LinkedList<E>();
        }

        //Lägg in alla bågar i en prioritetskö
        PriorityQueue<E> queue = new PriorityQueue<E>(nbrOfNodes, new CompKruskalEdge());
        //add edges to queue
        for (int j = 0; j < nodeList.length; j++) {
            for (int k = 0; k < nodeList[j].size(); k++) {
                queue.add(nodeList[j].get(k));
            }
        }

        while (!(queue.isEmpty()) && mst.length < nbrOfNodes) {
            Edge edge = queue.poll(); // funkar det för att hämta

            //hämta e = (from, to, weight) från kön
            int from = edge.from;
            int to = edge.to;

            //om from och to i listan inte är samma lista
            if (nodeList[from] != nodeList[to]) {
                //flytta över alla elementen från den
                //kortare listan till den andra och se till
                //att alla berörda noder i cc refererar
                //till den påfyllda listan

                //from < to
                if (nodeList[from].size() < nodeList[to].size()) {
                    nodeList[to].add((E) edge);
                    for (int s = 0; s < nodeList[from].size(); s++) {
                        E e = nodeList[from].get(s);
                        nodeList[to].add(e);
                        nodeList[e.getSource()] = nodeList[to];
                        nodeList[e.getDest()] = nodeList[to];
                    }
                }
                //to < from
                else {
                    nodeList[from].add((E) edge);
                    for (int s = 0; s < nodeList[to].size(); s++) {
                        E e = nodeList[to].get(s);
                        nodeList[from].add(e);
                        nodeList[e.getSource()] = nodeList[from];
                        nodeList[e.getDest()] = nodeList[from];

                    }
                }
            }

            //lägg slutligen e i den påfyllda listan
        }
        return mst ;
    }
    //********* Halp classus if needed **********


    //********* Comparator classes **********
    private class CompKruskalEdge implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.getWeight() < o2.getWeight()) {
                return -1;
            } else if (o1.getWeight() > o2.getWeight()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private class CompDijkstraPath implements Comparator<Edge> {

        //Compare distances between edges
        @Override
        public int compare(Edge o1, Edge o2) {
            return 0;
        }
    }
}
  
