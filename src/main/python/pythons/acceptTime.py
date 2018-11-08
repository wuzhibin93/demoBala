import time
import datetime
print(time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(time.time())))
now = datetime.datetime.now()
print(str(now.hour) +"----" +str(now.minute))