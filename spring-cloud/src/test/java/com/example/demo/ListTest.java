package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.List;

public class ListTest {


    @Test
    public void testSize() {
        Integer expected = 100;
        List list = PowerMockito.mock(List.class);
        PowerMockito.when(list.size()).thenReturn(expected);
        Integer actual = list.size();
        Assert.assertEquals("返回值不相等", expected, actual);
    }
}
