CREATE TABLE IF NOT EXISTS public.client (
    id bigint PRIMARY KEY NOT NULL ,
    user_name VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    account_non_expired BOOLEAN,
    account_non_locked BOOLEAN,
    credentials_non_expired BOOLEAN,
    enabled BOOLEAN,
    birth_date date,
    first_name character varying(255),
    gender smallint,
    last_name character varying(255),
    status smallint,
    telephone character varying(12),
    CONSTRAINT client_gender_check CHECK (((gender >= 0) AND (gender <= 1))),
    CONSTRAINT client_status_check CHECK (((status >= 0) AND (status <= 4)))
);

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.address (
    id bigint PRIMARY KEY NOT NULL,
    city character varying(255) NOT NULL,
    complement character varying(255),
    neighborhood character varying(255) NOT NULL,
    number integer NOT NULL,
    postal_code character varying(8) NOT NULL,
    state smallint NOT NULL,
    street character varying(255) NOT NULL,
    client_id bigint,
    CONSTRAINT address_state_check CHECK (((state >= 0) AND (state <= 26)))
);

CREATE SEQUENCE public.address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.permission (
    id bigint PRIMARY KEY NOT NULL,
    description VARCHAR(50) NOT NULL
);

CREATE SEQUENCE public.permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.client_permission (
    id_client bigint NOT NULL,
    id_permission bigint NOT NULL,
    PRIMARY KEY (id_client, id_permission),
    CONSTRAINT fk_user_permission_client FOREIGN KEY (id_client) REFERENCES client (id),
    CONSTRAINT fk_user_permission_permission FOREIGN KEY (id_permission) REFERENCES permission (id) 
);

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);
ALTER TABLE ONLY public.address ALTER COLUMN id SET DEFAULT nextval('public.address_id_seq'::regclass);
ALTER TABLE ONLY public.permission ALTER COLUMN id SET DEFAULT nextval('public.permission_id_seq'::regclass);
