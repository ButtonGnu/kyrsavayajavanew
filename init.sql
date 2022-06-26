CREATE TABLE if not exists public.customers (
  id BIGINT NOT NULL generated always as identity,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   phone_number VARCHAR(255),
   CONSTRAINT pk_customers PRIMARY KEY (id)
);
CREATE TABLE if not exists public.employees (
  id BIGINT NOT NULL generated always as identity,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   job_position VARCHAR(255),
   CONSTRAINT pk_employees PRIMARY KEY (id)
);
CREATE TABLE public.items_price
(
    id bigint NOT NULL generated always as identity,
    item text,
    price bigint,
    PRIMARY KEY (id)
);
CREATE TABLE public.requests
(
    id bigint NOT NULL generated always as identity,
    reason text,
    execution_stage character varying,
    request_status character varying,
	license_plate character varying,
    employee_diagnostics bigint,
    employee_repair bigint,
    price bigint,
    customer_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT employee_diagnostics FOREIGN KEY (employee_diagnostics)
        REFERENCES public.employees (id) ,
    CONSTRAINT employee_repair FOREIGN KEY (employee_repair)
        REFERENCES public.employees (id) ,
    CONSTRAINT customer_id FOREIGN KEY (customer_id)
        REFERENCES public.customers (id)
);