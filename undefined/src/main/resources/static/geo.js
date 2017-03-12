var arrows = {};
var requestParams = "";
function drawAirQualityZones() {

	var airQualityZones = new Array();

	var AirQuality = {
		GOOD: {
			index: 6,
			color: '#79bc6a'
		},
		SATISFACTORY: {
			index: 5,
			color: '#bbcf4c'
		},
		MODERATELY_POLLUTED: {
			index: 4,
			color: '#ffcf00'
		},
		POOR: {
			index: 3,
			color: '#ff9a00'
		},
		VERY_POOR: {
			index: 2,
			color: '#ff0000'
		},
		SEVERE: {
			index: 6,
			color: '#a52a2a'
		},
		DEFAULT: {
			index: null,
			color: '#4ff72a'
		},

		getAirQuality: function (i) {
			if (AirQuality.GOOD.index == i) {
				return AirQuality.GOOD;
			} else if (AirQuality.SATISFACTORY.index == i) {
				return AirQuality.SATISFACTORY;
			} else if (AirQuality.MODERATELY_POLLUTED.index == i) {
				return AirQuality.MODERATELY_POLLUTED;
			} else if (AirQuality.POOR.index == i) {
				return AirQuality.POOR;
			} else if (AirQuality.VERY_POOR.index == i) {
				return AirQuality.VERY_POOR;
			} else if (AirQuality.SEVERE.index == i) {
				return AirQuality.SEVERE;
			} else {
				return AirQuality.DEFAULT;
			}
		}
	};

	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 10,
		center: {lat: 49.749601, lng: 6.157497},
		mapTypeId: google.maps.MapTypeId.ROADMAP
	});

	function calculateRadius(point1, point2) {
		var latPoint1 = getLat(point1);
		var longPoint1 = getLang(point1);
		var latPoint2 = getLat(point2);
		var longPoint2 = getLang(point2);
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
		return point["lat"];
	}

	function getLang(point) {
		return point["lng"];
	}

	function drawAreaCircle(point, referencePoint, color) {
		var lat = getLat(point);
		var long = getLang(point);
		// var marker = new google.maps.Marker({
		//     map: map,
		//     position: new google.maps.LatLng(lat, long),
		//     title: 'Some location'
		// });
		var circle = new google.maps.Circle({
			map: map,
			strokeColor: color,
			strokeOpacity: 0.2,
			strokeWeight: 2,
			fillOpacity: 0.35,
			center: {
				lat: lat,
				lng: long
			},
			radius: calculateRadius(point, referencePoint),    // 10 miles in metres
			fillColor: color
		});
		// circle.bindTo('center', marker, 'position');
		circle.setMap(map);
	}

    jQuery.getJSON("/airquality/zones", function (data) {
	    $.each(data, function(index) {
		    jQuery.getJSON("/airquality/details/" + data[index], function (data) {
			    airQualityZones.push(data);
			    if(index >= 7) {
				    drawAreaCircle(airQualityZones[0], airQualityZones[1], AirQuality.getAirQuality(airQualityZones[0]["index"]).color);
				    drawAreaCircle(airQualityZones[1], airQualityZones[0], AirQuality.getAirQuality(airQualityZones[1]["index"]).color);
				    drawAreaCircle(airQualityZones[2], airQualityZones[3], AirQuality.getAirQuality(airQualityZones[2]["index"]).color);
				    drawAreaCircle(airQualityZones[3], airQualityZones[2], AirQuality.getAirQuality(airQualityZones[3]["index"]).color);
				    drawAreaCircle(airQualityZones[4], airQualityZones[5], AirQuality.getAirQuality(airQualityZones[4]["index"]).color);
				    drawAreaCircle(airQualityZones[5], airQualityZones[4], AirQuality.getAirQuality(airQualityZones[5]["index"]).color);
				    drawAreaCircle(airQualityZones[6], airQualityZones[7], AirQuality.getAirQuality(airQualityZones[6]["index"]).color);
				    drawAreaCircle(airQualityZones[7], airQualityZones[6], AirQuality.getAirQuality(airQualityZones[7]["index"]).color);
                }
		    });
	    });
    });

	airQualityZones = new Array();
}

var $map;
var markers = [];

var intersectData = function () {

    jQuery.each(markers, function (key, marker) {
        marker.setMap(null);
    });
    markers = [];

	jQuery('.checkbox').each(function () {
		document.getElementById("ext_"+this.id).style.display = this.checked ? 'block' : 'none';
		var checkbox = (this.checked ? jQuery(this) : null);

		if (checkbox != null) {

			if (!requestParams) {
				requestParams = "types=" + checkbox.attr("id") + ":"+document.getElementById("distance_"+checkbox.attr("id")).value;
			} else {
				requestParams = requestParams + "&types=" + checkbox.attr("id")  + ":"+document.getElementById("distance_"+checkbox.attr("id")).value;
			}
		}
	})

    jQuery.each(markers, function (key, circle) {
        circle.setMap(null);
    });

    markers = [];

    if (requestParams) {

        jQuery.getJSON("/areas?" + requestParams, function (data) {

            jQuery.each(data, function (key, area) {

                var circledArea = {lat: area.lat, lng: area.lon};

                var cityCircle = new google.maps.Circle({
                    strokeColor: '#00FF0C',
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: '#00FF0C',
                    fillOpacity: 0.15,
                    map: $map,
                    center: circledArea,
                    radius: area.radiusMeter
                });
                markers.push(cityCircle);
            });
        });
    }
};

var aggregateData = function () {


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
        var bounds = $map.getBounds()
        var topLeftLat = bounds.f.b;
        var topLeftLon = bounds.b.b;

        var bottomRightLat = bounds.f.f;
        var bottomRightLon = bounds.b.f;
        requestParams = requestParams + "&tlLat=" + topLeftLat;
        requestParams = requestParams + "&tlLon=" + topLeftLon;
        requestParams = requestParams + "&brLat=" + bottomRightLat;
        requestParams = requestParams + "&brLon=" + bottomRightLon;

        jQuery.getJSON("/search/pois/boundedbox?" + requestParams, function (data) {

            jQuery.each(data, function (key, $poi) {

                var image = arrows[$poi.type];
                var latLong = {lat: $poi.location.lat, lng: $poi.location.lon};

                var marker = new google.maps.Marker({
                    position: latLong,
                    visible: true,
                    mapTypeId: google.maps.MapTypeId.ROADMAP,
                    icon: image
                });

                if ($poi.type == "parking") {
                    console.log($poi.id);
                    marker.addListener('click', function () {
                        jQuery.getJSON("/parking/detail/" + $poi.id, function (data) {
                            console.log(data);
                            var contentString = '<div id="content">' +
                                'Total Spaces: ' + data.total + '</br>' +
                                'Free Spaces: ' + data.free + '</br>' +
                                'Open: ' + data.meta.open + '</br>' +
                                'Elevator: ' + data.meta.elevator + '</br>' +
                                'Phone: ' + data.meta.phone + '</br>' +
                                'Disabled lots: ' + data.meta.reserved_for_disabled + '</br>' +
                                'Motorbike lots: ' + data.meta.motorbike_lots + '</br>' +
                                'Bus lots: ' + data.meta.busLots + '</br>' +
                                'Bicycle docks: ' + data.meta.bicycle_docks + '</br>' +
                                '<b>Payment methods</b></br>' +
                                'Cash: ' + data.meta.payment_methods.cash + '</br>' +
                                'VPay: ' + data.meta.payment_methods.vpay + '</br>' +
                                'VISA: ' + data.meta.payment_methods.visa + '</br>' +
                                'Mastercard: ' + data.meta.payment_methods.mastercard + '</br>' +
                                'Eurocard: ' + data.meta.payment_methods.eurocard + '</br>' +
                                'AMEX: ' + data.meta.payment_methods.amex + '</br>' +
                                'Call2Park: ' + data.meta.payment_methods.call2park + '</br>' +
                                '</div>';

                            var infowindow = new google.maps.InfoWindow({
                                content: contentString
                            });
                            infowindow.open(map, marker);
                        });
                    });
                }

                marker.setMap($map);

                markers.push(marker);
            });
        });
    }
};

function initMap() {

    var  addressMarkers = [];
    $map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        center: {lat: 49.749601, lng: 6.157497},
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    $("#search").keypress(function(e) {
        if (e.which == 13) {

            jQuery.each(addressMarkers, function (key, marker) {
                marker.setMap(null);
            });
            //$('#pTest').text('test')
            var address = $("#search").val();

            $.getJSON("/search/description/address?address="+address, function(data, status){
                if(data){
                    $('#description').html(data.description);
                    var marker = new google.maps.Marker({
                        map: $map,
                        position: new google.maps.LatLng(data.coords.lat, data.coords.lon),
                        title: 'Some location'
                    });
                    marker.setMap($map);
                    addressMarkers.push(marker);
                }else{
                    $('#description').text("address not found!");
                }
            });
        }
    });

    // FIXME: Not the best pattern - just too tired to externalize :)

    arrows['airquality'] = 'http://labs.google.com/ridefinder/images/mm_20_purple.png';
    arrows['atm'] = 'http://labs.google.com/ridefinder/images/mm_20_yellow.png';
    arrows['bank'] = 'http://labs.google.com/ridefinder/images/mm_20_blue.png';
    arrows['cinema'] = 'http://labs.google.com/ridefinder/images/mm_20_white.png';
    arrows['hospital'] = 'http://labs.google.com/ridefinder/images/mm_20_green.png';
    arrows['library'] = 'http://labs.google.com/ridefinder/images/mm_20_red.png';
    arrows['stop'] = 'http://labs.google.com/ridefinder/images/mm_20_black.png';
    arrows['park'] = 'http://labs.google.com/ridefinder/images/mm_20_orange.png';
    arrows['parking'] = 'http://labs.google.com/ridefinder/images/mm_20_gray.png';
    arrows['playgound'] = 'http://labs.google.com/ridefinder/images/mm_20_brown.png';
    arrows['school'] = 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png';
    arrows['swimming'] = 'http://www.google.com/mapfiles/marker.png';
    arrows['theatre'] = 'http://www.google.com/mapfiles/dd-start.png';
}