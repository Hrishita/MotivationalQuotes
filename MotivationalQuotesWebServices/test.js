const pool = require("./pgsql/database_access");

pool
  .connect()
  .then((client) => {
    client
      .query("select * from django_admin_log;")
      .then((result) => {
        console.log(result);
      })
      .catch((reason) => {
        console.log(reason);
      });
    client.release();
  })
  .catch((reason) => {
    console.log(reason);
  });
