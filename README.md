#### Elasticsearch 单节点安装.
1、复制文件
* elasticsearch-6.3.0.rpm
* jdk-8u172-linux-x64.rpm
* ingest-geoip-6.3.0.zip
* ingest-user-6.3.0.zip
* installES_singlepoint.sh

到目标服务器,且在一个目录下
         
2、修改 installES_singlepoint.sh 权限

```
chmod 755 installES_singlepoint.sh
```

3、运行install.sh脚本

```
./installES_singlepoint.sh
```


#### Elasticsearch集群安装.
1、复制文件
* elasticsearch-6.3.0.rpm
* jdk-8u172-linux-x64.rpm
* ingest-geoip-6.3.0.zip
* ingest-user-6.3.0.zip
* installES_cluster.sh

到目标服务器,且在一个目录下

2、修改 installES_cluster.sh 权限

```
chmod 755 installES_cluster.sh
```

3、运行install.sh脚本

在运行脚本的时候加上集群名称与集群中每个节点的ip地址

eg:
`./installES_cluster.sh <集群名称> <IP:PORT> <IP:PORT> <IP:PORT>`


  
