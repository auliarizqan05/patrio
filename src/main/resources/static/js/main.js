 $(document).ready(function() {
    $("#listDetail").click(function (event) {

    alert('TEST2');
    var content = $("#content")
    event.preventDefault();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/extract-rss/listDetail",
            data: JSON.stringify(content),
            dataType: 'json',
            cache: false,
            timeout: 60000,
            success: function (data) {
            alert('can you hear me');
            }
        });
    });
});

function showCode() {

alert('TEST2');
    //var code = /*[[${code}]]*/ '12345';
      var code = /*[[#messages.msg('form.description')]]*/ 'descr';

    document.getElementById('code').innerHTML = code;
}
