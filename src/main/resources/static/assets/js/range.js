const slider = document.getElementById("myRange");
const output = document.getElementById("demo");
output.innerHTML = slider.value+".000" +"vnd";

slider.oninput = function() {
  output.innerHTML = this.value+".000" +"vnd";
}
