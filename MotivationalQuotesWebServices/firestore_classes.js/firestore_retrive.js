const db = require("../fb_config/fbconfig").db;
const cached_text = require("../cached_text_classes/cached_text").quick;
const counterRef = db.collection("counters");
const motivationalQuotesRef = db.collection("motivationalquotes");

function getTextFromIndexAndCategory(index, category) {
  return new Promise((resolve, reject) => {
    let res = cached_text.getItemAtPositionAndCategory(index, category);
    if (res) {
      resolve(res);
    } else {
      //fetch from firestore
      motivationalQuotesRef
        .where("category", "==", category)
        .where("uid", "==", index)
        .get()
        .then((snapshot) => {
          if (snapshot.empty) {
            reject("no data found");
          } else {
            const quote = snapshot.docs[0].data().text;
            cached_text.addItemAtPosition(index, category, quote);
            resolve(quote);
          }
        })
        .catch((reason) => {
          reject(reason);
        });
    }
  });
}

function uploadQuote(text, cat) {
  return new Promise((resolve, reject) => {
    let currVal, nextVal, newNextVal;
    counterRef
      .doc("quotescounter")
      .get()
      .then((value) => {
        currVal = value.data().currentval;
        nextVal = value.data().nextval;
        newNextVal = parseInt(nextVal) + 1;
        motivationalQuotesRef
          .doc(newNextVal + "")
          .set({
            text: text,
            category: cat,
          })
          .then((value) => {
            counterRef
              .doc("quotescounter")
              .update({
                currentval: nextVal,
                nextval: newNextVal,
              })
              .then((value) => {
                resolve("Success");
              })
              .catch((reason) => {
                console.log(reason);
              });
          });
      })
      .catch((reason) => {
        reject("Failed");
      });
  });
}

module.exports = {
  getTextFromIndexAndCategory: getTextFromIndexAndCategory,
  uploadQuote: uploadQuote,
};
