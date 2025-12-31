-- 创建用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    nickname VARCHAR(50),
    status INTEGER DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted INTEGER DEFAULT 0
);

-- 插入默认管理员用户（用户名：admin，密码：123456）
-- BCrypt加密后的密码值
INSERT INTO sys_user (username, password, email, nickname, status, deleted)
VALUES ('admin', '$2a$10$VlXZapEBZ2BsbQe1q0gL5uOztgD.wb4u9znWXcS4Hiok9HtDYzK1.', 'admin@eztask.com', '系统管理员', 1, 0)
ON CONFLICT (username) DO NOTHING;