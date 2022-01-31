<html>
    <head>
        <meta charset="UTF-8">
        <title>Carsharer</title>
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


        </style>
    </head>

    <body>
        <div id="wrapper">
            <div id="header">
            <h1> Carsharer </h1>
            </div>

            <div>

                <div id="home">
                    <button onclick="window.location.href='home'" >Home</button>
                </div>

                <div id="info">
                    <h1>Offene Fahrten des "besten Fahrer"</h1>
                    <p>Fahrer: ${userInfo.email}</p><br>
                    <p>Durchschnittsrating: ${fahrt.Durchschnittsrating}</p><br>
                   
                </div>

                <div id="fahrt">
                    <h2>Bonus</h2>
                      <table id="fahrt">
                          <thead>
                              <tr>
                                  <th>Icon</th>
                                  <th>Fahrt-ID</th>
                                  <th>Von</th>
                                  <th>Nach</th>
                              </tr>
                          </thead>

                          <tbody>
                              <#list erstProj as erstP>
                                   <tr>
                                       <td>${fahrt.icon}</td>
                                       <td><a href="viewProject?kennung=${fahrt.kennung}">${fahrt.Fahrt-ID}</a></td>
                                       <td>${fahrt.von}</td>
                                       <td>${fahrt.nach}</td>
                                   </tr>
                              </#list>
                          </tbody>
                      </table>
                </div>
            </div>
        </div>
    </body>
</html>
