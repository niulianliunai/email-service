# emai-service

简易的java发送邮件程序 支持抄送密送群发 (javax.email)



## 配置

![image](https://user-images.githubusercontent.com/49626606/111426914-4b832d00-8730-11eb-85d5-8592c9a36454.png)

* 以qq邮箱为例：

  在application.properties里输入你的用户名密码

## 使用

![image](https://user-images.githubusercontent.com/49626606/111426887-43c38880-8730-11eb-852d-0b1d834a883f.png)


* 发送邮件只需直接调用SendMailUtil.sendEmail即可
* subjet：主题
* content：内容
* fromName：发件人名字
* to：收件人，格式为["name:123@qq.com", ...] （群发给数组内的所有人）
* cc：抄送人，格式同上
* bcc：密送人，格式同上



## 备注

配置的密码不是你的qq密码，需要进入qq邮箱 -> 设置 -> 账户 -> POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务 开启服务
![image](https://user-images.githubusercontent.com/49626606/111426935-5342d180-8730-11eb-9025-50e1062401e2.png)

根据提示操作即可
