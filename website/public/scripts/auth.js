firebase.auth().onAuthStateChanged(function(user) {
    if(user){
        document.title="Perfil";

        var user = firebase.auth().currentUser;

        if (user != null){
            var email_id = user.email;
        }
})


function login(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("pass").value;

    /* firebase.auth().signInWithEmailAndPassword(email, password)
    .then((user) => {
        // Signed in
        // ...
        window.alert("Funciona!")
    })
    .catch((error) => {
        var errorCode = error.code;
        var errorMessage = error.message;

        console.log(error);
    }); */

    firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error){
        console.log(error);
    })

}


function logout(){
    firebase.auth().signOut()
} 