<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Map</title>
    <style>
        #map{
            height:400px;
            width: 100%;
        }
    </style>
</head>
<body>
    <h1>My Map</h1>
    <div id="map"></div>
    <script>
        var markers = [];
    </script>

    <#list meteorites as met>
        <script>
            markers.push(
            {
                cords:{lat:${met.getLatitude()} ,lng:${met.getLongitude()} },
                content:'<h1>This meteorite weighs ${met.getDescription()}<h1>'
            });

            var x = ${met.getLatitude()};
            var y = ${met.getLongitude()};
        </script>
    </#list>
    <script>
        function initMap(){
            //map options
            var options = {
                zoom:8,
                center:{lat:x,lng:y}
            };
            //new map
            var map = new google.maps.Map(document.getElementById('map'),options);

            //Loop through the marker array
            for(var i = 0; i< markers.length; i++){
                addMarker(markers[i]);
            }

            //Add Marker Function
            function addMarker(props){
                var marker = new google.maps.Marker({
                    position:props.cords,
                    map:map,
                    //icon: props.iconImage
                });

                //check for custom icon
                if(props.iconImage){
                    //Set icon image
                    marker.setIcon(props.iconImage);
                }

                //check for content
                if(props.content){
                    var infoWindow = new google.maps.InfoWindow({
                        content: props.content
                    });
                }
                marker.addListener('click',function(){
                    infoWindow.open(map,marker);
                });
            }//addMarker
        }
    </script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB54Ya2-xg84ZgJFNquS3JyiIg6NxOhaLI&callback=initMap">
    </script>
</body>
</html>