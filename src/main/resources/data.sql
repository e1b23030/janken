/* ユーザーの初期データ */
INSERT INTO users (name) VALUES ('CPU');
INSERT INTO users (name) VALUES ('ほんだ');
INSERT INTO users (name) VALUES ('いがき');

/* 試合結果の初期データ (user1=2 (ほんだ), user2=1 (CPU)) */
INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'Gu', 'Choki', FALSE);
INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'Gu', 'Gu', FALSE);
INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'Gu', 'Pa', FALSE);

/* matchinfoの初期データ */
/* (user1=1 (CPU), user2=2 (ほんだ)) */
INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (1, 2, 'Gu', FALSE);
/* (user1=2 (ほんだ), user2=3 (いがき)) */
INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (2, 3, 'Choki', FALSE);
