import java.util.ArrayList;
import java.util.List;

public class Lab2a {
    public static double[] simplifyShape(double[] poly, int k) {

        List<Double> list = new ArrayList<Double>();

        for (int i = 0; i < poly.length; i++) {
            list.add(new Double(poly[i]));
        }

        int n = list.size();
        double aX, aY, bX, bY, cX, cY, nP, oP, ab, bc, ac, abx, aby, bcx, bcy, acx, acy;
        while (n > k * 2) {

            int indexToRemove = 0; //Had to use a value
            oP = 10000000; //High value just because
            int i = 0;
            while (i < n - 5) {
                aX = list.get(i);
                aY = list.get(i + 1);
                bX = list.get(i + 2);
                bY = list.get(i + 3);
                cX = list.get(i + 4);
                cY = list.get(i + 5);

                abx = Math.abs(aX - bX);
                bcx = Math.abs(bX - cX);
                acx = Math.abs(aX - cX);

                aby = Math.abs(aY - bY);
                bcy = Math.abs(bY - cY);
                acy = Math.abs(aY - cY);

                ab = Math.hypot(abx, aby);
                bc = Math.hypot(bcx, bcy);
                ac = Math.hypot(acx, acy);

                nP = ab + bc - ac;
                if (nP <= oP) {
                    oP = nP;
                    indexToRemove = i + 2;//X-value of middle point that will be removed
                }
                i += 2;
            }
            if (indexToRemove > 0) {
                list.remove(indexToRemove);//Removes x coordinate
                list.remove(indexToRemove);//Removes y coordinate
            }
            n = n - 2;
        }
        double[] arr = new double[k * 2];
        for (int i = 0; i < k * 2; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}