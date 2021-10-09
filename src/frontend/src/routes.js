import Router from 'vue-router'

// Routing configurations, import componetes
import store from './store/store'

import Home from './pages/home/Home.vue'
import Matchs from './pages/matchs/Matchs.vue'
import Chat from './pages/chats/Chats.vue'
import Forums from './pages/forums/Forums.vue'
import Forum from './pages/forums/Forum.vue'
import Mentors from './pages/mentors/Mentors.vue'
import Login from './pages/session/Login.vue'
import Signup from './pages/session/Signup.vue'
import Profile from './pages/profile/Profile.vue'

// Componentes de Administrador
import Administrator from './pages/admin/Administrator.vue'

// Componentes de Error
import NotFound from './pages/error/NotFound.vue'
import NotAuth from './pages/error/NotAuth.vue'

// Create Routes
const router = new Router({
  //mode: 'history',
  routes: [
    { path: '/administrator', alias: '/admin', component: Administrator },
    { path: '/', alias: '/home', component: Home },
    { path: '/matchs', component: Matchs },
    { path: '/chats', component: Chat },
    { path: '/forums', component: Forums },
    { path: '/forums/:id', component: Forum },
    { path: '/mentors', component: Mentors },
    { path: '/login', component: Login },
    { path: '/signup', component: Signup },
    { path: '/profile', component: Profile },
    { path: '/notauth', component: NotAuth },
    { path: '/*', alias: '/notfound', component: NotFound },
  ]
})

router.beforeEach((to, from, next) => {
  const adminAuthRequiredPages = ['/administrator']
  const authRequiredPages = ['/matchs', '/chats', '/forums', '/mentors', '/profile'];
  const loggedIn = store.getters.isLoggedIn
  if (loggedIn) {
    if (adminAuthRequiredPages.includes(to.path) && !store.getters.isLoggedInAdmin) {
      // si require permiso de administrador y no lo tiene
      return next('/notauth');
    }
  } else {
    if (authRequiredPages.includes(to.path)) { // si require autorizacion
      return next('/login');
    }
  }
  next();
})

export default router