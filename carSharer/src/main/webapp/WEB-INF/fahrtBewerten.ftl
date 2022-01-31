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
            <h1> Fahrt Bewertung </h1>
            </div>


            <div>
                <div>
                    <h2> Bewertungstext </h2>

                    <form method="POST" action="viewFahrt?fid=${fid1}" >
                        <textarea rows="5" cols="50" placeholder="Die Fahrt war super!" name="textnachricht" ></textarea><br>
                        <h2> Bewertungsrating </h2>

                        <input type="radio" id="1" name="grade" value="1" required>
                        <label for="1">1</label>
                        <input type="radio" id="2" name="grade" value="2" required>
                        <label for="2">2</label>
                        <input type="radio" id="3" name="grade" value="3" required>
                        <label for="3">3</label><br>
                        <input type="radio" id="4" name="grade" value="4" required>
                        <label for="4">4</label>
                        <input type="radio" id="5" name="grade" value="5"required>
                        <label for="5">5</label>
                        <br>
                         
                 
                        <input type="submit" name="Bewerten" value="Bewerten">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
