<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<style>
    .container{
        padding-top: 100px;
    }
</style>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <canvas id="chart1" height="200" width="300"></canvas>
        </div>
        <div class="col-md-4">
            <canvas id="chart2" height="200" width="300"></canvas>
        </div>
        <div class="col-md-4">
            <canvas id="chart3" height="200" width="300"></canvas>
        </div>
        <div class="col-md-4">
            <canvas id="chart4" height="200" width="300"></canvas>
        </div>
    </div>
</div>

<script>
    var ctx1 = $('#chart1');
    var ctx2 = $('#chart2');
    var ctx3 = $('#chart3');
    var ctx4 = $('#chart4');


        new Chart(ctx1, {
            type: 'doughnut',
            data: {
                labels: ["总的收入 ${incomeAll}", "今日收入 ${incomeDay}"],
                datasets: [
                    {
                        data: [${incomeAll}, ${incomeDay}],
                        backgroundColor: [
                            "#FF6384",
                            "#36A2EB"
                        ],
                        hoverBackgroundColor: [
                            "#FF6384",
                            "#36A2EB"
                        ]
                    }]
            },
            options:{
                tooltips:{
                    enabled:false
                }
            }
        });


        new Chart(ctx2, {
            type: 'doughnut',
            data: {

                labels: ["全部订单 ${countOrderAll}", "完成的订单 ${countOrderDone}", "今日订单 ${countOrderDay}"],
                datasets: [
                    {
                        data: [${countOrderAll}, ${countOrderDone}, ${countOrderDay}],
                        backgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                        ],
                        hoverBackgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                        ]
                    }]
            },
            options:{
                tooltips:{
                    enabled:false
                }
            }
        });
        new Chart(ctx3, {
            type: 'doughnut',
            data: {
                labels: ["在售的 ${countGoodsUse}", "下架的 ${countGoodsUnUse}", "即将售罄的（少于5件） ${countGoodsZero}"],
                datasets: [
                    {
                        data: [${countGoodsUse}, ${countGoodsUnUse}, ${countGoodsZero}],
                        backgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                        ],
                        hoverBackgroundColor: [
                            "#FF6384",
                            "#36A2EB",
                            "#FFCE56"
                        ]
                    }]
            },
            options:{
                tooltips:{
                    enabled:false
                }
            }
        });

    new Chart(ctx4, {
        type: 'doughnut',
        data: {
            labels: ["粉丝 ${countFensi}"],
            datasets: [
                {
                    data: [${countFensi}],
                    backgroundColor: [
                        "#FF6384"
                    ],
                    hoverBackgroundColor: [
                        "#FF6384"
                    ]
                }]
        },
        options:{
            tooltips:{
                enabled:false
            }
        }
    });

</script>