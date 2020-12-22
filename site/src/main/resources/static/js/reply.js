
var replyManager = (function () {
    var getAll = function (obj, callback) {
        console.log("get All....");
        $.getJSON('/replies/'+obj,callback);
    };

    var add = function (obj,callback) {
        console.log("add....");

        $.ajax({
           type:'post',
           utl:'/replies/'+obj.bno,
           data:JSON.stringify(obj),
           dataType:'json',
           contentType:"application/json" ,
            success:callback
        });
    };

    var update = function (obj, callback) {
        console.log("update....");

        $.ajax({
            type:'put',
            utl:'/replies/'+obj.bno,
            data:JSON.stringify(obj),
            dataType:'json',
            contentType:"application/json" ,
            success:callback
        });
    };

    var remove = function (obj, callback) {
        console.log("remove...");

        $.ajax({
            type:'delete',
            utl:'/replies/'+obj.bno+"/"+obj.rno,
            data:JSON.stringify(obj),
            dataType:'json',
            contentType:"application/json" ,
            success:callback
        });
    };

    return{
        getAll:getAll,
        add:add,
        update:update,
        remove:remove
    }

})();
