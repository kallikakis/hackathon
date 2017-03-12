
function drawAirQualityZones() {

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        center: {lat: 49.749601, lng: 6.157497},
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    //TODO: Mocked data
    var point1 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                5.976941,
                49.505011
            ]
        },
        properties: {
            id: "aev:Esch-Alzette",
            name: "Esch-Alzette",
            temp: 8.7,
            pm10: 24,
            no2: 64,
            o3: 10,
            so2: 1.5,
            co: 0.3,
            last_update: "1489269600000"
        }
    }

    //TODO: Mocked data
    var point2 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                6.305332,
                49.722229
            ]
        },
        properties: {
            id: "aev:Beidweiler",
            name: "Beidweiler",
            temp: 7.1,
            pm10: 16,
            no2: 16.9,
            o3: 47,
            so2: 1.6,
            co: null,
            last_update: "1489269600000"
        }
    }

    function calculateRadius(point1, point2) {
        var latPoint1 = point1["geometry"]["coordinates"][1];
        var longPoint1 = point1["geometry"]["coordinates"][0];
        var latPoint2 = point2["geometry"]["coordinates"][1];
        var longPoint2 = point2["geometry"]["coordinates"][0];
        var R = 6371; // km
        var dLat = calculateDistance(latPoint1, latPoint2);
        var dLong = calculateDistance(longPoint1, longPoint2);
        var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(latPoint1 * Math.PI / 180) * Math.cos(latPoint2 * Math.PI / 180) *
            Math.sin(dLong / 2) * Math.sin(dLong / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c / 2 * 1000;
    }

    function calculateDistance(v1, v2) {
        return (v2 - v1) * Math.PI / 180;
    }

    function getLat(point) {
        return point["geometry"]["coordinates"][1];
    }

    function getLang(point) {
        return point["geometry"]["coordinates"][0];
    }

    function drawAreaCircle(point, referencePoint, color) {
        var lat = getLat(point);
        var long = getLang(point);
        var marker = new google.maps.Marker({
            map: map,
            position: new google.maps.LatLng(lat, long),
            title: 'Some location'
    });
        var circle = new google.maps.Circle({
            map: map,
            radius: calculateRadius(point, referencePoint),    // 10 miles in metres
            fillColor: color
        });
        circle.bindTo('center', marker, 'position');
        circle.setMap(map);
    }

    drawAreaCircle(point1, point2, '#AA0000');
    drawAreaCircle(point2, point1, '#33aa35');
}

var $map;
var markers = [];

var onchangeEvent = function() {
	var requestParams = "";

	jQuery.each(markers, function (key, marker) {
		marker.setMap(null);
	});
	markers = [];

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

	if (requestParams) {

			jQuery.getJSON("/search/pois?" + requestParams, function (data) {

			jQuery.each(data, function (key, $poi) {

				var image = 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png';
				var latLong = {lat: $poi.location.lat, lng: $poi.location.lon};

				var marker = new google.maps.Marker({
					position: latLong,
					visible: true,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					icon: image
				});

				marker.setMap($map);

				markers.push(marker);
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
}