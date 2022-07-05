### Spring概述

1. **轻量级开源Java EE 框架**
   
   * jar包数量少体积少，依赖少，可以独立支持使用
   * 框架模式，设定规则提供模块，解决企业开发的复杂性
   * IOC、AOP两个核心部分
     * IOC：控制翻转，把创建对象的过程交给Spring管理
     * AOP：面向切面，不修改源代码的情况下，增量式地增加功能
   * 特点和优势：
     * 方便解耦、简化开发（IOC）
     * AOP支持
     * 支持JUNIT内嵌，方便测试
     * 方便集成多重应用框架
     * 方便事务操作，对底层API封装简化代码
   
2. **IOC**
   
   1. IOC又称控制反转，是一种设计模式，用于降低代码的耦合度，常常使用依赖注入的方式实现，有时也通过依赖查找的方式进行实现。把对象的创建和对象的调用过程都交付给Spring框架去做，调用者本身内容中不包含对象的创建，可以降低代码的耦合度
   2. IOC底层原理
      1. XML解析
      2. 工厂模式
      3. 反射
   3. IOC的思想：
      1. 基于IOC容器，而容器底层就是对象工厂
      2. Spring提供两种容器实现方式：
         1. BeanFactory：IOC容器基本实现，是Spring内部使用的接口，开发人员不建议使用
         2. ApplicationContext：BeanFactory接口的子接口，提供更多更强大的功能，开发人员一般使用这种
         3. 区别：BeanFactory加载配置文件的时候不会创建对象，而是在使用的时候才会创建相应的类对象，而ApplicationFactory在**加载文件**的时候就会创建类对象，WEB操作中服务启动的时候创建对象比较有效率所以使用ApplicationFactory好一些
      3. ApplicationFactory有两个实现类
         1. FileSystemApplicationContext：文件索引使用文件路径
         2. ClassPathApplicationContext:配置文件的文件索引使用类路径,src是根路由
   
3. **Bean 管理**

   1. 对象的创建
   2. 属性的注入即DI，依赖注入
   3. 实现方式：XML和注解
   4. Spring有两种bean，一种是普通bean，一种是factorybean工厂bean，工厂bean的配置文件定义的类型可以和返回的对象类型不同，而普通bean必须相同
      * 工厂类bean：
        * 创建的时候，实现接口FactoryBean
        * 在接口里头的方法实现中返回bean的类型

4. **Bean的作用域和生命周期**

   1. 在Spring的默认情况下，bean是单实例的，设置bean是单实例还是多实例就是对bean的作用域的设计
   2. bean标签里头有一个属性值scope可以设置单实例或者多实例
      1. singleton:单实例，**加载Spring的配置文件的时候就会创建单例对象**
      2. prototype:多实例，**在调用getBean()方法的时候才会创建一个新的对象**
      3. request:
      4. session:
   3. 生命周期：从对象的创建到对象的销毁的过程
      1. 通过构造器创造bean的实例
      2. 为bean的属性设置值或者对其他的bean的引用
      3. {后置处理器:}容器类实现接口BeanPostProcessor的postProcessBeforeInitialization方法
      4. 调用bean的初始化方法(XML配置中可以设置)：使用bean标签里头的init-method属性进行设置
      5. {后置处理器:}容器类实现接口BeanPostProcessor的postProcessAfterInitialization方法
      6. bean的使用
      7. 当容器关闭的时候，调用bean的销毁方法(XML 配置中可以设置) ：使用bean标签里头的destroy-method属性进行设置：销毁的行为需要使用bean的容器代码中手动实现,contex.close()

5. **XML配置Bean的自动装配机制**

   * 根据注入的属性名称或者属性类型来自动进行属性的赋值（通过bean标签里头的autowire属性来进行设置）
   * autowire:
     * byName:通过属性名称匹配，这时候要求容器类的属性字面量和配置文件中的id对应上
     * byType:通过类型进行匹配， 这时候要求同一个类型的bean只有一个，这样才能将仅有的一个作为属性注入到容器中

6. **XML 引用外部的属性文件**

   1. 应用场景：类似于模板、类的作用，将XML配置文件中通用的赋值部分集合成一个文件引入到本XML文件当中
   2. 文件类型:properties
      1. 文件内容格式应该是key=value的形式
      2. 把外部的properties文件引入到XML配置文件中来
         1. 修改XML文件头的名称空间(同p-name的形式和util引入集合类型的形式)
         2. 在Spring的配置文件中先使用<contex:property-placeholder location="classpath:文件地址">的标签引入外部文件
         3. 文件中配置属性的位置使用占位符链接到properties文件中的key上
   3. 往往应用到数据库链接等固定初始化操作和bean设置中

7. **注解实现BEAN管理**

   1. 什么是注解：
      1. 注解是代码的特殊标记，格式：@注解名称{属性名称=属性值,属性名称=属性值},注解（Annontation），Java5引入的新特性，位于java.lang.annotation包中。提供了一种安全的类似注释的机制，用来将任何的信息或元数据（metadata）与程序元素（类、方法、成员变量等）进行关联。是一种说明、配置、描述性的信息，与具体业务无关，也不会影响正常的业务逻辑。但我们可以用反射机制来进行校验、赋值等操作。
      2. 注解可以放在类上、方法上、属性上
      3. 注解的作用：简化配置检验等工作
   2. Spring针对Bean管理中创建对象提供注解：
      1. @Component:普通组件
      2. @Service：主要偏向用于逻辑层
      3. @Controller：主要偏向用于视图层
      4. @Repository：主要偏向用于DOA层
      5. 事实上4个注解都能实现IOC，即Spring管理所有的BEAN生成
   3. 基于注解的方式实现对象的创建
      1. 引入spring的aop依赖
      2. 开启组件扫描：告诉Spring框架在哪些类中扫描并且解析注解
         1. 在配置文件中引入命名空间context
         2. <context:component-scan base-pacakge="包的名称"></context: component-scan>:多个包之间可以使用逗号隔开或者写入所有包的上层目录
         3. 创建类，在类上添加注解 
            * @Componet(value='userService')这里的userService等同于配置文件中的bean标签的id，如果不写，默认id名字是当前类的类名首字母小写如类名叫UserService，那id名字为userService
         4. use-default-filter=false可以关闭默认的过滤器，手动设置新的扫描过滤器
            1. context:include-filter type="annotation" expression="注解类型"如注解类型写成Controller就是仅仅扫描带Controller注解的类
            2. context:exclude-filter type,就是除了写了的注解其他的都扫描
      3. 注解方式实现属性的注入
         1. @AutoWired：根据属性的类型进行自动的注入,针对对象
            * 在某个属性（该属性对应一个对象）的上面写上AutoWired
         2. @Qualifier：根据属性的名称进行注入，针对对象，这个注解需要和AutoWired一起使用，针对多态的情况，同一个类型可能对应多个实现类，当实现类的名称确定的时候@Qualifier(value="实现类的名称")可以指定生成对应的实现类
         3. @Resource：可以根据类型，也可以根据名称注入，针对对象
            * @Resource直接根据类型注入
            * @Resource（name="类的名称"）根据名称注入
            * 这个注解是javax中的并不是spring框架中设置的注解，不太推荐
         4. @Value:注入普通类型
            * 普通类型上面写上Value类型，直接给普通的类型进行赋值
            * @Value（value=“字面量”）
         5. 纯注解开发（不写XML配置文件指明注解开发）
            1. 创建配置类，替代XML的配置文件
            2. 配置类上面需要加上@Configuration注解
            3. @ComponetScan(basePackages={"扫描包的地址","扫描包的地址"})
            4. 使用IOC创建的类的时候context需要使用AnnotationConfigApplicationContext(配置类.class)

8. **AOP**

   1. 什么是AOP

      1. AOP面向切面变成，通过预编译的方式和运行期间动态代理实现程序功能的统一维护，切分业务逻辑功能，降低模块间的耦合度，提高公用功能的重复利用率
      2. 主要功能：日志记录，性能统计，安全控制，事务处理，异常处理等，负责将不影响业务逻辑的部分隔离出来
      3. 不通过修改源代码的方式添加新的功能，将拆分出来的功能独立成模块在通过配置插入到业务逻辑中，这样业务逻辑中仅仅需要一小部分标记或者配置文件，将来不用的时候可以直接删除

   2. AOP的底层原理

      1. 动态代理，增强类的功能
         1. 有接口的情况（增强的类有实现一个**接口**）使用JDK的动态代理：创建接口的**实现类**代理对象
         2. 没有接口的情况使用CGLIB动态代理：为了增强类，创建**当前类的子类的代理对象**即可
      2. JDK动态代理：
         * 使用java.lang.reflect包中的Proxy类的newProxyInstance()
           * 参数一:类加载器
           * 参数二：被增强的目标方法的接口
           * 参数三:InvocationHandler，增强内容的方法体，这个handler是个接口，所以增强方法需要实现这个接口然后传入
             * 由于是增强，需要具备原有实现类的功能，即把被代理类对象通过有**参数构造**传进来
             * 上面的构造函数是指实现invocationHandler的这个类的构造函数
             * 在invocationHandler接口的invoke方法中有三个参数：proxy(代理对象),method(被增强的方法),args(method方法中使用的参数)

   3. AOP的术语

      1. **连接点**:可增强的方法位置，即哪些方法可以被增强，接口方式中体现为接口中定义的抽象方法
      2. **切入点**:实际被增强的方法，即编写了增强内容的方法
      3. **通知**（增强）:增强的逻辑内容
         * 前置通知：@Before
         * 后置通知（返回通知）：@AfterReturning
         * 环绕通知：被增强方法体的前后都会被执行@Around
         * 异常通知：相当于try catch部分的主要方法发生异常的catch里的部分@AfterThrowing
         * 最终通知:   相当于finally,不论有没有异常都会执行的部分使用@After注解
      4. **切面**:把通知应用到切入点的过程就叫做切面

   4. Spring框架中的AOP

      1. Spring框架中一般使用AspectJ实现AOP操作，AscpectJ不是Spring的固有部分，但是经常搭配在一起使用

      2. 基于AspectJ实现AOP的方式

         1. 基于XML配置

         2. 基于注解

            1. Spring配置文件中开启扫描或者使用注解开启扫描
            2. 使用注解创建User和UserProxy对象
            3. 在增强类UserProxy上加上注解@Aspect表示这个部分实现了增强类
            4. 在Spring的配置文件中开启生成代理对象
            5. 配置不同类型的通知：在增强类里头，在通知方法上协商通知类型，使用切入点表达式配置切入点

         3. 重用切入点的定义:切入点正常使用时是在切入方法上面的注解里头写入execution("路径")，而当有多个切入时机即多个@After @Before等标签增强同一个切入点的时候，可以重用省去重复的execution()编写

            * @Pointcut

         4. 多个增强类增强同一个方法的时候可以定义这个几个增强类的优先级：在增强类除@Component和@Aspect之外添加@Order(数字)的方式定义优先级，数字越小优先级越高

         5. **完全注解方式**：＠Configuration

            ​						   @ComponentScan(basePackages = "所在包")

            ​						   @EnableAspectJAutoProxy(proxyTargetClass=true)

   

   

