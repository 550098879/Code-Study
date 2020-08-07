/**
 * 1.定义子组件
 * 2.使用可以换行的字符串编辑html模板 ` `
 * @type {{}}
 */
const loginForm = {
    //在组件内侧template中,只能有一个root根标签
    template: `            
        <div>
            <h2>登录页面</h2>
            用户名: <input type="text"><br>
            密&emsp;码: <input type="password"><br>
            <input type="button" value="登录">
        </div>
    `,
};

export default loginForm;  //导出组件,方便便使用es6语法引入组件