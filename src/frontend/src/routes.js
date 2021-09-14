import Router from 'vue-router'

// Routing configurations, import componetes
import Home from './pages/home/Home.vue'
import Matchs from './pages/matchs/Matchs.vue'
import Chat from './pages/chats/Chats.vue'
import Forums from './pages/forums/Forums.vue'
import Mentors from './pages/mentors/Mentors.vue'
import Login from './pages/session/Login.vue'
import Signup from './pages/session/Signup.vue'
import Profile from './pages/profile/Profile.vue'

// Error componentes
import NotFound from './pages/error/NotFound.vue'

// Create Routes
const router = new Router({
  mode: 'history',
  routes: [
    { path: '/', alias: '/home', component: Home },
    { path: '/matchs', component: Matchs },
    { path: '/chats', component: Chat },
    { path: '/forums', component: Forums },
    { path: '/mentors', component: Mentors },
    { path: '/login', component: Login },
    { path: '/signup', component: Signup },
    { path: '/profile', component: Profile },
    { path: '/*', alias: '/notfound', component: NotFound },
  ]
})

export default router