package com.meta.verse.moip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matcher {
    public static void main(String[] args) throws IOException {
        String line = "";
        String splitBy = " ";
        List<String> list = new ArrayList<>();
        BufferedReader br1 = new BufferedReader(
                new FileReader("C:\\Users\\Raman.Nirala\\Downloads\\WorkSpace\\tempCollection.csv"));

            while ((line = br1.readLine()) != null)
            {
                String[] employee = line.split(splitBy);
                list.add(employee[0]);
            }
        File file = new File("C:\\Users\\Raman.Nirala\\Downloads\\WorkSpace\\wa_enterpId_2_2021.csv");
        BufferedReader br = new BufferedReader(new FileReader(file));
        line="";
        splitBy = " ";
        List<String> list1 = new ArrayList<>();
        while ((line = br.readLine()) != null){
            String[] responseId = line.split(splitBy);
            list1.add(responseId[0]);
        }
        System.out.println(list1.get(0)+ " "+list.get(0));
        for(int i=0;i<list.size();i++){
            if(!list1.contains(list.get(i))){
                System.out.println(list.get(i));
            }
        }
    }
}
