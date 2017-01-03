DROP TABLE seckill;
DROP SEQUENCE seckill_seq;
DROP TABLE success_killed;


CREATE TABLE seckill(
  seckill_id NUMBER PRIMARY KEY,
  name VARCHAR2(120) NOT NULL,
  seckill_num INT NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP NOT NULL,
  create_time TIMESTAMP DEFAULT systimestamp
);
CREATE SEQUENCE seckill_seq START WITH 1000;
COMMENT ON TABLE seckill IS '秒杀库存表';
COMMENT ON COLUMN seckill.seckill_id IS '商品库存ID';
COMMENT ON COLUMN seckill.name IS '商品名称';
COMMENT ON COLUMN seckill.seckill_num IS '库存数量';
COMMENT ON COLUMN seckill.start_time IS '秒杀开始时间';
COMMENT ON COLUMN seckill.end_time IS '秒杀结束时间';
COMMENT ON COLUMN seckill.create_time IS '秒杀创建时间';
CREATE INDEX idx_start_time ON seckill(start_time);
CREATE INDEX idx_end_time ON seckill(end_time);
CREATE INDEX idx_create_time ON seckill(create_time);
INSERT INTO seckill(seckill_id, name, seckill_num, start_time, end_time)
VALUES (seckill_seq.nextval,'1000元秒杀Iphone6s',100,to_date('2016-6-18','yyyy-mm-dd'), to_date('2016-6-19','yyyy-mm-dd'));
INSERT INTO seckill(seckill_id, name, seckill_num, start_time, end_time)
VALUES (seckill_seq.nextval,'500元秒杀Ipad2',200,to_date('2016-6-17','yyyy-mm-dd'), to_date('2016-6-18','yyyy-mm-dd'));
INSERT INTO seckill(seckill_id, name, seckill_num, start_time, end_time)
VALUES (seckill_seq.nextval,'300元秒杀小米5',300,to_date('2016-6-16','yyyy-mm-dd'), to_date('2016-6-17','yyyy-mm-dd'));
INSERT INTO seckill(seckill_id, name, seckill_num, start_time, end_time)
VALUES (seckill_seq.nextval,'200元秒杀红米3',400,to_date('2016-6-15','yyyy-mm-dd'), to_date('2016-6-16','yyyy-mm-dd'));
INSERT INTO seckill(seckill_id, name, seckill_num, start_time, end_time)
VALUES (seckill_seq.nextval,'500元秒杀红米33',400,to_date('2016-7-5','yyyy-mm-dd'), to_date('2016-8-16','yyyy-mm-dd'));
INSERT INTO seckill(seckill_id, name, seckill_num, start_time, end_time)
VALUES (seckill_seq.nextval,'2220元秒杀红米4123',400,to_date('2016-7-12','yyyy-mm-dd'), to_date('2016-8-16','yyyy-mm-dd'));
--秒杀成功明细表
CREATE TABLE success_killed(
  PRIMARY KEY (seckill_id,user_phone),
  seckill_id NUMBER,
  user_phone VARCHAR2(11) NOT NULL,
  state NUMBER(1) DEFAULT -1,
  create_time TIMESTAMP DEFAULT systimestamp
);
COMMENT ON TABLE success_killed IS '秒杀成功明细表';
COMMENT ON COLUMN success_killed.seckill_id IS '秒杀产品ID';
COMMENT ON COLUMN success_killed.user_phone IS '用户手机号';
COMMENT ON COLUMN success_killed.state IS '状态标示：-1:无效 0:成功 1:已付款';
CREATE INDEX idx_create_time1 ON success_killed(create_time);
