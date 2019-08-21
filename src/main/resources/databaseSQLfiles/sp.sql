DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `addComment`(IN COMMENT_BODY varchar(255), IN POSTID int, IN USERID int, IN USERNAME varchar(45))
BEGIN
	insert into comments (comment_body, post_id, user_id, commentBy) values (COMMENT_BODY,POSTID,USERID,USERNAME);
    UPDATE post SET modification_date = now() where id = POSTID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `addImage`(IN IMAGE_LINK varchar(255),
IN POST_ID int)
BEGIN
	insert into postimages(image_link, post_id) values(IMAGE_LINK, POST_ID);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `addPost`(IN POST_TITLE varchar(255), IN POST_DESC varchar(255),IN USERID int, IN CATEGORY int, IN GROUPID int, IN IS_REPORTED int, IN IS_IMAGE int, OUT out_param int)
BEGIN
	insert into post(post_title, post_desc, user_id, category, group_id,is_image, report) values(POST_TITLE, POST_DESC, USERID, CATEGORY, GROUPID,IS_IMAGE , IS_REPORTED);
    SET out_param = LAST_INSERT_ID();
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `addReply`(IN reply varchar(255), IN USERID int, IN COMMENTID int, IN REPLYNAME varchar(45))
BEGIN
	DECLARE post_id INT;
	insert into replies (description,user_id,comment_id, replyBy) values (reply,USERID,COMMENTID,REPLYNAME);
	select post_id from comments where id = COMMENTID INTO post_id;
    UPDATE post SET modification_date = now() where id = post_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `addReportingPost`(IN USERID int, IN POSTID int)
BEGIN
    INSERT into reportingposts (user_id, post_id) values (USERID,POSTID);
	UPDATE post
    SET report = report + 1
    WHERE id = POSTID;
	
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `addSubscriptionRequest`(IN NEWSTATUS boolean ,IN USERID int, IN GROUPID int)
BEGIN
	INSERT into subscription (status, user_id, group_id) values (NEWSTATUS,USERID,GROUPID);
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `approveSubscriptionRequest`(IN subscription_id int)
BEGIN
	UPDATE CSCI5308_6_DEVINT.subscription 
    SET status =1 
    where id = subscription_id;
   
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `deletePostById`(IN PostID int)
BEGIN
	DELETE FROM post where id = PostID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `fetchAllApprovedRequests`(IN userID int)
BEGIN
select g.name, s.user_id, s.group_id from groups g, subscription s 
where s.group_id = g.id and s.status = 1 and s.user_id = userID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `fetchAllSubscriptionRequests`()
BEGIN
	SELECT * FROM subscription where status = 0;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `fetchGroupsByUserId`(IN userid INT, out gid int, out gname varchar(50))
BEGIN
	
	SELECT g.id, g.name into gid, gname FROM CSCI5308_6_DEVINT.`groups` g WHERE g.id IN (
	
	SELECT group_id FROM CSCI5308_6_DEVINT.subscription WHERE user_id = userid
	);
	END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `fetchreportedPosts`(IN USERID int)
BEGIN
	select * from reportingposts where user_id = USERID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `fetchreportedPostsByUserID`(IN USERID int)
BEGIN
	select * from reportingposts where user_id = USERID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `fetchSubscriptionByID`(IN SubID int)
BEGIN
SELECT * FROM CSCI5308_6_DEVINT.subscription where id = SubID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `fetchSubscriptionByUserId`(IN USERID int)
BEGIN
	SELECT * FROM CSCI5308_6_DEVINT.subscription where user_id = USERID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getAdmin`()
BEGIN
	SELECT * FROM CSCI5308_6_DEVINT.` user` where role = 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getAllActivePosts`()
BEGIN
	SELECT * FROM post p where p.is_alive = 1;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getAllPosts`()
BEGIN
	SELECT * FROM post;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getCommentByName`()
BEGIN
	select comments.*, u.first_name as comment_by from comments, ` user` u where comments.user_id = u.id;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getCommentsByPostId`(IN postId int)
BEGIN
	SELECT * FROM comments where post_id = postId;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getImagesByPostId`(IN postId int)
BEGIN
	SELECT * FROM postimages where post_id = postId;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getPostById`(IN post_id int)
BEGIN
	SELECT * FROM post where id = post_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getPostsByGroupId`(IN GROUPID int)
BEGIN
	select * from post where group_id = GROUPID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getPostsByMaxReports`()
BEGIN
	select p.post_title,p.report as reportCount, u.id as USERID, u.first_name, u.email from post p, ` user` u where p.user_id = u.id order by p.report desc;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getPostsByUserID`(IN USERID int)
BEGIN
	SELECT * FROM post where user_id = USERID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getPrivatePostsByGroupID`(IN GROUPID int )
BEGIN
	SELECT * FROM post where group_id = GROUPID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getReplyByCommentId`(IN commentId int)
BEGIN
	SELECT * FROM replies where comment_id = commentId;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getSearchPost`(IN search varchar(400))
BEGIN
	select * from post where post_title LIKE CONCAT('%',search,'%');
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getSearchValidation`()
BEGIN
	select * from searchvalidations where name = 'onlyChar';
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getSubscriptionGroupList`()
BEGIN
	SELECT * FROM CSCI5308_6_DEVINT.`groups`;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getUserDetailsById`(IN USER_ID int)
BEGIN
	SELECT * FROM  ` user` WHERE id = USER_ID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `getUserIdFromPostID`(IN POST_ID int)
BEGIN
	SELECT user_id from `post` where id = POST_ID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `updateKarmaPointsByPostId`(IN KARMA_POINTS int, IN POST_ID int)
BEGIN
	DECLARE USER_ID int;
    DECLARE ORIGINAL_KARMA int;
	SELECT user_id from post where id = POST_ID INTO USER_ID;
    SELECT karma_points from  ` user` where id = @USER_ID INTO ORIGINAL_KARMA;
    update  ` user` set karma_points = @ORIGINAL_KARMA + KARMA_POINTS where id = @USER_ID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `updatePostById`(IN POST_TITLE varchar(500), IN POST_DESC VARCHAR(500),IN PostID int)
BEGIN
UPDATE post p
SET 
p.post_title = POST_TITLE,
p.post_desc = POST_DESC
WHERE p.id = PostID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `updatePostModDate`(IN MOD_DATE TIMESTAMP, IN POST_ID int )
BEGIN
	UPDATE post SET modification_date = MOD_DATE where id = POST_ID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `updatePostStatus`(IN POST_ID int, IN IS_ALIVE int)
BEGIN
UPDATE post p
SET 
p.is_alive = IS_ALIVE
WHERE p.id = POST_ID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`CSCI5308_6_DEVINT_USER`@`%` PROCEDURE `updateUser`( IN userid INT, IN useremail VARCHAR(50), IN fname VARCHAR(20), IN lname VARCHAR(20), IN passwd VARCHAR(20))
BEGIN
   UPDATE CSCI5308_6_DEVINT.` user` u
    SET
	u.first_name = fname,
	u.last_name = lname,
	u.PASSWORD = passwd,
	u.email = useremail
	WHERE u.id = userid;
    END$$
DELIMITER ;
