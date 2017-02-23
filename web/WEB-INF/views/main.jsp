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
        <div class="col-md-4">
            <canvas id="chart5" height="200" width="300"></canvas>
        </div>
    </div>
</div>

<script>
    var ctx1 = $('#chart1');
    var ctx2 = $('#chart2');
    var ctx3 = $('#chart3');
    var ctx4 = $('#chart4');
    var ctx5 = $('#chart5');

        new Chart(ctx1, {
            type: 'doughnut',
            data: {
                labels: ["全部会员 ${memberCount}", "今天注册的会员 ${memberCountNoDay}", "入驻的商家 ${countSj}"],
                datasets: [
                    {
                        data: [${memberCount}, ${memberCountNoDay}, ${countSj}],
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


        new Chart(ctx2, {
            type: 'doughnut',
            data: {

                labels: ["全部商品 ${countGoodsAll}", "商家的商品 ${countGoods1}", "商城的商品 ${countGoods2}"],
                datasets: [
                    {
                        data: [${countGoodsAll}, ${countGoods1}, ${countGoods2}],
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
                labels: ["全部订单 ${countOrderAll}", "已完成订单 ${countOrderDone}", "今日订单统计 ${countOrderDay}"],
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

    new Chart(ctx4, {
        type: 'doughnut',
        data: {
            labels: ["全部定向卡会员 ${memberCountDxk}", "今日定向卡会员 ${memberCountNoDayDxk}"],
            datasets: [
                {
                    data: [${memberCountDxk}, ${memberCountNoDayDxk}],
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

    new Chart(ctx5, {
        type: 'doughnut',
        data: {
            labels: ["商城评论 ${dianpuCommentCount}", "商品评论 ${goodsCommentCount}"],
            datasets: [
                {
                    data: [${dianpuCommentCount}, ${goodsCommentCount}],
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

</script>