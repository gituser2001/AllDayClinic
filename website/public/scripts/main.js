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

            list_div.innerHTML += "<div id='divConsulta'> <h2>" + doc.data().tipoConsulta + "</h2> <h2>Sala: " + doc.data().sala + "</div><br><br>"

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

function passwordChanged() {
  var pwd = document.getElementById("pass");
  var passconf = document.getElementById("meterConfirmPass");
  
    if (pwd.value == passconf.value) {
      document.getElementById('message').style.color = 'black';
      document.getElementById('message').innerHTML = 'Palavras-passe são iguais!';
    } else {
      document.getElementById('message').style.color = 'red';
      document.getElementById('message').innerHTML = 'Palavras-passe são diferentes!';
    }


  var strength = document.getElementById('strength');
  var strongRegex = new RegExp("^(?=.{14,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
  var mediumRegex = new RegExp("^(?=.{10,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
  var enoughRegex = new RegExp("(?=.{8,}).*", "g");
  
  if (pwd.value.length == 0) {
    strength.innerHTML = 'Escreva a Palavra-Passe';
  } else if (false == enoughRegex.test(pwd.value)) {
    strength.innerHTML = 'Mais caracteres';
  } else if (strongRegex.test(pwd.value)) {
    strength.innerHTML = '<span style="color:black">Forte!</span>';
  } else if (mediumRegex.test(pwd.value)) {
    strength.innerHTML = '<span style="color:orange">Média!</span>';
  } else {
    strength.innerHTML = '<span style="color:red">Fraca!</span>';
  }
}