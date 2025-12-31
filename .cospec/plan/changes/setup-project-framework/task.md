## 1. 目标
搭建完整的前后端分离技术框架，包括Vue3前端、SpringBoot后端、PostgreSQL数据库、JWT认证、AI API配置及基础功能（Swagger、日志、异常处理、跨域）。

## 2. 实施
- [ ] 2.1 后端项目初始化：`backend/`，创建Maven项目结构，配置pom.xml依赖（SpringBoot 2.7、PostgreSQL Driver、Spring Security、MyBatis-Plus、Lombok），创建主启动类`EasyTaskApplication.java`，注意Spring Boot版本和JDK兼容性。
- [ ] 2.2 后端基础配置：`backend/src/main/resources/`，创建application.yml、application-dev.yml、application-prod.yml，配置数据库连接（HikariCP）、MyBatis-Plus参数、profile环境切换，注意数据库连接字符串和驱动类名。
- [ ] 2.3 JWT认证实现：`backend/src/main/java/.../security/`，创建JwtConfig配置类、JwtTokenUtil工具类、JwtAuthenticationFilter过滤器、SecurityConfig安全配置类，登录接口`AuthController`，注意JWT密钥和过期时间配置。
- [ ] 2.4 AI API配置：`backend/src/main/resources/application.yml`，配置ai.api.base-url、ai.api.key、ai.api.timeout等参数；`backend/src/main/java/.../config/`，创建AiClientConfig配置类，注意敏感信息安全。
- [ ] 2.5 后端基础功能配置：`backend/src/main/java/.../config/`，创建CorsConfig跨域配置、GlobalExceptionHandler全局异常处理、Logback日志配置，注意CORS允许的域名和方法。
- [ ] 2.6 前端项目初始化：`frontend/`，使用Vite创建Vue3项目，配置package.json（vue、vue-router、pinia、axios、element-plus），创建src标准目录结构，注意Node.js版本兼容。
- [ ] 2.7 前端基础配置：`frontend/`，配置vite.config.js（路径别名、代理）、环境变量文件（.env.development、.env.production）、router路由配置、pinia状态管理、axios请求拦截器（JWT令牌注入），注意代理配置指向后端地址。
- [ ] 2.8 前端UI框架：`frontend/src/`，集成Element Plus，创建基础布局组件、登录页面、主页示例，配置主题色和国际化，注意按需引入优化打包体积。
