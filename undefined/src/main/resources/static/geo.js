function drawAirQualityZones() {

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
    //TODO: Mocked data
    var point1 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                49.943917,
                6.175912
            ]
        },
        properties: {
            id: "aev:Vianden",
            name: "Vianden",
            temp: 8.7,
            pm10: 24,
            no2: 64,
            o3: 10,
            so2: 1.5,
            co: 0.3,
            index: 6,
            last_update: "1489269600000"
        }
    }

    //TODO: Mocked data
    var point2 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                49.605276,
                6.139168
            ]
        },
        properties: {
            id: "aev:Station-meteo",
            name: "Station-meteo",
            temp: 7.1,
            pm10: 16,
            no2: 16.9,
            o3: 47,
            so2: 1.6,
            co: null,
            index: 3,
            last_update: "1489269600000"
        }
    }

    //TODO: Mocked data
    var point3 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                49.505011,
                5.976941
            ]
        },
        properties: {
            id: "aev:Esch-Alzette",
            name: "Esch-Alzette",
            temp: 7.9,
            pm10: 25,
            no2: 19,
            o3: 62,
            so2: 1.6,
            co: null,
            index: 4,
            last_update: "-62167222800000"
        }
    }

    //TODO: Mocked data
    var point4 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                49.722229,
                6.305332
            ]
        },
        properties: {
            id: "aev:Beidweiler",
            name: "Beidweiler",
            temp: 7.9,
            pm10: 25,
            no2: 19,
            o3: 62,
            so2: 1.6,
            co: null,
            index: 3,
            last_update: "-62167222800000"
        }
    }

    //TODO: Mocked data
    var point5 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                49.731825,
                5.847137
            ]
        },
        properties: {
            id: "aev:Beckerich",
            name: "Beckerich",
            temp: 7.9,
            pm10: 25,
            no2: 19,
            o3: 62,
            so2: 1.6,
            co: null,
            index: 5,
            last_update: "-62167222800000"
        }
    }

    //TODO: Mocked data
    var point6 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                49.611528,
                6.118472
            ]
        },
        properties: {
            id: "aev:Lux-Churchill",
            name: "Lux-Churchill",
            temp: 7.9,
            pm10: 25,
            no2: 19,
            o3: 62,
            so2: 1.6,
            co: null,
            index: 2,
            last_update: "-62167222800000"
        }
    }


    //TODO: Mocked data
    var point7 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                49.597692,
                6.137603
            ]
        },
        properties: {
            id: "aev:Lux-Bonnevoie",
            name: "Lux-Bonnevoie",
            temp: 7.9,
            pm10: 25,
            no2: 19,
            o3: 62,
            so2: 1.6,
            co: null,
            index: 4,
            last_update: "-62167222800000"
        }
    }

    //TODO: Mocked data
    var point8 = {
        type: "Feature",
        geometry: {
            type: "Point",
            coordinates: [
                49.606917,
                6.128028
            ]
        },
        properties: {
            id: "aev:Lux-Liberte",
            name: "Lux-Liberte",
            temp: 7.9,
            pm10: 25,
            no2: 19,
            o3: 62,
            so2: 1.6,
            co: null,
            index: 5,
            last_update: "-62167222800000"
        }
    }

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
        return point["geometry"]["coordinates"][0];
    }

    function getLang(point) {
        return point["geometry"]["coordinates"][1];
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

    drawAreaCircle(point1, point2, AirQuality.getAirQuality(point1["properties"]["index"]).color);
    drawAreaCircle(point2, point1, AirQuality.getAirQuality(point2["properties"]["index"]).color);
    drawAreaCircle(point3, point4, AirQuality.getAirQuality(point3["properties"]["index"]).color);
    drawAreaCircle(point4, point3, AirQuality.getAirQuality(point4["properties"]["index"]).color);
    drawAreaCircle(point5, point6, AirQuality.getAirQuality(point5["properties"]["index"]).color);
    drawAreaCircle(point6, point5, AirQuality.getAirQuality(point6["properties"]["index"]).color);
    drawAreaCircle(point7, point8, AirQuality.getAirQuality(point7["properties"]["index"]).color);
    drawAreaCircle(point8, point7, AirQuality.getAirQuality(point8["properties"]["index"]).color)

}

var $map;
var markers = [];

var onchangeEvent = function () {
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