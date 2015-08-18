// main.js

// stores the current and previous column to be sorted by
var currentCol = -1;
var prevCol    = -1;
var sortDown   = -1;
var theForm    = '';
var useForm    = false;


function CompareAlpha(a, b) {
	// this one works with ASCII values
	if (a[currentCol] < b[currentCol]) { return -1; }
	if (a[currentCol] > b[currentCol]) { return 1;  }
	return 0;
}

function CompareAlphaIgnore(a, b) {
	// this one ignores the difference between lowercase and uppercase chars
	strA = a[currentCol].toLowerCase();
	strB = b[currentCol].toLowerCase();
	if (strA < strB) { return -1; }
	else {
		if (strA > strB) { return 1; }
		else { return 0; }
	}
}

function CompareDate(a, b) {
	// this one works with date formats conforming to Javascript specifications, e.g. mm/dd/yyyy
	datA = new Date(a[currentCol]);
	datB = new Date(b[currentCol]);
	if (datA < datB) { return -1; }
	else {
		if (datA > datB) { return 1; }
		else { return 0; }
	}
}

function CompareDateEuro(a, b) {
	// this one works with european date formats, e.g. yyyy-mm-dd
	strA = a[currentCol].split("-");
	strB = b[currentCol].split("-")
	datA = new Date(strA[0], strA[1], strA[2]);
	datB = new Date(strB[0], strB[1], strB[2]);
	if (datA < datB) { return -1; }
	else {
		if (datA > datB) { return 1; }
		else { return 0; }
	}
}

function CompareNumeric(a, b) {
	// this one compares numbers
	numA = a[currentCol];
	numB = b[currentCol];
	if (isNaN(numA)) { return 0;}
	else {
		if (isNaN(numB)) { return 0; }
		else { return numA - numB; }
	}
}

function TableSort(myCol, mySort) {

	// Global vars for TableSort() fx
	var checkBoxName;	// holds the checkboxes' name property
	var linkImg = '';	// holds the link image

	// Remove and then put up/down arrow into theHeader
	if (prevCol!=-1) {
		var str = document.getElementById('theHeader').rows[0].cells[prevCol].innerHTML;
		if(IS_MOZILLA) str=str.substring(0, str.indexOf("<img"));
		else str=str.substring(0, str.indexOf("<IMG"));
		document.getElementById('theHeader').rows[0].cells[prevCol].innerHTML=str;
	}
	if (prevCol!=myCol) {
		prevCol=myCol;
		sortDown=0;
	} else {
		sortDown=sortDown ? 0 : 1;
	}
	if (!sortDown) document.getElementById('theHeader').rows[0].cells[myCol].innerHTML+='<img src="img/uparrow.gif"/>';
	else document.getElementById('theHeader').rows[0].cells[myCol].innerHTML+='<img src="img/downarrow.gif"/>';


	// (1) Create a two-dimensional array and fill it with the table's content
	// NOTE: the name of the table to be sorted=theRows
	var mySource = document.getElementById('theRows');
	var myRows = mySource.rows.length;
	var myCols = mySource.rows[0].cells.length;
	currentCol = myCol;
	var done   = '<fmt:message key="com.vh.label.done"/>';
	//alert(done);
	myArray = new Array(myRows);
	for (i=0; i < myRows; i++) {
		myArray[i] = new Array(myCols+1);
			
		// column #1: stores whether the checkbox is checked and disabled
		var str = mySource.rows[i].cells[0].innerHTML.toLowerCase();
		if(mySource.rows[i].cells[0].childNodes[0].checked || str.indexOf("checked") > 0) myArray[i][0]='checked="checked"';
		else myArray[i][0] = '';
		if(mySource.rows[i].cells[0].childNodes[0].disabled || str.indexOf("disabled")>0) myArray[i][0]+=' disabled="true"';
		str = str.substring(str.indexOf("name="));
		if(IS_MOZILLA) str = str.substring(0, str.indexOf(' '));
		else str = str.substring(0, str.indexOf('>'));
		checkBoxName = str; // checkBoxName = 'name="userId"';
		//alert(str);

		for (j=1; j < myCols; j++) {
			myArray[i][j] = mySource.rows[i].cells[j].innerText.trim();
		}

		// last column: stores % info
		if(!useForm) {
			str = mySource.rows[i].cells[myCols-1].innerHTML.toLowerCase();
			str = str.substring(str.indexOf("<td"), str.indexOf("</td>"));
			str = str.substring(str.indexOf("width=")+7);
			str = str.substring(0, str.indexOf("%"));
			if(str!='') { //str contains % info here, eg: str=70
				myArray[i][myCols-1]=str;
				if(str==99) done=mySource.rows[i].cells[myCols-1].innerText;
			} else {
				myArray[i][myCols-1]=0;
			}
		}

		// last+1 column: stores href info
		str = mySource.rows[i].cells[1].innerHTML;
	    linkImg = str.substring(str.indexOf("<img"), str.indexOf(">")+1);
		str = str.substring(str.indexOf(">")+1);
		str = str.substring(str.indexOf("href="), str.indexOf(">"));
		myArray[i][myCols]=str;
	}
	
	// (2) Sort the two-dimensional array
	switch (mySort) {
		case 0:
			myArray.sort(CompareAlpha);
			break;
		case 1:
			myArray.sort(CompareNumeric);
			break;
		case 2:
			myArray.sort(CompareAlphaIgnore);
			break;
		case 3:
			myArray.sort(CompareDate);
			break;
		case 4:
			myArray.sort(CompareDateEuro);
			break;
		case 5:
			myArray.sort(CompareSongLength);
			break;
		default:
			alert("WARNING: default case");
			myArray.sort(CompareAlpha);
			break;
	}
	if (sortDown) {
		myArray.reverse(); // this is for reverse sortings
	}	


	// (3) Re-write the table contents: array --> back to the table
	for (i=0; i < myRows; i++) {
	
		// column #1
		mySource.rows[i].cells[0].innerHTML	= '<input type="checkbox" '+myArray[i][0]+' '+checkBoxName+' value="'+myArray[i][1]+'" onclick="Toggle(this, '+((i+1)%2)+');"></input>';
		// column #2
		mySource.rows[i].cells[1].innerHTML	= linkImg+'&nbsp;&nbsp;'+'<a '+myArray[i][myCols]+'>'+myArray[i][1]+'</a>';
		
		for (j=2; j < myCols; j++) {
			mySource.rows[i].cells[j].innerText = myArray[i][j];
		}

		// last column
		if(!useForm) {
			if((myArray[i][myCols-1] > 0) && (myArray[i][myCols-1] != 99)) {
				mySource.rows[i].cells[myCols-1].innerHTML = '<table border="0" cellpadding="0" cellspacing="0"><tr><td width="'+myArray[i][myCols-1]+'%" class="progress" title="'+myArray[i][myCols-1]+'%"></td><td width="1" class="noprogress">&nbsp;</td></tr></table>';
			} else if(myArray[i][myCols-1]==99) {
				mySource.rows[i].cells[myCols-1].innerHTML = '<table border="0" cellpadding="0" cellspacing="0"><tr><td width="99%" class="noprogress">'+done+'</td><td width="1" class="noprogress">&nbsp;</td></tr></table>';
			} else {
				mySource.rows[i].cells[myCols-1].innerHTML = '<table border="0" cellpadding="0" cellspacing="0"><tr><td width="1" class="noprogress">&nbsp;</td></tr></table>';
			}
		}

		// altRows
		if(myArray[i][0].indexOf("checked") > 0) colorRow(mySource.rows[i].cells[0].childNodes[0]);
		else clearRow(mySource.rows[i].cells[0].childNodes[0], (i+1)%2);
	}		
}


// colors one table row (change tr class to "H")
function colorRow(V) {
	while(V.tagName!="TR") V=V.parentNode;
	V.className = "H";
}


// uncolors one table row (change tr class from "trhighlight" to "trodd" or "treven")
function clearRow(V, odd) {
	while(V.tagName!="TR") V=V.parentNode;
	if(odd) V.className = "trodd";
	else V.className = "treven";
}



// check OR uncheck all checkboxes
function ToggleAll() {
	if (useForm) {
		for(var i = 0, j = 1; i < theForm.elements.length; i++) {
			var v = theForm.elements[i];
            if((v.name!='toggleAll') && (v.type=='checkbox')) {
				if(v.disabled) j++;
				else {
					v.checked = theForm.toggleAll.checked;
					if(theForm.toggleAll.checked) colorRow(v);
					else clearRow(v, j++%2);
					if(theForm.name=='projectform') changeDropDown(v);
				}
            }
        }
	} else {
		var rows = document.getElementById('theRows').rows;
		for(var i = 0, j = 1; i < rows.length; i++) {
			var v = rows[i].cells[0].childNodes[0];
			if((v.name!='toggleAll') && (v.type=='checkbox')) {
				if(v.disabled) j++;
				else {
					v.checked = theForm.toggleAll.checked;
					if(theForm.toggleAll.checked) colorRow(v);
					else clearRow(v, j++%2);
					if(theForm.name=='projectform') changeDropDown(v);
				}
			}
		}
	}
}

// figure out whether ToggleAll checkbox should be checked too
function checkToggleAll() {
	var TV = TW = 0;

	// TV=how many checkboxes on the form
	// TW=how many CHECKED checkboxes on the form
	var rows = document.getElementById('theRows').rows;
	for(var i = 0; i < rows.length; i++) {
		var v = rows[i].cells[0].childNodes[0];
		if((v.name!='toggleAll') && (v.type=='checkbox')) {
			TV++;
			if(v.checked) TW++;
		}
	}
	if((TW==TV) && (TW > 0)) theForm.toggleAll.checked = true;
	else theForm.toggleAll.checked = false;
}

// checks one checkbox, and colors OR clears the table row
function Toggle(CB, odd) {
	if(CB.checked) colorRow(CB);
	else clearRow(CB, odd);
	checkToggleAll();
}


// color rows that were checked.  This function is called after column sort to recolor rows.
function altRows() {
	if (useForm) {
		for(var i = 0, j = 1; i < theForm.elements.length; i++) {
			var v = theForm.elements[i];
            if((v.name!='toggleAll') && (v.type=='checkbox')) {
				if(v.checked) colorRow(v);
                else clearRow(v, j++%2);
            }
        }
	} else {
		var rows = document.getElementById('theRows').rows;
		for(var i = 0, j = 1; i < rows.length; i++) {
			var v = rows[i].cells[0].childNodes[0];
			if((v.name!='toggleAll') && (v.type=='checkbox')) {
				if(v.checked) colorRow(v);
				else clearRow(v, j++%2);
			}
		}
	}
	checkToggleAll();
}


// objTypes:  1-task, 2-user, 3-project
function deleteObjects(objType) {
    if(objType==1) {
		var form = top.frames[2].document.getElementById('deleteform');
		var rows = top.frames[2].document.getElementById('theRows').rows;
	} else {
		var form = document.getElementById('deleteform');
	}

	if(form) {
		var rowCount = 0;
		if (objType==1) {
			rowCount = rows.length;
			for(var i = 0; i < rows.length; i++) {
				var v = rows[i].cells[0].childNodes[0];
				if((v.name!='toggleAll') && (v.type=='checkbox') && (v.checked)) break;
			}
		} else {
			var inputs = form.getElementsByTagName('INPUT');
			rowCount = inputs.length;
			for(var i = 0; i < inputs.length; i++) {
                var v = inputs[i];
                if((v.name!='toggleAll') && (v.type=='checkbox') && (v.checked)) break;
            }
		}
		if(i==rowCount) {
			if(objType==1) alert(LABEL_DELTASKS1);
			else if(objType==2) alert(LABEL_DELUSERS1);
			else alert(LABEL_DELPROJECTS1);
		} else {
			if(objType==1) var sure = confirm(LABEL_DELTASKS2);
			else if(objType==2) var sure = confirm(LABEL_DELUSERS2);
			else var sure = confirm(LABEL_DELPROJECTS2);
			if(sure) form.submit();
		}
	} else {
		if(objType==1) alert(LABEL_DELTASKS3);
		else if(objType==2) alert(LABEL_DELUSERS3);
		else alert(LABEL_DELPROJECTS3);
	}
}


// changes the dropdown on the editproj page whenever a checkbox next to a username is checked/unchecked
function changeDropDown(CB) {
	var sel = CB.parentNode.parentNode.getElementsByTagName('SELECT')[0];
	if(CB.checked) sel.selectedIndex = 1;
	else sel.selectedIndex = 0;
}


// changes the checkbox on the editproj page whenever a dropdown (project access) is changed
function changeCheckbox(sel, odd) {
	var CB = sel.parentNode.parentNode.getElementsByTagName('INPUT')[0];
	if(sel.options[sel.selectedIndex].value == -1) CB.checked = false;
	else CB.checked = true;
	Toggle(CB, odd);
}


// fix URL for production deployment
function fixURL(form) {
	if(form.action.search("http://localhost") == -1) form.action = form.action.substring(form.action.lastIndexOf("/"));
}


// initializes global vars on the page for later use in altRows(), TableSort().
function initPage(form, use) {
	theForm = form;
	useForm = use;
	fixURL(form);
	altRows();
}
