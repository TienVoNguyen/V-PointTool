create table item
(
    id            bigint auto_increment
        primary key,
    create_by     varchar(255) null,
    create_date   varchar(255) null,
    modified_by   varchar(255) null,
    modified_date datetime(6)  null,
    name          varchar(100) null,
    category_id   bigint       null,
    end           float        null,
    point_rule    int          null,
    start         float        null,
    type          int          null,
    constraint FK2n9w8d0dp4bsfra9dcg0046l4
        foreign key (category_id) references category (id)
);

INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (1, null, null, 'anonymousUser', '2022-11-19 17:06:23.105000', 'KPI cá nhân hàng tháng', 1, null, null, null, 0);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (2, null, null, 'anonymousUser', '2022-11-19 17:13:05.298000', 'Nhân viên xuất sắc tháng', 1, null, null, null, 5);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (3, null, null, 'anonymousUser', '2022-11-19 17:16:48.766000', 'Điểm BSC bộ phận', 2, null, null, null, 1);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (4, null, null, 'anonymousUser', '2022-11-19 17:19:59.996000', 'Hoạt động chung', 2, 4, 0, -0.5, 4);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (5, null, null, 'anonymousUser', '2022-11-19 17:22:15.918000', 'Đào tạo', 3, null, null, null, 1);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (6, null, null, 'anonymousUser', '2022-11-19 17:25:34.165000', 'Cải tiến đổi mới', 4, null, null, null, 3);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (7, null, null, 'anonymousUser', '2022-11-19 17:28:33.114000', 'Tôi yêu VMG', 5, null, null, null, 2);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (8, null, null, 'anonymousUser', '2022-11-19 17:29:31.832000', 'Kỷ luật', 5, 0, 0, null, 4);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (9, null, null, 'anonymousUser', '2022-11-19 17:30:30.630000', 'Bộ phận xuất sắc tháng', 1, null, null, null, 5);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (10, null, null, 'anonymousUser', '2022-11-19 17:30:50.485000', 'Bộ phận xuất sắc năm', 1, null, null, null, 5);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (11, null, null, 'anonymousUser', '2022-11-19 17:32:47.426000', 'Tham gia đào tạo', 3, null, null, null, 2);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (12, null, null, 'anonymousUser', '2022-11-19 17:34:00.077000', 'Phát triển cùng VMG', 3, 50, 0, 0, 4);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (13, null, null, 'anonymousUser', '2022-11-19 17:34:08.732000', 'Khen thưởng nóng', 5, null, 0, 0, 4);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (16, null, null, null, null, 'Nhân viên xuất sắc quý', 1, null, null, null, 3);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (17, null, null, null, null, 'Nhân viên xuất sắc năm', 1, null, null, null, 3);
INSERT INTO vpointapp.item (id, create_by, create_date, modified_by, modified_date, name, category_id, end, point_rule, start, type) VALUES (19, null, null, null, null, 'Cải tiến đổi mới năm', 4, 35, 1, 0, 4);
