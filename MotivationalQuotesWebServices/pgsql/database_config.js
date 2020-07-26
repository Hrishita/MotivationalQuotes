const { Pool } = require("pg");
let pool;
if (process.env.PORT) {
  pool = new Pool({
    connectionString:
      "postgres://hzrrhbzzdhtfat:4c760f4eb836cfebafa41268be68b5730876d355036fe60821819eefba00bc89@ec2-35-172-73-125.compute-1.amazonaws.com:5432/deddu3klprhvsm",
  });
} else {
  pool = new Pool({
    user: "devarsh",
    password: "jaygopal",
    database: "motivationalquotes",
    host: "127.0.0.1",
    port: 5432,
  });
}

module.exports = pool;
