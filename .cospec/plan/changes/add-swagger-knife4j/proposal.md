# 变更：添加 Swagger + Knife4j API 文档

## 原因
项目需要提供 API 文档功能，方便开发和测试人员查看和测试 REST 接口。当前项目缺少 API 文档支持。

## 变更内容
- 在 `pom.xml` 中添加 Knife4j 依赖（兼容 Spring Boot 2.7.18）
- 创建 Swagger 配置类 `SwaggerConfig`
- 在 `SecurityConfig` 中配置 Swagger 相关路径的访问权限
- 在配置文件中添加 Swagger 开关配置（仅开发/测试环境启用）
- 为现有 Controller 添加 Swagger 注解

## 影响
- **受影响的规范**：API 文档
- **受影响的代码**：
    - `backend/pom.xml`: 添加 Knife4j 依赖。
    - `backend/src/main/java/com/eztask/config/SecurityConfig.java`: 允许 Swagger 路径匿名访问。
    - `backend/src/main/resources/application-dev.yml`: 启用 Swagger。
    - `backend/src/main/resources/application-prod.yml`: 禁用 Swagger。
    - `backend/src/main/java/com/eztask/controller/AuthController.java`: 添加 Swagger 注解。