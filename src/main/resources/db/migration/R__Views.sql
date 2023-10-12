CREATE OR REPLACE VIEW public.accounts_full
 AS
 SELECT acc.id,
    acc.person_id,
    acc.name,
    acc.amount,
    persons.personal_number,
    (persons.first_name || ' '::text) || persons.last_name AS full_name
   FROM accounts acc
     JOIN persons ON persons.id = acc.person_id;

CREATE OR REPLACE VIEW public.full_transactions
 AS
 SELECT tr.id,
    tr.created_at AS transaction_date,
    tr.amount,
    tr.comment_text as comment,
    dp.personal_number AS receiver_person_pn,
    (dp.first_name || ' '::text) || dp.last_name AS receiver_person_full_name,
    cp.personal_number AS sender_person_pn,
    (cp.first_name || ' '::text) || cp.last_name AS sender_person_full_name,
    1 AS const,
    now() AS "current_date"
   FROM transactions tr
     JOIN accounts da ON da.id = tr.debit_account_id
     JOIN accounts ca ON ca.id = tr.credit_account_id
     JOIN persons dp ON dp.id = da.person_id
     JOIN persons cp ON cp.id = ca.person_id;
