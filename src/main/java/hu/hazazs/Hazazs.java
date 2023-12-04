package hu.hazazs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hazazs {

    private static final Integer MAX_DAYS = 12;
    private Integer total;
    private final List<List<Integer>> subtotals = new ArrayList<>();
    private List<Integer> nearest = new ArrayList<>();
    private Integer difference = Integer.MAX_VALUE;
    private final List<Integer> current = new ArrayList<>();

    public static void main(String[] args) {
        new Hazazs().run();
    }

    private void run() {
        int total = 60_688;
        //List<Integer> subtotals = List.of(5886, 1682, 12822, 8303, 15450);
        List<Integer> subtotals = List.of(2400, 2300);

        this.total = total;
        fillUpSubtatals(subtotals);

        List<Integer> result = new ArrayList<>();

        generatePermutations(this.subtotals, result, 0, 0);

        System.out.println(this.nearest);
        if (!this.nearest.isEmpty()) {
            int sum = 0;
            for (int i = 0; i < subtotals.size(); i++) {
                sum += subtotals.get(i) * this.nearest.get(i);
            }
            System.out.println(sum);
        } else {
            System.out.println("Ennyi napszámmal nem lehetséges.");
        }
        result.sort(Comparator.naturalOrder());
        System.out.println(result);
    }

    private void fillUpSubtatals(List<Integer> subtotals) {
        for (Integer subtotal : subtotals) {
            int count = 1;
            while (count * subtotal < this.total) {
                count++;
            }
            List<Integer> multipliers = new ArrayList<>();
            for (int i = 0; i <= count; i++) {
                multipliers.add(subtotal * i);
            }
            this.subtotals.add(multipliers);
            this.current.add(0);
        }
    }

    private void generatePermutations(List<List<Integer>> subtotals, List<Integer> result, int depth, Integer current) {
        if (depth == subtotals.size()) {
            int difference = current - this.total;
            if (difference >= 0 && difference < this.difference && this.current.stream().reduce(0, Integer::sum) <= MAX_DAYS) {
                this.nearest = new ArrayList<>(this.current);
                this.difference = difference;
            }
            result.add(current);
            return;
        }

        for (int i = 0; i < subtotals.get(depth).size(); i++) {
            this.current.set(depth, i);
            generatePermutations(subtotals, result, depth + 1, current + subtotals.get(depth).get(i));
        }
    }

}