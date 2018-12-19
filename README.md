# RestCall
nessuna
Test per la prova pratica Zanichelli
progetto testato con Tomcat 8.
Realizzato con JRE 1.8.
Per testarlo, utilizzare il file testZanichelli.html che punta già al servizio RestCall in modo relativo ( url: '../RestCall/User')
All'interno della cartella build c'è il file compilato RestCall.war.
All'interno della cartella dbmysql c'è il file per creare il db mySql. In realtà esistono le classi che implementano la creazione del db e il suo popolamento, ma non ho avuto il tempo di esporle.
il file Parametri.properties in clashpath specifica la connessione al db utente e password, anche la stringa di connessione, va modificato con le prorpie credenziali mysql.
Per il funzionamento del servizio:
Si interroga la servlet RestCall/User per qualunque crud azione, fermorestando i tipi di connessione che devono rispettare le specifiche:
Metodo HTTP	specifico per ogni operazione CRUD, ovvero:
Metodi:
POST	=Create.	Crea una nuova risorsa. Necessita di (nome,cognome,email,password)
  Risponde con un messaggio di tipo testo da visualizzare.
GET	=Read.	Ottiene una risorsa esistente. Non necessita di alcun parametro per la lista completa degli utenti, mentre per interrogazione   singola richiede parametro (id) di tipo int. Risponde con un messaggio di tipo json.
PUT	=Update.	Aggiorna una risorsa o ne modifica lo stato. Richiede parametri (id,nome,cognome,email,password) id int.
  Risponde con un messaggio di tipo testo da visualizzare.
DELETE	=Delete.	Elimina una risorsa. Richiede parametro (id) di tipo int.
  Risponde con un messaggio di tipo testo da visualizzare.


