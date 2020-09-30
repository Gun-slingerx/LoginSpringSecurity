# SpringSecurity+Jwt实现前后端分离登录

## 认证步骤        
     1. 用户发起表单请求后，首先进入UsernamePasswordAuthenticationFilter
     2. 在 UsernamePasswordAuthenticationFilter 中根据用户输入的用户名、密码构建了 UsernamePasswordAuthenticationToken，并将其交给 AuthenticationManager 来进行认证处理。
     3. 跳转到ProviderManager，该类是 AuthenticationManager 的实现类
     4. 当选择好一个合适的 AuthenticationProvider 后，通过 provider.authenticate(authentication) 来让 AuthenticationProvider 进行认证。
     5. 传统表单登录的 AuthenticationProvider 主要是由 AbstractUserDetailsAuthenticationProvider 来进行处理的，我们来看下它的 authenticate()方法。
     6. etrieveUser() 的具体实现在 DaoAuthenticationProvider 中
     7. this.getUserDetailsService().loadUserByUsername(username)调用了UserDetailsService接口的loadUserByUsername(username)方法
##鉴权思路
     1 获取访问该url所需要的用户角色权限信息
     2 获取当前用户的角色信息
     3 对比当前用户的角色信息是否包含访问该url的角色信息，包含则鉴权通过
## 鉴权步骤
    1. 自定义未登录权限处理器 AdminAuthenticationEntryPoint - 自定义未登录时访问无权限url响应内容
    2. 自定义访问鉴权过滤器 MyAuthenticationFilter - 记录请求响应日志、是否合法访问，验证token过期等
    3. 自定义 UrlFilterInvocationSecurityMetadataSource - 获取访问该url所需要的角色权限
    4. 自定义 UrlAccessDecisionManager - 对访问url进行权限认证处理
    5. 自定义 UrlAccessDeniedHandler - 登录过后访问无权限url失败处理器 - 自定义403无权限响应内容
    6. 在 Security 核心配置类中配置以上处理器和过滤器
