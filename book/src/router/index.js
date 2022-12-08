import Vue from 'vue'
import VueRouter from 'vue-router'
import LogIn from "@/views/LogIn";
import HomePage from "@/views/HomePage";
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomePage,
        redirect: '/login',
        children:[
            {path:'/index',component:()=>import('@/views/IndexBox')},
            {path:'/SearchBook',component:()=>import('@/views/SearchBook')},
            {path:'/UserServe',component:()=>import('@/views/UserServe')},
            {path:'/BookServe',component:()=>import('@/views/BookServe')},
            {path:'about',component:()=>import('@/views/AboutPage')},
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: LogIn,
    },
    {
        path:'/signup',
        name:'signup',
        component:()=>import('@/views/SignUp')
    }
]

const router = new VueRouter({
    mode: 'hash',
    routes
})

export default router
