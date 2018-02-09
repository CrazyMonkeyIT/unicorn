
*******************
独角兽出品  APP 介绍
*******************

采用springboot + maven + mybatis +shiro + mysql搭建，编译环境为java8
其中通讯方便为spring4.x的stomp的websocket

前端采用freemark模板+jquery

该项目为后台管理系统，项目结构如下：
unicorn
      .
      .
      djs-app       用于web部署后台，
      djs-db        用于管理持久层dao相关实现
      djs-service   用于实现基于事物逻辑层实现


注意事项:
****************************************************************************************
Mybatis插件使用 >>>>

   For mybatis
      右击项目，run as-> maven build 在goals中输入命令：mybatis-generator:generate
   For idea
      直接点开maven projects窗口双击Plugins/mybatis-generator/mybatis-generator:generate

****************************************************************************************

初始化数据脚本，存放在djs-db/db下，请先按照连接池配置的url进行建库操作

****************************************************************************************
数据源的sql监控，黑名单、防sql注入、url访问次数等访问>>>>
ip：port/djs-app/druid

***********************************************************
部署请注意，由于采用springboot，当开发者使用方式为 java -jar 或者为 run app.java 请在pom中将
scope注释，此操作为idea中的识别bug，eclipse正常操作即可，部署到外部tomcat请将注释放开
<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <!--<scope>provided</scope>-->
</dependency>
****************************************************************************************





