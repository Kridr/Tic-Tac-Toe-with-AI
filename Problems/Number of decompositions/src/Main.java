import java.util.Scanner;

class Main {
    public static void outAddends(int[] addends) {
        for (int i = addends.length - 1; i >= 0; i--) {
            for (int t = 0; t < addends[i]; t++) {
                System.out.print((i + 1) + " ");
            }
        }

        System.out.println();
    }

    public static void addendsWithLimit(int[] addends, int limit) {
        if (addends[0] - limit >= 0) {
            addends[limit - 1] += 1;
            addends[0] -= limit;

            outAddends(addends);

            for (int i = 2; i <= limit; i++) {
                var copy = addends.clone();
                addendsWithLimit(addends, i);
                addends = copy;
            }
        }

    }

    public static void addendsProcess(int[] addends) {
        outAddends(addends);
        for (int limit = 2; limit <= addends.length; limit++) {
            var copy = addends.clone();
            addendsWithLimit(addends, limit);
            addends = copy;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] initAddends = new int[n];

        initAddends[0] = n;

        addendsProcess(initAddends);
    }
}