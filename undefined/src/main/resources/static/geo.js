var map;

var onchangeEvent = function(){
	jQuery('.checkbox').each(function () {
		var checkbox = (this.checked ? jQuery(this) : null);
		if (checkbox != null) {
			console.log(checkbox.attr("id"));
		}
	});
};

function initMap() {

  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 10,
    center: {lat: 49.749601, lng: 6.157497},
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

  /*
    var stompClient = null;
    var socket = new SockJS('/trafficmonitor');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        var strokeOpacity = 1.0;
        var strokeWeight = 2;

        stompClient.subscribe('/topic/alive/beacon', function(checkPoint){
            message = JSON.parse(checkPoint.body);

            var flightPlanCoordinates = [
                {lat: message['start']['latitude'], lng: message['start']['longtitude']},
                {lat: message['end']['latitude'], lng: message['end']['longtitude']}
            ];

            var flightPath = new google.maps.Polyline({
              path: flightPlanCoordinates,
              geodesic: true,
              strokeColor: '#0113B3',
              strokeOpacity: strokeOpacity,
              strokeWeight: strokeWeight
            });

            flightPath.setMap(map);
        });

        stompClient.subscribe('/topic/traffic/report/HEAVY', function(trafficReport){
            message = JSON.parse(trafficReport.body);

            var flightPlanCoordinates = [
                 {lat: message['start']['latitude'], lng: message['start']['longtitude']},
                 {lat: message['end']['latitude'], lng: message['end']['longtitude']}
             ];

             var flightPath = new google.maps.Polyline({
               path: flightPlanCoordinates,
               geodesic: true,
               strokeColor: '#FE7569',
               strokeOpacity: strokeOpacity,
               strokeWeight: strokeWeight
             });

             flightPath.setMap(map);
        });

        stompClient.subscribe('/topic/traffic/report/MODERATE', function(trafficReport){
            message = JSON.parse(trafficReport.body);

            var flightPlanCoordinates = [
                 {lat: message['start']['latitude'], lng: message['start']['longtitude']},
                 {lat: message['end']['latitude'], lng: message['end']['longtitude']}
             ];

             var flightPath = new google.maps.Polyline({
               path: flightPlanCoordinates,
               geodesic: true,
               strokeColor: '#FFFF00',
               strokeOpacity: strokeOpacity,
               strokeWeight: strokeWeight
             });

             flightPath.setMap(map);
        });

        stompClient.subscribe('/topic/traffic/report/LIGHT', function(trafficReport){
            message = JSON.parse(trafficReport.body);

            var flightPlanCoordinates = [
                 {lat: message['start']['latitude'], lng: message['start']['longtitude']},
                 {lat: message['end']['latitude'], lng: message['end']['longtitude']}
             ];

             var flightPath = new google.maps.Polyline({
               path: flightPlanCoordinates,
               geodesic: true,
               strokeColor: '#00FF2B',
               strokeOpacity: strokeOpacity,
               strokeWeight: strokeWeight
             });

             flightPath.setMap(map);
        });
    });
    */
}