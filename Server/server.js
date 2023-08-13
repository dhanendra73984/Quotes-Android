const express = require('express');
const app = express();
const cors = require('cors')
const port = 4004;
const QuoteRouter = require('./router/QuoteRouter')
const UserRouter = require('./router/UserRouter')



app.use(cors("*"))
app.use(express.json())

app.use("/quotes",QuoteRouter)
app.use("/user",UserRouter)

app.listen(port,'0.0.0.0',()=>{


    console.log("started on "+"http://127.0.0.1:4004/quotes/")
})