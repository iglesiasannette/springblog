CREATE database spring_blog_db;
USE spring_blog_db;
CREATE USER springblog_user@localhost IDENTIFIED BY 'password';
GRANT ALL ON spring_blog_db.* TO springblog_user@localhost;

