package com.meta.verse.javaLearningFrom5To18;

//import lombok.var;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class JavaLearning {
    record Person(String name,String email, String phone){
        //if i am not satisfied with default constructor we can create custom constructor
        /*Person(String name,String email, String phone){
            if(name==null){
                throw new IllegalArgumentException("null");
            }
            this.name = name;
            this.email= email;
            this.phone = phone;
        }*/
        //or
        //we can create Compact constructor -> this is only allowed in record -> java 16
        //we can add Static feilds/initializers/methods
        //but can't add instance variable/initializers

//      NOTE:   int number; // this is not allowed,, but
        static int number; //allowed

        public String name(){ //non static declaration in record is allowed is instance method
            //we can write code for name instance
            System.out.println("do something");
            return name;
        }
        Person{
            if(name==null){
                throw new IllegalArgumentException("null");
            }
        }
    }
    /*
    * record eliminate verbosity in creating java beans
    * record provide: public accessor method (getter, setter, equals, toString etc) , constructor, equals, hashcode and toString are auto generated.
    * we can also create custom implementations if we want
    * */
    public static void main(String[] args) throws IOException {
        Person person = new Person("raman", "raman@raman.com","0123456789");
        Person person1 = new Person("raman", "raman@raman.com","0123456789");
        Person person2 = new Person("raman", "raman@raman.com","0123456799");
        System.out.println(person.name); //direct access
        System.out.println(person.equals(person1));//true because both object are same
        System.out.println(person.equals(person2));//false because phone number diff

        System.out.println("new things in java");
        StarterForJDK11 starter = new StarterForJDK11();
       // starter.testWithGettingImmutableList();
        starter.testWithFileRealatedApi();
        starter.testWithPredicateNot();
    }
}
class StarterForJDK11{
    public void testWithGettingImmutableList(){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<5;i++)
            list.add(i);
        List<Integer> tempList = List.copyOf(list); // see error its useful set jdk to 10 for functioning well.
        //by using this we are getting tempList as immutable list . similar method in (set, map)
        //if already list is immutable then it copyOf will return same list,
        doNotChange(tempList);

    }

    private void doNotChange(List<Integer> list) {
        //we don't want someone to add in my list
        try {
            list.add(99);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // this we don't want. so we want unmodifiable list
        //one option is create hardcode list. with input like .. List.of(2,3,4,5); //so here we get immutable list.

    }
    public void testWithFileRealatedApi() throws IOException {
        //i want to read schema.sql from resource folder
        //first set the path
        Path path = Paths.get("src/main/resources/schema.sql");
        String file = Files.readString(path);
        System.out.println(file); // we can read file content
        //lets write file content
        String newFileContent = file.replace("NULL","");
        System.out.println(newFileContent);
        //now we will save this as new file
        Path path1 = Paths.get("src/main/resources/schema1CreatedBypracticingJdk11.sql");
        Files.writeString(path1,newFileContent);
    }
    public static Boolean evenOrOdd(Integer integer){
        return integer%2==0;
    }
    public void testWithPredicateNot(){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<5;i++)
            list.add(i);
        //using predicate to filter even number
        Predicate<Integer> predicate = number-> number%2==0;
        list.stream().filter(predicate).forEach(System.out::println); //even using predicate
        list.stream().filter(predicate.negate()).forEach(System.out::println); //odd using predicate
        list.stream().filter(StarterForJDK11::evenOrOdd).forEach(System.out::println); //even using refernce function
        list.stream().filter(Predicate.not(StarterForJDK11::evenOrOdd)).forEach(System.out::println); //odd using refernce function

    }
    public void stringTemplate(){
        System.out.println(" ".isBlank()); // return true if empty or only contains space.
        System.out.println(" L R ".strip().replace(" ","@")); //strip all the white space and replace used to replace with a char or anything
        System.out.println(" l r ".stripLeading().replace(" ","@"));
        System.out.println(" l r ".stripTrailing().replace(" ","@"));
        System.out.println("Line1\nLine2\nLine3\nLine4".lines());
        System.out.println(("UPPER".transform(s -> s.substring(2))).isBlank());

    }
    public void testWithTypeInferenceRunner(){
        List<String> list = List.of("raman","kumar");
        List<String> list1 = List.of("raman","kumar");
        List<List<String>> list2 = List.of(list1,list);
        System.out.println(list2);
        //or
        var names = List.of(list1,list);
        names.stream().forEach(System.out::println);
        //type Inference : in this case java infers the type of variable during compile time // came in  java 10// can add final . ex: final var names= List.of();
        for (int i = 0; i < 5; i++);
            //or
        for (var i = 0; i < 5; i++);
        //or
        for (String name: list1);
        //or
        for (var name: list);
        //we can't assign null to var
        //var abc = null; // we can't do this
        var var =2; // allowed because ar is not a keyword
    }
    public void testWithSwitchStatement(int day){//jdk 14
        String dayOfWeek="";
        switch (day){
            case 0:dayOfWeek ="sunday"; break;
            case 1:dayOfWeek ="monday"; break;
            default:throw new IllegalArgumentException("Invalid option"+day);
        }
        //or
        String dayOfWeek2 = switch (day){
            case 0 -> "sunday"; //checkout yeild
            case 1 -> "monday";
            case 2 ->{
                System.out.println("tuesday");
//                yeild "tuesday";
                yield "tuesady";
            }
            default -> throw new IllegalArgumentException(""+day);
        };
        return;
    }
    public void testWithTextBlock(){
        String str = """
                'raman'
                "kumar"
                        nirala
                     alpha
                beta     
                """;
        System.out.println(str);//it will print as it is looking; but we have to write message from new line
        //we can't do like this :  """ raman kumar nirala"""; ... this is wrong
    }

}