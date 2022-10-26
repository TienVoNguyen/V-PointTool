import Vue from "vue";
import Router from "vue-router";
import AdminLayout from "@/components/admin/layout/AdminLayout";
import UserLayout from "@/components/user/layout/UserLayout";

Vue.use(Router);

const router = new Router({
    mode: "history",
    routes: [
        {
            path: '/login',
            component: () => import("@/components/auth/Login"),
        },
        {
            path: '/admin',
            redirect: 'home',
            component: AdminLayout,
            children: [
                {
                    path: 'home',
                    name: 'home',
                    component: () => import("@/components/admin/Home"),
                },
                {
                    path: 'importexcel',
                    name: 'importexcel',
                    component: () => import("@/components/admin/ImportExcel"),
                },
                {
                    path: 'detail/:idUser',
                    name: 'detail',
                    component: () => import("@/components/admin/Detail"),
                },
                {
                    path: 'AdminSeeDetailVPoint/:year/:month/:idUser',
                    name: 'DetailPoint',
                    component: () => import("@/components/admin/DetailPoint"),
                },
                {
                    path: 'manager',
                    name: 'UserManager',
                    component: () => import("@/components/admin/UserManager")
                }
            ]
        },
        {
            path: '/user',
            redirect: 'home',
            component: UserLayout,
            children: [
                {
                    path: 'home',
                    name: 'UserHome',
                    component: () => import("@/components/user/Home")
                },
                {
                    path: 'api/mark/:year/:month',
                    name: 'UserDetailPoint',
                    component: () => import("@/components/user/DetailPoint"),
                },
            ]
        }
    ]
});
router.beforeEach((to, from, next) => {
    const publicPages = ['/login'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');
    console.log(loggedIn)
    if (authRequired && !loggedIn) {
        next('/login');
    } else {
        next();
    }
});
export default router