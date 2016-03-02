import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestSetCorrectness {

    public static void main (String[] args){
        int setToTest = 0;
        int nbrOfRestarts = 0;
        int nbrOfRandOps = 0;
        int nbrOfInts = 0;

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
        SimpleSet<Integer> set = null;

        Random newRand = new Random();

        for(int i = 0; i < nbrOfRestarts; i++){
            Set<Integer> comparisonSet = new HashSet<Integer>();

            if(setToTest == 1){
                set = new SortedLinkedListSet<Integer>();
            }else{
                set = new SplayTreeSet<Integer>();
            }
            int previousOp = -1;
            for(int j = 0; j < nbrOfRandOps; j++){
                int opToDo = newRand.nextInt(4);
                int x = newRand.nextInt(nbrOfInts);
                switch(opToDo){

                    case 0:
                        if(set.size() != comparisonSet.size()){
                            System.out.println("accident in size test, previous OP was " + previousOp);
                            return;
                        }
                        previousOp = 0;
                        break;
                    case 1:
                        if(set.add(x) != comparisonSet.add(x)){
                            System.out.println("accident in add test, previous OP was " + previousOp);
                            return;
                        }
                        previousOp = 1;
                        break;
                    case 2:
                        if(set.contains(x) != comparisonSet.contains(x)){
                            System.out.println("accident in contains test, previous OP was " + previousOp);
                            return;
                        }
                        previousOp = 2;
                        break;
                    case 3:
                        if(set.remove(x) != comparisonSet.remove(x)){
                            System.out.println("accident in remove test, previous OP was " + previousOp);
                            return;
                        }
                        previousOp = 3;
                        break;
                }
            }
        }
        System.out.println("Test klart");
    }
}