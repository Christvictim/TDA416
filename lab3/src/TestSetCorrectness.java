import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public class TestSetCorrectness {
    public static void main (String[] args){
        int setToTest = 2;
        int nbrOfRestarts = 1000;
        int nbrOfRandOps = 1000;
        int nbrOfInts = 50;
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
        SimpleSet<Integer> set;
        Random newRand = new Random();
        for(int i = 0; i < nbrOfRestarts; i++){
            Set<Integer> comparisonSet = new HashSet<Integer>();

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