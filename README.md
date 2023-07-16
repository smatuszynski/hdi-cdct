|     |
|-----|
|     |
|     |

Kopfzeilen-Layouttabelle

# 

Was ist Wiremock?

Vereinfacht ausgedrückt handelt es sich bei Wiremock um ein
Mocking-Setup für Integrationstests. Es handelt sich einfach um einen
Mock-Server, der hochgradig konfigurierbar ist, um eine erwartete
Antwort auf eine bestimmte Anfrage zurückzugeben.

Es wird häufig während der Entwicklung und vor allem beim
Integrationstest verwendet, während ein System oder Service mit einer
oder mehreren externen Abhängigkeiten/Services kommuniziert.

Anwendungsbeispiel

Angenommen, wir beginnen jetzt mit der Erstellung einer neuen Anwendung.
Diese Anwendung ist auf externe API angewiesen, die uns andere Teams zur
Verfügung stellen.

Externe API-Systeme sind möglicherweise nicht immer verfügbar. d. h. wir
sind stark von externen Systemen abhängig und jede Ausfallzeit dort
wirkt sich auf unsere Tests und indirekt auf den Entwicklungsprozess
aus.

Mit Wiremock können wir die externen Services, mit denen wir verbunden
sind, mocken (simulieren). Dadurch können wir sie so einstellen, dass
sie den erwarteten Antworten ähneln, die voraussichtlich von den
externen Services zurückgegeben werden.

Dies verschafft uns Unabhängigkeit in der Entwicklungsphase und gibt uns
die Garantie, dass unser System während der Testphase nicht durch
externe Services beeinträchtigt wird (wir testen nur, was wir entwickelt
haben).

<img src="./media/image1.jpeg"
style="width:6.35417in;height:4.59074in" />

Wie reagiert Wiremock auf eine bestimmte Anfrage (Stubbing & Request
Matching)?

Wiremock ist ein gesteuerter Mock-Server. Die Art und Weise, wie er auf
eine bestimmte Anfrage reagiert, besteht zunächst darin, alle relevanten
simulierten Antworten in einem Ordner mit dem Namen „Mappings“ zu
speichern (**Stubbing**). Das zweite Kernkonzept ist (**Request
Matching**). Wiremock verfügt über eine Matcher-Komponente, die
eingehende Anfragen mit den gespeicherten „Mappings“ vergleicht. Wenn
ein erfolgreicher „Match“ zurückgegeben wird, wird der als Antwort für
die angegebene Anfrage zurückgegeben. Andernfalls wird eine
Fehlermeldung angezeigt, dass kein Match/Stub gefunden wurde.

<img src="./media/image2.jpeg"
style="width:5.66789in;height:3.46875in" />

Stubbing von Responses mit JSON-Mappings

Eine Antwort auf eine bestimmte Anfrage kann mit einer Mapping.json
simuliert oder gemockt werden.

<img src="./media/image3.png"
style="width:7.26042in;height:3.36458in" />

1.  Teil_1: Es ist der Teil, der dem Matching-Prozess gewidmet ist,
    wobei die Matcher-Komponente bei der Suche nach der geeigneten
    Antwort auf die von WireMock empfangene Anfrage darauf angewiesen
    ist.

- Request: Enthält Request-Daten der zu mockeden API.

- Method: HTTP-Methode der zu mockeden API

- URL: URL der API

- BodyPatterns: request-body-data der API

- EqualToJson: Enthält JSON-Daten, die im request-body der API übergeben
  werden sollen

2.  Teil_2: Die nach dem Matching-Prozess zurückgegebene Response.

- Response: Enthält Antwortdaten der zu mockeden API

- Status: HTTP-Statuscode der API

- Body: Enthält Response_Body

- BodyFileName: Enthält den Dateinamen (.json/.xml) für die Antwort

- Headers: Headers, die in der Antwort und/oder Anfrage vorhanden sind

Stubbing (OpenAPI-Specification TO JSON-Mappings)

Externe API-Systeme sind während des Entwicklungsprozesses
möglicherweise nicht immer verfügbar. In vielen Fällen befindet es sich
entweder in der Entwicklungsphase oder ist noch nicht entwickelt. In
solchen Fällen fragen wir möglicherweise das externe Team nach der
API-Spezifikation, d. h. wie die API-Anfragen und -Antworten aussehen
sollten.

Eine der heute am häufigsten verwendeten API-Spezifikationen ist die
OpenAPI-Spezifikation.

Die OpenAPI-Spezifikation ist ein Standard zur Beschreibung von
HTTP-Schnittstellen. Es kann auch zur Definition von
RESTful-Schnittstellen verwendet werden.

- openapi: 3.0.3

- info:

-   title: Adresse-MS

-   description: Microservice für die Verwaltung von Kundenadressen

-   version: 1.0.11

- servers:

-   - url: http://localhost:8807/kundenadressen/api

- paths:

-   /updateKundenadresse:

-     put:

-       tags:

-         - Kundenadresse

-       summary: Update eine vorhandene Adresse

-       description: Update eine bestehende Adresse per KundenId

-       operationId: updateAdresse

-       requestBody:

-         content:

-           application/json:

-             schema:

-               \$ref: "#/components/schemas/Kundenadresse"

-         required: true

-       responses:

-         "200":

-           description: Successful operation

-           content:

-             application/json:

-               schema:

-                 \$ref: "#/components/schemas/Kundenadresse"

-         "400":

-           description: Invalid ID supplied

-         "404":

-           description: Kundenadresse not found

-         "405":

-           description: Validation exception

-   /addKundenadresse:

-     post:

-       tags:

-         - Kundenadresse

-       summary: Neue Kundenadresse hinzufügen

-       description: Neue Kundenadresse hinzufügen

-       operationId: addKundenadresse

-       requestBody:

-         description: Neue Kundenadresse anlegen

-         content:

-           application/json:

-             schema:

-               \$ref: "#/components/schemas/Kundenadresse"

-         required: true

-       responses:

-         "200":

-           description: Successful operation

-           content:

-             application/json:

-               schema:

-                 \$ref: "#/components/schemas/Kundenadresse"

-         "405":

-           description: Invalid input

-   /findByKundenId:

-     get:

-       tags:

-         - Kundenadresse

-       summary: Kundenadresse nach KundenId suchen

-       description: Kundenadresse nach KundenId suchen

-       operationId: findByKundenId

-       parameters:

-         - name: KundenId

-           in: query

-           required: true

-           schema:

-             type: string

-             default: "ID123456"

-       responses:

-         "200":

-           description: successful operation

-           content:

-             application/json:

-               schema:

-                 \$ref: "#/components/schemas/Kundenadresse"

-         "400":

-           description: Invalid KundenId value

-   /deleteByKundenId:

-     delete:

-       tags:

-         - Kundenadresse

-       summary: Kundenadresse nach KundenId löschen

-       description: Kundenadresse nach KundenId löschen

-       operationId: deleteByKundenId

-       parameters:

-         - name: KundenId

-           in: query

-           required: true

-           schema:

-             type: string

-             default: "ID123456"

-       responses:

-         "200":

-           description: successful operation

-         "400":

-           description: Invalid KundenId value

-   /adressevalidierung:

-     put:

-       tags:

-         - Validierung

-       summary: Adresse Validierung

-       description: Adressvalidierung, hier z.B. Die Geocoding API wird
  als externer Service verwendet, um die Adressen zu validieren und
  einige Adressvorschläge zurückzugeben, wenn die Adresse falsch ist.

-       operationId: adressevalidierung

-       requestBody:

-         content:

-           application/json:

-             schema:

-               \$ref: "#/components/schemas/Adresse"

-         required: true

-       responses:

-         "200":

-           description: Successful operation

-           content:

-             application/json:

-               schema:

-                 \$ref: "#/components/schemas/Adresse"

-         "400":

-           description: Invalid ID supplied

-         "404":

-           description: Kundenadresse not found

-         "405":

-           description: validation exception

- components:

-   schemas:

-     Kundenadresse:

-       type: object

-       properties:

-         KundenId:

-           type: string

-           example: "ID123456"

-         adresse:

-           \$ref: "#/components/schemas/Adresse"

-     Adresse:

-       type: object

-       properties:

-         Straße:

-           type: string

-           example: "Charles-de-Gaulle-Platz"

-         hausnummer:

-           type: string

-           example: "1"

-         postleitzahl:

-           type: string

-           example: "50679"

-         ort:

-           type: string

-           example: "Köln"

-         land:

-           type: string

-           example: "Deutschland"

- 

Im Folgenden erstellen wir einige Stubs basierend auf der oben genannten
OpenAPI-Spezifikation, die es dem WireMock-Server ermöglichen, die
externe API zu simulieren, die wir mocken möchten.

1.  /updateKundenadresse

> <img src="./media/image4.png"
> style="width:6.03388in;height:1.13625in" />

- updateKundenadresse.json ist unser Stub, in dem wir unser
  Mock-Verhalten von (/updateCustomerAddress) definiert haben.

- {

-     "id" : "0a056750-f38b-3e76-bc63-181f5ac3fe44",

-     "request" : {

-       "url" : "/kundenadressen/api/updateKundenadresse",

-       "method" : "PUT",

-       "headers": {

-         "Content-Type": {

-           "equalTo": "application/json"

-         }

-       },

-       "bodyPatterns" : \[ {

-         "equalToJson": {"KundenId": "ID123456", "adresse": {
  "straße":"Charles-de-Gaulle-Platz", "hausnummer":"1",
  "postleitzahl":"50679", "ort":"Köln", "land":"Deutschland"}

-         }

-       } \]

-     },

-     "response" : {

-       "status" : 200,

-       "bodyFileName" : "Kundenadresse.json",

-       "headers" : {

-         "Content-Type" : "application/json",

-       }

-     },

-     "uuid" : "0a056750-f38b-3e76-bc63-181f5ac3fe44"

-   }

- Kundenadresse.json enthält den RespanseBody.

- {

-     "KundenId": "ID123456",

-     "adresse": {

-         "straße":"Charles-de-Gaulle-Platz",

-         "hausnummer":"1",

-         "postleitzahl":"50679",

-         "ort":"Köln",

-         "land":"Deutschland"

-     }

- }

- Postman Test

<img src="./media/image5.png"
style="width:7.26806in;height:3.76042in" />

WireMock Admin-api

Wiremock stellt Basis-URLs (endpoints) für die Verwaltung von Stubs
bereit:

- /\_\_admin/mappings
  (https://wiremock.org/docs/standalone/admin-api-reference/)

<table>
<colgroup>
<col style="width: 15%" />
<col style="width: 24%" />
<col style="width: 60%" />
</colgroup>
<thead>
<tr class="header">
<th colspan="2"></th>
<th></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td rowspan="3"><strong>GET</strong></td>
<td colspan="2"><p>Get all stub mappings (/__admin/mappings)</p>
<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th colspan="2"><strong>QUERY PARAMETERS</strong></th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td><strong>Limit (integer)</strong></td>
<td>Die maximale Anzahl der zurückzugebenden Ergebnisse</td>
</tr>
<tr class="even">
<td><strong>Offset (integer)</strong></td>
<td>Der Startindex der zurückzugebenden Ergebnisse</td>
</tr>
</tbody>
</table></td>
</tr>
<tr class="even">
<td colspan="2"><p>Get stub mapping by ID
(/__admin/mappings/{stubMappingId})</p>
<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th colspan="2">PATH PARAMETER</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>StubMappingId (string)</td>
<td>Die UUID der Stub-Mapping</td>
</tr>
</tbody>
</table></td>
</tr>
<tr class="odd">
<td colspan="2"></td>
</tr>
<tr class="even">
<td rowspan="5"><strong>POST</strong></td>
<td colspan="2"><p>Create a new stub mapping (/__admin/mappings)</p>
<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th colspan="2">REQUEST BODY SCHEMA</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Id (string)</td>
<td>Die eindeutige Kennung dieser Stub-Mapping</td>
</tr>
<tr class="even">
<td>uuid (string)</td>
<td>Alias für die ID</td>
</tr>
<tr class="odd">
<td>name (string)</td>
<td>Der Name der Stub-Mapping</td>
</tr>
<tr class="even">
<td>persistent (boolean)</td>
<td>Gibt an, dass die Stub- Mapping sofort beim
Erstellen/Aktualisieren/Löschen beibehalten werden soll und das
Zurücksetzen auf den Standardwert übersteht.</td>
</tr>
<tr class="odd">
<td>Priority (integer &gt;=1)</td>
<td>Die Priorität dieser Stub-Mapping im Vergleich zu anderen. 1 ist am
höchsten.</td>
</tr>
<tr class="even">
<td>scenarioName (string)</td>
<td>Der Name des Szenarios, zu dem diese Stub-Mapping gehört.</td>
</tr>
<tr class="odd">
<td>requiredScenarioState (string)</td>
<td>Der erforderliche Zustand des Szenarios, damit dieser Stub
abgeglichen werden kann.</td>
</tr>
<tr class="even">
<td>newScenarioState (string)</td>
<td>Der neue Status, auf den das Szenario aktualisiert werden soll,
nachdem dieser Stub bereitgestellt wurde.</td>
</tr>
</tbody>
</table></td>
</tr>
<tr class="odd">
<td colspan="2"><p>Persist stub mappings (/__admin/mappings/save)</p>
<p>Speicherung aller persistenten Stub-mappings im
Backup-Speicher</p></td>
</tr>
<tr class="even">
<td colspan="2"><p>Import stub mappings (/__admin/mappings/import)</p>
<p>Import spezifischer Stub-mappings in den Backup-Speicher</p></td>
</tr>
<tr class="odd">
<td colspan="2"><p>Find stubs by matching on their metadata
(/__admin/mappings/find-by-metadata)</p>
<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th colspan="2">REQUEST BODY SCHEMA (One of)</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>equalTo (boolean)</td>
<td>String equals</td>
</tr>
<tr class="even">
<td>contains (string)</td>
<td>String contains</td>
</tr>
<tr class="odd">
<td>matches (string)</td>
<td>Regular expression match</td>
</tr>
<tr class="even">
<td>doesNotMatch (string)</td>
<td>Negative regular expression match</td>
</tr>
<tr class="odd">
<td>equalToJson (string)</td>
<td>JSON equals</td>
</tr>
<tr class="even">
<td>matchesJsonPath (string)</td>
<td>JSONPath match</td>
</tr>
<tr class="odd">
<td>equalToXml (string)</td>
<td>XML equality</td>
</tr>
<tr class="even">
<td>matchesXpath (string)</td>
<td>XPath match</td>
</tr>
</tbody>
</table></td>
</tr>
<tr class="even">
<td colspan="2"><p>Delete stub mappings matching metadata
(/__admin/mappings/remove-by-metadata)</p>
<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th colspan="2">REQUEST BODY SCHEMA (One of)</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>equalTo (boolean)</td>
<td>String equals</td>
</tr>
<tr class="even">
<td>contains (string)</td>
<td>String contains</td>
</tr>
<tr class="odd">
<td>matches (string)</td>
<td>Regular expression match</td>
</tr>
<tr class="even">
<td>doesNotMatch (string)</td>
<td>Negative regular expression match</td>
</tr>
<tr class="odd">
<td>equalToJson (string)</td>
<td>JSON equals</td>
</tr>
<tr class="even">
<td>matchesJsonPath (string)</td>
<td>JSONPath match</td>
</tr>
<tr class="odd">
<td>equalToXml (string)</td>
<td>XML equality</td>
</tr>
<tr class="even">
<td>matchesXpath (string)</td>
<td>XPath match</td>
</tr>
</tbody>
</table></td>
</tr>
<tr class="odd">
<td><strong>PUT</strong></td>
<td colspan="2"><p>Update a stub mapping
(/__admin/mappings/{stubMappingId})</p>
<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th colspan="2">REQUEST BODY SCHEMA</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>Id (string)</td>
<td>Die eindeutige Kennung dieser Stub-Mapping</td>
</tr>
<tr class="even">
<td>uuid (string)</td>
<td>Alias für die ID</td>
</tr>
<tr class="odd">
<td>name (string)</td>
<td>Der Name der Stub-Mapping</td>
</tr>
<tr class="even">
<td>persistent (boolean)</td>
<td>Gibt an, dass die Stub- Mapping sofort beim
Erstellen/Aktualisieren/Löschen beibehalten werden soll und das
Zurücksetzen auf den Standardwert übersteht.</td>
</tr>
<tr class="odd">
<td>Priority (integer &gt;=1)</td>
<td>Die Priorität dieser Stub-Mapping im Vergleich zu anderen. 1 ist am
höchsten.</td>
</tr>
<tr class="even">
<td>scenarioName (string)</td>
<td>Der Name des Szenarios, zu dem diese Stub-Mapping gehört.</td>
</tr>
<tr class="odd">
<td>requiredScenarioState (string)</td>
<td>Der erforderliche Zustand des Szenarios, damit dieser Stub
abgeglichen werden kann.</td>
</tr>
<tr class="even">
<td>newScenarioState (string)</td>
<td>Der neue Status, auf den das Szenario aktualisiert werden soll,
nachdem dieser Stub bereitgestellt wurde.</td>
</tr>
</tbody>
</table></td>
</tr>
<tr class="even">
<td rowspan="2"><strong>DEL</strong></td>
<td colspan="2">Delete all stub mappings (/__admin/mappings)</td>
</tr>
<tr class="odd">
<td colspan="2"><p>Delete a stub mapping
(/__admin/mappings/{stubMappingId})</p>
<table>
<colgroup>
<col style="width: 50%" />
<col style="width: 50%" />
</colgroup>
<thead>
<tr class="header">
<th colspan="2">PATH PARAMETER</th>
</tr>
</thead>
<tbody>
<tr class="odd">
<td>StubMappingId (string)</td>
<td>Die UUID der Stub-Mapping</td>
</tr>
</tbody>
</table></td>
</tr>
</tbody>
</table>
