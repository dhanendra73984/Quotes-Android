const express = require('express');
const router = express.Router();
const connect = require('../db/dbconnect')
const util = require('../utils/util')


router.post("/login",(req,resp)=>{

    const {email,password} = req.body;


    connect.query("select * from user_tbl where email = ? and password = ?",[email,password],(err,data)=>{

        if(!err)
        {
            resp.send(util.createObject(err,data));
        }


    })



})

router.post("/register",(req,resp)=>{

    const{first_name,last_name,email,password,mobile} = req.body;
    q = "insert into user_tbl (first_name,last_name,email,password,mobile) values(?,?,?,?,?)"

    connect.query(q,[first_name,last_name,email,password,mobile],(err,data)=>{

        if(!err)
        {
            resp.send(util.createObject(err,data));
        }




    })



})

router.get("/getuserbyid/:id",(req,resp)=>{


    q =  "select * from user_tbl where user_id = ?"

    connect.query(q,[req.params.id],(err,data)=>{



        if(!err)
        {
            resp.send(util.createObject(err,data));
        }




    })

})

router.put("/edit/:id",(req,resp)=>{


    const {first_name,last_name,email,password,mobile} = req.body;
    q = "UPDATE user_tbl set first_name = ?, last_name = ?, email = ?, password = ?, mobile = ? where user_id =?;"


    connect.query(q,[first_name,last_name,email,password,mobile,req.params.id],(err,data)=>{


        resp.send(util.createObject(err,data));

    })


})



module.exports=router;