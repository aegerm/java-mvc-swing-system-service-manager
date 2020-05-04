/**
 * Author:  Alexandre Marques
 * Created: 30/03/2017
 */

create database sms;

use sms;

create table collaborators
(
    id 		int		not null auto_increment,
    name 	varchar(160) 	not null,
    address 	varchar(200) 	not null,
    city	varchar(80) 	not null,
    uf 		varchar(2)	not null,
    email	varchar(160) 	not null,
    cell_phone 	varchar(11) 	not null,
    function 	varchar(80) 	not null,
    cpf 	varchar(11)     not null unique,
    login	varchar(50)     not null unique,
    password    varchar(64)     not null,
    state       varchar(1)      not null,
    
    constraint pk_collaborators		primary key (id)
);

create table clients
(
    id		int		not null auto_increment,
    name	varchar(160) 	not null,
    address 	varchar(200) 	not null,
    city	varchar(80) 	not null,
    uf 		varchar(2) 	not null,
    email	varchar(160) 	not null,
    phone	varchar(10)	not null,
    cell_phone 	varchar(11) 	not null,
    cpf 	varchar(11)     not null unique,
    cnpj	varchar(14),
    state       varchar(1)      not null,
    
    constraint pk_clients primary key (id)
);

create table payments
(
    id			int         not null auto_increment,
    form_payment 	varchar(50) not null,
    state               varchar(1)  not null,
    
    constraint pk_payments primary key (id)
);

create table services
(
    id          int		not null auto_increment,
    cost        decimal(5,2) 	not null,
    service     varchar(160) 	not null,
    state       varchar(1)      not null,
    
    constraint pk_services primary key (id)
);

create table os
(
    id				int         not null auto_increment,
    date_register 		timestamp   not null,
    collaborators_id            int         not null,
    clients_id 			int         not null,
    state                       varchar(1)  not null,
    value_full                  double      not null,

    constraint pk_os 			primary key (id),
    constraint fk_collaborator_os 	foreign key (collaborators_id) 	references collaborators (id),
    constraint fk_clients_os 		foreign key (clients_id) 	references clients (id)
);

create table types_devices
(
    id 		int 		not null auto_increment,
    device 	varchar(160) 	not null,
    state       varchar(1)      not null,
    
    constraint pk_types_devices primary key (id)
);

create table devices
(
    id 				int		not null auto_increment,
    model 			varchar(160) 	not null,
    maker 			varchar(160) 	not null,
    serial_number 		varchar(100) 	not null,
    types_devices_id 		int             not null,
    state                       varchar(1)      not null,
    
    constraint pk_devices               primary key (id),
    constraint fk_devices_types_devices foreign key (types_devices_id) references types_devices (id)
);

create table items_os
(
    id 				int		not null auto_increment,
    os_id                       int             not null,
    devices_id                  int             not null,
    services_id                 int             not null,
    description_client 		longtext        not null,
    description_finished 	longtext        not null,
    state                       varchar(1)      not null,

    constraint pk_item_os       primary key (id),
    constraint fk_os            foreign key (os_id)            references os (id),   
    constraint fk_devices       foreign key (devices_id)       references devices (id),   
    constraint fk_services      foreign key (services_id)      references services (id)   
);

create table finances
(
    id              int not null auto_increment,
    date_pay        Date not null,
    date_sale       Date not null,
    value_provided  decimal(5,2) not null,
    value_received  decimal(5,2) not null,
    plots           int not null,
    discount        double,
    os_id           int not null,
    payments_id     int not null,
    state       varchar(1)      not null,

    constraint pk_finances	primary key (id),
    constraint fk_finances_os    foreign key (os_id) references os (id),
    constraint fk_finances_payments    foreign key (payments_id) references payments (id)
);

insert into collaborators values ( 0, 'Alexandre', 'teste', 'lajeado', 'RS', 'a@gmail.com', '51994462054', 'Developer', '03256039014', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'A' )