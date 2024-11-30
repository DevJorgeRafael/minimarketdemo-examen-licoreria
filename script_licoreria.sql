-- object: public.lic_tipos_licores_id_lic_tipos_licores_seq | type: SEQUENCE --
CREATE SEQUENCE public.lic_tipos_licores_id_lic_tipos_licores_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- object: public.lic_tipos_licores | type: TABLE --
CREATE TABLE public.lic_tipos_licores(
	id_lic_tipos_licores integer NOT NULL DEFAULT nextval('public.lic_tipos_licores_id_lic_tipos_licores_seq'::regclass),
	nombre_tipo character varying(50) NOT NULL,  -- Ejemplo: Whisky, Tequila, Ron, etc.
	descripcion character varying(255),          -- Descripción adicional (opcional).
	CONSTRAINT lic_tipos_licores_pk PRIMARY KEY (id_lic_tipos_licores),
	CONSTRAINT uk_nombre_tipo UNIQUE (nombre_tipo)
);

-- object: public.lic_licores_id_lic_licores_seq | type: SEQUENCE --
CREATE SEQUENCE public.lic_licores_id_licores_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

-- object: public.lic_licores | type: TABLE --
CREATE TABLE public.lic_licores(
	id_licores integer NOT NULL DEFAULT nextval('public.lic_licores_id_licores_seq'::regclass),
	nombre_licor character varying(100) NOT NULL,  -- Nombre del licor o trago.
	descripcion character varying(255),             -- Descripción del licor (opcional).
	volumen numeric(7,2),                           -- Volumen del licor (por ejemplo, 750ml, 1L).
	porcentaje_alcohol numeric(5,2),                -- Porcentaje de alcohol del licor (por ejemplo, 40.00%).
	id_lic_tipos_licores integer NOT NULL,          -- Relación con la tabla de tipos de licores.
	CONSTRAINT lic_licores_pk PRIMARY KEY (id_licores),
	CONSTRAINT fk_lic_tipo_licor FOREIGN KEY (id_lic_tipos_licores)
		REFERENCES public.lic_tipos_licores (id_lic_tipos_licores) MATCH FULL
		ON DELETE CASCADE
		ON UPDATE CASCADE
);


-- Insertar datos de ejemplo en las tablas

-- Tipos de licores
INSERT INTO public.lic_tipos_licores (nombre_tipo, descripcion) VALUES
('Whisky', 'Licor destilado a partir de granos de cereales.'),
('Tequila', 'Licor de agave originario de México.'),
('Ron', 'Licor producido de la caña de azúcar o melaza.');

-- Licores
INSERT INTO public.lic_licores (nombre_licor, descripcion, volumen, porcentaje_alcohol, id_lic_tipos_licores) VALUES
('Johnnie Walker Red Label', 'Whisky escocés de mezcla.', 750.00, 40, 1),
('Jose Cuervo Especial', 'Tequila con mezcla de agave.', 700.00, 35, 2),
('Bacardi Superior', 'Ron blanco de Puerto Rico.', 1000.00, 40, 3);

