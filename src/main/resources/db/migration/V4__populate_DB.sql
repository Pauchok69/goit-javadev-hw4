INSERT INTO developers (first_name, last_name, birth_date, status, salary) VALUES
    ('Andre', 'Markov', '1990-04-17', True, RANDOM() * 5000),
    ('Bert', 'Rock', '1980-07-22', True, RANDOM() * 5000),
    ('Leo', 'Hofs', '1986-12-17', True, RANDOM() * 5000),
    ('Sheld', 'Kup', '1987-11-01', True, RANDOM() * 5000),
    ('Jaja', 'Binks', '1991-01-28', False, RANDOM() * 5000);

INSERT INTO skills ("scope", "level", status) VALUES
    ('Java', 1, True),
    ('Java', 2, False),
    ('Java', 3, True),
    ('Mysql', 3, True),
    ('PHP', 3, False),
    ('HTML', 2, True),
    ('CSS', 3, True);

INSERT INTO developers_skills (developer_id, skill_id) VALUES
    (1, 3),
    (1, 4),
    (1, 5),
    (2, 1),
    (3, 2),
    (3, 4),
    (3, 7),
    (5, 6),
    (5, 7);

INSERT INTO companies ("name", status, date_start) VALUES
    ('Super Company', True, '2016-02-12'),
    ('Imperium', True, '2000-01-01'),
    ('Sold Company', False, '2016-02-12'),
    ('Federation', True, '2000-01-02');

INSERT INTO customers (first_name, last_name, status) VALUES
    ('Lary', 'Martin', True),
    ('Jessi', 'Frow', True),
    ('Migel', 'Bilman', True),
    ('Joke', 'Jenkins', True),
    ('Lady', 'Bret', False);

INSERT INTO projects (company_id, customer_id, "name", budget, status, date_start, date_end, "cost") VALUES
    (1, 2, 'Alfa', 20000.40, True, '2015-12-22', NULL, FLOOR((RANDOM() + 0.3) * 20000)),
    (2, 4, 'Deth Star', 9000000.99, True, '2005-11-11', NULL, FLOOR((RANDOM() + 0.3) * 20000)),
    (2, 2, 'Beta', 1104.40, True, '2016-12-22', '2017-01-15', FLOOR((RANDOM() + 0.3) * 20000)),
    (3, 5, 'Top Secret', 10.40, False, '2020-06-08', '2020-07-08', FLOOR((RANDOM() + 0.3) * 20000)),
    (4, 1, 'Quota', 7777.77, True, '2019-12-22', NULL, FLOOR((RANDOM() + 0.3) * 20000)),
    (4, 3, 'Limbo', 666666.77, True, '2004-09-09', NULL, FLOOR((RANDOM() + 0.3) * 20000));

INSERT INTO developers_projects (developer_id, project_id) VALUES
    (1, 5),
    (1, 1),
    (1, 2),
    (2, 2),
    (2, 6),
    (4, 1),
    (4, 2),
    (3, 1);