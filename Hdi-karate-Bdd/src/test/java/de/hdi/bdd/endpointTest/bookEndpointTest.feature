Feature: Book-Store karate test script
  
  Background:
    * url 'http://localhost:7777/'
 
#Es muss geprueft werden, dass ein erfolgreicher Vorgang "Buch Anlegen" mit dem richtigen "Create_Book_Success_String" best�tigt wird.
Scenario: Wenn ein Buch erfolgreich erstellt wurde, muss dies wie folgt bestaetigt werden: 'XY-Buch von XY-Autor wurde erfolgreich zu Ihrer Bibliothek hinzugefuegt'.
    Given path 'saveBook'
    And request {"isbn": "1234567891231", "title": "TitleTest","autor": "AutorTest","erscheinungsort": "erscheinungsortTest","erscheinungsjahr": "erscheinungsjahrTest","sprache": "spracheTest","laenge": 1234,"additional": "additionalTest"}
    When method post
    Then status 200
    And print response
    And match responseType == 'string'
    And print 'ResponseHeaders is: ', responseHeaders
    And match response ==  'TitleTest von AutorTest wurde erfolgreich zu Ihrer Bibliothek hinzugefuegt' 

 
#Es muss geprueft werden, ob Anlagen eines Buches mit fehlenden erforderlichen Buchinformationen nicht akzeptiert werden 
#und zu einer Exception (+ fachliche Fehlermeldung) fuehren.
Scenario: Wenn ein Buch ohne Titel erstellt wird, muss die Antwort mit dem Status 400 zurueckgegeben werden. Die Fehlermeldung muss 'Der Titel des Buches darf nicht null sein' enthalten.
    Given path 'saveBook'
    And request {"isbn": "1234567891231","autor": "autor","erscheinungsort": "erscheinungsort","erscheinungsjahr": "erscheinungsjahr","sprache": "sprache","laenge": 1234,"additional": "additional"}
    When method post
    Then status 400
    And print response
    And match responseType == 'string'
    And print 'ResponseHeaders is: ', responseHeaders
    And match response contains  'Der Titel des Buches darf nicht null sein'    

#Es muss geprueft werden, ob die fachlich nicht validierten Eingabefelder nicht akzeptiert werden.
  