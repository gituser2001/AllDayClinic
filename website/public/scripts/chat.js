class Mensagem {
  constructor(text, fromId, toId, timeStamp){
  this.text = text;
  var fromId = fromId;
  var toId = toId;
  var timeStamp = timeStamp;}
}

var outroid;



const list_div = document.querySelector("#divListaChat");

var nome;

function lerDMS(){
  
    firebase.auth().onAuthStateChanged(function(user) {
      if (user) {
        // User is signed in.
        var db = firebase.firestore(); 
        db.collection("latest_messages").doc(user.uid).collection("latest_message").orderBy("timeStamp", "desc")
        .get()
        .then(querySnapshot => {
            querySnapshot.forEach(doc => { 
              console.log(doc.id, " => ", doc.data());

              outroid = doc.id;

              mensagem = doc.data().text;

              getOutroUser(doc.id, mensagem);
              
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

function getOutroUser(outroid, msg){

  var db = firebase.firestore();


  var docRef = db.collection("users").doc(outroid);

  docRef.get().then(function(doc) {
    if (doc.exists) {
      
        console.log(doc.data().nome)

        nome= doc.data().nome;

        list_div.innerHTML += "<div id='pessoasChat' onclick='conversa()'><p id='pessoaChatTitulo'>" + nome + "</p><p id='pessoaChatDM'>" + msg + "</p></div>"
      
      return nome;

    } else {
        // doc.data() will be undefined in this case
        console.log("No such document!");
    }
  }).catch(function(error) {
    console.log("Error getting document:", error);

    return nome

  }); 
  
}


function conversa(){

    var db = firebase.firestore();
    firebase.auth().onAuthStateChanged(function(user) {
      if (user) {

        db.collection("user_messages").doc(user.uid).collection(outroid).orderBy("timeStamp")
        .get()
        .then(function(querySnapshot) {
            querySnapshot.forEach(function(doc) {

                
                // doc.data() is never undefined for query doc snapshots
                const escritaDM = document.querySelector("#conversa");
                console.log(doc.id, " => ", doc.data());

                dmrecebe = doc.data().fromId;
                dmenvia = doc.data().toId;
                mensag = doc.data().text

                
                escritaDM.innerHTML += "<div id='dmEsq'><p id='dm'>" + mensag + "</p></div>";
                document.getElementById("tituloDir").innerText = nome;
                
                
            });
        })
        .catch(function(error) {
            console.log("Error getting documents: ", error);
        });
      }else {
        // doc.data() will be undefined in this case
        console.log("No such document!");
    }
  }).catch(function(error) {
    console.log("Error getting document:", error);

  }); 

}