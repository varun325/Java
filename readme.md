1. Very  popular framework for building Java Application
2. Was introduced as a very good alternative to j2ee
3. Initially j2ee had only jsp and servelets, over the year it kept getting more and more features and became more and more complicated.
4. Enterprise java beans and there intial versions were very complex.
    - These beans were slow and complicated, and needed so many files for production.
5. People started making there own less complicated implementations.
6. Spring came around 2004 and started evolving alongside javaEE.
7. Java EE 7 had a lot of thing which spring had but the bad reputation didn't let it grow.
8. At the moment, you could do similar things with both but spring is very popular.
9. Picking up both the things can keep a person job ready.

## This repo will consist of my personal notes for Java framworks and related code.

[Spring Official Website](https://www.spring.io)

## Goals of Spring:
- Lightweight development with Java POJOs (Plain Old Java Objects)
- Dependency injection to prmote loose coupling.
- Declarative programming with Aspect-Oriented-Programming(AOP)
- Minimize boilerplate Java code

- ## Container
    - ## Aspect oriented coding
        - Build specific services for specific jobs
    - Aspects
    - Instrumentation -> use class level agents to achieve a certain thing, like building an agent to monitor something yourself, usually you'll be using things provided by spring itself.
    - Messaging

- ## Data access layer
    - JDBC -> connect to a database
    - ORM -> hook in hibernate or jpa
    - Transaction -> support transactions, database calls etc
    - OXM and JMS (Java message service -> send messages to a message queue asynchronously)

- ## Web Layer
    - Servlet
    - WebSocket
    - Web
    - Portlet
- ## Test Layer
    - Unit
    - Integration
    - Mock

- ## Spring 'Projects'
    - Additional Spring modules built on top of the core Spring Framework
    - Only use what you need.
        - Spring Cloud, Spring data
        - Spring Batch, Spring Security
        - Spring for Android, Spring Web Flow
        - Spring web services, spring LDAP

- ## Required Software
    - Install JDK 8 or higher
    - Install Apache tomcat
    - Java Application server
    - Eclipse/Intellij


- ## Inversion of Control
    - Outsourcing the creation and management of the objects.
    - 

- ## Spring development process
    - Configure your spring beans
    - Create a spring container -> generally known as application context
    - Retrieve Beans from Spring Container

- ## Using and xml based application context
    - Create the application context object and pass the xml file into it.
    - From the context call the getBean method and pass bean id and Interface, Abstract class name to it.
    - just call the object and invoke it's method
    - close the context

    - Here, the xml file can act as a configuration file for us and behind the scenes, Spring will do the type casting for us.

- ## Main file and using context for inversion of control and dependency injection

    - ```java

        package com.varun.springdemo;

        import org.springframework.context.support.ClassPathXmlApplicationContext;

        public class SetterDemoApp {

            public static void main(String[] args) {
                //Load the spring configuration file
                
                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                
                //retrieve bean from spring container
                Coach theCoach = context.getBean("myCricketCoach",CricketCoach.class);
                
                //call methods on the bean
                System.out.println(theCoach.getDailyWorkout());
                System.out.println(theCoach.getDailyFortune());
                
                //close the context
                context.close();
            }

        }
        
        ```

- ## Dependency inversion

    - You're outsourcing the creation of your objects and addition of it's dependencies.
    - ## Steps for a constructor injection
        - Define the dependency intgerface and class.
        - Create a constructor in your class for injections.
        - Configure the dependency injection in Spring config file.
        - That's it, Spring will handle the rest of the object creation for you.
        - > For the below xml code, the spring will create the below java code in the backend for you

        - ```xml
             <bean id="myFortune"
            class="com.varun.springdemo.HappyFortuneService">
            <!-- set up the constructor injection -->
            </bean>
        
            <bean id="myCoach"
                class="com.varun.springdemo.BaseBallCoach" >
                <constructor-arg ref="myFortune"></constructor-arg>
            </bean>
            ```
        - ```java
            FortuneService fortuneService = new HappyFortuneService();
            Coach coach = new BaseBallCoach(fortuneService);
            ```
        - Spring handled the creation of the object for you, i.e Inversion of control in the first line.
        - Spring also handled the dependency injection for you by passing the fortuneService object into the constructor using a constructor injection.
    - ## Steps for Setter injection
        - Create setter methods in your class for the injections.
        - Configure the dependency injection in Spring config file
        - > For the below xml code, the spring will create the below java code in the backend for you

        - ```xml
             <bean id="myFortune"
            class="com.varun.springdemo.HappyFortuneService">
            <!-- set up the constructor injection -->
            </bean>
            <bean id="myCricketCoach"
                class="com.varun.springdemo.BaseBallCoach" >
                <property name="fortuneService" ref="myFortune"><property>
            </bean>
            ```
        - ```java
            FortuneService fortuneService = new HappyFortuneService();
            Coach coach = new BaseBallCoach();
            coach.setFortuneService(fortuneService)
            ```
    - ## Injecting literal values
        - Create a setter method in your class for injections.
        - Configure the injections in Spring config file.
        - > For the below xml code, the spring will create the below java code in the backend for you

        - ```xml
            <bean id="myCricketCoach"
                class="com.varun.springdemo.BaseBallCoach" >
                <property name="email" value="abc@123.com"></property>
                <property name="team" value="Mumbai Indians"></property>
            </bean>
            ```
        - ```java
            CricketCoach coach = new CricketCoach();
            coach.setEmail("abc@123.com");
            coach.setTeam("Mumbai Indians");
            ```
        - > Note: We can't use the Coach interface referance for casting here because Coach class doesn't have the specific getter and setter for email and team property.
    

- ## Using an external properties file
    - While having literal injections in the application context is fine, it's not really good to add a lot of hard coded values in the application context
    - So, instead we can use a properties file that can hold all the properties.
        - Create the properties file {.properties extension} -> In the same path as applicationContext.xml
            - ```
                foo.email="abc@123.com"
                foo.team="Mumbai Indians"
                ```
        - Lead the values in the spring config file
            - ```xml
                <!-- Load the properties file -->
	            <context:property-placeholder location="classpath:sport.properties"/>
                ```
        - Reference the values from the properties file.
            - ```xml
                <bean id="myCricketCoach" class="com.varun.springdemo.CricketCoach">
                <!-- inject literal value -->
                <property name="email" value="${foo.email}"></property>
                <property name="team" value="${foo.team}"></property>
	            </bean>
                ```
- 
    