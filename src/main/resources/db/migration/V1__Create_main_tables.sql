CREATE TABLE developers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    birth_date timestamp,
    status BOOLEAN NOT NULL
);

CREATE TABLE skills (
    id SERIAL PRIMARY KEY,
    scope VARCHAR(60) NOT NULL,
    level INT NOT NULL,
    status BOOLEAN NOT NULL
);

CREATE TABLE developers_skills (
    developer_id INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (developer_id, skill_id),
    FOREIGN KEY(developer_id)
        REFERENCES developers(id),
    FOREIGN KEY(skill_id)
        REFERENCES skills(id)
);

CREATE TABLE companies (
    id SERIAL PRIMARY KEY,
    "name" VARCHAR(128) NOT NULL,
    status BOOLEAN NOT NULL,
    date_start DATE NOT NULL
);

CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    status BOOLEAN NOT NULL
);

CREATE TABLE projects (
    id SERIAL PRIMARY KEY,
    company_id INT NOT NULL,
    customer_id INT NOT NULL,
    "name" VARCHAR(128) NOT NULL,
    budget NUMERIC(12,2) NOT NULL,
    status BOOLEAN NOT NULL,
    date_start DATE NOT NULL,
    date_end DATE,
    CONSTRAINT fk_projects_companies FOREIGN KEY (company_id)
        REFERENCES companies (id),
    CONSTRAINT fk_projects_customers FOREIGN KEY (customer_id)
        REFERENCES customers (id)
);

CREATE TABLE developers_projects (
    developer_id INT NOT NULL,
    project_id INT NOT NULL,
    PRIMARY KEY (developer_id, project_id),
    FOREIGN KEY (developer_id)
        REFERENCES developers (id),
    FOREIGN KEY (project_id)
        REFERENCES projects (id)
);