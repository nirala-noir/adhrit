package com.meta.verse.impoAnnotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

/*@Configuration basically used to create a class which contains beans in form of methods
* means here we create methods which will return beans
* this annotation means a class which is source of beans where lots of methods present which are returning java beans.
* beans means automatic object , whose object will be maintained by spring
 * */
@Configuration
@ComponentScan(basePackages = {"C:\\Users\\Raman.Nirala\\Desktop\\work\\verse\\src\\main\\java\\com\\meta\\verse\\fetchDataFromPropertyFile","subPack"})
//@ComponentScan(basePackages = {"subPack"})
public class ConfigurationAndBeanAnnotation {
    //
    @Bean("object1")
    @Lazy
    public Student getStudent(){
        System.out.println("object is created here and catched by @Bean and then this bean is catched by @Configuration which " +
                "pushed all object to spring container from there @Autowired catch the respected dataType object");
        return new Student("Object1");
        /*here getStudent method returning object of student and configuration class marking that this student objet returning
        is a bean and this student object which is return by method getStudent() is managed by spring container/spring
         for that we will use @Bean annotation which will mark that from here one bean is returning after that
         we know that this bean will managed by spring so, now we don't need to create an object as (new Student()) we can directly
         use @autowired beause we know this function will return a bean.
         */
    }

    //let's create another object of student for testing of @qualifier
    @Bean("object2")
    @Lazy
    public Student getStudentAnother(){
        System.out.println("creating second object of student ");
        return new Student("Object 2 ");
    }

    /* because of creating 2 object of same class in spring container,
    //@Autowired confuse to get one of them nd throws
    //error: Field student in com.meta.verse.impoAnnotations.ImpoAnnotation required a single bean, but 2 were found:
    *so to overcome this problem we will name our @Bean
    *and we will use @Qualifier("Object name") along with @Autowired
    */
    @Bean
    public Date getDate(){
        System.out.println("creating own date and using @Bean annotation");
        return new Date();
    }
}
