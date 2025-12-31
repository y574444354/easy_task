# 变更：搭建前后端分离技术框架

## 原因
项目需要建立完整的前后端分离技术框架，包括Vue3前端、SpringBoot后端、PostgreSQL数据库及AI API配置能力，以支持后续业务功能开发。

## 变更内容
- 初始化Vue3 + Vite + Element Plus前端项目
- 初始化SpringBoot 2.7后端项目
- 集成PostgreSQL数据库连接
- 实现JWT用户认证鉴权
- 配置AI API相关参数（通过application.yml）
- 集成MyBatis-Plus持久层框架
- 实现全局异常处理
- 实现统一日志管理
- 配置前后端跨域支持

## 影响
- 受影响的规范：项目初始化、认证授权、配置管理
- 受影响的代码：
    - `frontend/`: 前端项目根目录，包含Vue3应用完整结构
    - `backend/`: 后端项目根目录，包含SpringBoot应用完整结构
    - `backend/src/main/resources/application.yml`: 数据库、AI API、JWT、MyBatis-Plus等配置
    - `backend/src/main/java/.../config/`: 跨域、异常处理等配置类
    - `backend/src/main/java/.../security/`: JWT认证相关实现
    - `backend/pom.xml`: 添加MyBatis-Plus依赖