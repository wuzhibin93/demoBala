#!/usr/bin/env bash

yum -y install wget
yum -y install gcc gcc-c++
yum -y install zlib* bzip2-devel openssl-devel ncurses-devel sqlite-devel readline-devel tk-devel

if [ ! -d "/usr/local/python3" ]; then
    mkdir /usr/local/python3
else
    echo "文件夹已存在"
fi
cd /usr/local
echo "------download Python3------"
wget https://www.python.org/ftp/python/3.6.5/Python-3.6.5.tgz
tar -zxvf Python-3.6.5.tgz
cd Python-3.6.5
./configure -prefix=/usr/local/python3
make & make install
ln -s /usr/local/python3/bin/python3 /usr/bin/python3
sed -i "s/PATH\=\$PATH\:\$HOME\/bin/PATH\=\$PATH\:\$HOME\/bin\:\/usr\/local\/python3\/bin/g"  /root/.bash_profile
source ~/.bash_profile
ln -s /usr/local/python3/bin/pip3 /usr/bin/pip3

echo "update pip"
pip3 install --upgrade pip
echo "Install ESRally"
pip3 install esrally
ln -s /usr/local/python3/bin/esrally /usr/bin/esrally
cd ..
echo "Install git"
yum -y install curl-devel expat-devel gettext-devel openssl-devel zlib-devel gcc perl-ExtUtils-MakeMaker
yum -y install asciidoc xmlto autoconf
yum remove git
wget http://github.com/git/git/archive/v2.9.2.tar.gz
tar -zxvf v2.9.2.tar.gz
cd git-2.9.2
make configure
./configure --prefix=/usr/local/git-2.9.2 --with-iconv=/usr/local/libiconv
make all doc
make install install-doc install-html
ln -s /usr/local/git-2.9.2/bin/git /usr/bin/git

echo `python3 -V`
echo `pip3 -V`
echo `esrally --version`
echo `git --version`