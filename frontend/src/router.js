import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App'
import RateUP from './components/RateUP'
import ViewRatingUP from './components/ViewRatingUP'

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes:[
        {
            path: '',
            component: App,
            children: [
                {
                    path: '',
                    redirect: 'RateUP'
                },
                {
                    path: 'RateUP',
                    component: RateUP
                },
                {
                    path: 'viewrateup',
                    component: ViewRatingUP
                },
            ]

        }
    ] 

})
export default router;