## 1. 目标
为项目添加 Swagger + Knife4j API 文档功能，支持开发和测试环境访问 API 文档并进行接口测试。

## 2. 实施
- [ ] 2.1 后端：`backend/pom.xml`，添加 Knife4j 依赖（版本 3.0.3，兼容 Spring Boot 2.7.18）。
- [ ] 2.2 后端：`backend/src/main/java/com/eztask/config/SwaggerConfig.java`，创建 Swagger 配置类，配置 API 文档基本信息和分组。
- [ ] 2.3 后端：`backend/src/main/java/com/eztask/config/SecurityConfig.java`，在 `antMatchers` 中添加 Swagger 相关路径的匿名访问权限。
- [ ] 2.4 后端：`backend/src/main/resources/application-dev.yml` 和 `backend/src/main/resources/application-test.yml`，添加 `knife4j.enabled: true` 配置。
- [ ] 2.5 后端：`backend/src/main/resources/application-prod.yml`，添加 `knife4j.enabled: false` 配置。
- [ ] 2.6 后端：`backend/src/main/java/com/eztask/controller/AuthController.java`，添加 `@Api` 和 `@ApiOperation` 注解。