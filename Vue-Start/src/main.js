//作为入口js文件
// 使用es6的语法导入js模块
import Vue from '../node_modules/vue/dist/vue';
import VueRouter from '../node_modules/vue-router/dist/vue-router'
import loginForm from './js/login'
import regForm from './js/reg'

//引入css:需要先安装加载器
import './css/index.css'

Vue.use(VueRouter)   //使用VueRouter模块

// 创建VueRouter对象
const router = new VueRouter({
    routes:[ // 编写多个路由规则
        {
            path:"/login", // 请求路径
            component:loginForm // 组件名称
        },
        {path:"/reg",component:regForm},
    ]
})
const vm = new Vue({
    el:"#app",
    components:{// 引用登录和注册组件
        loginForm,
        regForm
    },
    router  //引入路由组件
})