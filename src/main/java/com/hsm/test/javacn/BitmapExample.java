package com.hsm.test.javacn;

import java.util.BitSet;

/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.javacn
 * @className:(������): BitmapExample
 * @author: Hesumin
 * @createDate: 2025/04/30 11:27
 * @description: BitMap �� Java �е�ʹ��
 */

// BitMap �� Java �еľ���ʵ���� java.util �е� BitSet��BitSet ��һ���ɱ��С��λ�������ܹ���̬���������ɸ����λ����
public class BitmapExample {
    public static void main(String[] args) {
        // ����һ��BitSetʵ��
        BitSet bitmap = new BitSet();

        // ���õ�5��λ��Ϊ1����ʾ��5��Ԫ�ش���
        bitmap.set(5);

        // ����5��λ���Ƿ�������
        boolean exists = bitmap.get(5);
        System.out.println("Element at position 5 exists: " + exists);  // ���: Element at position 5 exists: true

        // ���ô�����10��20������λ��Ϊ1
        bitmap.set(0, 11);  // �����ǰ�����ʼ��Ͳ������յ������

        // ����bitset������ֵΪ1��λ���������൱�ڼ��������˵�Ԫ�ظ���
        int count = bitmap.cardinality();
        System.out.println("Number of set bits: " + count);

        // �����5��λ��
        bitmap.clear(5);

        // �ж�λͼ�Ƿ�Ϊ��
        boolean isEmpty = bitmap.isEmpty();
        System.out.println("Is the bitset empty after clearing some bits: " + isEmpty);
    }
}
