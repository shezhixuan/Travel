/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : localhost:3306
 Source Schema         : travel

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 24/05/2025 12:59:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (1, 1, 2, 'comment测试内容', '2025-03-27 19:52:19', '1234@qq.com');
INSERT INTO `comments` VALUES (2, 2, 2, 'commentcomment', '2025-03-27 20:17:47', '1234@qq.com');
INSERT INTO `comments` VALUES (3, 2, 1, '12345', '2025-03-27 12:25:52', '2939@qq.com');
INSERT INTO `comments` VALUES (4, 1, 1, '哈哈哈哈哈终于成了', '2025-03-27 12:27:09', '2939@qq.com');
INSERT INTO `comments` VALUES (5, 1, 1, '12121312121', '2025-03-27 15:08:30', '2939@qq.com');
INSERT INTO `comments` VALUES (6, 2, 1, '嘻嘻', '2025-03-28 08:37:33', '2939@qq.com');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` int(0) DEFAULT NULL,
  `rating` int(0) DEFAULT NULL,
  `facilities` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `tag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `region` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` timestamp(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (1, '杭州西湖国宾馆', '/images/xihubg.png', '位于西湖边的五星级酒店，环境优美，服务一流', '西湖区杨公堤18号', 1288, 5, 'wifi,pool,gym,restaurant,parking', '豪华', 'hangzhou', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (2, '全季酒店', '/images/quanji.png', '杭州大厦附近的舒适型酒店', '杭州大厦', 788, 3, 'wifi,parking', '舒适', 'hangzhou', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (3, '如家商旅酒店', '/images/rujia.png', '西湖湖滨银泰步行街附近的便捷酒店', '杭州西湖湖滨银泰步行街', 788, 5, 'wifi,parking,restaurant', '经济', 'hangzhou', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (4, '维景国际大酒店', '/images/weijing.png', '西湖景区内的豪华酒店', '杭州西湖景区', 788, 4, 'wifi,pool,restaurant,gym', '豪华', 'hangzhou', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (5, '宁波柏悦酒店', '/images/boyue.png', '东钱湖畔的奢华度假酒店', '东钱湖大堰路188号', 1588, 5, 'wifi,pool,restaurant,sea-view', '豪华', 'ningbo', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (6, '温州香格里拉大酒店', '/images/xianggeli.png', '瓯江畔的五星级酒店', '鹿城区香源路1号', 988, 5, 'wifi,gym,restaurant,river-view', '特惠', 'wenzhou', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (7, '乌镇枕水度假酒店', '/images/zhenshui.png', '乌镇景区内的特色酒店', '桐乡市乌镇景区内', 699, 4, 'wifi,ancient-town-view,restaurant', '舒适', 'jiaxing', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (8, '莫干山裸心谷', '/images/luoxingu.png', '莫干山高端度假村', '德清县莫干山镇', 2588, 5, 'wifi,pool,spa,mountain-view', '高档', 'huzhou', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (9, '绍兴咸亨酒店', '/images/xianheng.png', '鲁迅文化主题酒店', '越城区鲁迅中路179号', 588, 4, 'wifi,cultural-experience,restaurant', '经济', 'shaoxing', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (10, '横店国贸大厦', '/images/hengdian.png', '横店影视城主题酒店', '东阳市横店镇', 488, 5, 'wifi,gym,movie-theme', '特惠', 'jinhua', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (11, '舟山朱家尖绿城威斯汀度假酒店', '/images/weisi.png', '海景度假酒店', '普陀区朱家尖假日路1288号', 1388, 4, 'wifi,private-beach,pool,sea-view', '豪华', 'zhoushan', '2025-05-03 15:52:28');
INSERT INTO `hotel` VALUES (12, '天台山温泉度假酒店', '/images/wenquan.png', '温泉主题度假酒店', '天台县国清寺旁', 787, 5, 'wifi,hot-spring,mountain-view', '高档', 'taizhou', '2025-05-03 15:52:28');

-- ----------------------------
-- Table structure for index_spot
-- ----------------------------
DROP TABLE IF EXISTS `index_spot`;
CREATE TABLE `index_spot`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` int(0) DEFAULT NULL,
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `region` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created_at` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of index_spot
-- ----------------------------
INSERT INTO `index_spot` VALUES (1, '西湖', '/images/xihu.png', '杭州的标志性景点，世界文化遗产', '杭州', 63, 'scenery', 'hangzhou', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (2, '西溪湿地', '/images/xixishidi.png', '集城市湿地、农耕湿地、文化湿地于一体的国家湿地公园', '杭州', 80, 'scenery', 'hangzhou', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (3, '千岛湖', '/images/qiandaohu.png', '湖中有1078个岛屿，风景秀丽', '杭州', 130, 'scenery', 'hangzhou', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (4, '宋城', '/images/songcheng.png', '大型宋代文化主题公园', '杭州', 320, 'entertainment', 'hangzhou', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (5, '天一阁·月湖', '/images/yuehu.png', '中国现存最早的私家藏书楼', '宁波', 90, 'culture', 'ningbo', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (6, '雪窦山·溪口滕头', '/images/xikou.png', '佛教名山与蒋氏故里', '宁波', 80, 'scenery', 'ningbo', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (7, '宁波老外滩', '/images/waitan.png', '百年商埠，中西合璧', '宁波', 60, 'culture', 'ningbo', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (8, '东钱湖旅游度假区', '/images/dongqianhu.png', '浙江第一大淡水湖', '宁波', 130, 'scenery', 'ningbo', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (9, '雁荡山', '/images/yandangshan.png', '东南第一山，世界地质公园', '温州', 60, 'scenery', 'wenzhou', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (10, '楠溪江', '/images/nanxijiang.png', '以水秀、岩奇、瀑多、村古、滩林美而闻名', '温州', 70, 'scenery', 'wenzhou', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (11, '南湖', '/images/nanhu.png', '中国革命红船起航地', '嘉兴', 200, 'culture', 'jiaxing', '2025-05-02 22:39:37');
INSERT INTO `index_spot` VALUES (12, '乌镇', '/images/wuzhen.png', '中国历史文化名镇，典型的水乡古镇', '嘉兴', 300, 'culture', 'jiaxing', '2025-05-02 22:39:37');

-- ----------------------------
-- Table structure for post_details
-- ----------------------------
DROP TABLE IF EXISTS `post_details`;
CREATE TABLE `post_details`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(0) NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `travel_date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `likes` int(0) DEFAULT 0,
  `views` int(0) DEFAULT 0,
  `updated_at` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  CONSTRAINT `post_details_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_details
-- ----------------------------
INSERT INTO `post_details` VALUES (1, 1, '杭州西湖', '2023-05-15', 12, 157, '2023-06-10 14:30:00');
INSERT INTO `post_details` VALUES (2, 2, '宁波东钱湖', '2023-06-20', 8, 98, '2023-07-05 09:15:00');
INSERT INTO `post_details` VALUES (3, 3, '温州雁荡山', '2023-04-10', 25, 211, '2023-05-22 16:45:00');
INSERT INTO `post_details` VALUES (4, 4, '绍兴鲁迅故里', NULL, 5, 74, '2023-08-12 11:20:00');
INSERT INTO `post_details` VALUES (5, 5, '舟山普陀山', NULL, 18, 193, '2023-09-01 10:00:00');
INSERT INTO `post_details` VALUES (6, 6, '湖州南浔古镇', '2023-07-08', 32, 460, '2023-08-30 15:30:00');
INSERT INTO `post_details` VALUES (10, 10, NULL, NULL, 0, 0, '2025-05-06 12:29:08');
INSERT INTO `post_details` VALUES (11, 11, NULL, NULL, 0, 0, '2025-05-06 12:29:33');
INSERT INTO `post_details` VALUES (12, 12, NULL, NULL, 0, 0, '2025-05-06 12:29:51');
INSERT INTO `post_details` VALUES (13, 13, NULL, NULL, 0, 0, '2025-05-06 12:30:04');
INSERT INTO `post_details` VALUES (14, 14, NULL, NULL, 0, 0, '2025-05-06 12:31:20');
INSERT INTO `post_details` VALUES (15, 15, NULL, NULL, 0, 0, '2025-05-06 12:31:31');
INSERT INTO `post_details` VALUES (16, 16, NULL, NULL, 0, 0, '2025-05-06 12:31:43');
INSERT INTO `post_details` VALUES (17, 17, NULL, NULL, 0, 0, '2025-05-06 12:31:55');
INSERT INTO `post_details` VALUES (18, 18, NULL, NULL, 0, 0, '2025-05-06 12:32:09');
INSERT INTO `post_details` VALUES (19, 19, NULL, NULL, 0, 0, '2025-05-06 12:32:20');
INSERT INTO `post_details` VALUES (20, 20, NULL, NULL, 0, 1, '2025-05-06 12:32:34');
INSERT INTO `post_details` VALUES (21, 21, NULL, NULL, 0, 4, '2025-05-06 12:32:46');
INSERT INTO `post_details` VALUES (22, 22, NULL, NULL, 0, 0, '2025-05-08 06:30:44');

-- ----------------------------
-- Table structure for post_images
-- ----------------------------
DROP TABLE IF EXISTS `post_images`;
CREATE TABLE `post_images`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(0) NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  CONSTRAINT `post_images_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_images
-- ----------------------------
INSERT INTO `post_images` VALUES (1, 1, 'null');
INSERT INTO `post_images` VALUES (2, 1, 'null');

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `topic` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of posts
-- ----------------------------
INSERT INTO `posts` VALUES (1, 1, '2939@qq.com', 'post测试标题', 'post测试内容', '2025-03-27 19:51:40', '2025-05-04 17:10:43', '#西湖攻略');
INSERT INTO `posts` VALUES (2, 1, '1234@qq.com', '2_post测试内容', 'postpost', '2025-03-27 20:16:54', '2025-05-04 17:10:43', '#美食推荐');
INSERT INTO `posts` VALUES (3, 1, '2939@qq.com', '帖子测试标题', '帖子测试内容', '2025-03-28 08:37:59', '2025-05-04 17:10:43', '#自驾游');
INSERT INTO `posts` VALUES (4, 1, '2939@qq.com', '帖子增加积分测试标题', '帖子增加积分测试内容', '2025-04-03 12:18:47', '2025-05-04 17:10:43', '#亲子游');
INSERT INTO `posts` VALUES (5, 1, '2939@qq.com', '帖子测试标题2', '帖子测试标题', '2025-04-03 12:19:35', '2025-05-04 17:10:43', '#摄影点');
INSERT INTO `posts` VALUES (6, 1, '2939@qq.com', '帖子测试标题12.34', '111', '2025-04-06 04:34:51', '2025-05-04 17:10:43', '#民宿体验');
INSERT INTO `posts` VALUES (10, 1, '2939@qq.com', '西湖一日游完美路线分享', '清晨从断桥出发，沿着白堤漫步，湖面薄雾缭绕如仙境。9点前到达雷峰塔可避开人流，顶层俯瞰全景值得排队！中午推荐楼外楼醋鱼，下午乘摇橹船游三潭印月（记得带1元纸币拍照）。傍晚的苏堤是摄影黄金时间，长桥落日美到窒息。步行全程约15公里，穿舒适鞋子很重要。唯一遗憾是没预约到浙江博物馆，下次一定提前三天订票！', '2025-05-06 12:29:08', '2025-05-06 20:29:08', '#西湖攻略');
INSERT INTO `posts` VALUES (11, 1, '2939@qq.com', '避开人潮的西湖小众玩法', '发现西湖西线茅家埠才是宝藏！从郭庄开始，沿杨公堤骑行，沿途黛色参天、乌龟潭静谧得像世外桃源。这里游客稀少，本地人晨练居多，还能看到鸳鸯和夜鹭。于谦祠旁的茶室人均40元，龙井配藕粉绝了。建议下午4点后前往，光线柔和适合拍古风。相比断桥的人挤人，这里才能真正感受\"山色空蒙雨亦奇\"的意境。记得带驱蚊水，水边蚊虫较多。', '2025-05-06 12:29:33', '2025-05-06 20:30:49', '#西湖攻略');
INSERT INTO `posts` VALUES (12, 1, '2939@qq.com', '杭州必吃的片儿川地图', '作为面食爱好者，实测8家老字号后推荐：奎元馆的雪菜笋片肉片比例完美，汤头醇厚；菊英面店的猪油渣是灵魂，记得加一份；方老大面腰花处理得毫无腥味，爆炒镬气十足。最惊喜是中山南路的无名小店，老板用富阳手工面，筋道到能弹牙！吃片儿川要赶早，午市后汤底会变咸。搭配一瓶冰镇酸梅汤，就是老杭州的夏日限定快乐。价格15-35元不等，游客别去景区旁的那些\"网红店\"。', '2025-05-06 12:29:51', '2025-05-06 20:29:51', '#美食推荐');
INSERT INTO `posts` VALUES (13, 1, '2939@qq.com', '河坊街不踩雷小吃指南', '河坊街主街都是坑！拐进大井巷才有真美食：定胜糕要买蒋顺发的，现蒸的豆沙馅微甜不腻；龙翔桥臭豆腐认准\"老奶奶摊\"，配的甜辣酱是二十年前的老味道；福缘居的油墩儿外脆里嫩，萝卜丝清甜。特别推荐鼓楼旁的周萍粽子，蛋黄肉粽剥开流油，糯米吸饱肉香。这些摊位都只收现金，记得备零钱。傍晚5点后出摊最全，但要做好排队准备，本地人也爱买！', '2025-05-06 12:30:04', '2025-05-06 20:30:55', '#美食推荐');
INSERT INTO `posts` VALUES (14, 1, '2939@qq.com', '千岛湖环湖自驾终极攻略', '上海出发3小时直达，建议逆时针环湖：淳开线风景最佳，有12个观景台（\"红叶湾\"和\"小金山\"必停）。中午在姜家镇吃鱼头，老雷家的奶汤鱼头配紫苏绝了！下午开完淳杨线，这段路临水而建像海上公路。住推荐\"玄山云居\"，民宿有私人码头可划皮艇。全程180公里，70%沿湖路段，新手建议分两天开。特别注意：夏季午后易有团雾，导航记得选\"避开高速\"才能走湖边！', '2025-05-06 12:31:20', '2025-05-06 20:31:19', '#自驾游');
INSERT INTO `posts` VALUES (15, 1, '2939@qq.com', '四明山盘山公路惊险之旅', '号称\"浙江秋名山\"的四明山S213省道名不虚传！从梁弄镇出发，连续发卡弯多达32个，海拔骤升800米。观景台\"白鹿狮峰\"可拍云海，但停车位仅5个要抢早。途经四明湖水杉林，11月红叶季美如油画。午餐在大岚镇的农家乐，盐烤土豆和溪鱼必点。下山走细北线更刺激，部分路段仅容一车通过。建议开涡轮增压车型，自吸车爬坡会吃力。全程手机信号时有时无，提前下载离线地图！', '2025-05-06 12:31:31', '2025-05-06 20:31:31', '#自驾游');
INSERT INTO `posts` VALUES (16, 1, '2939@qq.com', '带娃玩转杭州野生动物世界', '亲子年卡用户经验：开园直奔小火车，10点前不用排队。投喂长颈鹿（20元/枝）孩子超兴奋，但小心被口水糊脸！熊猫馆空调足适合午休，12:30有饲养员讲解。最惊喜是亲子乐园全免费，旋转木马连玩5次不收费。建议自带辅食，餐厅又贵又难吃。必带推车（可租但脏）、防晒帽、换洗衣物（泼水节区域会湿身）。下午3点的白虎跳水表演别错过，前排可能会被水花溅到哦！', '2025-05-06 12:31:43', '2025-05-06 20:31:43', '#亲子游');
INSERT INTO `posts` VALUES (17, 1, '2939@qq.com', '良渚古城遗址亲子考古日', '预约\"小小考古家\"活动太值了！先在遗址公园坐电瓶车寻访五千年前的水坝，孩子领到仿制玉琮拼图。考古体验区能用专业工具挖\"文物\"（其实是预埋的陶片），博士会讲解如何辨别年代。鹿苑可喂食，梅花鹿会鞠躬讨食萌化了。建议带：放大镜、笔记本（盖章打卡）、便携折叠凳。最后去良渚博物院看真品，AR设备让玉器\"活\"起来。全程步行约6公里，4岁以下建议带背带。', '2025-05-06 12:31:55', '2025-05-06 20:31:54', '#亲子游');
INSERT INTO `posts` VALUES (18, 1, '2939@qq.com', '西溪湿地摇橹船摄影秘籍', '秋芦飞雪时节最佳！周家村码头包船（100元/小时）建议约7:30首班，晨雾中的芦苇逆光绝美。穿汉服拍要选深色系，浅色会过曝。重点机位：烟水渔庄的桑树滩（框架构图）、深潭口的百年香樟（低角度仰拍）。船夫知道所有隐蔽水道，加50元可去非开放区。设备建议：70-200mm长焦必备，偏振镜消除水面反光。冬季下午4点后就光线不足，要把握黄金两小时！', '2025-05-06 12:32:09', '2025-05-06 20:32:09', '#摄影点');
INSERT INTO `posts` VALUES (19, 1, '2939@qq.com', '钱江新城城市风光拍摄指南', '城市阳台8号观景台是拍\"日月同辉\"的绝佳机位！夏季傍晚6:30-7:00，奥体中心亮灯时刚好能捕捉晚霞。广角镜头需贴近地面拍倒影，ND减光镜必备。市民中心楼顶需登记身份证进入，俯拍车流用慢门15秒。隐藏彩蛋：城市阳台下方隧道有对称几何结构，超适合赛博朋克风格。每月1日、15日有灯光秀，但要提前2小时占位。记得带三脚架，江边风大需配重！', '2025-05-06 12:32:20', '2025-05-06 20:32:20', '#摄影点');
INSERT INTO `posts` VALUES (20, 1, '2939@qq.com', '莫干山\"隐西39\"泡池房测评', '竹林深处的日式温泉民宿令人惊艳！房内私汤是火山岩砌成，管家会提前撒玫瑰花瓣。270度落地窗外就是竹海，雨天云雾缭绕像水墨画。早餐九宫格套餐颜值超高，夜宵的鸡汤馄饨免费供应。细节满分：地暖、Muji香薰机、Marvis牙膏。唯一缺点：山间潮湿，相机镜头易起雾要放防潮箱。建议住\"天青\"房型，露台正对山谷，清晨能看到云海流动。宠物友好，带狗需加收200元清洁费。', '2025-05-06 12:32:34', '2025-05-06 20:32:34', '#民宿体验');
INSERT INTO `posts` VALUES (21, 1, '2939@qq.com', '桐庐\"大山初里\"洞穴风体验', '由日本设计师改造的夯土建筑太特别了！我们住的\"空林\"房型有7米挑高，穹顶天窗可看星空。浴室用的全是APPELLES备品，浴缸对着整面落地窗（放心，外面是悬崖）。下午茶的手打麻糍配桂花蜜绝配，晚餐150元/人的农家宴有惊喜（石锅豆腐用了三种菌菇）。管家可安排徒步路线，后山的古道通向废弃萤石矿，拍照很有末世感。注意：民宿在海拔600米处，盘山路最后3公里非常陡峭！', '2025-05-06 12:32:46', '2025-05-06 20:32:45', '#民宿体验');
INSERT INTO `posts` VALUES (22, 1, '2939@qq.com', '西湖一日游完美路线分享（2）', '1111', '2025-05-08 06:30:44', '2025-05-08 14:30:44', '#西湖攻略');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` int(0) NOT NULL,
  `stock` int(0) NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '14寸小型拉杆行李箱', 200, 78, '/images/xiang1.png');
INSERT INTO `product` VALUES (2, '晴雨两用遮阳伞', 80, 88, '/images/san.png');
INSERT INTO `product` VALUES (3, '出行收纳神器', 100, 92, '/images/xiang2.png');
INSERT INTO `product` VALUES (4, '男女通用旅行包', 100, 96, '/images/bao.png');
INSERT INTO `product` VALUES (5, '轻巧旅行水杯', 50, 98, '/images/bei.png');
INSERT INTO `product` VALUES (6, '防晒霜', 80, 96, '/images/fangshai.png');
INSERT INTO `product` VALUES (7, '零食大礼包', 90, 100, '/images/lingshi.png');
INSERT INTO `product` VALUES (8, '户外帐篷', 400, 100, '/images/zhangpeng.png');
INSERT INTO `product` VALUES (9, '防晒眼镜', 60, 100, '/images/yanjing.png');

-- ----------------------------
-- Table structure for product_purchase
-- ----------------------------
DROP TABLE IF EXISTS `product_purchase`;
CREATE TABLE `product_purchase`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NOT NULL,
  `product_id` int(0) NOT NULL,
  `price_paid` int(0) NOT NULL,
  `purchase_date` datetime(0) NOT NULL,
  `quantity` int(0) DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `product_purchase_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product_purchase_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_purchase
-- ----------------------------
INSERT INTO `product_purchase` VALUES (1, 1, 2, 200, '2025-05-15 18:37:19', 1);
INSERT INTO `product_purchase` VALUES (2, 1, 3, 300, '2025-05-06 18:37:38', 1);
INSERT INTO `product_purchase` VALUES (3, 1, 4, 400, '2025-05-14 18:37:51', 1);
INSERT INTO `product_purchase` VALUES (4, 1, 2, 80, '2025-05-07 03:35:46', 1);
INSERT INTO `product_purchase` VALUES (5, 1, 3, 100, '2025-05-07 03:35:49', 1);
INSERT INTO `product_purchase` VALUES (6, 1, 1, 200, '2025-05-07 03:35:53', 1);
INSERT INTO `product_purchase` VALUES (7, 1, 5, 50, '2025-05-07 03:35:56', 1);
INSERT INTO `product_purchase` VALUES (8, 1, 6, 80, '2025-05-07 03:35:59', 1);
INSERT INTO `product_purchase` VALUES (9, 1, 4, 100, '2025-05-07 03:36:02', 1);
INSERT INTO `product_purchase` VALUES (10, 1, 1, 200, '2025-05-07 08:03:51', 1);
INSERT INTO `product_purchase` VALUES (11, 1, 2, 80, '2025-05-08 06:30:59', 1);
INSERT INTO `product_purchase` VALUES (12, 1, 3, 100, '2025-05-08 06:31:03', 1);

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `spot_id` bigint(0) DEFAULT NULL,
  `user_id` bigint(0) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `created_at` timestamp(0) DEFAULT NULL,
  `rating` int(0) DEFAULT 0,
  `spot_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (1, 1, 1, '2939@qq.com', '好好好', NULL, 0, '西湖');
INSERT INTO `review` VALUES (30, 1, 1, '2939@qq.com', '西湖风景非常优美，湖水清澈，环境干净整洁，服务人员态度友好，是一次非常愉快的体验！', '2025-05-06 15:53:35', 5, '西湖');
INSERT INTO `review` VALUES (31, 3, 3, '2939@qq.com', '千岛湖的景色太棒了，游船项目很有趣，导游讲解专业，周边餐饮选择也多，强烈推荐！', '2025-05-06 16:04:25', 4, '千岛湖');
INSERT INTO `review` VALUES (32, 4, 4, '2939@qq.com', '古镇保存完好，古色古香的建筑很有韵味，夜晚的灯光秀非常漂亮，值得一来。', '2025-05-06 16:04:49', 5, '宋城');
INSERT INTO `review` VALUES (33, 1, 1, '2939@qq.com', '环境清幽，空气清新，寺庙建筑精美，僧侣们很和善，适合静心修行。', '2025-05-06 16:05:05', 4, '西湖');
INSERT INTO `review` VALUES (34, 5, 5, '2939@qq.com', '民宿环境优雅，房间干净舒适，周边自然风光秀丽，服务人员专业周到。', '2025-05-06 16:11:17', 4, '垂云通天河');
INSERT INTO `review` VALUES (36, 1, 1, '2939@qq.com', '西湖清晨特别美，薄雾笼罩着小桥流水，游人不多的时候很有意境', '2025-05-06 16:12:46', 5, '西湖');
INSERT INTO `review` VALUES (37, 4, 4, '2939@qq.com', '杭州宋城主题公园的演出非常震撼，演员专业，场景布置精美，仿佛穿越回古代。', '2025-05-06 16:13:05', 4, '宋城');
INSERT INTO `review` VALUES (38, 4, 4, '2939@qq.com', '景区管理混乱，售票处排长队，工作人员态度恶劣，厕所脏乱差，非常失望！', '2025-05-06 16:13:41', 2, '宋城');
INSERT INTO `review` VALUES (39, 6, 6, '2939@qq.com', '所谓的5A级景区名不副实，设施老旧，垃圾随处可见，门票价格完全不值这个价。', '2025-05-06 16:14:16', 1, '钱塘江');
INSERT INTO `review` VALUES (40, 5, 5, '2939@qq.com', '导游强制购物，在商店停留时间比景点还长，讲解敷衍了事，体验极差。', '2025-05-06 16:14:38', 1, '垂云通天河');
INSERT INTO `review` VALUES (41, 1, 1, '2939@qq.com', '节假日人山人海，根本看不到什么风景，全是人头，管理方应该限制客流', '2025-05-06 16:14:50', 2, '西湖');
INSERT INTO `review` VALUES (42, 4, 4, '2939@qq.com', '景点整体还可以，风景不错但也没有特别惊艳，服务中规中矩，门票价格适中。', '2025-05-06 16:16:09', 4, '宋城');
INSERT INTO `review` VALUES (43, 5, 5, '2939@qq.com', '周末带孩子来玩，设施比较普通，孩子玩得还算开心，但不会专门再来第二次。', '2025-05-06 16:16:21', 3, '垂云通天河');
INSERT INTO `review` VALUES (44, 4, 4, '2939@qq.com', '古镇保存得还行，商业化程度较高，商铺很多，适合随便逛逛但不要期待太高。', '2025-05-06 16:16:43', 4, '宋城');
INSERT INTO `review` VALUES (45, 4, 4, '2939@qq.com', '周边服务丰富', '2025-05-06 16:17:51', 5, '宋城');
INSERT INTO `review` VALUES (46, 4, 4, '2939@qq.com', '交通便利，想去一次真的很方便', '2025-05-06 16:19:00', 4, '宋城');
INSERT INTO `review` VALUES (47, 1, 1, '2939@qq.com', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', '2025-05-08 06:29:39', 4, '西湖');

-- ----------------------------
-- Table structure for scenic_spot
-- ----------------------------
DROP TABLE IF EXISTS `scenic_spot`;
CREATE TABLE `scenic_spot`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `created_at` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_spot
-- ----------------------------
INSERT INTO `scenic_spot` VALUES (1, '西湖', '/images/xihu.png', '杭州西湖，中国著名淡水湖，世界文化遗产，四季如画，人间天堂。', '2025-03-04 12:31:06', '杭州', 60);
INSERT INTO `scenic_spot` VALUES (2, '西溪湿地', '/images/xixishidi.png', '杭州西溪湿地，城市生态绿肺，水网纵横，野趣盎然，国家5A景区。', '2025-03-04 12:35:17', '杭州', 80);
INSERT INTO `scenic_spot` VALUES (3, '千岛湖', '/images/qiandaohu.png', '千岛湖，碧水千岛，生态绝佳，农夫山泉源地，长三角度假胜地。', '2025-03-04 12:35:38', '杭州', 130);
INSERT INTO `scenic_spot` VALUES (4, '宋城', '/images/songcheng.png', '杭州宋城，穿越千年，梦回南宋，大型实景演出，沉浸式文化体验。', '2025-03-04 12:35:51', '杭州', 320);
INSERT INTO `scenic_spot` VALUES (5, '垂云通天河', '/images/xixishidi.png', '垂云通天河，地下溶洞奇观，钟乳石林，暗河泛舟，自然艺术殿堂。', '2025-03-04 12:36:02', '杭州', 200);
INSERT INTO `scenic_spot` VALUES (6, '钱塘江', '/images/xixishidi.png', '钱塘江，浙江母亲河，天下第一潮，壮观澎湃，自然奇观。', '2025-03-04 12:36:24', '杭州', 130);
INSERT INTO `scenic_spot` VALUES (7, '龙井村', '/images/longjingcun.png', '龙井村，西湖茶乡，千年贡茶，狮峰龙井，茶香四溢醉游人。', '2025-04-09 13:10:12', '杭州', 160);
INSERT INTO `scenic_spot` VALUES (8, '九溪烟树', '/images/jiuxi.png', '九溪烟树，曲径通幽，溪涧潺潺，红叶斑斓，杭州秘境仙踪。', '2025-04-15 13:10:50', '杭州', 80);
INSERT INTO `scenic_spot` VALUES (9, '良渚古城遗址', '/images/gucheng.png', '良渚古城遗址，中华五千年文明实证，世界遗产，玉器之都，考古圣地。', '2025-04-25 13:11:29', '杭州', 100);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `spot_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `spot_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `spot_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `order_time` timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'PENDING',
  `original_price` double(10, 2) DEFAULT NULL,
  `actual_price` double(255, 0) DEFAULT NULL,
  `points_used` int(0) DEFAULT NULL,
  `points_earned` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (2, 1, 1, '小a', '111111', '西湖', 'static/images/image1.png', '2025-03-08 02:00:52', 'PENDING', 500.00, 500, 0, 0);
INSERT INTO `t_order` VALUES (4, 1, 1, '2939@qq.com', '122122122', '西湖', '/images/xihu.png', '2025-04-02 10:56:55', 'PAID', 99.00, 99, 0, 990);
INSERT INTO `t_order` VALUES (5, 1, 1, '2939@qq.com', '11111111111', '西湖', '/images/xihu.png', '2025-04-02 11:19:41', 'CANCELLED', 99.00, 82, 1734, 816);
INSERT INTO `t_order` VALUES (6, 1, 1, '2939@qq.com', '11111111111', '西湖', '/images/xihu.png', '2025-04-02 12:38:03', 'PAID', 99.00, 97, 193, 970);
INSERT INTO `t_order` VALUES (7, 1, 1, '2939@qq.com', '11111111111', '西湖', '/images/xihu.png', '2025-04-06 04:35:26', 'PAID', 99.00, 95, 364, 953);
INSERT INTO `t_order` VALUES (8, 1, 1, '2939@qq.com', '222222222222', '西湖', '/images/xihu.png', '2025-04-06 04:37:20', 'PAID', 99.00, 90, 851, 904);
INSERT INTO `t_order` VALUES (9, 1, 1, '2939@qq.com', '333333333333333333', '西湖', '/images/xihu.png', '2025-04-06 04:38:30', 'PAID', 99.00, 81, 1763, 813);
INSERT INTO `t_order` VALUES (10, 1, 1, '2939@qq.com', '222222', '西湖', '/images/xihu.png', '2025-04-06 04:42:24', 'PAID', 99.00, 90, 883, 901);
INSERT INTO `t_order` VALUES (11, 1, 1, '2939@qq.com', '123456', '西湖', '/images/xihu.png', '2025-05-04 04:20:52', 'CANCELLED', 99.00, 94, 491, 940);
INSERT INTO `t_order` VALUES (12, 1, 1, '2939@qq.com', '123456', '西湖', '/images/xihu.png', '2025-05-04 04:35:16', 'CANCELLED', 99.00, 94, 535, 936);
INSERT INTO `t_order` VALUES (13, 1, 1, '2939@qq.com', '123456', '西湖', '/images/xihu.png', '2025-05-08 06:29:55', 'PAID', 60.00, 54, 593, 540);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `points` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2939@qq.com', '20030621', 'USER', 828);
INSERT INTO `user` VALUES (2, '1234@qq.com', '1234', 'ADMIN', 102);
INSERT INTO `user` VALUES (3, '9999@qq.com', '1234', 'ADMIN', 103);
INSERT INTO `user` VALUES (4, '8888@qq.com', '1234', 'USER', 10);

SET FOREIGN_KEY_CHECKS = 1;
