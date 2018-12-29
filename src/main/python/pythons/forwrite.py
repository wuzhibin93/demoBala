# -*- coding: utf-8 -*-
import time
import datetime
start = datetime.datetime.now()
foo = ["{\"level\": \"WARNING\", \"@timestamp\": "+str(time.mktime(time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")))[:-2]+", \"user_name\": \"admin\", \"user_fullname\": \"超级管理员\", \"role\": \"超级权限\", \"privilege\": \"admin\", \"operation\": \"修改\", \"log_info\": \"更新LICENSE\", \"result\": \"失败原因：License 不合法\", \"client_ip\": \"192.168.5.125\", \"client_os\": \"Windows\", \"session\": \"dsa123dsa123\", \"country\": \"CN\", \"province\": \"JS\", \"city\": \"NJ\", \"longitude\": 25.21, \"latitude\": 12.32}",
       "{\"level\": \"INFO\", \"@timestamp\": "+str(time.mktime(time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")))[:-2]+", \"user_name\": \"admin\", \"user_fullname\": \"超级管理员\", \"role\": \"超级权限\", \"privilege\": \"admin\", \"operation\": \"登出\", \"log_info\": \"退出管理端\", \"result\": \"成功\", \"client_ip\": \"192.168.5.125\", \"client_os\": \"Windows\", \"session\": \"dsa123dsa123\", \"country\": \"CN\", \"province\": \"JS\", \"city\": \"NJ\", \"longitude\": 25.21, \"latitude\": 12.32}",
       "{\"level\": \"INFO\", \"@timestamp\": "+str(time.mktime(time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")))[:-2]+", \"user_name\": \"admin\", \"user_fullname\": \"超级管理员\", \"role\": \"超级权限\", \"privilege\": \"admin\", \"operation\": \"查询\", \"log_info\": \"获取页面菜单展示信息\", \"result\": \"成功\", \"client_ip\": \"192.168.5.125\", \"client_os\": \"Windows\", \"session\": \"dsa123dsa123\", \"country\": \"CN\", \"province\": \"JS\", \"city\": \"NJ\", \"longitude\": 25.21, \"latitude\": 12.32}",
       "{\"level\": \"INFO\", \"@timestamp\": "+str(time.mktime(time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")))[:-2]+", \"user_name\": \"admin\", \"user_fullname\": \"超级管理员\", \"role\": \"超级权限\", \"privilege\": \"admin\", \"operation\": \"修改\", \"log_info\": \"更新LICENSE\", \"result\": \"成功\", \"client_ip\": \"192.168.5.125\", \"client_os\": \"Windows\", \"session\": \"dsa123dsa123\", \"country\": \"CN\", \"province\": \"JS\", \"city\": \"NJ\", \"longitude\": 25.21, \"latitude\": 12.32}",
       "{\"level\": \"INFO\", \"@timestamp\": "+str(time.mktime(time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")))[:-2]+", \"user_name\": \"admin\", \"user_fullname\": \"超级管理员\", \"role\": \"超级权限\", \"privilege\": \"admin\", \"operation\": \"查询\", \"log_info\": \"获取ip列表\", \"result\": \"成功\", \"client_ip\": \"192.168.5.125\", \"client_os\": \"Windows\", \"session\": \"dsa123dsa123\", \"country\": \"CN\", \"province\": \"JS\", \"city\": \"NJ\", \"longitude\": 25.21, \"latitude\": 12.32}",
       "{\"level\": \"INFO\", \"@timestamp\": "+str(time.mktime(time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")))[:-2]+", \"user_name\": \"admin\", \"user_fullname\": \"超级管理员\", \"role\": \"超级权限\", \"privilege\": \"admin\", \"operation\": \"修改\", \"log_info\": \"修改ip\", \"result\": \"成功\", \"client_ip\": \"192.168.5.125\", \"client_os\": \"Windows\", \"session\": \"dsa123dsa123\", \"country\": \"CN\", \"province\": \"JS\", \"city\": \"NJ\", \"longitude\": 25.21, \"latitude\": 12.32}",
       "{\"level\": \"INFO\", \"@timestamp\": "+str(time.mktime(time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")))[:-2]+", \"user_name\": \"admin\", \"user_fullname\": \"超级管理员\", \"role\": \"超级权限\", \"privilege\": \"admin\", \"operation\": \"修改\", \"log_info\": \"修改认证方式\", \"result\": \"成功\", \"client_ip\": \"192.168.5.125\", \"client_os\": \"Windows\", \"session\": \"dsa123dsa123\", \"country\": \"CN\", \"province\": \"JS\", \"city\": \"NJ\", \"longitude\": 25.21, \"latitude\": 12.32}"
       ]

for x in list(range(1000000)):
    with open('/usr/local/esrally/benchmarks/data/enadmin/documents.json', 'a') as f:
        from random import choice
        f.write(choice(foo)+"\n")
end = datetime.datetime.now()
print(end-start)
