## 1. 目标
实现基于数据库的用户认证功能，包括用户表创建、默认用户初始化、密码加密存储和真实的登录逻辑。

## 2. 实施
- [ ] 2.1 数据库层： `backend/src/main/java/com/eztask/entity/User.java` ，创建用户实体类，包含id、username、password、email、nickname、status、createdAt、updatedAt字段，使用Lombok注解。
- [ ] 2.2 数据库层： `backend/src/main/java/com/eztask/mapper/UserMapper.java` ，创建MyBatis-Plus Mapper接口，继承BaseMapper<User>。
- [ ] 2.3 数据库初始化： `backend/src/main/resources/data.sql` ，创建SQL脚本，包含建表语句和插入默认用户admin（密码123456的BCrypt加密值）。
- [ ] 2.4 配置文件： `backend/src/main/resources/application.yml` ，在spring配置下添加`sql.init.mode: always`以启用SQL初始化脚本执行。
- [ ] 2.5 Service层： `backend/src/main/java/com/eztask/service/UserService.java` ，创建UserService接口，定义根据用户名查询用户的方法。
- [ ] 2.6 Service层： `backend/src/main/java/com/eztask/service/impl/UserServiceImpl.java` ，实现UserService，注入UserMapper，提供查询用户业务逻辑。
- [ ] 2.7 安全层： `backend/src/main/java/com/eztask/security/UserDetailsServiceImpl.java` ，修改loadUserByUsername方法，从数据库查询用户信息替代硬编码数据。
- [ ] 2.8 安全层： `backend/src/main/java/com/eztask/config/SecurityConfig.java` ，确保PasswordEncoder Bean已配置为BCryptPasswordEncoder。
- [ ] 2.9 Controller层： `backend/src/main/java/com/eztask/controller/AuthController.java` ，验证login和logout接口逻辑，确保与新的数据库用户认证集成正常。