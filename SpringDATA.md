## JDBCTemplate

>  什么是JDBCTemplate：Spring对JDBC的封装，简化JDBC操作

2. 按照规范有三个组件需要用到
   1. JDBCTemplate对应的dataSource
   2. DAO层映射的SQL增删改查等方法
   3. Service层属性对应DAO层对象，调用DAO层对象的方法
3. SQL的操作
   1. 增update方法    "insert into t_user values(?,?,?)";
   2. 改update方法    "update t_user set username="",userstatus="" where user_id="""
   3. 删update方法    "delete from t_user where user_id="""
   4. 查
      1. queryForObject(sql,返回类型.class)方法返回数值,sql  select count(*) from t_user
      2. queryForObject(sql,Map(ORM封装map),Object(sql中语句的字面量))Map一般用new BeanPropertyRowMapper<对象>(对象.class)的形式,返回为某个对象
      3. query方法,参数类型与queryForObject方法中的三个参数一致，返回一个List集合
4. 批量SQL操作
   1. batchUpdate(sql,List集合)，sql语句和上面的第三点是一致的，返回值也是一个数组
   2. batchDelete(sql,List集合)，同样地进行删除



## 事务操作

> 事务是数据库操作的基本单元，逻辑上的一组操作，成功和失败是统一的，事务具有ACID四个特性，Atom原子性：操作不可分，一致性：操作之前和操作之后数据的总量不变(体现在转账上就是钱不会凭空增加和消失)，隔离性：多个事务之间“互不影响”，持久性：事务成功提交后，数据永久性的发生改变，期待下次事务的提交

1. 事务管理基本流程：
   1. 开启事务
   2. 业务操作
   3. 提交事务
   4. 事务失败时回滚
2. Spring进行事务管理可以用编程事务管理(自己写结构实现)也可以用声明式的事务管理操作(XML 和 注解的方式)，底层使用了AOP
3. PlatformTransactionManager(Spring通用的事务管理器，针对不同的框架有不同的实现类):
   1. 使用JDBCTemplate、Mybatis的时候用DataSourceTransactionManager
   2. 使用Hibernate的时候用HibernateTransactonManager
   3. ......
4. Spring使用事务管理器的流程
   1. 在Spring的配置文件中创建事务管理器(创建一个DataSourceTransaction的Bean对象)
   2. 开启事务的注解:在spring的XML配置文件里头加上tx的命名空间，<tx: annotation_driven transaction-manager="第一步创建的Bean对象">标签开启注解
   3. 在Service类上，或者类的方法上添加事务注解@Transactional，为类添加表示类中所有的方法都添加事务
5. 事务管理的参数配置[(5条消息) 看完就明白_spring事务的7种传播行为_gnixlee的博客-CSDN博客_事务的传播](https://blog.csdn.net/weixin_39625809/article/details/80707695)
   1. 事务传播行为:事务之间的调用引起的逻辑安排
      1. REQUIRED:如果调用者有事务在运行，那么被调用的方法会在事务内部运行，否则，就会启动一个新的事务，并在自己的事务内部运行
      2. REQUIRED_NEW：当前方法必须启动新的事务，并且在它自己的事务内运行，如果有事务正在运行，应该将它挂起
      3. SUPPORTS：如果有事务在运行，当前方法就在这个事务内运行，否则它可以不运行在事务中
   2. TIMEOUT:超时时间，事务需要在一定时间内提交，如果不提交超过时间就回滚
   3. 事务隔离级别
   4. ReadOnly：是否只读，设置成true之后，方法体内部不能有修改内容的操作
   5. rollbackFor:回滚，设置出现哪些异常的的时候事务回滚
   6. noRollbackFor:不回滚：设置出现哪些异常的时候事务不进行回滚

