CREATE TABLE IF NOT EXISTS public.accounts
(
    id serial primary key,
    person_id integer NOT NULL  REFERENCES public.persons,
    name text,
    amount numeric(10,2) NOT NULL DEFAULT 0,
    blocked boolean DEFAULT false,
    created_at timestamp without time zone NOT NULL DEFAULT now(),
    iban text,
    amountf double precision,
    created_by integer
);

CREATE TABLE IF NOT EXISTS public.roles
(
    id serial primary key,
    name text NOT NULL,
    decs text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.user_roles
(
    id serial primary key,
    user_id integer NOT NULL REFERENCES public.roles(id),
    role_id integer NOT NULL REFERENCES public.roles(id)
);

CREATE TABLE IF NOT EXISTS public.transactions
(
    id serial primary key,
    debit_account_id integer NOT NULL REFERENCES public.accounts,
    credit_account_id integer NOT NULL REFERENCES public.accounts,
    amount numeric(10,2) NOT NULL,
    comment_text text,
    created_at timestamp without time zone NOT NULL DEFAULT now(),
    created_by integer
);
