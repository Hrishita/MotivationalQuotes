const pool = require("./database_config");
const { NO_ROW_FOUND, DATABASE_ERROR } = require("../CONSTANTS");

function getDataFromIndexAndCategory(offset, no_of_rows, category) {
  return new Promise((resolve, reject) => {
    pool.connect().then((client) => {
      client
        .query(
          `select quotes.q_text, author.a_name from quotes, category, author where quotes.c_id = category.c_id AND quotes.a_id = author.a_id AND category.c_name = '${category}' order by quotes.q_id DESC offset ${offset} ROWS FETCH FIRST ${no_of_rows} ROW ONLY`
        )
        .then((result) => {
          if (result.rowCount != 0) {
            client.release();
            resolve({
              rowCount: result.rowCount,
              rows: result.rows,
            });
          } else {
            client.release();
            reject(NO_ROW_FOUND);
          }
        })
        .catch((reason) => {
          client.release();
          reject(DATABASE_ERROR + "\n" + reason);
        });
    });
  });
}

function storeInDatabase(c_id, q_text, a_id) {
  return new Promise((resolve, reject) => {
    pool.connect().then((client) => {
      client
        .query(
          `insert into quotes (c_id, q_text, a_id) values(${c_id}, '${q_text}', ${a_id});`
        )
        .then((result) => {
          client.release();
          resolve("Success");
        })
        .catch((reason) => {
          client.release();
          reject("Failure");
        });
    });
  });
}

module.exports = {
  getDataFromIndexAndCategory: getDataFromIndexAndCategory,
  storeInDatabase: storeInDatabase,
};
