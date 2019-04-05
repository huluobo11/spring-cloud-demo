package com.imooc.userconsumer;

import org.junit.Test;

public class TestMe {

    @Test
    public void tests() {
        Runnable runnable = ()-> System.out.println("AAA");
        Thread thread = new Thread(runnable);
    }
}
