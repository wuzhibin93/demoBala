# -*- coding: utf-8 -*-
import time

foo = [{"geonameid": 11114307, "name": "Buffler Basin", "asciiname": "Buffler Basin", "feature_class": "U", "feature_code": "BSNU", "admin1_code": "00", "population": 0, "dem": "-9999", "location": [-91.61667, 27.83333]},
       'WARNING|'+time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+'|-|修改|更新LICENSE|1|admin|超级权限|超级管理员|192.168.5.125|3ddb6168-ab70-41f6-9433-5c02a2280012|失败原因：License 不合法|',
       'INFO|'+time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+'|-|登出|退出管理端|1|admin|超级权限|超级管理员|192.168.5.125|3ddb6168-ab70-41f6-9433-5c02a2280012|成功|',
       'INFO|'+time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+'|-|查询|获取页面菜单展示信息|1|admin|超级权限|超级管理员|192.168.5.125|3ddb6168-ab70-41f6-9433-5c02a2280012|成功|',
       'INFO|'+time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+'|-|修改|更新Client应用|1|admin|超级权限|超级管理员|192.168.5.125|3ddb6168-ab70-41f6-9433-5c02a2280012|成功|',
       'INFO|'+time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+'|-|查询|获取ip列表|1|admin|超级权限|超级管理员|192.168.5.125|3ddb6168-ab70-41f6-9433-5c02a2280012|成功|',
       'INFO|'+time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+'|-|修改|修改ip|1|admin|超级权限|超级管理员|192.168.5.125|3ddb6168-ab70-41f6-9433-5c02a2280012|成功|',
       'INFO|'+time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+'|-|修改|修改认证方式|1|admin|超级权限|超级管理员|192.168.5.125|3ddb6168-ab70-41f6-9433-5c02a2280012|成功|'
       ]

for x in list(range(10000)):
    with open('d:/pythons/a.java', 'a') as f:
        from random import choice
        f.write(choice(foo)+"\n")
print("Over")
#{"geonameid": 11114307, "name": "Buffler Basin", "asciiname": "Buffler Basin", "feature_class": "U", "feature_code": "BSNU", "admin1_code": "00", "population": 0, "dem": "-9999", "location": [-91.61667, 27.83333]}
