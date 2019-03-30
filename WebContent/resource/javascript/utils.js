
/**
 * 显示提示信息
 */
function alert_msg(title="提示", content="") {
	$("#notice_title").html(title);
	$("#notice_content").html(content);
	$("#notice").modal("show");
}