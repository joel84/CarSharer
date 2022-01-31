<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <style type="text/css">
            * {
               margin:0;
               padding:0;
            }

            body{
               text-align:center;
               background: #efe4bf none repeat scroll 0 0;
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

            .centerBlock{
                margin:0 auto;
            }

            #main{
                text-align: center;
            }

            #main input, label{
                margin: 20px;
            }

            #myProfile {
                border: 2px solid #fff;
                padding: 20px;
            }

            #search {
                border: 2px solid #fff;
                padding: 10px;
            }



            #project h2 {
                border: 2px solid #fff;
                padding: 5px;
            }

            #projects {
                  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                  border-collapse: collapse;
                  width: 100%;
                }

            #projects td, #projects th {
              border: 1px solid #ddd;
              padding: 8px;
            }

            #projects tr:nth-child(even){background-color: #f2f2f2;}

            #projects tr:hover {background-color: #ddd;}

            #projects th {
              padding-top: 12px;
              padding-bottom: 12px;
              text-align: left;
              background-color: #4CAF50;
              color: white;
            }

            #create {
                border: 2px solid #fff;
                padding: 20px;
            }


        </style>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
            <h1> CarSharer Website </h1>
            </div>


            <div id="project">
                  <h2>Meine Reservierten Fahrten</h2>
                  <table id="projects">
                      <thead>
                          <tr>
                              <th>Icon</th>
                              <th>Startort</th>
                              <th>Zielort</th>
                              <th>Status</th> 
                          </tr>
                      </thead>

                      <tbody>
                          <#list projZu as projZ>
                               <tr>
                                   <td><a href="viewFahrt?fid=${projZ.id}" ><img src="${projZ.icon}" /></a></td>
                                   <td>${projZ.startort}</td>
                                   <td>${projZ.zielort}</td>
                                   <td>${projZ.status}</td>
                               </tr>
                          </#list>
                      </tbody>
                  </table>
            </div>


            <div id="project">
                <h2>Offene Fahrten</h2>
                <table id="projects">
                   <thead>
                       <tr>
                          <th>Icon</th>
                          <th>Startort</th>
                          <th>Zielort</th>
                          <th>Freie Plaetze</th>
                          <th>Fahrtkosten</th>
                       </tr>
                   </thead>

                   <tbody>
                      <#list projOffen as projO>
                          <tr>
                             <td><a href="viewFahrt?fid=${projO.id}" ><img src="${projO.icon}" /></a></td>
                             <td>${projO.startort}</td>
                             <td>${projO.zielort}</td>
                             <td>${projO.freiePlaetze}</td>
                             <td>${projO.fahrtkosten}</td>
                          </tr>
                      </#list>
                   </tbody>
                </table>
            </div>

            <div id="create">
                <button onclick="window.location.href='fahrtErstellen'"> Fahrt </button>
            </div>


        </div>
    </body>
</html>
