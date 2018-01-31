function createChart(chartID, xAxisData, yAxisData1, yAxisData2) {
	var myChart = {
	  "type": "line",
	  "title": {
	    "text": "College Placement Statistics!!"
	  },
	  "plot": {
	    "value-box": {
	      "text": "%v"
	    }
	  },
	  "legend": {
		"align": "center",
		"vertical-align": "bottom",
	    "toggle-action": "remove",
	    "draggable": true,
	    "drag-handler": "icon"
	  },
	  "scale-x": {
	    "values": xAxisData
	  },
	  "series": [
	    {
	      "values": yAxisData1,
	      "text": "Total Students",
	      "palette": 0
	    },
	    {
	      "values": yAxisData2,
	      "text": "Total Students Placed",
	      "palette": 1
	    }
	  ]
	};
	zingchart.render({
	  id: chartID,
	  data: myChart
	});	
}