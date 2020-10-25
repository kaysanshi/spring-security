web权限方案--认证（authentication）

认证简单地说就是让系统知道是不是你，比如：你有身份证ID卡，那么你刷身份证到火车站，则可以通过人脸识别通过或者能够在系统中查到你的信息。

设置登录用户名和密码的通过配置文件
自定义登录界面
配置不需要配置的路径

     /**
         * 核心过滤器配置的方法
         * configure(WebSecurity web)
         * 用于配置WebSecurity webSecurity是基于servlet Filter的配置SpringSecurityFilterChain.而 springSecurityFilterChain 又被委托给了 Spring Security 核心过滤器 Bean DelegatingFilterProxy 。 相关逻辑你可以在 WebSecurityConfiguration 中找到。
         * 我们一般不会过多来自定义 WebSecurity , 使用较多的使其ignoring() 方法用来忽略 Spring Security 对静态资源的控制。
         * 如果一个请求路径不设置拦截：
         * 1.设置地址匿名访问
         * 2.直接过滤掉该地址，及该地址不走Spring Security 过滤器链。
         * 下面方法是演示直接过率掉该地址。
         * WebSecurity的使用
         *
         * @param web
         * @throws Exception
         */
        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
            // 忽略那些拦截
            web.ignoring().antMatchers("/vercode");
        }
