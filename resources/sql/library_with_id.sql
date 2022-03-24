use testdb001;

-- 用户信息表
-- account账户类型
drop table if exists l_user;
CREATE TABLE l_user(
       id        	int(11) NOT NULL AUTO_INCREMENT,
       username  	    varchar(40) NOT NULL,
       password  	    varchar(20) NOT NULL,
       sex       		int(1) DEFAULT 0,
       tel 		    varchar(11) DEFAULT 1,
       account	int(1)  DEFAULT 0,
       CONSTRAINT l_user_id_pk PRIMARY KEY (id),
       CONSTRAINT l_user_username_uk UNIQUE (username)
);

-- 图书信息表
drop table if exists l_book;
CREATE TABLE l_book(
       id 		int(11) NOT NULL AUTO_INCREMENT,
       book_name		varchar(40) NOT NULL,
       author		    varchar(50) DEFAULT NULL,
       publish         varchar(50) DEFAULT NULL,
       book_count      int(11) NOT NULL,
       price		    float(10,2),
    rent		    float(5,2),
    status		    int(1) DEFAULT 1,  -- 图书状态
    usable		    int(1) DEFAULT 0,  -- 上下架
    CONSTRAINT l_book_id_pk PRIMARY KEY (id)
);

-- 借阅信息表
drop table if exists l_record;
CREATE TABLE l_record(
       id	int(11) AUTO_INCREMENT,
       user_id		int(11) NOT NULL,
       username    varchar(40) NOT NULL,
       book_id	    int(11) NOT NULL,
       book_name   varchar(40) NOT NULL,
       lend_date	date,
       return_date	date,
       CONSTRAINT l_record_id_pk PRIMARY KEY (id)
);

-- 够买信息表
drop table if exists l_shop;
CREATE TABLE l_shop(
                       id	int(11) AUTO_INCREMENT,
                       user_id		int(11),
                       username    varchar(40) ,
                       book_id	    int(11) ,
                       book_name   varchar(40),
                       buy_time	date,
                       CONSTRAINT l_shop_id_pk PRIMARY KEY (id)
);

-- 添加用户数据
INSERT INTO l_user VALUES(1,"gg","123",1,"183666",1);
INSERT INTO l_user VALUES(2,"mm","123",0,"183667",1);
INSERT INTO l_user VALUES(3,"yeye","123456",0,"188111",0);
INSERT INTO l_user VALUES(4,"lucy","123456",0,"188112",0);
INSERT INTO l_user VALUES(5,"oken","123456",1,"188113",1);
INSERT INTO l_user VALUES(6,"godone","123456",1,"188114",0);

-- 添加图书数据
INSERT INTO `l_book` VALUES ('1', 'JAVA教程', '刘畅','外国出版社', '0', '999', '998', 1, 1);
INSERT INTO `l_book` VALUES ('2', 'C++教程', '罗比特',  '新华出版社', '10', '96', '98', 1, 1);
INSERT INTO `l_book` VALUES ('3', 'Python教程', '赛斯', '陕西出版社',  '10', '66', '100', 1, 1);
INSERT INTO `l_book` VALUES ('4', 'JS教程', '王能行','渭南出版社', '10', '58', '12', 1, 1);
INSERT INTO `l_book` VALUES ('5', 'JavaWeb基础', '孙红康', '尚硅谷',  '10', '83', '10', 1, 1);
INSERT INTO `l_book` VALUES ('6', 'Hadoop从入门到不可能',  '孙红康','尚硅谷', '11', '10', '1', 1, 1);
INSERT INTO `l_book` VALUES ('7', '爱情心理学',  '蓓邰', '宝鸡大学出版社', '12', '999', '999', 1, 1);
INSERT INTO `l_book` VALUES ('8', 'JAVA教程',  '张三','码歌教育出版社', '100', '46', '35', 1, 1);

-- 添加借阅数据
INSERT INTO l_record VALUES(1, 1, 'gg', '7', '爱情心理学', '2021-12-14', '2021-12-15');
INSERT INTO l_record VALUES(2, 4, 'lucy', '2', 'C++教程', '2021-12-14', '2021-12-15');
INSERT INTO l_record VALUES(3, 3, 'yeye', '3', 'Python教程', '2021-12-14', '2021-12-15');
INSERT INTO l_record VALUES(4, 2, 'mm', '5', 'JavaWeb基础', '2021-12-14', '2021-12-15');
INSERT INTO l_record VALUES(5, 1, 'gg', '6', 'Hadoop从入门到不可能', '2021-12-14', '2021-12-15');
INSERT INTO l_record VALUES(6, 1, 'gg', '6', 'Hadoop从入门到不可能', '2021-12-14', '2021-12-15');
INSERT INTO l_record VALUES(7, 5, 'oken', '1', 'JAVA教程', '2021-12-14', '2021-12-15');

-- 添加够买数据
INSERT INTO l_shop VALUES(1, 1, 'gg', '7', 'Python教程', '2021-12-14');
INSERT INTO l_shop VALUES(2, 4, 'lucy', '2', 'C++教程', '2021-12-14');
INSERT INTO l_shop VALUES(3, 3, 'yeye', '3', 'Python教程', '2021-12-14');
INSERT INTO l_shop VALUES(4, 2, 'mm', '5', 'JavaWeb基础', '2021-12-14');
INSERT INTO l_shop VALUES(5, 1, 'gg', '6', 'JS教程', '2021-12-14');
INSERT INTO l_shop VALUES(6, 1, 'ALiang', '6', '爱情心理学', '2021-12-14');
INSERT INTO l_shop VALUES(7, 5, 'oken', '1', 'JAVA教程', '2021-12-14');





