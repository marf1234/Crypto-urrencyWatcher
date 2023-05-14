create table if not exists "cryptocurrencies"
(
    id              bigserial       not null,
    symbol          varchar(60)     not null,
    price_usd       numeric(9,2)     not null,
    primary key (id)
);