// document.getElementById("cancel-button").addEventListener("click", function () {
//     history.back();
// });


function editNews() {
    var ids = []; // Mảng lưu trữ các newsId đã được chọn

    // Lặp qua tất cả các checkbox và kiểm tra xem chúng có được chọn hay không
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            var newsId = checkbox.getAttribute('data-news-id');
            ids.push(newsId);
        }
    });


    if (ids.length > 0) {
        var url = '/news-management/update_news/' + ids.join(',');
        window.location.href = url; // Chuyển hướng đến trang cập nhật với các newsId đã chọn
    } else {

        alert('Please select at least one news to update.');
    }
}


function deleteNews() {
    var selectedNews = document.querySelectorAll('input[name="newsIds"]:checked');

    if (selectedNews.length > 0) {
        // Tạo một mảng chứa các ID của tin tức được chọn
        var selectedIds = Array.from(selectedNews).map(function (checkbox) {
            return checkbox.getAttribute('data-news-id');
        });

        var params = new URLSearchParams();
        selectedIds.forEach(function (id) {
            params.append('newsIds', id);
        });

         if(confirm("Do you want to delete the record?")){
         }else {
             window.location.href = '/news-management/news_list';
         }
// Sử dụng fetch để gửi yêu cầu POST với các tham số URL
        fetch('/news-management/delete_news?' + params.toString(), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .then(function (response) {
                if (response.ok) {

                    window.location.href = '/news-management/news_list'; // Chuyển hướng về danh sách tin tức
                }
            })

    } else {
        alert("No data delete!");
    }
}


$('select').on('change', () => {
    var selectedOptionValue = $('select').val(); // Lấy giá trị của option được chọn
    window.location.href = '/news-management/news_list?pageSize=' + selectedOptionValue;
});


document.addEventListener("DOMContentLoaded", function () {
    var checkboxes = document.querySelectorAll('tbody input[type="checkbox"]');
    var checkAll = document.getElementById('checkAll');

    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            // Xử lý khi checkbox trong tbody thay đổi
        });
    });

    checkAll.addEventListener('change', function () {
        checkboxes.forEach(function (checkbox) {
            checkbox.checked = checkAll.checked;
        });
    });
});


var alertElement = document.querySelector('.alert');
alertElement.style.display = 'block';
setTimeout(function() {
    alertElement.style.display = 'none';
}, 5000); // Ẩn sau 5 giây
