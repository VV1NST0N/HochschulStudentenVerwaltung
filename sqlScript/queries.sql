
select * from bewerber;
select * from bewerbungsunterlagen;
select * from student;
SELECT * FROM studiengang;
select * from immatrikulationsverfahren_status;
select * from zahlungsstatusfremdsystem;
delete from studiengang WHERE studiengang_id='10';
select * from bewerber WHERE bewerber.bewerber_id='110017080';


INSERT INTO studiengang (studiengang_id, studiengang_name, numerus_clausus_note, studiengang_platzzahl, studiengang_freie_plaetze, vorraussetzung_test,nc_notwendig , zulassungszeitraum)
VALUES
    (1, "Mathematik", 0, 30, 5, 0, 0, '2019-08-11'),
    (2, "Maschinenbau", 0, 100, 5, 0,  0,'2019-08-11'),
    (3, "Physik", 0, 30, 5, 1,  0,'2019-08-11'),
    (4, "Geschichte", 0, 5, 5, 0, 0, '2019-08-11'),
    (5, "Theologie", 0, 20, 4, 0, 0,'2019-08-11'),
    (6, "Informatik", 0, 80, 3, 0, 0, '2019-08-11'),
    (7, "Bio Informatik", 0, 30, 5, 0, 0, '2019-08-11'),
    (8, "Bio Medizin", 0, 10, 3, 0, 0, '2019-08-11'), 
    (9, "Biologie", 0, 20, 10, 0, 0, '2019-08-11'),
    (10, "Medizinische Informatik", 0, 10, 5, 0,  0,'2019-08-11');
    
    INSERT INTO bewerber () VALUES
    (),
    ();
    
    
    