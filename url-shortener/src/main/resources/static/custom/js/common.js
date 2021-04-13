function ajaxPost(url, data, callback) {
    try{
        $.ajax({
            type : "POST"
            , async : true
            , timeout : 30000
            , dataType: "text"
            , cache : false
            , contentType: 'application/json'
            , url : url
            , data : data
            , error : function(jqXHR,textStatus, errorThrown) {
               callback(jqXHR.responseJSON, jqXHR);
            }
            , success : function(data, textStatus, jqXHR){
                callback(data, jqXHR);
            }
        });
    }catch (e) {
        alert("requestAjax :: " + e.message);
    }
}
