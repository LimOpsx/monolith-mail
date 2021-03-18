###  [gitee地址](http://gitee.com/opsx/monolith-mail)

###  English | [简体中文](./README_zh.md)

###Function
- 1: support random email address switching
- 2: display the latest email in real time
- 3: mail persistence
- 4: support receiving Gmail, QQ email, Sina email
- 5: use socket binding in connection pool
###How to use
-1: set MX resolution in server forward resolution and reverse resolution
--  1.1: you can view the blog to set ([ https://blog.csdn.net/whb1751178448/article/details/110959308 ]() )
- 2: open project port and 25 port
- 3: change project code port
- 4: open your server port 25 and project port
- 5: it can be deployed with docker
-- 5.1: docker start command:
```
Docker run -- name monolith - D - P 25:25 - P port mapping: 8080 monolith
```
-6: open IP: port / to access
>Tips: what you're doing
- ~~1: write a more complete document~~, and the principle of anonymous post office
- 2: add post office authentication (optional)
- ~~3: test several common email service providers on the market~~
- 4: mail address can be destroyed or not
- 5: websocket shows the data to UI, and consider changing to netty to realize WS
- 6: test the problem in the scenario of receiving a large number of e-mails
- 7: WS is disconnected after a period of time, to be solved
- 8: the email from outlook 163 126 mailbox can't be received, so we need to check the problem
> Mention the current status of this small tool, there are a lot of unreasonable project code, I first limited technology, and second, the work of this period of time is relatively busy. The purpose of this project is to learn from the experience of open source community. I can't build a car behind closed doors. This is my biggest feeling. I am open-minded to accept the criticisms and suggestions of the big guys