### 如何使用

1:设置你服务器mx解析
2:更改项目代码端口
3:打开你服务器25端口和项目端口
4:可以使用docker部署
    3.1:docker 启动命令:docker run --name monolith  -d -p 25:25 -p 端口映射:8080 monolith

### How to use

1: Set your server MX resolution
2: Change project code port
3: Open your server port 25 and project port
4: You can deploy using docker
    3.1:docker run command :docker run --name monolith  -d -p 25:25 -p port:8080 monolith

### 目前只测试过smtp协议
### At present, only the SMTP protocol has been tested
