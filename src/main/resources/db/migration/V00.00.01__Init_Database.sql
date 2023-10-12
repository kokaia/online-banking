CREATE TABLE IF NOT EXISTS public.persons
(
    id serial primary key,
    personal_number character varying(11),
    first_name text NOT NULL,
    last_name text,
    birth_date date NOT NULL,
    birth_place text,
    address text,
    blocked boolean NOT NULL DEFAULT false,
    created_at timestamp without time zone NOT NULL DEFAULT now(),
    tessttt integer
);

CREATE TABLE IF NOT EXISTS public.users
(
    id serial primary key,
    email character varying(80) NOT NULL UNIQUE,
    person_id integer NOT NULL UNIQUE references persons,
    last_login_date timestamp without time zone,
    phone character varying(20),
    password text,
    created_at timestamp without time zone NOT NULL DEFAULT now(),
    active boolean NOT NULL DEFAULT false
);
