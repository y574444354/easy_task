<template>
  <div style="width: 100%; height: 100vh; display: flex; flex-direction: column; font-family: 'PingFang SC', sans-serif; overflow: hidden; background: #fff;">
    <!-- 顶部导航栏（占满整个宽度） -->
    <div style="height: 60px; width: 100%; background: #fff; border-bottom: 1px solid #eee; display: flex; align-items: center; justify-content: space-between; padding: 0 20px; flex-shrink: 0; z-index: 100;">
      <!-- Logo区域 -->
      <div style="font-size: 18px; font-weight: bold; color: #333; display: flex; align-items: center;">
        <span style="background: #1890ff; color: white; padding: 2px 6px; border-radius: 4px; margin-right: 8px; font-size: 14px;">📂</span> 汇总易
      </div>

      <!-- 右侧操作区 -->
      <div style="display: flex; align-items: center;">
        <!-- 消息提醒 -->
        <div style="display: flex; align-items: center; margin-right: 20px; cursor: pointer; position: relative;">
          <span style="font-size: 20px;">🔔</span>
          <span style="position: absolute; top: -5px; right: -5px; background: #ff4d4f; color: white; font-size: 10px; padding: 2px 5px; border-radius: 10px; min-width: 18px; text-align: center;">12</span>
        </div>
        
        <!-- 用户信息下拉菜单 -->
        <div class="user-menu-container" style="position: relative;">
          <div
            @click="toggleUserMenu"
            style="display: flex; align-items: center; cursor: pointer; padding: 8px 12px; border-radius: 4px; transition: background 0.3s;"
            :style="userMenuVisible ? 'background: #f5f5f5;' : ''"
          >
            <!-- 用户头像 -->
            <div style="width: 32px; height: 32px; background: #ddd; border-radius: 50%; display: flex; align-items: center; justify-content: center; margin-right: 8px; font-size: 14px; color: #666;">
              {{ userStore.username ? userStore.username.charAt(0).toUpperCase() : 'U' }}
            </div>
            <!-- 用户名 -->
            <span style="font-size: 12px; color: #333; margin-right: 4px;">{{ userStore.username || '用户' }}</span>
            <span style="font-size: 10px; color: #999;">▼</span>
          </div>
          
          <!-- 下拉菜单 -->
          <div
            v-show="userMenuVisible"
            style="position: absolute; top: 100%; right: 0; margin-top: 8px; background: #fff; border: 1px solid #eee; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); min-width: 160px; z-index: 200;"
          >
            <div
              @click="navigateTo('/settings/profile')"
              style="padding: 12px 16px; font-size: 14px; color: #333; cursor: pointer; display: flex; align-items: center; transition: background 0.3s; border-radius: 8px 8px 0 0;"
              onmouseover="this.style.background='#f5f5f5'"
              onmouseout="this.style.background='#fff'"
            >
              <span style="margin-right: 8px;">👤</span>
              <span>个人信息</span>
            </div>
            <div
              @click="handleLogout"
              style="padding: 12px 16px; font-size: 14px; color: #ff4d4f; cursor: pointer; display: flex; align-items: center; transition: background 0.3s; border-radius: 0 0 8px 8px; border-top: 1px solid #f0f0f0;"
              onmouseover="this.style.background='#fff1f0'"
              onmouseout="this.style.background='#fff'"
            >
              <span style="margin-right: 8px;">🚪</span>
              <span>退出登录</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 下方主体区域（左侧菜单 + 右侧内容） -->
    <div style="flex: 1; display: flex; overflow: hidden;">
      <!-- 左侧固定菜单栏 -->
      <div style="width: 200px; background: #fff; border-right: 1px solid #f0f0f0; display: flex; flex-direction: column; overflow-y: auto; flex-shrink: 0;">
        <!-- 菜单列表 -->
        <div style="flex: 1;">
          <!-- 首页（一级菜单，可展开） -->
          <div class="menu-item" @click="toggleMenu('home')">
            <span class="menu-icon">🏠</span>
            <span class="menu-text">首页</span>
            <span :class="['arrow-icon', expandedMenus.home ? 'expanded' : '']">▼</span>
          </div>
          
          <!-- 首页子菜单 -->
          <div v-show="expandedMenus.home" class="submenu">
            <div
              :class="['submenu-item', activePath === '/home' ? 'submenu-item-active' : '']"
              @click="navigateTo('/home')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">AI任务创建</span>
            </div>
            <div
              :class="['submenu-item', activePath === '/my-tasks' ? 'submenu-item-active' : '']"
              @click="navigateTo('/my-tasks')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">我创建的</span>
            </div>
            <div
              :class="['submenu-item', activePath === '/assigned-tasks' ? 'submenu-item-active' : '']"
              @click="navigateTo('/assigned-tasks')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">分配给我的</span>
            </div>
          </div>

          <!-- 收集对象管理（一级菜单，可展开） -->
          <div class="menu-item" @click="toggleMenu('collectObjects')">
            <span class="menu-icon">👥</span>
            <span class="menu-text">收集对象管理</span>
            <span :class="['arrow-icon', expandedMenus.collectObjects ? 'expanded' : '']">▼</span>
          </div>
          
          <!-- 收集对象管理子菜单 -->
          <div v-show="expandedMenus.collectObjects" class="submenu">
            <div
              :class="['submenu-item', activePath === '/collect-objects/organizations' ? 'submenu-item-active' : '']"
              @click="navigateTo('/collect-objects/organizations')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">组织架构</span>
            </div>
            <div
              :class="['submenu-item', activePath === '/collect-objects/groups' ? 'submenu-item-active' : '']"
              @click="navigateTo('/collect-objects/groups')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">填报组管理</span>
            </div>
            <div
              :class="['submenu-item', activePath === '/collect-objects/contacts' ? 'submenu-item-active' : '']"
              @click="navigateTo('/collect-objects/contacts')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">联系人列表</span>
            </div>
          </div>

          <!-- 模板库（一级菜单，可展开） -->
          <div class="menu-item" @click="toggleMenu('templates')">
            <span class="menu-icon">📄</span>
            <span class="menu-text">模板库</span>
            <span :class="['arrow-icon', expandedMenus.templates ? 'expanded' : '']">▼</span>
          </div>
          
          <!-- 模板库子菜单 -->
          <div v-show="expandedMenus.templates" class="submenu">
            <div
              :class="['submenu-item', activePath === '/templates/task-templates' ? 'submenu-item-active' : '']"
              @click="navigateTo('/templates/task-templates')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">任务下发模板</span>
            </div>
            <div
              :class="['submenu-item', activePath === '/templates/report-templates' ? 'submenu-item-active' : '']"
              @click="navigateTo('/templates/report-templates')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">汇总报告模板</span>
            </div>
            <div
              :class="['submenu-item', activePath === '/templates/form-templates' ? 'submenu-item-active' : '']"
              @click="navigateTo('/templates/form-templates')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">表单模板库</span>
            </div>
          </div>

          <!-- 记忆中心（一级菜单，可展开） -->
          <div class="menu-item" @click="toggleMenu('memory')">
            <span class="menu-icon">🧠</span>
            <span class="menu-text">记忆中心</span>
            <span :class="['arrow-icon', expandedMenus.memory ? 'expanded' : '']">▼</span>
          </div>
          
          <!-- 记忆中心子菜单 -->
          <div v-show="expandedMenus.memory" class="submenu">
            <div
              :class="['submenu-item', activePath === '/memory/history' ? 'submenu-item-active' : '']"
              @click="navigateTo('/memory/history')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">历史记录</span>
            </div>
            <div
              :class="['submenu-item', activePath === '/memory/knowledge' ? 'submenu-item-active' : '']"
              @click="navigateTo('/memory/knowledge')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">知识库</span>
            </div>
            <div
              :class="['submenu-item', activePath === '/memory/favorites' ? 'submenu-item-active' : '']"
              @click="navigateTo('/memory/favorites')"
            >
              <span class="submenu-line"></span>
              <span class="submenu-text">收藏夹</span>
            </div>
          </div>

          <!-- 设置（一级菜单） -->
          <div
            :class="['menu-item', activePath === '/settings' ? 'menu-item-active' : '']"
            @click="navigateTo('/settings')"
          >
            <span class="menu-icon">⚙️</span>
            <span class="menu-text">设置</span>
          </div>
        </div>
      </div>

      <!-- 右侧内容区 -->
      <div style="flex: 1; background: linear-gradient(180deg, #f0f5ff 0%, #f5f7fa 100%); overflow-y: auto;">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted, onUnmounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { ElMessage, ElMessageBox } from 'element-plus'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

// 当前激活的路径
const activePath = computed(() => route.path)

// 展开的菜单项
const expandedMenus = ref({
  home: true,
  collectObjects: false,
  templates: false,
  memory: false,
  settings: false
})

// 用户菜单显示状态
const userMenuVisible = ref(false)

// 切换菜单展开/收起
const toggleMenu = (menuName) => {
  expandedMenus.value[menuName] = !expandedMenus.value[menuName]
}

// 切换用户菜单显示/隐藏
const toggleUserMenu = () => {
  userMenuVisible.value = !userMenuVisible.value
}

// 点击其他区域关闭用户菜单
const handleClickOutside = (event) => {
  if (!event.target.closest('.user-menu-container')) {
    userMenuVisible.value = false
  }
}

// 导航到指定路径
const navigateTo = (path) => {
  userMenuVisible.value = false
  router.push(path)
}

// 退出登录
const handleLogout = async () => {
  userMenuVisible.value = false
  try {
    await ElMessageBox.confirm('确定要退出登录吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await userStore.logout()
    ElMessage.success('退出登录成功')
    router.push({ name: 'Login' })
  } catch (error) {
    // 用户取消操作
  }
}

// 组件挂载时添加点击外部关闭事件
onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

// 组件卸载时移除点击外部关闭事件
onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside)
})

// 监听路由变化，自动展开对应的父菜单
watch(activePath, (newPath) => {
  if (newPath === '/home' || newPath === '/my-tasks' || newPath === '/assigned-tasks') {
    expandedMenus.value.home = true
  } else if (newPath.startsWith('/collect-objects')) {
    expandedMenus.value.collectObjects = true
  } else if (newPath.startsWith('/templates')) {
    expandedMenus.value.templates = true
  } else if (newPath.startsWith('/memory')) {
    expandedMenus.value.memory = true
  } else if (newPath.startsWith('/settings')) {
    expandedMenus.value.settings = true
  }
}, { immediate: true })
</script>

<style scoped>
/* 侧边栏一级菜单项通用样式 */
.menu-item {
  height: 40px;
  padding: 0 20px;
  margin: 2px 0;
  font-size: 14px;
  color: #666;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: space-between;
  transition: all 0.3s;
  border-right: 3px solid transparent;
  position: relative;
}

.menu-item:hover {
  background: #e6f7ff;
  color: #1890ff;
}

.menu-item-active {
  height: 40px;
  padding: 0 20px;
  margin: 2px 0;
  font-size: 14px;
  color: #1890ff;
  background: #e6f7ff;
  border-right: 3px solid #1890ff;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: space-between;
  transition: all 0.3s;
  position: relative;
}

.menu-icon {
  margin-right: 8px;
  font-size: 16px;
}

.menu-text {
  flex: 1;
}

.arrow-icon {
  font-size: 10px;
  color: #999;
  transition: transform 0.3s;
  transform: rotate(-90deg);
}

.arrow-icon.expanded {
  transform: rotate(0deg);
}

/* 子菜单容器 */
.submenu {
  background: #fafafa;
  border-top: 1px solid #f0f0f0;
  border-bottom: 1px solid #f0f0f0;
}

/* 子菜单项样式 */
.submenu-item {
  height: 36px;
  padding: 0 20px;
  margin: 1px 0;
  font-size: 13px;
  color: #666;
  cursor: pointer;
  display: flex;
  align-items: center;
  transition: all 0.3s;
  border-right: 3px solid transparent;
  position: relative;
}

.submenu-item:hover {
  background: #e6f7ff;
  color: #1890ff;
}

.submenu-item-active {
  height: 36px;
  padding: 0 20px;
  margin: 1px 0;
  font-size: 13px;
  color: #1890ff;
  background: #e6f7ff;
  border-right: 3px solid #1890ff;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  transition: all 0.3s;
  position: relative;
}

/* 子菜单层级线 */
.submenu-line {
  width: 2px;
  height: 12px;
  background: #d9d9d9;
  margin-right: 12px;
  border-radius: 1px;
}

.submenu-item-active .submenu-line {
  background: #1890ff;
}

.submenu-text {
  flex: 1;
}

/* 滚动条样式优化 */
.menu-item::-webkit-scrollbar,
.submenu::-webkit-scrollbar {
  width: 4px;
}

.menu-item::-webkit-scrollbar-thumb,
.submenu::-webkit-scrollbar-thumb {
  background: #d9d9d9;
  border-radius: 2px;
}

.menu-item::-webkit-scrollbar-track,
.submenu::-webkit-scrollbar-track {
  background: transparent;
}

/* 用户下拉菜单样式 */
.user-dropdown-item {
  padding: 12px 16px;
  font-size: 14px;
  cursor: pointer;
  display: flex;
  align-items: center;
  transition: all 0.3s;
}

.user-dropdown-item:hover {
  background: #f5f5f5;
}

.user-dropdown-item-danger:hover {
  background: #fff1f0;
}
</style>