CREATE OR REPLACE FUNCTION public.transaction_trgf()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
declare
begin

 update accounts set amount = amount + new.amount where accounts.id = new.debit_account_id;
 update accounts set amount = amount - new.amount where accounts.id = new.credit_account_id;
 raise notice 'transaction executed';

 return new;
end;
$BODY$;

CREATE OR REPLACE TRIGGER transactions_tg
    BEFORE INSERT
    ON public.transactions
    FOR EACH ROW
    EXECUTE FUNCTION public.transaction_trgf();
