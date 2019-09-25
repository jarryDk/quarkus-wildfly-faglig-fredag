 create table todos (
       id int4 not null,
        body varchar(255),
        createBy varchar(255),
        createdDate timestamp,
        endDate timestamp,
        importens int4,
        owner varchar(255),
        priority int4,
        startDate timestamp,
        subject varchar(255),
        updatedBy varchar(255),
        updatedDate timestamp,
        primary key (id)
    )