import {api, utills} from '/static/js/common/module/common_module.js';
import {ChartJs} from "/static/js/class/ChartJs.js";

$(function () {

    const labels = ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'];
    const datasets = [
        {
            label: 'Test1',
            data: [12, 19, 3, 5, 2, 3],
            borderWidth: 1
        }
    ];

    const employee = document.getElementById('employeeChart');
    const employeeChart = new ChartJs(employee, 'line', labels, datasets);
    employeeChart.initializeChart();

    const revenue = document.getElementById('revenueChart');
    const revenueChart = new ChartJs(revenue, 'line', labels, datasets);
    revenueChart.initializeChart();


});

const func = {}