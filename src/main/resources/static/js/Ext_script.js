// Get the context path from the meta tag
var contextPath = document.querySelector('meta[name="context-path"]').getAttribute('content');

if(contextPath == null || contextPath == undefined || contextPath == "")
	contextPath = "";

//get the menus by selecting menugroup
function getmenus(){
    let menugroup = document.getElementById("menugroupoptions").value;
    let url = contextPath + "/dashboard/user_management/"+menugroup;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            var menuDropdown = document.getElementById("menu");
            menuDropdown.innerHTML = ""; // Clear existing options

            var defaultOption = document.createElement("option");
            defaultOption.value = "select";
            defaultOption.text = "-- Select --";
            defaultOption.disabled = true;
            defaultOption.selected = true;
            menuDropdown.add(defaultOption);

            data.forEach(menu => {
                var option = document.createElement("option");
                option.value = menu;
                option.text = menu;
                menuDropdown.add(option);
            });
        })
        .catch(error => console.error('Error fetching menus:', error));       
}

function getusers(){
	let username = $("#usersoptions").val();
	$("#useredit").prop("disabled",false);
	$.ajax({
		url: contextPath + "/findUser/" + username,
		method: "GET",
		success: function(response){
			$("#name2").val(response.name);
			if(response.islocked)
				$("#islocked").prop("checked",true);
		},
		error: function(error) {
            console.error("Error fetching user data", error);
        }
	});
}

//display show permission block
function showPermissionBlock(){
    let menugroup = document.getElementById("menugroupoptions").value;
    let menu = document.getElementById("menu").value;
    let user = document.getElementById("username").value;
    $("#view").prop("checked",false);
    $("#create").prop("checked",false);
    $("#edit").prop("checked",false);
    $("#delete").prop("checked",false);
    let url =contextPath+"/dashboard/user_management/"+user+"/"+menugroup+"/"+menu;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            if(data.view){
                $("#view").prop("checked",true);
            }
            if(data.create){
                $("#create").prop("checked",true);
            }
            if(data.edit){
                $("#edit").prop("checked",true);
            }
            if(data.delete){
                $("#delete").prop("checked",true);
            }
        })
        .catch(error => console.error('Error fetching menus:', error));

    if(menugroup != 'select' && menu != 'select' && user != 'select'){
        let disp = document.getElementById("disp");
        let disp1 = document.getElementById("disp1");
        disp.classList.remove("disp");
        disp1.classList.remove("disp");
        document.getElementById("menuname").innerHTML = menu;
    }
}
function openPopup(){
    $(".popup").css("visibility", "visible");
    $(".popup").css("transform", "translate(0%, 50%) scale(1)");
    console.log("hello");
    $(".popup1").css("visibility", "visible");
    $(".popup1").css("transform", "translate(-5%, 50%) scale(1)");
    $(".popup2").css("visibility", "visible");
    $(".popup2").css("transform", "translate(-5%, 100%) scale(1)");
}
function closePopup(){
    $(".popup").css("visibility", "hidden");
    $(".popup1").css("visibility", "hidden");
    $(".popup2").css("visibility", "hidden");
}
function deleteHsncode(hsncodeid,hsncode){
   Swal.fire({
      title: "Are you sure?",
      showDenyButton: true,
      confirmButtonText: "Confirm",
      denyButtonText: `Cancel`
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
            url: contextPath+"/dashboard/hsncode/delete/"+hsncode,
            method: "GET",
            success: function(response) {
                if(response) {
                    Swal.fire("Deleted..!", "", "success")
                    .then(()=>{
                        window.location=contextPath+"/dashboard/delete/hsn/"+hsncodeid;
                    });
                }
                else{
                    Swal.fire({
                        icon: "error",
                        title: "HsnCode is already is use..."
                      });
                }
            },
            error: function(error) {
                console.error("Error fetching HSN data", error);
            }
        });
      } else if (result.isDenied) {
        Swal.fire("HsnCode is not Deleted", "", "info");
      }
    });
}

function edithsncode(hsnid,hsncode,gst,desc){
    $(".popup").css("visibility", "visible");
    $(".popup").css("transform", "translate(0%, 70%) scale(1)");

    document.getElementById("heading").innerHTML = "<u>EDIT HSNCODE</u>";
    
    $.ajax({
        url: contextPath+"/dashboard/edit_Hsn/"+hsnid,
        method: "GET",
        success: function(response) {
            $("#status").val("edit");
            $("#hsnid1").val(hsnid);
            $("#floatingInputGroup1").val(hsncode);
            $("#floatingInputGroup2").val(gst);
            $("#floatingInputGroup3").val(desc);
        },
        error: function(error) {
            console.error("Error fetching HSN data", error);
        }
    });

}

function deletewood(woodid,woodname){
    Swal.fire({
       title: "Are you sure?",
       showDenyButton: true,
       confirmButtonText: "Confirm",
       denyButtonText: `Cancel`
     }).then((result) => {
       /* Read more about isConfirmed, isDenied below */
       if (result.isConfirmed) {
        $.ajax({
            url: contextPath+ "/dashboard/checkwood/delete/"+woodname, 
            method: "GET",
            success: function(response) {
                console.log(response);
                if(response) {
                    Swal.fire("Deleted..!", "", "success")
                    .then(()=>{
                        window.location=contextPath+"/dashboard/delete/wood/"+woodid;
                    });
                }
                else{
                    Swal.fire({
                        icon: "error",
                        title: "Wood already in use..."
                      });
                }
            },
            error: function(error) {
                console.error("Error fetching Wood data", error);
            }
        });
       } else if (result.isDenied) {
         Swal.fire("Wood is not Deleted", "", "info");
       }
     });
}
function editwood(woodid,woodname,rate,hsncode,effectivedate,desc){
    $(".popup").css("visibility", "visible");
    $(".popup").css("transform", "translate(0%, 50%) scale(1)");

    document.getElementById("heading").innerHTML = "<u>EDIT WOOD</u>"; 

    $.ajax({
        url: contextPath+"/dashboard/edit_wood/"+woodid,
        method: "GET",
        success: function(response) {
            $("#status").val("edit");
            $("#woodid1").val(woodid);
            $("#woodname").val(woodname);
            $("#rate").val(rate);
            $("#woodhsncode").val(hsncode);
            $("#effectivedate").val(effectivedate);
            $("#description").val(desc);
        },
        error: function(error) {
            console.error("Error fetching data", error);
        }
    });
}
function deletefinyear(finid){
    Swal.fire({
       title: "Are you sure?",
       showDenyButton: true,
       confirmButtonText: "Confirm",
       denyButtonText: `Cancel`
     }).then((result) => {
       /* Read more about isConfirmed, isDenied below */
       if (result.isConfirmed) {
         //Swal.fire("Deleted..!", "", "success")
         Swal.fire("FinYear is not Deleted", "", "info")
         .then(()=>{
             //window.location="/dashboard/delete/finyear/"+finid;
         });
       } else if (result.isDenied) {
         Swal.fire("FinYear is not Deleted", "", "info");
       }
     });
 }
function editfinyear(id,name,fromdate,todate,current,active){
    $(".popup1").css("visibility", "visible");
    $(".popup1").css("transform", "translate(0%, 50%) scale(1)");

    document.getElementById("heading").innerHTML = "<u>EDIT FIN YEAR</u>"; 
    
	$.ajax({
		url: contextPath + "/dashboard/transaction/finyear/estimate",
		method: "GET",
		success: function(response1){
			response1.forEach((data)=>{
				if(data[0] === name){
					let finname = document.getElementById("name");
					finname.readOnly = true;
				}	
			});
		},
        error: function(error) {
            console.error("Error fetching est data", error);
        }
	});
    $.ajax({
        url: contextPath+ "/dashboard/edit_finyear/"+id,
        method: "GET",
        success: function(response) {
            $("#status").val("edit");
            $("#finid").val(id);
            $("#name").val(name);
            $("#fromdate").val(fromdate);
            $("#todate").val(todate); 
            if(current)
                $("#current").prop("checked",true);
            else
                $("#current").prop("checked",false); 
            if(active)
                $("#active").prop("checked",true);
            else
                $("#active").prop("checked",false);   
        },
        error: function(error) {
            console.error("Error fetching data", error);
        }
    });
}

//calculate to date when we select the from date from FINYEAR page
function todatepicker(){
    let fromdate = $("#fromdate").val();
    const [year, month, day] = fromdate.split('-');
    const selectedDate = new Date(year, month - 1, day);

    // Calculate the date one year later
    selectedDate.setFullYear(selectedDate.getFullYear() + 1);
    // Subtract one day from the resulting date
    selectedDate.setDate(selectedDate.getDate() - 1);

    // Format the new date as dd-mm-yyyy
    const nextYearDate =  selectedDate.getFullYear() + '-' +
                        ('0' + (selectedDate.getMonth() + 1)).slice(-2) + '-' + 
                        ('0' + selectedDate.getDate()).slice(-2);
                        

    //console.log('Date after one year: ' + nextYearDate);
    $("#todate").val(nextYearDate);
}

//process selected fin year from the login page 
function loadlogin(currentyear){
    let select = document.getElementById("finyear");
    if(currentyear != "select")
        select.value = currentyear;
    savefinyear();
}
//save selected fin year from the login page
function savefinyear(){

    var inputs = document.querySelectorAll('input');
    inputs.forEach(function(input) {
        input.disabled = false;
    });

    let finname = document.getElementById("finyear").value;
    console.log("Finyear is:" + finname);
    $.ajax({
        url: contextPath +"/save_finyear/"+finname,
        method: "GET",
        success: function(response) {
            console.log(response);
        },
        error: function(error) {
            console.error("Error Saving data", error);
        }
    });
}


function isNumber(input) {
    const regex = /^-?\d+(\.\d+)?(e-?\d+)?$/i;
    return regex.test(input);
}

function numbervalidate(){
    let rate = $("#rate").val();
    if(!isNumber(rate)){
        alert("Please Enter the correct rate")
        //Swal.fire("Please Enter the correct rate", "", "info");
        $("#rate").val("0");
    }    
}

function resetloginpage(){
	window.location = contextPath + "/";
}

$(document).ready(function () {
  	$('.dropdown-submenu').on("click", function (e) {
      e.stopPropagation();
      $(this).next('.dropdown-menu').toggle();
      $(this).parent().siblings().find('.dropdown-menu').hide();     
    });
});

