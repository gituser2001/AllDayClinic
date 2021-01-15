import firebase from 'firebase'
// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional

try{
var firebaseConfig = {
  apiKey: "AIzaSyB7E4l6Uld9gNRKcOgauiYAT9bkAU5W_y8",
  authDomain: "alldayclinic-15a43.firebaseapp.com",
  databaseURL: "https://alldayclinic-15a43.firebaseio.com",
  projectId: "alldayclinic-15a43",
  storageBucket: "alldayclinic-15a43.appspot.com",
  messagingSenderId: "1019067873830",
  appId: "1:1019067873830:web:904c8b3567de44f88cc4e7",
  measurementId: "G-M4PBYG679L"}

  // Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();

}
catch (err) {
  // we skip the "already exists" message which is
  // not an actual error when we're hot-reloading
  if (!/already exists/.test(err.message)) {
  console.error('Firebase initialization error', err.stack)
  }
  }
const fb= firebase
export default fb