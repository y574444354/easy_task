## ADDED Requirements

### Requirement: 用户表管理
系统应提供用户表用于存储用户信息，支持用户认证和授权。

#### Scenario: 创建用户表
- **WHEN** 应用启动时
- **THEN** 系统自动创建sys_user表，包含id、username、password、email、nickname、status、created_at、updated_at字段

#### Scenario: 初始化默认用户
- **WHEN** 应用首次启动或表为空时
- **THEN** 系统自动插入默认用户admin，密码为123456的BCrypt加密值

### Requirement: 用户认证
系统应支持基于数据库的用户认证，验证用户名和密码。

#### Scenario: 用户登录成功
- **WHEN** 用户提供正确的用户名admin和密码123456
- **THEN** 系统返回JWT令牌和用户信息

#### Scenario: 用户登录失败-密码错误
- **WHEN** 用户提供正确的用户名admin但密码错误
- **THEN** 系统返回401错误，提示用户名或密码错误

#### Scenario: 用户登录失败-用户不存在
- **WHEN** 用户提供不存在的用户名
- **THEN** 系统返回401错误，提示用户名或密码错误

### Requirement: 用户登出
系统应支持用户登出功能。

#### Scenario: 用户登出成功
- **WHEN** 用户调用登出接口
- **THEN** 系统返回登出成功消息
- **AND** 客户端应删除存储的JWT令牌

### Requirement: 密码加密
系统应使用BCrypt算法对用户密码进行加密存储。

#### Scenario: 密码加密存储
- **WHEN** 创建新用户或更新用户密码
- **THEN** 系统使用BCryptPasswordEncoder对密码进行加密
- **AND** 存储加密后的密码到数据库