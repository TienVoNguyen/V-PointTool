import Vue from 'vue';
import App from './App.vue';
import router from './commons/router';
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/en';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import store from './store'
import 'element-ui/lib/theme-chalk/index.css';
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(ElementUI, { locale });
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
