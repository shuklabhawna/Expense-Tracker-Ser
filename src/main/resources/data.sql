
insert into USER (ID,USER_CODE,NAME,EMAIL,SALT,PASSWORDHASH) values(1,'userA','userA','userA@gmail.com','A001','A001UserA');
insert into USER (ID,USER_CODE,NAME,EMAIL,SALT,PASSWORDHASH) values(2,'userB','userB','userB@gmail.com','B002','B002UserB');
insert into USER (ID,USER_CODE,NAME,EMAIL,SALT,PASSWORDHASH) values(3,'userC','userC','userC@gmail.com','C003','C003UserC');
insert into USER (ID,USER_CODE,NAME,EMAIL,SALT,PASSWORDHASH) values(4,'userD','userD','userD@gmail.com','D004','D004UserD');
insert into USER (ID,USER_CODE,NAME,EMAIL,SALT,PASSWORDHASH) values(5,'userE','userE','userE@gmail.com','E005','E005UserE');

insert into EXPENSE_TYPE (ID,CODE,DESCRIPTION) values(1,'TR01','Travel');
insert into EXPENSE_TYPE (ID,CODE,DESCRIPTION) values(2,'FD01','Food');
insert into EXPENSE_TYPE (ID,CODE,DESCRIPTION) values(3,'MD01','Medical');
insert into EXPENSE_TYPE (ID,CODE,DESCRIPTION) values(4,'HT01','Hotel');
insert into EXPENSE_TYPE (ID,CODE,DESCRIPTION) values(5,'TK01','Ticket');
insert into EXPENSE_TYPE (ID,CODE,DESCRIPTION) values(6,'MS01','Miscellaneous');

insert into CURRENCY(CURRENCY_CODE,CURRENCY_NAME) values('SGD','Singapore Dollar');
insert into CURRENCY(CURRENCY_CODE,CURRENCY_NAME) values('USD','US Dollar');
insert into CURRENCY(CURRENCY_CODE,CURRENCY_NAME) values('MYR','Malaysian Ringitt');
insert into CURRENCY(CURRENCY_CODE,CURRENCY_NAME) values('AUD','Australian Dollar');
insert into CURRENCY(CURRENCY_CODE,CURRENCY_NAME) values('HKD','HongKong Dollar');

insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(1,'USD','SGD',1.3306633,'2021-05-01');
insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(2,'SGD','MYR',3.0778162,'2021-05-01');
insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(3,'AUD','SGD',1.0269166,'2021-05-01');
insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(4,'SGD','HKD',5.8365633,'2021-05-01');

insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(5,'USD','MYR',4.0955295,'2021-05-01');
insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(6,'USD','AUD',1.2959771,'2021-05-01');
insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(7,'USD','HKD',7.7676504,'2021-05-01');

insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(8,'AUD','MYR',3.1601867,'2021-05-01');
insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(9,'HKD','MYR',0.52725461,'2021-05-01');

insert into FX_RATES(ID,PRIMARY_CCY,SECONDARY_CCY,EXCHANGE_RATE,T_DATE) values(10,'AUD','HKD',5.9936635,'2021-05-01');

--how much of the quote currency is needed to buy one unit of the provided base currency.


insert into expense_details (amount, currency_code, date, description, expense_code, status, user_id, id) values (20, 'SGD', '2021-05-01', 'Test', 3, 'PENDING', 5, 1);
insert into expense_details (amount, currency_code, date, description, expense_code, status, user_id, id) values (50, 'SGD', '2021-05-01', 'Medical',3, 'PENDING', 4, 2);

insert into ACCOUNT(ID,USERID,ACCOUNTCODE,CURRENCY_CODE,BALANCE) values(1,1,'123-User1','SGD',35000);
insert into ACCOUNT(ID,USERID,ACCOUNTCODE,CURRENCY_CODE,BALANCE) values(2,2,'321-User2','SGD',35000);
insert into ACCOUNT(ID,USERID,ACCOUNTCODE,CURRENCY_CODE,BALANCE) values(3,3,'233-User3','SGD',35000);
insert into ACCOUNT(ID,USERID,ACCOUNTCODE,CURRENCY_CODE,BALANCE) values(4,4,'544-User4','SGD',35000);
insert into ACCOUNT(ID,USERID,ACCOUNTCODE,CURRENCY_CODE,BALANCE) values(5,5,'234-User5','SGD',35000);

