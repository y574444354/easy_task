<template>
  <router-view />
</template>

<script setup>
</script>

<style>
#app {
  width: 100%;
  height: 100vh;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'PingFang SC', 'Helvetica Neue', Helvetica, 'Hiragino Sans GB',
    'Microsoft YaHei', '微软雅黑', Arial, sans-serif;
}

/* 全局通用样式 - 基础控件 */
/* 主按钮（蓝色） */
.btn-primary {
  padding: 4px 12px;
  border-radius: 8px;
  background: #1890ff;
  color: #fff;
  border: none;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.3s;
}

.btn-primary:hover {
  background: #40a9ff;
}

.btn-primary-purple {
  padding: 4px 12px;
  border-radius: 8px;
  background: #6a5acd;
  color: #fff;
  border: none;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.3s;
}

.btn-primary-purple:hover {
  background: #7b68ee;
}

/* 次按钮（灰色） */
.btn-secondary {
  padding: 4px 12px;
  border-radius: 4px;
  background: #f5f5f5;
  color: #666;
  border: 1px solid #ddd;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s;
}

.btn-secondary:hover {
  background: #e8e8e8;
  border-color: #bbb;
}

/* 图标按钮 */
.btn-icon {
  width: 24px;
  height: 24px;
  border-radius: 4px;
  background: transparent;
  border: none;
  cursor: pointer;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background 0.3s;
}

.btn-icon:hover {
  background: #eee;
}

.btn-icon-lg {
  width: 32px;
  height: 32px;
  border-radius: 4px;
  background: transparent;
  border: none;
  cursor: pointer;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background 0.3s;
}

.btn-icon-lg:hover {
  background: #eee;
}

/* 文本输入框 */
.input-text {
  height: 32px;
  padding: 4px 8px;
  border-radius: 4px;
  border: 1px solid #ddd;
  font-size: 14px;
  outline: none;
  transition: border-color 0.3s;
}

.input-text:focus {
  border-color: #1890ff;
}

/* 富文本输入框 */
.input-rich {
  min-height: 80px;
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ddd;
  font-size: 14px;
  outline: none;
  transition: border-color 0.3s;
  resize: vertical;
}

.input-rich:focus {
  border-color: #1890ff;
}

/* 下拉筛选框 */
.select-filter {
  height: 32px;
  padding: 4px 8px;
  border-radius: 4px;
  border: 1px solid #ddd;
  font-size: 14px;
  outline: none;
  cursor: pointer;
  transition: border-color 0.3s;
}

.select-filter:focus {
  border-color: #1890ff;
}

/* 状态标签 */
.status-tag {
  padding: 4px 12px;
  border-radius: 16px;
  font-size: 12px;
  display: inline-block;
}

.status-tag-blue {
  background: #e6f7ff;
  color: #1890ff;
}

.status-tag-green {
  background: #f6ffed;
  color: #52c41a;
}

.status-tag-gray {
  background: #f5f5f5;
  color: #999;
}

.status-tag-red {
  background: #fff1f0;
  color: #ff4d4f;
}

/* Toast提示 */
.toast-message {
  padding: 10px 20px;
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  font-size: 14px;
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  z-index: 1001;
}

/* 居中弹窗 */
.modal-dialog {
  width: 50%;
  max-width: 60%;
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;
}

.modal-header {
  padding: 20px;
  border-bottom: 1px solid #f0f0f0;
  font-size: 16px;
  font-weight: bold;
}

.modal-body {
  padding: 20px;
}

.modal-footer {
  padding: 20px;
  border-top: 1px solid #f0f0f0;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

/* 右侧抽屉弹窗 */
.drawer-right {
  width: 80%;
  max-width: 90%;
  height: 100vh;
  background: #fff;
  box-shadow: -4px 0 12px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  right: 0;
  z-index: 1000;
  display: flex;
  flex-direction: column;
}

.drawer-header {
  padding: 20px;
  border-bottom: 1px solid #f0f0f0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}

.drawer-body {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.drawer-footer {
  padding: 20px;
  border-top: 1px solid #f0f0f0;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

/* 遮罩层 */
.modal-mask {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 999;
}
</style>