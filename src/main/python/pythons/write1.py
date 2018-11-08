# -*- coding: utf-8 -*-
# w直接写是清空原有内容重写
f = open('d:/pythons/a.java', 'w')
f.write("Hello World")
f.close()

with open('d:/pythons/a.java', 'w') as d:
    d.write("HELLO WORLD \n")

# a 是追加的形式，（append）
with open('d:/pythons/a.java', 'a') as g:
    g.write("HELLO WORLD")