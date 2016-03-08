
import java.util.*;

public class DirectedGraph<E extends Edge> {

    //*************** Variables ****************
    private int nbrOfNodes;
    private List<E> edgeList = new ArrayList<E>();
    /*
	TODO: Allt ska göras här i den här klassen!
	Fixa de fel som påstås i NodeTable.java
	 */

    public DirectedGraph(int nbrOfNodes) {
        ;
    }

    public void addEdge(E e) {
        ;
    }

    /*
    pseudocode dijkstra(G: graph (V,E))
        ssf : array(1..n) of shortest paths so far
        v, w, u: nodes
        S = {1} // the start node
        while S ≠ V loop
            choose the edge (u, w) with minimum cost s.t. u∈S and w∈V-S
            add (w) to S (and remove from V-S)
            for all v on EL(w) loop
                if it is shorter to go by way of v
                    update ssf(v) and p(v)
        end loop
    end dijkstra
    */
    public Iterator<E> shortestPath(int from, int to) {
        return null;
    }

    /*
    pseudo kruskal(G: graph(V, E)) return set of edges
        0 cc: MFset // a Merge-Find set
        mst: set = ∅ //the growing spanning tree
        u, v: nodes
        nbrOfCc: int = n
        insert all edges in a priority queue
        while nbrOfCc > 1 loop
            (u,v) = deletemin(edges)
            ucomp = find(u, cc),
            vcomp = find(v, cc)
            if ucomp ≠ vcomp then
                merge(ucomp, vcomp, cc)
                nbrOfCc = nbrOfCc - 1
                add (u, v) to mst
            end if
        end while
        return mst
    end kruskal
    */
    public Iterator<E> minimumSpanningTree() {
        return null;
    }

}
  
