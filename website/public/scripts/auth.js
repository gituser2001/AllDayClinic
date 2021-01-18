firebase.auth().onAuthStateChanged((user) => {
    if (user) {
      // User is signed in, see docs for a list of available properties
      // https://firebase.google.com/docs/reference/js/firebase.User
      var uid = user.uid;
      //É direcionado para a Página Inicial
      document.location.replace("../inicial.html")
      // ...
    } else {
      // User is signed out
      // ...
      document.location.replace("../login.html")

    }
  });



function login(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("pass").value;

     firebase.auth().signInWithEmailAndPassword(email, password)
    .then((user) => {
        // Signed in
        // Abre a Página Inicial
        document.location.replace("../inicial.html")
    })
    .catch((error) => {
        var errorCode = error.code;
        var errorMessage = error.message;

        // Aparece no ecrã que ocorreu um erro

        window.alert("Email ou palavra-passe incorretos.");

        console.log(error);
    }); 

    firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error){
        console.log(error);
    })

}

function registo(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("pass").value;

    var nomeUser = document.getElementById("meterNome").value;
    var dataNasc = document.getElementById("meterAno").value;

    var db = firebase.firestore()

    firebase.auth().createUserWithEmailAndPassword(email, password)
    .then((user) => {
      // Signed in
      var userid = user.uid;
      
      db.collection("users").doc(userid).set({
        dtaNasc: dataNasc,
        nome: nomeUser,
        uid: userid
        })
        .then(function() {
            window.alert("Utilizador criado com sucesso");

            // Abre a Página Login
            document.location='login.html'
        })
        .catch(function(error) {
            window.alert("Dados não introduzidos no Firestore");
        });
    })
    .catch((error) => {
      var errorCode = error.code;
      var errorMessage = error.message;
      // Aparece no ecrã que ocorreu um erro ao criar a conta

      window.alert("Ocorreu um erro ao criar a conta. Verifique se as informações estão corretas.");
      console.log(error);
    });

}

function logout(){
    firebase.auth().signOut().then(function() {
        // Sign-out successful.
        document.location='auth/login.html'

      }).catch(function(error) {
        // An error happened.
        window.alert("Ocorreu um erro a terminar a sessão.");
      });
}