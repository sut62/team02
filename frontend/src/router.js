import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App'
import RateUP from './components/RateUP'
import Member from './components/Member'
import addVideo from './components/addVideo'
import AddBook from './components/AddBook'
import borrow from './components/borrow'
import ViewRateUP from './components/ViewRatingUP'
import Remand from './components/Remand'
import ShowMember from './components/ShowMember'

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
                    path:'remand',
                    component: Remand
                },
                {
                    path: 'rates',
                    component: RateUP,
                },

                
                {
                    path: 'Member',
                    component: Member,
                },

                {
                    path: 'addVideo',
                    component: addVideo,
                },
                {
                    path: 'borrow',
                    component: borrow,
                },
                {
                    path: 'AddBook',
                    component: AddBook
                }
                ,
                {
                    path: 'ShowMember',
                    component: ShowMember,
                },
                {
                    path: 'viewRateUP',
                    component: ViewRateUP
                }

            ]

        }
    ] 

})
export default router;