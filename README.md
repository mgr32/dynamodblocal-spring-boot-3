This project reproduces an exception that's thrown from `DynamoDBLocal 1.20.0` when Spring Boot 3 is used
(this is because migration from javax to jakarta is required by Spring Boot 3.0, as described in the [migration guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide#jakarta-ee)).

The skeleton has been generated from [spring initializr](https://start.spring.io/), and then:
* `DynamoDBLocal` has been added according to the instructions [here](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)
* `DynamoDbLocalConfig` class has been added that tries to instantiate the `LocalDynamoDBRequestHandler` from `DynamoDBLocal` 

To reproduce, run `./mvnw spring-boot:run`. The following error will appear:

```
2022-12-09T10:58:10.208+01:00  WARN 77500 --- [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'localDynamoDBServerHandler' defined in class path resource [com/example/demo/DynamoDbLocalConfig.class]: Failed to instantiate [com.amazonaws.services.dynamodbv2.local.server.LocalDynamoDBServerHandler]: Factory method 'localDynamoDBServerHandler' threw exception with message: javax/servlet/ServletInputStream
2022-12-09T10:58:10.210+01:00  INFO 77500 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2022-12-09T10:58:10.217+01:00  INFO 77500 --- [           main] .s.b.a.l.ConditionEvaluationReportLogger :

Error starting ApplicationContext. To display the condition evaluation report re-run your application with 'debug' enabled.
2022-12-09T10:58:10.225+01:00 ERROR 77500 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'localDynamoDBServerHandler' defined in class path resource [com/example/demo/DynamoDbLocalConfig.class]: Failed to instantiate [com.amazonaws.services.dynamodbv2.local.server.LocalDynamoDBServerHandler]: Factory method 'localDynamoDBServerHandler' threw exception with message: javax/servlet/ServletInputStream
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:652) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:488) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1324) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1161) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:561) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:521) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:326) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:324) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:200) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:961) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:915) ~[spring-context-6.0.2.jar:6.0.2]
        at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:584) ~[spring-context-6.0.2.jar:6.0.2]
        at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146) ~[spring-boot-3.0.0.jar:3.0.0]
        at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:730) ~[spring-boot-3.0.0.jar:3.0.0]
        at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:432) ~[spring-boot-3.0.0.jar:3.0.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:308) ~[spring-boot-3.0.0.jar:3.0.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1302) ~[spring-boot-3.0.0.jar:3.0.0]
        at org.springframework.boot.SpringApplication.run(SpringApplication.java:1291) ~[spring-boot-3.0.0.jar:3.0.0]
        at com.example.demo.DemoApplication.main(DemoApplication.java:10) ~[classes/:na]
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.amazonaws.services.dynamodbv2.local.server.LocalDynamoDBServerHandler]: Factory method 'localDynamoDBServerHandler' threw exception with message: javax/servlet/ServletInputStream
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:171) ~[spring-beans-6.0.2.jar:6.0.2]
        at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:648) ~[spring-beans-6.0.2.jar:6.0.2]
        ... 19 common frames omitted
Caused by: java.lang.NoClassDefFoundError: javax/servlet/ServletInputStream
        at com.example.demo.DynamoDbLocalConfig.localDynamoDBServerHandler(DynamoDbLocalConfig.java:14) ~[classes/:na]
        at com.example.demo.DynamoDbLocalConfig$$SpringCGLIB$$0.CGLIB$localDynamoDBServerHandler$0(<generated>) ~[classes/:na]
        at com.example.demo.DynamoDbLocalConfig$$SpringCGLIB$$2.invoke(<generated>) ~[classes/:na]
        at org.springframework.cglib.proxy.MethodProxy.invokeSuper(MethodProxy.java:257) ~[spring-core-6.0.2.jar:6.0.2]
        at org.springframework.context.annotation.ConfigurationClassEnhancer$BeanMethodInterceptor.intercept(ConfigurationClassEnhancer.java:331) ~[spring-context-6.0.2.jar:6.0.2]
        at com.example.demo.DynamoDbLocalConfig$$SpringCGLIB$$0.localDynamoDBServerHandler(<generated>) ~[classes/:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
        at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:139) ~[spring-beans-6.0.2.jar:6.0.2]
        ... 20 common frames omitted
Caused by: java.lang.ClassNotFoundException: javax.servlet.ServletInputStream
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641) ~[na:na]
        at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188) ~[na:na]
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:520) ~[na:na]
        ... 31 common frames omitted
```

