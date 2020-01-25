import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App'
import RateUP from './components/RateUP'
import Member from './components/Member'
import addVideo from './components/addVideo'
import AddBook from './components/AddBook'
import borrow from './components/borrow'
import ViewDocument from './components/ViewDocument'
import viewVideo from './components/viewVideo'
import viewRateUP from './components/ViewRatingUP'
import Remand from './components/Remand'

import ShowMember from './components/ShowMember'
import home from './components/home'
<<<<<<< HEAD
import showHistory from './components/showHistory'
=======
import home from './components/showHistory'

>>>>>>> 6d3bb658ec929f796b3933d974bdcb01a7645421
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
                    redirect: 'home'
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
                    path: 'home',
                    component: home,
                },
                {
<<<<<<< HEAD
                    path: 'showHistory',
                    component: showHistory,
                }
=======
                    path: 'viewdocument',
                    component: ViewDocument
                },
                {
                    path: 'viewVideo',
                    component: viewVideo               
                },
		{
                    path: 'viewRateUP',
                    component: viewRateUP                      
                },
                {
                            path: 'showHistory',
                            component: showHistory                     
                        }

>>>>>>> 6d3bb658ec929f796b3933d974bdcb01a7645421


            ]

        }
    ] 

})
export default router;