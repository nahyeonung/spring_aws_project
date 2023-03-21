function getBoardList(){
    $.ajax({
        type: 'GET',
        url: '/members/getBoardList',
        success: function(result){
            console.log("성공", result[0]);
            if(result.length>1){
                for(var i=0; i<result.length; i++){
                    str='<tr>'
                    str+= "<td>" + result[i].title + "</td>";
                    str+="</tr>"
                    console.log(str);
                    $("#study_table").append(str);
                }
            }
        },
        error: function(result){console.log("실패")},
        complete: function(result){
        }
    })
}
getBoardList();