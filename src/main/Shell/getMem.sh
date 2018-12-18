#!/usr/bin/env bash
#获取总内存，单位为G
MemFree=`free -g |grep "Mem:" | awk '{print $2}'`
echo ${MemFree}

#获取cpu个数
cpu = `cat /proc/cpuinfo |grep "cpu cores" |uniq |awk -F: '{print $2}'`
echo ${cpu}