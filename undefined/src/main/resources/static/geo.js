var $map;

var onchangeEvent = function() {
	var requestParams = "";

	jQuery('.checkbox').each(function () {
		var checkbox = (this.checked ? jQuery(this) : null);

		if (checkbox != null) {

			if (!requestParams) {
				requestParams = "filters=" + checkbox.attr("id");
			} else {
				requestParams = requestParams + "&filters=" + checkbox.attr("id");
			}
		}
	})

	/*
	 id:"200403022"
	 location:
	 	lat:6.115973
	 	lon:49.613246
	 name:"Luxembourg, Square New York"
	 type:"stop"
	 */


	if (requestParams) {

			jQuery.getJSON("/search/pois?" + requestParams, function (data) {

			jQuery.each(data, function (key, $poi) {

				var image = 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png';
				var latLong = {lat: $poi.location.lat, lng: $poi.location.lon};
				console.log(latLong);

				var marker = new google.maps.Marker({
					position: latLong,
					visible: true,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					icon: image
				});
				/* */

				marker.setMap($map);
			});
		});
	}
};

function initMap() {

  $map = new google.maps.Map(document.getElementById('map'), {
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