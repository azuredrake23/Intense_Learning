package test;

import main.MyArrayList;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        MyArrayList<String> customList = new MyArrayList<>();
        MyArrayList<String> customList1 = new MyArrayList<>(list1);
        MyArrayList<String> customList2 = new MyArrayList<>(10);

        customList.add("ELEMENT1");
        System.out.println(customList);
        customList.add(1, "ELEMENT2");
        System.out.println(customList);
        customList.addAll(list1);
        System.out.println(customList);
        String var = customList.get(0);
        System.out.println(var);
        boolean var1 = customList.isEmpty();
        System.out.println(var1);
        customList.sort(Comparator.naturalOrder());
        System.out.println(customList);
        customList.remove(0);
        System.out.println(customList);
        System.out.println(customList.remove("ELEMENT10"));
        customList.remove("ELEMENT1");
        System.out.println(customList);
        customList.clear();
        System.out.println(customList);
    }
}
