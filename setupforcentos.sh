#!/bin/sh

#install nginx
nginx_install(){
	rpm -ivh http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm
	yum install nginx -y
}

nginx -v
if [ $? -ne 0 ];then 
	nginx_install
fi

yum -y install gcc gcc-c++ make wget initscripts

#install redis
redis_install(){
	cd /usr/local/src
	wget http://download.redis.io/redis-stable.tar.gz
	tar xzf redis-stable.tar.gz
	rm redis-stable.tar.gz -f
	cd redis-stable
	make
	mkdir /etc/redis /var/lib/redis
	cp src/redis-server src/redis-cli /usr/local/bin
	sed -e "s/^daemonize no$/daemonize yes/" -e "s/^# bind 127.0.0.1$/bind 127.0.0.1/" -e "s/^dir \.\//dir \/var\/lib\/redis\//" -e "s/^loglevel verbose$/loglevel notice/" -e "s/^logfile stdout$/logfile \/var\/log\/redis.log/" redis.conf > /etc/redis/redis.conf
	wget https://raw.githubusercontent.com/starhq/script/master/redis-server
	mv redis-server /etc/init.d
	chmod 755 /etc/init.d/redis-server
	chkconfig --add redis-server
	chkconfig --level 345 redis-server on
	systemctl start redis-server 
}

redis-server -v  
if [ $? -ne 0 ];then
	redis_install
fi


#install mongo
mongod_install(){
	echo '[10gen]' >> /etc/yum.repos.d/10gen.repo
	echo 'name=10gen Repository' >> /etc/yum.repos.d/10gen.repo
	echo 'baseurl=http://downloads-distro.mongodb.org/repo/redhat/os/x86_64' >> /etc/yum.repos.d/10gen.repo
	echo 'gpgcheck=0' >> /etc/yum.repos.d/10gen.repo
	echo 'enabled=1' >> /etc/yum.repos.d/10gen.repo

	yum install -y mongo-10gen mongo-10gen-server

	systemctl start mongod 
	chkconfig mongod on
}

mongod --version  
if [ $? -ne 0 ];then
	mongod_install
fi


#install jdk1.8.0_162
#need set url to choise new version of jdk
jdk_install(){
	chmod +x /tmp/jdk.rpm
	yum -y localinstall /tmp/jdk.rpm
	rm -f /tmp/jdk.rpm
	sed -i '$a\JAVA_HOME=/usr/java/jdk1.8.0_162'/etc/profile
	sed -i '$a\JRE_HOME=$JAVA_HOME/jre' /etc/profile
	sed -i '$a\PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin' /etc/profile
	sed -i '$a\CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib' /etc/profile
	sed -i '$a\export JAVA_HOME JRE_HOME PATH CLASSPATH' /etc/profile
	source /etc/profile
}
  
if [ ! -e /tmp/jdk.rpm ];then
	wget -O /tmp/jdk.rpm --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u162-b12/0da788060d494f5095bf8624735fa2f1/jdk-8u162-linux-x64.rpm"
	jdk_install
else
	jdk_install
fi


#install tomcat8.0
tomcat_install(){
	cd /tmp
	chmod +x tomcat.tar.gz
	tar -zxvf tomcat.tar.gz
	if [ -e "/usr/tomcat" ];then  
		rm -rf /usr/tomcat
		mv "apache-tomcat-8.0.46" /usr/tomcat
	else
		mv "apache-tomcat-8.0.46" /usr/tomcat
	fi 
	rm -f tomcat.tar.gz

	if [ ! -e /usr/tomcat/bin/setenv.sh ];then
		cat > /usr/tomcat/bin/setenv.sh<<EOF
		#!/bin/sh
		#add tomcat pid
		CATALINA_PID=\$CATALINA_BASE/tomcat.pid
		#add java opts
		JAVA_OPTS="-server -XX:PermSize=256M -XX:MaxPermSize=1024m -Xms512M -Xmx1024M -XX:MaxNewSize=256m"
EOF
		chmod +x /usr/tomcat/bin/setenv.sh
	fi

# create /usr/lib/systemd/system/tomcatd.service
	if [ ! -e /usr/lib/systemd/system/tomcatd.service ];then
		cat > /usr/lib/systemd/system/tomcatd.service<<EOF
		[Unit]
		Description=Tomcat
		After=syslog.target network.target remote-fs.target nss-lookup.target
		
		[Service]
		Type=forking
		PIDFile=/usr/tomcat/tomcat.pid
		ExecStart=/usr/tomcat/bin/startup.sh 
		ExecReload=/bin/kill -s HUP \$MAINPID
		ExecStop=/bin/kill -s QUIT \$MAINPID
		PrivateTmp=true
		
		[Install]
		WantedBy=multi-user.target
EOF
		chmod +x /usr/lib/systemd/system/tomcatd.service
	fi
	systemctl enable tomcatd.service
	systemctl start tomcatd.service
	cd ~
}

if [ ! -e /tmp/tomcat.tar.gz ];then
	wget -O /tmp/tomcat.tar.gz "http://mirror.bit.edu.cn/apache/tomcat/tomcat-8/v8.0.46/bin/apache-tomcat-8.0.46.tar.gz"
	tomcat_install
else
	tomcat_install
fi

#install mysql-server
mysql_install(){
	chmod +x /tmp/mysql.rpm
	yum -y localinstall /tmp/mysql.rpm
	rm -f /tmp/mysql.rpm
	yum -y install mysql-server
	systemctl enable mysqld.service
	systemctl start mysqld.service
	#systemctl status mysqld.service
	grep 'temporary password' /var/log/mysqld.log
	mysql_secure_installation
	read -p "input your password" mysqlpass
	mysql -uroot -p${mysqlpass} -e "
	set character_set_client=utf8;
	set character_set_connection=utf8;
	set character_set_database=utf8;
	set character_set_results=utf8;
	set character_set_server=utf8;
	show variables like 'character_set_%';
	quit"
}
if [ ! -e /tmp/mysql.rpm ];then
	wget -O /tmp/mysql.rpm http://dev.mysql.com/get/mysql57-community-release-el7-7.noarch.rpm
	mysql_install
else
	mysql_install
fi
