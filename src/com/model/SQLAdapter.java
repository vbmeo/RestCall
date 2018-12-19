package com.model;


/**
 * serve per!!!<br>
 * ESEGUE DIRETTAMENTE UNA QUERY IN BATIS<BR>
 * Avendo creato un modello oggetto che ritorna semplicemente <br>
 * la stringa passata, batis non sa di che tipo è e quindi lo passa letteralmente senza mettere apici
 * @param queryXbatis
 * @return
 */
public class SQLAdapter {  
String sql;  

public SQLAdapter(String sql) {  
    this.sql = sql;  
}  

public String getSql() {  
    return sql;  
}  

public void setSql(String sql) {  
    this.sql = sql;  
}   }
