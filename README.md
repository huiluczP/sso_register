# sso_register
SSO center achieved by Springboot.

Springboot实现的基于登陆中心的SSO单点登录系统的登陆中心部分。</br>
client部分：[https://github.com/huiluczP/sso_client](https://github.com/huiluczP/sso_client)</br>
项目简介：[基于登录中心的跨域SSO实现](https://blog.csdn.net/qq_41733192/article/details/124652716)

SSO登陆中心功能：
1.	登录页面显示
2.	登录成功的判断
3.	登陆成功后的页面跳转
4.	Token的redis存储
5.	Token的有效性判断
6.	登出时的token处理

访问时序图：</br>
![image](https://user-images.githubusercontent.com/36394708/167293384-c7af88d0-7beb-4094-a35a-65e5a4d4b1ee.png)

演示：</br>
![login](https://user-images.githubusercontent.com/36394708/167294633-f0ee1f83-553d-4cfa-bc11-77a6a99ca5b8.gif)


