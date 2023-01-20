-- default development records
-- ROLES
insert into "dailylink"."roles" ("id", "name") values ('1a7284ce-f00a-42a4-ae6a-06fcdceaf1f1', 'ROLE_ADMIN');
insert into "dailylink"."roles" ("id", "name") values ('64dd57f6-89b1-4c8a-82e8-fe523b073201', 'ROLE_MODERATOR');
insert into "dailylink"."roles" ("id", "name") values ('95274c56-91ec-47c2-b1f5-cb78ed8610c5', 'ROLE_USER');

-- USERS
insert into "dailylink"."users" ("email", "id", "password", "profile_picture_url", "username") values ('moderator@moderator.ch', '04b11c30-ecbf-46ca-a855-9aeec60906f5', '$2a$10$m/OlYOX7VPTE9AGam5j9NuUtqWbC7Ehx.QYa1h6DHCExpfDz1yCgO', 'https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg', 'moderator');
insert into "dailylink"."users" ("email", "id", "password", "profile_picture_url", "username") values ('user@user.ch', 'a7fc1f5c-86eb-4693-b959-09266acbc6ca', '$2a$10$w6hjfF1.T3REiAOhLWtO3OUGNlAGoBVIwhtKuudYkRm9BoMLtNhhW', 'https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg', 'user');
insert into "dailylink"."users" ("email", "id", "password", "profile_picture_url", "username") values ('admin@admin.ch', 'df3d54b5-51ed-47e2-80f6-df3567990392', '$2a$10$xmbL6/mJMJW/xxDW2f20quBsXO8EVZeP3gOY3.UknyyT18e7RI85S', 'https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg', 'admin');

-- USERS ROLES
insert into "dailylink"."user_roles" ("role_id", "user_id") values ('95274c56-91ec-47c2-b1f5-cb78ed8610c5', 'a7fc1f5c-86eb-4693-b959-09266acbc6ca');
insert into "dailylink"."user_roles" ("role_id", "user_id") values ('95274c56-91ec-47c2-b1f5-cb78ed8610c5', '04b11c30-ecbf-46ca-a855-9aeec60906f5');
insert into "dailylink"."user_roles" ("role_id", "user_id") values ('95274c56-91ec-47c2-b1f5-cb78ed8610c5', 'df3d54b5-51ed-47e2-80f6-df3567990392');
insert into "dailylink"."user_roles" ("role_id", "user_id") values ('64dd57f6-89b1-4c8a-82e8-fe523b073201', '04b11c30-ecbf-46ca-a855-9aeec60906f5');
insert into "dailylink"."user_roles" ("role_id", "user_id") values ('64dd57f6-89b1-4c8a-82e8-fe523b073201', 'df3d54b5-51ed-47e2-80f6-df3567990392');
insert into "dailylink"."user_roles" ("role_id", "user_id") values ('1a7284ce-f00a-42a4-ae6a-06fcdceaf1f1', 'df3d54b5-51ed-47e2-80f6-df3567990392');

-- POSTS
insert into "dailylink"."post" ("author_id", "content", "created_at", "id", "media", "updated_at") values ('a7fc1f5c-86eb-4693-b959-09266acbc6ca', 'Children, teenagers, and adults as well, can all identify with the message of this poem for children from Shel Silverstein published in his book, "Every Thing On It." Sometimes we all feel like no one really truly knows us, as if we are wearing a mask and our true self is hidden from all underneath our face. #Childrenspoem #Learning', '2020-06-22 19:10:25', '66f8f600-4e9b-4bc3-a1f3-cf99873158d6', NULL, NULL);
insert into "dailylink"."post" ("author_id", "content", "created_at", "id", "media", "updated_at") values ('a7fc1f5c-86eb-4693-b959-09266acbc6ca', 'A short poem may be a stylistic choice or it may be that you have said what you intended to say in a more concise way. Either way, they differ stylistically from a long poem in that there tends to be more care in word choice. Since there are fewer words people tend to spend more time on choosing a word that fits the subject to perfection. Because of this meticulous attitude, writing a short poem is often more tedious than writing a long poem. #longPoem #cool', '2020-06-22 19:10:25', 'db5cab74-3938-49df-8e88-97c5b12fe0f7', NULL, NULL);
