# Schritt 1 Studiengänge initialisieren
# Initialisierung der Studiengänge unbedingt notwendig für die Nutzung des Immatrikualtionsprozess
INSERT INTO studiengang (studiengang_id, studiengang_name, numerus_clausus_note, studiengang_platzzahl, studiengang_freie_plaetze, vorraussetzung_test,nc_notwendig , zulassungszeitraum, semesterbeginn, zahlungszeitraum)
VALUES
    (1, "Mathematik", 0, 30, 5, 0, 0, '2019-12-11', '2020-04-11', '2020-01-10'),
    (2, "Maschinenbau", 0, 100, 5, 0,  0,'2019-12-11','2020-04-11', '2020-01-10'),
    (3, "Physik", 0, 30, 5, 1,  0,'2019-12-11','2020-04-11', '2020-01-10'),
    (4, "Geschichte", 0, 5, 5, 0, 0, '2019-12-11','2020-04-11', '2020-01-10'),
    (5, "Theologie", 0, 20, 4, 0, 0,'2019-12-11','2020-04-11', '2020-01-10'),
    (6, "Informatik", 0, 80, 5, 0, 0, '2019-12-11','2020-04-11', '2020-01-10'),
    (7, "Bio Informatik", 0, 30, 5, 0, 0, '2019-12-11','2020-04-11', '2020-01-10'),
    (8, "Bio Medizin", 0, 10, 3, 0, 0, '2019-12-11','2020-04-11', '2020-01-10'), 
    (9, "Biologie", 0, 20, 10, 0, 0, '2019-12-11','2020-04-11', '2020-01-10'),
    (10, "Medizinische Informatik", 0, 10, 5, 0,  0,'2019-12-11','2020-04-11', '2020-01-10');
    
    # Schritt 2: Bewerber für den NC-Test initialisieren
    # Zum Test des Numerus Clausus können die Folgenden Studenten für den Studiengang Informatik in die Datenbank geladen werden:
    # Diese werden jedoch nicht im Immatrikulationsprozess berücksichtigt sondern nur bei der Berechnung des NCs.
	# Sie dienen dazu, dass man die Wirksamkeit des NCs mit der Eingabe eines einzelnen Bewerbers testen kann.
    # In der realen Anwendung sollten direkte Manipulationen der Datenbank möglichst gut geschützt/verhindert werden.
    INSERT INTO bewerber (bewerber_id, nachname, vorname, email, geburtsort, nationalitaet, wohnort, adresse, abiturnote, geburtsdatum, studiengang_id) VALUES
    (1, "Mohammed", "Ali", "mohammedAli@testmail.com", "Louisville",  "Amerikanisch", "Denver", "First Street", "3.1",  "1980-04-11", 6),
    (2, "Stephe", "Wozniak", "Stephewozniak@testmail.com", "England",  "Amerikanisch", "Washington", "First Street", "1.9",  "1980-04-11", 6),
    (3, "Ada", "Lovelace", "AdaLovelace@testmail.com", "England",  "Engländisch", "London", "First Street", "1.4",  "1980-04-11", 6),
    (4, "Grace", "Hopper", "GraceHopper@testmail.com", "Louisville",  "Amerikanisch", "Washington", "First Street", "1.2",  "1980-04-11", 6),
    (5, "Albert", "Einstein", "albertEinstein@testmail.com", "Louisville",  "Amerikanisch", "Denver", "First Street", "3.3",  "1980-04-11", 6),
    (6, "Mahatma", "Ganghi", "mahatmaGandhi@testmail.com", "Louisville",  "Indisch", "Neu Dheli", "First Street", "4.9",  "1980-04-11", 6),
    (7, "Wolfgang Amadeus", "Mozart", "wolgangAMozart@testmail.com", "Salzburh",  "Österreich", "Salzburg", "Hauptstraße", "4.2",  "1980-04-11", 6);
    
# Oft genutzte Queries zur Kontrolle der Datenbank    
select * from bewerber; 
select * from bewerbungsunterlagen;
select * from student;
SELECT * FROM studiengang;
select * from immatrikulationsverfahren_status;
select * from student_studiengang;
select * from bewerber WHERE studiengang_id='6';
select *  from bewerber WHERE bewerber.bewerber_id='345169008';



    
    
    