# EasyTask 前后端分离项目

基于 Vue3 + SpringBoot 的前后端分离任务管理系统。

## 技术栈

### 后端
- Spring Boot 2.7.18
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Swagger 2.9.2 + Knife4j 3.0.3
- Lombok

### 前端
- Vue 3
- Vite
- Element Plus
- Vue Router
- Pinia
- Axios

## 项目结构

```
easy_task/
├── backend/                 # 后端项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/        # Java源码
│   │   │   │   └── com/eztask/
│   │   │   │       ├── config/       # 配置类
│   │   │   │       ├── controller/   # 控制器
│   │   │   │       ├── dto/          # 数据传输对象
│   │   │   │       ├── exception/     # 异常处理
│   │   │   │       ├── security/     # 安全认证
│   │   │   │       └── util/         # 工具类
│   │   │   └── resources/
│   │   │       ├── application.yml          # 主配置文件
│   │   │       ├── application-dev.yml       # 开发环境配置
│   │   │       ├── application-prod.yml      # 生产环境配置
│   │   │       └── logback-spring.xml        # 日志配置
│   │   └── pom.xml
│
└── frontend/                # 前端项目
    ├── src/
    │   ├── api/           # API接口
    │   ├── router/        # 路由配置
    │   ├── stores/        # 状态管理
    │   ├── utils/         # 工具函数
    │   ├── views/         # 页面组件
    │   ├── App.vue        # 根组件
    │   └── main.js        # 入口文件
    ├── index.html
    ├── package.json
    └── vite.config.js
```

## 快速开始

### 环境要求

- JDK 11+
- Node.js 16+
- PostgreSQL 12+
- Maven 3.6+

### 后端启动

1. 配置数据库连接（修改 `backend/src/main/resources/application-dev.yml`）：
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/easytask_dev
    username: postgres
    password: postgres
```

2. 启动后端服务：
```bash
cd backend
mvn spring-boot:run
```

3. 访问API文档：
- Swagger UI: http://localhost:8080/api/swagger-ui.html
- Knife4j UI: http://localhost:8080/api/doc.html

### 前端启动

1. 安装依赖：
```bash
cd frontend
npm install
```

2. 启动开发服务器：
```bash
npm run dev
```

3. 访问前端应用：http://localhost:3000

## 测试账号

- 管理员：admin / password
- 普通用户：user / password

## 主要功能

### 已实现功能

- ✅ 用户登录/登出（JWT认证）
- ✅ 跨域配置
- ✅ 全局异常处理
- ✅ 统一日志管理
- ✅ Swagger + Knife4j API文档
- ✅ 前端基础布局
- ✅ 路由守卫
- ✅ Axios请求拦截器
- ✅ Pinia状态管理
- ✅ Element Plus UI框架集成

### 待开发功能

- 🔲 用户管理
- 🔲 任务管理
- 🔲 项目管理
- 🔲 AI功能集成

## API文档

启动后端服务后，可以通过以下地址访问API文档：

- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **Knife4j UI**: http://localhost:8080/api/doc.html

Knife4j提供了增强的API文档界面，支持：
- 接口分组管理
- 在线调试
- 离线文档导出
- 全局参数配置

## 配置说明

### 后端配置

主要配置文件：`backend/src/main/resources/application.yml`

- JWT配置：密钥、过期时间
- 数据库配置：连接URL、用户名、密码
- AI API配置：基础URL、API密钥、超时时间
- Swagger配置：启用/禁用、文档信息

### 前端配置

环境变量文件：
- `.env.development`: 开发环境配置
- `.env.production`: 生产环境配置

配置项：
- `VITE_APP_TITLE`: 应用标题
- `VITE_APP_API_BASE_URL`: API基础路径
- `VITE_APP_API_TIMEOUT`: 请求超时时间

## 开发规范

### 后端

- 使用RESTful API设计
- 统一异常处理
- 使用Swagger注解标注接口
- 遵循分层架构：Controller -> Service -> Repository

### 前端

- 使用Vue 3 Composition API
- 组件化开发
- 统一的API调用封装
- 使用Pinia进行状态管理

## 构建部署

### 后端打包

```bash
cd backend
mvn clean package
java -jar target/easytask-backend-1.0.0.jar --spring.profiles.active=prod
```

### 前端打包

```bash
cd frontend
npm run build
```

打包后的文件在 `frontend/dist` 目录，可部署到Nginx等Web服务器。

## 许可证

MIT License