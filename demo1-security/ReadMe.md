### 使用自定義實現類形式去設置。
spring security在读取配置中是否有用户名和密码设置，如果有的话就去使用，如果没有配置则会去UserDetailsService接口查找。

- 创建一个配置类，设置使用哪个userDetailsservice实现类。
- 编写实现类（配置或者查数据库），返回User对象，User对象有用户名密码和操作权限。


