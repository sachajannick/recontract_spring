INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

INSERT INTO app_user(hiring_or_freelancer, password, username) VALUES('freelancer', '$2a$12$T8/vGZViB54paEs663CaJ.Z0wm0/It5mkCizAtF/tmtKVMBIo9GxC', 'sarah123');
INSERT INTO app_user(hiring_or_freelancer, password, username) VALUES('freelancer', '$2a$12$lXffb6LtnmwYIzI9m22Ctegg4JMoEt8UZGGrJBLjX7u0xXGIb.Lde', 'dave123');
INSERT INTO app_user(hiring_or_freelancer, password, username) VALUES('hiring', '$2a$12$PjKIYgiDT/mSviPPFyQGFedwEVvyMXtNZe7o9Fc71GarH4aJBCeR2', 'sam123');
INSERT INTO app_user(hiring_or_freelancer, password, username) VALUES('hiring', '$2a$12$0RVf21UMDDOgsTf1Me1AcOD2dPO8Nfo7cSIUQLDuvJhr5ssGCfq12', 'christina123');
INSERT INTO app_user(hiring_or_freelancer, password, username) VALUES('hiring', '$2a$12$QPfb.YuR.WTWcQxk24rtlueS9bs/DQ6KtVnb2iu54VQHkea1hOaOK', 'admin');

INSERT INTO user_role(user_id, role_id) VALUES(1, 1);
INSERT INTO user_role(user_id, role_id) VALUES(2, 1);
INSERT INTO user_role(user_id, role_id) VALUES(3, 1);
INSERT INTO user_role(user_id, role_id) VALUES(4, 1);
INSERT INTO user_role(user_id, role_id) VALUES(5, 2);

INSERT INTO searches(id, amount, email, full_name, function_title, headline, location, profile_picture, user_id) VALUES(1, '85', 's.fadim@gmail.com', 'Sarah Fadim', 'Medior Front-end Developer', 'headline', 'Amsterdam', null, 1);
INSERT INTO searches(id, amount, email, full_name, function_title, headline, location, profile_picture, user_id) VALUES(2, '95', 'd.li@gmail.com', 'Dave Li', 'Senior Java Developer', 'headline', 'Rotterdam', null, 2);
INSERT INTO searches(id, amount, email, full_name, function_title, headline, location, profile_picture, user_id) VALUES(3, '80', 'sam@kpmg.com', 'Sam Kessels', 'Medior Front-end Developer', 'headline', 'Amstelveen', null, 3);
INSERT INTO searches(id, amount, email, full_name, function_title, headline, location, profile_picture, user_id) VALUES(4, '95', 'christina@capgemini.com', 'Christina Smith', 'Senior Java Developer', 'headline', 'Utrecht', null, 4);