
import java.util.Comparator;

/**
 * Created by Morpheus on 2016-03-07.
 */
public class CompKruskalEdge implements Comparator<Edge> {

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
