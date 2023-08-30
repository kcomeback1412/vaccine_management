document.addEventListener("DOMContentLoaded", function () {
    // Lấy tham chiếu đến các phần tử
    const reportRadio = document.getElementById("report");
    const dataTable = document.getElementById("data-table");
    const chartRadio = document.getElementById("chart");

    // Ẩn bảng khi trang được tải
    dataTable.style.display = "none";

    // Thêm xử lý sự kiện khi nút "Report" hoặc "Chart" được thay đổi
    reportRadio.addEventListener("change", function () {
        if (reportRadio.checked) {
            // Hiển thị bảng khi nút "Report" được chọn
            dataTable.style.display = "table"; // hoặc "block"
        }
    });

    chartRadio.addEventListener("change", function () {
        if (chartRadio.checked) {
            // Ẩn bảng khi nút "Chart" được chọn
            dataTable.style.display = "none";
        }
    });
});
