package com.meta.verse.moip;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MOIP {
    public static void main(String[] args) throws IOException {
        String line = "";
        String splitBy = ",";
        Map<String,String> responseAndStatus = new HashMap<>();
        Integer count=0,count2=0;
        BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Raman.Nirala\\Downloads\\WorkSpace\\moip\\MOIP_5th_Oct_Data.csv"));
            while ((line = br1.readLine()) != null)
            {
                String[] employee = line.split(splitBy);
                count++;
                responseAndStatus.put(employee[1],employee[4]);
                if(employee[4]==null){
                    count2++;
                   // System.out.println(employee[1]);
                }
            }
        System.out.println(count +" "+ responseAndStatus.size() +"contains null in csv "+ count2);
        Map<String,String> map1 = new HashMap<>();
        File file = new File(
                "C:\\Users\\Raman.Nirala\\Downloads\\WorkSpace\\moip\\Acl2_success_2022-10-05.txt");
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        line="";
        splitBy = ",";
        count=0;
        count2=0;
        List<String> list = new ArrayList<>();
        while ((line = br.readLine()) != null){
            String[] responseId = line.split(splitBy);
            count++;

            list.add(responseId[0]);
        }
//        System.out.println(count +" "+map1.size() + "contains null in txt"+count2);
        Integer count1=0;
        String fail ="failed";
        List<String> list1 = new ArrayList<>();
        for(Map.Entry m:map1.entrySet()){
            if(!responseAndStatus.containsKey(m.getKey())){
                continue;
            }
            String response = responseAndStatus.get(m.getKey());
//            if(response==null){
//                System.out.println(response);
//                continue;
//            }
            if(response.equals(fail) ){
                list1.add((String) m.getKey());
                count1++;
            }
            //System.out.println(m.getKey() + " "+count1);
        }
        System.out.println(count1+" "+ list.get(0));
        for (int i=80000;i<120000;i++){
            System.out.println(list.get(i)+", ");
        }



    }
}
