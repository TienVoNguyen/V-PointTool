import Vue from "vue";
import Router from "vue-router";
import UserVpoint from "@/views/UserVpoint";
import Login from "@/views/Login";
import Profile from "@/views/Profile";
import VueRouter from "vue-router";
import DetailPoint from "@/views/DetailPoint";
import AdminSeeDetail from "@/views/AdminSeeDetail";
import AdminSeeDetailVpoint from "@/views/AdminSeeDetailVpoint";
import UserManager from "@/views/UserManager";

Vue.use(Router);

const routes = [
    {
        path: '/',
        name: 'UserVpoint',
        component: Login
    },
    {
        path: '/login',
        name: 'home',
        component: Login
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile
    },
    {
        path: '/UserVpoint',
        name: 'UserVpoint',
        component: UserVpoint
    },
    {
        path: '/AdminSeeDetail/:idUser',
        name: 'AdminSeeDetail',
        component: AdminSeeDetail
    },
    {
        path: '/api/mark/:year/:month',
        name: 'DetailPoint',
        component: DetailPoint
    },
    {
        path: '/AdminSeeDetailVPoint/:year/:month/:idUser',
        name: 'AdminSeeDetailVpoint',
        component: AdminSeeDetailVpoint
    },
    {
        path: '/manager',
        name: 'UserManager',
        component: UserManager
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/AboutView.vue')
    },
    {
        path: '/importmark',
        alias: '/import',
        name: 'importmarkexcel',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../components/ImportExcel')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
