<html>
    <head>
        <meta charset="UTF-8">
        <title>Fahrt View</title>
        <style type="text/css">
            * {
               margin:0;
               padding:0;
            }

            body{
               text-align:center;
               background: #efe4bf none repeat scroll 0 0;
            }
            
            span:before{
                content:" "; 
                display:inline-block; 
                width:100px;
            }

            #wrapper{
               width:960px;
               margin:0 auto;
               text-align:left;
               background-color: #fff;
               border-radius: 0 0 10px 10px;
               padding: 20px;
               box-shadow: 1px -2px 14px rgba(0, 0, 0, 0.4);
            }

            #header{
             color: #fff;
             background-color: #2c5b9c;
             height: 3.5em;
             padding: 1em 0em 1em 1em;

            }

            #site{
                background-color: #fff;
                padding: 20px 0px 0px 0px;
            }
            .centerBlock{
                margin:0 auto;
            }


            #home {
                border: 2px solid #fff;
                padding: 20px;
            }

            #search {
                border: 2px solid #fff;
                padding: 10px;
            }

            #info {
                border: 2px solid #fff;
                padding: 10px;
            }

            #info h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #info p {
                border: 2px solid #fff;
                padding: 2px;
            }

            #actions {
                border: 2px solid #fff;
                padding: 10px;
            }

            #actions h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #spender {
                border: 2px solid #fff;
                padding: 10px;
            }

            #spender h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #spender ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
            }

            #comments {
                border: 2px solid #fff;
                padding: 10px;
            }

            #comments h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #comments ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
            }

            #comment {
                border: 2px solid #fff;
                padding: 20px;
            }


        </style>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
            <h1> Fahrt View</h1>
            </div>

            <div>


                <div id="info">
                    <h2>Informationen</h2>
                    <p>${fahrt.icon}</p>
                    <p>Anbieter :  ${fahrt.email}</p>
                    <p>Fahrtdatum und -uhrzeit :  ${fahrt.fahrtdatumzeit}</p>
                    <p>Von :  ${fahrt.startort}</p>
                    <p>Nach :  ${fahrt.zielort}</p>
                    <p>Anzahl freier Plätze: ${fahrt.freiePlaetze}</p>
                    <p>Fahrtkosten(€) :  ${fahrt.fahrtkosten}</p>
                    <p>Status :  ${fahrt.status}</p>
                    <p>Beschreibung :  ${fahrt.beschreibung}</p>
                    <div>
                    
                       

                <div id="actions">
                    <h2>Aktionsleiste</h2>

                <form action="viewFahrt?fid=${fid1}" method="post">
                     <label for="anzPlaetze">Anzahl Plätze für Reservierung:</label>
                        <input type="number" id="points" name="anzPlaetzeR" value="0" min ="0" max="2" step="1">
                        
                        <span><button id="button" type="submit" name="Fahrt reservieren" value="fahrtReservieren">Fahrt reservieren</button></span></form>
                        <form action="carSharer" method="post">
                        <span><button id="button" type="submit" name="Fahrt loeschen" value="fahrtLoeschen" >Fahrt Löschen</button></span>
                   
                   </form><br>     
                </div>

               
                <div id="comments">
                    <h2>Bewertungen</h2>
                    
                    <p><span style="float: right">Durchschnittsrating: ${rating}</span></p><br>
               
                      
                      <table id="bewertung">
         
                          <#list bewertung as bewertung1>
                               <tr>
                                   <td>${bewertung1.email}</td>
                                   <td><span>${bewertung1.textnachricht}</span></td>
                                   <td><span>${bewertung1.rating}</span></td>
                               </tr>
                          </#list>
                  </table>
                      
                </div>
                <div id="comment">
                    <button onclick="window.location.href='fahrtBewerten?fid=${fid1}'">Fahrt bewerten</button>
                </div>

            </div>
        </div>
    </body>
</html>
