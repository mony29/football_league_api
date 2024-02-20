create table accounts
(
    id         serial primary key,
    name       varchar(50),
    password   varchar(50),
    email      varchar(100),
    profile    varchar(50),
    -- create_at timestamp ,
    is_enabled boolean default false
);

create table roles
(
    id   serial primary key,
    name varchar(50) not null default ('USER')
);

create table email_verification
(
    id          serial primary key,
    user_id     integer references accounts (id),
    code        varchar(20),
    is_verified boolean default false
);

create table leagues
(
    id               serial primary key,
    name             varchar(100) not null,
    logo             varchar(50),
    country          varchar(50),
    season           varchar(50),
    start_date       timestamp,
    end_date         timestamp,
    league_code      varchar(10),
    established_year timestamp,
    market_value     varchar(50),
    description      varchar(100)
);

create table league_details
(
    user_id   integer references accounts (id),
    league_id integer references leagues (id),
    role_id   integer references roles (id)
);

create table teams
(
    id               serial primary key,
    name             varchar(100) not null,
    logo             varchar(50),
    team_code        varchar(10),
    country          varchar(50),
    league_id        integer references leagues (id),
    location         varchar(100),
    stadium          varchar(100),
    capacity         varchar(50),
    established_year varchar(10),
    market_value     varchar(50),
    honor_id         integer references honor_awards (id)
);

create table honor_awards
(
    id     serial primary key,
    name   varchar(100) not null,
    season varchar(50)  not null,
    logo   varchar(50)
);

create table league_standing
(
    id              serial primary key,
    league_id       integer references leagues (id),
    team_id         integer references teams (id),
    played          integer default 0,
    won             integer default 0,
    draw            integer default 0,
    lost            integer default 0,
    goals_for       integer default 0,
    goals_different integer default 0,
    point           integer default 0
);

create table player
(
    -- main
    id           serial primary key,
    full_name    varchar(50) not null,
    kit_no       integer,
    position     varchar(50),
    nationality  varchar(50),
    flag         varchar(50),
    team_id      integer references teams (id),
    age          integer,
    goals_scored integer     default 0,
    goals_assits integer     default 0,
    clean_sheet  integer, -- For GK & DF
    --optional
    birth_date   timestamp,
    height       varchar(20),
    weight       varchar(20),
    market_value varchar(50),
    honor_id     integer references honor_awards (id),
    status       varchar(50) default ('Active'),
    yellow_card  integer     default 0,
    red_card     integer     default 0,
    foot         varchar(20),
    played       integer
);

create table contract
(
    id        serial primary key,
    start_at  timestamp,
    end_at    timestamp,
    worth     varchar(50),
    team_id   integer references teams (id),
    player_id integer references players (id)
);

create table matches
(
    id           serial primary key,
    home_team_id integer references match_results (id),
    away_team_id integer references match_results (id),
    match_week   integer,
    match_date   timestamp,
    match_time   timestamp,
    venue        varchar(50),
    result_id    integer references match_results (id)
);

create table match_results
(
    id               serial primary key,
    goals_scored     integer default 0,
    goal_scorer      integer references players (id) not null,
    goal_minute      integer,
    goal_assist      integer references players (id) not null,
    possession       float,
    shoot_on_target  integer,
    shoot_off_target integer,
    foul             integer,
    yellow_card      integer,
    red_card         integer
);

create table match_line_up
(
    id        serial primary key,
    team_id   integer references teams (id),
    player_id integer references players (id),
    team_form varchar(50) -- 4-3-3
);

