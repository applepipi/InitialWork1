$(function(){
    /**
     * 初始化提示信息、验证表单
     */
    showTip();
    Pagination(1,{});

    /**
     * 取消提示
     */
    function showTip(){
        setTimeout(function(){
            $('.J_tip').hide();
        }, 2000);
    }

    /**
     * 分页
     */
    $('#pageLimit').bootstrapPaginator({
        //currentPage: 3,
        totalPages: $('.pageDataCount').val(),
        size: "small",
        bootstrapMajorVersion: 3,
        alignment: "right",
        numberOfPages: 5,
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first": return "首页";
                case "prev": return "<";
                case "next": return ">";
                case "last": return "末页";
                case "page": return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {
            var
                form = $(".J_searchForm").serializeObject();
            Pagination(page, form);
        }
    });
    /**
     * 分页刷数据
     */
    function Pagination(page, extraData){

        var
            currentPage = page,
            str = '',
            data = {
                page: currentPage
            };
        jQuery.extend(data, extraData);

        $.ajax({
            type: "GET",
            url: "/admin/user/ajax/list",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: data,     //JSON.stringify
            dataType: "json",
            success: function (rs) {
                $('.pageDataCount').val(rs.dataCount);
                $('#pageLimit').bootstrapPaginator({totalPages: $('.pageDataCount').val()});
                $('#J_template').empty();
                if( rs.code == 0){
                    $.each(rs.list, function(index, item){
                        str += '<tr>\
                                    <td>'+ item.number +'</td>\
                                    <td>'+ item.clientName +'</td>\
                                    <td>'+ item.genderName +'</td>\
                                    <td>'+ item.clientIdcardNum +'</td>\
                                    <td>'+ item.clientTel +'</td>\
                                    <td>'+ item.clientType +'</td>\
                                    <td>'+ item.firstVisitType +'</td>\
                                    <td>'+ item.visitTimes +'</td>\
                                    <td>'+ new Date(item.visitTime).format("yyyy-MM-dd") +'</td>\
                                    <td>'+ item.cost +'</td>\
                                    <td>\
                                        <a href="/admin/client/visit/detailList?clientId='+ item.clientId +'" class="label-info"><i class="fa fa-search"></i>&nbsp;详情</a>\
                                    </td>\
                                </tr>'
                    });
                    $('#J_template').append(str);
                }else{
                    location.reload();
                }

            },
            error: function (message) {
                location.reload();
            }
        });
    }

    /**
     * 模糊匹配-可输入也可下拉选择
     */
    $('#editable-select1').editableSelect1({
        effects: 'slide'
    });
    $('#editable-select2').editableSelect2({
        effects: 'slide'
    });
    $('#editable-select3').editableSelect3({
        effects: 'slide'
    });
    $('#html1').editableSelect1();
    $('#html2').editableSelect2();
    $('#html3').editableSelect3();

    /**
     * 姓名-模糊匹配-keyup事件
     */
    $('.J_selectName').keyup(function(){
        var
            clientName = $('input.J_selectName').val();

        $(".es-list1").empty();
        $.ajax({
            type: "GET",
            url: "/admin/client/visit/ajax/clientName",
            contentType: "application/json; charset=utf-8",
            data: {clientName: clientName},     //JSON.stringify
            dataType: "json",
            success: function (rs) {
                var
                    li = "";

                $(rs.list).each(function(key, item){
                    li +='<li class="es-visible" style="display: block;">' + item.clientName + '</li>';
                    // console.log(li);
                });
                $(".es-list1").append(li);
            },
            error: function (errMsg) {
                console.log(errMsg);
            }
        });
    });

    /**
     * 身份证-模糊匹配-keyup事件
     */
    $('.J_selectId').keyup(function(){
        var
            idcard = $('input.J_selectId').val();

        $(".es-list2").empty();
        $.ajax({
            type: "GET",
            url: "/admin/client/visit/ajax/idcard",
            contentType: "application/json; charset=utf-8",
            data: {idcard: idcard},     //JSON.stringify
            dataType: "json",
            success: function (rs) {
                var
                    li = "";

                $(rs.list).each(function(key, item){
                    li +='<li class="es-visible" style="display: block;">' + item.idcard + '</li>';
                    // console.log(li);
                });
                $(".es-list2").append(li);
            },
            error: function (errMsg) {
                console.log(errMsg);
            }
        });
    });

    /**
     * 电话-模糊匹配-keyup事件
     */
    $('.J_selectPhone').keyup(function(){
        var
            tel = $('input.J_selectPhone').val();

        $(".es-list3").empty();
        $.ajax({
            type: "GET",
            url: "/admin/client/visit/ajax/tel",
            contentType: "application/json; charset=utf-8",
            data: {tel: tel},     //JSON.stringify
            dataType: "json",
            success: function (rs) {
                var
                    li = "";

                $(rs.list).each(function(key, item){
                    li +='<li class="es-visible" style="display: block;">' + item.tel + '</li>';
                    // console.log(li);
                });
                $(".es-list3").append(li);
            },
            error: function (errMsg) {
                console.log(errMsg);
            }
        });
    });

    /**
     * 列表点击搜索事件
     * @param  {[type]} ){                     var                                                            form [description]
     * @return {[type]}     [description]
     */
    $(".J_search").click(function(){
        var
            form = $(".J_searchForm").serializeObject();

        Pagination(1, form);
    });
});