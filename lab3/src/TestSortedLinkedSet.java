public class TestSortedLinkedSet {
    public static void main(String[] args) {
        SimpleSet<Integer> set = new SortedLinkedListSet<Integer>();
        System.out.println("************** ADD ***************");
        set.add(4);
        set.add(1);
        set.add(2);
        set.add(25);
        set.add(12);
        set.add(23);
        set.add(32);
        set.add(25);
        set.add(3);
        set.add(13);
        System.out.println(set.toString());

        System.out.println("");
        System.out.println("************** CONTAINS and SIZE ***************");
        System.out.println("True if the set contains 13: " + set.contains(13));
        System.out.println("False because the set doesnt contain 101: " + set.contains(101));
        System.out.println("The size of the set is: " + set.size());

        System.out.println("");
        System.out.println("************** REMOVE ***************");
        System.out.println(set.toString());
        set.remove(98);
        set.remove(12);
        set.remove(25);
        System.out.println(set.toString());

        System.out.println("The size of the set is: " + set.size());

        System.out.println("");
        System.out.println("************** REMOVE ALL ***************");
        set.remove(4);
        set.remove(1);
        set.remove(2);
        set.remove(25);
        set.remove(12);
        set.remove(23);
        set.remove(32);
        set.remove(25);
        set.remove(3);
        set.remove(13);

        System.out.println("The size of the set is: " + set.size());
    }
}
