createObject = (err,data)=>{

    const result = {}

    if(err)
    {
        result["status"] = "error"
        result["data"] = err
    }

    else if(data.length == 0)
    {
        result["status"] = "error"
        result["data"] = data
    }
    else{
        result["status"] = "success"
        result["data"] = data

    }

    return result;





}

module.exports = {createObject};