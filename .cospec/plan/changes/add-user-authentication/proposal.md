# 变更：实现用户认证功能

## 原因
当前系统使用硬编码的用户数据进行测试，无法满足实际业务需求。需要建立真实的用户管理体系，支持用户表存储、密码加密验证和初始化数据。

## 变更内容
- 创建用户表（sys_user），包含扩展字段
- 实现基于数据库的用户认证逻辑
- 添加SQL初始化脚本，自动创建默认用户（admin/123456）
- 密码使用BCrypt加密存储
- 修改UserDetailsServiceImpl从数据库加载用户信息
- 完善登录和退出登录功能

## 影响
- 受影响的规范：用户管理
- 受影响的代码：
    - `backend/src/main/java/com/eztask/security/UserDetailsServiceImpl.java`: 从数据库查询用户替代硬编码。
    - `backend/src/main/resources/application.yml`: 添加SQL初始化配置。
    - `backend/src/main/resources/data.sql`: 新增用户表创建和初始化脚本。
    - `backend/src/main/java/com/eztask/entity/User.java`: 新增用户实体类。
    - `backend/src/main/java/com/eztask/mapper/UserMapper.java`: 新增用户Mapper接口。