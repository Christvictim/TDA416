/**
 * Created by Kristoffer Bader and Viktor Engström on 2016-01-24.
 */
public class MySqrt {

    /**
     * Error messege is x is below 0
     * Then checks which intervall x is in and calculates the root
     *
     * @param x
     * @param epsilon
     * @return the root of x
     */
    public static double mySqrtLoop(double x, double epsilon){
        if (x < 0){
            return Double.NaN;
        }
        double ymitt;
        double ymax;
        double ymin;

        //intervall 0-1
        if ((x >= 0) && (x <= 1)) {
            ymitt = 0.5;
            //vårt intervall
            ymin = 0;
            ymax = 1;
        } else {
            ymitt = (x+1)/2;
            ymin = 1;
            ymax = x;
        }

        //Modifierar ymitt tills dess att vi kommer såpass när epsilon att det är godkänt
        while((Math.abs(Math.pow(ymitt,2) - x) > epsilon)){

            // om ymitt^2 < x så måste vi ändra vårt intervall och sätter ymin till ymitt. Sedan gör vi om
            // så att ymitt hamnar mellan detta nya intervall
            if (Math.pow(ymitt,2) < x){
                ymin = ymitt;
                ymitt = (ymin + ymax)/2;

                // om ymitt^2 < x så måste vi ändra vårt intervall och sätter ymax till ymitt. Sedan gör vi om
                // så att ymitt hamnar mellan detta nya intervall
            } else {
                ymax = ymitt;
                ymitt = (ymin + ymax)/2;
            }
        }
        return ymitt;
    }

    /**
     * Prints out error-messege "Double.NaN" if x is below 0.
     * Recursevly finds the root between 0-1 if x is between intervall 0-1
     * Recursevly finds the root between 1-x if x is between intervall 1-x
     *
     * @param x
     * @param epsilon
     * @return the root of x unless x is below 0
     */
    public static double mySqrtRecurse(double x, double epsilon){
        if (x < 0){
            return Double.NaN;
        }
        else if ((x >= 0) && (x <= 1)) {
            return helpRecurse(x, epsilon, 0, 1);
        }
        else {
            return helpRecurse(x, epsilon, 1, x);
        }
    }

    //ymax som den får in är det x vi har ifrån början. senare i rekursionen
    //kommer den få ett nytt max eller min beroende på vad y^2 - x ger
    private static double helpRecurse(double x, double epsilon, double ymin, double ymax){
        double ymitt = (ymin + ymax)/2;

        // kolla ifall |ymitt^2 - x| < epsilon. ifall detta stämmer. returna ymitt för det är så nära
        // rooten vi kommer om vi utgår från felmarginalen
        if ( Math.abs(Math.pow(ymitt,2) - x) < epsilon){
            return ymitt;
        }

        // om ymitt^2 > x så måste vi ändra vårt intervall och sätter ymin till ymitt.
        // det vi vill göra är leta på högre tal i vårt intervall
        if (Math.pow(ymitt,2) > x){
            return helpRecurse(x, epsilon, ymin, ymitt);
        }

        // om ymitt^2 < x så måste vi ändra vårt intervall och sätter ymax till ymitt.
        // det vi vill gära är leta på lägre tal i vårt intervall
        else {
            return helpRecurse(x, epsilon, ymitt, ymax);
        }
    }
    /*
    public static void main(String[] args){
        System.out.println("----------------- TEST REKURSIV-METOD -----------------");
        System.out.println("Felmarginalen är 10^-6");
        System.out.println("FÖRSTA testet med roten ur -1. Svaret ska bli NaN");
        System.out.println("Svaret blev: " + mySqrtRecurse(-1,Math.pow(10,-6)));
        System.out.println();
        System.out.println("ANDRA testet med roten ur 0.");
        System.out.println("Svaret blev: " + mySqrtRecurse(0,Math.pow(10,-6)));
        System.out.println();
        System.out.println("TREDJE testet med roten ur 1.");
        System.out.println("Svaret blev: " + mySqrtRecurse(1,Math.pow(10,-6)));
        System.out.println();
        System.out.println("FJÄRDE och FEMTE testet med roten ur 42 och 1337.");
        System.out.println("Roten ur 42. Svaret blev: " + mySqrtRecurse(42,Math.pow(10,-6)));
        System.out.println("Roten ur 1337. Svaret blev: " + mySqrtRecurse(1337,Math.pow(10,-6)));

        System.out.println();

        System.out.println("----------------- TEST LOOP-METOD -----------------");
        System.out.println("Felmarginalen är 10^-6");
        System.out.println("FÖRSTA testet med roten ur -1. Svaret ska bli NaN");
        System.out.println("Svaret blev: " + mySqrtLoop(-1,Math.pow(10,-6)));
        System.out.println();
        System.out.println("ANDRA testet med roten ur 0.");
        System.out.println("Svaret blev: " + mySqrtLoop(0,Math.pow(10,-6)));
        System.out.println();
        System.out.println("TREDJE testet med roten ur 1.");
        System.out.println("Svaret blev: " + mySqrtLoop(1,Math.pow(10,-6)));
        System.out.println();
        System.out.println("FJÄRDE och FEMTE testet med roten ur 42 och 1337.");
        System.out.println("Roten ur 42. Svaret blev: " + mySqrtLoop(42,Math.pow(10,-6)));
        System.out.println("Roten ur 1337. Svaret blev: " + mySqrtLoop(1337,Math.pow(10,-6)));
    }
    */
}