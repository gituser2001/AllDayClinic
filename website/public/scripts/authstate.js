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
