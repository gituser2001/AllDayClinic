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

function lerConsultas(){
    var db = firebase.firestore();

    db.collection("Consulta").where("idUser", "==", firebase.auth().currentUser.uid)
    .get()
    .then(function(querySnapshot) {
        querySnapshot.forEach(function(doc) {
            // doc.data() is never undefined for query doc snapshots
            console.log(doc.id, " => ", doc.data());
        });
    })
    .catch(function(error) {
        console.log("Error getting documents: ", error);
    });

    window.alert("Esta a dar esta funcao");
    }

function lerPerfil(){
    var db = firebase.firestore()

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
          document.location.replace("auth/login.html")
        }
      });
}


