
    let myChart = document.getElementById('myChart').getContext('2d');
    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Arial';
    Chart.defaults.global.defaultFontSize = 15;
    Chart.defaults.global.defaultFontColor = '#777';


    let massPopChart = new Chart(myChart, {
    type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
    data:{
    labels:['January', 'February', 'March', 'April', 'May', 'June','July','August','September','October','November','December'],
    datasets:[{
    label:'Population',
    data:[
    617594,
    181045,
    153060,
    106519,
    105162,
    95072
    ],
    //backgroundColor:'green',
    backgroundColor:[
    'rgba(255, 99, 132, 0.6)',
    'rgba(54, 162, 235, 0.6)',
    'rgba(255, 206, 86, 0.6)',
    'rgba(75, 192, 192, 0.6)',
    'rgba(153, 102, 255, 0.6)',
    'rgba(255, 159, 64, 0.6)',
    'rgba(255, 99, 132, 0.6)'
    ],
    borderWidth:1,
    borderColor:'#777',
    hoverBorderWidth:3,
    hoverBorderColor:'#000'
}]
},
    options:{
    title:{
    display:true,
    text  :'REPORT INJECTION CHART',
    fontSize:25
},
    legend:{
    display:true,
    position:'right',
    labels:{
    fontColor:'#000'
}
},
    layout:{
    padding:{
    left:50,
    right:0,
    bottom:0,
    top:0
}
},
    tooltips:{
    enabled:true
}
}
});


    document.addEventListener("DOMContentLoaded", function () {
        // Lấy tham chiếu đến các phần tử
        const reportRadio = document.getElementById("report");

        reportRadio.addEventListener("change", function () {
            if (reportRadio.checked) {
                // click khi nút "Report" được chọn
                window.location.href = "report_injection_result";
e
            }
        });
    });