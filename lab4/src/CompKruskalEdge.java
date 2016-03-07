/**
 * Created by Morpheus on 2016-03-07.
 */
public class CompKruskalEdge {
    /*
    pseudo kruskal(G: graph(V, E)) return set of edges
        0 cc: MFset // a Merge-Find set
        mst: set = ∅ //the growing spanning tree
        u, v: nodes
        nbrOfCc: int = n
        insert all edges in a priority queue
        while nbrOfCc > 1 loop
            (u,v) = deletemin(edges)
            ucomp = find(u, cc), vcomp = find(v, cc)
            if ucomp ≠ vcomp then
                merge(ucomp, vcomp, cc)
                nbrOfCc = nbrOfCc - 1
                add (u, v) to mst
            end if
        end while
        return mst
    end kruskal
    */
}
