$(document).ready(function(){
	$(".button-collapse").sideNav();
});

function submitFormIfConfirm(form){
	if(confirm("Finir toutes les tâche de l'utilisateur ?"))
		form.submit();
}