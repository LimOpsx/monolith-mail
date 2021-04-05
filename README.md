###  [gitee地址](http://gitee.com/opsx/monolith-mail)
***
###  English | [简体中文](./README_zh.md)
***
Experience address: http://8.136.23.192:9091/#/
***
Use with front-end interface... <br/>
Project front end address: https://github.com/linenhui023/monolith-ui
***
### Function
- 1: support random email address switching
- 2: display the latest email in real time
- 3: mail persistence
- 4: support receiving the mainstream email in the market, including but not limited to Gmail, QQ email, Sina email, 126163, yeah, outlook
***
### How to use
- 1: set MX resolution in server forward resolution and reverse resolution
- 2 : you can view the blog to set ([ https://blog.csdn.net/whb1751178448/article/details/110959308 ]() )
- 3: open project port and 25 port
- 4: change project code port
- 5: open your server port 25 and project port
- 6: it can be deployed with docker
- 7: docker start command:
```
Docker run -- name monolith - D - P 25:25 - P port mapping: 8080 monolith
```
- 8: open IP: port / to access
***
>Tips: what you're doing
- ~~1: Write a more complete document~~, and the principle of anonymous post office
- ~~2: Test many common mailbox service providers in the market~~ (<font color="red">complete</font>)
- 3: The email address is optional
- 4: WebSocket displays data to UI, consider switching to Netty to implement WebSocket
- 5: Test the problem in the scenario of mass mail reception
- 6: ~~The WS connection is disconnected after a period of time, which needs to be solved~~(<font color="red" > complete </font>)
- ~~7: Outlook 163 126 E-mail cannot be received, need to troubleshoot the problem~~(<font color="red"> complete </font>)
- ~~8: Write a~~ nice ~~UI to display the email~~ (<font color="red">complete</font>)
- 9: Implement email forgery to send, make a more interesting gadget
***
>Mention the current status of this small tool, there are a lot of unreasonable project code, I first limited technology, and second, the work of this period of time is relatively busy. The purpose of this project is to learn from the experience of open source community. I can't build a car behind closed doors. This is my biggest feeling. I am open-minded to accept the criticisms and suggestions of the big guys
> ***
>As I am not particularly familiar with the front-end, now I want to find a front-end partner who can use Vue to complete the preparation of this post office, my email address :linenhui023@outlook.com