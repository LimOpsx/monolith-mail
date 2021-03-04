### [github地址](https://github.com/linenhui023/monolith-mail)

###  [English](./README.md) | 简体中文

### 功能

- 1:支持随机切换邮件地址
- 2:实时显示最新一封邮件
- 3:邮件持久化
- 4:......

### 如何使用

- 1:设置服务器正向解析和反向解析中的 mx 解析
- - 1.1:可查看该博客来进行设置( [https://blog.csdn.net/whb1751178448/article/details/110959308]() )
- 2:开放项目端口和 25 端口
- 3:更改项目代码端口
- 4:打开你服务器 25 端口和项目端口
- 5:可以使用 docker 部署
- - 5.1:docker 启动命令:

```
docker run --name monolith  -d -p 25:25 -p 端口映射:8080 monolith
```

- 6:打开 ip:端口/ ,进行访问

> tips:正在做的事

- 1:写一篇更加完善的文档、以及匿名邮局原理
- 2:添加邮局身份认证(可选)
- 3:测试市面上多家常见的邮箱服务商
- 4:邮件地址可选是否销毁
- 5:webSocket 展示数据到 ui，考虑换成 netty 来实现 ws
- 6:测试大量接收邮件场景下的问题

> 提一下目前这个小工具的现况,项目代码还有大量不合理的地方,本人一是技术有限,二是工作这段时间比较忙。这个项目发出来是为了学习开源社区的经验,不能闭门造车这是我最大的感受,虚心接受各位大佬的批评以及建议.