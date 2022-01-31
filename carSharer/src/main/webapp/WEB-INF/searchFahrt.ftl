<html>
    <head>
        <meta charset="UTF-8">
        <title>CarSharer</title>
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

            #title h2 {

                border: 2px solid #fff;
                padding: 5px;
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
            <h1> CarSharer Website </h1>
            </div>

            <div id="main">

                <form method="POST" action="searchFahrt">
                 <div>
                      <label> Start: </label>
                        <input type="text" name="start" > <br>
                </div>
                         
                <div>
                      <label> Ziel: </label>
                       <input type="text" name="ziel" > <br>
                </div>
                
                 <div>
                        <label> Fahrtdatum </label>
                                <input type="date" name="fahrtDatum" id="fahrtDatum" value="2022-02-12">
                 </div>
                
                <div id="search" >
                        <input type="submit" name="search" value="search">
                </div>
               

                <div id="title">
                   <h2> Suchergebnisse</h2>
                </div>

                <div id="fahrt">
                <h2>Fahrten</h2>
                <table id="fahrten">
                   <thead>
                       <tr>
                          <th>Icon</th>
                          <th><span>Startort</span></th>
                          <th><span>Zielort</span></th>
                          <th><span>Fahrtkosten</span></th>
                       </tr>
                   </thead>

                   <tbody>
                   
                    <div id="fahrt">
                      
                      <table id="fahrt">
         
                          <#list fahrt as fahrt1>
                               <tr>
                                   <td>${fahrt1.icon}</td>
                                   <td><span>${fahrt1.startort}</span></td>
                                   <td><span>${fahrt1.zielort}</span></td>
                                   <td><span>${fahrt1.fahrtkosten}</span></td>
                               </tr>
                          </#list>
                  </table>
                      
                </div>
                   </form>
            </div>


            </div>
            <div>

            </div>
        </div>
    </body>
</html>
