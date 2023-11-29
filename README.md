## 本项目实现的最终作用是基于JSP校园快递代取管理平台
## 分为2个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 人员管理
 - 快递管理
 - 查看已发送私信
 - 查看我的私信
 - 管理员登录
### 第2个角色为用户角色，实现了如下功能：
 - 查看好友快递
 - 查看好友申请
 - 查看已发送私信
 - 查看我的好友
 - 查看我的快递
 - 查看我的私信
 - 用户登录
## 数据库设计如下：
# 数据库设计文档

**数据库名：** xykuaidisys

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [apply](#apply) |  |
| [express](#express) |  |
| [friend](#friend) |  |
| [user](#user) | 用户表 |
| [user_message](#user_message) |  |

**表名：** <a id="apply">apply</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增主键  |
|  2   | addDate |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | checked |   bit   | 1 |   0    |    N     |  N   |       |   |
|  4   | content |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 内容  |
|  5   | from_userid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  6   | to_userid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="express">express</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增主键  |
|  2   | area |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | finDate |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  4   | phone |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 电话  |
|  5   | sid |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  6   | status |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 状态  |
|  7   | addressee |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  8   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |

**表名：** <a id="friend">friend</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增主键  |
|  2   | checked |   bit   | 1 |   0    |    N     |  N   |       |   |
|  3   | user1id |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  4   | user2id |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="user">user</a>

**说明：** 用户表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增主键  |
|  2   | loginId |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | name |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 名字  |
|  4   | password |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 密码  |
|  5   | userRole |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="user_message">user_message</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增主键  |
|  2   | addDate |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  3   | allUser |   bit   | 1 |   0    |    N     |  N   |       |   |
|  4   | content |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 内容  |
|  5   | from_userid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  6   | to_userid |   int   | 10 |   0    |    Y     |  N   |   NULL    |   |
|  7   | recontent |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**运行不出来可以微信 javape 我的公众号：源码码头**
