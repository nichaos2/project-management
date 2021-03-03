// get the data as a string
var chartData = decodeHtml(chartData);

// convert the string to a JSON object
var chartJsonArray = JSON.parse(chartData);
// console.log(chartJsonArray);

// take the chartJson Array and populate new arrays with the data
// this helps later so we do not write each data one by one
var numericData = [];
var labelData = [];

for (i = 0; i < chartJsonArray.length; i++) {
	numericData[i] = chartJsonArray[i].stageCnt;
	labelData[i] = chartJsonArray[i].stage;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), { // target the DOM element in the html.
	// The type of chart we want to create
	type: 'pie',

	// The data for our dataset
	data: {
		labels: labelData,
		datasets: [{
			label: 'Project Statuses',
			backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
			//borderColor: 'rgb(255, 99, 132)',
			data: numericData
		}]
	},

	// Configuration options go here
	options: {
		title: {
			diplay: true,
			text: 'Project Statuses'
		}
	}
});

// decodes the values from the html to a simplified string
// returns [{"value": the value, "label" : the attribute }]
function decodeHtml(html) {
	var txt = document.createElement("textarea")
	txt.innerHTML = html;
	return txt.value;
}