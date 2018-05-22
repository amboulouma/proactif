function connexion() {
    var champLogin = $('#champ-login').val();
    var champPassword = $('#champ-password').val();

    $('#message').html('Connexion en cours...');

    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'connexionEmployee',
            login: champLogin,
            password: champPassword
        },
        dataType: 'json'
    }).done(function (data) {
        if (data.connected)
            window.location = "accueilEmploye.html";
        else{
            $('#message').html('Identifiants non valides, veuillez réessayer ...');
        }
        // si connexion pas ok afficher un texte dans la div message :
        //$('#message').html('Echec de la connexion');
    });
}

$(document).ready(function () {
    // ajout d'un "handler" sur le clic du bouton de connexion
    $('#bouton-connexion').on('click', function () {
        // affichage pour debugage dans la console javascript du navigateur
        console.log('Click sur le bouton "Se Connecter"');
        // appel de la fonction connexion
        connexion();
    });
});