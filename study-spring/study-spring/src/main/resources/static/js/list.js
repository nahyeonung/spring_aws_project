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
                    str+= "<td>" + result[i].time + "</td>";
                    str+= "<td>" + result[i].date + "</td>";
                    str+= "<td><a href='/members/content?id="+`${result[i].idx}`+"' id="+`${result[i].idx}`+">내용보기</a><td>"
                    str+="</tr>"
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
//function test(id){
//    $.ajax({
//        type="POST",
//        url:'/members/getBoardList',
//        success: function(){
//
//        }
//    })
//}
