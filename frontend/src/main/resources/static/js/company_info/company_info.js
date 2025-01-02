import {api, utills} from '/static/js/common/module/common_module.js';
import {ChartJs} from "/static/js/class/ChartJs.js";
import {KakaoMap} from "/static/js/class/KakaoMap.js";

$(function () {


    const employee = document.getElementById('employeeChart');
    const employeeChart = new ChartJs(employee, 'line', [], []);
    employeeChart.initializeChart();

    const revenue = document.getElementById('revenueChart');
    const revenueChart = new ChartJs(revenue, 'line', [], []);
    revenueChart.initializeChart();

    const addr = document.querySelector("#addr").innerHTML;
    const companyName = document.querySelector(".company-name .name").innerHTML;

    let kakaoMap = new KakaoMap("addrMap", addr, companyName);

    const id = document.querySelector("input[name=companyId]").value;
    api.get("/api/v1/company-info/extra/" + id)
        .then(res => {

            const datasets = {
                employee: [
                    {
                        type: 'bar',
                        label: '사원수',
                        data: res.body.employees.map((employee) => {
                            return employee.emploCnt
                        }),
                        borderWidth: 1
                    },
                    {
                        type: 'line',
                        label: '사원수',
                        data: res.body.employees.map((employee) => {
                            return employee.emploCnt
                        }),
                        borderWidth: 1
                    }
                ],
                revenue: [
                    {
                        type: 'bar',
                        label: '매출액',
                        data: res.body.revenuses.map((revenue) => {
                            return revenue.revenuePay
                        }),
                        borderWidth: 1
                    },
                    {
                        type: 'line',
                        label: '매출액',
                        data: res.body.revenuses.map((revenue) => {
                            return revenue.revenuePay
                        }),
                        borderWidth: 1
                    }
                ],
            };


            const labels = {
                employee: res.body.employees.map((employee) => {
                    return utills.date.toYearMonth(employee.emploDate)
                }),
                revenue: res.body.revenuses.map((revenue) => {
                    return revenue.revenueYear + "년"
                }),
            }

            employeeChart.updateData(labels.employee, datasets.employee);
            revenueChart.updateData(labels.revenue, datasets.revenue);
        })
        .catch(error => console.error(error));

});

const func = {}