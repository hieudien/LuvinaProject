
function goToPage(path) {
    window.location.href = path;
}

function anThongTin(){
	if(document.getElementById("khungNgay").style.display=="none"){
		document.getElementById("khungNgay").style.display="block";
	}
	else{
		document.getElementById("khungNgay").style.display="none";
	}
}