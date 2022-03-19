package com.qezhhnjy.antq.algorithm.study.od;

import lombok.Data;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author zhaoyangfu
 * @date 2022/3/15-20:29
 * 16、比赛
 * 一个有N个选手参加比赛，选手编号为1~N（3<=N<=100），有M（3<=M<=10）个评委对选手进行打分。打分规则为每个评委对选手打分，最高分10分，最低分1分。
 * 请计算得分最多的3位选手的编号。如果得分相同，则得分高分值最多的选手排名靠前(10分数量相同，则比较9分的数量，以此类推，用例中不会出现多个选手得分完全相同的情况)
 */
public class question16 {

    public static void main(String[] args) {
        int m = 4, n = 5, limit = 3;
        // 每一行分别表示某评委对5个选手的打分
        int[][] scores = new int[][]{
                {10, 6, 9, 7, 6},
                {9, 10, 6, 7, 5},
                {8, 10, 6, 5, 10},
                {9, 10, 8, 4, 9}
        };

        PlayerInfo[] infos = new PlayerInfo[n];
        for (int i = 0; i < scores.length; i++) {
            int[] s = scores[i];
            for (int index = 0; index < s.length; index++) {
                int score = s[index];
                if (infos[index] == null) {
                    PlayerInfo info = new PlayerInfo();
                    info.setId(index + 1);
                    info.setTotal(score);
                    info.setScores(new int[m]);
                    info.getScores()[i] = score;
                    infos[index] = info;
                } else {
                    PlayerInfo info = infos[index];
                    info.setTotal(info.getTotal() + score);
                    info.getScores()[i] = score;
                }
            }
        }
        Arrays.stream(infos).sorted().limit(limit).forEach(System.out::println);
    }

    @Data
    public static class PlayerInfo implements Comparable<PlayerInfo> {
        private int   id;
        private int   total;
        private int[] scores;

        @Override
        public int compareTo(PlayerInfo o) {
            // 倒序
            if (total != o.total) return o.total - total;
            int[] sorted = IntStream.of(scores).sorted().toArray();
            int[] anotherSorted = IntStream.of(o.scores).sorted().toArray();
            for (int i = sorted.length - 1; i >= 0; i--) {
                if (sorted[i] != anotherSorted[i]) return anotherSorted[i] - sorted[i];
            }
            return 0;
        }
    }
}
