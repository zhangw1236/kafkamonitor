<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>kafka monitor</title>
    <script src="../js/echarts.min.js"></script>
    <script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>
    <div id="main" style="height:400px"></div>
    <script type="text/javascript">
        var data = [];

        var myChart = echarts.init(document.getElementById('main')); 
		
        var option = {
            title: {
                text: 'kafka monitor',
            },
            
            tooltip: {
                trigger: 'axis',
                formatter: function (params) {
                    params = params[0];
                    var date = new Date(params.name);
                    return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
                },
                axisPointer: {
                    animation: false
                }
            },
            
            xAxis: {
                type: 'time',
                splitLine: {
                    show: false
                }
            },
            
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%'],
                splitLine: {
                    show: false
                }
            },
    
            series: [{
                name: '销量',
                type: 'line',
                showSymbol: false,
                hoverAnimation: false,
                data: data
            }]
        };

        myChart.setOption(option);
		
        setInterval(function () {
            $.post("${ctx}/monitor").done(function(response){
				var jsonResp = eval("("+response+")");
				//data.shift();
                data.push(jsonResp);

                myChart.setOption({
                    series: [{
                        data: data
                    }]
                });
		    });
        }, 1000);
    </script>
</body>