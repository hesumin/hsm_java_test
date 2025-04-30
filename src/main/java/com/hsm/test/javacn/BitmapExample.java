package com.hsm.test.javacn;

import java.util.BitSet;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.javacn
 * @className:(类名称): BitmapExample
 * @author: Hesumin
 * @createDate: 2025/04/30 11:27
 * @description: BitMap 在 Java 中的使用
 */

// BitMap 在 Java 中的具体实现是 java.util 中的 BitSet，BitSet 是一个可变大小的位向量，能够动态增长以容纳更多的位数据
public class BitmapExample {
    public static void main(String[] args) {
        // 创建一个BitSet实例
        BitSet bitmap = new BitSet();

        // 设置第5个位置为1，表示第5个元素存在
        bitmap.set(5);

        // 检查第5个位置是否已设置
        boolean exists = bitmap.get(5);
        System.out.println("Element at position 5 exists: " + exists);  // 输出: Element at position 5 exists: true

        // 设置从索引10到20的所有位置为1
        bitmap.set(0, 11);  // 参数是包含起始点和不包含终点的区间

        // 计算bitset中所有值为1的位的数量，相当于计算设置了的元素个数
        int count = bitmap.cardinality();
        System.out.println("Number of set bits: " + count);

        // 清除第5个位置
        bitmap.clear(5);

        // 判断位图是否为空
        boolean isEmpty = bitmap.isEmpty();
        System.out.println("Is the bitset empty after clearing some bits: " + isEmpty);
    }
}
