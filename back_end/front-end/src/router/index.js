import Vue from 'vue'
import VueRouter from 'vue-router'
import PokeHome from '../views/PokeHome.vue'
import { Message } from 'element-ui'

Vue.use(VueRouter)

const routes = [
	{
		path: '/',
		name: 'home',
		component: PokeHome ,
		redirect:'/manage',
		children:[
			{
				path:'/chart',
				name:'chart',
				component:()=>import('../views/pokemen/chart')
			},
			{
				path:'/manage',
				name:'manage',
				component:()=>import('../views/pokemen/manage')
			}
		],
		// redirect:'/subarea',
		// children:[
		// 	{
		// 		path:'/subarea',
		// 		name:'subarea',
		// 		component:()=>import('../views/exam/subarea')
		// 	},
		// 	{
		// 		path:'/questions',
		// 		name:'questions',
		// 		component:()=>import('../views/exam/questions')
		// 	}
		// ],
		
	},
	// {
	// 	path: '/test',
	// 	name: 'test',
	// 	component:()=>import('../views/exam/test')
	// },
	// {
	// 	path: '/exam',
	// 	name: 'exam',
	// 	component:()=>import('../views/exam/exam')
	// }
]



const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})
// router.beforeEach((to, from, next) => {
// 	const token = sessionStorage.getItem('token')

// 	if(to.path == '/login'){
// 		if(token){
// 			next('/')
// 			Message.info('您已登录，请不要重复登录')
// 			return
// 		}else{
// 			return next()
// 		}
// 	}else{
// 		if(token){
// 			return next()
// 		}else{
// 			Message.info('没登就往里进？？？？')
// 			return next('/login')
// 		}
// 	}

// })

let routerPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return routerPush.call(this, location).catch(err => err)
}
export default router
