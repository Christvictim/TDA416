/**
 * Created by Morpheus on 2016-03-07.
 */
public class CompDijkstraPath {
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
}
