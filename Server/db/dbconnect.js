const mysql = require('mysql')

const connection = mysql.createConnection({

    host : '127.0.0.1',
    user : 'root',
    password : 'manager',
    database : 'quotes',
    port: 3306
})

connection.connect((err)=>{

    if(!err)
    {
        console.log("connection with DB established successfully");
    }


})

module.exports=connection;