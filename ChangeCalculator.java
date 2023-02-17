import java.util.*;
public class ChangeCalculator {
    public static int[] calculateChange(int amount, int[] denominations) {
        int[] coinCounts = new int[denominations.length];
        for (int i = denominations.length - 1; i >= 0; i--) {
            int denomination = denominations[i];
            while (amount >= denomination && coinCounts[i] < getAvailableCoins(denomination)) {
                amount -= denomination;
                coinCounts[i]++;
            }
        }
        if (amount > 0) {
            throw new RuntimeException("Unable to make change for the given amount with the available coins");
        }
        return coinCounts;
    }

    private static int getAvailableCoins(int denomination) {
        switch (denomination) {
            case 1:
            case 2:
            case 5:
            case 10:
                return Integer.MAX_VALUE;
            case 20:
                return 100;
            case 50:
                return 50;
            case 25:
                return 200;
            default:
                throw new IllegalArgumentException("Unsupported denomination");
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int amount = sc.nextInt();
        int[] denominations = {1, 2, 5, 10, 20, 50};
        int[] coinCounts = calculateChange(amount, denominations);
        System.out.println(Arrays.toString(coinCounts)); 
    }
}