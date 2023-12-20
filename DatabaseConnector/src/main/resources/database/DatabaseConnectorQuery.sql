create database DatabaseConnectorDB;
use DatabaseConnectorDB;

create table Account (id_acc     integer primary key identity(1,1),
					  name_acc   varchar(500) not null,
					  email_acc  varchar(500) not null, 
					  hashpw_acc varbinary(256) not null,
					  image_acc  varbinary(256) null);

create table Item (id_item    integer primary key identity(1,1), 
				   name_item  varchar(500) not null, 
				   prc_item   float not null,
				   qty_item   integer not null,
				   image_item varbinary(256) null);

create table Purchase (id_purc       integer primary key identity(1,1), 
					   id_acc        integer foreign key references Account(id_acc) not null, 
					   total_amt     float not null,
					   n_of_items    integer not null,
					   datetime_purc smalldatetime not null);

create table Purchased_Items (id_item       integer foreign key references Item(id_item) not null,
							  qty_purchased integer not null,
							  id_purc       integer foreign key references Purchase(id_purc) not null);

-- mocking table --

create table Region (id_region        integer primary key not null,
                     name_region      varchar(500) not null,
					 num_of_clusters  integer not null, 
					 status_of_region varchar(500) null);

create table MockingBean (id_mockbean    integer primary key identity(1,1),
						  id_region      integer foreign key references Region(id_region) not null,
						  name_mockbean  varchar(500) not null);
