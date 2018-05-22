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
    });
}


function inscription() {
    var champNom = $('#champ-nom').val();
    var champPrenom = $('#champ-prenom').val();
    var champCivilite = $('#champ-civilite').val();
    var champAdresse = $('#champ-adresse').val();
    var champTel = $('#champ-tel').val();
    var champDate = $('#champ-date').val();
    var champEmail = $('#champ-email').val();
    var champPassword = $('#champ-password').val();
    var champCPassword = $('#champ-cpassword').val();
    $('#message').html('Inscription en cours...');
    $.ajax({
        url: './ActionServlet',
        method: 'POST',
        data: {
            todo: 'inscriptionClient',
            nom: champNom,
            prenom: champPrenom,
            civilite: champCivilite,
            adresse: champAdresse,
            tel: champTel,
            date: champDate,
            email: champEmail,
            password: champPassword,
            cpassword: champCPassword
        },
        dataType: 'json'
    }).done(function (data) {
        console.log(data);
        if (data != null)
            window.location = "connexionClient.html";
        else{
            $('#message').html('Saisie non valide, veuillez réessayer ...');
        }
    });
}

$(document).ready(function () {
    $('#bouton-inscription').on('click', function () {
        console.log('Click sur le bouton "S\'enregistrer"');
        inscription();
    });
    
    
    $('#bouton-connexion').on('click', function () {
        console.log('Click sur le bouton "Se Connecter"');
        connexion();
    });
});