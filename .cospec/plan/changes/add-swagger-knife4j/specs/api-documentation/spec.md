## ADDED Requirements
### Requirement: API 文档访问
系统应提供基于 Knife4j 的 API 文档界面，开发人员可通过浏览器访问文档并进行接口测试。

#### Scenario: 开发环境访问 Swagger 文档
- **WHEN** 用户在开发环境访问 `/api/doc.html` 路径
- **THEN** 显示 Knife4j 文档界面，包含所有 API 接口信息

#### Scenario: 生产环境禁用 Swagger 文档
- **WHEN** 应用在生产环境运行
- **THEN** Swagger 文档功能处于禁用状态，访问 `/api/doc.html` 返回 404

#### Scenario: Swagger 路径匿名访问
- **WHEN** 用户访问 Swagger 相关路径（`/doc.html`, `/swagger-resources/**`, `/v2/api-docs/**`, `/webjars/**`）
- **THEN** 无需认证即可访问这些路径

### Requirement: API 接口文档注解
所有 Controller 类应包含 Swagger 注解，提供清晰的接口文档信息。

#### Scenario: Controller 添加 Swagger 注解
- **WHEN** 开发人员在 Controller 类上添加 `@Api` 注解
- **THEN** Swagger 文档显示该 Controller 的描述信息

#### Scenario: 接口方法添加 Swagger 注解
- **WHEN** 开发人员在接口方法上添加 `@ApiOperation` 注解
- **THEN** Swagger 文档显示该接口的详细描述、参数和返回值信息