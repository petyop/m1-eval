package net.petyo.m1eval.prime;

public class PrimalityServiceNaive {

    public static boolean isPrime(long number) {
        if (number == 1l) {
            return false;
        }
        if (number == 2l) {
            return true;
        }
        if (number % 2l == 0l) {
            return false;
        }

        for (long d = 3; d <= (long) Math.sqrt(number); d++) {
            if (number % d == 0) {
                return false;
            }
        }

        return true;
    }

}
