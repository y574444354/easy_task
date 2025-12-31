<template>
  <div class="home-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card class="welcome-card">
          <template #header>
            <div class="card-header">
              <span>欢迎使用 EasyTask 任务管理系统</span>
            </div>
          </template>
          <div class="welcome-content">
            <p>这是一个基于 Vue3 + SpringBoot 的前后端分离任务管理系统。</p>
            <p>系统已集成以下技术和功能：</p>
            <ul>
              <li>Vue3 + Vite + Element Plus 前端框架</li>
              <li>SpringBoot 2.7 + Spring Security + JPA 后端框架</li>
              <li>JWT 无状态认证授权</li>
              <li>Swagger + Knife4j API 文档</li>
              <li>PostgreSQL 数据库</li>
              <li>AI API 配置支持</li>
            </ul>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <el-icon class="stat-icon" color="#409eff"><Document /></el-icon>
            <div class="stat-info">
              <div class="stat-value">0</div>
              <div class="stat-label">任务总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <el-icon class="stat-icon" color="#67c23a"><CircleCheck /></el-icon>
            <div class="stat-info">
              <div class="stat-value">0</div>
              <div class="stat-label">已完成</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <el-icon class="stat-icon" color="#e6a23c"><Clock /></el-icon>
            <div class="stat-info">
              <div class="stat-value">0</div>
              <div class="stat-label">进行中</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="24">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>快速开始</span>
            </div>
          </template>
          <div class="quick-start">
            <el-button type="primary" @click="goToTaskList">
              <el-icon><List /></el-icon>
              查看任务列表
            </el-button>
            <el-button type="success" @click="createTask">
              <el-icon><Plus /></el-icon>
              创建新任务
            </el-button>
            <el-button type="info" @click="viewApiDoc">
              <el-icon><Document /></el-icon>
              查看 API 文档
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goToTaskList = () => {
  router.push('/task/list')
}

const createTask = () => {
  router.push('/task/create')
}

const viewApiDoc = () => {
  window.open('http://localhost:8080/doc.html', '_blank')
}
</script>

<style scoped>
.home-container {
  padding: 20px;
}

.card-header {
  font-size: 18px;
  font-weight: bold;
  color: #333;
}

.welcome-card {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.welcome-content {
  line-height: 2;
  color: #606266;
}

.welcome-content ul {
  padding-left: 20px;
}

.stat-card {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: all 0.3s;
}

.stat-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 20px 0 rgba(0, 0, 0, 0.15);
}

.stat-content {
  display: flex;
  align-items: center;
  padding: 10px 0;
}

.stat-icon {
  font-size: 48px;
  margin-right: 20px;
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 32px;
  font-weight: bold;
  color: #303133;
}

.stat-label {
  font-size: 14px;
  color: #909399;
  margin-top: 5px;
}

.quick-start {
  display: flex;
  gap: 15px;
  justify-content: center;
  padding: 20px 0;
}

.quick-start .el-button {
  padding: 12px 24px;
  font-size: 16px;
}
</style>