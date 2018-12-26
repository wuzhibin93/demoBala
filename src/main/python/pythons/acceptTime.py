import time
import datetime

tt = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+""
print(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"")
now = datetime.datetime.now()
t = "2017-11-24 17:30:00"
# 将其转换为时间数组
timeStruct = time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")
# 转换为时间戳:
timeStamp = str(time.mktime(time.strptime(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time()))+"", "%Y-%m-%d %H:%M:%S")))
print(timeStamp)

print(timeStamp[:-2])
