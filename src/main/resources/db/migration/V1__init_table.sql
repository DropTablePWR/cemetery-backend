create table cemeteries
(
    id int auto_increment,
    name varchar(64) not null,
    description int not null,
    max_grid_x int not null,
    max_grid_y int not null,
    type int default 0 not null,
    constraint Cemeteries_pk
        primary key (id)
);

create unique index Cemeteries_name_uindex
    on cemeteries (name);

create table cemeteries_forbidden_positions
(
    id int not null,
    cemetery_id int not null,
    from_x1 int not null,
    from_y1 int not null,
    from_x2 int not null,
    from_y2 int not null,
    constraint cemeteries_forbidden_positions_pk
        primary key (id),
    constraint cemeteries_forbidden_positions_cemeteries_id_fk
        foreign key (cemetery_id) references cemeteries (id)
            on update cascade on delete cascade
);

create table tombstones
(
    id int not null,
    cemetery_id int not null,
    grid_x int not null,
    grid_y int not null,
    constraint tombstones_pk
        primary key (id),
    constraint tombstones_cemeteries_id_fk
        foreign key (cemetery_id) references cemeteries (id)
            on update cascade on delete cascade
);

create table guests
(
    id int not null,
    tombstone_id int not null,
    first_name varchar(64) not null,
    last_name varchar(64) null,
    birth_date date not null,
    death_date date null,
    constraint guests_pk
        primary key (id),
    constraint Guests_tombstones_id_fk
        foreign key (tombstone_id) references tombstones (id)
            on update cascade on delete cascade
);

create unique index Guests_tombstone_id_uindex
    on guests (tombstone_id);

create table features
(
    id int not null,
    name varchar(64) not null,
    code varchar(64) not null,
    constraint features_pk
        primary key (id)
);

create table tombstones_features
(
    id int not null,
    tombstone_id int not null,
    feature_id int not null,
    place varchar(64) not null,
    constraint tombstones_features_pk
        primary key (id),
    constraint tombstones_features_features_id_fk
        foreign key (feature_id) references features (id)
            on update cascade on delete cascade,
    constraint tombstones_features_tombstones_id_fk
        foreign key (tombstone_id) references tombstones (id)
            on update cascade on delete cascade
);

create unique index tombstones_features_tombstone_id_place_uindex
    on tombstones_features (tombstone_id, place);

