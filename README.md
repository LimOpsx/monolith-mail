###  [gitee地址](http://gitee.com/opsx/monolith-mail)

###  English | [简体中文](./README_zh.md)

### function

- 1: Support random email address switching
- 2: Display the latest email in real time
- 3: Mail persistence
- 4:......

### How to use

- 1: Set MX resolution in server forward resolution and reverse resolution
- - 1.1: you can view the blog to set ([ https://blog.csdn.net/whb1751178448/article/details/110959308 ]() )
- 2: Open project port and 25 port
- 3: Change project code port
- 4: Open your server port 25 and project port
- 5: You can deploy using docker
- -5.1: docker start command:

```
docker run --name monolith  -d -p 25:25 -p 端口映射:8080 monolith
```

- 6: Open IP: port / to access

> Tips: what you're doing

- 1: Write a more complete document, as well as the principle of anonymous post office
- 2: Add post office authentication (optional)
- 3: Test several common email service providers on the market
- 4: The email address can be destroyed or not
- 5: Websocket displays data to UI, and consider changing to netty to implement WS
- 6: Test the problem in the scenario of receiving a large number of e-mails

> Mention the current status of this small tool, there are a lot of unreasonable project code, I first limited technology, and second, the work of this period of time is relatively busy. The purpose of this project is to learn from the experience of open source community. I can't build a car behind closed doors. This is my biggest feeling. I am open-minded to accept the criticisms and suggestions of the big guys
