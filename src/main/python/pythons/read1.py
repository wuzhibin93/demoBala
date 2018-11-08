# -*- coding: utf-8 -*-
print('----第一次读取----')
fpath = r'd:\pythons\a.java'
with open(fpath, 'r') as f:
    s = f.read()
    print(s);

print('----第二次读取----')
try:
    f = open('d:\pythons\\a.java', 'r')
    print(f.read())
finally:
    if f:
        f.close()