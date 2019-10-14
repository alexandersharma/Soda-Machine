drop table Soda;
CREATE TABLE Soda(id INT NOT NULL auto_increment, name VARCHAR(20) NOT NULL, count INT NOT NULL, primary key(id));
INSERT INTO Soda(name,count) values('coke','3');
INSERT INTO Soda(name,count) values('sprite','2');
INSERT INTO Soda(name,count)values('diet coke','1');