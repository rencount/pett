const menu = {
    list() {
        return [{
            "frontMenu": [{
                "child": [{
                    "buttons": ["新增", "删除", "修改", "查看"],
                    "menu": "咨询",
                    "menuJump": "列表",
                    "tableName": "zixun"
                }], "menu": "咨询"
            }, {
                "child": [{
                    "buttons": ["查看"],
                    "menu": "学习管理",
                    "menuJump": "列表",
                    "tableName": "xuexiguanli"
                }], "menu": "学习管理"
            }, {
                "child": [{ "buttons": ["查看"], "menu": "宠物资讯", "tableName": "news" }],
                "menu": "宠物资讯"
            }], "roleName": "用户", "tableName": "yonghu"
        }, {
            "backMenu": [{
                "child": [{
                    "buttons": ["回复"],
                    "menu": "诊断",
                    "menuJump": "列表",
                    "tableName": "zhenduan"
                }], "menu": "诊断"
            }, {
                "child": [{
                    "buttons": ["查看", "修改", "删除"],
                    "menu": "咨询评论",
                    "menuJump": "列表",
                    "tableName": "discusszixun"
                }], "menu": "咨询评论"
            }, {
                "child": [{
                    "buttons": ["回复"],
                    "menu": "咨询",
                    "menuJump": "列表",
                    "tableName": "zixun"
                }], "menu": "咨询"
            }, {
                "child": [{
                    "buttons": ["查看", "修改", "删除"],
                    "menu": "宠物管理评论",
                    "menuJump": "列表",
                    "tableName": "discusschongwuguanli"
                }], "menu": "宠物管理评论"
            }, {
                "child": [{
                    "buttons": ["查看"],
                    "menu": "宠物管理",
                    "menuJump": "列表",
                    "tableName": "chongwuguanli"
                }], "menu": "宠物管理"
            }, {
                "child": [{
                    "buttons": ["新增", "删除", "修改", "查看"],
                    "menu": "问诊",
                    "menuJump": "列表",
                    "tableName": "wenzhen"
                }], "menu": "问诊"
            }, {
                "child": [{
                    "buttons": ["查看"],
                    "menu": "疾病上传",
                    "menuJump": "列表",
                    "tableName": "jibingshangchuan"
                }], "menu": "疾病上传"
            }], "frontMenu": [{
                "child": [{ "buttons": ["查看"], "menu": "宠物资讯", "tableName": "news" }],
                "menu": "宠物资讯"
            }, {
                "child": [{ "buttons": ["查看"], "menu": "宠物资讯", "tableName": "news" }],
                "menu": "宠物资讯"
            }], "roleName": "宠物医生", "tableName": "chongwuyisheng"
        }, {
            "backMenu": [{
                "child": [{
                    "buttons": ["查看", "修改", "删除"],
                    "menu": "宠物管理评论",
                    "menuJump": "列表",
                    "tableName": "discusschongwuguanli"
                }], "menu": "宠物管理评论"
            }, {
                "child": [{
                    "buttons": ["新增", "删除", "修改", "查看"],
                    "menu": "宠物管理",
                    "menuJump": "列表",
                    "tableName": "chongwuguanli"
                }], "menu": "宠物管理"
            }, {
                "child": [{
                    "buttons": ["审核"],
                    "menu": "诊断",
                    "menuJump": "列表",
                    "tableName": "zhenduan"
                }], "menu": "诊断"
            }, {
                "child": [{
                    "buttons": ["新增", "删除", "修改", "查看"],
                    "menu": "用户",
                    "menuJump": "列表",
                    "tableName": "yonghu"
                }], "menu": "用户"
            }, {
                "child": [{
                    "buttons": ["新增", "删除", "修改", "查看"],
                    "menu": "宠物医生",
                    "menuJump": "列表",
                    "tableName": "chongwuyisheng"
                }], "menu": "宠物医生"
            }, {
                "child": [{
                    "buttons": ["查看", "修改", "删除"],
                    "menu": "学习管理评论",
                    "menuJump": "列表",
                    "tableName": "discussxuexiguanli"
                }], "menu": "学习管理评论"
            }, {
                "child": [{
                    "buttons": ["新增", "删除", "修改", "查看"],
                    "menu": "学习管理",
                    "menuJump": "列表",
                    "tableName": "xuexiguanli"
                }], "menu": "学习管理"
            }, {
                "child": [{
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "轮播图管理",
                    "tableName": "config"
                }, {
                    "buttons": ["新增", "查看", "修改", "删除"],
                    "menu": "宠物资讯", "tableName": "news"
                }],
                "menu": "系统管理"
            }], "roleName": "管理员", "tableName": "users"
        }, {
            "ykMenu": [{
                "child": [{ "buttons": ["查看"], "menu": "宠物资讯", "tableName": "news" }],
                "menu": "宠物资讯"
            }], "roleName": "游客", "tableName": "游客"
        }]
    }
}

const geren = {
    list() {
        return [{
            "ykMenu": [{
                "child": [{
                    "buttons": ["新增", "删除", "修改", "查看", "查看评论", "回复"],
                    "menu": "宠物管理",
                    "menuJump": "列表",
                    "tableName": "chongwuguanli"
                }], "menu": "宠物管理"
            },{
                "child": [{
                    "buttons": ["新增", "删除", "修改", "查看"],
                    "menu": "我的收藏管理",
                    "menuJump": "列表",
                    "tableName": "wodeshouzangguanli"
                }], "menu": "我的收藏管理"
            }], "roleName": "个人中心", "tableName": "个人中心"
        }]
    }
}
export {
    menu, geren
}
