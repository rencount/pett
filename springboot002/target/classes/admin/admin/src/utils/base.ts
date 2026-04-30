const base = {
    get() {
        return {
            url: "http://localhost:8080/springboot002/",
            name: "springboot002",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot002/front/index.html'
        };
    },
    getProjectName() {
        return {
            projectName: "宠物疾病诊断系统"
        }
    }
}
export default base
