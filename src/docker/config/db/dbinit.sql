CREATE DATABASE IF NOT EXISTS blog;
CREATE USER IF NOT EXISTS 'blog-application';
ALTER USER 'blog-application'@'%' IDENTIFIED WITH mysql_native_password BY 'secret';
GRANT ALL ON blog.* TO 'blog-application'@'%' ;
GRANT SUPER ON *.* TO 'blog-application'@'%';
FLUSH PRIVILEGES;