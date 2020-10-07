import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Classes from '@/components/Classes'
import PickOne from '@/components/PickOne'
import Students from '@/components/Students'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/classes',
      name: 'classes',
      component: Classes
    },
    {
      path: '/pick',
      name: 'pick',
      component: PickOne
    },
    {
      path: '/students',
      name: 'students',
      component: Students
    }
  ]
})
