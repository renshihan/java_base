package com.renshihan.leet;

import java.util.Arrays;

/**
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class T20201109 {
    public int[][] kClosest(int[][] points, int K) {
        //1.排序
        Arrays.sort(points, (c1, c2) -> (c1[0] * c1[0]) + (c1[1] * c1[1]) - (c2[0] * c2[0]) - (c2[1] * c2[1]));
        //2.截取
        return Arrays.copyOfRange(points, 0, K);
    }


}
