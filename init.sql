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

CREATE TABLE if not exists public.requests (
  id BIGINT NOT NULL generated always as identity,
  reason VARCHAR,
   employee_id BIGINT,
   execution_stage VARCHAR(255),
   request_status VARCHAR(255),
   customer_id BIGINT NOT NULL,
   CONSTRAINT pk_requests PRIMARY KEY (id)
);

ALTER TABLE public.requests ADD CONSTRAINT FK_REQUESTS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE public.requests ADD CONSTRAINT FK_REQUESTS_ON_EMPLOYEE FOREIGN KEY (employee_id) REFERENCES employees (id);