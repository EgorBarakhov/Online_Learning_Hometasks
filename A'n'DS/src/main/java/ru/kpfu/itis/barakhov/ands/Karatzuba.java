package ru.kpfu.itis.barakhov.ands;

public class Karatzuba {
    private static final int MIN_KARA = 2;
    /*
    You may use Integer.toBinaryString(A) and 'for' loop to add your value to the boolean array
    n = A.length = B.length (You may add insignificant false's left of shorter number)
    x = 2^(n / 2), x^2 = 2^n
    '* x' MEANS << n / 2, '* x^2' MEANS << n
    A = ax + b
    B = cx + d
    AB = (ax + b)(cx + d) = acx^2 + ((a + b)(c + d) - ac - bd)x + bd
    NEED TO COUNT
    ac, bd, (a + b)(c + d)
     */

    private static boolean[] multiply(boolean[] bigA, boolean[] bigB) {
        int length = bigA.length, halfLength = length / 2;
        if (length <= MIN_KARA) return simplyMultiply(bigA, bigB);
        boolean[] a = new  boolean[halfLength];
        boolean[] b = new  boolean[halfLength];
        boolean[] c = new  boolean[halfLength];
        boolean[] d = new  boolean[halfLength];
        System.arraycopy(bigA, 0, a, 0, halfLength);
        System.arraycopy(bigA, halfLength, b, 0, halfLength);
        System.arraycopy(bigB, 0, c, 0, halfLength);
        System.arraycopy(bigB, halfLength, d, 0, halfLength);
        boolean[] ac = multiply(a, c);
        boolean[] bd = multiply(b, d);
        boolean[] aPlusB = sum(a, b);
        boolean[] cPlusD = sum(c, d);
        boolean[] aPlusBMultCPlusD = multiply(aPlusB, cPlusD);
        boolean[] expr = sub(aPlusBMultCPlusD, sum(ac, bd));
        boolean[] acxPow2 = new boolean[ac.length * 2];
        System.arraycopy(ac, 0, acxPow2, 0, ac.length);
        boolean[] exprx = new boolean[ac.length * 2];
        System.arraycopy(expr, 0, exprx, 0, expr.length);
        return sum(sum(acxPow2, exprx), bd);
    }

    private static int[] fromBoolArrayToIntArray(boolean[] a, boolean[] b) {
        int intA = 0, intB = 0, power = 1;
        for (int i = a.length - 1; i >= 0; i--) {
            intA = a[i] ? intA + power : intA;
            intB = b[i] ? intB + power : intB;
            power *= 2;
        }
        int[] result = new int[2];
        result[0] = intA;
        result[1] = intB;
        return result;
    }

    private static boolean[] fromIntToBoolArray(int result) {
        String binResult = Integer.toBinaryString(result);
        boolean[] boolResult = new boolean[binResult.length()];
        for (int i = 0; i < binResult.length(); i++) {
            boolResult[i] = binResult.charAt(i) == 1;
        }
        return boolResult;
    }

    private static boolean[] sub(boolean[] aPlusBMultCPlusD, boolean[] ac) {
        int[] arrResult = fromBoolArrayToIntArray(aPlusBMultCPlusD, ac);
        int result = arrResult[0] - arrResult[1];
        return fromIntToBoolArray(result);
    }


    private static boolean[] sum(boolean[] a, boolean[] b) {
        int[] arrResult = fromBoolArrayToIntArray(a, b);
        int result = arrResult[0] + arrResult[1];
        return fromIntToBoolArray(result);
    }

    public static void main(String[] args) {
        final boolean[] A = { true, false, false, true,
                false, true, false, false,
                true, true, false, false,
                true, false, false, true};
        final boolean[] B = { false, true, true, false,
                false, true, false, false,
                false, false, true, true,
                true, false, false, true};
        int n = A.length;
        boolean[] result = multiply(A, B);
    }

    private static boolean[] simplyMultiply(boolean[] a, boolean[] b) {
        int[] arrResult = fromBoolArrayToIntArray(a, b);
        int result = arrResult[0] * arrResult[1];
        return fromIntToBoolArray(result);
    }
}
