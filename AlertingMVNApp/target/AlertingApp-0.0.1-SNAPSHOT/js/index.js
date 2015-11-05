var margin ={top:50, right:50, bottom:30, left:20},
    width=700-margin.left - margin.right, 
    height=400-margin.top-margin.bottom;

// scale to ordinal because x axis is not numerical
var x = d3.scale.ordinal().rangeRoundBands([0, width], .9);

//scale to numerical value by height
var y = d3.scale.linear().range([height, 2]);

var chart = d3.select("#chart")  
              .append("svg")  //append svg element inside #chart
              .attr("width", width+(2*margin.left)+margin.right)    //set width
              .attr("height", height+margin.top+margin.bottom);  //set height
var xAxis = d3.svg.axis()
              .scale(x)
              .orient("bottom");  //orient bottom because x-axis will appear below the bars

var yAxis = d3.svg.axis()
              .scale(y)
              .orient("left");

d3.json("report.jsp", function(error, data){
  x.domain(data.map(function(d){ return d.letter}));
  y.domain([0, d3.max(data, function(d){return d.frequency})]);
  
  var bar = chart.selectAll("g")
                    .data(data)
                  .enter()
                    .append("g")
                    .attr("transform", function(d, i){
                      return "translate("+x(d.letter)+", 0)";
                    });
  
  bar.append("rect")
      .attr("y", function(d) { 
        return y(d.frequency); 
      })
      .attr("x", function(d,i){
        return x.rangeBand()+(margin.left/2);
      })
      .attr("height", function(d) { 
        return height - y(d.frequency); 
      })
      .attr("width", x.rangeBand());  //set width base on range on ordinal data

  bar.append("text")
      .attr("x", x.rangeBand()+margin.left )
      .attr("y", function(d) { return y(d.frequency) -10; })
      .attr("dy", ".75em")
      .text(function(d) { return d.frequency; });
  
  chart.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate("+margin.left+","+ height+")")        
        .call(xAxis);
  
  chart.append("g")
        .attr("class", "y axis")
        .attr("transform", "translate("+margin.left+",0)")
        .call(yAxis)
        .append("text")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", ".71em")
        .style("text-anchor", "end")
        .text("Frequency");
});

function type(d) {
    d.letter = +d.letter; // coerce to number
    return d;
  }