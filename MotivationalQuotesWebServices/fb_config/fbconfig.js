var admin = require("firebase-admin");

var serviceAccount = require("../firebase_credentials/motivationalquotes-1c781-firebase-adminsdk-eugpn-c498a2118c.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://motivationalquotes-1c781.firebaseio.com",
});

const db = admin.firestore();

module.exports = {
  db: db,
};
