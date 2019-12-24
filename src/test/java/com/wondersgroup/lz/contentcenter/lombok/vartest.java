package com.wondersgroup.lz.contentcenter.lombok;

import lombok.val;
import lombok.var;
import org.junit.Test;

import java.util.ArrayList;

public class vartest {
    public static void main(String[] args) {
        val name="xiaoming";
        System.out.println(name);
        var age=35;
        System.out.println(age);
    }
    @Test
    public void val(){
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
    }
}
