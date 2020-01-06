CREATE TABLE public."Employee"
(
    "ID" numeric NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Employee_pkey" PRIMARY KEY ("ID")
)

    TABLESPACE pg_default;

ALTER TABLE public."Employee"
    OWNER to postgres;

CREATE OR REPLACE FUNCTION public.get_employees(
)
    RETURNS refcursor
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE

AS $BODY$
DECLARE
    ref refcursor;                                                     -- Declare a cursor variable
BEGIN
    OPEN ref FOR SELECT * FROM public."Employee";   -- Open a cursor
    RETURN ref;                                                       -- Return the cursor to the caller
END;
$BODY$;

ALTER FUNCTION public.get_employees()
    OWNER TO postgres;


CREATE OR REPLACE FUNCTION public.get_employees_count(
)
    RETURNS integer
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE

AS $BODY$                                                   -- Declare a cursor variable
BEGIN
    RETURN (SELECT COUNT(*) FROM public."Employee");                                                      -- Return the cursor to the caller
END;
$BODY$;

ALTER FUNCTION public.get_employees_count()
    OWNER TO postgres;


CREATE OR REPLACE FUNCTION public.get_single_employee(
)
    RETURNS refcursor
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE

AS $BODY$
DECLARE
    ref refcursor;                                                     -- Declare a cursor variable
BEGIN
    OPEN ref FOR SELECT * FROM public."Employee" WHERE public."Employee"."ID" = 3;   -- Open a cursor
    RETURN ref;                                                       -- Return the cursor to the caller
END;
$BODY$;

ALTER FUNCTION public.get_single_employee()
    OWNER TO postgres;
