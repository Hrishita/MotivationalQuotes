const {
  getDataFromIndexAndCategory,
  storeInDatabase,
} = require("./pgsql/database_CRUD");
const express = require("express");
const { response } = require("express");
const app = express();
const port = process.env.PORT || 8000;

app.get("/api/getCount/", (req, res) => {});

app.get("/api/getImages/:beg/:category", (req, res) => {
  let beginning = req.params.beg;
  beginning = parseInt(beginning);
  const category = req.params.category;
  getTextFromIndexAndCategory(beginning, category)
    .then((text) => {
      const jsonString = `{
        "text":${text}
      }`;
      console.log(text);
      res.json({
        text: text,
      });
    })
    .catch((reason) => {
      res.json("failed");
    });
});

// app.get("/api/uploadQuote/:text/:category", (req, res) => {
//   const text = req.params.text;
//   const category = req.params.category;
//   uploadQuote(text, category)
//     .then((value) => {
//       res.json({
//         response: "success!",
//       });
//     })
//     .catch((reason) => {
//       res.json({
//         response: "failed to upload",
//       });
//     });
// });

app.get(
  "/api/getQuoteFromCategory/:offset/:no_of_rows/:category",
  (req, res) => {
    const offset = req.params.offset;
    const no_of_rows = req.params.no_of_rows;
    const category = req.params.category;
    getDataFromIndexAndCategory(offset, no_of_rows, category)
      .then((result) => {
        console.log(result.rows[0]);
        res.json({
          response: result.rows,
          rowCount: result.rowCount + "",
          reason: "",
        });
      })
      .catch((reason) => {
        res.json({
          response: "",
          rowsCount: 0,
          reason: reason,
        });
      });
  }
);

app.uploadQuote("/api/uploadQuote/:c_id/:q_text/:a_id", (req, res) => {
  const c_id = req.params.c_id;
  const q_text = req.params.q_text;
  const a_id = req.params.a_id;
  storeInDatabase(c_id, q_text, a_id)
    .then((response) => {
      res.json({
        response: response,
      });
    })
    .catch((reason) => {
      res.json({
        response: reason,
      });
    });
});

app.listen(port, () => {
  console.log("server started");
});
