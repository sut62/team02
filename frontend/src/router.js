import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App'
import RateUP from './components/RateUP'

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes:[
        {
            path: '',
            component: App,
            children:[
                {
                    path: '',
                    redirect: 'rates'
                },
                {
                    path: 'rates',
                    component: RateUP,
                },
            ]

        }
    ] 

})
export default router;