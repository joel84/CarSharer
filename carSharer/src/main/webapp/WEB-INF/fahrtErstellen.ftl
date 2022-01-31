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
        </style>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
            <h1> CarSharer Website </h1>
            </div>


            <div>
                <div>
                    <h2> Fahrt erstellen </h2>

                    <form method="POST" action="fahrtErstellen" >
                       <legend>Required Information</legend>
                         <div>
                                <label> Von: </label>
                                <input type="text" name="von" required> <br>
                         </div>
                         
                         <div>
                                <label> Nach: </label>
                                <input type="text" name="nach" required> <br>
                         </div>
                         
                         <div>
                                <label> Maximale Kapazität: </label>
                                <input type="number" name="maxKap"  required> <br>
                         </div>
                         
                         <div>
                                <label>Fahrtkosten (&euro;)</label>
                                <input type="number" name="fahrtkosten" required> <br>
                         </div>
                         
                        <div>
                                <label> Transportmittel: </label>
                                <input type="radio" id="transportmittel1" name="transport" value="auto" required>
                                <label for="transportmittel1">Auto</label>

                                <input type="radio" id="transportmittel2" name="transport" value="bus" required>
                                <label for="transportmittel2">Bus</label>

                                <input type="radio" id="transportmittel3" name="transport" value="kleintransporter" required>
                                <label for="transportmittel3">Kleintransporter</label>
                        </div>
                        
                        <div>
                                <label> Fahrtdatum </label>
                                <input type="date" name="fahrtDatum" id="fahrtDatum" value="2022-02-12"required>
                                <input type="time" name="fahrtDatum2" id="fahrtDatum2" value = "12:00" required>
                         </div>
                         
                        <div>
                                <label> Beschreibung: </label>
                                <textarea id="beschreibung" name="beschreibung" maxlength = "50" rows="4" cols="50"> </textarea>
                         </div>

                        <div id="inputs">
                            <input type="submit" value="Erstellen">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
