package com.example.go;

public class Test {
    public static void main(String[] args) {
        String s = new StringBuilder("网管群").append("初六话").toString();
        System.out.println(s == s.intern());
    }
}
