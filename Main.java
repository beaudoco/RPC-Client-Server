import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] tmpArr = {0,1,0,1,1,0,0,1};

        //int[] tmpHold = cellCompete(tmpArr,7);
//        for(int i = 0; i < tmpHold.length; i++) {
//            System.out.println(tmpHold[i]);
//        }



//        public static int countGroupsLinear(int[] a){
//            Stack<Integer> stack = new Stack<>();
//            stack.push(a[0]);
//            for (int i = 1; i < a.length; i++) {
//                if (a[i] >= stack.peek()) stack.push(a[i]);
//                else {
//                    int last = stack.pop();
//                    while (stack.size() > 0 && a[i] < stack.peek()) stack.pop();
//                    stack.push(last);
//                }
//            }
//            return stack.size();
//        }
//
//        int rand = 776;
//        int negRand = -2567;
//        System.out.println(positiveCheck(rand));

//        char a[] = {'a','b','a'};
//        char b[] = {'b','c','b'};
//        if(AreStringsIsomorphic(a,b)) {
//            System.out.println("true");
//        } else System.out.println("false");
    }

    public static int[] cellCompete(int[] cells, int N) {
        N = (N % 14 == 0) ? 14 : N % 14; // Evident cycle after 14th iteration, print intermediate results to figure out on your own!
        for ( int i = 0 ; i < N ; i++ ) {
            if ( i != 0 ) { // After first iteration boundry cells will become 0 and stay 0 for upcoming iterations
                cells[0] = 0;
                cells[cells.length - 1 ] = 0;
            }
            for ( int j = 1 ; j < cells.length - 1; j++ ) {
                if ( (cells[j-1] & 1) == (cells[j+1] & 1) ) {
                    // next state 1
                    if ( (cells[j] & 1 ) /* `&1` to get current state*/  == 1  ) cells[j] = 3;
                    else cells[j] = 2;
                }
                else {
                    // next state 0
                    if ( (cells[j] & 1) == 1 ) cells[j] = 1;
                    else cells[j] = 0;
                }
            }
            for ( int j = 0 ;j < cells.length; j++  ){
                // Set all the cell to next state on order to prepare for next iteration
                cells[j] = cells[j]>>1; // get next state by `>> 1`
            }
        }
        return new int[]{0, 1};
        //return cells;
    }



    private static int positiveCheck(int rand) {
        char[] chars = ("" + rand).toCharArray();
        int pos = 0;

        for(int i =0; i < chars.length; i++) {
            if(chars[i] <= '5') {
                pos = i;
                break;
            }
        }
        if(pos == 0) {
            pos = chars.length;
        }

        char[] charArr = new char[10];
        int indx = 0;
        for(int i = 0; i < chars.length + 1; i++) {
            if(i == pos) {
                charArr[i] = '5';
            } else if(indx < chars.length ) {
                charArr[i] = chars[indx];
                indx ++;
            }
        }

        return Integer.parseInt(new String(charArr));

    }

    private static int negativeCheck(int rand) {
        char[] chars = ("" + rand).toCharArray();
        int pos = 0;

        for(int i =0; i < chars.length; i++) {
            if(chars[i] > '5') {
                pos = i;
                break;
            }

        }

        char[] charArr = new char[10];
        int indx = 0;
        for(int i = 0; i < chars.length + 1; i++) {
            if(i == pos) {
                charArr[i] = '5';
            } else {
                charArr[i] = chars[indx];
                indx ++;
            }
        }

        return Integer.parseInt(new String(charArr));

    }

    private static boolean AreStringsIsomorphic(char[] a, char[] b) {
        if(a.length != b.length) return false;
        Hashtable<Character, Character> tmpDict = new Hashtable<>();

        for(int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) {
                Character tmpVal = tmpDict.get(a[i]);
                if(tmpVal == null) {
                    tmpDict.put(a[i],b[i]);
                    a[i] = b[i];
                } else {
                    if(b[i] != tmpVal){
                        return false;
                    }
                    a[i] = tmpVal;
                }
            }
        }
        for(int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) return false;
        }
        return true;
    }
}
