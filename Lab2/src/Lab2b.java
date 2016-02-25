import java.util.Comparator;
import java.util.PriorityQueue;

public class Lab2b {

    public static double[] simplifyShape(double[] poly, int k) {
        DLList<DataElement> nodeList = new DLList<DataElement>();

        PriorityQueue<DLList<DataElement>.Node> queue = new PriorityQueue<>((poly.length - 4) / 2, new DataElementComparator());

        //add first last element to list
        nodeList.addFirst(new DataElement(poly[0], poly[1]));
        nodeList.addLast(new DataElement(poly[2], poly[3]));

        //add the rest of the element to the list and all (except the first and last) to the queue
        for (int i = 4; i < poly.length; i += 2) {
            nodeList.addLast(new DataElement(poly[i], poly[i + 1]));
            queue.add(nodeList.last.prev);
        }

        //while the queue size +2 is bigger than k we remove elements from the list and queue until we have the amount of elements we want
        while (queue.size() + 2 > k) {
            DLList<DataElement>.Node removedNode = queue.remove();
            DLList<DataElement>.Node prevNode = removedNode.prev;
            DLList<DataElement>.Node nextNode = removedNode.next;

            nodeList.remove(removedNode);

            if (prevNode.prev != null) {
                queue.remove(prevNode);
                queue.add(prevNode);
            }
            if (nextNode.next != null) {
                queue.remove(nextNode);
                queue.add(nextNode);
            }
        }
        double[] arr = new double[k * 2];
        DLList<DataElement>.Node tmpNod = nodeList.first;

        //adds the element to the array we want to return
        for (int i = 0; i < k * 2; i += 2) {
            arr[i] = tmpNod.elt.x;
            arr[i + 1] = tmpNod.elt.y;
            tmpNod = tmpNod.next;
        }
        return arr;
    }
}

//Class we use to compare the priority between two nodes p1 and p2
class DataElementComparator implements Comparator<DLList<DataElement>.Node> {
    @Override
    public int compare(DLList<DataElement>.Node p1, DLList<DataElement>.Node p2) {
        return Double.compare(p1.getValue().dataPriority(p1.prev.getValue(), p1.next.getValue()),
                p2.getValue().dataPriority(p2.prev.getValue(), p2.next.getValue()));
    }
}

class DataElement {
    double x;
    double y;

    public DataElement(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    //calculates the priority of a node with respect of its pre and next nodes
    public double dataPriority(DataElement pre, DataElement next) {
        if (pre == null || next == null) {
            throw new IllegalArgumentException("Null values on pre or next nodes");

        }
        //calculate all the x-distances in our "triangle" of elements.
        double thisPreX = Math.abs(this.getX() - pre.getX());
        double preNextX = Math.abs(pre.getX() - next.getX());
        double thisNextX = Math.abs(this.getX() - next.getX());

        //calculate all the y-distances in our "triangle" of elements.
        double thisPreY = Math.abs(this.getY() - pre.getY());
        double preNextY = Math.abs(pre.getY() - next.getY());
        double thisNextY = Math.abs(this.getY() - next.getY());

        //calculate all our points correct distances between this element, pre and next element
        double thisPre = Math.hypot(thisPreX, thisPreY);
        double preNext = Math.hypot(preNextX, preNextY);
        double thisNext = Math.hypot(thisNextX, thisNextY);

        return thisPre + thisNext - preNext;
    }
}
