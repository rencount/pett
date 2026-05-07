
import { createRouter, createWebHistory,createWebHashHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router';
import { Session } from '@/utils/storage';
import { Key } from '@/stores/auth';

export const dynamicRoutes: RouteRecordRaw[] = [
    {
        path: "/",
        name: "layout", // layout 的 children 数组为用户菜单页面的集合
        redirect: "/home",
        component: () => import('@/layout/index.vue'),
        children: [
            {
                path: "/home",
                name: "home",
                meta: {
                    hideInMenu: false, // 该菜单是否隐藏；true 为隐藏
                    title: "首页",
                    cache: true, // 为true，则缓存菜单
                    icon: "&#xe611;", // 图标
                },
                component: () => import("@/views/home.vue"),
            },
            {
                path: "/cen",
                name: "cen",
                meta: {
                    title: "个人信息",
                    icon: "&#xe7fc;", // 图标
                },
                redirect:'/center',
                children: [
                    {
                        path: "/center",
                        name: "center",
                        meta: {
                            title: "个人信息",
                            icon: "&#xe90f;", // 图标
                        },
                        component: () => import("@/views/center.vue"),
                    },

                ],
            },


            

            {
                path: "/forum",
                name: "forum",
                meta: {
                    title: "${genProject.forumName}管理",
                    icon: "&#xe7fc;", // 图标
                },
                redirect:'/forum',
                children: [
                    {
                        path: "/forum",
                        name: "forum",
                        meta: {
                            title: "${genProject.forumName}管理",
                            icon: "&#xe90f;", // 图标
                        },
                        component: () => import("@/views/forum/index.vue"),
                    },

                ],
            },


            
                 		 {
                        path: "/wenzhen",
                        name: "wenzhen",
                        meta: {
                            title: "问诊",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/wenzhen',
                        children: [
                            {
                                path: "/wenzhen",
                                name: "wenzhen",
                                meta: {
                                    title: "问诊",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/wd/index.vue"),
                            },
                        ],
                    },
                
            
                                    {
                        path: "/news",
                        name: "news",
                        meta: {
                            title: "公告资讯",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/news',
                        children: [
                            {
                                path: "/news",
                                name: "news",
                                meta: {
                                    title: "公告资讯",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/news/index.vue"),
                            },

                        ],
                    },

                
            
                                    {
                        path: "/chongwuguanli",
                        name: "chongwuguanli",
                        meta: {
                            title: "宠物管理",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/chongwuguanli',
                        children: [
                            {
                                path: "/chongwuguanli",
                                name: "chongwuguanli",
                                meta: {
                                    title: "宠物管理",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/chongwuguanli/index.vue"),
                            },

                        ],
                    },
                
            
                                    {
                        path: "/discusschongwuguanli",
                        name: "/discusschongwuguanli",
                        meta: {
                            title: "宠物管理评论",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/discusschongwuguanli',
                        children: [
                            {
                                path: "/discusschongwuguanli",
                                name: "discusschongwuguanli",
                                meta: {
                                    title: "宠物管理评论",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/discusschongwuguanli/index.vue"),
                            },

                        ],
                    },





                
            





                
            
                                    {
                        path: "/jibingshangchuan",
                        name: "jibingshangchuan",
                        meta: {
                            title: "疾病上传",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/jibingshangchuan',
                        children: [
                            {
                                path: "/jibingshangchuan",
                                name: "jibingshangchuan",
                                meta: {
                                    title: "疾病上传",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/jibingshangchuan/index.vue"),
                            },

                        ],
                    },
                
            
                                    {
                        path: "/zhenduan",
                        name: "zhenduan",
                        meta: {
                            title: "诊断",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/zhenduan',
                        children: [
                            {
                                path: "/zhenduan",
                                name: "zhenduan",
                                meta: {
                                    title: "诊断",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/zhenduan/index.vue"),
                            },

                        ],
                    },
                
            
                                    {
                        path: "/zixun",
                        name: "zixun",
                        meta: {
                            title: "咨询",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/zixun',
                        children: [
                            {
                                path: "/zixun",
                                name: "zixun",
                                meta: {
                                    title: "咨询",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/zixun/index.vue"),
                            },

                        ],
                    },
                
            
                                    {
                        path: "/discusszixun",
                        name: "/discusszixun",
                        meta: {
                            title: "咨询评论",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/discusszixun',
                        children: [
                            {
                                path: "/discusszixun",
                                name: "discusszixun",
                                meta: {
                                    title: "咨询评论",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/discusszixun/index.vue"),
                            },

                        ],
                    },





                
            
                                    {
                        path: "/yonghu",
                        name: "yonghu",
                        meta: {
                            title: "用户",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/yonghu',
                        children: [
                            {
                                path: "/yonghu",
                                name: "yonghu",
                                meta: {
                                    title: "用户",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/yonghu/index.vue"),
                            },

                        ],
                    },
                
            
                                    {
                        path: "/chongwuyisheng",
                        name: "chongwuyisheng",
                        meta: {
                            title: "宠物医生",
                            icon: "&#xe7fc;", // 图标
                        },
                        redirect:'/chongwuyisheng',
                        children: [
                            {
                                path: "/chongwuyisheng",
                                name: "chongwuyisheng",
                                meta: {
                                    title: "宠物医生",
                                    icon: "&#xe90f;", // 图标
                                },
                                component: () => import("@/views/chongwuyisheng/index.vue"),
                            },

                        ],
                    },
                
                        {
                path: "/config",
                name: "config",
                meta: {
                    title: "系统管理",
                    icon: "&#xe7fc;", // 图标
                },
                redirect:'/config',
                children: [
                    {
                        path: "/config",
                        name: "config",
                        meta: {
                            title: "轮播图列表",
                            icon: "&#xe90f;", // 图标
                        },
                        component: () => import("@/views/config/index.vue"),
                    },

                ],
            },



            {
                path: '/401',
                name: 'NoPermission',
                component: () => import('@/views/error/401.vue'),
                meta: {
                    title: '401页面',
                    icon: 'ele-Warning',
                    cache: true,
                    hidden: false,
                }
            },
            {
                path: '/:path(.*)*', //  404匹配不存在的路由
                name: 'NotFound',
                component: () => import('@/views/error/404.vue'),
                meta: {
                    title: '未找到此页面',
                    cache: true,
                    hidden: true,
                }
            },

        ],
    },
    {
        path:'/login',
        name:'Login',
        component:()=>import('@/views/login/index.vue')
    }

]

const router = createRouter({
    // 参数获取的是 vite.config.ts 中base 属性值
    history: createWebHashHistory(),
    routes: dynamicRoutes,
});


router.beforeEach((to,from)=>{
    // const token = localStorage.getItem('token');
    //用户未登录，且跳转的目标路由不是登陆页面：则跳转登录页面/login


    const token=Session.get(Key.accessTokenKey);
    console.log(token);
    if(!token && (to.path !=='/login')){
        //通过return返回一个跳转的路由对象，重定向到指定路由
        //  return router.push({path:'/login'});
        //  return {path:'/login'}


        return {path:'/login'}


    }
    //已登录,正常进入目标路由
    return true;


})

// 路由后置守卫

export default router
