/**
 * Created by user on 2017/12/13.
 */
$("#upload").click(uploadFile);

function uploadFile(){
    //获得上传文件
    $.ajax({
        url: '/fileOperate/upload',
        type: 'POST',
        cache: false,
        data: new FormData($('#uploadForm')),
        processData: false,
        contentType: false,
        success:function(result){
            //var $checkedLi = $("#note_list li a.checked").parent();
            //$checkedLi.data("attachment",result.data);
            alert("上传成功"+result.data);
        }
    });
}