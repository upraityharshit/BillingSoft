//  Chart.js Script with Dummy Data 
// Dummy monthly count data (January to December)
const labels = [
    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
];
const data = [8, 12, 10, 6, 14, 9, 11, 15, 7, 13, 5, 6];

// Define colors for each month (12 colors)
const backgroundColors = [
    '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0',
    '#9966FF', '#FF9F40', '#E7E9ED', '#8B0000',
    '#00CED1', '#FF4500', '#2E8B57', '#4682B4'
];

const ctx = document.getElementById('monthlyChart').getContext('2d');
new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels,
        datasets: [{
            label: 'Purchase Orders Per Month',
            data: data,
            backgroundColor: 'linear-gradient(115deg, #a19ae6d2, #6e8de0);',
            borderColor: 'rgba(74, 84, 228, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
                labels: {
                    generateLabels: function(chart) {
                        const labels = Chart.defaults.plugins.legend.labels.generateLabels(chart);
                        labels.forEach(label => {
                            if (label.text === 'Purchase Orders Per Month') {
                                label.fillStyle = 'rgba(74, 84, 228, 1)'; // solid color for label box
                            }
                        });
                        return labels;
                    }
                }
            },
            title: {
                display: true,
                text: 'Monthly Purchase Orders'
            }
        },
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    stepSize: 1
                }
            }
        }
    }
});

const ctx1 = document.getElementById('monthlyChart1').getContext('2d');
new Chart(ctx1, {
    type: 'line',
    data: {
        labels: labels,
        datasets: [{
            label: 'Orders Return Per Month',
            data: data,
            backgroundColor: 'linear-gradient(115deg, #7cc472d2, #a2d875);',
            borderColor: 'rgba(120, 235, 149, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
                labels: {
                    generateLabels: function(chart) {
                        const labels = Chart.defaults.plugins.legend.labels.generateLabels(chart);
                        labels.forEach(label => {
                            if (label.text === 'Orders Return Per Month') {
                                label.fillStyle = 'rgba(120, 235, 149, 1)'; // solid color for label box
                            }
                        });
                        return labels;
                    }
                }
            },
            title: {
                display: true,
                text: 'Monthly Orders Returns'
            }
        },
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    stepSize: 1
                }
            }
        }
    }
});

const ctx2 = document.getElementById('monthlyChart2').getContext('2d'); 
new Chart(ctx2, {
    type: 'doughnut',
    data: {
        labels: labels,
        datasets: [{
            label: 'Gross Sale Per Month',
            data: data,
            backgroundColor: backgroundColors,
            borderColor: 'white',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'bottom', // show labels under the chart
                labels: {
                    boxWidth: 20,
                    padding: 15
                }
            },
            title: {
                display: true,
                text: 'Monthly Orders Gross Sale'
            }
        }
    }
});