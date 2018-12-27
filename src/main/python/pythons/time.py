import datetime
import time


def doSth():
    print("Message")


for x in list(range(24)):
    def main(h=x):
        while True:
            now = datetime.datetime.now()
            print(str(now.hour)+"--------"+str(now.minute))
            print(x)
            if now.hour == h:
                break
            time.sleep(60)
        doSth()

main()