//存放主要交互逻辑的js代码
// javascript 模块化(package.类.方法)

var app = {

    //封装相关ajax的url
    URL: {
        now: function () {
            return '/app/time/now';
        },
        edit:function(url){
            return url + '/eps/opt/edit';
        }
    },
    //list页逻辑
    list: {
        //list页初始化
        init: function (params) {
            //alert("hello");
            $('#submitBtn').click(function () {
                var url = app.URL.edit(params.baseUrl);
                alert(url);
                $.post(app.URL.edit(params.baseUrl), {}, function (result) {
                    if (result && result['success']) {
                        var exposer = result['data'];
                        alert("a");
                    }
                    else
                    {
                        alert("b");
                    }
                });
            });



            $('.editBtn').click(function () {
                var tid = $(this).attr('item');
                console.log("tid: " + tid);
                var editModal = $('#editModal');

                $('#edit_tid').val(tid);
                $('#edit_psam').val($(('#psamTid_'+tid)).html());
                $('#edit_ip').val($(('#ip_'+tid)).html());
                $('#edit_port').val($(('#port_'+tid)).html());

                editModal.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });

            });

        }
    }
}