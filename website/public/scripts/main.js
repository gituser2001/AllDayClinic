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

    const list_div = document.querySelector("#divListaConsultas");

    firebase.auth().onAuthStateChanged(function(user) {
      if (user) {
        // User is signed in.
        db.collection("Consulta").where("idUser", "==", firebase.auth().currentUser.uid)
        .get()
        .then(function(querySnapshot) {
        querySnapshot.forEach(function(doc) {
            // doc.data() is never undefined for query doc snapshots

            list_div.innerHTML += "<div id='divConsulta'> <h2>" + doc.data().tipoConsulta + "</h2> <h2>" + doc.data().Data; + "</div><br><br>"

            console.log(doc.id, " => ", doc.data());

          
        });
    })
    .catch(function(error) {
        console.log("Error getting documents: ", error);
    });
    
      } else {
        // No user is signed in.
        document.location.replace("auth/login.html")
      }
    });
}

function lerPerfil(){
    var db = firebase.firestore();

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

function lerDMS(){

  const list_div = document.querySelector("#divListaChat");
  var fromId;
  var html = "";

  firebase.auth().onAuthStateChanged(function(user) {
    if (user) {
      // User is signed in.
      var db = firebase.firestore(); 
      db.collection("latest_messages").doc(user.uid).collection("latest_message").get()
      .then(querySnapshot => {
          querySnapshot.forEach(doc => {

            fromId = doc.data().fromId;

            console.log(doc.id, " => ", doc.data());

            list_div.innerHTML += "<div id='pessoasChat'><p id='pessoaChatTitulo'>" + doc.id + "</p><p id='pessoaChatDM'>" + doc.data().text + "</p></div>";
          });
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

//