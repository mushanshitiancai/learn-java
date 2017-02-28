package com.mushan.rabbit.demo1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mazhibin on 16/8/3
 */
public class Main {

    public static void main(String[] args) {
        Main m = new Main();
//        m.test2();
//        m.testStream();
//        m.testStream2();
//        m.testStream3();
//        m.testStream4();
//        m.testStream5();
        m.testMore();
    }

    private void testMore() {
        add(1,2,3);
        int[] arr = {5,6,7};
        add(arr);
    }

    public void add(int... a){
        for (int i : a) {
            System.out.println(i);
        }
    }

    public void test1(){
        Integer[] arr = new Integer[]{4,3,1,2,3};
        Arrays.sort(arr,(Integer a,Integer b) -> { return Integer.compare(a,b); });
        System.out.println(Arrays.toString(arr));
    }

    public void test2(){
        int s = 100;
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println("run "+s);
            }).start();
        }
    }

    public void testStream(){
        Stream<String> words = Stream.of("/Users/mazhibin/project/java/learn-java/learn-java/learn-dubbo/web-test/pom.xml".split("/"));
        System.out.println(words.count());

        Stream<Integer> ints = Stream.generate(() -> 1);
        System.out.println(Arrays.toString(ints.limit(10).toArray()));

        Stream<Integer> ints2 = Stream.iterate(0, n -> ++n);
        System.out.println(Arrays.toString(ints2.limit(10).toArray()));

        Stream<Integer> ints3 = Stream.iterate(0, n -> ++n);
        System.out.println(Arrays.toString(ints3.limit(10).map(n -> n*n).toArray()));

        Stream<Integer> ints4 = Stream.iterate(0, n -> ++n);
        System.out.println(Arrays.toString(ints4.limit(10).flatMap(n -> Stream.iterate(n, m -> ++m).limit(2)).toArray()));
    }

    public void testStream2(){
        Integer[] arr = new Integer[]{3,1,2,3};
        System.out.println(Arrays.toString(Stream.of(arr).sorted(Comparator.comparing(n -> n)).toArray()));

        Optional<Integer> v0 = Stream.of(arr).max(Comparator.comparing(Integer::valueOf));
        System.out.println(v0);

        Optional<Integer> v = Stream.of(arr).filter(n -> n > 1).findAny();
        if(v.isPresent()){
            System.out.println(v.get());
        }

        v.ifPresent(System.out::println);
        v.map(n -> ++n).ifPresent(System.out::println);
    }

    public void testStream3(){
        Integer[] arr = new Integer[]{1,2,3};

        Object[] strArr = Stream.of(arr).toArray();
        System.out.println(Arrays.toString(strArr));
        strArr[0] = "a";
        System.out.println(Arrays.toString(strArr));
        System.out.println(strArr.getClass());

        System.out.println();

        ArrayList<Integer> list = Stream.of(arr).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(list);
        System.out.println(list.getClass());

        List<Integer> list1 = Stream.of(arr).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list1.getClass());

        List<Integer> list2 = Stream.of(arr).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(list2);
        System.out.println(list2.getClass());

        System.out.println();


        Stream<String> words = Stream.of("/Users/mazhibin/project/java/learn-java/learn-java/learn-dubbo/web-test/pom.xml".split("/"));
        String s = words.collect(Collectors.joining());
        System.out.println(s);

        System.out.println();

        Double d = Stream.of(arr).collect(Collectors.summarizingInt(Integer::new)).getAverage();
        System.out.println(d);
    }

    public void testStream4(){
        List<Person> personList = Arrays.asList(
                new Person(1,"mzb"),
                new Person(2,"fuck"),
                new Person(1,"hehe")
        );

        Map<Integer, String> collect = personList.stream().collect(Collectors.toMap(Person::getId, Person::getName,(c,n)->n));
        System.out.println(collect);

    }

    public void testStream5(){
        List<Person> personList = Arrays.asList(
                new Person(1,"mzb"),
                new Person(2,"fuck"),
                new Person(1,"hehe")
        );

        personList.forEach(o -> o.setName("after"));
        System.out.println(personList);
    }

    class Person{
        private int Id;
        private String name;

        public Person(int id, String name) {
            Id = id;
            this.name = name;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "Id=" + Id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
