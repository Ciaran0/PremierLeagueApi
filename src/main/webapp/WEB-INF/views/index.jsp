<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Premier League API</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        body { padding-top: 40px; }
        @media screen and (max-width: 768px) {
            body { padding-top: 0px; }
        }
    </style>

</head>
<body>
<div class="container">
    <div class="row">
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">

            </div>
        </nav>
    </div>
    <br />
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <table id="premierLeagueTable" class="table table-striped table-hover">
                <th>Position</th> <th>Team</th> <th>Points</th>
            </table>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script>
    $(document).ready(function(){
        $.getJSON( "/api/v1/getPremierLeagueTable", function( data ) {
            //var items = [];
            $.each( data, function( key, val ) {
                if(key == 1){
                    $('#premierLeagueTable tbody').append( "<tr class='active' id='" + key + "'><td>" + key + "</td><td>"+val.teamname+"</td><td>"+val.points+"</td></tr>" );
                }
                else if (key >17){
                    $('#premierLeagueTable tbody').append( "<tr class='danger' id='" + key + "'><td>" + key + "</td><td>"+val.teamname+"</td><td>"+val.points+"</td></tr>" );
                } else
                {
                    $('#premierLeagueTable tbody').append( "<tr id='" + key + "'><td>" + key + "</td><td>"+val.teamname+"</td><td>"+val.points+"</td></tr>" );
                }
                //.css('textTransform', 'capitalize')
            });
        });
    });
</script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>