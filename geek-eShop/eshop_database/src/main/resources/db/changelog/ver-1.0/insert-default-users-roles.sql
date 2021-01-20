INSERT INTO `users` (`create_date`, `modify_date`,`first_name`,`last_name`, `user_name`, `password`, `email`, `phone`)
    VALUE   (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP ,'Artem', 'Senchenko', 'admin', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', 'test@test.com', '+7(000)000-00-00'),
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,'n/a', 'n/a', 'guest', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', 'n/a', 'n/a');
GO

INSERT INTO `roles` (`create_date`, `modify_date`, `title`)
    VALUE (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_ADMIN'), (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_GUEST');
GO

INSERT INTO `users_roles`(`user_id`, `role_id`)
SELECT (SELECT id FROM `users` WHERE `user_name` = 'admin'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `users` WHERE `user_name` = 'guest'), (SELECT id FROM `roles` WHERE `title` = 'ROLE_GUEST');
GO