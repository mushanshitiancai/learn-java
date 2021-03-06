package com.mushan.rabbit.demo1.learn;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by mazhibin on 16/8/1
 */
public class DemoTest {

    @Test
    public void testAdd() throws InterruptedException {
        Server server = new Server();
        assertEquals("test add", 10, server.add(8, 2));
    }

    @Test
    public void testEquals() {
        System.out.println(Arrays.equals(new int[][]{{1},{1,2},{1}},new int[][]{{1},{1},{1}}));
        System.out.println(Arrays.deepEquals(new int[][]{{1},{1,2},{1}},new int[][]{{1},{1,2},{1}}));
        System.out.println(Arrays.equals(new int[][]{{1}},new int[][]{{1}}));
        System.out.println(Arrays.deepEquals(new int[][]{{1}},new int[][]{{1}}));
        System.out.println(Arrays.equals(new String[][]{{"1"}},new String[][]{{"1"}}));
        System.out.println(Arrays.deepEquals(new String[][]{{"1"}},new String[][]{{"1"}}));
    }

    @Test
    public void testThat(){
        assertThat("a",is("a"));
        assertThat("a",is(new String("a")));
        assertThat("a",equalTo("a"));

        List<Integer> list = Arrays.asList(1,2,3);
        assertThat(list,hasItem(1));
        assertThat(list,hasItems(is(1),is(3)));
//        assertThat(list,everyItem(is(1)));
    }

    @Test
    public void testChild(){
        assertEquals(new Child().hi(),Child.class);
    }


    @Test
    public void testMock(){
        Book book = mock(Book.class);
        when(book.getName()).thenReturn("fuck");

        Child child = new Child();
        child.setBook(book);

        String name = child.getBook().getName();
        assertEquals(name,"fuck");
    }

    @Test
    public void testGUID(){
        System.out.println(UUID.randomUUID());
    }
}
