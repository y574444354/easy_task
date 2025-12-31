import { defineStore } from 'pinia'
import { login, logout } from '@/api/auth'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    username: localStorage.getItem('username') || ''
  }),

  getters: {
    isLoggedIn: (state) => !!state.token
  },

  actions: {
    setToken(token) {
      this.token = token
      localStorage.setItem('token', token)
    },

    setUsername(username) {
      this.username = username
      localStorage.setItem('username', username)
    },

    getToken() {
      return this.token
    },

    getUsername() {
      return this.username
    },

    async login(loginData) {
      try {
        const response = await login(loginData)
        this.setToken(response.token)
        this.setUsername(response.username)
        return response
      } catch (error) {
        throw error
      }
    },

    async logout() {
      try {
        await logout()
      } catch (error) {
        console.error('登出失败:', error)
      } finally {
        this.token = ''
        this.username = ''
        localStorage.removeItem('token')
        localStorage.removeItem('username')
      }
    }
  }
})