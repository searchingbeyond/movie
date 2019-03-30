
/**
 * 当首页加载完成之后，请求后端显示所有的网站列表
 */
$(document).ready(function(){
    $.getJSON("list",function(result){
    	let content = "";
        $.each(result, function(i, field){
        	content += `
				<div class="five wide column">
					<a href="${field['url']}">
						<div><img class="ui avatar image" src="./resource/images/${field['icon']}"></div>
						<div>${field['sitename']}</div>
					</a>
				</div>
				`;
        });
        
        $("#site_box").html(content);
    });
});