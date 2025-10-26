/* ユーザーの初期データ */
INSERT INTO users (name) VALUES ('CPU');
INSERT INTO users (name) VALUES ('ほんだ');

/* 試合結果の初期データ (user1=2 (ほんだ), user2=1 (CPU)) */
INSERT INTO matches (user1, user2, user1Hand, user2Hand) VALUES (2, 1, 'Gu', 'Choki');
INSERT INTO matches (user1, user2, user1Hand, user2Hand) VALUES (2, 1, 'Gu', 'Gu');
INSERT INTO matches (user1, user2, user1Hand, user2Hand) VALUES (2, 1, 'Gu', 'Pa');
