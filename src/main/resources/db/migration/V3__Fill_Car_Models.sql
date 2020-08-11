insert into models (id, name, maker_id)
values (1, 'CL', 1),
       (2, 'CSX', 1),
       (3, 'EL', 1),
       (4, 'A7', 2),
       (5, 'A8', 2),
       (6, 'S8', 2),
       (7, 'Aveo', 3),
       (8, 'Camaro', 3),
       (9, 'Spark', 3),
       (10, 'Explorer', 4),
       (11, 'Focus', 4),
       (12, 'Mondeo', 4),
       (13, 'CR-V', 5),
       (14, 'Civic', 5),
       (15, 'HR-V', 5),
       (16, 'Genesis', 6),
       (17, 'Porter', 6),
       (18, 'Solaris', 6),
       (19, 'Optima', 6),
       (20, 'RIO', 7),
       (21, 'Spectra', 7),
       (22, 'CX-7', 8),
       (23, 'MPV', 8),
       (24, 'RX-8', 8),
       (25, 'A-класс', 9),
       (26, 'C-класс', 9),
       (27, 'S-класс', 9),
       (28, 'Delica', 10),
       (29, 'Eclipse', 10),
       (30, 'Lancer Evolution', 10),
       (31, 'Juke', 11),
       (32, 'Murano', 11),
       (33, 'Patrol',11),
       (34, 'Astra', 12),
       (35, 'Corsa', 12),
       (36, 'Vivaro', 12),
       (37, 'Octavia', 13),
       (38, 'Rapid', 13),
       (39, 'Yeti', 13),
       (40, 'Forester', 14),
       (41, 'Impreza', 14),
       (42, 'Legacy', 14),
       (43, 'Escudo', 15),
       (44, 'Jimny', 15),
       (45, 'Samurai', 15),
       (46, 'Corolla', 16),
       (47, 'Cresta', 16),
       (48, 'Mark II', 16),
       (49, 'Polo', 17),
       (50, 'Tiguan', 17),
       (51, 'Touareg', 17),
       (52, 'C30', 18),
       (53, 'S40', 18),
       (54, 'V70', 18);

alter sequence models_id_seq restart with 55;