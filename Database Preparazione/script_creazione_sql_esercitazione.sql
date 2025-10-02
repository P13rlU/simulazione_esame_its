create table corsi
(
    corso_id        serial
        constraint corsi_pk
            primary key,
    titolo          varchar(50)  not null,
    data_ora_inizio timestamp    not null,
    luogo           varchar(100) not null,
    disponibilita   integer      not null
);

create table ISCRIZIONI
(
    iscrizione_id        serial
        constraint ISCRIZIONI_pk
            primary key,
    id_corso             integer
        constraint ISCRIZIONI_corsi_corso_id_fk
            references corsi,
    partecipante_nome    varchar(30) not null,
    partecipante_cognome varchar(30) not null,
    partecipante_email   varchar(50) not null,
    data_ora_iscrizione  timestamp   not null
);