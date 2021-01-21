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

    

    /*
    //este é o que funciona

    const UsersRef = db.collection("users").doc(firebase.auth().currentUser.uid);

    UsersRef.get().then((doc) => {
        if (!doc.exists) return;
        console.log("Document data:", doc.data());
        // Document data: { title: 'The Great Gatsby' }
        });

*/
    /*
    //este nao sei
    UsersRef.get()
    .then((doc) => {
            var dtaNasc = doc.data().dtaNasc;
            var nome = doc.data().nome;
            var uid = firebase.auth().currentUser.uid;

            document.getElementById("dtaNasc").innerText = dtaNasc;   
            document.getElementById("nome").innerText = nome;
            document.getElementById("uid").innerText = uid;
        })
    .catch(err => {
        console.log('Error getting documents', err);
    }); */

    firebase.auth().onAuthStateChanged(function(user) {
        if (user) {
          // User is signed in.
          var db = firebase.firestore();
            var docRef = db.collection("users").doc(user.uid);
            docRef.get().then(function(doc) {
               if(doc && doc.exists) {
               const myData = doc.data();
               const dtaNasc = myData.dtaNasc;
               const nome = myData.nome;
               const uid = myData.uid;
               document.getElementById("dtaNasc").innerText = dtaNasc;
               document.getElementById("nome").innerText = nome;
               document.getElementById("uid").innerText = uid;
      
          }
          })
          .catch(function(error) {
          console.log("Got an error: ",error);
          });
        } else {
          // No user is signed in.
        }
      });
}

function feedBom() {
  var divbom = document.getElementById("divBom").style;
  var divmau = document.getElementById("divMau").style;

  divbom.display = "block";
  divmau.display = "none";
	
}

function feedMau() {
  var divbom = document.getElementById("divBom").style;
  var divmau = document.getElementById("divMau").style;

  divmau.display = "block";
  divbom.display = "none";
	
}