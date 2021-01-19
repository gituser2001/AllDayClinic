


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
    var userid = firebase.auth().currentUser.uid;

    firebase.auth().createUserWithEmailAndPassword(email, password)
    .then((user) => {
      // Signed in

        db.collection("users").doc(firebase.auth().currentUser.uid).set({
            dtaNasc: dataNasc,
            nome: nomeUser,
            uid: firebase.auth().currentUser.uid
        })
        .then(function() {
            document.location.replace("login.html")
        })
        .catch(function(error) {
            console.error("Erro a registar no Firestore: ", error);
    });
      
    })
    .catch((error) => {
      var errorCode = error.code;
      var errorMessage = error.message;
      // Aparece no ecrã que ocorreu um erro ao criar a conta

      console.log("Não foi possível criar a conta.");
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

function lerDados(){
    var db = firebase.firestore()
    var userid = firebase.auth().currentUser.uid;

    var docRef = db.collection("users").doc(userid);

    docRef.get().then(function(doc) {
        if (doc.exists) {
            console.log("Document data:", doc.data());
        } else {
            // doc.data() will be undefined in this case
            console.log("No such document!");
        }
    }).catch(function(error) {
        console.log("Error getting document:", error);
});

}