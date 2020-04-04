import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
    public static LinkedList<int[]> voc = new LinkedList<>();
    public static ArrayList<Integer> chars = new ArrayList<>();
    public static int deep = 3;

    public static void main(String[] args) {
        chars.add(97);
        chars.add(98);
        chars.add(99);

        for (int curr : chars) {
            voc.add(new int[]{curr});
//            for (int curr2 : chars) {
//                voc.add(new int[]{curr, curr2});
//            }
        }


                int ii = 0;
        int[] nn;
//        while (ii < 1000) {
//            if (x[x.length-1]==97){
//
//            }
//
//            ii++;
//        }
aaa(new int[]{97,98,99});
        for (int[] curr : voc) {
            String str = "";
            for (int i = 0; i < curr.length; i++) {
                str += String.valueOf((char) curr[i]);
            }
            System.out.println(str);
        }
    }

    public static String makeString(int[] data) {
        String str = "";
        for (int i : data) {
            str += String.valueOf((char) i);
        }
        return str;
    }
    public static void aaa(int[] x){
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < chars.size(); j++) {
                voc.add(new int[]{x[i], chars.get(j)});
                //System.out.println(makeString(new int[]{x[i], chars.get(j)}));
            }

        }
    }
}
