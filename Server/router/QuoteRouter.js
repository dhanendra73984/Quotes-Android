const express = require('express');
const router = express.Router();
const connect = require('../db/dbconnect')
const util = require('../utils/util')

router.get("/all",(req,resp)=>{

    
    connect.query("select * from quotes_tbl",(err,data)=>{

            if(!err)
            {
                resp.send(util.createObject(err,data));
            }
            



    })


})

router.get("/byuserid/:id",(req,resp)=>{


    connect.query("select * from quotes_tbl where user_id=?",[req.params.id],(err,data)=>{

            if(!err)
            {
                resp.send(util.createObject(err,data));
            }

    })


})


router.post("/addquote/:id",(req,resp)=>{


    const {text,author} = req.body;


    q = "insert into quotes_tbl(text,author,user_id) values(?,?,?)";

    connect.query(q,[text,author,req.params.id],(err,data)=>{

        
            if(!err)
            {
                    resp.send(util.createObject(err,data));
            }


    })



})

router.post("/favquotes",(req,resp)=>{

     const {user_id,q_id} = req.body;

    // connect.query("insert into fav_quotes(user_id,q_id) values(?,?)",[user_id,q_id],(err,data)=>{


    //         resp.send(util.createObject(err,data));



    // })

    connect.query("select * from fav_quotes ",(err,data)=>{


            const obj = JSON.stringify(data)

            

            for(var ob of data)
            {
                if(ob.user_id === user_id && ob.q_id === q_id)
                {

                   var update = "t";
                    f_id = ob.f_id;
                    console.log("ture");
                  
                    
                }
               
            }
            
                if(update === "t")
                {
                   
                    connect.query("update fav_quotes set user_id = ?, q_id = ? where f_id =?",[user_id,q_id,f_id],(err,data)=>{

                        resp.send(util.createObject(err,data));


                    })
                }else{

                connect.query("insert into fav_quotes(user_id,q_id) values(?,?)",[user_id,q_id],(err,data)=>{


                    resp.send(util.createObject(err,data));
        
        
        
            })
        }

         




    })



})

router.delete("/delbyquote/:id",(req,resp)=>{

    connect.query("delete from fav_quotes where q_id = ?",[req.params.id],(err,data)=>{


            resp.send(util.createObject(err,data));



    })




})


router.get("/favquotes/:id",(req,resp)=>{


    q = "select qt.q_id,qt.text,qt.author,qt.user_id from quotes_tbl qt join fav_quotes fq on qt.q_id = fq.q_id where fq.user_id = ?";


    connect.query(q,[req.params.id],(err,data)=>{

            resp.send(util.createObject(err,data))



    })




})

router.put("/editquote",(req,resp)=>{

    const {text,author,q_id} = req.body

    q = "UPDATE quotes_tbl set text = ?, author = ? where q_id = ?";

    connect.query(q,[text,author,q_id],(err,data)=>{



        resp.send(util.createObject(err,data));
    })




})

router.delete("/delquote/:id",(req,resp)=>{

   

    q = "delete from quotes_tbl where q_id = ?";


    connect.query(q,[req.params.id],(err,data)=>{



        resp.send(util.createObject(err,data));
    })




})






module.exports=router;