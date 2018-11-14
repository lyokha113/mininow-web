
//LOAD IMAGE
function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
      $('#showImage')
      .attr('src', e.target.result)
      .width(100)
      .height(100);
    };

    reader.readAsDataURL(input.files[0]);
  }
}

function newItemExtra() {
  var extraName = document.getElementById("inputExtraName").value;
  var extraPrice = document.getElementById("inputExtraPrice").value;
  if(extraName == "" || extraPrice == ""){
    document.getElementById("errorExtra").innerHTML ='!';
  }else{
   document.getElementById("errorExtra").innerHTML ='';
   var ul = document.getElementById("listExtra");
   var li = document.createElement("li");
   li.appendChild(document.createTextNode("" + extraName +":"+extraPrice));
   ul.appendChild(li);
   document.getElementById("inputExtraName").value = "";
   document.getElementById("inputExtraPrice").value = "";
   li.onclick = removeItem;}
 }

 function newItemRequire() {
  var requireName = document.getElementById("inputRequireName").value;
  var requirePrice = document.getElementById("inputRequirePrice").value;
  if(requireName == "" || requirePrice == ""){
    document.getElementById("errorRequire").innerHTML ='!';
  }else{
   document.getElementById("errorRequire").innerHTML ='';
   var ul = document.getElementById("listRequire");
   var li = document.createElement("li");
   li.appendChild(document.createTextNode("" + requireName +":"+requirePrice));
   ul.appendChild(li);
   document.getElementById("inputRequireName").value = "";
   document.getElementById("inputRequirePrice").value = "";
   li.onclick = removeItem;
 }
}

function removeItem(e) {
  e.target.parentElement.removeChild(e.target);
}
