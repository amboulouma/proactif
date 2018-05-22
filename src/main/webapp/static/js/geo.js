var googleMapInstance = null;

function makeInfoWindow(title) {
    return new google.maps.InfoWindow({
        content: '<div>Information: ' + title + '</div>'
    });
}

function attachInfoWindow(marker, infowindow, htmlDescription) {
    marker.addListener('click', function () {

        infowindow.setContent(htmlDescription);
        infowindow.open(googleMapInstance, this);
    });
}

function initMap() {

    googleMapInstance = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 45.7601424, lng: 4.8961779},
        zoom: 13
    });

    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'tableauDeBordEmploye',
        },
        dataType: 'json'
    }).done(function (data) {
        generateMarkers(data, googleMapInstance)
    });
}

function generateMarkers(data, googleMapInstance) {

    var infowindow = makeInfoWindow('');

    var geocoder = new google.maps.Geocoder();

    var iter = 0;

    for (iter = 0; iter < data.length; iter++) {

        geocoder.geocode({'address': data[iter].client.address}, function(results, status) {
            if (status === 'OK') {
                iter ++;
                var marker = new google.maps.Marker({
                map: googleMapInstance,
                position: results[0].geometry.location,
                title: 'Intervention #'+(iter-data.length)

              });
              alert(data[iter-(data.length+1)]);
              var type;
              var solved = false;
              var state;
              var fin = '';
              if (data[iter-(data.length+1)].pet == undefined && data[iter-(data.length+1)].object == undefined)
                {
                    type = 'Incident';
                }
                else if (data[iter-(data.length+1)].pet == undefined)
                {
                    type = 'Delivery';
                }
                else
                {
                    type = 'Pet';
                }
                if (data[iter-(data.length+1)].resolutionDate == undefined)
                {
                    solved = false;
                }
                else
                {
                    solved = true;
                }
                if (solved == true)
                {
                    state = 'Resolue';
                    fin = 'Date et heure de fin : ' + data[iter-(data.length+1)].resolutionDate + '<br/>';
                }
                else
                {
                    state = 'En cours ...';
                }
                attachInfoWindow(
                marker, infowindow,
                '<div><strong><a href="./endroit.html?' + (iter-data.length) + '">Intervention #' + (iter-data.length) +
                '</a></strong><br/>Lieu de l\'intervention numéro ' + (iter-data.length) + '<br/>' +
                'Type : ' + type + '<br/>' +'Description : ' + data[iter-(data.length+1)].description + '<br/>' +
                'Client : ' + data[iter-(data.length+1)].client.firstName + ' ' + data[iter-(data.length+1)].client.name + '<br/>' +
                'Adresse : ' + data[iter-(data.length+1)].client.address + '<br/>' +
                'Date et heure de la demande : ' + data[iter-(data.length+1)].creationDate + '<br/>' +
                fin +
                'Etat : '+ state + '</div>'
                );
            } else {
              alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    }
}

function GenerateCoordinates(address){
    geocoder.geocode({'address': address}, function(results, status) {
        if (status === 'OK') {
            return results[0].geometry.location;
        } else {
          alert('Geocode was not successful for the following reason: ' + status);
        }
      });

}

function deconnexionEmployee() {
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'deconnexionEmployee'
        },
        dataType: 'json'
    }).done(function (data) {
        window.location = "connexionEmploye.html";
    });
}

$(document).ready(function () {
    $('#bouton-deconnexion-employee').on('click', function () {
        deconnexionEmployee();
    });
});