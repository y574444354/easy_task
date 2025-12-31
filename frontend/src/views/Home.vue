<template>
  <div style="flex: 1; background: linear-gradient(180deg, #f0f5ff 0%, #f5f7fa 100%); display: flex; flex-direction: column; align-items: center; justify-content: center; position: relative; height: 100%;">

    <!-- 主标题 -->
    <h2 style="font-size: 32px; color: #333; margin-bottom: 30px; letter-spacing: 1px;">
      指令<span style="color: #6a5acd;">一键发</span>，任务<span style="color: #333;">全搞定</span>
    </h2>
    
    <!-- AI指令输入区域 -->
    <div style="width: 70%; background: #fff; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.08); padding: 20px; position: relative; margin-bottom: 40px;">
      <div style="display: flex; align-items: flex-start; gap: 10px;">
        <span style="font-size: 18px;">✨</span>
        <div style="flex: 1;">
          <textarea
            v-model="taskInput"
            @keydown.enter.prevent="handleSend"
            placeholder="请输入任务指令（如'创建部门满意度调研问卷，5月30日截止'），AI帮你全程搞定"
            style="width: 100%; min-height: 60px; max-height: 200px; border: none; outline: none; resize: vertical; font-size: 14px; line-height: 1.6; color: #333; background: transparent;"
            rows="2"
          ></textarea>
        </div>
      </div>
      <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 20px;">
        <div style="display: flex; gap: 10px;">
          <span style="background: #f5f5f5; padding: 4px 12px; border-radius: 16px; font-size: 12px; color: #666; cursor: pointer;" @click="handleQuickSelect('通用表单')">通用表单</span>
          <span style="background: #f5f5f5; padding: 4px 12px; border-radius: 16px; font-size: 12px; color: #666; cursor: pointer;" @click="handleQuickSelect('文件签署')">文件签署</span>
          <span style="background: #f5f5f5; padding: 4px 12px; border-radius: 16px; font-size: 12px; color: #666; cursor: pointer;" @click="handleQuickSelect('更多')">更多</span>
        </div>
        <button
          @click="handleSend"
          style="background: #6a5acd; color: white; border: none; width: 32px; height: 32px; border-radius: 8px; cursor: pointer; display: flex; align-items: center; justify-content: center; font-size: 16px;"
        >➤</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()

// 任务输入内容
const taskInput = ref('')

// 处理发送指令
const handleSend = () => {
  if (!taskInput.value.trim()) {
    return
  }
  
  console.log('发送任务指令:', taskInput.value)
  
  // TODO: 调用后端API处理任务指令
  // 这里暂时只在控制台输出，后续需要对接后端接口
  
  // 清空输入框（可选）
  // taskInput.value = ''
}

// 快捷场景选择
const handleQuickSelect = (type) => {
  const templates = {
    '通用表单': '创建部门满意度调研问卷',
    '文件签署': '创建文件签署收集任务',
    '更多': ''
  }
  
  if (templates[type]) {
    taskInput.value = templates[type]
  }
}
</script>

<style scoped>
.home-container {
  flex: 1;
  background: linear-gradient(180deg, #f0f5ff 0%, #f5f7fa 100%);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  position: relative;
  height: 100%;
}

/* 顶部用户信息区域 */
.user-info-bar {
  position: absolute;
  top: 20px;
  right: 30px;
  display: flex;
  align-items: center;
  gap: 20px;
  color: #666;
}

.notification-icon {
  position: relative;
  cursor: pointer;
  font-size: 16px;
}

.notification-badge {
  position: absolute;
  top: -5px;
  right: -5px;
  background: #ff4d4f;
  color: white;
  font-size: 10px;
  border-radius: 50%;
  padding: 0 4px;
  min-width: 18px;
  height: 18px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
}

.user-profile {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
}

.user-avatar {
  width: 32px;
  height: 32px;
  background: #ddd;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
}

.user-details {
  font-size: 12px;
  display: flex;
  flex-direction: column;
}

.user-name {
  color: #333;
}

.user-department {
  color: #999;
}

/* 主标题 */
.slogan-text {
  font-size: 32px;
  color: #333;
  margin-bottom: 30px;
  letter-spacing: 1px;
  font-weight: normal;
}

.slogan-highlight {
  color: #6a5acd;
}

.slogan-normal {
  color: #333;
}

/* AI指令输入区域 */
.ai-input-container {
  width: 70%;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.08);
  padding: 20px;
  position: relative;
  margin-bottom: 40px;
}

.input-content {
  display: flex;
  align-items: flex-start;
  gap: 10px;
}

.ai-icon {
  font-size: 18px;
}

.input-placeholder {
  flex: 1;
  color: #999;
  font-size: 14px;
  line-height: 1.6;
}

.input-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.quick-scenario-buttons {
  display: flex;
  gap: 10px;
}

.scenario-btn {
  background: #f5f5f5;
  padding: 4px 12px;
  border-radius: 16px;
  font-size: 12px;
  color: #666;
  cursor: pointer;
  transition: all 0.3s;
}

.scenario-btn:hover {
  background: #e8e8e8;
  color: #333;
}

.send-btn {
  width: 32px;
  height: 32px;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
}

/* 底部后续迭代提示卡片 */
.iteration-card {
  position: absolute;
  bottom: 40px;
  right: 40px;
  width: 200px;
  background: #e6f7ff;
  padding: 15px;
  border-radius: 8px;
  font-size: 12px;
  color: #333;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}
</style>