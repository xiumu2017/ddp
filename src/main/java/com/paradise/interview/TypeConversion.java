package com.paradise.interview;

/**
 * 类型转换
 *
 * @author Paradise
 */
public class TypeConversion {
    public static void main(String[] args) {
        // 强制类型转换 精度 损失问题
        // shot占两个字节，byte只占一个字节，强制类型转换时只截取低字节
        short a = 128;
        byte b = (byte) a;
        System.out.println(b);
        String hash;
        Object o;
    }
}
