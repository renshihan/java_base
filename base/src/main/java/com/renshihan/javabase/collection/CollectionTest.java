package com.renshihan.javabase.collection;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 2017/8/14.
 */
@Slf4j
public class CollectionTest {

    @Data
    static class Human{
        private int age;
        private String name;

        public Human(int age, String name) {
            this.age = age;
            this.name = name;
        }
        public static int compareByNameAndAge(Human h1,Human h2){
            if(h1.getName().equals(h2.getName())){
                return Integer.compare(h1.getAge(),h2.getAge());
            }
            return h1.getName().compareTo(h2.getName());
        }
        @Override
        public String toString() {
            return "Human{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static List<Human> init(){
        return Lists.newArrayList(
                new Human(20,"小米"),
                new Human(21,"小米"),
                new Human(30,"小名"),
                new Human(335,"小李abc"),
                new Human(31,"小来"));
    }
    public static void main(String[] args) {
        List<Human> humans= init();
        log.info("比较前[{}]",humans);
        humans.sort((Human h1,Human h2) -> h1.getName().compareTo(h2.getName()));
        humans.sort((Human h1,Human h2) -> h1.getAge()-h2.getAge());
        log.info("比较后1====[{}]",humans);

        humans= init();
        humans.sort(Human::compareByNameAndAge);
        log.info("比较后2====[{}]",humans);
        humans= init();
        Collections.sort(humans, Comparator.comparing(Human::getName));
        log.info("比较后3====[{}]",humans);
        humans= init();
        Comparator<Human> comparator=Human::compareByNameAndAge;
        humans.sort(comparator.reversed());
        log.info("比较后4=(反序)===[{}]",humans);
    }

}
