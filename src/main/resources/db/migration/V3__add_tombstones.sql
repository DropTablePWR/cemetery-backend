SELECT id INTO @id_c FROM cemeteries WHERE name='Ultra cemetery';

INSERT INTO tombstones (cemetery_id, grid_x, grid_y) VALUES (@id_c, 0, 1);
INSERT INTO tombstones (cemetery_id, grid_x, grid_y) VALUES (@id_c, 1, 0);

SELECT id INTO @id_t FROM tombstones WHERE cemetery_id = @id_c and grid_x = 0 and grid_y = 1;

INSERT INTO guests (tombstone_id, first_name, last_name, birth_date, death_date) VALUES (@id_t, 'Pimpek', 'Król Dzielni', '2001-11-03', '2021-11-16')
