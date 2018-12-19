# RestCall
nessuna
Test per la prova pratica Zanichelli
progetto testato con Tomcat 8.
Realizzato con JRE 1.8.
per testarlo, utilizzare il file testZanichelli.html che punta già al servizio RestCall in modo relativo ( url: '../RestCall/User')
All'interno della cartella build c'è il file compilato RestCall.war.
All'interno della cartella dbmysql c'è il file per creare il db mySql
In realtà esistono le classi che implementano la creazione del db e il suo popolamento, ma non ho avuto il tempo di esporle.
il file Parametri.properties in clashpath specifica la connessione al db utente e password, anche la stringa di connessione.
Si interroga la servlet RestCall/User per qualunque crud azione, fermorestando i tipi di connessione che devono rispettare le specifiche:
Metodo HTTP	Operazione CRUD	Descrizione
POST	Create	Crea una nuova risorsa
GET	Read	Ottiene una risorsa esistente
PUT	Update	Aggiorna una risorsa o ne modifica lo stato
DELETE	Delete	Elimina una risorsa
