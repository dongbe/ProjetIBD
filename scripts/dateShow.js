/**
 * Created by donatien on 02/04/14.
 */
var $cible = $("#cible");
var spec = $('#spectacle');
function dateShow(){
    $.ajax({
            type:'POST',
            url: '/servlets/ConsulterRepresentationServlet',
        data : spec.value,
            success:function(response){
              $cible.html(response);
            }
    });
}