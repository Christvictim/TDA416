import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class TestSetCorrectness {
    public static void main(String[] args) {
        int n1 = 2, n2 = 1000, n3 = 500, n4 = 20;


        Random random = new Random();

        // how many resets?
        for (int i = 0; i < n2; i++) {
            SimpleSet<Integer> testSet = null;
            Set<Integer> javaSet = new HashSet<Integer>();

            if (n1 == 1) {
                testSet = new SortedLinkedListSet<Integer>();
            } else if (n1 == 2) {
                testSet = new SplayTreeSet<Integer>();
            } else {
                System.out.println("n1 must be either 1 or 2");
                return;
            }

            // how many operations?
            for (int j = 0; j < n3; j++) {
                int operation = random.nextInt(4);
                int value = random.nextInt(n4);
                switch (operation) {
                    case 0: // size
                        System.out.println("Operation: size");
                        if (testSet.size() != javaSet.size()) {
                            System.out.println("Error! size(): reset " + i + ", operation " + j);
                            return;
                        }
                        break;
                    case 1: // add
                        System.out.println("Operation: add(" + value + ")");
                        if (testSet.add(value) != javaSet.add(value)) {
                            System.out.println("Error! add(" + value + "): reset " + i + ", operation " + j);
                            return;
                        }
                        break;
                    case 2: // remove
                        System.out.println("Operation: remove(" + value + ")");
                        if (testSet.remove(value) != javaSet.remove(value)) {
                            System.out.println("Error! remove(" + value + "): reset " + i + ", operation " + j);
                            return;
                        }
                        break;
                    case 3: // contains
                        System.out.println("Operation: contains(" + value + ")");
                        if (testSet.contains(value) != javaSet.contains(value)) {
                            System.out.println("Error! contains(" + value + "): reset " + i + ", operation " + j);
                            return;
                        }
                        break;
                }
            }
        }

        System.out.println("All tests passed!");
    }
}
/*
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class TestSetCorrectness {

	public static void main (String[] args){
		int setToTest = 2;
		int nbrOfRestarts = 100;
		int nbrOfRandOps = 100;
		int nbrOfInts = 100;
		if(args.length == 4){
			int tmp = Integer.parseInt(args[0]);
			if(tmp == 1 || tmp == 2){
				setToTest = tmp;
			}else{
				System.out.print("First value not OK");
			}
			tmp = Integer.parseInt(args[1]);
			if(tmp > 0){
				nbrOfRestarts = tmp;
			}else{
				System.out.print("Second value not OK");
			}
			tmp = Integer.parseInt(args[2]);
			if(tmp > 0){
				nbrOfRandOps = tmp;
			}else{
				System.out.print("Third value not OK");
			}
			tmp = Integer.parseInt(args[3]);
			if(tmp > 0){
				nbrOfInts = tmp;
			}else{
				System.out.print("Fourth value not OK");
			}
		}
		Set<Integer> comparisonSet = new HashSet<Integer>();

		SimpleSet<Integer> set;

		Random newRand = new Random();

		for(int i = 0; i < nbrOfRestarts; i++){
			if(setToTest == 1){
				set = new SortedLinkedListSet<Integer>();
			}else{
				set = new SplayTreeSet<Integer>();
			}
			for(int j = 0; j < nbrOfRandOps; j++){
				int opToDo = newRand.nextInt(4) + 1;
				int x = newRand.nextInt(nbrOfInts);
				if(opToDo == 1){
					if(set.size() != comparisonSet.size()){
						System.out.println("Error in size test");
					}
				}else if(opToDo == 2){
					if(set.add(x) != comparisonSet.add(x)){
						System.out.println("Error in add test");
					}
				}
				else if(opToDo == 3){
					if(set.contains(x) != comparisonSet.contains(x)){
						System.out.println("Error in contains test");
					}
				}else{
					if(set.remove(x) != comparisonSet.remove(x)){
						System.out.println("Error in remove test");
					}
				}
			}
		}
		System.out.println("Test klart");
	}
}
 */