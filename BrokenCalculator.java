// Did this code successfully run on Leetcode : Yes

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * LC 991
 */
public class BrokenCalculator {

    /**
     * Gives TLE
     * TC: exponential
     *
     * @param start
     * @param target
     * @return
     */
    public int brokenCalc_bfs(int start, int target) {
        int level = 0;
        if (start == target) {
            return level;
        }
        Set<Integer> visited = new HashSet<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        // initial push
        queue.offer(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int popIndex = 0, pop = 0; popIndex < size; popIndex++) {
                pop = queue.poll();
                if (pop < target) {
                    int curr = pop * 2;
                    if (curr == target) {
                        return level + 1;
                    }
                    if (!visited.contains(curr)) {
                        queue.offer(curr);
                        visited.add(curr);
                    }
                }
                if (pop > 0) {
                    int curr = pop - 1;
                    if (curr == target) {
                        return level + 1;
                    }
                    if (!visited.contains(curr)) {
                        queue.offer(curr);
                        visited.add(curr);
                    }
                }
            }
            level++;
        }
        return level;
    }

    /**
     * Gives StackOverflow Error as we end up landing on the root problem in a subtree.
     *
     * @param start
     * @param target
     * @return
     */
    public int brokenCalc_dfs(int start, int target) {
        return helper(start, target);
    }

    private int helper(int start, int target) {
        // base
        if (start == target) {
            return 1;
        }
        int stepsX2 = Integer.MAX_VALUE;
        if (start < target) {
            stepsX2 = helper(start * 2, target);
        }
        int steps_1 = Integer.MAX_VALUE;
        if (start > 0) {
            steps_1 = helper(start - 1, target);
        }
        int minSteps = Math.min(steps_1, stepsX2);
        return minSteps != Integer.MAX_VALUE ? minSteps + 1 : Integer.MAX_VALUE;
    }

    /**
     * Greedy from start to target -> FAILS.
     * as there are multiple paths to reach from start to target thinking greedily in this fashion,
     * and we might end up choosing the non-optimal path.
     * <br>
     * NOTE: any # can be *2, and it would have been better if we'd have -1 and then *2.
     * So, basically we don't end up with the optimal path in this way.
     *
     * @param start
     * @param target
     * @return
     */
    public int brokenCalc_greedy(int start, int target) {
        int steps = 0;
        while (start < target) {
            start *= 2;
            steps++;
        }
        if (start > target) {
            steps += start - target;
        }
        return steps;
    }

    /**
     * Greedy again!!!
     * <br>
     * Reverse approach!
     * <br>
     * Pick the optimal path!
     * Optimal path should end up on the target --> so, start from target and think greedy!
     * Think greedy: Try to maximize the chunk out, i.e., divide by 2, but only if it's completely divisible by 2,
     * else we will end up losing numbers by missing the modulus.
     */
    public int brokenCalc(int start, int target) {
        int steps = 0;
        while (start < target) {
            // start = start * 2;
            // NOTE: since, we're dividing, we should always go for complete divisiblity,
            // else we will be leaving out the modulus
            if (target % 2 == 0) {
                target = target / 2;
            } else {
                target++;
            }
            steps++;
        }
        // now, chances are we end up landing on the start
        // or, away from the start i.e. target < start
        steps += start - target;
        return steps;
    }

}
