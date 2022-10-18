import Vue from "vue";
import Router from "vue-router";
import UserVpoint from "@/views/UserVpoint";
import Login from "@/views/Login";
import Profile from "@/views/Profile";
import VueRouter from "vue-router";

Vue.use(Router);

const routes = [
    {
        path: '/',
        name: 'UserVpoint',
        component: UserVpoint
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
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
